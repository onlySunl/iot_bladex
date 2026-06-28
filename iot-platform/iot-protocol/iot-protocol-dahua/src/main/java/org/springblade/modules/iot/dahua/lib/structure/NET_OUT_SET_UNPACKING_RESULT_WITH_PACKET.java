package org.springblade.modules.iot.dahua.lib.structure;
/**
 * CLIENT_SetUnpackingResultWithPacket 接口输出参数
*/
public class NET_OUT_SET_UNPACKING_RESULT_WITH_PACKET extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_SET_UNPACKING_RESULT_WITH_PACKET() {
        this.dwSize = this.size();
    }
}

