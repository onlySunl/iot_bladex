package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 订阅视频统计摘要出参
 */
public class NET_OUT_ATTACH_VIDEOSTAT_SUM extends SdkStructure {
    public int dwSize;

    public NET_OUT_ATTACH_VIDEOSTAT_SUM() {
        dwSize = size();
    }
}
