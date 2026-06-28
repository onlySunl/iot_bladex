package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 矩阵设置摄像头入参
 */
public class NET_IN_MATRIX_SET_CAMERAS extends SdkStructure {
    public int dwSize;
    public int nChannel;
    public int nCameraCount;
    public NET_MATRIX_CAMERA_INFO[] stCameras; // NET_MATRIX_CAMERA_INFO[]

    public NET_IN_MATRIX_SET_CAMERAS() {
        dwSize = size();
        nCameraCount = 16;
        stCameras = new NET_MATRIX_CAMERA_INFO[16];
        for (int i = 0; i < 16; i++) {
            stCameras[i] = new NET_MATRIX_CAMERA_INFO();
        }
    }
}
