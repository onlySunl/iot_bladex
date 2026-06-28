package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 车流量统计结果信息
*/
public class NET_VEHICLE_FLOW_STAT_INFO extends SdkStructure
{
    /**
     * 开始时间,参见结构体定义 {@link NET_TIME}
    */
    public NET_TIME stuStartTime = new NET_TIME();
    /**
     * 结束时间,参见结构体定义 {@link NET_TIME}
    */
    public NET_TIME stuEndTime = new NET_TIME();
    /**
     * 总车辆
    */
    public int              nTotal;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_VEHICLE_FLOW_STAT_INFO() {
    }
}

