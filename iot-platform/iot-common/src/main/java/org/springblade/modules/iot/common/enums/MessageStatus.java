package org.springblade.modules.iot.common.enums;

enum MessageStatus {
CREATED, // 已创建
PUBLISHING, // 发布中
PUBLISHED, // 已发布
ACKNOWLEDGED, // 已确认
FAILED, // 发布失败
EXPIRED, // 已过期
CANCELLED // 已取消
}