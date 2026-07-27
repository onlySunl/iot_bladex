package org.springblade.model.enumeration;

import com.mqttsnet.basic.interfaces.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 用户
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-09
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Sex", description = "性别-枚举")
public enum Sex implements BaseEnum {

    /**
     * M="男"
     */
    M("1", "男"),
    /**
     * W="女"
     */
    W("2", "女"),
    /**
     * N="未知"
     */
    N("0", "未知");

    private String code;
    @Schema(description = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static Sex match(String val, Sex def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static Sex get(String val) {
        return match(val, null);
    }

    public boolean eq(Sex val) {
        return val != null && eq(val.name());
    }

}
