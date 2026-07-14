

package org.springblade.modules.iot.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springblade.common.entity.CustomBaseEntity;
/**
 * rulego规则链管理实体
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@TableName("rulego_chain")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoChain extends CustomBaseEntity {

  /** 主键ID */

  /** rulego规则链ID */
  @Schema(description = "rulego规则链ID")
  @TableField("rulego_id")
  private String rulegoId;

  /** 规则链名称 */
  @Schema(description = "规则链名称")
  @TableField("chain_name")
  private String chainName;

  /** 规则链描述 */
  @Schema(description = "规则链描述")
  @TableField("description")
  private String description;

  /** 创建人unionId */
  @Schema(description = "创建人unionId")
  @TableField("creator_id")
  private String creatorId;

  /** 创建人姓名 */
  @Schema(description = "创建人姓名")
  @TableField("creator_name")
  private String creatorName;


  /** 规则链DSL内容(JSON格式) */
  @Schema(description = "规则链DSL内容")
  @TableField("dsl_content")
  private String dslContent;

  /** 最后同步时间 */
  @Schema(description = "最后同步时间")
  @TableField("last_sync_time")
  private Date lastSyncTime;


  /** 是否删除：0-未删除，1-已删除 */
  @Schema(description = "是否删除")
  @TableField("deleted")
  private Integer deleted;
}
