package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 矩阵设置摄像头出参
 */
public class NET_OUT_MATRIX_SET_CAMERAS extends SdkStructure {
    public int dwSize;

    public NET_OUT_MATRIX_SET_CAMERAS() {
        dwSize = size();
    }
}
