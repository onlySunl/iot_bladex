package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 继续查询数量统计出参
 */
public class NET_OUT_DOFINDNUMBERSTAT extends SdkStructure {
    public int dwSize;
    public int nRetCount;
    public NET_COUNT_STAT[] stuStatInfo;

    public NET_OUT_DOFINDNUMBERSTAT() {
        dwSize = size();
        nRetCount = 16;
        stuStatInfo = new NET_COUNT_STAT[16];
        for (int i = 0; i < 16; i++) {
            stuStatInfo[i] = new NET_COUNT_STAT();
        }
    }
}
