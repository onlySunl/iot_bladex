package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 矩阵摄像头信息
 */
public class NET_MATRIX_CAMERA_INFO extends SdkStructure {
    public int dwSize;
    public int nCameraID;
    public byte[] szCameraName = new byte[64];
    public int nChannelID;

    public NET_MATRIX_CAMERA_INFO() {
        dwSize = size();
    }
}
