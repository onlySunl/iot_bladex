package org.springblade.modules.iot.dahua.lib.structure;


import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.enumeration.EM_COMPLIANCE_STATE;
import org.springblade.modules.iot.dahua.lib.enumeration.EM_WEARING_STATE;

/**
 * @author ： 260611
 * @description ： 无帽子相关属性状态信息
 * @since ： Created in 2022/03/10 11:17
 */

public class NET_NOHAT_ATTRIBUTE extends SdkStructure {
    /**
     * 是否有戴帽子,{@link EM_WEARING_STATE}
     */
    public int              emHasHat;
    /**
     * 帽子检测结果,{@link EM_COMPLIANCE_STATE}
     */
    public int              emHasLegalHat;
}

