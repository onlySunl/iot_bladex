package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 分割能力集
 */
public class NET_SPLIT_CAPS extends SdkStructure {
    public int bSupportPreView;          // 是否支持预览分割
    public int bSupportPlayBack;         // 是否支持回放分割
    public int bSupportTour;             // 是否支持轮巡
    public int nMaxSplitMode;            // 最大分割模式数
    public int nMaxWindowCount;          // 最大窗口数
    public byte[] byReserved = new byte[256];  // 保留字段
}
