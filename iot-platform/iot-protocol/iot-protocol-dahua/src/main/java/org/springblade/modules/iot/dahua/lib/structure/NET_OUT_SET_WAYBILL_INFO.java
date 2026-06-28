package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_SetWaybillInfo 接口输出参数
*/
public class NET_OUT_SET_WAYBILL_INFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_OUT_SET_WAYBILL_INFO() {
        this.dwSize = this.size();
    }
}

