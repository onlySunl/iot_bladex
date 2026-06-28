package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 停车场控制开始查询入参
 */
public class NET_IN_PARKING_CONTROL_START_FIND_PARAM extends SdkStructure {
    public int dwSize;
    public byte[] szDeviceID = new byte[32];
    public int nChannel;
    public int nParkingIndex;
    public int nRecordType;
    public int bTimeEnable;
    public NET_TIME stuStartTime;
    public NET_TIME stuEndTime;
    
    public NET_IN_PARKING_CONTROL_START_FIND_PARAM() {
        this.dwSize = this.size();
    }
}
