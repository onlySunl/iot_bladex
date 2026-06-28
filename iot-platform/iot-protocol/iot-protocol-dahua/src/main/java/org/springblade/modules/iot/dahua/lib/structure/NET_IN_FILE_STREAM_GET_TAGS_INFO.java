package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 获取文件流标签信息入参
 */
public class NET_IN_FILE_STREAM_GET_TAGS_INFO extends SdkStructure {
    public int dwSize;
    public byte[] szFileId = new byte[128];
    public int nTagsSize;
    
    public NET_IN_FILE_STREAM_GET_TAGS_INFO() {
        this.dwSize = this.size();
    }
}
