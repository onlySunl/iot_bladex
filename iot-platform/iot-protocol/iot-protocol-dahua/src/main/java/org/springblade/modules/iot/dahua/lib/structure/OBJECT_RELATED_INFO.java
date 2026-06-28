package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;


/**
 * @author 291189
 * @version 1.0
 * @description  目标关联信息
 * @date 2021/8/24 15:09
 */
public class OBJECT_RELATED_INFO extends SdkStructure {
    public int              nObjectID;                            // 物体ID
    public int              nRelativeID;                          // 相关物体ID
    public byte[]           szObjectType = new byte[128];         // 物体类型,如"HumanFace"
    public NET_RECT stuBoundingBox = new NET_RECT(); // 物体包围盒，坐标[0, 8191]
    public NET_RECT stuOriginalBoundingBox = new NET_RECT(); // 原始物体包围盒，坐标[0, 8191]
    public byte[]           byReserved = new byte[1024];          // 预留字段

    @Override
    public String toString() {
        return "OBJECT_RELATED_INFO{" +
                "nObjectID=" + nObjectID +
                ", nRelativeID=" + nRelativeID +
                ", szObjectType=" + new String(szObjectType) +
                ", stuBoundingBox=" + stuBoundingBox +
                ", stuOriginalBoundingBox=" + stuOriginalBoundingBox +
                '}';
    }
}

