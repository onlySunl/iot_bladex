package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 引导屏幕信息
 */
public class NET_GUIDESCREEN_INFO extends SdkStructure {
    public int dwSize;
    public int bEnable;
    public byte[] szText = new byte[128];
    public int emDispMode;

    public NET_GUIDESCREEN_INFO() {
        dwSize = size();
    }
}
