package org.springblade.modules.iot.enumeration.plugin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginInstanceStatusEnum
 * -----------------------------------------------------------------------------
 * Description:
 * PluginStatusEnum
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
 * @date 2024/8/30 17:35
 */
@Getter
@Schema(title = "PluginInstanceStatusEnum", description = "插件实例状态 枚举")
public enum PluginInstanceStatusEnum {
    INSTALL_PENDING(0, "待安装"),
    INSTALL_SUCCEEDED(1, "安装失败"),
    INSTALL_FAILED(2, "安装失败"),
    UNINSTALL_SUCCEEDED(3, "卸载成功"),
    UNINSTALL_FAILED(4, "卸载失败"),
    UNKNOWN(5, "未知异常");

    private Integer value;
    private String desc;
    PluginInstanceStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static Optional<PluginInstanceStatusEnum> fromValue(Integer value) {
        return Stream.of(PluginInstanceStatusEnum.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}