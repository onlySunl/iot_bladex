package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 订阅日志回调入参
*/
public class NET_IN_ATTACH_DBGINFO extends SdkStructure
{
    public int              dwSize;
    /**
     * 日志等级,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_DBGINFO_LEVEL}
    */
    public int              emLevel;
    /**
     * 回调,参见回调函数定义 {@link FDebugInfoCallBack}
    */
    public FDebugInfoCallBack pCallBack;
    /**
     * 用户数据
    */
    public Pointer          dwUser;

    public NET_IN_ATTACH_DBGINFO() {
        this.dwSize = this.size();
    }
}

