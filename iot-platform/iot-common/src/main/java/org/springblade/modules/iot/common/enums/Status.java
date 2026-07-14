package org.springblade.modules.iot.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 通用状态枚举
 */
@Getter
public enum Status {

    SUCCESS(1, "成功"),
    FAILED(0, "失败"),
    RUNNING(2, "运行中");

    private final Integer code;
    private final String desc;

    Status(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Jackson序列化返回code，前端拿到数字
     */
    @JsonValue
    public Integer getCode() {
        return code;
    }

    /**
     * 根据code获取枚举
     * @param code 状态码
     * @return Status
     */
    public static Status getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (Status value : Status.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("无效状态码：" + code);
    }
}