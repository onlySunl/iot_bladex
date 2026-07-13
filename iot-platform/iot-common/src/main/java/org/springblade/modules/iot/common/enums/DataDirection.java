package org.springblade.modules.iot.common.enums;

enum DataDirection {
/** 仅数据输入（从外部系统拉取数据） */
INPUT("INPUT", "数据输入"),

/** 仅数据输出（向外部系统推送数据） */
OUTPUT("OUTPUT", "数据输出"),

/** 双向流转（既可输入也可输出） */
BIDIRECTIONAL("BIDIRECTIONAL", "双向流转");

private final String code;
private final String description;

DataDirection(String code, String description) {
this.code = code;
this.description = description;
}

String getCode() {
return code;
}

String getDescription() {
return description;
}
}