package org.springblade.modules.iot.enumeration.linkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 规则实例状态
 * </p>
 *
 * @author shihuan sun
 * @date 2023-04-14
 */
@Getter
@Schema(title = "RuleInstanceStatusEnum", description = "规则实例状态类型")
public enum RuleInstanceStatusEnum {

    /**
     * 已激活
     */
    ACTIVATED(0, "启用"),

    /**
     * 已锁定
     */
    LOCKED(1, "停用"),

    ;


    RuleInstanceStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    /**
     * 可选值
     */
    public static final List<Integer> STATE_COLLECTION = List.of(ACTIVATED.value, LOCKED.value);
    private Integer value;
    private String desc;

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