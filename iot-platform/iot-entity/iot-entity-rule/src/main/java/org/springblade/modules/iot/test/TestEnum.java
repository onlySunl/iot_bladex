package org.springblade.modules.iot.test;

import lombok.Getter;

@Getter
public enum TestEnum {
    A(1, "a"),
    B(2, "b");

    private Integer value;
    private String desc;

    TestEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
