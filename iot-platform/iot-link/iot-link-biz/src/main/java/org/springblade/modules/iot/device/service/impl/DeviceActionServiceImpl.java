package org.springblade.modules.iot.device.service.impl;

import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.tool.api.R;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.common.utils.BeanUtil;
import org.springblade.modules.iot.broker.MqttBrokerOpenInnerFacade;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceActionCacheVO;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.enumeration.DeviceActionStatusEnum;
import org.springblade.modules.iot.common.enums.DeviceActionTypeEnum;
import org.springblade.modules.iot.device.manager.DeviceActionManager;
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
 * 业务实现类
 * 设备动作数据
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
     * 保存设备动作数据
     *
     * @param deviceActionSaveVO 设备动作数据
     * @return {@link DeviceAction} 保存完成的设备动作数据
     */
    @Override
    public DeviceAction saveDeviceAction(DeviceActionSaveVO deviceActionSaveVO) {
        // 校验参数
        checkedDeviceActionSaveVO(deviceActionSaveVO);

        // 构建参数
        DeviceAction deviceAction = builderDeviceActionSaveVO(deviceActionSaveVO);

        // 保存设备动作数据
        boolean saveSuccess = Optional.of(superManager.save(deviceAction)).orElse(false);

        if (saveSuccess) {
            // 从缓存中获取设备信息
            Optional<DeviceCacheVO> deviceCacheVOOptional = linkCacheDataHelper.getDeviceCacheVO(deviceAction.getDeviceIdentification());
            if (deviceCacheVOOptional.isPresent()) {
                DeviceActionCacheVO actionCacheVO = BeanUtil.toBeanIgnoreError(deviceAction, DeviceActionCacheVO.class);
                linkCacheDataHelper.setDeviceActionCacheVO(deviceCacheVOOptional.get().getProductIdentification(), deviceCacheVOOptional.get().getDeviceIdentification(), actionCacheVO);
            }
        }

        return deviceAction;
    }

    /**
     * 查询设备动作数据VO列表
     *
     * @param query 查询参数
     * @return {@link List <DeviceActionResultVO>} 设备动作数据VO列表
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
            // 记录设备动作
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
     * 构建 DeviceActionSaveVO 对象
     *
     * @param deviceActionSaveVO 要进行构建的对象
     * @return 构建好的 DeviceAction 对象
     */
    private DeviceAction builderDeviceActionSaveVO(DeviceActionSaveVO deviceActionSaveVO) {
        return BeanUtil.toBeanIgnoreError(deviceActionSaveVO, DeviceAction.class);
    }

    /**
     * 检查 DeviceActionSaveVO 参数完整性
     *
     * @param deviceActionSaveVO 要进行检查的对象
     */
    private void checkedDeviceActionSaveVO(DeviceActionSaveVO deviceActionSaveVO) {
        ArgumentAssert.notNull(deviceActionSaveVO, "deviceActionSaveVO Cannot be null");
        ArgumentAssert.notBlank(deviceActionSaveVO.getDeviceIdentification(), "deviceIdentification Cannot be null");
        ArgumentAssert.notBlank(deviceActionSaveVO.getActionType(), "actionType Cannot be null");
        ArgumentAssert.notBlank(deviceActionSaveVO.getMessage(), "message Cannot be null");
        ArgumentAssert.notNull(deviceActionSaveVO.getStatus(), "status Cannot be null");
    }

}

