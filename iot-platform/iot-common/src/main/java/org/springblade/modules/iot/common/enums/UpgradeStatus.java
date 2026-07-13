package org.springblade.modules.iot.common.enums;

public enum UpgradeStatus {
/** 空闲状态 */
IDLE("空闲"),

/** 下载中 */
DOWNLOADING("下载中"),

/** 校验中 */
VERIFYING("校验中"),

/** 安装中 */
INSTALLING("安装中"),

/** 重启中 */
REBOOTING("重启中"),

/** 升级成功 */
SUCCESS("升级成功"),

/** 升级失败 */
FAILED("升级失败");

private final String description;

UpgradeStatus(String description) {
this.description = description;
}

String getDescription() {
return description;
}
}