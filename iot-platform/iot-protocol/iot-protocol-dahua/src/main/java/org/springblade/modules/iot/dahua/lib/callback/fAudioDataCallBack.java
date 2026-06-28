package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 音频数据回调
 */
public interface fAudioDataCallBack extends Callback {
    void invoke(LLong lTalkHandle, Pointer pBuf, int dwBufLen, byte byAudioFlag, Pointer dwUser);
}
