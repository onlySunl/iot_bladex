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
package org.springblade.modules.iot.ruleengine.rule;


import org.springblade.modules.iot.ruleengine.action.Action;
import org.springblade.modules.iot.ruleengine.filter.Filter;
import org.springblade.modules.iot.ruleengine.listener.Listener;
import org.springblade.modules.iot.api.rule.dto.TriggerOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    private Long id;

    private String name;

    private List<Listener<?>> listeners;

    private List<Filter<?>> filters;

    private List<Action<?>> actions;

    private Long tenantId;

    /**
     * 触发控制配置（频率限制、延时、告警解除）
     */
    private TriggerOptions triggerOptions;

}
