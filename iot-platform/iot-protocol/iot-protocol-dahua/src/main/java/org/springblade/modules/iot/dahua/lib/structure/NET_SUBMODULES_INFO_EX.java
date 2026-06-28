package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 子模块扩展信息
*/
public class NET_SUBMODULES_INFO_EX extends SdkStructure
{
    /**
     * 芯片的cos固件信息， Base64解码之后的
    */
    public byte[]           szGMChipUpdateInfo = new byte[512];
    /**
     * 芯片的cos固件信息长度
    */
    public int              nGMChipUpdateInfoLen;
    /**
     * 预留字节
    */
    public byte[]           byReserve = new byte[1020];

    public NET_SUBMODULES_INFO_EX() {
    }
}

