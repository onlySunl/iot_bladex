package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.*;

/**
 * 设置引导屏幕配置入参
 */
public class NET_IN_SET_GUIDESCREEN_CFG extends SdkStructure {
    public int dwSize;
    public int nChannel;
    public NET_GUIDESCREEN_INFO stGuideScreenInfo;

    public NET_IN_SET_GUIDESCREEN_CFG() {
        dwSize = size();
        stGuideScreenInfo = new NET_GUIDESCREEN_INFO();
    }
}
