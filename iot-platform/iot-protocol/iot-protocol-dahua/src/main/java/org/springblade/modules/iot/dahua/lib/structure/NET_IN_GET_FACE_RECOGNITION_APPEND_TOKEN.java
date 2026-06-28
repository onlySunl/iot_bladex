package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_GetFaceRecognitionAppendToken 接口输入参数
*/
public class NET_IN_GET_FACE_RECOGNITION_APPEND_TOKEN extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_FACE_RECOGNITION_APPEND_TOKEN() {
        this.dwSize = this.size();
    }
}

