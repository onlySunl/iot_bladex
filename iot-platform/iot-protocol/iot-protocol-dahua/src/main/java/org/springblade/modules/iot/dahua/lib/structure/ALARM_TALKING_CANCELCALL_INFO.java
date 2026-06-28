package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 设备呼叫中取消呼叫事件(对应事件 DH_ALARM_TALKING_CANCELCALL)
*/
public class ALARM_TALKING_CANCELCALL_INFO extends SdkStructure
{
    /**
     * 事件发生的时间,参见结构体定义 {@link NET_TIME_EX}
    */
    public NET_TIME_EX stuTime = new NET_TIME_EX();
    /**
     * 呼叫ID
    */
    public byte[]           szCallID = new byte[32];

    public ALARM_TALKING_CANCELCALL_INFO() {
    }
}

