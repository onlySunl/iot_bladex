

package org.springblade.modules.iot.rule.scene.deviceUp;
import org.springblade.modules.iot.common.enums.MessageType;
import MessageType;

import org.springblade.modules.iot.common.message.UPRequest;
import org.springblade.modules.iot.pojo.bo.TriggerBO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DeviceReplyUp extends AbstractDeviceUp implements DeviceUp {

  @Override
  public String messageType() {
    return MessageType.REPLY.name();
  }

  @Override
  public boolean testAlarm(List<TriggerBO> triggers, String separator, UPRequest upRequest) {
    return false;
  }
}
