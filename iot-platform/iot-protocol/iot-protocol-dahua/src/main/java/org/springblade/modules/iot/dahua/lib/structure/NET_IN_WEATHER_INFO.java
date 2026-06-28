package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.Pointer;
import org.springblade.modules.iot.dahua.lib.callback.FWeatherInfoCallBack;

public class NET_IN_WEATHER_INFO extends SdkStructure {
/**
 * 订阅气象信息输入参数
*/
public class NET_IN_WEATHER_INFO extends SdkStructure
{
    public int              dwSize;
    /**
     * 云台通道
    */
    public int              nChannel;
    /**
     * 回调函数,参见回调函数定义 {@link FWeatherInfoCallBack}
    */
    public FWeatherInfoCallBack cbWeatherInfo;
    /**
     * 用户数据
    */
    public Pointer          dwUser;

    public NET_IN_WEATHER_INFO() {
        this.dwSize = this.size();
    }
}

