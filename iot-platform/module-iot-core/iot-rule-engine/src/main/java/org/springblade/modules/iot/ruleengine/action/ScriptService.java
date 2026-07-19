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
package org.springblade.modules.iot.ruleengine.action;

import org.springblade.modules.iot.api.device.service.RemoteIotDeviceService;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springblade.modules.iot.engine.IScriptEngine;
import org.springblade.modules.iot.engine.ScriptEngineFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
public class ScriptService {

    private IScriptEngine scriptEngine = ScriptEngineFactory.getScriptEngine("js");

    private String script;


    private RemoteIotDeviceService deviceApi;

    public void setScript(String script) {
        this.script=script;
        scriptEngine.setScript(script);
    }

    public <T> T execScript(TypeReference<T> type, ThingModelMessage msg) {
        try {
            //取设备信息
            DeviceInfo deviceInfo = deviceApi.getDeviceInfoFromCache(msg.getDeviceId());

            //执行转换脚本
            return scriptEngine.invokeMethod(type, "translate", msg, deviceInfo);
        } catch (Throwable e) {
            log.error("run script error", e);
            return null;
        }
    }
}
