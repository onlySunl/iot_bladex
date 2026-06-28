package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 订阅气象信息输出参数
*/
public class NET_OUT_WEATHER_INFO extends SdkStructure
{
    public int              dwSize;

    public NET_OUT_WEATHER_INFO() {
        this.dwSize = this.size();
    }
}

