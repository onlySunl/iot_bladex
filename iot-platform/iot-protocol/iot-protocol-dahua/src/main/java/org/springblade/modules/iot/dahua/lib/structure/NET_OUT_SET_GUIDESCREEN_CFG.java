package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 设置引导屏幕配置出参
 */
public class NET_OUT_SET_GUIDESCREEN_CFG extends SdkStructure {
    public int dwSize;

    public NET_OUT_SET_GUIDESCREEN_CFG() {
        dwSize = size();
    }
}
