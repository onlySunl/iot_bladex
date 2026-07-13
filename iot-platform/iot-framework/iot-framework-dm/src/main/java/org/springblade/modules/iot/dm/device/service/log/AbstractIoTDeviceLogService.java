

package org.springblade.modules.iot.dm.device.service.log;
import org.springblade.modules.iot.common.enums.MessageType;
import MessageType;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.common.constant.MessageType;
import org.springblade.modules.iot.core.message.UPRequest;
import org.springblade.modules.iot.core.metadata.DeviceMetadata;
import org.springblade.modules.iot.dm.device.constant.DeviceManagerConstant;
import org.springblade.modules.iot.dm.device.service.impl.IoTProductDeviceService;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.pojo.entity.IoTDeviceEvents;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLog;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLogMetadata;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLogMetadata.IoTDeviceLogMetadataBuilder;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import org.springblade.modules.iot.persistence.mapper.IoTProductMapper;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/9/23
 */
public abstract class AbstractIoTDeviceLogService implements IIoTDeviceLogService {

  @Resource private IoTProductMapper ioTProductMapper;
  @Resource IoTProductDeviceService iotProductDeviceService;

  DeviceMetadata getDeviceMetadata(String metadata) {
    if (StrUtil.isBlank(metadata)) {
      return new DeviceMetadata(new JSONObject());
    }
    return new DeviceMetadata(new JSONObject(metadata));
  }

  IoTDeviceLogMetadataBuilder builder(UPRequest up) {
    final IoTDeviceLogMetadataBuilder ioTDeviceLogMetadataBuilder =
        IoTDeviceLogMetadata.builder()
            .createTime(LocalDateTime.now())
            .deviceId(up.getDeviceId())
            .deviceName(StrUtil.sub(up.getDeviceName(), 0, 30))
            .ext1(JSONUtil.toJsonStr(up.getData()))
            .iotId(up.getIotId())
            .productKey(up.getProductKey())
            .messageType(up.getMessageType().name());
    return ioTDeviceLogMetadataBuilder;
  }

  IoTDeviceLog build(BaseUPRequest upRequest, IoTDeviceDTO ioTDeviceDTO) {
    IoTDeviceLog log =
        IoTDeviceLog.builder()
            .content(peopertiesOrEventData(upRequest))
            .deviceId(ioTDeviceDTO.getDeviceId())
            .iotId(ioTDeviceDTO.getIotId())
            .productKey(ioTDeviceDTO.getProductKey())
            .messageType(upRequest.getMessageType().name())
            .event(functionOrEvent(upRequest))
            .commandId(upRequest.getCommandId())
            .commandStatus(upRequest.getCommandStatus())
            .createTime(LocalDateTime.now())
            .deviceName(StrUtil.sub(ioTDeviceDTO.getDeviceName(), 0, 30))
            .build();
    if (upRequest.getProperties() != null
        && upRequest.getProperties().containsKey(DeviceManagerConstant.COORDINATES)) {
      log.setPoint(ioTDeviceDTO.getCoordinate());
    }

    return log;
  }

  private String peopertiesOrEventData(BaseUPRequest upRequest) {
    if (MessageType.PROPERTIES.equals(upRequest.getMessageType())) {
      return JSONUtil.toJsonStr(upRequest.getProperties());
    } else if (MessageType.EVENT.equals(upRequest.getMessageType())) {
      Map<String, Object> content = new HashMap<>();
      Map<String, Object> data = upRequest.getData();
      Map<String, Object> properties = upRequest.getProperties();
      if (MapUtil.isNotEmpty(properties)) {
        content.putAll(properties);
      }
      if (MapUtil.isNotEmpty(data)) {
        content.putAll(data);
      }
      return JSONUtil.toJsonStr(content);
    }
    return StrUtil.EMPTY;
  }

  private String functionOrEvent(BaseUPRequest upRequest) {
    if (MessageType.FUNCTIONS.equals(upRequest.getMessageType())
        || MessageType.REPLY.equals(upRequest.getMessageType())) {
      // online-上线
      return upRequest.getFunction();
    } else if (MessageType.EVENT.equals(upRequest.getMessageType())) {
      return upRequest.getEvent();
    }
    return StrUtil.EMPTY;
  }

  List<IoTDeviceEvents> selectDevEvents(String productKey) {
    IoTProduct ioTProduct = ioTProductMapper.getProductByProductKey(productKey);
    JSONObject metadata = JSONUtil.parseObj(ioTProduct.getMetadata());
    List<IoTDeviceEvents> ioTDeviceEventsList = new ArrayList<>();
    JSONArray properties = metadata.getJSONArray("events");
    if (properties != null) {
      for (Object object : properties) {
        JSONObject jsonObject = JSONUtil.parseObj(object);
        JSONObject expands = JSONUtil.parseObj(jsonObject.getStr("expands"));
        IoTDeviceEvents ioTDeviceEvents =
            IoTDeviceEvents.builder()
                .id(jsonObject.getStr("id"))
                .name(jsonObject.getStr("name"))
                .description(jsonObject.getStr("description"))
                .level(expands.getStr("level"))
                .build();
        ioTDeviceEventsList.add(ioTDeviceEvents);
      }
    }
    return ioTDeviceEventsList;
  }
}
