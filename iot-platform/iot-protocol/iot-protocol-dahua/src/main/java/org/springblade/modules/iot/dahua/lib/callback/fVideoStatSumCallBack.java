package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import org.springblade.modules.iot.dahua.lib.structure.NET_VIDEOSTAT_SUM;
import com.sun.jna.Pointer;

/**
 * 视频统计摘要回调
 */
public interface fVideoStatSumCallBack extends Callback {
    void invoke(LLong lLoginID, int nChannel, NET_VIDEOSTAT_SUM pstStatInfo, Pointer dwUser);
}
