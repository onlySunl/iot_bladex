package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import static org.springblade.modules.iot.dahua.lib.MAX_PREVIEW_CHANNEL_NUM;

/**
 * 获取真实预览通道号 出参 {@link NetSDKLib#CLIENT_GetRealPreviewChannel}
 *
 * @author ： 47040
 * @since ： Created in 2020/9/18 10:00
 */
public class NET_OUT_GET_REAL_PREVIEW_CHANNEL extends SdkStructure {
    /**
     * 结构体大小
     */
    public int              dwSize;
    /**
     * 通道数量
     */
    public int              nChannelNum;
    /**
     * 通道号
     */
    public int[]            nChannel = new int[MAX_PREVIEW_CHANNEL_NUM];

    public NET_OUT_GET_REAL_PREVIEW_CHANNEL() {
        dwSize = this.size();
    }
}

