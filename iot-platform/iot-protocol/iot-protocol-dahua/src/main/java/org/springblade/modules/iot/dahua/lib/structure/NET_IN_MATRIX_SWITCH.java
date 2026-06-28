package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 矩阵切换入参
 */
public class NET_IN_MATRIX_SWITCH extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nVideoInput;              // 视频输入源
    public int nVideoOutput;             // 视频输出
    public byte[] byReserved = new byte[256];  // 保留字段
}
