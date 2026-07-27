package org.springblade.modules.iot.protocol.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 设备Acl动作状态 枚举
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-04
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceAclRuleActionTypeEnum", description = "设备Acl动作状态 枚举")
public enum DeviceAclActionTypeEnum {
    /**
     * 发布操作
     */
    PUBLISH(1, "发布"),

    /**
     * 订阅操作
     */
    SUBSCRIBE(2, "订阅"),

    /**
     * 取消订阅操作
     */
    UNSUBSCRIBE(3, "取消订阅");;

    private Integer value;
    private String desc;

    /**
     * 根据key获取对应的枚举
     *
     * @param value 状态值
     * @return 返回对应的枚举，如果没找到则返回 Optional.empty()
     */
    public static Optional<DeviceAclActionTypeEnum> fromValue(Integer value) {
        return Stream.of(DeviceAclActionTypeEnum.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }

}
