package org.springblade.modules.iot.enumeration.plugin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginStatusEnum
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
@Schema(title = "PluginStatusEnum", description = "插件信息状态 枚举")
public enum PluginInfoStatusEnum {
    UPLOADED(0, "上传成功"),
    PRELOAD_SUCCEEDED(1, "预加载成功"),
    PRELOAD_FAILED(2, "预加载失败"),
    ;


    PluginInfoStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    private Integer value;
    private String desc;

    public static Optional<PluginInfoStatusEnum> fromValue(Integer value) {
        return Stream.of(PluginInfoStatusEnum.values())
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