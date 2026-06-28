package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 订阅视频统计摘要入参
 */
public class NET_IN_ATTACH_VIDEOSTAT_SUM extends SdkStructure {
    public int dwSize;
    public LLong lLoginID;
    public int nChannel;
    public fVideoStatSumCallBack cbVideoStatSum;
    public Pointer dwUser;

    public NET_IN_ATTACH_VIDEOSTAT_SUM() {
        dwSize = size();
    }
}
