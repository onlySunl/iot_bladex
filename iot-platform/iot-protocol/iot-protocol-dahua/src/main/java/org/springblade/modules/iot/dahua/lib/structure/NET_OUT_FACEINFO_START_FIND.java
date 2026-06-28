package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 人脸信息查询开始出参
 */
public class NET_OUT_FACEINFO_START_FIND extends SdkStructure {
    public int nTotalCount;              // 总数量
    public byte[] byReserved = new byte[256];  // 保留字段
}
