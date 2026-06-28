package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 开始查询人脸信息入参
 */
public class NET_IN_FACEINFO_DO_FIND extends SdkStructure {
    public int dwSize;
    public int nChannelID;
    public NET_TIME stuStartTime;
    public NET_TIME stuEndTime;
    public int nFindType;
    public int nOffSet;
    public int nMaxCount;

    public NET_IN_FACEINFO_DO_FIND() {
        dwSize = size();
        stuStartTime = new NET_TIME();
        stuEndTime = new NET_TIME();
        nMaxCount = 16;
    }
}
