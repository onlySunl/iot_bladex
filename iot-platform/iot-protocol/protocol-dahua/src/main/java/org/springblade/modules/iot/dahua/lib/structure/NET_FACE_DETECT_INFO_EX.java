package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 目标检测事件扩展信息
*/
public class NET_FACE_DETECT_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 文件路径
    */
    public byte[]           szFilePath = new byte[260];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_FACE_DETECT_INFO_EX() {
    }
}

