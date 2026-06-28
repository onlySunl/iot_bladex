package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 停车场控制记录
 */
public class NET_PARKING_CONTROL_RECORD extends SdkStructure {
    public int dwSize;
    public byte[] szPlateNumber = new byte[32];
    public byte[] szDeviceID = new byte[32];
    public int nChannel;
    public int nEventType;
    }
