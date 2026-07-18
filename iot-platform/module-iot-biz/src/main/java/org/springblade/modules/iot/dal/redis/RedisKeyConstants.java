

package org.springblade.modules.iot.dal.redis;

/**
 * yl Redis Key 枚举类
 *
 * @author EnjoyIot
 */
public interface RedisKeyConstants {

    /**
    产品缓存
     */
    String PRODUCT = "iot:product";
    /**
     设备缓存
     */

    String DEVICE = "iot:device";
    String DEVICE_ID = "iot:deviceId";

    /**
     设备最新上报缓存
     */
    String DEVICE_LAST="iot:deviceLast:";
    /**
     设备属性缓存
     */

    String DEVICE_PROPERTY="iot:deviceProperty:";

    /**
     * 设备配置缓存
     */
    String DEVICE_CONFIG = "iot:deviceConfig";

    String DEVICE_ID_CONFIG = "iot:deviceIdConfig";

    /**
     产品物模型缓存
     */
    String THING_MODEL = "iot:thing_model";



}
