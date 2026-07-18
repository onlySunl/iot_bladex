

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 设备ota信息 DO
 *
 * @author EnjoyIot
 */
@TableName("device_ota_info")
// @KeySequence("device_ota_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceOtaInfoDO extends CustomBaseEntity {

    /**
     * 识别数量
     */
    @AutoColumn(comment = "识别数量")
    @TableField("fail")
    private Integer fail;
    /**
     * 模块
     */
    @AutoColumn(comment = "模块")
    @TableField("module")
    private String module;
    /**
     * 升级包id
     */
    @AutoColumn(comment = "升级包id")
    @TableField("package_id")
    private Long packageId;
    /**
     * 产品key
     */
    @AutoColumn(comment = "产品key")
    @TableField("product_key")
    private String productKey;
    /**
     * 成功数量
     */
    @AutoColumn(comment = "成功数量")
    @TableField("success")
    private Integer success;
    /**
     * 总数
     */
    @AutoColumn(comment = "总数")
    @TableField("total")
    private Integer total;
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
