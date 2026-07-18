

package org.springblade.modules.iot.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import lombok.*;
import org.springblade.common.entity.CustomBaseEntity;

/**
 * 告警记录 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_alert_record")
// @KeySequence("eiot_alert_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertRecordDO extends CustomBaseEntity {

    /**
     * 告警时间
     */
    @AutoColumn(comment = "告警时间")
    @TableField("alert_time")
    private Long alertTime;
    /**
     * 告警详情
     */
    @AutoColumn(comment = "告警详情")
    @TableField("details")
    private String details;
    /**
     * 告警等级
     */
    @AutoColumn(comment = "告警等级")
    @TableField("level")
    private String level;
    /**
     * 告警名称
     */
    @AutoColumn(comment = "告警名称")
    @TableField("name")
    private String name;
    /**
     * 是否已读
     */
    @AutoColumn(comment = "是否已读")
    @TableField("read_flg")
    private Boolean readFlg;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

}
