package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 重定向服务器回调
 */
public interface fRedirectServerCallBack extends Callback {
    void invoke(LLong lLoginID, String pchIP, int nPort, Pointer dwUser);
}
