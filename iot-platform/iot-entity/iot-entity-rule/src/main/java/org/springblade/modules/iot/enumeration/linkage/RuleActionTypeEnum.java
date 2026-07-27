package org.springblade.modules.iot.enumeration.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: RuleActionTypeEnum
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
 * 2023/12/17       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2023/12/17 20:00
 */
@Getter
@Schema(title = "RuleActionTypeEnum", description = "规则动作类型")
public enum RuleActionTypeEnum {

    /**
     * 命令下发动作
     */
    COMMAND(0, "命令下发动作"),

    /**
     * 触发告警动作
     */
    ALERT(1, "触发告警动作"),

    /**
     * 数据转发动作
     */
    FORWARD(2, "数据转发动作");

    private Integer value;
    private String desc;
    RuleActionTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static Optional<RuleActionTypeEnum> fromValue(Integer value) {
        return Stream.of(RuleActionTypeEnum.values())
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