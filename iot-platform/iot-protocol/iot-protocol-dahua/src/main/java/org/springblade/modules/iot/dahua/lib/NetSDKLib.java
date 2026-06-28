package org.springblade.modules.iot.dahua.lib;

import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import org.springblade.modules.iot.dahua.lib.method.LLong;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NetSDK JNA接口封装
 */
public interface NetSDKLib extends Library {

    NetSDKLib NETSDK_INSTANCE = Native.load(LibraryLoad.getLoadLibrary("dhnetsdk"), NetSDKLib.class);

    NetSDKLib CONFIG_INSTANCE = Native.load(LibraryLoad.getLoadLibrary("dhconfigsdk"), NetSDKLib.class);

    //NetSDKLib CONFIG_JNI = (NetSDKLib)Native.loadLibrary(util.getLoadLibrary("JNI1.dll"), INetSDK.class);

    /**
     * @brief 获取当前所有规则温度信息
     * @param pInParam 接口输入参数， 参考结构体定义 NET_IN_RADIOMETRY_GET_CUR_TEMPER_ALL_INFO
     * @param pOutParam 接口输出参数， 参考结构体定义 NET_OUT_RADIOMETRY_GET_CUR_TEMPER_ALL_INFO
     */
    public boolean CLIENT_RadiometryGetCurTemperAll(LLong lLoginID, Pointer pInParam, Pointer pOutParam, int nWaitTime);

    /**
     * @brief 获取测温项温度的参数值
     * @param pInParam 接口输入参数， 参考结构体定义 NET_IN_RADIOMETRY_GET_TEMPER_INFO
     * @param pOutParam 接口输出参数， 参考结构体定义 NET_OUT_RADIOMETRY_GET_TEMPER_INFO
     */
    public boolean CLIENT_RadiometryGetTemper(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取EAS设备能力集
     * @param pstuInParam 接口输入参数， 参考结构体定义 NET_IN_GET_EAS_DEVICE_CAPS_INFO
     * @param pstuOutParam 接口输出参数， 参考结构体定义 NET_OUT_GET_EAS_DEVICE_CAPS_INFO
     */
    public boolean CLIENT_GetEASDeviceCaps(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设备导出文件接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_EXPORT_DATA_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_EXPORT_DATA_INFO}
     */
    public boolean CLIENT_SecurityExportDataEx(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 设备导入文件接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_IMPORT_DATA_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_IMPORT_DATA_INFO}
     */
    public boolean CLIENT_SecurityImportDataEx(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 准备生成导出文件接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_PREPARE_EXPORT_DATA_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_PREPARE_EXPORT_DATA_INFO}
     */
    public boolean CLIENT_SecurityPrepareExportData(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 查询特定数据类型的任务状态接口
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_GETCAPS_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_GETCAPS_INFO}
     */
    public boolean CLIENT_SecurityGetCaps(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 查询当前支持的导入导出数据类型
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECURITY_GET_TASK_STATUS_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECURITY_GET_TASK_STATUS_INFO}
     */
    public boolean CLIENT_SecurityGetTaskStatus(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 添加添加本地分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ADD_LOCAL_ANALYSE_TASK}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ADD_LOCAL_ANALYSE_TASK}
     */
    public boolean CLIENT_RecordSecondaryAnalyseAddLocalAnalyseTask(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取设备点位信息
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SCADA_GET_ATTRIBUTE_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SCADA_GET_ATTRIBUTE_INFO}
     */
    public boolean CLIENT_SCADAGetAttributeInfo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 订阅录像二次分析实时结果 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_SECONDARY_ANALYSE_RESULT}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_SECONDARY_ANALYSE_RESULT}
     */
    public LLong CLIENT_AttachRecordSecondaryAnalyseResult(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 开始录像二次分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECONDARY_ANALYSE_STARTTASK}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECONDARY_ANALYSE_STARTTASK}
     */
    public boolean CLIENT_RecordSecondaryAnalyseStartTask(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 设置日志加密密码
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SET_LOG_ENCRYPT_KEY_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SET_LOG_ENCRYPT_KEY_INFO}
     */
    public boolean CLIENT_SetLogEncryptKey(LLong lLogID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 订阅测温温度数据,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RADIOMETRY_ATTACH_TEMPER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RADIOMETRY_ATTACH_TEMPER}
     */
    public LLong CLIENT_RadiometryAttachTemper(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 取消订阅录像二次分析实时结果
     */
    public boolean CLIENT_DetachRecordSecondaryAnalyseResult(LLong lAttachHandle);

    /**
     * @brief 删除录像二次分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECONDARY_ANALYSE_REMOVETASK}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECONDARY_ANALYSE_REMOVETASK}
     */
    public boolean CLIENT_RecordSecondaryAnalyseRemoveTask(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 暂停录像二次分析任务 pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SECONDARY_ANALYSE_PAUSETASK}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SECONDARY_ANALYSE_PAUSETASK}
     */
    public boolean CLIENT_RecordSecondaryAnalysePauseTask(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取闸机状态
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ACCESS_GET_ASG_STATE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ACCESS_GET_ASG_STATE}
     */
    public boolean CLIENT_GetASGState(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 清除闸机的统计信息方法，pstInParam与pstOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ASGMANAGER_CLEAR_STATISTICS}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ASGMANAGER_CLEAR_STATISTICS}
     */
    public boolean CLIENT_ASGManagerClearStatistics(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 开始放音接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_START_REMOTE_SPEAK_PLAY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_START_REMOTE_SPEAK_PLAY}
     */
    public boolean CLIENT_StartRemoteSpeakPlay(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止放音接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_STOP_REMOTE_SPEAK_PLAY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_STOP_REMOTE_SPEAK_PLAY}
     */
    public boolean CLIENT_StopRemoteSpeakPlay(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 单条测温规则设置更新
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_SET_RADIOMETRY_RULE_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_SET_RADIOMETRY_RULE_INFO}
     */
    public boolean CLIENT_SetRadiometryRule(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 订阅气象信息,pstuInParam与pstuOutParam内存由用户申请释放
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_WEATHER_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_WEATHER_INFO}
     */
    public LLong CLIENT_AttachWeatherInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止订阅气象信息,lAttachHandle是CLIENT_AttachWeatherInfo返回值
     */
    public boolean CLIENT_DetachWeatherInfo(LLong lAttachHandle);

    /**
     * @brief 获取气象信息,pInBuf与pOutBuf内存由用户申请释放
     * @param pInBuf 接口输入参数， 参考结构体定义 {@link NET_IN_GET_ATOMSPHDATA}
     * @param pOutBuf 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_ATOMSPHDATA}
     */
    public boolean CLIENT_GetAtomsphData(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * @brief 获取测温区域的参数值, pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RADIOMETRY_RANDOM_REGION_TEMPER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RADIOMETRY_RANDOM_REGION_TEMPER}
     */
    public boolean CLIENT_RadiometryGetRandomRegionTemper(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 分组人员统计--获取摘要信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_NUMBERSTATGROUPSUMMARY_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_NUMBERSTATGROUPSUMMARY_INFO}
     */
    public boolean CLIENT_GetNumberStatGroupSummary(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int waittime);

    /**
     * @brief 订阅电梯内实时数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_ELEVATOR_FLOOR_COUNTER_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_ELEVATOR_FLOOR_COUNTER_INFO}
     */
    public LLong CLIENT_AttachElevatorFloorCounter(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取临时token
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_TEMPORARY_TOKEN}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_TEMPORARY_TOKEN}
     */
    public boolean CLIENT_GetTemporaryToken(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅电梯内实时数据
     */
    public boolean CLIENT_DetachElevatorFloorCounter(LLong lAttachHandle);

    /**
     * @brief 获取事件详细信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_IVSEVENT_DETAIL_INFO}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link NET_OUT_GET_IVSEVENT_DETAIL_INFO}
     */
    public boolean CLIENT_GetIVSEventDetail(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 得到远程指定目录下文件信息（含文件/子目录）pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_REMOTE_LIST}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_REMOTE_LIST}
     */
    public boolean CLIENT_RemoteList(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 唤醒设备
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_WAKE_UP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_WAKE_UP_INFO}
     */
    public boolean CLIENT_DoWakeUpLowPowerDevcie(LLong lChannelHandle,Pointer pInParam,Pointer pOutParam);

    /**
     * @brief 创建低功耗通道
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_CREATE_LOW_POWER_CHANNEL}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_CREATE_LOW_POWER_CHANNEL}
     */
    public LLong CLIENT_CreateLowPowerChannel(LLong lSubBizHandle,Pointer pInParam,Pointer pOutParam);

    /**
     * @brief 销毁低功耗通道
     */
    public boolean CLIENT_DestoryLowPowerChannel(LLong lChannelHandle);

    /**
     * @brief 拒绝休眠
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_REFUSE_SLEEP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_REFUSE_SLEEP_INFO}
     */
    public boolean CLIENT_RefuseLowPowerDevSleep(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取X射线源能力
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_XRAY_SOURCE_CAPS_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_XRAY_SOURCE_CAPS_INFO}
     */
    public boolean CLIENT_GetXRaySourceCaps(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取X射线源累积出束时间
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_XRAY_SOURCE_CUMULATE_TIME_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_XRAY_SOURCE_CUMULATE_TIME_INFO}
     */
    public boolean CLIENT_GetXRaySourceCumulateTime(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 查找记录:nFilecount:需要查询的条数, 一般情况出参中nRetRecordNum<nFilecount则相应时间段内的文件查询完毕,但部分设备性能限制,nRetRecordNum<nFilecount不代表查询完毕,建议和CLIENT_QueryRecordCount配套使用,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FIND_SEEK_NEXT_RECORD_PARAM}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FIND_SEEK_NEXT_RECORD_PARAM}
     */
    public boolean CLIENT_FindSeekNextRecord(Pointer pInParam,Pointer pOutParam,int waittime);

    /**
     * @brief 查询人脸库总空间大小、剩余空间大小、人脸库可导入总条数和剩余可导入条数
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_FACE_RECOGNITION_GROUP_SPACE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_FACE_RECOGNITION_GROUP_SPACE_INFO}
     */
    public boolean CLIENT_GetFaceRecognitionGroupSpaceInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 人员重新建模, pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FACE_RECOGNITION_REABSTRACT_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FACE_RECOGNITION_REABSTRACT_INFO}
     */
    public boolean CLIENT_FaceRecognitionReAbstract(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 终止建模
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_STOP_FACE_RECOGNITION_REABSTRACT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_STOP_FACE_RECOGNITION_REABSTRACT}
     */
    public boolean CLIENT_StopFaceRecognitionReAbstract(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 目标组重新建模, pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FACE_RECOGNITION_GROUP_REABSTRACT_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FACE_RECOGNITION_GROUP_REABSTRACT_INFO}
     */
    public boolean CLIENT_FaceRecognitionGroupReAbstract(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取目标导入令牌
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_FACE_RECOGNITION_APPEND_TOKEN}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_FACE_RECOGNITION_APPEND_TOKEN}
     */
    public boolean CLIENT_GetFaceRecognitionAppendToken(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 组成要设置的智能配置信息(lpInBuffer，szOutBuffer内存由用户申请释放)
     */
    public boolean CLIENT_PacketIntelliSchemeData(Pointer szCommand,Pointer lpInBuffer,int dwInBufferSize,Pointer szOutBuffer,int dwOutBufferSize);

    /**
     * @brief 解析查询到的智能配置信息(szInBuffer，lpOutBuffer内存由用户申请释放)
     */
    public boolean CLIENT_ParseIntelliSchemeData(Pointer szCommand,Pointer szInBuffer,Pointer lpOutBuffer,int dwOutBufferSize,Pointer pReserved);

    /**
     * @brief 新配置接口，查询配置信息(以Json格式，具体见配置SDK)(szOutBuffer内存由用户申请释放)
     */
    public boolean CLIENT_IntelliSchemeGetNewDevConfig(LLong lLoginID,Pointer szCommand,int nChannelID,Pointer szOutBuffer,int dwOutBufferSize,IntByReference error,int waittime,Pointer pReserved,int nSchemeID);

    /**
     * @brief 设置指定的智能套餐方案的配置信息
     */
    public boolean CLIENT_IntelliSchemeSetNewDevConfig(LLong lLoginID,Pointer szCommand,int nChannelID,Pointer szInBuffer,int dwInBufferSize,IntByReference error,IntByReference restart,int waittime,int nTransactionID,int nSchemeID);

    /**
     * @brief 订阅历史库以图搜图查询结果, 配合CLIENT_StartFindFaceRecognition使用, pstInParam和pstOutParam由用户申请和释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_RESULT_FINDHISTORY_BYPIC}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_RESULT_FINDHISTORY_BYPIC}
     */
    public LLong CLIENT_AttachResultOfFindHistoryByPic(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 取消订阅历史库以图搜图查询结果, lFindHandle 为 CLIENT_AttachResultOfFindRegisterByPic接口返回的值
     */
    public boolean CLIENT_DetachResultOfFindHistoryByPic(LLong lFindHandle);

    /**
     * @brief 获取智能套餐方案的基础信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_SCHEME_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_SCHEME_INFO}
     */
    public boolean CLIENT_GetSchemeInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 下发https服务器地址，用于公交线路、报站信息的上报,pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_DISPATCH_BUS_HTTPS_SERVERS_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_DISPATCH_BUS_HTTPS_SERVERS_INFO}
     */
    public boolean CLIENT_DispatchBusHttpsServers(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 订阅485实时数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_IOTBOX_COMM}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_IOTBOX_COMM}
     */
    public LLong CLIENT_AttachIotboxComm(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅实时数据
     */
    public boolean CLIENT_DetachIotboxComm(LLong lAttachHandle);

    /**
     * @brief 订阅485实时数据扩展
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_IOTBOX_COMM_EX}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_IOTBOX_COMM_EX}
     */
    public LLong CLIENT_AttachIotboxCommEx(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅实时数据扩展
     */
    public boolean CLIENT_DetachIotboxCommEx(LLong lAttachHandle);

    /**
     * @brief 订阅区域流量数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_AREA_FLOW}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link NET_OUT_ATTACH_AREA_FLOW}
     */
    public LLong CLIENT_AttachAreaFlow(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 订阅前端设备对讲状态,pstInParam与pstOutParam内存由用户申请释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_TALK_STATE}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_TALK_STATE}
     */
    public LLong CLIENT_AttachTalkState(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 取消订阅前端设备对讲状态, lAttachHandle为CLIENT_AttachTalkState返回的句柄
     */
    public boolean CLIENT_DetachTalkState(LLong lAttachHandle);

    /**
     * @brief 获取安检机录像文件
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_XRAY_DOWNLOAD_RECORD_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_XRAY_DOWNLOAD_RECORD_INFO}
     */
    public boolean CLIENT_GetXRayDownloadRecord(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置屏幕叠加回调
     * @param cbDraw 接口输入参数， 参考回调函数定义 {@link fDrawCallBack}
     */
    public void CLIENT_RigisterDrawFun(fDrawCallBack cbDraw,Pointer dwUser);

    /**
     * @brief 上传二进制地图图片
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_UPLOAD_MAP_PIC}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_UPLOAD_MAP_PIC}
     */
    public boolean CLIENT_UploadMapPic(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 加载地图图片
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_LOAD_MAP_PIC}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_LOAD_MAP_PIC}
     */
    public boolean CLIENT_LoadMapPic(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取地图已标记信息列表
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_PIC_MAP_MARK_LIST}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_PIC_MAP_MARK_LIST}
     */
    public boolean CLIENT_GetPicMapMarkList(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 地图上标记通道位置信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MARK_PIC_MAP_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MARK_PIC_MAP_CHANNEL}
     */
    public boolean CLIENT_MarkPicMapChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消通道的地图标记
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_UNMARK_PIC_MAP_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_UNMARK_PIC_MAP_CHANNEL}
     */
    public boolean CLIENT_UnmarkPicMapChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 重置清空地图（包括地图图片，标记信息）
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RESET_PIC_MAP}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RESET_PIC_MAP}
     */
    public boolean CLIENT_ResetPicMap(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 收藏目标事件记录
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     */
    public boolean CLIENT_MarkObjectFavoritesLibraryObjectRecords(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消收藏目标事件记录
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_UNMARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_UNMARK_OBJECT_FAVORITES_LIBRARY_OBJECT_RECORDS}
     */
    public boolean CLIENT_UnmarkObjectFavoritesLibraryObjectRecords(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 检查目标事件记录的收藏状态
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_CHECK_OBJECT_FAVORITES_LIBRARY_MARK_STATUS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_CHECK_OBJECT_FAVORITES_LIBRARY_MARK_STATUS}
     */
    public boolean CLIENT_CheckObjectFavoritesLibraryMarkStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 开始按条件，查找收藏夹内容数据
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_START_FIND_OBJECT_FAVORITES_LIBRARY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_START_FIND_OBJECT_FAVORITES_LIBRARY}
     */
    public LLong CLIENT_StartFindObjectFavoritesLibrary(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取收藏夹内容数据, lFindID为CLIENT_StartFindObjectFavoritesLibrary接口返回的查找ID
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_DO_FIND_OBJECT_FAVORITES_LIBRARY}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_DO_FIND_OBJECT_FAVORITES_LIBRARY}
     */
    public boolean CLIENT_DoFindObjectFavoritesLibrary(LLong lFindID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止查找收藏夹内容数据，清空查询会话, lFindID为CLIENT_StartFindObjectFavoritesLibrary接口返回的查找ID
     */
    public boolean CLIENT_StopFindObjectFavoritesLibrary(LLong lFindID);

    /**
     * @brief 根据目标轨迹过滤规则，开始查询
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_START_FIND_OBJECT_MEDIA_FIND}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_START_FIND_OBJECT_MEDIA_FIND}
     */
    public LLong CLIENT_StartFindObjectMediaFind(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取录像查询结果
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FIND_OBJECT_MEDIA_FIND_FILE}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FIND_OBJECT_MEDIA_FIND_FILE}
     */
    public boolean CLIENT_FindObjectMediaFindFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取目标事件查询结果
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_FIND_OBJECT_MEDIA_FIND_EVENT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_FIND_OBJECT_MEDIA_FIND_EVENT}
     */
    public boolean CLIENT_FindObjectMediaFindEvent(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 停止查询
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_STOP_FIND_OBJECT_MEDIA_FIND}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_STOP_FIND_OBJECT_MEDIA_FIND}
     */
    public boolean CLIENT_StopFindObjectMediaFind(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取系统参数
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_BOOT_PARAMETER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_BOOT_PARAMETER}
     */
    public boolean CLIENT_GetBootParameter(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 一键操作录播录像的开启/暂停/关闭
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MANUAL_CONTROL_COURSE_RECORD_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MANUAL_CONTROL_COURSE_RECORD_INFO}
     */
    public boolean CLIENT_ManualControlCourseRecord(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置车位检测器车灯亮灯计划,pNetDataIn与pNetDataOut由用户申请内存
     * @param pNetDataIn 接口输入参数， 参考结构体定义 {@link NET_IN_SET_PARKING_SPACE_LIGHT_PLAN}
     * @param pNetDataOut 接口输出参数， 参考结构体定义 {@link NET_OUT_SET_PARKING_SPACE_LIGHT_PLAN}
     */
    public boolean CLIENT_SetParkingSpaceLightPlan(LLong lLoginID,Pointer pNetDataIn,Pointer pNetDataOut,int nWaitTime);

    /**
     * @brief 修改采集文件备注
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_MODIFY_MEDIA_FILE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_MODIFY_MEDIA_FILE_INFO}
     */
    public boolean CLIENT_ModifyMediaFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 删除采集文件
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_DELETE_MEDIA_FILE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_DELETE_MEDIA_FILE_INFO}
     */
    public boolean CLIENT_DeleteMediaFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 开始查询采集文件信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_QUERY_MEDIA_FILE_OPEN_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_QUERY_MEDIA_FILE_OPEN_INFO}
     */
    public boolean CLIENT_QueryMediaFileOpen(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 查询采集文件信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_QUERY_MEDIA_FILE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_QUERY_MEDIA_FILE_INFO}
     */
    public boolean CLIENT_QueryMediaFile(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 关闭采集文件查询
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_QUERY_MEDIA_FILE_CLOSE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_QUERY_MEDIA_FILE_CLOSE_INFO}
     */
    public boolean CLIENT_QueryMediaFileClose(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 云运维异步诊断接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ASYNC_CHECK_FAULT_CHECK}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ASYNC_CHECK_FAULT_CHECK}
     */
    public boolean CLIENT_AsyncCheckFaultCheck(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 四合一烟感平台下发整机重启
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_REBOOT_DEVICE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_REBOOT_DEVICE}
     */
    public boolean CLIENT_RebootDevice(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 重启系统,恢复出厂默认(包括清空配置和删除账户)并重启，实现硬复位功能
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_RESET_SYSTEM}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_RESET_SYSTEM}
     */
    public boolean CLIENT_ResetSystem(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取设备当前状态并绑定导出接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_START_STREAM_DATA}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_START_STREAM_DATA}
     */
    public LLong CLIENT_AttachStartStreamData(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 订阅抓包数据,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_SNIFFER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ATTACH_SNIFFER}
     */
    public LLong CLIENT_AttachSniffer(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 订阅调试日志回调
     * @param pInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ATTACH_DBGINFO}
     * @param pOutParam 接口输入参数， 参考结构体定义 {@link NET_OUT_ATTACH_DBGINFO}
     */
    public LLong CLIENT_AttachDebugInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 注销一键导出处理
     */
    public boolean CLIENT_DetachStopStreamData(LLong lAttachHandle);

    /**
     * @brief 退订抓包数据
     */
    public boolean CLIENT_DetachSniffer(LLong lAttachHandle);

    /**
     * @brief 退订调试日志回调
     */
    public boolean CLIENT_DetachDebugInfo(LLong lAttachHanle);

    /**
     * @brief 添加Onvif用户， pstInParam、pstOutParam 内存由用户申请、释放
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link NET_IN_ADD_ONVIF_USER_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_ADD_ONVIF_USER_INFO}
     */
    public boolean CLIENT_AddOnvifUser(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取子模块信息
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link NET_IN_GET_SUBMODULES_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link NET_OUT_GET_SUBMODULES_INFO}
     */
    public boolean CLIENT_GetSubModuleInfo(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取软件版本
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_SOFTWAREVERSION_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_SOFTWAREVERSION_INFO}
     */
    public boolean CLIENT_GetSoftwareVersion(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 开始抓包,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_SNIFFER}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_SNIFFER}
     */
    public LLong CLIENT_StartSniffer(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 停止抓包
     */
    public boolean CLIENT_StopSniffer(LLong lLoginID,LLong lSnifferID);

    /**
     * @brief 获取抓包状态,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_SNIFFER_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_SNIFFER_INFO}
     */
    public boolean CLIENT_GetSnifferInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取抓包能力,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_SNIFFER_CAP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_SNIFFER_CAP}
     */
    public boolean CLIENT_GetSnifferCaps(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取从片版本信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_PERIPHERAL_CHIP_VERSION}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_PERIPHERAL_CHIP_VERSION}
     */
    public boolean CLIENT_GetPeripheralChipVersion(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 调节光圈
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_PTZ_ADJUST_IRIS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_PTZ_ADJUST_IRIS}
     */
    public boolean CLIENT_PTZAdjustIris(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取CPU温度
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_CPU_TEMPERATURE_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_CPU_TEMPERATURE_INFO}
     */
    public boolean CLIENT_GetCPUTemperature(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 订阅智能分析结果
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_VIDEO_ANALYSE_ANALYSE_PROC_EX}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_VIDEO_ANALYSE_ANALYSE_PROC_EX}
     */
    public LLong CLIENT_AttachVideoAnalyseAnalyseProcEx(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 取消订阅智能分析结果
     */
    public boolean CLIENT_DetachVideoAnalyseAnalyseProcEx(LLong lAttachHandle);

    /**
     * @brief 持续对焦
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_FOCUS_PTZ_CONTINUOUSLY_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_FOCUS_PTZ_CONTINUOUSLY_INFO}
     */
    public boolean CLIENT_FocusPTZContinuously(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置电视墙场景,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MONITORWALL_SET_SCENE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MONITORWALL_SET_SCENE}
     */
    public boolean CLIENT_MonitorWallSetScene(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取电视墙场景,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MONITORWALL_GET_SCENE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MONITORWALL_GET_SCENE}
     */
    public boolean CLIENT_MonitorWallGetScene(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 设置窗口场景信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SPLIT_SET_WINDOWS_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SPLIT_SET_WINDOWS_INFO}
     */
    public boolean CLIENT_SetSplitWindowsInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 根据执法人ID获取该执法人绑定的执法记录仪序列号列表
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_COLLECT_DEVICES_INFO_BY_TSFID_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_COLLECT_DEVICES_INFO_BY_TSFID_INFO}
     */
    public boolean CLIENT_GetCollectDevicesInfoByTsfId(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取所有 Onvif 用户信息，pstInParam、pstOutParam 内存由用户申请、释放
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GETONVIF_USERINFO_ALL_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GETONVIF_USERINFO_ALL_INFO}
     */
    public boolean CLIENT_GetOnvifUserInfoAll(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * @brief 获取蜂窝邻区信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_NEIGHBOUR_CELL_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_NEIGHBOUR_CELL_INFO}
     */
    public boolean CLIENT_GetNeighbourCellInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 设置窗口轮巡显示源(pInparam, pOutParam内存由用户申请释放)
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_TOUR_SOURCE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_TOUR_SOURCE}
     */
    public boolean CLIENT_SetTourSource(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取窗口轮巡显示源(pInparam, pOutParam内存由用户申请释放)
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_TOUR_SOURCE}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_TOUR_SOURCE}
     */
    public boolean CLIENT_GetTourSource(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 楼层一键标定
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_AUTO_CALIBRATE}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_AUTO_CALIBRATE}
     */
    public boolean CLIENT_AutoCalibrate(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 手动修改楼层高度并生成气压差信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MODIFY_FLOOR_HEIGHT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MODIFY_FLOOR_HEIGHT}
     */
    public boolean CLIENT_ModifyFloorHeight(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取ElevatorFloorCounter.autoCalibrate下发后的标定状态
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_CALIBRATE_STATUS}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_CALIBRATE_STATUS}
     */
    public boolean CLIENT_GetCalibrateStatus(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 启动程序
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_APP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_APP}
     */
    public boolean CLIENT_StartApp(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 停止程序运行
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_STOP_APP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_STOP_APP}
     */
    public boolean CLIENT_StopApp(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 卸载程序
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_REMOVE_APP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_REMOVE_APP}
     */
    public boolean CLIENT_RemoveApp(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 第三方APP升级进度和结果的实时上报
     * @param emType 接口输出参数， 参考枚举定义 {@link com.netsdk.lib.enumeration.EM_NET_UPGRADE_INSTALL_TYPE}
     */
    public boolean CLIENT_UpgraderInstall(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaittime);

    /**
     * @brief 获取安装的应用列表
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_INSTALLED_APP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_INSTALLED_APP_INFO}
     */
    public boolean CLIENT_GetInstalledAppInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 通用RPC接口，支持Json传参
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_NORMAL_RPCCALL_USING_JSON}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_NORMAL_RPCCALL_USING_JSON}
     */
    public boolean CLIENT_NormalRpcCallUsingJson(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取存储端口信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_PORTINFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_PORTINFO}
     */
    public boolean CLIENT_GetStoragePortInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int waittime);

    /**
     * @brief 订阅录像状态变化,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_RECORDMANAGER_ATTACH_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_RECORDMANAGER_ATTACH_INFO}
     */
    public LLong CLIENT_AttachRecordManagerState(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取外部文件上传的文件名和路径
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_UPLOAD_PATH_INFO}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_UPLOAD_PATH_INFO}
     */
    public boolean CLIENT_GetUploadPath(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取指定盘组下的设备成员信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_WORK_GROUP_DEVICE_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_WORK_GROUP_DEVICE_INFO}
     */
    public boolean CLIENT_GetWorkGroupDeviceInfos(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief json通用订阅接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_NORMAL_USING_JSON}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_NORMAL_USING_JSON}
     */
    public LLong CLIENT_AttachNormalUsingJson(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief json通用订阅退订接口
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DETACH_NORMAL_USING_JSON}
     */
    public boolean CLIENT_DetachNormalUsingJson(LLong lLoginID,Pointer pstuInParam);

    /**
     * @brief 开启音频录音并得到录音名
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_AUDIO_RECORD_MANAGER_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_AUDIO_RECORD_MANAGER_CHANNEL}
     */
    public boolean CLIENT_StartAudioRecordManagerChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 关闭即时录音
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_STOP_AUDIO_RECORD_MANAGER_CHANNEL}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_STOP_AUDIO_RECORD_MANAGER_CHANNEL}
     */
    public boolean CLIENT_StopAudioRecordManagerChannel(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 按组获取视频通道
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_CAMERA_ALL_BY_GROUP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_CAMERA_ALL_BY_GROUP}
     */
    public boolean CLIENT_MatrixGetCameraAllByGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 投递异步登录任务
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_POST_LOGIN_TASK}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_POST_LOGIN_TASK}
     */
    public int CLIENT_PostLoginTask(Pointer pInParam,Pointer pOutParam);

    /**
     * @brief 取消 CLIENT_PostLoginTask 接口的异步登录任务，dwTaskID 为 CLIENT_PostLoginTask 返回值
     */
    public boolean CLIENT_CancelLoginTask(int dwTaskID);

    /**
     * @brief 设置优化方案 pParam内存由用户申请释放，大小参照emType对应的结构体
     * @param emType 接口输入参数， 参考枚举定义 {@link com.netsdk.lib.enumeration.NET_SYS_ABILITY}
     */
    public boolean CLIENT_SetOptimizeMode(int emType,Pointer pParam);

    /**
     * @brief 查询部门信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_DEPARTMENT_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_DEPARTMENT_INFO}
     */
    public boolean CLIENT_GetTipStaffManagerDepartmentInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 查询执法人信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_TIP_STAFF_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_TIP_STAFF_INFO}
     */
    public boolean CLIENT_GetTipStaffManagerTipStaffInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取雷达检测目标数据
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_WATERRADAR_OBJECTINFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_WATERRADAR_OBJECTINFO}
     */
    public boolean CLIENT_GetWaterRadarObjectInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 报警上传功能,启动服务；dwTimeOut参数已无效
     * @param pfscb 接口输入参数， 参考回调函数定义 {@link com.netsdk.lib.NetSDKLib.fServiceCallBack}
     */
    public LLong CLIENT_StartService(int wPort,Pointer pIp,fServiceCallBack pfscb,int dwTimeOut,Pointer dwUserData);

    /**
     * @brief 设置实时预览帧信息回调
     * @param cbFrameInfo 接口输入参数， 参考回调函数定义 {@link com.netsdk.lib.NetSDKLib.fFrameInfoCallBackEx}
     */
    public boolean CLIENT_SetRealFrameInfoCallBack(LLong lRealHandle,fFrameInfoCallBackEx cbFrameInfo,Pointer dwUser);

    /**
     * @brief 发送按键消息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SEND_XRAY_KEY_MANAGER_KEY_INFO}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SEND_XRAY_KEY_MANAGER_KEY_INFO}
     */
    public boolean CLIENT_SendXRayKeyManagerKey(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 取消订阅测温温度数据,lAttachTemperHandle是 CLIENT_RadiometryAttachTemper 的返回值
     */
    public boolean CLIENT_RadiometryDetachTemper(LLong lAttachTemperHandle);

    /**
     * @brief 获取车流统计摘要信息
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_START_FIND_VEHICLE_FLOW_STAT}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_START_FIND_VEHICLE_FLOW_STAT}
     */
    public LLong CLIENT_StartFindVehicleFlowStat(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 获取车流量统计结果信息, lFindID为 CLIENT_StartFindVehicleFlowStat 接口返回的查询ID
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DO_FIND_VEHICLE_FLOW_STAT}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DO_FIND_VEHICLE_FLOW_STAT}
     */
    public boolean CLIENT_DoFindVehicleFlowStat(LLong lFindID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 结束车流量统计结果查询
     */
    public boolean CLIENT_StopFindVehicleFlowStat(LLong lFindID);

    /**
     * @brief 设置水平旋转组边界值
     * @param pstInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_PAN_GROUP_LIMIT_INFO}
     * @param pstOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_PAN_GROUP_LIMIT_INFO}
     */
    public boolean CLIENT_PTZSetPanGroupLimit(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int dwWaitTime);

    /**
     * @brief 获取电视墙上屏幕窗口解码信息,pInParam与pOutParam内存由用户申请释放
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_MW_GET_WINODW_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_MW_GET_WINDOW_INFO}
     */
    public boolean CLIENT_MonitorWallGetWindowInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取工作目录组名
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_WORKDIRECTORY_GETGROUP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_WORKDIRECTORY_GETGROUP_INFO}
     */
    public boolean CLIENT_WorkDirectoryGetGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 设置工作目录组名
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_WORKDIRECTORY_SETGROUP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_WORKDIRECTORY_SETGROUP_INFO}
     */
    public boolean CLIENT_WorkDirectorySetGroup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取存储设备信息,pDevice内存由用户申请释放
     * @param pDevice 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_STORAGE_DEVICE}
     */
    public boolean CLIENT_GetStorageDeviceInfo(LLong lLoginID,Pointer pszDevName,Pointer pDevice,int nWaitTime);

    /**
     * @brief 异步格式化设备,格式化进度通过CLIENT_AttachDevStorageDevFormat接口的回调获取  pszDevName与CLIENT_GetStorageDeviceInfo中的pszDevName保持一致
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DEVSTORAGE_FORMAT_PARTITION_ASYN}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DEVSTORAGE_FORMAT_PARTITION_ASYN}
     */
    public boolean CLIENT_DevStorageFormatPartitionAsyn(LLong lLoginID,Pointer pszDevName,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取所有盘组信息
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_STORAGE_ASSISTANT_GROUP_INFO}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_STORAGE_ASSISTANT_GROUP_INFO}
     */
    public boolean CLIENT_GetStorageAssistantGroupInfos(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 设置工作目录组名
     * @param pInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_SET_WORK_DIRECTORY_GROUP}
     * @param pOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_SET_WORK_DIRECTORY_GROUP}
     */
    public boolean CLIENT_SetWorkDirectoryGoup(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * @brief 获取工作组内的工作目录名称列表
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_GET_WORK_GROUP_DIRECTORIES}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_GET_WORK_GROUP_DIRECTORIES}
     */
    public boolean CLIENT_GetWorkGroupDirectories(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 添加盘组
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ADD_STORAGE_ASSISTANT_WORK_GROUP}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ADD_STORAGE_ASSISTANT_WORK_GROUP}
     */
    public boolean CLIENT_AddStorageAssistantWorkGroup(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief 删除盘组
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_DELETE_STORAGE_ASSISTANT_WORK_GROUP}
     * @param pstuOutParam 接口输出参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_DELETE_STORAGE_ASSISTANT_WORK_GROUP}
     */
    public boolean CLIENT_DeleteStorageAssistantWorkGroup(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_DOCK_EVENTS}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_DOCK_EVENTS}
     */
    public LLong CLIENT_AttachDockEvents(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     */
    public boolean CLIENT_DetachDockEvents(LLong lAttachHandle);

    /**
     * @brief
     * @param pstuInParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_IN_ATTACH_DOCK_INFO}
     * @param pstuOutParam 接口输入参数， 参考结构体定义 {@link com.netsdk.lib.structure.NET_OUT_ATTACH_DOCK_INFO}
     */
    public LLong CLIENT_AttachDockInfo(LLong lLoginID,Pointer pstuInParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * @brief
     */
    public boolean CLIENT_DetachDockInfo(LLong lAttachHandle);

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
