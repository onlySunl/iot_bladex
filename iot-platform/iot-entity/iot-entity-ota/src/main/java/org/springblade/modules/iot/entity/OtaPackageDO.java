

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * ota包 DO
 *
 * @author EnjoyIot
 */
@TableName("iot_ota_package")
// @KeySequence("ota_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtaPackageDO extends CustomBaseEntity {

    /**
     * 产品key
     */
    @AutoColumn(comment = "产品key")
    @TableField("product_key")
    private String productKey;
    /**
     * 名称
     */
    @AutoColumn(comment = "名称")
    @TableField("name")
    private String name;
    /**
     * 升级包地址
     */
    @AutoColumn(comment = "升级包地址")
    @TableField("url")
    private String url;
    /**
     * 版本
     */
    @AutoColumn(comment = "版本")
    @TableField("version")
    private String version;
    /**
     * 升级包大小
     */
    @AutoColumn(comment = "升级包大小")
    @TableField("size")
    private Long size;
    /**
     * 签名方式
     */
    @AutoColumn(comment = "签名方式")
    @TableField("sign_method")
    private String signMethod;
    /**
     * 签名内容
     */
    @AutoColumn(comment = "签名内容")
    @TableField("sign")
    private String sign;
    /**
     * 额外内容
     */
    @AutoColumn(comment = "额外内容")
    @TableField("ext_data")
    private String extData;
    /**
     * 是否差分包
     */
    @AutoColumn(comment = "是否差分包")
    @TableField("is_diff")
    private Boolean isDiff;
    /**
     * md5
     */
    @AutoColumn(comment = "md5")
    @TableField("md5")
    private String md5;
    /**
     * 模块
     */
    @AutoColumn(comment = "模块")
    @TableField("module")
    private String module;

}
