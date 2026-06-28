package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 按数据类型回放出参
 */
public class NET_OUT_PLAYBACK_BY_DATA_TYPE extends SdkStructure {
    public LLong lPlayHandle;            // 回放句柄
    public byte[] byReserved = new byte[256];  // 保留字段
}
