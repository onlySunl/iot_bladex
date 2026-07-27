package org.springblade.modules.iot.enumeration.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: RuleExecutionStatusEnum
 * -----------------------------------------------------------------------------
 * Description:
 * 规则执行状态
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/12/4       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/12/4 11:42
 */
@Getter
@Schema(title = "RuleExecutionStatusEnum", description = "规则执行状态枚举")
public enum RuleExecutionStatusEnum {
    /**
     * 未执行
     */
    NOT_EXECUTED(0, "未执行"),

    /**
     * 执行中
     */
    IN_PROGRESS(1, "执行中"),

    /**
     * 已完成
     */
    COMPLETED(2, "已完成");


    private Integer value;
    private String desc;
    RuleExecutionStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    /**
     * 根据值获取枚举类型
     *
     * @param value 枚举值
     * @return Optional<RuleExecutionStatusEnum>
     */
    public static Optional<RuleExecutionStatusEnum> fromValue(Integer value) {
        return Stream.of(RuleExecutionStatusEnum.values())
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