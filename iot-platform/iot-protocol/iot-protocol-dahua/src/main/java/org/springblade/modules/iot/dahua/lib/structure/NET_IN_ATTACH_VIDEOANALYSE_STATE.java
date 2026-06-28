package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 视频分析状态订阅入参
 */
public class NET_IN_ATTACH_VIDEOANALYSE_STATE extends SdkStructure {
    public int nChannel;                 // 通道号
    public byte[] byReserved = new byte[256];  // 保留字段
}
