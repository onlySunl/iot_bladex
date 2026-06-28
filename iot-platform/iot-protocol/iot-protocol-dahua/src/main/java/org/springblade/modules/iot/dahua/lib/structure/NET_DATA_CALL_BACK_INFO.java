package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.LLong;

/**
 * 数据回调信息
 */
public class NET_DATA_CALL_BACK_INFO extends SdkStructure {
    public int nDataType;               // 数据类型
    public int nDataLen;                // 数据长度
    public LLong pData;                 // 数据指针
    public int nUser;                  // 用户数据
    public byte[] byReserved = new byte[256];  // 保留字段
}
