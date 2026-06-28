package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 查询帧信息出参
 */
public class NET_OUT_FIND_FRAMEINFO_PRAM extends SdkStructure {
    public int dwSize;
    public int nFound;
    public NET_FRAME_INFO[] pstuFrameInfo;
    public int nMaxCount;
    public int nRetCount;
    
    public NET_OUT_FIND_FRAMEINFO_PRAM() {
        this.dwSize = this.size();
    }
}
