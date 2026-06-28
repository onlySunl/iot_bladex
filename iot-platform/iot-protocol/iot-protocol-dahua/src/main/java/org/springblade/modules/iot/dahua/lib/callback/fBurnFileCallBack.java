package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 刻录文件回调
 */
public interface fBurnFileCallBack extends Callback {
    void invoke(LLong lLoginID, String pchFile, int nState, Pointer dwUser);
}
