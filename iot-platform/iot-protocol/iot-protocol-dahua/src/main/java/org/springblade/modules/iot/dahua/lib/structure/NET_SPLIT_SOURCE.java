package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 分割源信息
 */
public class NET_SPLIT_SOURCE extends SdkStructure {
    public int dwSize;
    public byte[] szIp = new byte[64];
    public int nPort;
    public byte[] szDeviceId = new byte[64];
    public int nChannel;
    public int nStreamType;
    
    public NET_SPLIT_SOURCE() {
        this.dwSize = this.size();
    }
}
