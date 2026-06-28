package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 停车场信息项
 */
public class NET_PARK_INFO_ITEM extends SdkStructure {
    public int nParkNo;                 // 车位编号
    public int nStatus;                // 状态
    public int nParkType;              // 类型
    public byte[] byReserved = new byte[256];  // 保留字段
}
