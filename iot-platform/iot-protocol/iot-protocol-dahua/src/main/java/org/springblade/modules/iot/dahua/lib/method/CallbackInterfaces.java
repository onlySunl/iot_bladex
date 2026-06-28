package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.*;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.Utils.LLong;

/**
 * NetSDK 回调接口定义
 */
public interface CallbackInterfaces {

    public interface fDisConnect extends Callback {
        public void invoke(NetSDKLib.LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser);
    }

    // 网络连接恢复回调函数原形
    public interface fHaveReConnect extends Callback {
        public void invoke(LLong lLoginID,String pchDVRIP,int nDVRPort,Pointer dwUser);
    }

    // 消息回调函数原形(pBuf内存由SDK内部申请释放)
    public interface fMessCallBack extends Callback {
        public boolean invoke(int lCommand,LLong lLoginID,Pointer pStuEvent,int dwBufLen,String strDeviceIP,NativeLong nDevicePort,Pointer dwUser);
    }

    // 消息回调函数原形(pBuf内存由SDK内部申请释放)
    // 新增参数说明
    // bAlarmAckFlag : TRUE,该事件为可以进行确认的事件；FALSE,该事件无法进行确认
    // nEventID 用于对 CLIENT_AlarmAck 接口的入参进行赋值,当 bAlarmAckFlag 为 TRUE 时,该数据有效
    // pBuf内存由SDK内部申请释放
    public interface fMessCallBackEx1 extends Callback {
        public boolean invoke(int lCommand,LLong lLoginID,Pointer pStuEvent,int dwBufLen,String strDeviceIP,NativeLong nDevicePort,int bAlarmAckFlag,NativeLong nEventID,Pointer dwUser);
    }

    // 订阅人脸回调函数
    public interface fFaceFindState extends Callback {
// pstStates 指向NET_CB_FACE_FIND_STATE的指针
        public void invoke(LLong lLoginID,LLong lAttachHandle,Pointer pstStates,int nStateNum,Pointer dwUser);
    }

    // 智能分析数据回调;nSequence表示上传的相同图片情况，为0时表示是第一次出现，为2表示最后一次出现或仅出现一次，为1表示此次之后还有
    // int nState = *(int*) reserved 表示当前回调数据的状态, 为0表示当前数据为实时数据，为1表示当前回调数据是离线数据，为2时表示离线数据传送结束
    // pAlarmInfo 对应智能事件信息, pBuffer 对应智能图片信息, dwBufSize 智能图片信息大小
    public interface fAnalyzerDataCallBack extends Callback {
        public int invoke(LLong lAnalyzerHandle,int dwAlarmType,Pointer pAlarmInfo,Pointer pBuffer,int dwBufSize,Pointer dwUser,int nSequence,Pointer reserved) throws UnsupportedEncodingException;
    }

    // 抓图回调函数原形(pBuf内存由SDK内部申请释放)
    // EncodeType 编码类型，10：表示jpeg图片      0：mpeg4    CmdSerial : 操作流水号，同步抓图的情况下用不上
    public interface fSnapRev extends Callback {
        public void invoke(LLong lLoginID,Pointer pBuf,int RevLen,int EncodeType,int CmdSerial,Pointer dwUser);
    }

    // 异步搜索设备回调(pDevNetInfo内存由SDK内部申请释放)
    public interface fSearchDevicesCB extends Callback {
        public void invoke(Pointer pDevNetInfo,Pointer pUserData);
    }

    // 按时间回放进度回调函数原形
    public interface fTimeDownLoadPosCallBack extends Callback {
        public void invoke(LLong lPlayHandle,int dwTotalSize,int dwDownLoadSize,int index,NET_RECORDFILE_INFO.ByValue recordfileinfo,Pointer dwUser);
    }

    // 回放数据回调函数原形
    public interface fDataCallBack extends Callback {
        public int invoke(LLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, Pointer dwUser);
    }

    // 回放进度回调函数原形
    public interface fDownLoadPosCallBack extends Callback {
        public void invoke(LLong lPlayHandle,int dwTotalSize,int dwDownLoadSize,Pointer dwUser);
    }

    // 视频统计摘要信息回调函数原形，lAttachHandle 是 CLIENT_AttachVideoStatSummary 返回值
    public interface fVideoStatSumCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_VIDEOSTAT_SUMMARY pBuf,int dwBufLen,Pointer dwUser);
    }

    // 回放数据回调函数原形（扩展）
    public interface fDataCallBackEx extends Callback {
        public int invoke(LLong lRealHandle,NET_DATA_CALL_BACK_INFO pDataCallBack,Pointer dwUser);
    }

    // 用户自定义的数据回调   lTalkHandle是CLIENT_StartTalkEx的返回值
    // byAudioFlag：   0表示是本地录音库采集的音频数据 ，  1表示收到的设备发过来的音频数据
    public interface pfAudioDataCallBack extends Callback {
        public void invoke(LLong lTalkHandle,Pointer pDataBuf,int dwBufSize,byte byAudioFlag,Pointer dwUser);
    }

    // lHandle是文件传输句柄 ，nTransType是文件传输类型，nState是文件传输状态，
    public interface fTransFileCallBack extends Callback {
        public void invoke(LLong lHandle,int nTransType,int nState,int nSendSize,int nTotalSize,Pointer dwUser);
    }

    // GPS信息订阅回调--扩展
    public interface fGPSRevEx extends Callback {
        public void invoke(LLong lLoginID,GPS_Info.ByValue GpsInfo,ALARM_STATE_INFO.ByValue stAlarmInfo,Pointer dwUserData,Pointer reserved);
    }

    // GPS信息订阅回调--扩展2
    public interface fGPSRevEx2 extends Callback {
        public void invoke(LLong lLoginID,NET_GPS_LOCATION_INFO lpData,Pointer dwUserData,Pointer reserved);
    }

    // 实时预览数据回调函数--扩展(pBuffer内存由SDK内部申请释放)EM_CLASS_CROWD_ABNORMAL
    // lRealHandle实时预览           dwDataType: 0-原始数据   1-帧数据    2-yuv数据   3-pcm音频数据
    // pBuffer对应BYTE*
    // param:当类型为0(原始数据)和2(YUV数据) 时为0。当回调的数据类型为1时param为一个tagVideoFrameParam结构体指针。
    // param:当数据类型是3时,param也是一个tagCBPCMDataParam结构体指针
    public interface fRealDataCallBackEx extends Callback {
        public void invoke(LLong lRealHandle,int dwDataType,Pointer pBuffer,int dwBufSize,int param,Pointer dwUser);
    }

    // 实时预览数据回调函数原形--扩展(pBuffer内存由SDK内部申请释放)
    // 通过 dwDataType 过滤得到对应码流，具体码流类型请参考 EM_REALDATA_FLAG; 转码流时 dwDataType 值请参考 NET_DATA_CALL_BACK_VALUE 说明
    // 当转码流时，param 为具体的转码信息（视频帧、音频帧等信息），对应结构体 NET_STREAMCONVERT_INFO
    public interface fRealDataCallBackEx2 extends Callback {
        void invoke(LLong lRealHandle,int dwDataType,Pointer pBuffer,int dwBufSize,LLong param,Pointer dwUser);
    }

    // 视频预览断开回调函数, (param内存由SDK内部申请释放 )
    // lOperateHandle监控句柄   dwEventType对应EM_REALPLAY_DISCONNECT_EVENT_TYPE   param对应void*,事件参数
    public interface fRealPlayDisConnect extends Callback {
        public void invoke(LLong lOperateHandle,int dwEventType,Pointer param,Pointer dwUser);
    }

    // 订阅过车记录数据回调函数原型     lAttachHandle为CLIENT_ParkingControlAttachRecord返回值
    public interface fParkingControlRecordCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,NET_CAR_PASS_ITEM pInfo,int nBufLen,Pointer dwUser);
    }

    // 订阅车位信息回调函数原型
    public interface fParkInfoCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,NET_PARK_INFO_ITEM pInfo,int nBufLen,Pointer dwUser);
    }

    // 订阅监测点位信息回调函数原型
    public interface fSCADAAttachInfoCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,NET_SCADA_NOTIFY_POINT_INFO_LIST pInfo,int nBufLen,Pointer dwUser);
    }

    // 透明串口回调函数原形(pBuffer内存由SDK内部申请释放))
    public interface fTransComCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lTransComChannel,Pointer pBuffer,int dwBufSize,Pointer dwUser);
    }

    //视频分析进度状态实时回调函数
    public interface fVideoAnalyseState extends Callback {
        public int invoke(LLong lAttachHandle,NET_VIDEOANALYSE_STATE pAnalyseStateInfos,Pointer dwUser,Pointer pReserved);
    }

    // 侦听服务器回调函数原形
    public interface fServiceCallBack extends Callback {
        public int invoke(LLong lHandle,String pIp,int wPort,int lCommand,Pointer pParam,int dwParamLen,Pointer dwUserData);
    }

    // 雷达RFID信息回调函数原形
    public interface fRadarRFIDCardInfoCallBack extends Callback {
        public int invoke(LLong lLoginID,LLong lAttachHandle,NET_RADAR_NOTIFY_RFIDCARD_INFO pBuf,int dwBufLen,Pointer pReserved,Pointer dwUser);
    }

    // CLIENT_ListenServer 接口回调fServiceCallBack函数支持的命令类型
    public static class EM_LISTEN_TYPE extends SdkStructure
    {
        public static final int   NET_DVR_DISCONNECT = -1;              // 验证期间设备断线回调
        public static final int   NET_DVR_SERIAL_RETURN = 1;            // 设备注册携带序列号 对应 char* szDevSerial
        public static final int   NET_DEV_AUTOREGISTER_RETURN = 2;      // 设备注册携带序列号和令牌 对应NET_CB_AUTOREGISTER
        public static final int   NET_DEV_NOTIFY_IP_RETURN = 3;         // 设备仅上报IP, 不作为主动注册用, 用户获取ip后只能按照约定的端口按照非主动注册的类型登录
    }

    //订阅Bus状态回调函数原型
    public interface fBusStateCallBack extends Callback {
        public void invoke(LLong lAttachHandle,int lCommand,Pointer pBuf,int dwBufLen,Pointer dwUser);
    }

    // GPS温湿度信息订阅回调
    public interface fGPSTempHumidityRev extends Callback {
        public void invoke(LLong lLoginID,GPS_TEMP_HUMIDITY_INFO.ByValue GpsTHInfo,Pointer dwUserData);
    }

    // 向设备注册的回调函数原型
    public interface fDeviceStateCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_CB_ATTACH_DEVICE_STATE pstDeviceState,Pointer dwUser);
    }

    // 注册添加设备的回调函数原型
    public interface fAddDeviceCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_CB_ATTACH_ADD_DEVICE pstAddDevice,Pointer dwUser);
    }

    // 定义监测点报警信息回调函数原型
    public interface fSCADAAlarmAttachInfoCallBack extends Callback {
        public void invoke(LLong lAttachHandle,NET_SCADA_NOTIFY_POINT_ALARM_INFO_LIST pInfo,int nBufLen,Pointer dwUser);
    }

    //视频诊断结果上报回调函数
    public interface fRealVideoDiagnosis extends Callback {
        public int invoke(LLong lDiagnosisHandle,NET_REAL_DIAGNOSIS_RESULT pDiagnosisInfo,Pointer pBuf,int nBufLen,Pointer dwUser);
    }

    /// \fn 温度分布数据状态回调函数
    /// \brief
    /// \param  LLONG lAttachHandle [OUT] 订阅句柄, CLIENT_RadiometryAttach 的返回值
    /// \param  NET_RADIOMETRY_DATA pBuf [OUT] 热图数据信息
    /// \param  int nBufLen [OUT] 状态信息长度
    /// \param  LDWORD dwUser 用户数据
    /// \return 无
    public interface fRadiometryAttachCB extends Callback {
        public void invoke(LLong lAttachHandle,NET_RADIOMETRY_DATA pBuf,int nBufLen,Pointer dwUser);
    }

    // 刻录设备回调函数原形,lAttachHandle是CLIENT_AttachBurnState返回值, 每次1条,pBuf->dwSize == nBufLen
    public interface fAttachBurnStateCB extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,NET_CB_BURNSTATE pBuf,int nBufLen,Pointer dwUser);
    }

    // 刻录设备回调扩展函数原形
    public interface fAttachBurnStateCBEx extends Callback {
        public void invoke(LLong lLoginID,LLong lAttachHandle,NET_OUT_BURN_GET_STATE pBuf,int nBufLen,Pointer dwUser);
    }

    //刻录设备回调函数,lUploadHandle是CLIENT_StartUploadFileBurned返回值
    //typedef void (CALLBACK *fBurnFileCallBack) (LLONG lLoginID, LLONG lUploadHandle, int nTotalSize, int nSendSize, LDWORD dwUser);
    public interface fBurnFileCallBack extends Callback {
        public void invoke(LLong lLoginID,LLong lUploadHandle,int nTotalSize,int nSendSize,Pointer dwUser);
    }

    // 升级回调
    public interface fUpgradeCallBackEx extends Callback {
        public void invoke(LLong lLoginID,LLong lUpgradechannel,int nTotalSize,int nSendSize,Pointer dwUserData);
    }

    /************************************************************************
     ** 接口
     ***********************************************************************/
    //  JNA直接调用方法定义，cbDisConnect 实际情况并不回调Java代码，仅为定义可以使用如下方式进行定义。 fDisConnect 回调
}
