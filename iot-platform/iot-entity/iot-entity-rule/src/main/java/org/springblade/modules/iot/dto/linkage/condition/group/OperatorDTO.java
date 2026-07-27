package org.springblade.modules.iot.dto.linkage.condition.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: OperatorDTO.java
 * -----------------------------------------------------------------------------
 * Description:
 * 条件比较的运算符
 * -----------------------------------------------------------------------------
 *
 * @author ShiHuan Sun
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * <p>
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2023-11-26 22:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(title = "OperatorDTO", description = "用于表示条件比较的运算符")
public class OperatorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "运算符描述", example = "等于")
    private String desc;

    @Schema(description = "运算符值", example = "EQ")
    private String value;
}
