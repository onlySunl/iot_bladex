package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 服务回调
 */
public interface fServiceCallBack extends Callback {
    void invoke(LLong lHandle, String pIp, int nPort, int nType, Pointer pData, int dwUser);
}
