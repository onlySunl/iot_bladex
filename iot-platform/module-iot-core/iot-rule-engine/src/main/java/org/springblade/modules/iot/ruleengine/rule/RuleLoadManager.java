

package org.springblade.modules.iot.ruleengine.rule;


import cn.hutool.core.collection.CollectionUtil;
import org.springblade.modules.iot.api.rule.service.RemoteIotRuleService;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.CodecUtil;
import com.alibaba.fastjson.JSON;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RuleLoadManager {

    @Autowired
    private RuleManager ruleManager;

    @Resource
    private RemoteIotRuleService ruleApi;

    final private Map<Long, String> ruleMd5Map = new HashMap<>();

    public RuleLoadManager() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(this::initRules, 1, 30, TimeUnit.SECONDS);
    }

    @SneakyThrows
    public void initRules() {
        int idx = 1;
        int pageSize = 100;
        while (true) {
            RuleInfoPageReqVO pageRequest = new RuleInfoPageReqVO();
            pageRequest.setPageNo(idx);
            pageRequest.setPageSize(pageSize);
            PageResult<RuleInfo> all = ruleApi.selectPage(pageRequest);
            List<RuleInfo> rules = all.getList();
            if (CollectionUtil.isEmpty(rules)) {
                return;
            }

            for (RuleInfo rule : rules) {
                Long ruleId = rule.getId();
                String oldMd5 = ruleMd5Map.get(ruleId);
                String md5 = CodecUtil.md5Str(JSON.toJSONString(rule));
                if (oldMd5 != null && oldMd5.equals(md5)) {
                    continue;
                }

                log.info("rule {} has changed", ruleId);
                ruleMd5Map.put(ruleId, md5);
                refreshRule(rule);
            }
            if(all.getTotal()<pageSize){
                return;
            }
            idx++;
        }
    }

    private void refreshRule(RuleInfo ruleInfo) {
        ruleManager.remove(ruleInfo.getId());
        ruleManager.add(ruleInfo);
    }

}
