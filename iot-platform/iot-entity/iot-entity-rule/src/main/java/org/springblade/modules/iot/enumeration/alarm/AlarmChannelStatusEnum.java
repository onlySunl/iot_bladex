package org.springblade.modules.iot.enumeration.alarm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 告警渠道状态
 * </p>
 *
 * @author shihuan sun
 * @date 2024-05-31
 */
@Getter
@Schema(title = "AlarmChannelStatusEnum", description = "告警渠道状态枚举")
public enum AlarmChannelStatusEnum {

    LOCKED(0, "停用"),

    ACTIVATED(1, "启用"),

    ;


    AlarmChannelStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    private Integer value;
    private String desc;

    public static Optional<AlarmChannelStatusEnum> fromValue(Integer value) {
        return Optional.ofNullable(value)
                .flatMap(val -> Stream.of(AlarmChannelStatusEnum.values())
                        .filter(e -> e.getValue().equals(val))
                        .findFirst());
    }


    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }


    public String getDesc() {
        return this.desc;
    }

}