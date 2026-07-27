package org.springblade.modules.iot.enumeration.plugin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginRunModeEnum
 * -----------------------------------------------------------------------------
 * Description:
 * PluginRunModeEnum
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/30       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/30 17:39
 */
@Getter
@Schema(title = "PluginRunModeEnum", description = "插件运行模式 枚举")
public enum PluginRunModeEnum {
    SINGLE_NODE(0, "单节点"),
    CLUSTER(1, "集群");

    private Integer value;
    private String desc;
    PluginRunModeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static Optional<PluginRunModeEnum> fromValue(Integer value) {
        return Stream.of(PluginRunModeEnum.values())
                .filter(runMode -> runMode.getValue().equals(value))
                .findFirst();
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}