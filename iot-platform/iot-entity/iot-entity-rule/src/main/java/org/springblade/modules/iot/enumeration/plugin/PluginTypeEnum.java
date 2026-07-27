package org.springblade.modules.iot.enumeration.plugin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginInfoTypeEnum
 * -----------------------------------------------------------------------------
 * Description:
 * PluginInfoTypeEnum
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
 * @date 2024/8/30 17:37
 */
@Getter
@Schema(title = "PluginTypeEnum", description = "插件类型 枚举")
public enum PluginTypeEnum {
    DEVICE_PROTOCOL(0, "设备协议插件"),
    BUSINESS(1, "业务插件");

    private Integer value;
    private String desc;
    PluginTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static Optional<PluginTypeEnum> fromValue(Integer value) {
        return Stream.of(PluginTypeEnum.values())
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