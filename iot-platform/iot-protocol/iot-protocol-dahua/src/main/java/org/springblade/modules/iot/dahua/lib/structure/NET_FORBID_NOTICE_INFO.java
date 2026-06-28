package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 禁开提醒
*/
public class NET_FORBID_NOTICE_INFO extends SdkStructure
{
    /**
     * 使能
    */
    public int              bEnable;
    /**
     * 提醒内容
    */
    public byte[]           szNoticeString = new byte[128];
    /**
     * 预留字段
    */
    public byte[]           szReserved = new byte[124];

    public NET_FORBID_NOTICE_INFO() {
    }
}

