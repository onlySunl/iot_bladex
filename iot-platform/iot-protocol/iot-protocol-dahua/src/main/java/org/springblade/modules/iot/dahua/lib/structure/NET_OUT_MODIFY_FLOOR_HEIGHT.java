package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_ModifyFloorHeight 接口输出参数
*/
public class NET_OUT_MODIFY_FLOOR_HEIGHT extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;

    public NET_OUT_MODIFY_FLOOR_HEIGHT() {
        this.dwSize = this.size();
    }
}

