package org.springblade.model.enumeration;

import org.springblade.common.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否
 *
 * @author mqttsnet
 * @date 2021/4/16 11:26 上午
 */
@Getter
@AllArgsConstructor
@Schema(title = "BooleanEnum", description = "布尔")
public enum BooleanEnum implements BaseEnum {
    TRUE(true, 1, "1", "是"),
    FALSE(false, 0, "0", "否");
    private final Boolean bool;
    private final int integer;
    private final String str;
    private final String desc;

    @Override
    public String getCode() {
        return this.bool.toString();
    }

    public boolean eq(Integer val) {
        if (val == null) {
            return FALSE.getBool();
        }
        return val.equals(this.getInteger());
    }

    public boolean eq(String val) {
        if (val == null) {
            return FALSE.getBool();
        }
        return val.equals(this.getStr());
    }

    public boolean eq(Boolean val) {
        if (val == null) {
            return FALSE.getBool();
        }
        return val.equals(this.getBool());
    }
}
