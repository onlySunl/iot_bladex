package org.springblade.modules.iot.dahua.lib.callback;

import com.sun.jna.Callback;
import org.springblade.modules.iot.dahua.lib.method.LLong;
import org.springblade.modules.iot.dahua.lib.structure.*;
import com.sun.jna.Pointer;

/**
 * 停车场控制记录回调
 */
public interface fParkingControlRecordCallBack extends Callback {
    void invoke(LLong lLoginID, NET_PARKING_CONTROL_RECORD pRecordInfo, int dwBufLen, Pointer dwUser);
}
