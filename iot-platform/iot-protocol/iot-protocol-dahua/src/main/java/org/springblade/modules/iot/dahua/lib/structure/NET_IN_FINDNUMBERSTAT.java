package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 开始查询数量统计入参
 */
public class NET_IN_FINDNUMBERSTAT extends SdkStructure {
    public int dwSize;
    public NET_TIME stuStartTime;
    public NET_TIME stuEndTime;
    public int nChannel;
    public int emStatType;

    public NET_IN_FINDNUMBERSTAT() {
        dwSize = size();
        stuStartTime = new NET_TIME();
        stuEndTime = new NET_TIME();
    }
}
