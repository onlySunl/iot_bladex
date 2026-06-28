package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 视频分析状态
 */
public class NET_VIDEOANALYSE_STATE extends SdkStructure {
    public int nChannel;                // 通道号
    public int nState;                 // 状态
    public int nRuleCount;             // 规则数量
    public byte[] byReserved = new byte[256];  // 保留字段
}
