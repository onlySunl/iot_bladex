package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 数据回调
 */
public interface fDataCallBack extends Callback {
    void invoke(LLong lPlayHandle, Pointer pBuf, int dwBufLen, int dwUser);
}
