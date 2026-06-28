package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 文件流标签信息出参
 */
public class NET_OUT_FILE_STREAM_TAGS_INFO extends SdkStructure {
    public int[] nTags;        // 标签数组
    public int nTagsLen;       // 标签数组长度
    public int nTagsCount;     // 实际标签数量
    public byte[] byReserved = new byte[256];  // 保留字段
}
