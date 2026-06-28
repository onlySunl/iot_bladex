package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_StartFindObjectMediaFind 接口输出参数
*/
public class NET_OUT_START_FIND_OBJECT_MEDIA_FIND extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_START_FIND_OBJECT_MEDIA_FIND() {
        this.dwSize = this.size();
    }
}

