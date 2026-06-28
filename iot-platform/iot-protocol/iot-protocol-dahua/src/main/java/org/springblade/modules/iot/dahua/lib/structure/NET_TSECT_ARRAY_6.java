package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.structure.NET_TSECT;

public class NET_TSECT_ARRAY_6 extends SdkStructure {
    /**
     * 参见结构体定义 {@link NET_TSECT}
    */
    public NET_TSECT[] obj_6 = new NET_TSECT[6];

    public NET_TSECT_ARRAY_6() {
        for(int i = 0; i < obj_6.length; i++){
            obj_6[i] = new NET_TSECT();
        }
    }
}

