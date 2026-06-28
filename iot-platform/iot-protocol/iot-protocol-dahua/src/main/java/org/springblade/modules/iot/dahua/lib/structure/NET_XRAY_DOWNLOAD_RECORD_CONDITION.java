package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 查询条件
*/
public class NET_XRAY_DOWNLOAD_RECORD_CONDITION extends SdkStructure
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
     * 通道号
    */
    public int              nChannel;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_XRAY_DOWNLOAD_RECORD_CONDITION() {
    }
}

