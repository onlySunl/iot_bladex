package org.springblade.modules.iot.enumeration.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: ExecutionLogTypeEnum
 * -----------------------------------------------------------------------------
 * Description:
 * <p>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/12/2       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/12/2 19:51
 */
@Getter
@Schema(title = "ExecutionLogTypeEnum", description = "执行日志类型枚举")
public enum ExecutionLogTypeEnum {

    /**
     * 规则执行日志
     */
    RULE(0, "规则执行日志"),

    /**
     * 条件执行日志
     */
    CONDITION(1, "条件执行日志"),

    /**
     * 动作执行日志
     */
    ACTION(2, "动作执行日志");

    private Integer value;
    private String desc;
    ExecutionLogTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    /**
     * 根据值获取枚举类型
     *
     * @param value 枚举值
     * @return Optional<ExecutionLogTypeEnum>
     */
    public static Optional<ExecutionLogTypeEnum> fromValue(Integer value) {
        return Stream.of(ExecutionLogTypeEnum.values())
                .filter(e -> e.getValue().equals(value))
                .findFirst();
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}