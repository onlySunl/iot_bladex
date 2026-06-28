package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 下载进度回调
 */
public interface fDownLoadPosCallBack extends Callback {
    void invoke(LLong lPlayBackHandle, int nTotalSize, int nDownLoadSize, int dwUser);
}
