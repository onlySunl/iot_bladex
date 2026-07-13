package org.springblade.modules.iot.common.enums;

public enum PushStatus {
SUCCESS("成功"),
FAILED("失败"),
RETRYING("重试中"),
TIMEOUT("超时"),
CANCELLED("已取消");

private final String description;

PushStatus(String description) {
this.description = description;
}

String getDescription() {
return description;
}
}