package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import static org.springblade.modules.iot.dahua.lib.NET_DEVICE_NAME_LEN;
import static org.springblade.modules.iot.dahua.lib.NET_TSCHE_DAY_NUM;

/**
 * 电视墙预案时间表
 *
 * @author ： 47040
 * @since ： Created in 2020/10/19 9:40
 */
public class MONITORWALL_COLLECTION_SCHEDULE extends SdkStructure {
    /**
     * 结构体大小
     */
    public int              dwSize;
    /**
     * 预案名称
     */
    public byte[]           szName = new byte[NET_DEVICE_NAME_LEN];
    /**
     * 时间表
     */
    public NET_TSECT_DAY[]  stuSchedules = new NET_TSECT_DAY[NET_TSCHE_DAY_NUM];

    public MONITORWALL_COLLECTION_SCHEDULE() {
        dwSize = this.size();
        for (int i = 0; i < stuSchedules.length; i++) {
            stuSchedules[i] = new NET_TSECT_DAY();
        }
    }
}

