package org.springblade.modules.iot.common.enums;

public enum WebSocketUPRequestProcessingStage {
/** 初始化 */
INIT,
/** 已认证 */
AUTHENTICATED,
/** 已提取元数据 */
METADATA_EXTRACTED,
/** 已解码 */
DECODED,
/** 已验证 */
VALIDATED,
/** 已推送 */
PUBLISHED,
/** 处理完成 */
COMPLETED,
/** 处理失败 */
FAILED
}