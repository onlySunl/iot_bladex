package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 矩阵获取摄像头入参
 */
public class NET_IN_MATRIX_GET_CAMERAS extends SdkStructure {
    public int dwSize;
    public int nChannel;

    public NET_IN_MATRIX_GET_CAMERAS() {
        dwSize = size();
    }
}
