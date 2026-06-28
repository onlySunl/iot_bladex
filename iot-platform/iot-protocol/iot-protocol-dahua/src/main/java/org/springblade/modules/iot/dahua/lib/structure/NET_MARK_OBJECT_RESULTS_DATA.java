package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 收藏结果集合
*/
public class NET_MARK_OBJECT_RESULTS_DATA extends SdkStructure
{
    /**
     * 收藏成功的唯一标识，若值为-1表示收藏失败
    */
    public int              nID;
    /**
     * 保留字段
    */
    public byte[]           szReserved = new byte[1020];

    public NET_MARK_OBJECT_RESULTS_DATA() {
    }
}

