package org.springblade.modules.iot.common.enums;

enum UpgradeMode {
/** 普通模式（下载完成后升级） */
NORMAL("普通模式"),

/** 增量模式（差分升级） */
INCREMENTAL("增量模式"),

/** 流式模式（边下载边升级） */
STREAMING("流式模式");

private final String description;

UpgradeMode(String description) {
this.description = description;
}

String getDescription() {
return description;
}
}