package org.springblade.modules.iot.msg.biz;

import cn.hutool.core.bean.BeanUtil;
import org.springblade.basic.utils.ArgumentAssert;
import org.springblade.core.database.mybatis.conditions.Wraps;
import org.springblade.modules.iot.msg.entity.DefMsgTemplate;
import org.springblade.modules.iot.msg.entity.ExtendMsgTemplate;
import org.springblade.modules.iot.msg.service.DefMsgTemplateService;
import org.springblade.modules.iot.msg.service.ExtendMsgTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mqttsnet
 * @date 2022/7/21 0021 18:49
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ExtendMsgTemplateBiz {

    private final ExtendMsgTemplateService extendMsgTemplateService;
    private final DefMsgTemplateService defMsgTemplateService;

    public Boolean importMsgTemplate(List<Long> ids) {
        ArgumentAssert.notEmpty(ids, "请选择消息模板");
        List<DefMsgTemplate> defMsgTemplateList = defMsgTemplateService.listByIds(ids);
        ArgumentAssert.notEmpty(defMsgTemplateList, "请选择正确的消息模板");
        List<String> codeList = defMsgTemplateList.stream().map(DefMsgTemplate::getCode).toList();

        List<ExtendMsgTemplate> existMsgTemplateList = extendMsgTemplateService.list(
                Wraps.<ExtendMsgTemplate>lbQ().in(ExtendMsgTemplate::getCode, codeList));
        Set<String> exists = existMsgTemplateList.stream().map(ExtendMsgTemplate::getCode).collect(Collectors.toSet());

        List<ExtendMsgTemplate> extendMsgTemplateList = new ArrayList<>();
        defMsgTemplateList.forEach(item -> {
            if (!exists.contains(item.getCode())) {
                ExtendMsgTemplate extendMsgTemplate = BeanUtil.toBean(item, ExtendMsgTemplate.class);
                extendMsgTemplateList.add(extendMsgTemplate);
            }
        });

        return extendMsgTemplateService.saveBatch(extendMsgTemplateList);
    }
}
