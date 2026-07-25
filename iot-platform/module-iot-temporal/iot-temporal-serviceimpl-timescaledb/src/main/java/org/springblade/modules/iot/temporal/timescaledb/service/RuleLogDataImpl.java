/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot]
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.temporal.timescaledb.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.iot.IRuleLogData;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.temporal.timescaledb.dao.PgRuleLogMapper;
import org.springblade.modules.iot.temporal.timescaledb.model.PgRuleLog;
import org.springblade.modules.iot.api.rule.dto.RuleLog;
import org.postgresql.util.PGTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RuleLogDataImpl implements IRuleLogData {

    @Autowired
    private PgRuleLogMapper ruleLogMapper;

    @Override
    public void deleteByRuleId(Long ruleId) {
        ruleLogMapper.delete(Wrappers.lambdaQuery(PgRuleLog.class).eq(PgRuleLog::getRuleId, ruleId));
    }

    @Override
    public PageResult<RuleLog> findByRuleId(Long ruleId, int page, int size) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNo(page);
        pageParam.setPageSize(size);
        IPage<PgRuleLog> iPage = ruleLogMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                Wrappers.lambdaQuery(PgRuleLog.class).eq(PgRuleLog::getRuleId, ruleId).orderByDesc(PgRuleLog::getTime)
        );
        return new PageResult<>(iPage.getRecords().stream().map(r ->
                        new RuleLog(r.getTime().getTime(), ruleId, r.getState1(),
                                r.getContent(), r.getSuccess(), r.getTime().getTime()))
                .collect(Collectors.toList()), iPage.getTotal());
    }

    @Override
    public void add(RuleLog log) {
        PgRuleLog ruleLog = BeanUtils.toBean(log, PgRuleLog.class);
        ruleLog.setState1(log.getState());
        ruleLog.setTime(new PGTimestamp(System.currentTimeMillis()));
        ruleLogMapper.insert(ruleLog);
    }
}
