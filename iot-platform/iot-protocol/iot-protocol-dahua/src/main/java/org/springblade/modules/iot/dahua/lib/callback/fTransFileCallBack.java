package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 传输文件回调
 */
public interface fTransFileCallBack extends Callback {
    void invoke(LLong lHandle, int nTransType, int nState, int nPercent, Pointer dwUser);
}
