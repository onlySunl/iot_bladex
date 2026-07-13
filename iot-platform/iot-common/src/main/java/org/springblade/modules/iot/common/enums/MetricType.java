package org.springblade.modules.iot.common.enums;

/**
 * Extracted from IoTDashboardStatistics
 */
public enum MetricType {
    DEVICE_TOTAL("device_total", "设备总数"),
    DEVICE_ONLINE("device_online", "在线设备数"),
    MESSAGE_TOTAL("message_total", "消息总数"),
    MESSAGE_SUCCESS("message_success", "成功消息数"),
    MESSAGE_FAILED("message_failed", "失败消息数"),
    MESSAGE_RETRY("message_retry", "重试消息数"),
    MESSAGE_PUSH("message_push", "推送消息数");

    private final String code;
    private final String description;

    MetricType(String code, String description) {
      this.code = code;
      this.description = description;
    }

    public String getCode() {
      return code;
    }

    public String getDescription() {
      return description;
    }
  }
