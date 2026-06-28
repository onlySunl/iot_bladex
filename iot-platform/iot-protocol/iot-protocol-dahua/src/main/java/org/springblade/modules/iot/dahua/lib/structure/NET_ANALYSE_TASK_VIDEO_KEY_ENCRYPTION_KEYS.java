package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 分析文件的加密密钥
*/
public class NET_ANALYSE_TASK_VIDEO_KEY_ENCRYPTION_KEYS extends SdkStructure
{
    /**
     * 加密密钥ID
    */
    public byte[]           szId = new byte[64];
    /**
     * 加密密钥，256-bit随机码
    */
    public byte[]           szKey = new byte[256];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[256];

    public NET_ANALYSE_TASK_VIDEO_KEY_ENCRYPTION_KEYS() {
    }
}

