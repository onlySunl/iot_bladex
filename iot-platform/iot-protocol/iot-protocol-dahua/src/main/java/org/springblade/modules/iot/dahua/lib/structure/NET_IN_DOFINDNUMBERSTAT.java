package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 继续查询数量统计入参
 */
public class NET_IN_DOFINDNUMBERSTAT extends SdkStructure {
    public int dwSize;
    public int nStartNumber;
    public int nCount;

    public NET_IN_DOFINDNUMBERSTAT() {
        dwSize = size();
    }
}
