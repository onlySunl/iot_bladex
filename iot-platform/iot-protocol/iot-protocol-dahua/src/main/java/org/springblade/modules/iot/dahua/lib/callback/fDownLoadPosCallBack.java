package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;

/**
 * 下载进度回调
 */
public interface fDownLoadPosCallBack extends Callback {
    /**
     * @param lPlayBackHandle  playback handle
     * @param nTotalSize      total size
     * @param nDownLoadSize    downloaded size
     * @param dwUser           user data
     */
    void invoke(LLong lPlayBackHandle, int nTotalSize, int nDownLoadSize, int dwUser);
}
