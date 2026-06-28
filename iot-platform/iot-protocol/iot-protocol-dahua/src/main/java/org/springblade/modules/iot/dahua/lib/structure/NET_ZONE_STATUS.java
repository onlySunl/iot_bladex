package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 防区异常信息
*/
public class NET_ZONE_STATUS extends SdkStructure
{
    /**
     * 防区号
    */
    public int              nIndex;
    /**
     * 防区异常状态,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_ZONE_STATUS}
    */
    public int              emStatus;
    /**
     * 保留字节
    */
    public byte[]           byReserved = new byte[1024];

    public NET_ZONE_STATUS() {
    }
}

