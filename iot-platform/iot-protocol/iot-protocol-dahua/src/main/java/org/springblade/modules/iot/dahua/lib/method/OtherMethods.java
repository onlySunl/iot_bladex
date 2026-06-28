package org.springblade.modules.iot.dahua.lib.method;

import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 其他方法
 */
public interface OtherMethods extends NetSDKLib {

    // 码头状态订阅
    public LLong CLIENT_AttachDockStatus(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    public boolean CLIENT_DetachDockStatus(LLong lAttachHandle);

    // 码头事件回复
    public boolean CLIENT_DockEventsReply(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 码头属性设置
    public boolean CLIENT_DockPropertySet(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 码头状态回复
    public boolean CLIENT_DockStatusReply(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 航班任务准备
    public boolean CLIENT_FlightTaskPrepare(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 航班任务执行
    public boolean CLIENT_FlightTaskExecute(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 返回
    public boolean CLIENT_ReturnHome(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 运单信息
    public boolean CLIENT_SetWaybillInfo(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 开包检查
    public boolean CLIENT_SetUnpackingResultWithPacket(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // X光机
    public LLong CLIENT_XRay_AttachUnpackingResult(LLong lLoginID, Pointer pInParam, Pointer pOutParam, int nWaitTime);

    public boolean CLIENT_XRay_DetachUnpackingResult(LLong lAttachHandle);

    // 运单状态
    public boolean CLIENT_SetWaybillStatus(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);

    // 存储信息
    public boolean CLIENT_GetStorageInfoByFileType(LLong lLoginID, Pointer pstuInParam, Pointer pstuOutParam, int nWaitTime);
}
