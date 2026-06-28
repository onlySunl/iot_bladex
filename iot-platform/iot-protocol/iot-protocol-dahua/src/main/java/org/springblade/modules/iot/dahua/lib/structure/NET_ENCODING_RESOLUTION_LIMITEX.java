package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 主辅码流分辨率组合限制
*/
public class NET_ENCODING_RESOLUTION_LIMITEX extends SdkStructure
{
    /**
     * 分辨率1,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_CAPTURE_SIZE_EX}
    */
    public int              emResolution1;
    /**
     * 分辨率2,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_CAPTURE_SIZE_EX}
    */
    public int              emResolution2;
    /**
     * 保留
    */
    public byte[]           szReserved = new byte[1024];

    public NET_ENCODING_RESOLUTION_LIMITEX() {
    }
}

