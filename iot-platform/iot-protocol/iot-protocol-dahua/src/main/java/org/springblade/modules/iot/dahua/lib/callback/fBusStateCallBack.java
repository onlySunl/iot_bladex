package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 总线状态回调
 */
public interface fBusStateCallBack extends Callback {
    void invoke(LLong lBusHandle, Pointer pBuf, int dwBufLen, Pointer dwUser);
}
