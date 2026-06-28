package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 区域顶点信息
*/
public class NET_CFG_POLYGON extends SdkStructure
{
    /**
     * X坐标
    */
    public int              nX;
    /**
     * Y坐标
    */
    public int              nY;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[32];

    public NET_CFG_POLYGON() {
    }
}

