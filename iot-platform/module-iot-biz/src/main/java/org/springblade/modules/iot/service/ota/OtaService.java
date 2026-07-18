

package org.springblade.modules.iot.service.ota;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.ota.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: EnjoyIot
 * @Date: 2025/1/17 11:58
 * @Version: V1.0
 * @Description: ota接口
 */
public interface OtaService {
    OtaPackageUploadVo uploadFile(MultipartFile file) throws Exception;

    Long addOtaPackage(OtaPackageBo request);

    Boolean delOtaPackageById(Long id);

    PageResult<OtaPackage> getOtaPackagePageList(OtaPackagePageReq request);

    String startUpgrade(Long otaId, List<Long> deviceIds);

    PageResult<DeviceOtaDetailVo> otaDeviceDetail(DeviceOtaDetailPageReq request);

    PageResult<DeviceOtaInfoVo> otaDeviceInfo(DeviceOtaPageReq request);

    void testStartUpgrade();
}
