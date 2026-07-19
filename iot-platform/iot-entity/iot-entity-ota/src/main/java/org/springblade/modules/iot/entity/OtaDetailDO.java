

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 设备ota详情 DO
 *
 * @author EnjoyIot
 */
@TableName("iot_device_ota_detail")
// @KeySequence("device_ota_detail_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtaDetailDO extends CustomBaseEntity {

    /**
     * 设备id
     */
    @AutoColumn(comment = "设备id")
    @TableField("device_id")
    private Long deviceId;
    /**
     * 设备名称
     */
    @AutoColumn(comment = "设备名称")
    @TableField("device_name")
    private String deviceName;
    /**
     * 模块
     */
    @AutoColumn(comment = "模块")
    @TableField("module")
    private String module;
    /**
     * ota信息id
     */
    @AutoColumn(comment = "ota信息id")
    @TableField("ota_info_id")
    private Long otaInfoId;
    /**
     * 产品key
     */
    @AutoColumn(comment = "产品key")
    @TableField("product_key")
    private String productKey;
    /**
     * 步骤
     */
    @AutoColumn(comment = "步骤")
    @TableField("step")
    private Integer step;
    /**
     * 任务id
     */
    @AutoColumn(comment = "任务id")
    @TableField("task_id")
    private Long taskId;
    /**
     * 版本
     */
    @AutoColumn(comment = "版本")
    @TableField("version")
    private String version;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

}
