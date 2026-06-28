package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 人脸信息
 */
public class NET_FACEINFO extends SdkStructure {
    public int dwSize;
    public int nFaceID;
    public byte[] szPersonName = new byte[64];
    public byte[] szID = new byte[32];
    public NET_TIME stuTime;
    public int nChannel;

    public NET_FACEINFO() {
        dwSize = size();
        stuTime = new NET_TIME();
    }
}
