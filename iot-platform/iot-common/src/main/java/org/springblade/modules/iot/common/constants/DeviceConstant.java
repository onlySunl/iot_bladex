package org.springblade.modules.iot.common.constants;

/**
 * IoT设备常量
 */
public class DeviceConstant {

    /** 设备状态 */
    public static final int DEVICE_STATUS_ONLINE = 1;
    public static final int DEVICE_STATUS_OFFLINE = 2;
    public static final int DEVICE_STATUS_INACTIVE = 3;

    /** 日志类型 */
    public static final int LOG_TYPE_PROPERTY = 1;
    public static final int LOG_TYPE_FUNCTION = 2;
    public static final int LOG_TYPE_EVENT = 3;
    public static final int LOG_TYPE_UPGRADE = 4;
    public static final int LOG_TYPE_ONLINE = 5;
    public static final int LOG_TYPE_OFFLINE = 6;

    /** 协议类型 */
    public static final String PROTOCOL_MQTT = "MQTT";
    public static final String PROTOCOL_TCP = "TCP";
    public static final String PROTOCOL_UDP = "UDP";
    public static final String PROTOCOL_HTTP = "HTTP";
    public static final String PROTOCOL_COAP = "CoAP";

    /** 桥接类型 */
    public static final int BRIDGE_TYPE_HTTP = 3;
    public static final int BRIDGE_TYPE_MQTT = 4;
    public static final int BRIDGE_TYPE_DATABASE = 5;

    /** 桥接方向 */
    public static final int BRIDGE_DIRECTION_INPUT = 1;
    public static final int BRIDGE_DIRECTION_OUTPUT = 2;

    /** 物模型类型 */
    public static final int MODEL_TYPE_PROPERTY = 1;
    public static final int MODEL_TYPE_FUNCTION = 2;
    public static final int MODEL_TYPE_EVENT = 3;

    /** 设备类型 */
    public static final int DEVICE_TYPE_DIRECT = 1;
    public static final int DEVICE_TYPE_GATEWAY = 2;
    public static final int DEVICE_TYPE_SUB = 3;

    /** 告警级别 */
    public static final int ALERT_LEVEL_INFO = 1;
    public static final int ALERT_LEVEL_WARNING = 2;
    public static final int ALERT_LEVEL_CRITICAL = 3;

    /** 任务类型 */
    public static final int JOB_TYPE_TIMER = 1;
    public static final int JOB_TYPE_ALERT = 2;
    public static final int JOB_TYPE_SCENE = 3;

    private DeviceConstant() {}
}
