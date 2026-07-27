package org.springblade.modules.iot.enumeration.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 规则信息状态
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Schema(title = "RuleStatusEnum", description = "规则信息状态类型")
public enum RuleStatusEnum {

    /**
     * 已锁定
     */
    LOCKED(0, "停用"),

    /**
     * 已激活
     */
    ACTIVATED(1, "启用"),

    ;


    RuleStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 可选值
     */
    public static final List<Integer> STATE_COLLECTION = List.of(ACTIVATED.value, LOCKED.value);
    private Integer value;
    private String desc;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
