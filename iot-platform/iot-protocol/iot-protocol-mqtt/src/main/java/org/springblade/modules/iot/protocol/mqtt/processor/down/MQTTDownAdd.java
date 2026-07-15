package org.springblade.modules.iot.protocol.mqtt.processor.down;
import org.springblade.modules.iot.common.enums.DeviceStatus;
import DeviceStatus;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.common.constant.DeviceStatus;
import org.springblade.modules.iot.common.constant.IoTConstant.DownCmd;
import org.springblade.modules.iot.common.constant.IoTConstant.ERROR_CODE;
import org.springblade.modules.iot.common.domain.R;
import org.springblade.modules.iot.dm.device.service.impl.IoTProductDeviceService;
import org.springblade.modules.iot.protocol.mqtt.entity.MQTTDownRequest;
import org.springblade.modules.iot.protocol.mqtt.processor.MQTTDownMessageProcessor;
import org.springblade.modules.iot.persistence.base.IoTDeviceLifeCycle;
import org.springblade.modules.iot.persistence.base.IoTDownAdapter;
import org.springblade.modules.iot.pojo.entity.IoTDevice;
import org.springblade.modules.iot.pojo.entity.SupportMapAreas;
import org.springblade.modules.iot.persistence.mapper.IoTDeviceMapper;
import org.springblade.modules.iot.persistence.mapper.SupportMapAreasMapper;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mqtt 设备增加
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/7/10 10:40
 */
@Component
@Slf4j(topic = "mqtt")
public class MQTTDownAdd extends IoTDownAdapter<MQTTDownRequest>
    implements MQTTDownMessageProcessor {

  private String CUSTOM_FIELD = "customField";

  @Resource(name = "ioTDeviceActionAfterService")
  private IoTDeviceLifeCycle ioTDeviceLifeCycle;

  @Resource private SupportMapAreasMapper supportMapAreasMapper;
  @Resource private IoTDeviceMapper ioTDeviceMapper;
  @Autowired private IoTProductDeviceService iotProductDeviceService;

  @Override
  public R<?> process(MQTTDownRequest downRequest) {
    /** 当编解码请求结果为空 或者 编解码请求结果不为空且状态码等于0时，添加设备到本地 */
    log.info(
        "添加mqtt设备,deviceId={},productKey={}",
        downRequest.getDeviceId(),
        downRequest.getProductKey());
    R preR =
        beforeDownAction(downRequest.getIoTProduct(), downRequest.getDownCommonData(), downRequest);
    if (Objects.nonNull(preR)) {
      return preR;
    }
    IoTDevice ioTDevice =
        IoTDevice.builder()
            .productKey(downRequest.getProductKey())
            .deviceId(downRequest.getDeviceId())
            .build();
    int size = ioTDeviceMapper.selectCount(ioTDevice);
    if (size > 0) {
      // 设备已经存在
      return R.error(
          ERROR_CODE.DEV_ADD_DEVICE_ID_EXIST.getCode(),
          ERROR_CODE.DEV_ADD_DEVICE_ID_EXIST.getName());
    }
    // 操作数据库
    R<?> rs = saveIoTDevice(downRequest);
    return rs;
  }

  private R<?> saveIoTDevice(MQTTDownRequest downRequest) {
    IoTDevice ioTDevice =
        IoTDevice.builder()
            .deviceId(downRequest.getDeviceId())
            .createTime(System.currentTimeMillis() / 1000)
            .deviceName(downRequest.getDownCommonData().getDeviceName())
            .state(DeviceStatus.offline.getCode())
            .iotId(downRequest.getProductKey()+downRequest.getDeviceId())
            .application(downRequest.getApplicationId())
            .creatorId(downRequest.getAppUnionId())
            .productName(downRequest.getIoTProduct().getName())
            .detail(downRequest.getDetail())
            .gwProductKey(downRequest.getGwProductKey())
            .extDeviceId(downRequest.getGwDeviceId())
            .productKey(downRequest.getProductKey())
            .build();

    if (StrUtil.isNotBlank(downRequest.getDownCommonData().getExtDeviceId())) {
      ioTDevice.setExtDeviceId(downRequest.getDownCommonData().getExtDeviceId());
    }

    if (StrUtil.isNotBlank(downRequest.getDownCommonData().getLatitude())
        && StrUtil.isNotBlank(downRequest.getDownCommonData().getLongitude())) {
      ioTDevice.setCoordinate(
          StrUtil.join(
              ",",
              downRequest.getDownCommonData().getLongitude(),
              downRequest.getDownCommonData().getLatitude()));

      SupportMapAreas supportMapAreas =
          supportMapAreasMapper.selectMapAreas(
              downRequest.getDownCommonData().getLongitude(),
              downRequest.getDownCommonData().getLatitude());
      if (supportMapAreas == null) {
        log.info(
            "查询区域id为空,lot={},lat={}",
            downRequest.getDownCommonData().getLongitude(),
            downRequest.getDownCommonData().getLatitude());
      } else {
        ioTDevice.setAreasId(supportMapAreas.getId());
      }
    }
    Map<String, Object> config = new HashMap<>();
    finalDown_Add(config, downRequest.getIoTProduct(), downRequest, downRequest.getData());
    ioTDevice.setConfiguration(JSONUtil.toJsonStr(config));
    int success = ioTDeviceMapper.insertUseGeneratedKeys(ioTDevice);
    if (success > 0) {
      // 推送设备创建消息
      ioTDeviceLifeCycle.create(
          downRequest.getProductKey(), downRequest.getDeviceId(), downRequest);
      // 组件返回字段
      Map<String, Object> result = new HashMap<>();
      result.put("iotId", ioTDevice.getIotId());
      result.put("areasId", ioTDevice.getAreasId() == null ? "" : ioTDevice.getAreasId());
      if (StrUtil.isNotBlank(downRequest.getIoTProduct().getMetadata())) {
        result.put("metadata", JSONUtil.parseObj(downRequest.getIoTProduct().getMetadata()));
      }
      result.put("productKey", downRequest.getProductKey());
      result.put("deviceNode", downRequest.getIoTProduct().getDeviceNode());
      return R.ok(result);
    } else {
      return R.error("设备增加失败");
    }
  }

  @Override
  public boolean supports(MQTTDownRequest request) {
    if (!DownCmd.DEV_ADD.equals(request.getCmd())) {
      return false;
    }
    return true;
  }

  @Override
  public String getName() {
    return "";
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
