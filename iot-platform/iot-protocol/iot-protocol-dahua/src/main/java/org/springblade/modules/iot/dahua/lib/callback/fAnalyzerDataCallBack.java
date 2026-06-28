package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import com.sun.jna.Pointer;

/**
 * 分析数据回调
 */
public interface fAnalyzerDataCallBack extends Callback {
    void invoke(LLong lAnalyzerHandle, int dwAlarmType, Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize, Pointer dwUser, int nSequence);
}
