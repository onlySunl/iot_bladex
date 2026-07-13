package org.springblade.modules.iot.common.enums;

enum MessageCategory {
/** 认证消息 */
AUTH,
/** 事件消息 */
EVENT,
/** 数据消息 */
DATA,
/** 心跳消息 */
PING,
/** 订阅消息 */
SUBSCRIBE,
/** 其他消息 */
OTHER
}