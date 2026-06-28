package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 联动业务大类选项
*/
public class NET_LINK_CLASS_TYPE extends SdkStructure
{
    /**
     * 待级联的业务所在通道号
    */
    public int              nChannel;
    /**
     * 待级联的业务大类,参见枚举定义 {@link EM_SCENE_CLASS_TYPE}
    */
    public int              emClassType;
    /**
     * 预留字段
    */
    public byte[]           szResvered = new byte[252];

    public NET_LINK_CLASS_TYPE() {
    }
}

