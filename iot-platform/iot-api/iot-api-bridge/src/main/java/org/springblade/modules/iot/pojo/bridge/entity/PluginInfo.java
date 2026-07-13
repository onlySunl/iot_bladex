

package org.springblade.modules.iot.pojo.bridge.entity;
import org.springblade.modules.iot.common.enums.DataDirection;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 插件信息
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PluginInfo extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 插件名称 */
  private String name;

  /** 插件版本 */
  private String version;

  /** 插件描述 */
  private String description;

  /** 插件作者 */
  private String author;

  /** 插件类型 */
  private String pluginType;

  /** 支持的资源类型 */
  private List<String> supportedResourceTypes;

  /** 数据流向 - INPUT: 仅输入, OUTPUT: 仅输出, BIDIRECTIONAL: 双向 */
  private DataDirection dataDirection;

  /** 插件分类 - 用于前端分组显示 */
  private String category;

  /** 图标名称 - 用于前端显示 */
  private String icon;

  /** 是否启用 */
  @Builder.Default private Boolean enabled = true;

  /** 数据流向枚举 */
}
