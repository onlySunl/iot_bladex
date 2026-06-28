package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * className：NET_IN_ATTACH_TRAFFICLIGHT_INFO
 * description：CLIENT_AttachTrafficLightState 接口入参
 * author：251589
 * createTime：2021/1/19 14:30
 *
 * @version v1.0
 */
public class NET_IN_ATTACH_TRAFFICLIGHT_INFO extends SdkStructure {
    /**
     *  结构体大小
     */
    public int              dwSize;
    /**
     *  回调函数
     */
    public FTrafficLightState cbState;
    /**
     *  用户信息
     */
    public long             dwUser;

    public NET_IN_ATTACH_TRAFFICLIGHT_INFO(){
        this.dwSize = this.size();
    }
}

