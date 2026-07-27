package org.springblade.modules.iot.dashboard.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 链路数据类型 枚举
 * </p>
 *
 * @author shihuan sun
 * @date 2023-08-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "LinkDataTypeEnum", description = "链路数据类型 枚举")
public enum LinkDataTypeEnum {
    /**
     * 上行
     */
    UPLINK(0, "Represents an uplink type."),

    /**
     * 下行
     */
    DOWNLINK(1, "Represents a downlink type."),
    ;

    private Integer value;
    private String desc;

    /**
     * 根据key获取对应的枚举
     *
     * @param value 状态值
     * @return 返回对应的枚举，如果没找到则返回null
     */
    public static LinkDataTypeEnum fromValue(Integer value) {
        for (LinkDataTypeEnum status : LinkDataTypeEnum.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
