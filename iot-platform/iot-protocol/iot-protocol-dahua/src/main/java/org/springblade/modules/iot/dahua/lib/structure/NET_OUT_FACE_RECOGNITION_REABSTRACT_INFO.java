package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_FaceRecognitionReAbstract 接口输出参数
*/
public class NET_OUT_FACE_RECOGNITION_REABSTRACT_INFO extends SdkStructure
{
    /**
     * 此结构体大小
    */
    public int              dwSize;

    public NET_OUT_FACE_RECOGNITION_REABSTRACT_INFO() {
        this.dwSize = this.size();
    }
}

