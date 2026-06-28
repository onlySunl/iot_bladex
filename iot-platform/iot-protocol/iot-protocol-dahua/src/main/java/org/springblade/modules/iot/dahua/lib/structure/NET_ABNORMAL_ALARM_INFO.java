package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 设备异常报警状态灯色控制
*/
public class NET_ABNORMAL_ALARM_INFO extends SdkStructure
{
    /**
     * 视频遮挡状态灯色,参见结构体定义 {@link NET_PARKINGSPACELIGHT_INFO}
    */
    public NET_PARKINGSPACELIGHT_INFO stuVideoBlind = new NET_PARKINGSPACELIGHT_INFO();
    /**
     * 烟雾火焰状态灯色,参见结构体定义 {@link NET_PARKINGSPACELIGHT_INFO}
    */
    public NET_PARKINGSPACELIGHT_INFO stuSmokeFire = new NET_PARKINGSPACELIGHT_INFO();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[256];

    public NET_ABNORMAL_ALARM_INFO() {
    }
}

