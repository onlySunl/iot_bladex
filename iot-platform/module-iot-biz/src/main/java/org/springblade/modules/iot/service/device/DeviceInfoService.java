

package org.springblade.modules.iot.service.device;

import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.api.device.dto.DeviceInfo;
import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import org.springblade.modules.iot.api.device.dto.DeviceShortInfo;
import org.springblade.modules.iot.api.device.dto.RegisterDevice;
import org.springblade.modules.iot.controller.admin.device.vo.*;
import org.springblade.modules.iot.controller.admin.device.vo.devicegroup.DeviceImportRespVO;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * 设备信息 Service 接口
 *
 * @author EnjoyIot
 */
public interface DeviceInfoService {

    /**
     * 创建设备信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDeviceInfo(@Valid DeviceInfoSaveReqVO createReqVO);

    /**
     * 更新设备信息
     *
     * @param updateReqVO 更新信息
     */
    void updateDeviceInfo(@Valid DeviceInfoSaveReqVO updateReqVO);

    /**
     * 删除设备信息
     *
     * @param id 编号
     */
    Boolean deleteDeviceInfo(Long id);

    /**
     * 获得设备信息
     *
     * @param id 编号
     * @return 设备信息
     */
    DeviceInfo getDeviceInfo(Long id);

    DeviceInfo getDeviceByPkDnByCache(String pk, String dn);

    DeviceInfo getDeviceInfoFromCache(Long deviceId);

    Map<String, DevicePropertyCache> getPropertiesFromCache(Long deviceId);

    /**
     * 获得设备信息分页
     *
     * @param pageReqVO 分页查询
     * @return 设备信息分页
     */
    PageResult<DeviceShortInfo> getDeviceInfoPage(DeviceInfoPageReqVO pageReqVO);

    DeviceInfo getDeviceBySerialNo(String serialNumber);

    boolean deleteByIds(List<Long> ids);

    DeviceImportRespVO importDevice(List<DeviceInfoImportVo> list, Long productId);

    List<DeviceInfo> findSubDeviceList(String productKey, String deviceName);

    PageResult<DeviceShortInfo> getUnbindPage(DeviceUnbindPageReqVO pageReqVO);

    void bindParent(DeviceBindReqVO saveReqVO);

    void unbindParent(DeviceUnbindReqVO unbindReqVO);

    DeviceInfo registerDevice(RegisterDevice registerDevice);

    long getLastTimeCache(Long deviceId);

    void updateDeviceLastTimeCache(Long deviceId, long lastTime);

    Boolean updateDeviceState(Long deviceId, boolean online);

    void savePropertiesCache(Long deviceId, Map<String, DevicePropertyCache> properties);

    void clearPropertiesCache(String productKey);

    List<DeviceInfo> getDeviceInfoList(List<Long> subDeviceIds);

    Boolean subDeRegisterDevice(String pk, String dn, String subPkDeregister, String subDnDeregister1);
}
