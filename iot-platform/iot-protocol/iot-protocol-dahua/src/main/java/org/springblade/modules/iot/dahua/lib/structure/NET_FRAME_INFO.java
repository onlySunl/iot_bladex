package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 帧信息
 */
public class NET_FRAME_INFO extends SdkStructure {
    public int dwSize;
    public int nTime;
    public int nType;
    public byte[] szFileName = new byte[128];
    
    public NET_FRAME_INFO() {
        this.dwSize = this.size();
    }
}
