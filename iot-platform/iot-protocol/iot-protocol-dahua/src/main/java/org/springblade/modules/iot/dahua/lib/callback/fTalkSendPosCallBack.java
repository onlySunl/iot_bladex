package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 对讲发送位置回调
 */
public interface fTalkSendPosCallBack extends Callback {
    void invoke(LLong lTalkHandle, int nPosType, Pointer pBuf, int dwBufLen, Pointer dwUser);
}
