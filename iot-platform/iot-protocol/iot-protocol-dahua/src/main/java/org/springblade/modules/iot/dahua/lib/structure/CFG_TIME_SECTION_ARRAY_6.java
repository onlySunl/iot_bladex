package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

public class CFG_TIME_SECTION_ARRAY_6 extends SdkStructure {
    /**
     * 参见结构体定义 {@link CFG_TIME_SECTION}
    */
    public CFG_TIME_SECTION[] obj_6 = new CFG_TIME_SECTION[6];

    public CFG_TIME_SECTION_ARRAY_6() {
        for(int i = 0; i < obj_6.length; i++){
            obj_6[i] = new CFG_TIME_SECTION();
        }
    }
}

