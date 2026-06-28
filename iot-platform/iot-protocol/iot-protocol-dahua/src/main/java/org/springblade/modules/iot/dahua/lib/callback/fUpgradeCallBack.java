package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 升级回调
 */
public interface fUpgradeCallBack extends Callback {
    void invoke(int nTotalSize, int nRecvSize, Pointer dwUser);
}
