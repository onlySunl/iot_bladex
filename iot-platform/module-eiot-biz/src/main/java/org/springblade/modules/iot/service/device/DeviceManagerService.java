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

package org.springblade.modules.iot.service.device;


import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.api.device.dto.DeviceConfig;
import org.springblade.modules.iot.api.device.dto.DeviceGroup;
import org.springblade.modules.iot.api.device.dto.DeviceProperty;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceInfoWithPropertyVO;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceLogPageReqVo;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceTagAddBo;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceGroupPageReqVO;
import org.springblade.modules.iot.controller.admin.device.vo.deviceconfig.DeviceConfigVo;
import org.springblade.modules.iot.controller.admin.device.vo.devicegroup.DeviceAddGroupBo;
import org.springblade.modules.iot.controller.admin.device.vo.devicegroup.DeviceGroupBo;
import org.springblade.modules.iot.controller.admin.device.vo.devicegroup.DeviceGroupImportVo;
import org.springblade.modules.iot.controller.admin.device.vo.devicegroup.GroupImportRespVO;

import java.util.List;

/**
 * @Author: EnjoyIot
 * @Date: 2023/5/31 11:05
 * @Version: V1.0
 * @Description: 设备服务接口
 */
public interface DeviceManagerService {

    PageResult<ThingModelMessage> logs(DeviceLogPageReqVo request);
    List<DeviceProperty> getPropertyHistory(Long deviceId, String name, long start, long end, Integer pageNo, Integer pageSize);

    Boolean addTag(DeviceTagAddBo bo);

    Boolean simulateSend(ThingModelMessage message);

    PageResult<DeviceGroup> selectGroupPageList(DeviceGroupPageReqVO pageRequest);

    Boolean addGroup(DeviceGroupBo group);

    Boolean updateGroup(DeviceGroupBo bo);

    Boolean deleteGroup(Long id);

    Boolean clearGroup(Long id);

    DeviceConfigVo getConfig(Long deviceId);

    Boolean saveConfig(DeviceConfig data);

    Boolean removeDevicesInGroup(Long group, List<Long> deviceIds);

    Boolean addDevice2Group(DeviceAddGroupBo bo);

    DeviceInfoWithPropertyVO getDeviceInfoWithProperty(Long deviceId);

    String genSerialNO(Integer nodeType);

    GroupImportRespVO importGroup(List<DeviceGroupImportVo> list, Boolean updateSupport);
}


