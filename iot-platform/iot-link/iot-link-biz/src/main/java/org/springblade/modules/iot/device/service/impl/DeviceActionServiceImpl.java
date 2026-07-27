package org.springblade.modules.iot.device.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotdeviceserviceimplDeviceActionServiceImpl.java.mapper.DeviceActionMapper;

import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson2.JSON;
import org.springblade.core.tool.api.R;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.broker.MqttBrokerOpenInnerFacade;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceActionCacheVO;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.enumeration.DeviceActionStatusEnum;
import org.springblade.modules.iot.common.enums.DeviceActionTypeEnum;
import org.springblade.modules.iot.device.service.DeviceActionService;
import org.springblade.modules.iot.device.vo.query.DeviceActionPageQuery;
import org.springblade.modules.iot.device.vo.result.DeviceActionResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceActionSaveVO;
import org.springblade.modules.iot.vo.query.KillClientRequestVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * 璁惧鍔ㄤ綔鏁版嵁
 * </p>
 *
 * @author mqttsnet
 * @date 2023-06-10 16:38:09
 * @create [2023-06-10 16:38:09] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceActionServiceImpl extends BaseServiceImpl<DeviceActionMapper, DeviceAction> implements DeviceActionService {

    private final LinkCacheDataHelper linkCacheDataHelper;
    private final MqttBrokerOpenInnerFacade mqttBrokerOpenInnerFacade;

    /**
     * 淇濆瓨璁惧鍔ㄤ綔鏁版嵁
     *
     * @param deviceActionSaveVO 璁惧鍔ㄤ綔鏁版嵁
     * @return {@link DeviceAction} 淇濆瓨瀹屾垚鐨勮澶囧姩浣滄暟鎹?
     */
    @Override
    public DeviceAction saveDeviceAction(DeviceActionSaveVO deviceActionSaveVO) {
        // 鏍￠獙鍙傛暟
        checkedDeviceActionSaveVO(deviceActionSaveVO);

        // 鏋勫缓鍙傛暟
        DeviceAction deviceAction = builderDeviceActionSaveVO(deviceActionSaveVO);

        // 淇濆瓨璁惧鍔ㄤ綔鏁版嵁
        boolean saveSuccess = Optional.of(superManager.save(deviceAction)).orElse(false);

        if (saveSuccess) {
            // 浠庣紦瀛樹腑鑾峰彇璁惧淇℃伅
            Optional<DeviceCacheVO> deviceCacheVOOptional = linkCacheDataHelper.getDeviceCacheVO(deviceAction.getDeviceIdentification());
            if (deviceCacheVOOptional.isPresent()) {
                DeviceActionCacheVO actionCacheVO = BeanUtil.toBeanIgnoreError(deviceAction, DeviceActionCacheVO.class);
                linkCacheDataHelper.setDeviceActionCacheVO(deviceCacheVOOptional.get().getProductIdentification(), deviceCacheVOOptional.get().getDeviceIdentification(), actionCacheVO);
            }
        }

        return deviceAction;
    }

    /**
     * 鏌ヨ璁惧鍔ㄤ綔鏁版嵁VO鍒楄〃
     *
     * @param query 鏌ヨ鍙傛暟
     * @return {@link List <DeviceActionResultVO>} 璁惧鍔ㄤ綔鏁版嵁VO鍒楄〃
     */
    @Override
    public List<DeviceActionResultVO> getDeviceActionResultVOList(DeviceActionPageQuery query) {
        return superManager.getDeviceActionResultVOList(query);
    }

    @Override
    public Boolean disconnectDevice(String deviceIdentification) {
        Optional<DeviceCacheVO> deviceCacheVOOptional = linkCacheDataHelper.getDeviceCacheVO(deviceIdentification);
        ArgumentAssert.isTrue(deviceCacheVOOptional.isPresent(), "Device does not exist!");
        DeviceCacheVO deviceCacheVO = deviceCacheVOOptional.get();
        KillClientRequestVO killClientRequestVO = new KillClientRequestVO().toBuilder()
                .clientId(deviceCacheVO.getClientId())
                .userId(deviceCacheVO.getDeviceIdentification())
                .tenantId(deviceCacheVO.getTenantId().toString())
                .clientType("web")
                .build();
        R<?> r = mqttBrokerOpenInnerFacade.closeConnection(killClientRequestVO);
        if (r.getIsSuccess()) {
            // 璁板綍璁惧鍔ㄤ綔
            DeviceActionTypeEnum deviceActionTypeEnum = DeviceActionTypeEnum.DISCONNECT;
            DeviceActionSaveVO deviceActionSaveVO = new DeviceActionSaveVO();
            deviceActionSaveVO.setDeviceIdentification(deviceCacheVO.getDeviceIdentification());
            deviceActionSaveVO.setActionType(deviceActionTypeEnum.getValue());
            deviceActionSaveVO.setMessage(JSON.toJSONString(killClientRequestVO));
            deviceActionSaveVO.setStatus(DeviceActionStatusEnum.SUCCESSFUL.getValue());
            deviceActionSaveVO.setRemark("Manual Operation..." + deviceActionTypeEnum.getDesc());
            saveDeviceAction(deviceActionSaveVO);
        }
        return r.getIsSuccess();
    }

    /**
     * 鏋勫缓 DeviceActionSaveVO 瀵硅薄
     *
     * @param deviceActionSaveVO 瑕佽繘琛屾瀯寤虹殑瀵硅薄
     * @return 鏋勫缓濂界殑 DeviceAction 瀵硅薄
     */
    private DeviceAction builderDeviceActionSaveVO(DeviceActionSaveVO deviceActionSaveVO) {
        return BeanUtil.toBeanIgnoreError(deviceActionSaveVO, DeviceAction.class);
    }

    /**
     * 妫€鏌?DeviceActionSaveVO 鍙傛暟瀹屾暣鎬?
     *
     * @param deviceActionSaveVO 瑕佽繘琛屾鏌ョ殑瀵硅薄
     */
    private void checkedDeviceActionSaveVO(DeviceActionSaveVO deviceActionSaveVO) {
        ArgumentAssert.notNull(deviceActionSaveVO, "deviceActionSaveVO Cannot be null");
        ArgumentAssert.notBlank(deviceActionSaveVO.getDeviceIdentification(), "deviceIdentification Cannot be null");
        ArgumentAssert.notBlank(deviceActionSaveVO.getActionType(), "actionType Cannot be null");
        ArgumentAssert.notBlank(deviceActionSaveVO.getMessage(), "message Cannot be null");
        ArgumentAssert.notNull(deviceActionSaveVO.getStatus(), "status Cannot be null");
    }

}

