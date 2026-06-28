package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 文件流标签信息入参
 */
public class NET_IN_FILE_STREAM_TAGS_INFO extends SdkStructure {
    public int nChannelID;     // 通道号
    public int nStreamType;    // 码流类型
    public byte[] byReserved = new byte[256];  // 保留字段
}
