package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 设备类型信息
*/
public class CFG_DEVICE_CLASS_INFO extends SdkStructure
{
    public int              dwSize;
    /**
     * 设备类型,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.NET_EM_DEVICE_TYPE}
    */
    public int              emDeviceType;

    public CFG_DEVICE_CLASS_INFO() {
        this.dwSize = this.size();
    }
}

