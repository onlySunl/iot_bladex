package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * CLIENT_GetIVSEventDetail接口入参
*/
public class NET_IN_GET_IVSEVENT_DETAIL_INFO extends SdkStructure
{
    /**
     * 此结构体大小
    */
    public int              dwSize;
    /**
     * 待查询事件ID数量
    */
    public int              nIdNum;
    /**
     * 待查询事件ID列表
    */
    public int[]            nId = new int[128];
    /**
     * 回调函数,参见回调函数定义 {@link FNotifyIVSEventDetail}
    */
    public FNotifyIVSEventDetail cbNotifyIVSEventDetail;
    /**
     * 用户自定义参数
    */
    public Pointer          dwUser;

    public NET_IN_GET_IVSEVENT_DETAIL_INFO() {
        this.dwSize = this.size();
    }
}

