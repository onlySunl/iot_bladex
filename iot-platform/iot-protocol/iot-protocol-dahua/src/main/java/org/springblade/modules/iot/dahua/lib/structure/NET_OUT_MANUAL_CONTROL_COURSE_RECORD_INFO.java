package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_ManualControlCourseRecord 接口输出参数
*/
public class NET_OUT_MANUAL_CONTROL_COURSE_RECORD_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 错误码枚举,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_MANUAL_CONTROL_ERRORCODE_TYPE}
    */
    public int              emErrorCode;

    public NET_OUT_MANUAL_CONTROL_COURSE_RECORD_INFO() {
        this.dwSize = this.size();
    }
}

