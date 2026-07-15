
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
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
package org.springblade.modules.iot.component.core;

import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.common.thing.ComponentMessage;
import org.springblade.modules.iot.message.core.MqConsumer;
import org.springblade.modules.iot.message.core.MqProducer;
import org.springblade.modules.iot.api.component.ComponentApi;
import org.springblade.modules.iot.api.device.DeviceApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Data
@Component
public class ComponentServices {

    @Resource
    private MqConsumer<ThingModelMessage> consumer;

    @Resource
    private MqProducer<ThingModelMessage> producer;

    @Resource
    private MqProducer<ComponentMessage> componentMessageProducer;

    @Resource
    private MqConsumer<ComponentMessage> componentMessageConsumer;



    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ComponentApi componentApi;

    @Resource
    private DeviceApi deviceApi;

}
