package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 雷达RFID卡信息回调
 */
public interface fRadarRFIDCardInfoCallBack extends Callback {
    void invoke(LLong lLoginID, Pointer pBuf, int dwBufLen, Pointer dwUser);
}
