package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_GetWaterRadarObjectInfo 输入参数
*/
public class NET_IN_GET_WATERRADAR_OBJECTINFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_WATERRADAR_OBJECTINFO() {
        this.dwSize = this.size();
    }
}

