package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 按数据类型实时播放入参
 */
public class NET_IN_REALPLAY_BY_DATA_TYPE extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nStreamType;              // 码流类型
    public LLong lRealHandle;            // 播放句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
