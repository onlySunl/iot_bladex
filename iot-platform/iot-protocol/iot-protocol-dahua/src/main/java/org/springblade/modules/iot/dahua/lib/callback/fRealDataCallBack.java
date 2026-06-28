package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 实时数据回调
 */
public interface fRealDataCallBack extends Callback {
    void invoke(LLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, Pointer dwUser);
}
