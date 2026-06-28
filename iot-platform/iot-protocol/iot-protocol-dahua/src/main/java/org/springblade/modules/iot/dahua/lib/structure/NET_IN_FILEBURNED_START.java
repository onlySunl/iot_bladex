package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 文件刻录开始入参
 */
public class NET_IN_FILEBURNED_START extends SdkStructure {
    public int nChannel;                 // 通道号
    public byte[] szFileName;            // 文件名
    public byte[] byReserved = new byte[256];  // 保留字段
}
