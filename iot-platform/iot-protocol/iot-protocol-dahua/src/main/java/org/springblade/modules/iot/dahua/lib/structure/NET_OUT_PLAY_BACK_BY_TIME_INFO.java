package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 按时间回放出参
 */
public class NET_OUT_PLAY_BACK_BY_TIME_INFO extends SdkStructure {
    public int dwSize;
    public LLong lPlayBackHandle;

    public NET_OUT_PLAY_BACK_BY_TIME_INFO() {
        dwSize = size();
    }
}
