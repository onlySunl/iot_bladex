package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 按时间回放入参
 */
public class NET_IN_PLAY_BACK_BY_TIME_INFO extends SdkStructure {
    public int dwSize;
    public int nChannelID;
    public NET_TIME stuStartTime;
    public NET_TIME stuStopTime;
    public int emPlayBackMode;
    public int nPlayDirection;
    public int emSpeedRate;
    public int emStreamType;

    public NET_IN_PLAY_BACK_BY_TIME_INFO() {
        dwSize = size();
        stuStartTime = new NET_TIME();
        stuStopTime = new NET_TIME();
    }
}
