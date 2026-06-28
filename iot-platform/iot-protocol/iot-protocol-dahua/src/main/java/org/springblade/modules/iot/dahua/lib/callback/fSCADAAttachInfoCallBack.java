package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import org.springblade.modules.iot.dahua.lib.structure.*;
import com.sun.jna.Pointer;

/**
 * SCADA订阅回调
 */
public interface fSCADAAttachInfoCallBack extends Callback {
    void invoke(LLong lAttachHandle, NET_SCADA_NOTIFY_POINT_INFO_LIST pInfo, int dwBufLen, Pointer dwUser);
}
