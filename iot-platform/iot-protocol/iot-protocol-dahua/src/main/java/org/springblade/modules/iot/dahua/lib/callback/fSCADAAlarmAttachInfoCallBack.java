package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import org.springblade.modules.iot.dahua.lib.structure.*;
import com.sun.jna.Pointer;

/**
 * SCADA报警订阅回调
 */
public interface fSCADAAlarmAttachInfoCallBack extends Callback {
    void invoke(LLong lAttachHandle, NET_SCADA_NOTIFY_POINT_ALARM_INFO_LIST pAlarmInfo, int dwBufLen, Pointer dwUser);
}
