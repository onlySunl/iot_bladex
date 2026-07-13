

package org.springblade.modules.iot.dm.device.service.wrapper;
import MessageType;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.common.constant.MessageType;
import org.springblade.modules.iot.common.domain.R;
import org.springblade.modules.iot.common.exception.IoTErrorCode;
import org.springblade.modules.iot.core.message.DownRequest;
import org.springblade.modules.iot.dm.device.service.impl.IoTDeviceSubscribeService;
import org.springblade.modules.iot.persistence.base.IoTDownWrapper;
import org.springblade.modules.iot.persistence.dto.IoTDeviceSubscribeBO;
import org.springblade.modules.iot.pojo.entity.IoTDevice;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import org.springblade.modules.iot.pojo.entity.IoTUserApplication;
import org.springblade.modules.iot.pojo.entity.SupportMapAreas;
import org.springblade.modules.iot.persistence.mapper.IoTDeviceMapper;
import org.springblade.modules.iot.persistence.mapper.IoTUserApplicationMapper;
import org.springblade.modules.iot.persistence.mapper.SupportMapAreasMapper;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** 设备分享，实际上是绑定了订阅 */
@Service("ioTDeviceShareIntercept")
@Slf4j
public class IoTDeviceShareIntercept implements IoTDownWrapper {

  @Resource private IoTDeviceSubscribeService iotDeviceSubscribeService;

  @Resource private IoTUserApplicationMapper iotUserApplicationMapper;

  @Resource private IoTDeviceMapper ioTDeviceMapper;

  @Resource private SupportMapAreasMapper supportMapAreasMapper;

  @Override
  public R beforeDownAction(IoTProduct product, Object data, DownRequest downRequest) {
    // 非设备分享，直接返回
    if (!downRequest.isDeviceReuse()) {
      return null;
    }
    IoTDevice ioTDevice =
        ioTDeviceMapper.selectOne(
            IoTDevice.builder()
                .productKey(downRequest.getProductKey())
                .deviceId(downRequest.getDeviceId())
                .build());
    switch (downRequest.getCmd()) {
      case DEV_ADD:
        if (Objects.isNull(ioTDevice)) {
          return R.error(
              IoTErrorCode.DEV_MASTER_NOT_EXIST_ERROR.getCode(),
              IoTErrorCode.DEV_MASTER_NOT_EXIST_ERROR.getName());
        }
        IoTUserApplication applicationAdd =
            iotUserApplicationMapper.selectIotUserApplicationById(downRequest.getApplicationId());
        if (applicationAdd == null
            || (StrUtil.isBlank(applicationAdd.getNotifyUrl())
                && StrUtil.isBlank(applicationAdd.getUpTopic()))) {
          return R.error(IoTErrorCode.DATA_CAN_NOT_NULL.getCode(), "添加失败");
        }
        // 添加订阅
        R rAdd =
            iotDeviceSubscribeService.doSubscribe(
                ioTDevice.getIotId(),
                downRequest.getProductKey(),
                applicationAdd.getUnionId(),
                applicationAdd.getAppUniqueId(),
                IoTDeviceSubscribeBO.builder()
                    .msgType(MessageType.ALL.name())
                    .url(applicationAdd.getNotifyUrl())
                    .build());
        if (!R.SUCCESS.equals(rAdd.getCode())) {
          return rAdd;
        }
        // 组件返回字段
        Map<String, Object> resultAdd = new HashMap<>();
        resultAdd.put("iotId", ioTDevice.getIotId());
        resultAdd.put("areasId", ioTDevice.getAreasId() == null ? "" : ioTDevice.getAreasId());
        if (StrUtil.isNotBlank(product.getMetadata())) {
          resultAdd.put("metadata", JSONUtil.parseObj(product.getMetadata()));
        }
        resultAdd.put("productKey", downRequest.getProductKey());
        resultAdd.put("deviceNode", product.getDeviceNode());
        return R.ok(resultAdd);
      case DEV_DEL:
        if (Objects.isNull(ioTDevice)) {
          return R.ok();
        }
        // 删除订阅
        IoTUserApplication applicationDel =
            iotUserApplicationMapper.selectIotUserApplicationById(downRequest.getApplicationId());
        if (applicationDel == null) {
          return R.error(IoTErrorCode.DATA_CAN_NOT_NULL.getCode(), "操作失败");
        }
        R rDel =
            iotDeviceSubscribeService.deleteSubscribe(
                ioTDevice.getIotId(), applicationDel.getUnionId(), applicationDel.getAppUniqueId());
        if (!R.SUCCESS.equals(rDel.getCode())) {
          return rDel;
        }
        return R.ok();
      case DEV_UPDATE:
        if (Objects.isNull(ioTDevice)) {
          return R.error(IoTErrorCode.DEV_MASTER_NOT_EXIST_ERROR.getCode(), "主设备不存在");
        }
        JSONObject location = JSONUtil.parseObj(data);
        SupportMapAreas supportMapAreas =
            supportMapAreasMapper.selectMapAreas(
                location.getStr("longitude"), location.getStr("latitude"));
        if (supportMapAreas == null) {
          log.info(
              "查询区域id为空,lot={},lat={}", location.getStr("longitude"), location.getStr("latitude"));
        } else {
          ioTDevice.setAreasId(supportMapAreas.getId());
        }
        // 组件返回字段
        Map<String, Object> resultUp = new HashMap<>();
        resultUp.put("deviceId", ioTDevice.getDeviceId());
        resultUp.put("areasId", ioTDevice.getAreasId() == null ? "" : ioTDevice.getAreasId());
        return R.ok(resultUp);
      case DEV_FUNCTION:
        if (Objects.isNull(ioTDevice)) {
          return R.error(IoTErrorCode.DEV_MASTER_NOT_EXIST_ERROR.getCode(), "主设备不存在");
        }
        // 权限判断
        IoTUserApplication applicationFun =
            iotUserApplicationMapper.selectIotUserApplicationById(downRequest.getApplicationId());
        if (applicationFun == null) {
          return R.error(IoTErrorCode.DATA_CAN_NOT_NULL.getCode(), "操作失败");
        }
        if (StrUtil.isNotBlank(applicationFun.getScope())) {
          JSONObject scope = JSONUtil.parseObj(applicationFun.getScope());
          if (StrUtil.isNotBlank(scope.getStr("functionDown"))) {
            return null;
          }
        }
        return R.error("权限不足");
      default:
        log.debug("复用设备处理下行未匹配到方法");
        return R.error("操作失败");
    }
  }
}
