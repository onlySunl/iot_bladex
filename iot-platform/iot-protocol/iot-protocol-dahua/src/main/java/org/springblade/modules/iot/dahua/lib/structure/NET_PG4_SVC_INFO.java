package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 对应CLIENT_StartSearchDevicesEx接口TTLV回调
*/
public class NET_PG4_SVC_INFO extends SdkStructure
{
    public int              nEz;
    public int              nEzs;
    /**
     * 扩展字段
    */
    public byte[]           szReserved = new byte[2048];

    public NET_PG4_SVC_INFO() {
    }
}

