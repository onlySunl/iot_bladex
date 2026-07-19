package org.springblade.modules.iot.entity;

import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


@TableName("iot_modbus_info")
// @KeySequence("eiot_modbus_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModbusInfoDO extends CustomBaseEntity {


    /**
     * 产品名称
     */
    @AutoColumn(comment = "产品名称")
    @TableField("name")
    private String name;

    /**
     * productKey
     */
    @AutoColumn(comment = "productKey")
    @TableField("product_key")
    private String productKey;



}
