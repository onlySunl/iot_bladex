package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 刻录状态信息
 */
public class NET_OUT_BURN_GET_STATE extends SdkStructure {
    public int nState;                   // 刻录状态
    public int nProgress;                // 刻录进度
    public byte[] byReserved = new byte[256];  // 保留字段
}
