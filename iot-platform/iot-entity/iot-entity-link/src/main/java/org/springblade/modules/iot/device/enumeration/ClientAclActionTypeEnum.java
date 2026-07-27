package org.springblade.modules.iot.device.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 客户端Acl动作状态 枚举
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-04
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ClientAclActionTypeEnum {
    /**
     * 发布操作
     */
    PUBLISH(1, "PUBLISH"),

    /**
     * 订阅操作
     */
    SUBSCRIBE(2, "SUBSCRIBE"),

    /**
     * 取消订阅操作
     */
    UNSUBSCRIBE(3, "UNSUBSCRIBE");;

    private Integer value;
    private String desc;

    /**
     * 根据key获取对应的枚举
     *
     * @param value 状态值
     * @return 返回对应的枚举，如果没找到则返回 Optional.empty()
     */
    public static Optional<ClientAclActionTypeEnum> fromValue(Integer value) {
        return Stream.of(ClientAclActionTypeEnum.values())
                .filter(status -> status.getValue().equals(value))
                .findFirst();
    }

}
