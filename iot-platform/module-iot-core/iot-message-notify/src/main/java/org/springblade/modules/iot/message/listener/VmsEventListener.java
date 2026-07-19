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

package org.springblade.modules.iot.message.listener;


import org.springblade.modules.iot.api.alert.dto.CallByTtsRequest;
import org.springblade.modules.iot.api.alert.service.RemoteIotChannelVmsService;
import org.springblade.modules.iot.common.utils.JsonUtils;
import org.springblade.modules.iot.message.event.MessageEvent;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springblade.modules.iot.api.alert.dto.VmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * author: EnjoyIot
 * date: 2023-05-08 15:09
 * description:
 **/
@Slf4j
@Component
public class VmsEventListener implements MessageEventListener {
    @Resource
    private RemoteIotChannelVmsService channelVmsStrategy;

    @Override
    @EventListener(classes = MessageEvent.class, condition = "#event.message.channelCode=='VMS'")
    public void doEvent(MessageEvent event) {
        Message message = event.getMessage();
        String channelConfig = message.getChannelConfig();
        VmsConfig vmsConfig = JsonUtils.parseObject(channelConfig, VmsConfig.class);
        CallByTtsRequest callByTtsRequest = new CallByTtsRequest();
        callByTtsRequest.setVmsConfig(vmsConfig);
        callByTtsRequest.setTemplateParam(message.getParam());
        callByTtsRequest.setTemplateId( message.getTemplateCode());
        channelVmsStrategy.callByTts(callByTtsRequest);
    }

}
