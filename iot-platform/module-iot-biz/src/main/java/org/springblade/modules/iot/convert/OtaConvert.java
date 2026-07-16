
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
package org.springblade.modules.iot.convert;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.api.ota.dto.DeviceOtaInfo;
import org.springblade.modules.iot.controller.admin.ota.vo.DeviceOtaDetailVo;
import org.springblade.modules.iot.controller.admin.ota.vo.DeviceOtaInfoVo;
import org.springblade.modules.iot.controller.admin.ota.vo.OtaPackage;
import org.springblade.modules.iot.controller.admin.ota.vo.OtaPackageBo;
import org.springblade.modules.iot.entity.DeviceOtaInfoDO;
import org.springblade.modules.iot.entity.OtaDetailDO;
import org.springblade.modules.iot.entity.OtaPackageDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface OtaConvert {
    OtaConvert INSTANCE = Mappers.getMapper(OtaConvert.class);


    OtaPackageDO convert(OtaPackage otaPackage);

    OtaPackageDO convert(OtaPackageBo otaPackage);

    PageResult<OtaPackage> convertPage(PageResult<OtaPackageDO> selectPage);

    PageResult<DeviceOtaDetailVo> convertDetailPage(PageResult<OtaDetailDO> selectPage);

    OtaPackage convertPackage(OtaPackageDO selectById);

    PageResult<DeviceOtaInfo> convertInfoPage(PageResult<DeviceOtaInfoDO> selectPage);

    PageResult<DeviceOtaInfoVo> convertInfoVoPage(PageResult<DeviceOtaInfoDO> selectPage);

    DeviceOtaInfo convertInfo(DeviceOtaInfoDO build);

    DeviceOtaInfoDO convertInfoDO(DeviceOtaInfo build);
}

