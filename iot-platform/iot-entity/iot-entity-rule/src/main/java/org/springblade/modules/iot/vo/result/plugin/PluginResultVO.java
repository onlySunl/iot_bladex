package org.springblade.modules.iot.vo.result.plugin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 插件包结果
 * </p>
 *
 * @author mqttsnet
 * @date 2024-09-03 18:05:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Builder
@Schema(description = "插件包结果")
public class PluginResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 插件标识 （pluginCode + pluginVersion）
     */
    @Schema(description = "插件标识")
    private String pluginIdentification;

    /**
     * 插件编码
     */
    @Schema(description = "插件编码")
    private String pluginCode;

    /**
     * 插件描述
     */
    @Schema(description = "插件描述")
    private String pluginDesc;

    /**
     * 插件版本
     */
    @Schema(description = "插件版本")
    private String pluginVersion;

    /**
     * 插件扩展信息
     */
    @Schema(description = "插件扩展信息")
    private String pluginExt;

    /**
     * 插件启动类
     */
    @Schema(description = "插件启动类")
    private String pluginBootClass;

    /**
     * 配置支持列表
     */
    @Schema(description = "配置支持列表")
    private List<ConfigSupport> configSupportList;


    /**
     * 配置支持的内部类
     */
    @Data
    @Schema(description = "配置支持")
    public static class ConfigSupport implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 配置项名称
         */
        @Schema(description = "配置项名称")
        private String keyName;

        /**
         * 配置项默认值
         */
        @Schema(description = "配置项默认值")
        private String defaultValue;

        /**
         * 配置项描述
         */
        @Schema(description = "配置项描述")
        private String desc;

        /**
         * 是否必填
         */
        @Schema(description = "是否必填")
        private boolean required;

        /**
         * 插件标识 （pluginCode + pluginVersion）
         */
        @Schema(description = "插件标识")
        private String pluginIdentification;
    }
}
