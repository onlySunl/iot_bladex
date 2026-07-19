

package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 产品物模型 DO
 *
 * @author EnjoyIot
 */
@TableName("iot_thing_model")
// @KeySequence("eiot_thing_model_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThingModelDO extends CustomBaseEntity {

    /**
     * 产品key
     */
    @AutoColumn(comment = "产品key")
    @TableField("product_key")
    private String productKey;
    /**
     * 物模型
     */
    @AutoColumn(comment = "物模型")
    @TableField("model")
    private String model;

}
