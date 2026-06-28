package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 按数据类型回放入参
 */
public class NET_IN_PLAYBACK_BY_DATA_TYPE extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nStreamType;              // 码流类型
    public LLong lPlayHandle;            // 回放句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
