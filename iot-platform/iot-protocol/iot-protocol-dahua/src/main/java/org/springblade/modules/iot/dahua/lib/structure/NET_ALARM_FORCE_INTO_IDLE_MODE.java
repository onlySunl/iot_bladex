package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 强制进入休眠模式通知 (对应 DH_ALARM_FORCE_INTO_IDLE_MODE)
*/
public class NET_ALARM_FORCE_INTO_IDLE_MODE extends SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 事件动作, 0:Pulse
    */
    public int              nAction;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 事件发生的时间,参见结构体定义 {@link NET_TIME_EX}
    */
    public NET_TIME_EX stuUTC = new NET_TIME_EX();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_ALARM_FORCE_INTO_IDLE_MODE() {
    }
}

