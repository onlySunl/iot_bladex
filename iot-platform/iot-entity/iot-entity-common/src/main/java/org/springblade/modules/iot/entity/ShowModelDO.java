

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 产品显示模型 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_show_model")
// @KeySequence("eiot_show_model_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowModelDO extends CustomBaseEntity {

    /**
     * 显示内容
     */
    @AutoColumn(comment = "显示内容")
    @TableField("cnf")
    private String cnf;
    /**
     * 类型 0:详情页, 1配置页
     */
    @AutoColumn(comment = "类型 0:详情页, 1配置页")
    @TableField("typ")
    private Integer typ;
    /**
     * 配置名称
     */
    @AutoColumn(comment = "配置名称")
    @TableField("name")
    private String name;
    /**
     * 模型code
     */
    @AutoColumn(comment = "模型code")
    @TableField("model_code")
    private String modelCode;

    @AutoColumn(comment = "product Key")
    @TableField("product_key")
    private String productKey;


}
