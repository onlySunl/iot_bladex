package org.springblade.modules.iot.dahua.lib.structure;


import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.enumeration.EM_COMPLIANCE_STATE;
import org.springblade.modules.iot.dahua.lib.enumeration.EM_WEARING_STATE;

/**
 * @author ： 260611
 * @description ： 围裙相关属性状态信息
 * @since ： Created in 2022/03/10 11:17
 */

public class NET_APRON_ATTRIBUTE extends SdkStructure {
    /**
     * 是否有穿围裙,{@link EM_WEARING_STATE}
     */
    public int              emHasApron;
    /**
     * 围裙检测结果,{@link EM_COMPLIANCE_STATE}
     */
    public int              emHasLegalApron;
}

