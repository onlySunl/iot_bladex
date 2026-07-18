

package org.springblade.modules.iot.component.core.model.down;



import org.springblade.modules.iot.component.core.model.AbstractAction;
import org.springblade.modules.iot.component.core.model.ActionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 设备配置
 *
 * @author sjg
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DeviceOta extends AbstractAction {

    /**
     * 配置信息
     */
    private Object data;

    @Override
    public ActionType getType() {
        return ActionType.OTA;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtaData{
        /**
         * 文件包大小
         */
        private Long size;

        /**
         * 签名
         */
        private String sign;

        /**
         * 是否差分升级
         */
        private Boolean isDiff;

        /**
         * 文件MD5后的值
         */
        private String md5;

        /**
         * 包名
         */
        private String name;

        /**
         * 描述
         */
        private String remark;

        /**
         * 版本
         */
        private String version;

        /**
         * 升级包地址
         */
        private String url;

        /**
         * 签名方式
         */
        private String signMethod;

        /**
         * 模块
         */
        private String module;

        /**
         * 扩展数据
         */
        private String extData;
    }
}
