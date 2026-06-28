package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 文件刻录开始出参
 */
public class NET_OUT_FILEBURNED_START extends SdkStructure {
    public LLong hBurnHandle;            // 刻录句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
