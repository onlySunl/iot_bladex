package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import org.springblade.modules.iot.dahua.lib.structure.*;
import com.sun.jna.Pointer;

/**
 * 停车场信息回调
 */
public interface fParkInfoCallBack extends Callback {
    void invoke(LLong lLoginID, NET_PARK_INFO_ITEM pParkInfo, int dwBufLen, Pointer dwUser);
}
