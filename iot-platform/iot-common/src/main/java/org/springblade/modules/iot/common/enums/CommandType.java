package org.springblade.modules.iot.common.enums;

enum CommandType {
/** 开始升级 */
START_UPGRADE("开始升级"),

/** 暂停升级 */
PAUSE_UPGRADE("暂停升级"),

/** 恢复升级 */
RESUME_UPGRADE("恢复升级"),

/** 取消升级 */
CANCEL_UPGRADE("取消升级"),

/** 重新开始升级 */
RESTART_UPGRADE("重新开始升级"),

/** 查询升级状态 */
QUERY_STATUS("查询升级状态");

private final String description;

CommandType(String description) {
this.description = description;
}

String getDescription() {
return description;
}
}