package org.springblade.modules.iot.dinger.enumeration;

import com.mqttsnet.basic.interfaces.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * @program: blade-cloud
 * @description: 通知渠道类型 枚举
 * @packagename: org.springblade.modules.iot.dinger.enumeration
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2023-09-11 00:20
 **/
@Getter
@NoArgsConstructor
@Schema(title = "NotificationChannelType", description = "通知渠道类型")
public enum NotificationChannelType implements BaseEnum {

    DINGTALK("dingTalk", "钉钉"),
    FEISHU("fresh", "飞书"),
    WEWORK("weWork", "企业微信"),
    EMAIL("email", "电子邮件"),
    SMS("sms", "短信");

    @Schema(description = "编码")
    private String value;

    @Schema(description = "描述")
    private String desc;

    NotificationChannelType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static NotificationChannelType match(String val) {
        return Stream.of(values())
                .filter(item -> item.value.equalsIgnoreCase(val))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getCode() {
        return this.name();
    }

    public boolean eq(NotificationChannelType type) {
        return type != null && this.name().equals(type.name());
    }

}
