package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 刻录状态回调信息
 */
public class NET_CB_BURNSTATE extends SdkStructure {
    public int nState;                  // 状态
    public int nProgress;               // 进度
    public int nBurnedVolume;           // 已刻录容量
    public byte[] byReserved = new byte[256];  // 保留字段
}
