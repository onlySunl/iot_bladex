package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 电视墙结构
 */
public class NET_MONITORWALL extends SdkStructure {
    /**
     * 电视墙名称
     */
    public byte[] szName = new byte[64];
    /**
     * 电视墙ID
     */
    public int nMonitorWallID;

    public NET_MONITORWALL() {
    }
}
