package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import org.springblade.modules.iot.dahua.lib.structure.*;
import com.sun.jna.Pointer;

/**
 * 添加设备回调
 */
public interface fAddDeviceCallBack extends Callback {
    void invoke(LLong lLoginID, Pointer pBuf, int dwBufLen, Pointer dwUser);
}
