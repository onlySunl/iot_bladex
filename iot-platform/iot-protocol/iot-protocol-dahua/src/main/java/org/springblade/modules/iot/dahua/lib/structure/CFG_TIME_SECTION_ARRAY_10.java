package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

public class CFG_TIME_SECTION_ARRAY_10 extends SdkStructure {
    /**
     * 参见结构体定义 {@link CFG_TIME_SECTION}
    */
    public CFG_TIME_SECTION[] obj_10 = new CFG_TIME_SECTION[10];

    public CFG_TIME_SECTION_ARRAY_10() {
        for(int i = 0; i < obj_10.length; i++){
            obj_10[i] = new CFG_TIME_SECTION();
        }
    }
}

