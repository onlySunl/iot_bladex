package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import org.springblade.modules.iot.dahua.lib.structure.*;
import com.sun.jna.Pointer;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 视频统计热图回调
 */
public interface fVideoStatHeatMapCallBack extends Callback {
    void invoke(LLong lLoginID, int nChannel, NET_VIDEOSTAT_SUMMARY pstStatInfo, Pointer dwUser);
}
