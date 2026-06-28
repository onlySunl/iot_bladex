package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 查询帧信息入参
 */
public class NET_IN_FIND_FRAMEINFO_PRAM extends SdkStructure {
    public int dwSize;
    public int nChannel;
    public int nRecordType;
    public int nStartTime;
    public int nEndTime;
    
    public NET_IN_FIND_FRAMEINFO_PRAM() {
        this.dwSize = this.size();
    }
}
