package org.springblade.modules.iot.common.enums;

enum ReportType {
/** 固件信息上报 */
FIRMWARE_INFO("固件信息上报"),

/** 升级进度上报 */
UPGRADE_PROGRESS("升级进度上报"),

/** 升级结果上报 */
UPGRADE_RESULT("升级结果上报");

private final String description;

ReportType(String description) {
this.description = description;
}

String getDescription() {
return description;
}
}