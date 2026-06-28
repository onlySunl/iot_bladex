package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 设备状态回调
 */
public interface fDeviceStateCallBack extends Callback {
    void invoke(LLong lLoginID, int dwType, Pointer pBuf, int dwBufLen, Pointer dwUser);
}
