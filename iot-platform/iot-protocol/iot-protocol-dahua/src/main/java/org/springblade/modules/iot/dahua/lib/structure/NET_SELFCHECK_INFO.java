package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 自检信息
 */
public class NET_SELFCHECK_INFO extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nSelfCheckResult;         // 自检结果
    public byte[] byReserved = new byte[256];  // 保留字段
}
