package org.springblade.modules.iot.base.utils;

import org.springblade.core.basic.utils.Func;
import org.springblade.core.basic.utils.StringUtil;

/**
 * IoT 工具类
 *
 * @author Chill
 */
public class IotUtils {

    private IotUtils() {
    }

    /**
     * 生成设备 Topic
     *
     * @param productKey 产品Key
     * @param deviceName 设备名称
     * @param topicType  Topic类型
     * @return 设备 Topic
     */
    public static String generateDeviceTopic(String productKey, String deviceName, String topicType) {
        return StringUtil.format("/{}/{}/{}", productKey, deviceName, topicType);
    }

    /**
     * 生成产品 Topic
     *
     * @param productKey 产品Key
     * @param topicType  Topic类型
     * @return 产品 Topic
     */
    public static String generateProductTopic(String productKey, String topicType) {
        return StringUtil.format("/{}/{}", productKey, topicType);
    }

    /**
     * 生成广播 Topic
     *
     * @param productKey 产品Key
     * @param topicType  Topic类型
     * @return 广播 Topic
     */
    public static String generateBroadcastTopic(String productKey, String topicType) {
        return StringUtil.format("/{}/broadcast/{}", productKey, topicType);
    }

    /**
     * 校验 ProductKey 格式
     *
     * @param productKey 产品Key
     * @return 是否有效
     */
    public static boolean isValidProductKey(String productKey) {
        if (Func.isBlank(productKey)) {
            return false;
        }
        return productKey.matches("^[a-zA-Z0-9]{8,32}$");
    }

    /**
     * 校验 DeviceName 格式
     *
     * @param deviceName 设备名称
     * @return 是否有效
     */
    public static boolean isValidDeviceName(String deviceName) {
        if (Func.isBlank(deviceName)) {
            return false;
        }
        return deviceName.matches("^[a-zA-Z0-9_\\-]{4,32}$");
    }

}
