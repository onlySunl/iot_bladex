package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 消息回调
 */
public interface fMessCallBack extends Callback {
    void invoke(LLong lLoginID, int dwType, Pointer pBuf, int dwBufLen, Pointer dwUser);
}
