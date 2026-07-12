/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.rule.scene.deviceUp;

import org.springblade.modules.iot.common.constant.IoTConstant;
import org.springblade.modules.iot.common.message.UPRequest;
import org.springblade.modules.iot.persistence.entity.bo.TriggerBO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DeviceReplyUp extends AbstractDeviceUp implements DeviceUp {

  @Override
  public String messageType() {
    return IoTConstant.MessageType.REPLY.name();
  }

  @Override
  public boolean testAlarm(List<TriggerBO> triggers, String separator, UPRequest upRequest) {
    return false;
  }
}
