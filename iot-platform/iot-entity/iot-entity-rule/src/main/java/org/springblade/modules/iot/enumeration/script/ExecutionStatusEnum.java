package org.springblade.modules.iot.enumeration.script;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * ============================================================================
 * Description:
 * 规则脚本执行结果状态枚举
 * ============================================================================
 *
 * @author Sun Shihuan
 * @version 1.0.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2025/4/13      Sun Shihuan        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2025/4/13 16:51
 */
@Getter
@Schema(title = "ExecutionStatusEnum", description = "规则脚本执行结果状态 枚举")
public enum ExecutionStatusEnum {

    /**
     * 执行失败
     */
    FAILED(500, "执行失败"),
    /**
     * 执行成功
     */
    SUCCESS(200, "执行成功"),
    /**
     * 没有找到脚本
     */
    NO_SCRIPT(4004, "没有找到groovy脚本"),
    /**
     * 参数有误
     */
    PARAM_ERROR(3003, "没有找到groovy脚本");

    private Integer value;
    private String desc;
    ExecutionStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static Optional<ExecutionStatusEnum> fromValue(Integer value) {
        return Stream.of(ExecutionStatusEnum.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst();
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}