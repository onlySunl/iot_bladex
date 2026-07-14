

package org.springblade.modules.iot.protocol.http.entity;

import cn.universal.persistence.base.BaseDownRequest;
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
public class HttpDownRequest extends BaseDownRequest {

  private String downResult;

  // ===== 网关子设备支持（网关代理模式）=====

  /** 子设备ID（网关代理模式时使用，记录真实的子设备ID） */
  private String subDeviceId;

  /** 子设备ProductKey（网关代理模式时使用，记录真实的子设备ProductKey） */
  private String subProductKey;

  /** 是否为网关代理模式（true表示使用网关设备通信，但编解码用子设备） */
  private Boolean isGatewayProxy = false;
}
