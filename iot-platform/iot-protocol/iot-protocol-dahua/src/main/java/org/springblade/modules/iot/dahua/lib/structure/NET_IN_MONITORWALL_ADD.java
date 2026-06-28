package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 添加电视墙输入参数
*/
public class NET_IN_MONITORWALL_ADD extends SdkStructure
{
    public int              dwSize;
    /**
     * 电视墙信息,参见结构体定义 {@link NET_MONITORWALL}
    */
    public NET_MONITORWALL stuMonitorWall = new NET_MONITORWALL();

    public NET_IN_MONITORWALL_ADD() {
        this.dwSize = this.size();
    }
}

