package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 获取自检信息入参
 */
public class NET_IN_GET_SELTCHECK_INFO extends SdkStructure {
    public byte[] byReserved = new byte[256];  // 保留字段
}
