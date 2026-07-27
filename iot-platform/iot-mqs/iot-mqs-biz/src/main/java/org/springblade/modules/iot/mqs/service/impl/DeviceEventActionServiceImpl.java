package org.springblade.modules.iot.mqs.service.impl;

import java.util.Optional;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.common.cache.link.device.DeviceCacheKeyBuilder;
import org.springblade.common.constant.CommonIotConstants;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.enumeration.DeviceActionStatusEnum;
import org.springblade.common.enums.DeviceActionTypeEnum;
import org.springblade.modules.iot.device.vo.save.DeviceActionSaveVO;
import org.springblade.modules.iot.entity.device.CommonDeviceEvent;
import org.springblade.modules.iot.link.facade.DeviceOpenInnerFacade;
import org.springblade.modules.iot.mqs.service.DeviceEventActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ============================================================================
 * Description:
 * ??????????b??????????ervice Impl
 * ============================================================================
 *
 * @author mqttsnet
 * @version 1.0.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/3/2      mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/3/2 13:10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceEventActionServiceImpl implements DeviceEventActionService {
    @Autowired
    private DeviceOpenInnerFacade deviceOpenInnerApi;

    @Autowired
    private CachePlusUtil cachePlusOpsUtil;

    /**
     * ?e?????????????????????????     *
     * @param eventMessage ??????????????     * @param actionType   ??????????????     * @param describable  ???????     */
    @Override
    public void saveDeviceEventAction(String eventMessage, DeviceActionTypeEnum actionType, String describable) {
        JSONObject map = JSON.parseObject(eventMessage);
        String clientId = String.valueOf(map.get(CommonIotConstants.CLIENT_ID));

        Optional<DeviceCacheVO> deviceCacheVOOptional = cachePlusOpsUtil.getObjectFromCache(DeviceCacheKeyBuilder.build(clientId).getKey(), DeviceCacheVO.class);
        if (deviceCacheVOOptional.isEmpty()) {
            return;
        }

        // save device action
        DeviceActionSaveVO deviceActionSaveVO = new DeviceActionSaveVO();
        deviceActionSaveVO.setDeviceIdentification(deviceCacheVOOptional.get().getDeviceIdentification());
        deviceActionSaveVO.setActionType(actionType.getValue());
        deviceActionSaveVO.setMessage(eventMessage);
        deviceActionSaveVO.setStatus(DeviceActionStatusEnum.SUCCESSFUL.getValue());
        deviceActionSaveVO.setRemark(describable);
        R<DeviceAction> deviceActionR = deviceOpenInnerApi.saveDeviceAction(deviceActionSaveVO);
        if (Boolean.TRUE.equals(R.isSuccess(deviceActionR))) {
            log.info("Save device action success: deviceAction={}", deviceActionR.getData());
        } else {
            log.error("Save device action failed: deviceAction={}", deviceActionR.getData());
        }
    }

    /**
     * {@inheritDoc}
     * <p>??????????event ??????????{@link DeviceActionSaveVO} ??facade;deviceId ??????????facade ????????????????????????????     */
    @Override
    public void save(CommonDeviceEvent event) {
        if (event == null || event.getActionType() == null) {
            return;
        }
        String deviceId = event.getDeviceIdentification();
        if (deviceId == null || deviceId.isEmpty()) {
            log.warn("[DeviceEventAction] deviceIdentification missing, skip persist clientId={} action={}",
                event.getClientId(), event.getActionType());
            return;
        }
        try {
            DeviceActionSaveVO vo = new DeviceActionSaveVO();
            vo.setDeviceIdentification(deviceId);
            vo.setActionType(event.getActionType().getValue());
            vo.setMessage(event.getRawMessage());
            vo.setStatus(DeviceActionStatusEnum.SUCCESSFUL.getValue());
            vo.setRemark(event.getActionType().getDesc());
            R<DeviceAction> r = deviceOpenInnerApi.saveDeviceAction(vo);
            if (!Boolean.TRUE.equals(R.isSuccess(r))) {
                log.warn("[DeviceEventAction] save failed (non-blocking) clientId={} action={} msg={}",
                    event.getClientId(), event.getActionType(), r.getMsg());
            }
        } catch (Exception e) {
            log.warn("[DeviceEventAction] save exception (non-blocking) clientId={} action={}",
                event.getClientId(), event.getActionType(), e);
        }
    }

}
