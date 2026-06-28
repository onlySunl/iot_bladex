package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.Pointer;

/**
 * 数量统计信息
 */
public class NET_COUNT_STAT extends SdkStructure {
    public int dwSize;
    public NET_TIME stuStartTime;
    public NET_TIME stuEndTime;
    public int nEnteredSubtotal;
    public int nExitedSubtotal;

    public NET_COUNT_STAT() {
        dwSize = size();
        stuStartTime = new NET_TIME();
        stuEndTime = new NET_TIME();
    }
}
