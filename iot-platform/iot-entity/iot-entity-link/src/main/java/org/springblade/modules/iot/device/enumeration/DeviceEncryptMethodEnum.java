package org.springblade.modules.iot.device.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 协议加密方式
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "DeviceEncryptMethodEnum", description = "协议加密方式类型")
public enum DeviceEncryptMethodEnum {

    /**
     * 明文
     */
    PLAINTEST(0, "明文"),

    /**
     * SM4
     */
    SM4(1, "SM4"),

    /**
     * AES256
     */
    AES256(2, "AES256"),
    ;

    private Integer value;
    private String desc;

    public static DeviceEncryptMethodEnum findMatchingHandler(Integer cipherFlag) {
        return Stream.of(values())
                .filter(handler -> handler.getValue().equals(cipherFlag))
                .findFirst()
                .orElse(null);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
