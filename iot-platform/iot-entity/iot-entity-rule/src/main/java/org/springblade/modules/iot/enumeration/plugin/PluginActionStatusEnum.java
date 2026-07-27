package org.springblade.modules.iot.enumeration.plugin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginActionStatusEnum
 * -----------------------------------------------------------------------------
 * Description:
 * 插件操作状态枚举，用于表示插件的安装或卸载状态
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/11/15       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/11/15 17:22
 */
@Getter
@Schema(title = "PluginActionStatusEnum", description = "插件操作状态枚举，包括安装和卸载")
public enum PluginActionStatusEnum {

    INSTALL(0, "安装"),
    UNINSTALL(1, "卸载");

    private Integer value;
    private String desc;
    PluginActionStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    /**
     * 根据value获取枚举
     *
     * @param value 枚举值
     * @return 对应的枚举实例
     */
    public static Optional<PluginActionStatusEnum> fromValue(Integer value) {
        return Stream.of(PluginActionStatusEnum.values())
                .filter(action -> action.getValue().equals(value))
                .findFirst();
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}