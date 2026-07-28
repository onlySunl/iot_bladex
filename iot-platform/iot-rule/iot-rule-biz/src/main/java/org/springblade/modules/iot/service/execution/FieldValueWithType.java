package org.springblade.modules.iot.service.execution;

import org.springblade.basic.tds.enumeration.TdDataTypeEnum;
import lombok.Data;
import lombok.Getter;

/**
 * -----------------------------------------------------------------------------
 * File Name: PropertyTypeAndValue
 * -----------------------------------------------------------------------------
 * Description:
 * 用于封装属性值及其数据类型
 * 这个类允许我们在一个对象中同时持有属性的值和它的数据类型，便于之后的处理和转换。
 * -----------------------------------------------------------------------------
 *
 * @author mqttsnet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/4/8       mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/4/8 23:22
 */
@Getter
@Data
public class FieldValueWithType {
    /**
     * 属性的数据类型
     * -- GETTER --
     *  获取属性的数据类型。
     *
     * @return 数据类型枚举

     */
    private final TdDataTypeEnum dataType;
    /**
     * 属性的值
     * -- GETTER --
     *  获取属性的值。
     *
     * @return 属性的值

     */
    private final Object value;

    /**
     * 构造函数，初始化数据类型和值。
     *
     * @param dataType 数据类型枚举
     * @param value    属性的值
     */
    public FieldValueWithType(TdDataTypeEnum dataType, Object value) {
        this.dataType = dataType;
        this.value = value;
    }

}
