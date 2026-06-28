package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_AttachIotboxComm 接口入参
*/
public class NET_IN_ATTACH_IOTBOX_COMM extends SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 回调函数,参见回调函数定义 {@link FNotifyIotboxRealdata}
    */
    public FNotifyIotboxRealdata cbNotifyIotboxRealdata;
    /**
     * 用户自定义参数
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_IOTBOX_COMM() {
        this.dwSize = this.size();
    }
}

