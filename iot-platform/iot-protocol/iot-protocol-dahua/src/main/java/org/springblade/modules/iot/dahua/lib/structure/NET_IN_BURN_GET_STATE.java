package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 获取刻录状态入参
 */
public class NET_IN_BURN_GET_STATE extends SdkStructure {
    public byte[] byReserved = new byte[256];  // 保留字段
}
