package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 开始查询数量统计出参
 */
public class NET_OUT_FINDNUMBERSTAT extends SdkStructure {
    public int dwSize;
    public int nTotalCount;

    public NET_OUT_FINDNUMBERSTAT() {
        dwSize = size();
    }
}
