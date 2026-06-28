package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 继续查询人脸信息出参
 */
public class NET_OUT_FACEINFO_DO_FIND extends SdkStructure {
    public int dwSize;
    public int nRetCount;
    public NET_FACEINFO[] stuFaceInfo;

    public NET_OUT_FACEINFO_DO_FIND() {
        dwSize = size();
        nRetCount = 16;
        stuFaceInfo = new NET_FACEINFO[16];
        for (int i = 0; i < 16; i++) {
            stuFaceInfo[i] = new NET_FACEINFO();
        }
    }
}
