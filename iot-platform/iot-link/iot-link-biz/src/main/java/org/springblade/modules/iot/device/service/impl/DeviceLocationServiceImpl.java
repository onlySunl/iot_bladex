package org.springblade.modules.iot.device.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotdeviceserviceimplDeviceLocationServiceImpl.java.mapper.DeviceLocationMapper;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.DeviceLocation;
import org.springblade.modules.iot.device.service.DeviceLocationService;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceLocationSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceLocationUpdateVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * 璁惧浣嶇疆琛?
 * </p>
 *
 * @author mqttsnet
 * @date 2023-05-30 23:05:31
 * @create [2023-05-30 23:05:31] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationServiceImpl extends BaseServiceImpl<DeviceLocationMapper, DeviceLocation> implements DeviceLocationService {

    /**
     * 淇濆瓨璁惧浣嶇疆淇℃伅
     *
     * @param deviceLocationSaveVO 璁惧浣嶇疆淇℃伅
     * @return {@link DeviceLocationSaveVO} 淇濆瓨瀹屾垚鐨勮澶囦綅缃俊鎭?
     */
    @Override
    public DeviceLocationSaveVO saveDeviceLocation(DeviceLocationSaveVO deviceLocationSaveVO) {
        // 鏍￠獙鍙傛暟
        checkedDeviceLocationSaveVO(deviceLocationSaveVO);

        // 鏋勫缓鍙傛暟
        DeviceLocation deviceLocation = builderDeviceLocationSaveVO(deviceLocationSaveVO);

        // 淇濆瓨璁惧浣嶇疆淇℃伅
        superManager.save(deviceLocation);

        return BeanUtil.toBeanIgnoreError(deviceLocation, DeviceLocationSaveVO.class);
    }

    /**
     * 鏇存柊璁惧浣嶇疆淇℃伅
     *
     * @param deviceLocationUpdateVO 璁惧浣嶇疆淇℃伅
     * @return {@link DeviceLocationUpdateVO} 鏇存柊瀹屾垚鐨勮澶囦綅缃俊鎭?
     */
    @Override
    public DeviceLocationUpdateVO updateDeviceLocation(DeviceLocationUpdateVO deviceLocationUpdateVO) {
        log.info("updateDeviceLocation updateVO:{}", deviceLocationUpdateVO);

        checkedDeviceLocationUpdateVO(deviceLocationUpdateVO);

        deviceLocationUpdateVO.setCreatedOrgId(AuthUtil.getCurrentDeptId());

        //鏋勫缓鍙傛暟
        Builder<DeviceLocation> deviceLocationBuilder = builderDeviceLocationUpdateVO(deviceLocationUpdateVO);

        //鏇存柊
        superManager.updateById(deviceLocationBuilder.with(DeviceLocation::setId, deviceLocationUpdateVO.getId()).build());
        return deviceLocationUpdateVO;
    }

    /**
     * 鏌ヨ璁惧浣嶇疆淇℃伅VO鍒楄〃
     *
     * @param query 鏌ヨ鍙傛暟
     * @return {@link List <DeviceLocationResultVO>} 璁惧浣嶇疆淇℃伅VO鍒楄〃
     */
    @Override
    public List<DeviceLocationResultVO> getDeviceLocationResultVOList(DeviceLocationPageQuery query) {
        return superManager.getDeviceLocationResultVOList(query);
    }

    /**
     *
     * @param deviceLocationUpdateVO 璁惧浣嶇疆淇℃伅
     */
    private void checkedDeviceLocationUpdateVO(DeviceLocationUpdateVO deviceLocationUpdateVO) {
        ArgumentAssert.notNull(deviceLocationUpdateVO, "deviceLocationSaveVO Cannot be null");
        ArgumentAssert.notNull(deviceLocationUpdateVO.getId(), "id Cannot be null");
        ArgumentAssert.notNull(deviceLocationUpdateVO.getLatitude(), "latitude Cannot be null");
        ArgumentAssert.notNull(deviceLocationUpdateVO.getLongitude(), "longitude Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getFullName(), "fullName Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getProvinceCode(), "provinceCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getCityCode(), "cityCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationUpdateVO.getRegionCode(), "regionCode Cannot be null");

    }

    private Builder<DeviceLocation> builderDeviceLocationUpdateVO(DeviceLocationUpdateVO deviceLocationUpdateVO) {
        return new DeviceLocation()
                .with(DeviceLocation::setLatitude, deviceLocationUpdateVO.getLatitude())
                .with(DeviceLocation::setLongitude, deviceLocationUpdateVO.getLongitude())
                .with(DeviceLocation::setFullName, deviceLocationUpdateVO.getFullName())
                .with(DeviceLocation::setProvinceCode, deviceLocationUpdateVO.getProvinceCode())
                .with(DeviceLocation::setCityCode, deviceLocationUpdateVO.getCityCode())
                .with(DeviceLocation::setRegionCode, deviceLocationUpdateVO.getRegionCode())
                .with(DeviceLocation::setRemark, deviceLocationUpdateVO.getRemark())
                .with(DeviceLocation::setCreatedOrgId, deviceLocationUpdateVO.getCreatedOrgId());
    }

    /**
     * 妫€鏌?DeviceLocationSaveVO 鍙傛暟瀹屾暣鎬?
     *
     * @param deviceLocationSaveVO 瑕佽繘琛屾鏌ョ殑瀵硅薄
     */
    private void checkedDeviceLocationSaveVO(DeviceLocationSaveVO deviceLocationSaveVO) {
        ArgumentAssert.notNull(deviceLocationSaveVO, "deviceLocationSaveVO Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getDeviceIdentification(), "deviceIdentification Cannot be null");
        ArgumentAssert.notNull(deviceLocationSaveVO.getLatitude(), "latitude Cannot be null");
        ArgumentAssert.notNull(deviceLocationSaveVO.getLongitude(), "longitude Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getFullName(), "fullName Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getProvinceCode(), "provinceCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getCityCode(), "cityCode Cannot be null");
        ArgumentAssert.notBlank(deviceLocationSaveVO.getRegionCode(), "regionCode Cannot be null");
    }

    /**
     * 鏋勫缓 DeviceLocationSaveVO 瀵硅薄
     *
     * @param deviceLocationSaveVO 瑕佽繘琛屾瀯寤虹殑瀵硅薄
     * @return 鏋勫缓濂界殑 DeviceLocation 瀵硅薄
     */
    private DeviceLocation builderDeviceLocationSaveVO(DeviceLocationSaveVO deviceLocationSaveVO) {
        DeviceLocation deviceLocation = BeanUtil.toBeanIgnoreError(deviceLocationSaveVO, DeviceLocation.class);
        deviceLocation.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        return deviceLocation;
    }
}

