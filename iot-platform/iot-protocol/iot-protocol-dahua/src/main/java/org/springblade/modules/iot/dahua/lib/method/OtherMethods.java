package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.*;
import com.sun.jna.ptr.*;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * NetSDK 方法定义 - OtherMethods.java
 */
public interface OtherMethods {

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_DOCK_STATUS}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_DOCK_STATUS}
     */
    public LLong CLIENT_AttachDockStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     */
    public boolean CLIENT_DetachDockStatus(LLong lAttachHandle);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DOCK_EVENTS_REPLY}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DOCK_EVENTS_REPLY}
     */
    public boolean CLIENT_DockEventsReply(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DOCK_PROPERTY_SET}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DOCK_PROPERTY_SET}
     */
    public boolean CLIENT_DockPropertySet(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DOCK_STATUS_REPLY}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DOCK_STATUS_REPLY}
     */
    public boolean CLIENT_DockStatusReply(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_FLIGHT_TASK_PREPARE}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_FLIGHT_TASK_PREPARE}
     */
    public boolean CLIENT_FlightTaskPrepare(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_FLIGHT_TASK_EXECUTE}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_FLIGHT_TASK_EXECUTE}
     */
    public boolean CLIENT_FlightTaskExecute(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_RETURN_HOME}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_RETURN_HOME}
     */
    public boolean CLIENT_ReturnHome(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置运单信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_WAYBILL_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_WAYBILL_INFO}
     */
    public boolean CLIENT_SetWaybillInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置开包检查结果带图片
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_UNPACKING_RESULT_WITH_PACKET}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_UNPACKING_RESULT_WITH_PACKET}
     */
    public boolean CLIENT_SetUnpackingResultWithPacket(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief X光机集中判图时 订阅开包检查结果
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_XRAY_ATTACH_UNPACKING}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_XRAY_ATTACH_UNPACKING}
     */
    public LLong CLIENT_XRay_AttachUnpackingResult(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief X光机集中判图时 退订开包检查结果
     */
    public boolean CLIENT_XRay_DetachUnpackingResult(LLong lAttachHandle);

    /**
     * @brief 设置运单开始/结束状态
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_WAYBILL_STATUS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_WAYBILL_STATUS}
     */
    public boolean CLIENT_SetWaybillStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取工作目录信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_STORAGE_INFO_BY_FILE_TYPE}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_STORAGE_INFO_BY_FILE_TYPE}
     */
    public boolean CLIENT_GetStorageInfoByFileType(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

}
