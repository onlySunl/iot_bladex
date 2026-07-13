

package org.springblade.modules.iot.pojo.bo;

import org.springblade.modules.iot.pojo.entity.Network;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网络组件业务对象
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NetworkBO extends Network {

  /** 绑定mqtt产品数量 */
  private int bindMqttServerProductCount;

  /** 绑定的mqtt产品信息 */
  private List<IoTProductBO> bindMqttServerProducts;

  /** 绑定tcp产品数量 */
  private IoTProductBO bindTcpServerProducts;

  /** 绑定的tcp产品信息 */
  private int bindTcpServerProductCount;

  /** 网络类型名称 */
  private String typeName;

  /** 是否正在运行 */
  private boolean running;

  /** 状态名称 */
  private String stateName;

  /** 创建时间格式化 */
  private String createDateStr;

  /** 网络类型列表（多个类型） */
  private List<String> types;

  /** 启用/停用状态名称 */
  private String enableName;
}
