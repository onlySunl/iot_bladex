package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 人脸信息查询开始入参
 */
public class NET_IN_FACEINFO_START_FIND extends SdkStructure {
    public int nChannel;                 // 通道号
    public int nStartTime;               // 开始时间
    public int nEndTime;                 // 结束时间
    public byte[] byReserved = new byte[256];  // 保留字段
}
