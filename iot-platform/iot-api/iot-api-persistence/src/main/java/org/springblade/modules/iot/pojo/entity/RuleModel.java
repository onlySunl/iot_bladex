

package org.springblade.modules.iot.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * 规则模型 @Author gitee.com/NexIoT
 *
 * @since 2023/1/13 14:28
 */
@TableName("rule_model")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleModel extends CustomBaseEntity {

  /** 主键ID */

  /** 规则名称 */
  @Schema(description = "规则名称")
  @TableField("rule_name")
  private String ruleName;

  /** 数据级别 */
  @Schema(description = "数据级别")
  @TableField("data_level")
  private String dataLevel;

  /** 描述 */
  @Schema(description = "描述")
  @TableField("description")
  private String description;


  /** 产品KEY */
  @Schema(description = "产品KEY")
  @TableField("product_key")
  private String productKey;

  /** 规则配置 */
  @Schema(description = "规则配置")
  @TableField("config")
  private String config;

  /** 创建人 */
  @Schema(description = "创建人")
  @TableField("creator_id")
  private String creatorId;

}
