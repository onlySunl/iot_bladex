package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 串口透明通道回调
 */
public interface fTransComCallBack extends Callback {
    void invoke(LLong lLoginID, int lTransComChannel, Pointer pBuf, int dwBufLen, Pointer dwUser);
}
