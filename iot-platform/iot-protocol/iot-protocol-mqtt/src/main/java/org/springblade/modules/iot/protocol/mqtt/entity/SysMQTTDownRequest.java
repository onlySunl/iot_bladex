

package org.springblade.modules.iot.protocol.mqtt.entity;

import org.springblade.modules.iot.pojo.base.BaseDownRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * http下行参数
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/19 11:19
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class SysMQTTDownRequest extends BaseDownRequest {

  private String downResult;
}
