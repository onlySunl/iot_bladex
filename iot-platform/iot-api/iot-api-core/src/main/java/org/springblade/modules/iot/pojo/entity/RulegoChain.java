

package org.springblade.modules.iot.pojo.entity;

import org.springblade.modules.iot.persistence.common.inteceptor.SQenGenId;
import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * rulego规则链管理实体
 *
 * @author gitee.com/NexIoT
 * @since 2025/01/15
 */
@TableName("rulego_chain")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RulegoChain extends CustomBaseEntity {

  private static final long serialVersionUID = 1L;

  /** 主键ID */
  @KeySql(genId = SQenGenId.class)
  @Schema(description = "主键ID")
  private Long id;

  /** rulego规则链ID */
  @Schema(description = "rulego规则链ID")
  @TableField(value = "rulego_id")
  @AutoColumn(comment = "rulego规则链ID", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String rulegoId;

  /** 规则链名称 */
  @Schema(description = "规则链名称")
  @TableField(value = "chain_name")
  @AutoColumn(comment = "规则链名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String chainName;

  /** 规则链描述 */
  @Schema(description = "规则链描述")
  @TableField(value = "description")
  @ColumnType("text")
  @AutoColumn(comment = "规则链描述", defaultValueType = DefaultValueEnum.NULL)
  private String description;

  /** 创建人unionId */
  @Schema(description = "创建人unionId")
  @TableField(value = "creator_id")
  @AutoColumn(comment = "创建人unionId", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;

  /** 创建人姓名 */
  @Schema(description = "创建人姓名")
  @TableField(value = "creator_name")
  @AutoColumn(comment = "创建人姓名", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorName;

  /** 状态：draft-草稿，deployed-已部署，stopped-已停止 */
  @Schema(description = "状态")
  @TableField(value = "status")
  @AutoColumn(comment = "状态：draft-草稿，deployed-已部署，stopped-已停止", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String status;

  /** 规则链DSL内容(JSON格式) */
  @Schema(description = "规则链DSL内容")
  @TableField(value = "dsl_content")
  @ColumnType("text")
  @AutoColumn(comment = "规则链DSL内容(JSON格式)", defaultValueType = DefaultValueEnum.NULL)
  private String dslContent;

  /** 最后同步时间 */
  @Schema(description = "最后同步时间")
  @TableField(value = "last_sync_time")
  @AutoColumn(comment = "最后同步时间", defaultValueType = DefaultValueEnum.NULL)
  private Date lastSyncTime;

  /** 创建时间 */
  @Schema(description = "创建时间")
  @TableField(value = "create_time")
  @AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  private Date createTime;

  /** 更新时间 */
  @Schema(description = "更新时间")
  @TableField(value = "update_time")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  private Date updateTime;

  /** 是否删除：0-未删除，1-已删除 */
  @Schema(description = "是否删除")
  @TableField(value = "deleted")
  @AutoColumn(comment = "是否删除：0-未删除，1-已删除", defaultValueType = DefaultValueEnum.NULL)
  private Integer deleted;
}
