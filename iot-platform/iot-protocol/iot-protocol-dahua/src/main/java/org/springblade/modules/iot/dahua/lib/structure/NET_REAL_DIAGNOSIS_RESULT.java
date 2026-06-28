package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 实时诊断结果
 */
public class NET_REAL_DIAGNOSIS_RESULT extends SdkStructure {
    public int nChannel;                // 通道号
    public int nSignalState;           // 信号状态
    public int nVideoLoss;             // 视频丢失
    public int nVideoBlind;            // 视频遮挡
    public byte[] byReserved = new byte[256];  // 保留字段
}
