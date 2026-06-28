package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 按时下载进度回调
 */
public interface fTimeDownLoadPosCallBack extends Callback {
    void invoke(LLong lPlayBackHandle, int nTotalSize, int nDownLoadSize, int dwUser);
}
