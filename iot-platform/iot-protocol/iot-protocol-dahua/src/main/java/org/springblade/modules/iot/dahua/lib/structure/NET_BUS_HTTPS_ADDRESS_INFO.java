package org.springblade.modules.iot.dahua.lib.structure;
/**
 * 服务器地址参数
*/
public class NET_BUS_HTTPS_ADDRESS_INFO extends SdkStructure
{
    public byte[]           szIPAddress = new byte[64];
    public int              nPort;
    public byte[]           byReserved = new byte[956];

    public NET_BUS_HTTPS_ADDRESS_INFO() {
    }
}

