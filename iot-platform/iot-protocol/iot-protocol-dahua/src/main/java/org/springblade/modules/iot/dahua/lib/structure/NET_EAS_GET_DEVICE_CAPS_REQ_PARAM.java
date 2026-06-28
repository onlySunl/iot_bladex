package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 获取EAS设备能力集查询参数
*/
public class NET_EAS_GET_DEVICE_CAPS_REQ_PARAM extends SdkStructure
{
    /**
     * EAS通道号，-1表示所有通道
    */
    public int              nDeviceChannel;
    /**
     * 保留字节
    */
    public byte[]           szResvered = new byte[128];

    public NET_EAS_GET_DEVICE_CAPS_REQ_PARAM() {
    }
}

