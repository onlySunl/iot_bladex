package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/**
 * @author 260611
 * @description 设备列表
 * @date 2022/04/20 11:31:57
 */
public class NET_THINGS_DEVLIST_INFO extends SdkStructure {
    /**
     * "1","2"等
     */
    public byte[]           szDevID = new byte[128];
    /**
     * "001","002"等
     */
    public byte[]           szDevClass = new byte[128];
    /**
     * 预留字节
     */
    public byte[]           szReserved = new byte[1024];
}

