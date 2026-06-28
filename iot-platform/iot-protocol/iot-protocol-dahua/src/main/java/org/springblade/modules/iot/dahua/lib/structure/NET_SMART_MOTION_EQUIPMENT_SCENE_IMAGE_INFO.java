package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 场景大图
*/
public class NET_SMART_MOTION_EQUIPMENT_SCENE_IMAGE_INFO extends SdkStructure
{
    /**
     * 场景大图索引, 不存在时为~0u
    */
    public int              nIndexInData;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[252];

    public NET_SMART_MOTION_EQUIPMENT_SCENE_IMAGE_INFO() {
    }
}

