package org.springblade.modules.iot.common.constant;

import static com.mqttsnet.basic.context.ContextConstants.TENANT_BASE_POOL_NAME_HEADER;
import static com.mqttsnet.basic.context.ContextConstants.TENANT_EXTEND_POOL_NAME_HEADER;

/**
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/15 6:05 下午
 * @create [2021/9/15 6:05 下午 ] [mqttsnet] [初始创建]
 */
public interface DsConstant {
    /**
     * 默认数据源
     */
    String DEFAULTS = "0";

    /**
     * 时序性默认数据源
     */
    String TDS_DEFAULTS = "1";

    /**
     * 动态租户数据源
     */
    String BASE_TENANT = "#thread." + TENANT_BASE_POOL_NAME_HEADER;
    String EXTEND_TENANT = "#thread." + TENANT_EXTEND_POOL_NAME_HEADER;
}
