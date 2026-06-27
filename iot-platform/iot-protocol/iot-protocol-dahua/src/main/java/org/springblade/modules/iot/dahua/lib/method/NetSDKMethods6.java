package org.springblade.modules.iot.dahua.lib.method;

/**
 * NetSDK 方法定义 - 第 6 部分
 */
public interface NetSDKMethods6 {
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szUserID = new byte[NET_MAX_USERID_LEN]; // 用户ID
        public byte[]           szUserIDEx = new byte[128];           // 用户ID扩展，当前只支持32位有效值下发
        public int              bUserIDEx;                            // szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段

        public NET_IN_ACCESS_FINGERPRINT_SERVICE_GET() {
            this.dwSize = this.size();
        }
    }

    // 获取信息信息出参
    public static class NET_OUT_ACCESS_FINGERPRINT_SERVICE_GET extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nRetFingerPrintCount;                 // 实际返回的信息个数
        public int              nSinglePacketLength;                  // 单个信息数据包长度
        public int              nDuressIndex;                         // 胁迫信息序号
        public int              nMaxFingerDataLength;                 // 接受信息数据的缓存的最大长度
        public int              nRetFingerDataLength;                 // 实际返回的总的信息数据包的长度
        public Pointer          pbyFingerData;                        // 用户分配释放内存,信息数据
        public NET_TIME         stuUpdateTime;                        // 信息更新时间,UTC时间
        public BYTE_ARRAY_32[]  szFingerPrintName = new BYTE_ARRAY_32[3]; //指纹名称数组，指纹名称个数和指纹包个数nRetFingerPrintCount一致

        public NET_OUT_ACCESS_FINGERPRINT_SERVICE_GET() {
            this.dwSize = this.size();
        }
    }

    // 删除用户信息信息入参
    public static class NET_IN_ACCESS_FINGERPRINT_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nUserNum;                             // 删除的用户数量
        public USERID[]         szUserIDs = (USERID[])new USERID().toArray(100); // 用户ID
        public USERIDEX[]       szUserIDEx = (USERIDEX[])new USERIDEX().toArray(100); // 用户ID扩展，当前只支持32位有效值下发
        public int              bUserIDEx;                            // szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段

        public NET_IN_ACCESS_FINGERPRINT_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 删除用户信息信息出参
    public static class NET_OUT_ACCESS_FINGERPRINT_SERVICE_REMOVE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nMaxRetNum;                           // 最大返回数量,不小于 NET_IN_ACCESS_FINGERPRINT_SERVICE_REMOVE中nUserNum
        public Pointer          pFailCode;                            // 对应 FAIL_CODE, 用户分配释放内存,删除失败时，对应插入的每一项的结果,返回个数同NET_IN_ACCESS_FINGERPRINT_SERVICE_REMOVE中nUserNum

        public NET_OUT_ACCESS_FINGERPRINT_SERVICE_REMOVE() {
            this.dwSize = this.size();
        }
    }

    // 清除所有信息信息入参
    public static class NET_IN_ACCESS_FINGERPRINT_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_ACCESS_FINGERPRINT_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    // 清除所有信息信息出参
    public static class NET_OUT_ACCESS_FINGERPRINT_SERVICE_CLEAR extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_ACCESS_FINGERPRINT_SERVICE_CLEAR() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartFindUserInfo 输入参数
    public static class NET_IN_USERINFO_START_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szUserID = new byte[MAX_COMMON_STRING_32]; // 用户ID

        public NET_IN_USERINFO_START_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartFindUserInfo 输出参数
    public static class NET_OUT_USERINFO_START_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTotalCount;                          // 符合查询条件的总数
        public int              nCapNum;                              // doFind一次查询的最大数量

        public NET_OUT_USERINFO_START_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindUserInfo 输入参数
    public static class NET_IN_USERINFO_DO_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nStartNo;                             // 起始序号
        public int              nCount;                               // 本次查询的条数

        public NET_IN_USERINFO_DO_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindUserInfo 输出参数
    public static class NET_OUT_USERINFO_DO_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nRetNum;                              // 本次查询到的个数
        public Pointer          pstuInfo;                             // 查询结果, 对应 NET_ACCESS_USER_INFO数组, 用户分配释放内存,大小为sizeof(NET_ACCESS_USER_INFO)*nMaxNum
        public int              nMaxNum;                              // 用户分配内存的个数
        public byte[]           byReserved = new byte[4];

        public NET_OUT_USERINFO_DO_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartFindCardInfo 输入参数
    public static class NET_IN_CARDINFO_START_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szUserID = new byte[32];              // 用户ID
        public byte[]           szCardNo = new byte[32];              // 卡号
        public int              emType;                               // 卡类型,只支持一般卡、胁迫卡和母卡, 参考  NET_ACCESSCTLCARD_TYPE
        public int              bUserIDEx;                            //szUserIDEx 是否有效，为TRUE时，使用szUserIDEx字段
        public byte[]           szAlign = new byte[7];                //字节对齐
        public byte[]           szUserIDEx = new byte[33];            //用户ID扩展，当前只支持32位有效值下发

        public NET_IN_CARDINFO_START_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_StartFindCardInfo 输出参数
    public static class NET_OUT_CARDINFO_START_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nTotalCount;                          // 符合查询条件的总数
        public int              nCapNum;                              // CLIENT_DoFindCardInfo接口一次查询的最大数量

        public NET_OUT_CARDINFO_START_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindCardInfo 输入参数
    public static class NET_IN_CARDINFO_DO_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nStartNo;                             // 起始序号
        public int              nCount;                               // 本次查询的条数

        public NET_IN_CARDINFO_DO_FIND() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindCardInfo 输出参数
    public static class NET_OUT_CARDINFO_DO_FIND extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nRetNum;                              // 本次查询到的个数
        public Pointer          pstuInfo;                             // 查询结果, 对应 NET_ACCESS_CARD_INFO数组, 用户分配释放内存,大小为sizeof(NET_FACEINFO)*nMaxNum
        public int              nMaxNum;                              // 用户分配内存的个数
        public byte[]           byReserved = new byte[4];             // 保留字节

        public NET_OUT_CARDINFO_DO_FIND() {
            this.dwSize = this.size();
        }
    }

    // 回放数据回调结构体
    public static class NET_DATA_CALL_BACK_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              dwDataType;                           // 数据类型
        public Pointer          pBuffer;                              // 数据
        public int              dwBufSize;                            // 数据长度
        public NET_DATA_CALL_BACK_TIME stuTime;                       // 时间戳
        public int              emFramType;                           // 帧类型,EM_DATA_CALL_BACK_FRAM_TYPE
        public int              emFramSubType;                        // 帧子类型,EM_DATA_CALL_BACK_FRAM_SUB_TYPE

        public NET_DATA_CALL_BACK_INFO() {
            this.dwSize = this.size();
        }
    }

    // 回调数据时间信息
    public static class NET_DATA_CALL_BACK_TIME extends SdkStructure
    {
        public int              dwYear;                               // 年
        public int              dwMonth;                              // 月
        public int              dwDay;                                // 日
        public int              dwHour;                               // 时
        public int              dwMinute;                             // 分
        public int              dwSecond;                             // 秒
        public int              dwMillisecond;                        // 毫秒
        public int              dwPTS;                                // pts 时间戳
        public int              dwDTS;                                // dts 时间戳
        public int[]            dwReserved = new int[3];              // 预留字段
    }

    // 升级类型
    public static class EM_UPGRADE_TYPE extends SdkStructure
    {
        public static final int   DH_UPGRADE_BIOS_TYPE = 1;             // BIOS升级
        public static final int   DH_UPGRADE_WEB_TYPE = 2;              // WEB升级
        public static final int   DH_UPGRADE_BOOT_YPE = 3;              // BOOT升级
        public static final int   DH_UPGRADE_CHARACTER_TYPE = 4;        // 汉字库
        public static final int   DH_UPGRADE_LOGO_TYPE = 5;             // LOGO
        public static final int   DH_UPGRADE_EXE_TYPE = 6;              // EXE,例如播放器等
        public static final int   DH_UPGRADE_DEVCONSTINFO_TYPE = 7;     // 设备固有信息设置(如：硬件ID,MAC,序列号)
        public static final int   DH_UPGRADE_PERIPHERAL_TYPE = 8;       // 外设接入从片(如车载287芯片)
        public static final int   DH_UPGRADE_GEOINFO_TYPE = 9;          // 地理信息定位芯片
        public static final int   DH_UPGRADE_MENU = 10;                 // 菜单（设备操作界面的图片）
        public static final int   DH_UPGRADE_ROUTE = 11;                // 线路文件（如公交线路）
        public static final int   DH_UPGRADE_ROUTE_STATE_AUTO = 12;     // 报站音频（与线路配套的报站音频）
        public static final int   DH_UPGRADE_SCREEN = 13;               // 调度屏（如公交操作屏）
    }

    /***********************************************************************
     ** 回调
     ***********************************************************************/
    //JNA Callback方法定义,断线回调
    public interface fDisConnect extends Callback {
        public void invoke(LLong lLoginID,String pchDVRIP,int nDVRPort,Pointer dwUser);
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
        public int invoke(LLong lRealHandle,int dwDataType,Pointer pBuffer,int dwBufSize,Pointer dwUser);
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
    public boolean CLIENT_Init(Callback cbDisConnect,Pointer dwUser);

    //  JNA直接调用方法定义，SDK退出清理
    public void CLIENT_Cleanup();

    //  JNA直接调用方法定义，设置断线重连成功回调函数，设置后SDK内部断线自动重连, fHaveReConnect 回调
    public void CLIENT_SetAutoReconnect(Callback cbAutoConnect,Pointer dwUser);

    // 返回函数执行失败代码
    public int CLIENT_GetLastError();

    // 设置连接设备超时时间和尝试次数
    public void CLIENT_SetConnectTime(int nWaitTime,int nTryTimes);

    // 设置登陆网络环境
    public void CLIENT_SetNetworkParam(NET_PARAM pNetParam);

    //
    public boolean CLIENT_SetDeviceSearchParam(NET_DEVICE_SEARCH_PARAM pstParam);

    // 获取SDK的版本信息
    public int CLIENT_GetSDKVersion();

    //  JNA直接调用方法定义，登陆接口
    public LLong CLIENT_LoginEx(String pchDVRIP,int wDVRPort,String pchUserName,String pchPassword,int nSpecCap,Pointer pCapParam,NET_DEVICEINFO lpDeviceInfo,IntByReference error/*= 0*/);

    //  JNA直接调用方法定义，登陆扩展接口///////////////////////////////////////////////////
    //  nSpecCap 对应  EM_LOGIN_SPAC_CAP_TYPE 登陆类型
    public LLong CLIENT_LoginEx2(String pchDVRIP,int wDVRPort,String pchUserName,String pchPassword,int nSpecCap,Pointer pCapParam,NET_DEVICEINFO_Ex lpDeviceInfo,IntByReference error/*= 0*/);

    //  JNA直接调用方法定义，向设备注销
    public boolean CLIENT_Logout(LLong lLoginID);

    // 获取配置
    // error 为设备返回的错误码： 0-成功 1-失败 2-数据不合法 3-暂时无法设置 4-没有权限
    public boolean CLIENT_GetNewDevConfig(LLong lLoginID,String szCommand,int nChannelID,byte[] szOutBuffer,int dwOutBufferSize,IntByReference error,int waiitime,Pointer pReserved);

    // 设置配置
    public boolean CLIENT_SetNewDevConfig(LLong lLoginID,String szCommand,int nChannelID,byte[] szInBuffer,int dwInBufferSize,IntByReference error,IntByReference restart,int waittime);

    // 删除配置接口(Json格式)
    public boolean CLIENT_DeleteDevConfig(LLong lLoginID,NET_IN_DELETECFG pInParam,NET_OUT_DELETECFG pOutParam,int waittime);

    // 获取配置成员名称接口(Json格式)(pInParam, pOutParam内存由用户申请释放)
    public boolean CLIENT_GetMemberNames(LLong lLoginID,NET_IN_MEMBERNAME pInParam,NET_OUT_MEMBERNAME pOutParam,int waittime);

    // 解析查询到的配置信息
    public boolean CLIENT_ParseData(String szCommand,byte[] szInBuffer,Pointer lpOutBuffer,int dwOutBufferSize,Pointer pReserved);

    // 组成要设置的配置信息
    public boolean CLIENT_PacketData(String szCommand,Pointer lpInBuffer,int dwInBufferSize,byte[] szOutBuffer,int dwOutBufferSize);

    // 设置报警回调函数, fMessCallBack 回调
    public void CLIENT_SetDVRMessCallBack(Callback cbMessage,Pointer dwUser);

    // 设置报警回调函数, fMessCallBackEx1 回调
    public void CLIENT_SetDVRMessCallBackEx1(fMessCallBackEx1 cbMessage,Pointer dwUser);

    // 向设备订阅报警--扩展
    public boolean CLIENT_StartListenEx(LLong lLoginID);

    // 停止订阅报警
    public boolean CLIENT_StopListen(LLong lLoginID);

    /////////////////////////////////目标识别接口/////////////////////////////////////////
    //目标识别数据库信息操作（包括添加,修改和删除）
    // pstInParam指向NET_IN_OPERATE_FACERECONGNITIONDB类型的指针
    // pstOutParam指向NET_OUT_OPERATE_FACERECONGNITIONDB类型的指针
    public boolean CLIENT_OperateFaceRecognitionDB(LLong lLoginID,NET_IN_OPERATE_FACERECONGNITIONDB pstInParam,NET_OUT_OPERATE_FACERECONGNITIONDB pstOutParam,int nWaitTime);

    // 按条件查询目标识别结果
    // pstInParam指向NET_IN_STARTFIND_FACERECONGNITION类型的指针
    // pstOutParam指向NET_OUT_STARTFIND_FACERECONGNITION类型的指针
    public boolean CLIENT_StartFindFaceRecognition(LLong lLoginID,NET_IN_STARTFIND_FACERECONGNITION pstInParam,NET_OUT_STARTFIND_FACERECONGNITION pstOutParam,int nWaitTime);

    // 查找目标识别结果:nFilecount:需要查询的条数, 返回值为媒体文件条数 返回值<nFilecount则相应时间段内的文件查询完毕(每次最多只能查询20条记录)
    // pstInParam指向NET_IN_DOFIND_FACERECONGNITION类型的指针
    // pstOutParam指向NET_OUT_DOFIND_FACERECONGNITION类型的指针
    public boolean CLIENT_DoFindFaceRecognition(final NET_IN_DOFIND_FACERECONGNITION pstInParam,NET_OUT_DOFIND_FACERECONGNITION pstOutParam,int nWaitTime);

    //结束查询
    public boolean CLIENT_StopFindFaceRecognition(LLong lFindHandle);

    // 目标检测(输入一张大图,输入大图中被检测出来的人脸图片)
    // pstInParam指向NET_IN_DETECT_FACE类型的指针
    // pstOutParam指向NET_OUT_DETECT_FACE类型的指针
    public boolean CLIENT_DetectFace(LLong lLoginID,NET_IN_DETECT_FACE pstInParam,NET_OUT_DETECT_FACE pstOutParam,int nWaitTime);

    // 目标识别人员组操作（包括添加,修改和删除）
    // pstInParam指向NET_IN_OPERATE_FACERECONGNITION_GROUP类型的指针
    // pstOutParam指向NET_OUT_OPERATE_FACERECONGNITION_GROUP类型的指针
    public boolean CLIENT_OperateFaceRecognitionGroup(LLong lLoginID,NET_IN_OPERATE_FACERECONGNITION_GROUP pstInParam,NET_OUT_OPERATE_FACERECONGNITION_GROUP pstOutParam,int nWaitTime);

    // 查询目标识别人员组信息
    // pstInParam指向NET_IN_FIND_GROUP_INFO类型的指针
    // pstOutParam指向NET_OUT_FIND_GROUP_INFO类型的指针
    public boolean CLIENT_FindGroupInfo(LLong LLong,NET_IN_FIND_GROUP_INFO pstInParam,NET_OUT_FIND_GROUP_INFO pstOutParam,int nWaitTime);

    // 获取布控在视频通道的组信息,pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_GetGroupInfoForChannel(LLong lLoginID,NET_IN_GET_GROUPINFO_FOR_CHANNEL pstInParam,NET_OUT_GET_GROUPINFO_FOR_CHANNEL pstOutParam,int nWaitTime);

    // 布控通道人员组信息
    // pstInParam指向NET_IN_SET_GROUPINFO_FOR_CHANNEL类型的指针
    // pstOutParam指向NET_OUT_SET_GROUPINFO_FOR_CHANNEL类型的指针
    public boolean CLIENT_SetGroupInfoForChannel(LLong lLoginID,NET_IN_SET_GROUPINFO_FOR_CHANNEL pstInParam,NET_OUT_SET_GROUPINFO_FOR_CHANNEL pstOutParam,int nWaitTime);

    // 以人脸库的角度进行布控, pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_FaceRecognitionPutDisposition(LLong lLoginID,NET_IN_FACE_RECOGNITION_PUT_DISPOSITION_INFO pstInParam,NET_OUT_FACE_RECOGNITION_PUT_DISPOSITION_INFO pstOutParam,int nWaitTime);

    // 以人脸库的角度进行撤控, pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_FaceRecognitionDelDisposition(LLong lLoginID,NET_IN_FACE_RECOGNITION_DEL_DISPOSITION_INFO pstInParam,NET_OUT_FACE_RECOGNITION_DEL_DISPOSITION_INFO pstOutParam,int nWaitTime);

    // 订阅人脸查询状态
    // pstInParam指向NET_IN_FACE_FIND_STATE类型的指针
    // pstOutParam指向NET_OUT_FACE_FIND_STATE类型的指针
    public LLong CLIENT_AttachFaceFindState(LLong lLoginID,NET_IN_FACE_FIND_STATE pstInParam,NET_OUT_FACE_FIND_STATE pstOutParam,int nWaitTime);

    //取消订阅人脸查询状态,lAttachHandle为CLIENT_AttachFaceFindState返回的句柄
    public boolean CLIENT_DetachFaceFindState(LLong lAttachHandle);

    // 文件下载, 只适用于小文件,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_DownloadRemoteFile(LLong lLoginID,NET_IN_DOWNLOAD_REMOTE_FILE pInParam,NET_OUT_DOWNLOAD_REMOTE_FILE pOutParam,int nWaitTime);

    // 打开日志功能
    // pstLogPrintInfo指向LOG_SET_PRINT_INFO的指针
    public boolean CLIENT_LogOpen(LOG_SET_PRINT_INFO pstLogPrintInfo);

    // 关闭日志功能
    public boolean CLIENT_LogClose();

    // 获取符合查询条件的文件总数
    // reserved为void *
    public boolean CLIENT_GetTotalFileCount(LLong lFindHandle,IntByReference pTotalCount,Pointer reserved,int waittime);

    // 设置查询跳转条件
    // reserved为void *
    public boolean CLIENT_SetFindingJumpOption(LLong lFindHandle,NET_FINDING_JUMP_OPTION_INFO pOption,Pointer reserved,int waittime);

    // 按查询条件查询文件
    // pQueryCondition为void *, 具体类型根据emType的类型确定,对应 EM_FILE_QUERY_TYPE
    // reserved为void *, 具体类型根据emType的类型确定
    public LLong CLIENT_FindFileEx(LLong lLoginID,int emType,Pointer pQueryCondition,Pointer reserved,int waittime);

    // 查找文件:nFilecount:需要查询的条数, 返回值为媒体文件条数 返回值<nFilecount则相应时间段内的文件查询完毕
    // pMediaFileInfo为void *
    // reserved为void *
    public int CLIENT_FindNextFileEx(LLong lFindHandle,int nFilecount,Pointer pMediaFileInfo,int maxlen,Pointer reserved,int waittime);

    // 结束录像文件查找
    public boolean CLIENT_FindCloseEx(LLong lFindHandle);

    // 实时上传智能分析数据－图片(扩展接口,bNeedPicFile表示是否订阅图片文件,Reserved类型为RESERVED_PARA)
    // bNeedPicFile为BOOL类型，取值范围为0或者1, fAnalyzerDataCallBack回调
    public LLong CLIENT_RealLoadPictureEx(LLong lLoginID,int nChannelID,int dwAlarmType,int bNeedPicFile,Callback cbAnalyzerData,Pointer dwUser,Pointer Reserved);

    // 停止上传智能分析数据－图片
    public boolean CLIENT_StopLoadPic(LLong lAnalyzerHandle);

    // 设置抓图回调函数, fSnapRev回调
    public void CLIENT_SetSnapRevCallBack(Callback OnSnapRevMessage,Pointer dwUser);

    // 抓图请求扩展接口
    public boolean CLIENT_SnapPictureEx(LLong lLoginID,SNAP_PARAMS stParam,IntByReference reserved);

    // 异步搜索局域网内IPC、NVS等设备, fSearchDevicesCB回调
    public LLong CLIENT_StartSearchDevices(Callback cbSearchDevices,Pointer pUserData,String szLocalIp);

    // 停止异步搜索局域网内IPC、NVS等设备
    public boolean CLIENT_StopSearchDevices(LLong lSearchHandle);

    // 同步跨网段搜索设备IP (pIpSearchInfo内存由用户申请释放)DEVICE_IP_SEARCH_INFO
    // szLocalIp为本地IP，可不做输入, fSearchDevicesCB回调
    // 接口调用1次只发送搜索信令1次
    public boolean CLIENT_SearchDevicesByIPs(Pointer pIpSearchInfo,Callback cbSearchDevices,Pointer dwUserData,String szLocalIp,int dwWaitTime);

    // 开始实时预览
    // rType  : NET_RealPlayType    返回监控句柄
    public LLong CLIENT_RealPlayEx(LLong lLoginID,int nChannelID,Pointer hWnd,int rType);

    // 停止实时预览--扩展     lRealHandle为CLIENT_RealPlayEx的返回值
    public boolean CLIENT_StopRealPlayEx(LLong lRealHandle);

    // 开始实时预览支持设置码流回调接口     rType  : NET_RealPlayType   返回监控句柄
    // cbRealData 对应 fRealDataCallBackEx 回调
    // cbDisconnect 对应 fRealPlayDisConnect 回调
    public LLong CLIENT_StartRealPlay(
    LLong lLoginID,int nChannelID,
    Pointer hWnd,int rType,Callback cbRealData,Callback cbDisconnect,Pointer dwUser,int dwWaitTime);

    // 停止实时预览
    public boolean CLIENT_StopRealPlay(LLong lRealHandle);

    // 设置实时预览数据回调函数扩展接口    lRealHandle监控句柄,fRealDataCallBackEx 回调
    public boolean CLIENT_SetRealDataCallBackEx(LLong lRealHandle,Callback cbRealData,Pointer dwUser,int dwFlag);

    // 设置图象流畅性
    // 将要调整图象的等级(0-6),当level为0时，图象最流畅；当level为6时，图象最实时。Level的默认值为3。注意：直接解码下有效
    public boolean CLIENT_AdjustFluency(LLong lRealHandle,int nLevel);

    // 保存数据为文件,lRealHandle为CLIENT_RealPlayEx的返回值,pchFileName为实时预览保存文件名
    public boolean CLIENT_SaveRealData(LLong lRealHandle,String pchFileName);

    // 结束保存数据为文件,lRealHandle为CLIENT_RealPlayEx的返回值
    public boolean CLIENT_StopSaveRealData(LLong lRealHandle);

    // 打开声音
    public boolean CLIENT_OpenSound(LLong hPlayHandle);

    // 关闭声音
    public boolean CLIENT_CloseSound();

    // 设置显示源(pInparam, pOutParam内存由用户申请释放)
    public boolean CLIENT_MatrixSetCameras(LLong lLoginID,NET_IN_MATRIX_SET_CAMERAS pInParam,NET_OUT_MATRIX_SET_CAMERAS pOutParam,int nWaitTime);

    // 获取所有有效显示源
    // pInParam  对应  NET_IN_MATRIX_GET_CAMERAS
    // pOutParam 对应  NET_OUT_MATRIX_GET_CAMERAS
    public boolean CLIENT_MatrixGetCameras(LLong lLoginID,NET_IN_MATRIX_GET_CAMERAS pInParam,NET_OUT_MATRIX_GET_CAMERAS pOutParam,int nWaitTime);

    // 抓图同步接口,将图片数据直接返回给用户
   // public boolean CLIENT_SnapPictureToFile(LLong lLoginID, NET_IN_SNAP_PIC_TO_FILE_PARAM pInParam, NET_OUT_SNAP_PIC_TO_FILE_PARAM pOutParam, int nWaitTime);
    public boolean CLIENT_SnapPictureToFile(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 查询时间段内的所有录像文件
    // nRecordFileType 录像类型 0:所有录像  1:外部报警  2:动态监测报警  3:所有报警  4:卡号查询   5:组合条件查询   6:录像位置与偏移量长度   8:按卡号查询图片(目前仅HB-U和NVS特殊型号的设备支持)  9:查询图片(目前仅HB-U和NVS特殊型号的设备支持)  10:按字段查询    15:返回网络数据结构(金桥网吧)  16:查询所有透明串数据录像文件
    // nriFileinfo 返回的录像文件信息，是一个 NET_RECORDFILE_INFO 结构数组
    // maxlen 是 nriFileinfo缓冲的最大长度(单位字节，建议在(100~200)*sizeof(NET_RECORDFILE_INFO)之间)
    // filecount返回的文件个数，属于输出参数最大只能查到缓冲满为止的录像记录;
    // bTime 是否按时间查(目前无效)
    public boolean CLIENT_QueryRecordFile(LLong lLoginID,int nChannelId,int nRecordFileType,NET_TIME tmStart,NET_TIME tmEnd,String pchCardid,NET_RECORDFILE_INFO[] stFileInfo,int maxlen,IntByReference filecount,int waittime,boolean bTime);

    // NET_RECORDFILE_INFO[] stFileInfo Pointer 版本
    public boolean CLIENT_QueryRecordFile(LLong lLoginID,int nChannelId,int nRecordFileType,NET_TIME tmStart,NET_TIME tmEnd,String pchCardid,Pointer pFileInfo,int maxlen,IntByReference filecount,int waittime,boolean bTime);

    // 查询时间段内是否有录像文件   bResult输出参数，true有录像，false没录像
    public boolean CLIENT_QueryRecordTime(LLong lLoginID,int nChannelId,int nRecordFileType,NET_TIME tmStart,NET_TIME tmEnd,String pchCardid,IntByReference bResult,int waittime);

    // 通过时间下载录像--扩展
    // nRecordFileType 对应 EM_QUERY_RECORD_TYPE
    // cbTimeDownLoadPos 对应 fTimeDownLoadPosCallBack 回调
    // fDownLoadDataCallBack 对应 fDataCallBack 回调
    public LLong CLIENT_DownloadByTimeEx(LLong lLoginID,int nChannelId,int nRecordFileType,NET_TIME tmStart,NET_TIME tmEnd,String sSavedFileName,Callback cbTimeDownLoadPos,Pointer dwUserData,Callback fDownLoadDataCallBack,Pointer dwDataUser,Pointer pReserved);

    // 停止录像下载
    public boolean CLIENT_StopDownload(LLong lFileHandle);

    /******************************************************************************
     功能描述	:	通过时间下载录像--扩展,可加载码流转换库
     输入参数	:
     lLoginID:       登录接口返回的句柄
     nChannelId:     视频通道号,从0开始
     nRecordFileType:录像类型 0 所有录像文件
     1 外部报警
     2 动态检测报警
     3 所有报警
     4 卡号查询
     5 组合条件查询
     6 录像位置与偏移量长度
     8 按卡号查询图片(目前仅HB-U和NVS特殊型号的设备支持)
     9 查询图片(目前仅HB-U和NVS特殊型号的设备支持)
     10 按字段查询
     15 返回网络数据结构(金桥网吧)
     16 查询所有透明串数据录像文件
     tmStart:        开始时间
     tmEnd:          结束时间
     sSavedFileName: 保存录像文件名,支持全路径
     cbTimeDownLoadPos: 下载进度回调函数(回调下载进度,下载结果), 对应回调   fTimeDownLoadPosCallBack
     dwUserData:     下载进度回调对应用户数据
     fDownLoadDataCallBack: 录像数据回调函数(回调形式暂不支持转换PS流)，对应回调  fDataCallBack
     dwDataUser:     录像数据回调对应用户数据
     scType:         码流转换类型,0-DAV码流(默认); 1-PS流,3-MP4
     pReserved:      保留参数,后续扩展
     输出参数	：	N/A
     返 回 值	：	LLONG 下载录像句柄
     其他说明	：	特殊接口,SDK默认不支持转PS流,需SDK
     ******************************************************************************/
    public LLong CLIENT_DownloadByTimeEx2(LLong lLoginID,int nChannelId,int nRecordFileType,NET_TIME tmStart,NET_TIME tmEnd,String sSavedFileName,Callback cbTimeDownLoadPos,Pointer dwUserData,Callback fDownLoadDataCallBack,Pointer dwDataUser,int scType,Pointer pReserved);

    /**
     * 下载录像文件--扩展
     * sSavedFileName 不为空, 录像数据写入到该路径对应的文件; fDownLoadDataCallBack不为空, 录像数据通过回调函数返回
     * pReserved 指加密录像的密码（长度不小于 8 的字符串）
     *
     * @param lLoginID 登录句柄
     * @param lpRecordFile 录像信息
     * @param sSavedFileName 本地保存路径 如果要保存到本地则必填
     * @param cbDownLoadPos 下载进度回调 建议使用 可以在下载完成时调结束下载接口
     * @param dwUserData 下载进度回调对应用户数据 不建议使用 直接填 null
     * @param fDownLoadDataCallBack 下载数据回调 不为 null 则录像数据
     * @param dwDataUser 录像数据回调对应用户数据 不建议使用 直接填 null
     * @param pReserved pReserved 指加密录像的密码（长度不小于 8 的字符串） 没有的话填 null
     * @return 录像下载句柄fDownLoadDataCallBack
     */
    public LLong CLIENT_DownloadByRecordFileEx(LLong lLoginID,LPNET_RECORDFILE_INFO lpRecordFile,Pointer sSavedFileName,Callback cbDownLoadPos,Pointer dwUserData,Callback fDownLoadDataCallBack,Pointer dwDataUser,Pointer pReserved);

    // 下载录像文件--扩展 重载接口
    // lpRecordFile 类型 替换为 NET_RECORDFILE_INFO
    public LLong CLIENT_DownloadByRecordFileEx(LLong lLoginID,NET_RECORDFILE_INFO lpRecordFile,Pointer sSavedFileName,Callback cbDownLoadPos,Pointer dwUserData,Callback fDownLoadDataCallBack,Pointer dwDataUser,Pointer pReserved);

    // 自适应速度的按文件下载录像, pstInParam 和 pstOutParam 资源由用户申请和释放
    // pstInParam->NET_IN_DOWNLOAD_BYFILE_SELFADAPT
    // pstOutParam->NET_OUT_DOWNLOAD_BYFILE_SELFADAPT
    public LLong CLIENT_DownloadByFileSelfAdapt(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int dwWaitTime);

    // 自适应速度的按时间下载录像
    // pstInParam->NET_IN_ADAPTIVE_DOWNLOAD_BY_TIME
    // pstOutParam->NET_OUT_ADAPTIVE_DOWNLOAD_BY_TIME
    public LLong CLIENT_AdaptiveDownloadByTime(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int dwWaitTime);

    // 私有云台控制扩展接口,支持三维快速定位
    public boolean CLIENT_DHPTZControlEx(LLong lLoginID,int nChannelID,int dwPTZCommand,int lParam1,int lParam2,int lParam3,int dwStop);

    // 云台控制扩展接口,支持三维快速定位,鱼眼
    // dwStop类型为BOOL, 取值0或者1
    // dwPTZCommand取值为NET_EXTPTZ_ControlType中的值或者是NET_PTZ_ControlType中的值
    // NET_IN_PTZBASE_MOVEABSOLUTELY_INFO
    // 精准绝对移动控制命令, param4对应结构 NET_IN_PTZBASE_MOVEABSOLUTELY_INFO（通过 CFG_CAP_CMD_PTZ 命令获取云台能力集( CFG_PTZ_PROTOCOL_CAPS_INFO )，若bSupportReal为TRUE则设备支持该操作）
    public boolean CLIENT_DHPTZControlEx2(LLong lLoginID,int nChannelID,int dwPTZCommand,int lParam1,int lParam2,int lParam3,int dwStop,Pointer param4);

    // 设备控制(param内存由用户申请释放)  emType对应 枚举 CtrlType
    public boolean CLIENT_ControlDevice(LLong lLoginID,int emType,Pointer param,int waittime);

    // 设备控制扩展接口，兼容 CLIENT_ControlDevice (pInBuf, pOutBuf内存由用户申请释放)
    // emType的取值为CtrlType中的值
    public boolean CLIENT_ControlDeviceEx(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    // 查询配置信息(lpOutBuffer内存由用户申请释放)
    public boolean CLIENT_GetDevConfig(LLong lLoginID,int dwCommand,int lChannel,Pointer lpOutBuffer,int dwOutBufferSize,IntByReference lpBytesReturned,int waittime);

    // 设置配置信息(lpInBuffer内存由用户申请释放)
    public boolean CLIENT_SetDevConfig(LLong lLoginID,int dwCommand,int lChannel,Pointer lpInBuffer,int dwInBufferSize,int waittime);

    // 查询设备状态(pBuf内存由用户申请释放)
    // pBuf指向char *,输出参数
    // pRetLen指向int *;输出参数，实际返回的数据长度，单位字节
    public boolean CLIENT_QueryDevState(LLong lLoginID,int nType,Pointer pBuf,int nBufLen,IntByReference pRetLen,int waittime);

    // 查询远程设备状态(pBuf内存由用户申请释放)
    // nType为DH_DEVSTATE_ALARM_FRONTDISCONNECT时，通道号从1开始
    public boolean CLIENT_QueryRemotDevState(LLong lLoginID,int nType,int nChannelID,Pointer pBuf,int nBufLen,IntByReference pRetLen,int waittime);

    // 获取设备能力接口
    // pInBuf指向void*，输入参数结构体指针       pOutBuf指向void*，输出参数结构体指针
    public boolean CLIENT_GetDevCaps(LLong lLoginID,int nType,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    // 新系统能力查询接口，查询系统能力信息(以Json格式，具体见配置SDK)(szOutBuffer内存由用户申请释放)
    // szCommand: 对应命令查看上文
    // szOutBuffer: 获取到的信息, 通过 CLIENT_ParseData 解析
    // error 指向 int * ： 错误码大于0表示设备返回的，小于0表示缓冲不够或数据校验引起的
    public boolean CLIENT_QueryNewSystemInfo(LLong lLoginID,String szCommand,int nChannelID,byte[] szOutBuffer,int dwOutBufferSize,IntByReference error,int waittime);

    // 新系统能力查询接口扩展，查询系统能力信息，入参新增扩展数据(以Json格式，具体见配置SDK)(szOutBuffer内存由用户申请释放)
    public boolean CLIENT_QueryNewSystemInfoEx(LLong lLoginID,String szCommand,int nChannelID,byte[] szOutBuffer,int dwOutBufferSize,IntByReference error,Pointer pExtendInfo,int waittime);

    // 查询系统能力信息(pSysInfoBuffer内存由用户申请释放，大小参照DH_SYS_ABILITY对应的结构体，若nSystemType为 ABILITY_DYNAMIC_CONNECT ，内存大小至少为sizeof(BOOL),若nSystemType为ABILITY_TRIGGER_MODE，内存大小为sizeof(int))
    public boolean CLIENT_QuerySystemInfo(LLong lLoginID,int nSystemType,String pSysInfoBuffer,int nChannelID,byte[] szOutBuffer,int dwOutBufferSize,IntByReference error,int waittime);

    // 订阅视频统计摘要信息
    public LLong CLIENT_AttachVideoStatSummary(LLong lLoginID,NET_IN_ATTACH_VIDEOSTAT_SUM pInParam,NET_OUT_ATTACH_VIDEOSTAT_SUM pOutParam,int nWaitTime);

    // 取消订阅视频统计摘要信息，lAttachHandle为CLIENT_AttachVideoStatSummary的返回值
    public boolean CLIENT_DetachVideoStatSummary(LLong lAttachHandle);

    // 开始查询视频统计信息/获取人数统计信息
    public LLong CLIENT_StartFindNumberStat(LLong lLoginID,NET_IN_FINDNUMBERSTAT pstInParam,NET_OUT_FINDNUMBERSTAT pstOutParam);

    // 继续查询视频统计/继续查询人数统计
    public int CLIENT_DoFindNumberStat(LLong lFindHandle,NET_IN_DOFINDNUMBERSTAT pstInParam,NET_OUT_DOFINDNUMBERSTAT pstOutParam);

    // 结束查询视频统计/结束查询人数统计
    public boolean CLIENT_StopFindNumberStat(LLong lFindHandle);

    // 设置语音对讲模式,客户端方式还是服务器方式
    // emType : 方式类型 参照 EM_USEDEV_MODE
    public boolean CLIENT_SetDeviceMode(LLong lLoginID,int emType,Pointer pValue);

    ///////////////// 录像回放相关接口 ///////////////////////
    // 按时间方式回放
    public LLong CLIENT_PlayBackByTime(LLong lLoginID,int nChannelID,NET_TIME lpStartTime,NET_TIME lpStopTime,Pointer hWnd,fDownLoadPosCallBack cbDownLoadPos,Pointer dwPosUser);

    // 按时间方式回放--扩展接口
    // cbDownLoadPos 对应 fDownLoadPosCallBack 回调
    // fDownLoadDataCallBack 对应 fDataCallBack 回调
    public LLong CLIENT_PlayBackByTimeEx(LLong lLoginID,int nChannelID,NET_TIME lpStartTime,NET_TIME lpStopTime,Pointer hWnd,Callback cbDownLoadPos,Pointer dwPosUser,Callback fDownLoadDataCallBack,Pointer dwDataUser);

    public LLong CLIENT_PlayBackByTimeEx2(LLong lLoginID,int nChannelID,NET_IN_PLAY_BACK_BY_TIME_INFO pstNetIn,NET_OUT_PLAY_BACK_BY_TIME_INFO pstNetOut);

    // 停止录像回放接口
    public boolean CLIENT_StopPlayBack(LLong lPlayHandle);

    // 获取回放OSD时间
    public boolean CLIENT_GetPlayBackOsdTime(LLong lPlayHandle,NET_TIME lpOsdTime,NET_TIME lpStartTime,NET_TIME lpEndTime);

    // 暂停或恢复录像回放
    // bPause: 1 - 暂停	0 - 恢复
    public boolean CLIENT_PausePlayBack(LLong lPlayHandle,int bPause);

    // 快进录像回放
    public boolean CLIENT_FastPlayBack(LLong lPlayHandle);

    // 慢进录像回放
    public boolean CLIENT_SlowPlayBack(LLong lPlayHandle);

    // 恢复正常回放速度
    public boolean CLIENT_NormalPlayBack(LLong lPlayHandle);

    // 设置录像回放速度, emSpeed 对应枚举 EM_PLAY_BACK_SPEED
    public boolean CLIENT_SetPlayBackSpeed(LLong lPlayHandle,int emSpeed);

    // 查询设备当前时间
    public boolean CLIENT_QueryDeviceTime(LLong lLoginID,NET_TIME pDeviceTime,int waittime);

    // 设置设备当前时间
    public boolean CLIENT_SetupDeviceTime(LLong lLoginID,NET_TIME pDeviceTime);

    // 获得亮度、色度、对比度、饱和度的参数
    // param1/param2/param3/param4 四个参数范围0~255
    public boolean CLIENT_ClientGetVideoEffect(LLong lPlayHandle,byte[] nBrightness,byte[] nContrast,byte[] nHue,byte[] nSaturation);

    // 设置亮度、色度、对比度、饱和度的参数
    // nBrightness/nContrast/nHue/nSaturation四个参数为 unsigned byte 范围0~255
    public boolean CLIENT_ClientSetVideoEffect(LLong lPlayHandle,byte nBrightness,byte nContrast,byte nHue,byte nSaturation);

    //------------------------用户管理-----------------------
    // 查询用户信息--扩展(info内存由用户申请释放,大小为sizeof(USER_MANAGE_INFO_EX))
    public boolean CLIENT_QueryUserInfoEx(LLong lLoginID,USER_MANAGE_INFO_EX info,int waittime);

    // 查询用户信息--最大支持64通道设备
    // pReserved指向void*
    public boolean CLIENT_QueryUserInfoNew(LLong lLoginID,USER_MANAGE_INFO_NEW info,Pointer pReserved,int nWaittime);

    // 设置用户信息接口--操作设备用户--最大支持64通道设备
    // opParam指向void*           subParam指向void*
    // pReserved指向void*
    // opParam（设置用户信息的输入缓冲）和subParam（设置用户信息的辅助输入缓冲）对应结构体类型USER_GROUP_INFO_NEW或USER_INFO_NEW
    public boolean CLIENT_OperateUserInfoNew(LLong lLoginID,int nOperateType,Pointer opParam,Pointer subParam,Pointer pReserved,int nWaittime);

    /**
     * // 查询设备日志，以分页方式查询(pQueryParam, pLogBuffer内存由用户申请释放)
     * CLIENT_NET_API BOOL CALL_METHOD CLIENT_QueryDeviceLog(LLONG lLoginID, QUERY_DEVICE_LOG_PARAM *pQueryParam, char *pLogBuffer, int nLogBufferLen, int *pRecLogNum, int waittime=3000);
     * pQueryParam 对应结构体QUERY_DEVICE_LOG_PARAM
     * pLogBuffer:char *
     * waitTime:默认3000
     */
    public boolean CLIENT_QueryDeviceLog(LLong lLoginID,Pointer pQueryParam,Pointer pLogBuffer,int nLogBufferLen,IntByReference pRecLogNum,int waittime);

    //----------------------语音对讲--------------------------
    // 向设备发起语音对讲请求          pfcb是用户自定义的数据回调接口, pfAudioDataCallBack 回调
    public LLong CLIENT_StartTalkEx(LLong lLoginID,Callback pfcb,Pointer dwUser);

    // 停止语音对讲        lTalkHandle语音对讲句柄，是CLIENT_StartTalkEx的返回 值
    public boolean CLIENT_StopTalkEx(LLong lTalkHandle);

    // 启动本地录音功能(只在Windows平台下有效)，录音采集出来的音频数据通过CLIENT_StartTalkEx的回调函数回调给用户，对应操作是CLIENT_RecordStopEx
    // lLoginID是CLIENT_Login的返回值
    public boolean CLIENT_RecordStartEx(LLong lLoginID);

    // 开始PC端录音
    public boolean CLIENT_RecordStart();

    // 结束PC端录音
    public boolean CLIENT_RecordStop();

    // 停止本地录音(只在Windows平台下有效)，对应操作是CLIENT_RecordStartEx。
    public boolean CLIENT_RecordStopEx(LLong lLoginID);

    // 向设备发送用户的音频数据，这里的数据可以是从CLIENT_StartTalkEx的回调接口中回调出来的数据
    public LLong CLIENT_TalkSendData(LLong lTalkHandle,Pointer pSendBuf,int dwBufSize);

    // 解码音频数据扩展接口(只在Windows平台下有效)    pAudioDataBuf是要求解码的音频数据内容
    public void CLIENT_AudioDec(Pointer pAudioDataBuf,int dwBufSize);

    public boolean CLIENT_AudioDecEx(LLong lTalkHandle,Pointer pAudioDataBuf,int dwBufSize);

    // 音频格式信息
    public class NET_AUDIO_FORMAT extends SdkStructure
    {
        public byte             byFormatTag;                          // 编码类型,如0：PCM
        public short            nChannels;                            // 声道数
        public short            wBitsPerSample;                       // 采样深度
        public int              nSamplesPerSec;                       // 采样率
    }

    // 音频格式信息
    public class LPDH_AUDIO_FORMAT extends SdkStructure
    {
        public byte             byFormatTag;                          // 编码类型,如0：PCM
        public short            nChannels;                            // 声道数
        public short            wBitsPerSample;                       // 采样深度
        public int              nSamplesPerSec;                       // 采样率
    }

    // 音频编码--初始化(特定标准格式->私有格式) 初始化对讲中的音频编码接口，告诉SDK内部要编码的源音频数据的音频格式，对不支持的音频格式初始化会失败
    public int CLIENT_InitAudioEncode(NET_AUDIO_FORMAT aft);

    // 进行音频的数据二次编码，从标准音频格式转换成设备支持的格式
    // 音频编码--数据编码(lpInBuf, lpOutBuf内存由用户申请释放)
    public int CLIENT_AudioEncode(LLong lTalkHandle,Pointer lpInBuf,IntByReference lpInLen,Pointer lpOutBuf,IntByReference lpOutLen);

    // 音频编码--完成退出  解码功能使用完毕后，告诉接口清理内部资源
    public int CLIENT_ReleaseAudioEncode();

    //----------------------语音对讲音频裸数据相关接口--------------------------
    /**
     * 打开语音对讲，这个接口可以从回调中得到音频裸数据，而CLIENT_StartTalkEx只能得到带音频头的数据
     * @param lLoginID
     * @param pInParam -> NET_IN_START_TALK_INFO
     * @param pOutParam -> NET_OUT_START_TALK_INFO
     * @param nWaittime
     * @return LLong
     */
    public LLong CLIENT_StartTalkByDataType(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaittime);

    /**
     * 发送语音数据到设备 返回值为发送给设备的音频流长度，-1表示接口调用失败
     * @param lTalkHandle
     * @param pInParam -> NET_IN_TALK_SEND_DATA_STREAM
     * @param pOutParam -> NET_OUT_TALK_SEND_DATA_STREAM
     * @return LLong 返回值为发送给设备的音频流长度，-1表示接口调用失败
     */
    public LLong CLIENT_TalkSendDataByStream(LLong lTalkHandle,Pointer pInParam,Pointer pOutParam);

    /**
     * 发送语音文件中的音频数据到设备 成功返回 lTalkHandle， 失败返回 0
     * @param lTalkHandle
     * @param pInParam -> NET_IN_TALK_SEND_DATA_FILE
     * @param pOutParam -> NET_OUT_TALK_SEND_DATA_FILE
     * @return LLong 成功返回 lTalkHandle， 失败返回 0
     */
    public LLong CLIENT_TalkSendDataByFile(LLong lTalkHandle,Pointer pInParam,Pointer pOutParam);

    /**
     * 停止发送音频文件
     */
    public boolean CLIENT_StopTalkSendDataByFile(LLong lTalkHandle);

     /**
      * 用户自定义的数据回调   lTalkHandle是CLIENT_StartTalkByDataType的返回值
      * @param stAudioInfo -> NET_AUDIO_DATA_CB_INFO
      * @param byAudioFlag -> 音频数据来源，参考枚举 EM_AUDIO_SOURCE_FLAG
      * @param dwUser -> 用户自定义数据
      */
    public interface fAudioDataCallBackEx extends Callback {
        public void invoke(LLong lTalkHandle,NET_AUDIO_DATA_CB_INFO stAudioInfo,int emAudioFlag,Pointer dwUser);
    }

    /**
     * 音频文件发送进度回调函数
     * @param lTalkHandle
     * @param dwTotalSize
     * @param dwSendSize
     */
    public interface fTalkSendPosCallBack extends Callback {
        public void invoke(LLong lTalkHandle,int dwTotalSize,int dwSendSize,Pointer dwUser);
    }

    //-------------------允许名单-------------------------
    // 按查询条件查询记录          pInParam查询记录参数        pOutParam返回查询句柄
    // 可以先调用本接口获得查询句柄，再调用  CLIENT_FindNextRecord函数获取记录列表，查询完毕可以调用CLIENT_FindRecordClose关闭查询句柄。
    public boolean CLIENT_FindRecord(LLong lLoginID,NET_IN_FIND_RECORD_PARAM pInParam,NET_OUT_FIND_RECORD_PARAM pOutParam,int waittime);

    // 查找记录:nFilecount:需要查询的条数, 返回值为媒体文件条数 返回值小于nFilecount则相应时间段内的文件查询完毕
    public boolean CLIENT_FindNextRecord(NET_IN_FIND_NEXT_RECORD_PARAM pInParam,NET_OUT_FIND_NEXT_RECORD_PARAM pOutParam,int waittime);

    // 结束记录查找,lFindHandle是CLIENT_FindRecord的返回值
    public boolean CLIENT_FindRecordClose(LLong lFindHandle);

    // 查找记录条数,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_QueryRecordCount(NET_IN_QUEYT_RECORD_COUNT_PARAM pInParam,NET_OUT_QUEYT_RECORD_COUNT_PARAM pOutParam,int waittime);

    // 禁止/允许名单操作 ,pstOutParam = null;
    public boolean CLIENT_OperateTrafficList(LLong lLoginID,NET_IN_OPERATE_TRAFFIC_LIST_RECORD pstInParam,NET_OUT_OPERATE_TRAFFIC_LIST_RECORD pstOutParam,int waittime);

    // 文件上传控制接口，允许名单上传需要三个步骤配合使用，CLIENT_FileTransmit的 NET_DEV_BLACKWHITETRANS_START、  NET_DEV_BLACKWHITETRANS_SEND、   NET_DEV_BLACKWHITETRANS_STOP，如下所示
    // fTransFileCallBack 回调
    public LLong CLIENT_FileTransmit(LLong lLoginID,int nTransType,Pointer szInBuf,int nInBufLen,Callback cbTransFile,Pointer dwUserData,int waittime);

    // 查询设备信息
    public boolean CLIENT_QueryDevInfo(LLong lLoginID,int nQueryType,Pointer pInBuf,Pointer pOutBuf,Pointer pReservedL,int nWaitTime);

    // ------------------车载GPS-------------------------
    // 设置GPS订阅回调函数--扩展, fGPSRevEx 回调
    public void CLIENT_SetSubcribeGPSCallBackEX(Callback OnGPSMessage,Pointer dwUser);

    // 设置GPS订阅回调函数--扩展2， fGPSRevEx2 回调
    public void CLIENT_SetSubcribeGPSCallBackEX2(Callback OnGPSMessage,Pointer dwUser);

    // GPS信息订阅
    // bStart:表明是订阅还是取消          InterTime:订阅时间内GPS发送频率(单位秒)
    // KeepTime:订阅持续时间(单位秒) 值为-1时,订阅时间为极大值,可视为永久订阅
    // 订阅时间内GPS发送频率(单位秒)
    public boolean CLIENT_SubcribeGPS(LLong lLoginID,int bStart,int KeepTime,int InterTime);

    // 设置文件长度, pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_PreUploadRemoteFile(LLong lLoginID,NET_IN_PRE_UPLOAD_REMOTE_FILE pInParam,NET_OUT_PRE_UPLOAD_REMOTE_FILE pOutParam,int nWaitTime);

    // 同步文件上传, 只适用于小文件
    public boolean CLIENT_UploadRemoteFile(LLong lLoginID,NET_IN_UPLOAD_REMOTE_FILE pInParam,NET_OUT_UPLOAD_REMOTE_FILE pOutParam,int nWaitTime);

    // 显示目录中文件和子目录,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_ListRemoteFile(LLong lLoginID,NET_IN_LIST_REMOTE_FILE pInParam,NET_OUT_LIST_REMOTE_FILE pOutParam,int nWaitTime);

    // 删除文件或目录,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_RemoveRemoteFiles(LLong lLoginID,NET_IN_REMOVE_REMOTE_FILES pInParam,NET_OUT_REMOVE_REMOTE_FILES pOutParam,int nWaitTime);

    // 过车记录订阅
    public LLong CLIENT_ParkingControlAttachRecord(LLong lLoginID,NET_IN_PARKING_CONTROL_PARAM pInParam,NET_OUT_PARKING_CONTROL_PARAM pOutParam,int nWaitTime);

    // 取消过车记录订阅
    public boolean CLIENT_ParkingControlDetachRecord(LLong lAttachHandle);

    // 开始过车记录查询
    public LLong CLIENT_ParkingControlStartFind(LLong lLoginID,NET_IN_PARKING_CONTROL_START_FIND_PARAM pInParam,NET_OUT_PARKING_CONTROL_START_FIND_PARAM pOutParam,int waittime);

    // 获取过车记录
    public boolean CLIENT_ParkingControlDoFind(LLong lFindeHandle,NET_IN_PARKING_CONTROL_DO_FIND_PARAM pInParam,NET_OUT_PARKING_CONTROL_DO_FIND_PARAM pOutParam,int waittime);

    // 结束过车记录查询
    public boolean CLIENT_ParkingControlStopFind(LLong lFindHandle);

    // 车位状态订阅,pInParam与pOutParam内存由用户申请释放
    public LLong CLIENT_ParkingControlAttachParkInfo(LLong lLoginID,NET_IN_PARK_INFO_PARAM pInParam,NET_OUT_PARK_INFO_PARAM pOutParam,int nWaitTime);

    // 取消车位状态订阅
    public boolean CLIENT_ParkingControlDetachParkInfo(LLong lAttachHandle);

    // 清除异常车位车辆信息 NET_IN_REMOVE_PARKING_CAR_INFO, NET_OUT_REMOVE_PARKING_CAR_INFO
    public boolean CLIENT_RemoveParkingCarInfo(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 电源控制,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_PowerControl(LLong lLoginID,NET_IN_WM_POWER_CTRL pInParam,NET_OUT_WM_POWER_CTRL pOutParam,int nWaitTime);

    // 载入/保存预案,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_LoadMonitorWallCollection(LLong lLoginID,NET_IN_WM_LOAD_COLLECTION pInParam,NET_OUT_WM_LOAD_COLLECTION pOutParam,int nWaitTime);

    public boolean CLIENT_SaveMonitorWallCollection(LLong lLoginID,NET_IN_WM_SAVE_COLLECTION pInParam,NET_OUT_WM_SAVE_COLLECTION pOutParam,int nWaitTime);

    // 获取电视墙预案,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_GetMonitorWallCollections(LLong lLoginID,NET_IN_WM_GET_COLLECTIONS pInParam,NET_OUT_WM_GET_COLLECTIONS pOutParam,int nWaitTime);

    // 查询/设置显示源(pstuSplitSrc内存由用户申请释放),  nWindow为-1表示所有窗口 ; pstuSplitSrc 对应 NET_SPLIT_SOURCE 指针
    public boolean CLIENT_GetSplitSource(LLong lLoginID,int nChannel,int nWindow,NET_SPLIT_SOURCE[] pstuSplitSrc,int nMaxCount,IntByReference pnRetCount,int nWaitTime);

    public boolean CLIENT_SetSplitSource(LLong lLoginID,int nChannel,int nWindow,NET_SPLIT_SOURCE pstuSplitSrc,int nSrcCount,int nWaitTime);

    // 设置显示源, 支持同时设置多个窗口(pInparam, pOutParam内存由用户申请释放)
    public boolean CLIENT_SplitSetMultiSource(LLong lLoginID,NET_IN_SPLIT_SET_MULTI_SOURCE pInParam,NET_OUT_SPLIT_SET_MULTI_SOURCE pOutParam,int nWaitTime);

    // 查询矩阵子卡信息(pstuCardList内存由用户申请释放)
    public boolean CLIENT_QueryMatrixCardInfo(LLong lLoginID,NET_MATRIX_CARD_LIST pstuCardList,int nWaitTime);

    // 开始查找录像文件帧信息(pInParam, pOutParam内存由用户申请释放)
    public boolean CLIENT_FindFrameInfo(LLong lLoginID,NET_IN_FIND_FRAMEINFO_PRAM pInParam,NET_OUT_FIND_FRAMEINFO_PRAM pOutParam,int nWaitTime);

    // 获取标签信息
    public boolean CLIENT_FileStreamGetTags(LLong lFindHandle,NET_IN_FILE_STREAM_GET_TAGS_INFO pInParam,NET_OUT_FILE_STREAM_GET_TAGS_INFO pOutParam,int nWaitTime);

    // 设置标签信息
    public boolean CLIENT_FileStreamSetTags(LLong lFindHandle,NET_IN_FILE_STREAM_TAGS_INFO pInParam,NET_OUT_FILE_STREAM_TAGS_INFO pOutParam,int nWaitTime);

    // 查询/设置分割模式(pstuSplitInfo内存由用户申请释放)
    public boolean CLIENT_GetSplitMode(LLong lLoginID,int nChannel,NET_SPLIT_MODE_INFO pstuSplitInfo,int nWaitTime);

    public boolean CLIENT_SetSplitMode(LLong lLoginID,int nChannel,NET_SPLIT_MODE_INFO pstuSplitInfo,int nWaitTime);

    // 开窗/关窗(pInparam, pOutParam内存由用户申请释放)
    public boolean CLIENT_OpenSplitWindow(LLong lLoginID,NET_IN_SPLIT_OPEN_WINDOW pInParam,NET_OUT_SPLIT_OPEN_WINDOW pOutParam,int nWaitTime);

    public boolean CLIENT_CloseSplitWindow(LLong lLoginID,NET_IN_SPLIT_CLOSE_WINDOW pInParam,NET_OUT_SPLIT_CLOSE_WINDOW pOutParam,int nWaitTime);

    // 获取当前显示的窗口信息(pInparam, pOutParam内存由用户申请释放)
    public boolean CLIENT_GetSplitWindowsInfo(LLong lLoginID,NET_IN_SPLIT_GET_WINDOWS pInParam,NET_OUT_SPLIT_GET_WINDOWS pOutParam,int nWaitTime);

    // 查询分割能力(pstuCaps内存由用户申请释放)
    public boolean CLIENT_GetSplitCaps(LLong lLoginID,int nChannel,NET_SPLIT_CAPS pstuCaps,int nWaitTime);

    // 下位矩阵切换(pInparam, pOutParam内存由用户申请释放)
    public boolean CLIENT_MatrixSwitch(LLong lLoginID,NET_IN_MATRIX_SWITCH pInParam,NET_OUT_MATRIX_SWITCH pOutParam,int nWaitTime);

    // 打开刻录会话, 返回刻录会话句柄,pstInParam与pstOutParam内存由用户申请释放
    public LLong CLIENT_StartBurnSession(LLong lLoginID,NET_IN_START_BURN_SESSION pstInParam,NET_OUT_START_BURN_SESSION pstOutParam,int nWaitTime);

    // 关闭刻录会话
    public boolean CLIENT_StopBurnSession(LLong lBurnSession);

    //------------有盘/无盘刻录----lBurnSession 是 CLIENT_StartBurnSession返回的句柄//
    // 开始刻录,pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_StartBurn(LLong lBurnSession,NET_IN_START_BURN pstInParam,NET_OUT_START_BURN pstOutParam,int nWaitTime);

    // 停止刻录
    public boolean CLIENT_StopBurn(LLong lBurnSession);

    // 暂停/恢复刻录
    public boolean CLIENT_PauseBurn(LLong lBurnSession,int bPause);

    // 获取刻录状态
    public boolean CLIENT_BurnGetState(LLong lBurnSession,NET_IN_BURN_GET_STATE pstInParam,NET_OUT_BURN_GET_STATE pstOutParam,int nWaitTime);

    // 监听刻录状态,pstInParam与pstOutParam内存由用户申请释放
    public LLong CLIENT_AttachBurnState(LLong lLoginID,NET_IN_ATTACH_STATE pstInParam,NET_OUT_ATTACH_STATE pstOutParam,int nWaitTime);

    // 取消监听刻录状态,lAttachHandle是CLIENT_AttachBurnState返回值
    public boolean CLIENT_DetachBurnState(LLong lAttachHandle);

    // 开始刻录附件上传
    // 刻录上传开始 返回此次上传操作句柄, 注意以下接口不能在fAttachBurnStateCB回调函数里面调用,pstInParam与pstOutParam内存由用户申请释放
    public LLong CLIENT_StartUploadFileBurned(LLong lLoginID,NET_IN_FILEBURNED_START pstInParam,NET_OUT_FILEBURNED_START pstOutParam,int nWaitTime);

    //上传刻录附件,lUploadHandle是CLIENT_StartUploadFileBurned返回值
    public boolean CLIENT_SendFileBurned(LLong lUploadHandle);

    //停止刻录附件上传
    // 刻录上传停止,lUploadHandle是CLIENT_StartUploadFileBurned返回值,此接口不能在fBurnFileCallBack回调函数中调用
    public boolean CLIENT_StopUploadFileBurned(LLong lUploadHandle);

    // 下载指定的智能分析数据 - 图片, fDownLoadPosCallBack 回调
    // emType 参考 EM_FILE_QUERY_TYPE
    public LLong CLIENT_DownloadMediaFile(LLong lLoginID,int emType,Pointer lpMediaFileInfo,String sSavedFileName,Callback cbDownLoadPos,Pointer dwUserData,Pointer reserved);

    // 停止下载数据
    public boolean CLIENT_StopDownloadMediaFile(LLong lFileHandle);

    // 下发通知到设备 接口, 以emNotifyType来区分下发的通知类型, pInParam 和 pOutParam 都由用户来分配和释放, emNotifyType对应结构体 NET_EM_NOTIFY_TYPE
    public boolean CLIENT_SendNotifyToDev(LLong lLoginID,int emNotifyType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 查询IO状态(pState内存由用户申请释放,根据emType对应的类型找到相应的结构体，进而确定申请内存大小), emType 参考 NET_IOTYPE
    public boolean CLIENT_QueryIOControlState(LLong lLoginID,int emType,Pointer pState,int maxlen,IntByReference nIOCount,int waittime);

    // IO控制(pState内存由用户申请释放,根据emType对应的类型找到相应的结构体，进而确定申请内存大小),emType 参考 NET_IOTYPE
    public boolean CLIENT_IOControl(LLong lLoginID,int emType,Pointer pState,int maxlen);

    // 订阅监测点位信息,pInParam与pOutParam内存由用户申请释放
    public LLong CLIENT_SCADAAttachInfo(LLong lLoginID,NET_IN_SCADA_ATTACH_INFO pInParam,NET_OUT_SCADA_ATTACH_INFO pOutParam,int nWaitTime);

    // 取消监测点位信息订阅
    public boolean CLIENT_SCADADetachInfo(LLong lAttachHandle);

    // 创建透明串口通道,TransComType高2个字节表示串口序号,低2个字节表示串口类型,目前类型支持 0：串口(232), 1:485
    // baudrate 串口的波特率，1~8分别表示1200，2400，4800，9600，19200，38400，57600，115200
    // databits 串口的数据位 4~8表示4位~8位
    // stopbits 串口的停止位   232串口 ： 数值0 代表停止位1; 数值1 代表停止位1.5; 数值2 代表停止位2.    485串口 ： 数值1 代表停止位1; 数值2 代表停止位2.
    // parity 串口的检验位，0：无校验，1：奇校验；2：偶校验;
    // cbTransCom 串口数据回调，回调出前端设备发过来的信息
    // fTransComCallBack 回调
    public LLong CLIENT_CreateTransComChannel(LLong lLoginID,int TransComType,int baudrate,int databits,int stopbits,int parity,Callback cbTransCom,Pointer dwUser);

    // 透明串口发送数据(pBuffer内存由用户申请释放)
    public boolean CLIENT_SendTransComData(LLong lTransComChannel,byte[] pBuffer,int dwBufSize);

    // 释放通明串口通道
    public boolean CLIENT_DestroyTransComChannel(LLong lTransComChannel);

    // 查询透明串口状态(pCommState内存由用户申请释放), TransComType 低2个字节表示串口类型， 0:串口(232)， 1:485口；高2个字节表示串口通道号，从0开始
    public boolean CLIENT_QueryTransComParams(LLong lLoginID,int TransComType,NET_COMM_STATE pCommState,int nWaitTime);

    // 订阅智能分析进度（适用于视频分析源为录像文件时）,pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_AttachVideoAnalyseState(LLong lLoginID,NET_IN_ATTACH_VIDEOANALYSE_STATE pstInParam,NET_OUT_ATTACH_VIDEOANALYSE_STATE pstOutParam,int nWaittime);

    // 停止订阅
    public boolean CLIENT_DetachVideoAnalyseState(LLong lAttachHandle);

    // 抓图, hPlayHandle为预览或回放句柄
    public boolean CLIENT_CapturePicture(LLong hPlayHandle,String pchPicFileName);

    // 抓图, hPlayHandle为预览或回放句柄
    public boolean CLIENT_CapturePictureEx(LLong hPlayHandle,String pchPicFileName,int eFormat);

    // 获取设备自检信息,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_GetSelfCheckInfo(LLong lLoginID,NET_IN_GET_SELTCHECK_INFO pInParam,NET_SELFCHECK_INFO pOutParam,int nWaitTime);

    // 主动注册功能,启动服务；nTimeout参数已无效 .
    // cbListen对象为  fServiceCallBack 子类
    public LLong CLIENT_ListenServer(String ip,int port,int nTimeout,Callback cbListen,Pointer dwUserData);

    // 停止服务
    public boolean CLIENT_StopListenServer(LLong lServerHandle);

    // 指定回调数据类型 实施预览(预览), 数据回调函数 cbRealData 中得到的码流类型为 emDataType 所指定的类型
    public LLong CLIENT_RealPlayByDataType(LLong lLoginID,NET_IN_REALPLAY_BY_DATA_TYPE pstInParam,NET_OUT_REALPLAY_BY_DATA_TYPE pstOutParam,int dwWaitTime);

    // 指定回调数据格式  开始回放,  数据回调函数 fDownLoadDataCallBack 中得到的码流类型为 emDataType 所指定的类型
    public LLong CLIENT_PlayBackByDataType(LLong lLoginID,NET_IN_PLAYBACK_BY_DATA_TYPE pstInParam,NET_OUT_PLAYBACK_BY_DATA_TYPE pstOutParam,int dwWaitTime);

    // 指定码流类型 开始下载, 下载得到的文件和数据回调函数 fDownLoadDataCallBack 中得到的码流类型均为 emDataType 所指定的类型
    // NET_IN_DOWNLOAD_BY_DATA_TYPE pstInParam, NET_OUT_DOWNLOAD_BY_DATA_TYPE pstOutParam
    public LLong CLIENT_DownloadByDataType(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int dwWaitTime);

    /************************************************************************/
    /*                            BUS订阅                                   */
    /************************************************************************/
    // 订阅Bus状态,pstuInBus与pstuOutBus内存由用户申请释放
    public LLong CLIENT_AttachBusState(LLong lLoginID,NET_IN_BUS_ATTACH pstuInBus,NET_OUT_BUS_ATTACH pstuOutBus,int nWaitTime);

    // 停止订阅Bus状态,lAttachHandle是CLIENT_AttachBusState返回值
    public boolean CLIENT_DetachBusState(LLong lAttachHandle);

    //订阅事件重传,pInParam内存由用户申请释放
    public LLong CLIENT_AttachEventRestore(LLong lLoginID,NET_IN_ATTACH_EVENT_RESTORE pInParam,int nWaitTime);

    // 停止订阅事件重传,pInParam内存由用户申请释放
    public boolean CLIENT_DetachEventRestore(LLong lAttachHandle);

    // 设置GPS温湿度订阅回调函数, fGPSTempHumidityRev
    public void CLIENT_SetSubcribeGPSTHCallBack(Callback OnGPSMessage,Pointer dwUser);

    // GPS温湿度信息订阅, bStart为BOOL类型
    public boolean CLIENT_SubcribeGPSTempHumidity(LLong lLoginID,int bStart,int InterTime,Pointer Reserved);

    // 人脸信息记录操作函数
    public boolean CLIENT_FaceInfoOpreate(LLong lLoginID,int emType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    // 下发人脸图片信息
    public boolean CLIENT_DeliverUserFacePicture(LLong lLoginID,NET_IN_DELIVER_USER_PICTURE pInParam,NET_OUT_DELIVER_USER_PICTURE pOutParam,int nWaitTime);

    //开始查询人脸信息
    public LLong CLIENT_StartFindFaceInfo(LLong lLoginID,NET_IN_FACEINFO_START_FIND pstIn,NET_OUT_FACEINFO_START_FIND pstOut,int nWaitTime);

    //获取人脸信息
    public boolean CLIENT_DoFindFaceInfo(LLong lFindHandle,NET_IN_FACEINFO_DO_FIND pstIn,NET_OUT_FACEINFO_DO_FIND pstOut,int nWaitTime);

    //停止查询人脸信息
    public boolean CLIENT_StopFindFaceInfo(LLong lFindHandle);

    /***********************************************************************************
     *						                     诱导屏相关接口									  *
     **********************************************************************************/
    // 设置诱导屏配置信息接口
    public boolean CLIENT_SetGuideScreenCfg(LLong lLoginID,NET_IN_SET_GUIDESCREEN_CFG pInParam,NET_OUT_SET_GUIDESCREEN_CFG pstOutPqram,int nWaitTime);

    // 添加一个节目信息到诱导屏
    public boolean CLIENT_AddOneProgramme(LLong lLoginID,NET_IN_ADD_ONE_PROGRAMME pInParam,NET_OUT_ADD_ONE_PROGRAMME pOutParam,int nWaitTime);

    // 通过节目ID 修改节目
    public boolean CLIENT_ModifyOneProgrammeByID(LLong lLoginID,NET_IN_MODIFY_ONE_PROGRAMME pInParam,NET_OUT_MODIFY_ONE_PROGRAMME pOutParam,int nWaitTime);

    // 批量删除节目信息
    public boolean CLIENT_DelMultiProgrammesById(LLong lLoginID,NET_IN_DEL_PROGRAMMES pInParam,NET_OUT_DEL_PROGRAMMES pOutParam,int nWaitTime);

    // 增加一个即时节目计划
    public boolean CLIENT_AddOneImmediProgrammePlan(LLong lLoginID,NET_IN_ADD_IMME_PROGRAMMEPLAN pInParam,NET_OUT_ADD_PROGRAMMEPLAN pOutParam,int nWaitTime);

    // 修改一个即时节目计划
    public boolean CLIENT_ModifyOneImmediProgrammePlan(LLong lLoginID,NET_IN_MODIFY_IMME_PROGRAMMEPLAN pInParam,NET_OUT_MODIFY_IMME_PROGRAMMEPLAN pOutParam,int nWaitTime);

    // 增加一个定时节目计划
    public boolean CLIENT_AddOneTimerProgrammePlan(LLong lLoginID,NET_IN_ADD_TIMER_PROGRAMMEPLAN pInParam,NET_OUT_ADD_PROGRAMMEPLAN pOutParam,int nWaitTime);

    // 修改一个定时节目计划
    public boolean CLIENT_ModifyOneTimerProgrammePlan(LLong lLoginID,NET_IN_MODIFY_TIMER_PROGRAMMEPLAN pInParam,NET_OUT_MODIFY_TIMER_PROGRAMMEPLAN pOutParam,int nWaitTime);

    // 删除多个节目计划
    public boolean CLIENT_DelMultiProgrammePlans(LLong lLoginID,NET_IN_DEL_PROGRAMMEPLANS pInParam,NET_OUT_DEL_PROGRAMMEPLANS pOutParam,int nWaitTime);

    // 通过诱导屏ID 获取诱导屏配置信息
    public boolean CLIENT_GetOneGuideScreenCfgById(LLong lLoginID,NET_IN_GET_GUIDESCREEN_CFG_BYID pInParam,NET_OUT_GET_GUIDESCREEN_CFG_BYID pOutParam,int nWaitTime);

    // 获取所有诱导屏配置信息
    public boolean CLIENT_GetAllGuideScreenCfg(LLong lLoginID,NET_IN_GET_ALL_GUIDESCREEN_CFG pInParam,NET_OUT_GET_ALL_GUIDESCREEN_CFG pOutParam,int nWaitTime);

    // 通过节目ID 获取节目信息
    public boolean CLIENT_GetOneProgrammeById(LLong lLoginID,NET_IN_GET_PROGRAMME_BYID pInParam,NET_OUT_GET_PROGRAMME_BYID pOutParam,int nWaitTime);

    // 获取所有节目信息
    public boolean CLIENT_GetAllProgrammes(LLong lLoginID,NET_IN_GET_ALL_PROGRAMMES pInParam,NET_OUT_GET_ALL_PROGRAMMES pOutParam,int nWaitTime);

    // 获取所有节目的简要信息
    public boolean CLIENT_GetAllBrieflyProgrammes(LLong lLoginID,NET_IN_GET_ALL_BRIEFLYPROGRAMMES pInParam,NET_OUT_GET_ALL_BRIEFLYPROGRAMMES pOutParam,int nWaitTime);

    // 获取所有节目计划信息
    public boolean CLIENT_GetAllProgrammePlans(LLong lLoginID,NET_IN_GET_ALL_PROGRAMMEPLANS pInParam,NET_OUT_GET_ALL_PROGRAMMEPLANS pOutParam,int nWaitTime);

    // 通过节目计划ID 获取节目计划
    public boolean CLIENT_GetOneProgrammePlanByID(LLong lLoginID,NET_IN_GET_PROGRAMMEPLAN_BYID pInParam,NET_OUT_GET_PROGRAMMEPLAN_BYID pOutParam,int nWaitTime);

    // 设置光带状态信息
    public boolean CLIENT_SetGuideScreenGDStatus(LLong lLoginID,NET_IN_SET_GD_STATUS pInParam,NET_OUT_SET_GD_STATUS pOutParam,int nWaitTime);

    /***********************************************************************************
     *						                  播放盒与广告机的节目操作接口					      *
     **********************************************************************************/
    // 获取播放盒上全部节目信息
    public boolean CLIENT_GetAllProgramOnPlayBox(LLong lLoginID,NET_IN_GET_ALL_PLAYBOX_PROGRAM pInParam,NET_OUT_GET_ALL_PLAYBOX_PROGRAM pOutParam,int nWaitTime);

    // 通过programme ID 获取播放盒上对应的节目信息
    public boolean CLIENT_GetOneProgramByIdOnPlayBox(LLong lLoginID,NET_IN_GET_PLAYBOX_PROGRAM_BYID pInParam,NET_OUT_GET_PLAYBOX_PROGRAM_BYID pOutParam,int nWaitTime);

    // 在播放盒上添加一个节目
    public boolean CLIENT_AddOneProgramToPlayBox(LLong lLoginID,NET_IN_ADD_ONE_PLAYBOX_PRAGROM pInParam,NET_OUT_ADD_ONE_PLAYBOX_PRAGROM pOutParam,int nWaitTime);

    // 在播放盒上修改指定ID的节目信息
    public boolean CLIENT_ModifyProgramOnPlayBoxById(LLong lLoginID,NET_IN_MODIFY_PLAYBOX_PROGRAM_BYID pInParam,NET_OUT_MODIFY_PLAYBOX_PROGRAM_BYID pOutParam,int nWaitTime);

    // 获取配置信息(szOutBuffer内存由用户申请释放, 具体见枚举类型 NET_EM_CFG_OPERATE_TYPE 说明)
    public boolean CLIENT_GetConfig(LLong lLoginID,int emCfgOpType,int nChannelID,Pointer szOutBuffer,int dwOutBufferSize,int waittime,Pointer reserve);

    // 设置配置信息(szInBuffer内存由用户申请释放, 具体见枚举类型 NET_EM_CFG_OPERATE_TYPE 说明)
    public boolean CLIENT_SetConfig(LLong lLoginID,int emCfgOpType,int nChannelID,Pointer szInBuffer,int dwInBufferSize,int waittime,IntByReference restart,Pointer reserve);

    // 显示私有数据，例如规则框，规则框报警，移动侦测等       lPlayHandle:播放句柄      bTrue=1 打开, bTrue= 0 关闭
    public boolean CLIENT_RenderPrivateData(LLong lPlayHandle,int bTrue);

    // 按设备信息添加显示源,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_MatrixAddCamerasByDevice(LLong lLoginID,NET_IN_ADD_LOGIC_BYDEVICE_CAMERA pInParam,NET_OUT_ADD_LOGIC_BYDEVICE_CAMERA pOutParam,int nWaitTime);

    // 订阅监测点位报警信息,pInParam与pOutParam内存由用户申请释放
    public LLong CLIENT_SCADAAlarmAttachInfo(LLong lLoginID,NET_IN_SCADA_ALARM_ATTACH_INFO pInParam,NET_OUT_SCADA_ALARM_ATTACH_INFO pOutParam,int nWaitTime);

    // 取消订阅监测点位报警信息
    public boolean CLIENT_SCADAAlarmDetachInfo(LLong lAttachHandle);

    /***********************************************************************************
     *						           IVSS设备添加相关接口				             *
     **********************************************************************************/
    // 注册设备状态回调
    public LLong CLIENT_AttachDeviceState(LLong lLoginID,NET_IN_ATTACH_DEVICE_STATE pInParam,NET_OUT_ATTACH_DEVICE_STATE pOutParam,int nWaitTime);

    // 注销设备状态回调
    public boolean CLIENT_DetachDeviceState(LLong lAttachHandle);

    // 添加设备
    public boolean CLIENT_AsyncAddDevice(LLong lLoginID,NET_IN_ASYNC_ADD_DEVICE pInParam,NET_OUT_ASYNC_ADD_DEVICE pOutParam,int nWaitTime);

    // 注册添加设备回调
    public LLong CLIENT_AttachAddDevice(LLong lLoginID,NET_IN_ATTACH_ADD_DEVICE pInParam,NET_OUT_ATTACH_ADD_DEVICE pOutParam,int nWaitTime);

    // 注销添加设备回调
    public boolean CLIENT_DetachAddDevice(LLong lAttachHandle);

    // 获取添加中的设备状态
    public boolean CLIENT_GetAddDeviceInfo(LLong lLoginID,NET_IN_GET_ADD_DEVICE_LIST_INFO pInParam,NET_OUT_GET_ADD_DEVICE_LIST_INFO pOutParam,int nWaitTime);

    // 获取已添加的设备状态
    public boolean CLIENT_GetDeviceInfo(LLong lLoginID,NET_IN_GET_DEVICE_LIST_INFO pInParam,NET_OUT_GET_DEVICE_LIST_INFO pOutParam,int nWaitTime);

    // 设置连接通道
    public boolean CLIENT_SetConnectChannel(LLong lLoginID,NET_IN_SET_CONNECT_CHANNEL pInParam,NET_OUT_SET_CONNECT_CHANNEL pOutParam,int nWaitTime);

    // 获取设备通道信息
    public boolean CLIENT_GetChannelInfo(LLong lLoginID,NET_IN_GET_CHANNEL_INFO pInParam,NET_OUT_GET_CHANNEL_INFO pOutParam,int nWaitTime);

    // 删除设备
    public boolean CLIENT_RemoveDevice(LLong lLoginID,NET_IN_REMOVE_DEVICE pInParam,NET_OUT_REMOVE_DEVICE pOutParam,int nWaitTime);

    // 中止添加设备任务
    public boolean CLIENT_CancelAddDeviceTask(LLong lLoginID,NET_IN_CANCEL_ADD_TASK pInParam,NET_OUT_CANCEL_ADD_TASK pOutParam,int nWaitTime);

    // 确认添加设备任务
    public boolean CLIENT_ConfirmAddDeviceTask(LLong lLoginID,NET_IN_CONFIRM_ADD_TASK pInParam,NET_OUT_CONFIRM_ADD_TASK pOutParam,int nWaitTime);

    // 球机，地磁车位同步上报车位信息,如有车停入、或车从车位开出
    public boolean CLIENT_SyncParkingInfo(LLong lLoginID,NET_IN_SYNC_PARKING_INFO pInParam,NET_OUT_SYNC_PARKING_INFO pOutParam,int nWaitTime);

    // 初始化账户
    public boolean CLIENT_InitDevAccount(NET_IN_INIT_DEVICE_ACCOUNT pInitAccountIn,NET_OUT_INIT_DEVICE_ACCOUNT pInitAccountOut,int dwWaitTime,String szLocalIp);

    // 根据设备IP初始化账户
    public boolean CLIENT_InitDevAccountByIP(NET_IN_INIT_DEVICE_ACCOUNT pInitAccountIn,NET_OUT_INIT_DEVICE_ACCOUNT pInitAccountOut,int dwWaitTime,String szLocalIp,String szDeviceIP);

    public boolean CLIENT_ModifyDevice(DEVICE_NET_INFO_EX pDevNetInfo,int nWaitTime,IntByReference iError,String szLocalIp);

    /**
     * 门禁控制器操作接口
     * @param lLoginID 登录句柄
     * @param emtype 门禁控制器操作类型, 对应 枚举{@link NET_EM_ACCESS_CTL_MANAGER}
     * @param pstInParam 入参, 根据 emtype 来填
     * @param pstOutParam 出参, 根据 emtype 来填
     * @param nWaitTime 超时等待时间
     * @return true：成功    false：失败
     */
    public boolean CLIENT_OperateAccessControlManager(LLong lLoginID,int emtype,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 设置安全密钥(播放加密码流使用)
     * @param lPlayHandle 拉流句柄
     * @param szKey 密钥
     * @param nKeyLen 密钥的长度
     * @return true：成功    false：失败
     */
    public boolean CLIENT_SetSecurityKey(LLong lPlayHandle,String szKey,int nKeyLen);

    /***********************************************************************************
     *						           	  考勤机相关接口						         *
     **********************************************************************************/
    //考勤新增加用户
    public boolean CLIENT_Attendance_AddUser(LLong lLoginID,NET_IN_ATTENDANCE_ADDUSER pstuInAddUser,NET_OUT_ATTENDANCE_ADDUSER pstuOutAddUser,int nWaitTime);

    //考勤删除用户
    public boolean CLIENT_Attendance_DelUser(LLong lLoginID,NET_IN_ATTENDANCE_DELUSER pstuInDelUser,NET_OUT_ATTENDANCE_DELUSER pstuOutDelUser,int nWaitTime);

    //考勤修改用户信息
    public boolean CLIENT_Attendance_ModifyUser(LLong lLoginID,NET_IN_ATTENDANCE_ModifyUSER pstuInModifyUser,NET_OUT_ATTENDANCE_ModifyUSER pstuOutModifyUser,int nWaitTime);

    //考勤获取用户信息
    public boolean CLIENT_Attendance_GetUser(LLong lLoginID,NET_IN_ATTENDANCE_GetUSER pstuInGetUser,NET_OUT_ATTENDANCE_GetUSER pstuOutGetUser,int nWaitTime);

    //考勤机  通过用户ID插入信息数据
    public boolean CLIENT_Attendance_InsertFingerByUserID(LLong lLoginID,NET_IN_FINGERPRINT_INSERT_BY_USERID pstuInInsert,NET_OUT_FINGERPRINT_INSERT_BY_USERID pstuOutInsert,int nWaitTime);

    //考勤机 删除单个用户下所有信息数据
    public boolean CLIENT_Attendance_RemoveFingerByUserID(LLong lLoginID,NET_CTRL_IN_FINGERPRINT_REMOVE_BY_USERID pstuInRemove,NET_CTRL_OUT_FINGERPRINT_REMOVE_BY_USERID pstuOutRemove,int nWaitTime);

    //考勤机 通过信息ID获取信息数据
    public boolean CLIENT_Attendance_GetFingerRecord(LLong lLoginID,NET_CTRL_IN_FINGERPRINT_GET pstuInGet,NET_CTRL_OUT_FINGERPRINT_GET pstuOutGet,int nWaitTime);

    //考勤机 通过信息ID删除信息数据
    public boolean CLIENT_Attendance_RemoveFingerRecord(LLong lLoginID,NET_CTRL_IN_FINGERPRINT_REMOVE pstuInRemove,NET_CTRL_OUT_FINGERPRINT_REMOVE pstuOutRemove,int nWaitTime);

    //考勤机 查找用户
    public boolean CLIENT_Attendance_FindUser(LLong lLoginID,NET_IN_ATTENDANCE_FINDUSER pstuInFindUser,NET_OUT_ATTENDANCE_FINDUSER pstuOutFindUser,int nWaitTime);

    //考勤机 通过用户ID查找该用户下的所有信息数据
    public boolean CLIENT_Attendance_GetFingerByUserID(LLong lLoginID,NET_IN_FINGERPRINT_GETBYUSER pstuIn,NET_OUT_FINGERPRINT_GETBYUSER pstuOut,int nWaitTime);

    //获取考勤机在线状态
    public boolean CLIENT_Attendance_GetDevState(LLong lLoginID,NET_IN_ATTENDANCE_GETDEVSTATE pstuInParam,NET_OUT_ATTENDANCE_GETDEVSTATE pstuOutParam,int nWaitTime);

    /*********************************************************************************************************
     * 									视频诊断功能接口														 *												 												     *
     * 视频诊断参数表配置 CFG_CMD_VIDEODIAGNOSIS_PROFILE														 *
     * 视频诊断任务表配置 CFG_CMD_VIDEODIAGNOSIS_TASK_ONE						 								 *
     * 视频诊断计划表配置 CFG_CMD_VIDEODIAGNOSIS_PROJECT														 *
     * 删除任务接口  CLIENT_DeleteDevConfig																	 *
     * 获取成员配置接口 CLIENT_GetMemberNames	  对应命令  CFG_CMD_VIDEODIAGNOSIS_TASK					         *
     * 获取诊断状态  CLIENT_QueryNewSystemInfo CFG_CMD_VIDEODIAGNOSIS_GETSTATE							 	 *
     *********************************************************************************************************/
    // 实时获取视频诊断结果,pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_StartVideoDiagnosis(LLong lLoginID,NET_IN_VIDEODIAGNOSIS pstInParam,NET_OUT_VIDEODIAGNOSIS pstOutParam);

    // 停止视频诊断结果上报
    public boolean CLIENT_StopVideoDiagnosis(LLong hDiagnosisHandle);

    // 开始视频诊断结果查询,pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_StartFindDiagnosisResult(LLong lLoginID,NET_IN_FIND_DIAGNOSIS pstInParam,NET_OUT_FIND_DIAGNOSIS pstOutParam);

    // 获取视频诊断结果信息,pstInParam与pstOutParam内存由用户申请释放
    public boolean CLIENT_DoFindDiagnosisResult(LLong hFindHandle,NET_IN_DIAGNOSIS_INFO pstInParam,NET_OUT_DIAGNOSIS_INFO pstOutParam);

    // 结束视频诊断结果查询
    public boolean CLIENT_StopFindDiagnosis(LLong hFindHandle);

    // 获取视频诊断进行状态
    public boolean CLIENT_GetVideoDiagnosisState(LLong lLoginID,NET_IN_GET_VIDEODIAGNOSIS_STATE pstInParam,NET_OUT_GET_VIDEODIAGNOSIS_STATE pstOutParam,int nWaitTime);

    /********************************************************************************************
     * 									热成像												    *
     ********************************************************************************************/
    // 订阅温度分布数据（热图）,pInParam与pOutParam内存由用户申请释放
    public LLong CLIENT_RadiometryAttach(LLong lLoginID,NET_IN_RADIOMETRY_ATTACH pInParam,NET_OUT_RADIOMETRY_ATTACH pOutParam,int nWaitTime);

    // 取消订阅温度分布数据,lAttachHandle是 CLIENT_RadiometryAttach 的返回值
    public boolean CLIENT_RadiometryDetach(LLong lAttachHandle);

    // 通知开始获取热图数据,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_RadiometryFetch(LLong lLoginID,NET_IN_RADIOMETRY_FETCH pInParam,NET_OUT_RADIOMETRY_FETCH pOutParam,int nWaitTime);

    // 热图数据解压与转换接口
    /// \brief
    /// \param  pRadiometryData [IN] 热图数据， 由 fRadiometryAttachCB 获得
    /// \param  pGrayImg [IN, OUT] 解压后的数据，是一张灰度图，
    ///			传空指针表示不需要此数据
    ///         用户需保证传入的缓冲区足够大（不小于 图像像素数*sizeof(unsigned short)）
    ///         每个像素对应一个 unsigned short 型数据，表示图像某个像素的热成像灰度（范围 0 ~ 16383），
    ///         低地址对应画面左上角，高地址对应画面右下角
    /// \param  pTempForPixels [IN, OUT] 每个像素的温度数据
    ///			传空指针表示不需要此数据
    ///         用户需保证传入的缓冲区足够大（不小于 图像像素数*sizeof(float)）
    ///         每个像素对应一个 float 型数据，表示该像素位置的摄氏温度
    ///         低地址对应画面左上角，高地址对应画面右下角
    /// \return TRUE 成功，FALSE 失败
    public boolean CLIENT_RadiometryDataParse(NET_RADIOMETRY_DATA pRadiometryData,short[] pGrayImg,float[] pTempForPixels);

    // 开始查询信息（获取查询句柄）(pInBuf, pOutBuf内存由用户申请释放,根据emType对应的类型找到相应的结构体，进而确定申请内存大小)
    public boolean CLIENT_StartFind(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    // 查询信息(pInBuf, pOutBuf内存由用户申请释放,根据emType对应的类型找到相应的结构体，进而确定申请内存大小
    public boolean CLIENT_DoFind(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    // 停止查询信息（销毁查询句柄）(pInBuf, pOutBuf内存由用户申请释放,根据emType对应的类型找到相应的结构体，进而确定申请内存大小)
    public boolean CLIENT_StopFind(LLong lLoginID,int emType,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    // 智能锁添加更新用户信息接口
    public boolean CLIENT_UpdateSmartLockUser(LLong lLoginID,NET_IN_SMARTLOCK_UPDATE_USER_INFO pstInParam,NET_OUT_SMARTLOCK_UPDATE_USER_INFO pstOutParam,int nWaitTime);

    // 获取当前智能锁的注册用户信息
    public boolean CLIENT_GetSmartLockRegisterInfo(LLong lLoginID,NET_IN_GET_SMART_LOCK_REGISTER_INFO pstInParam,NET_OUT_GET_SMART_LOCK_REGISTER_INFO pstOutParam,int nWaitTime);

    // 智能锁修改用户信息
    public boolean CLIENT_SetSmartLockUsername(LLong lLoginID,NET_IN_SET_SMART_LOCK_USERNAME pstInParam,NET_OUT_SET_SMART_LOCK_USERNAME pstOutParam,int nWaitTime);

    // 智能锁删除用户接口
    public boolean CLIENT_RemoveSmartLockUser(LLong lLoginID,NET_IN_SMARTLOCK_REMOVE_USER_INFO pstInParam,NET_OUT_SMARTLOCK_REMOVE_USER_INFO pstOutParam,int nWaitTime);

    // 字符串加密接口 (pInParam, pOutParam内存由用户申请释放) 接口
    public boolean CLIENT_EncryptString(NET_IN_ENCRYPT_STRING pInParam,NET_OUT_ENCRYPT_STRING pOutParam,int nWaitTime);

    /*************************************************************************************************************
     * 门禁用户、卡、人脸、信息操作新接口
     * 1、添加一个用户
     * 2、根据用户ID可以来添加多张卡、多张人脸、多个信息
     *************************************************************************************************************/
    /**
     *  门禁人员信息管理接口
     * @param emtype 对应 {@link NET_EM_ACCESS_CTL_USER_SERVICE}
     */
    public boolean CLIENT_OperateAccessUserService(LLong lLoginID,int emtype,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    // 开始查询人员信息
    public LLong CLIENT_StartFindUserInfo(LLong lLoginID,NET_IN_USERINFO_START_FIND pstIn,NET_OUT_USERINFO_START_FIND pstOut,int nWaitTime);

    // 获取人员信息 ,lFindHandle 为 CLIENT_StartFindUserInfo 接口返回值
    public boolean CLIENT_DoFindUserInfo(LLong lFindHandle,NET_IN_USERINFO_DO_FIND pstIn,NET_OUT_USERINFO_DO_FIND pstOut,int nWaitTime);

    // 停止查询人员信息 ,lFindHandle 为 CLIENT_StartFindUserInfo 接口返回值
    public boolean CLIENT_StopFindUserInfo(LLong lFindHandle);

    /**
     * 门禁卡片信息管理接口
     * @param emtype 对应 {@link NET_EM_ACCESS_CTL_CARD_SERVICE}
     */
    public boolean CLIENT_OperateAccessCardService(LLong lLoginID,int emtype,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    // 开始查询卡片信息
    public LLong CLIENT_StartFindCardInfo(LLong lLoginID,NET_IN_CARDINFO_START_FIND pstIn,NET_OUT_CARDINFO_START_FIND pstOut,int nWaitTime);

    // 获取卡片信息,lFindHandle 为CLIENT_StartFindCardInfo接口返回值
    public boolean CLIENT_DoFindCardInfo(LLong lFindHandle,NET_IN_CARDINFO_DO_FIND pstIn,NET_OUT_CARDINFO_DO_FIND pstOut,int nWaitTime);

    // 停止查询卡片信息,lFindHandle 为CLIENT_StartFindCardInfo接口返回值
    public boolean CLIENT_StopFindCardInfo(LLong lFindHandle);

    /**
     * 门禁人脸信息管理接口
     * @param emtype 对应 {@link NET_EM_ACCESS_CTL_FACE_SERVICE}
     */
    public boolean CLIENT_OperateAccessFaceService(LLong lLoginID,int emtype,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 信息信息管理接口
     * @param emtype 对应 {@link NET_EM_ACCESS_CTL_FINGERPRINT_SERVICE}
     */
    public boolean CLIENT_OperateAccessFingerprintService(LLong lLoginID,int emtype,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    // 开始升级
    public LLong CLIENT_StartUpgradeEx(LLong lLoginID,int emtype,String pchFileName,Callback cbUpgrade,Pointer dwUser);

    // 发送数据
    public boolean CLIENT_SendUpgrade(LLong lUpgradeID);

    // 结束升级设备程序
    public boolean CLIENT_StopUpgrade(LLong lUpgradeID);

    // 查询产品定义
    public boolean CLIENT_QueryProductionDefinition(LLong lLoginID,NET_PRODUCTION_DEFNITION pstuProdDef,int nWaitTime);

    /**
     * 录播主机相关 添加新的课程记录
     * NET_IN_ADD_COURSE *pstInParam, NET_OUT_ADD_COURSE *pstOutParam
     */
    public boolean CLIENT_AddCourse(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     *  录播主机相关 修改课程
     *  NET_IN_MODIFY_COURSE *pstInParam, NET_OUT_MODIFY_COURSE *pstOutParam
     */
    public boolean CLIENT_ModifyCourse(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     *  录播主机相关 删除课程
     *  NET_IN_DELETE_COURSE *pstInParam, NET_OUT_DELETE_COURSE *pstOutParam
     */
    public boolean CLIENT_DeleteCourse(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 开始查询课程
     * NET_IN_QUERY_COURSE_OPEN *pstInParam, NET_OUT_QUERY_COURSE_OPEN *pstOutParam
     */
    public boolean CLIENT_QueryCourseOpen(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 查询课程
     * NET_IN_QUERY_COURSE *pstInParam, NET_OUT_QUERY_COURSE *pstOutParam
     */
    public boolean CLIENT_QueryCourse(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 结束查询课程
     * NET_IN_QUERY_COURSE_CLOSE *pstInParam, NET_OUT_QUERY_COURSE_CLOSE *pstOutParam
     */
    public boolean CLIENT_QueryCourseClose(LLong lLoginID,Pointer pstInParam,Pointer pstOutParam,int nWaitTime);

    /**
     * 获取真实预览通道号，pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_GET_REAL_PREVIEW_CHANNEL* pInBuf, NET_OUT_GET_REAL_PREVIEW_CHANNEL* pOutBuf
     */
    public boolean CLIENT_GetRealPreviewChannel(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 录播主机组合通道模式操作 emOperateType NET_COURSECOMPOSITE_MODE_OPERATE_TYPE
     *
     * NET_COURSECOMPOSITE_MODE_OPERATE_TYPE {
     *
     *      NET_COURSECOMPOSITE_MODE_ADD,      // 添加模式,对应结构体
     *                                         // pInParam = NET_IN_COURSECOMPOSITE_CHANNEL_MODE_ADD,
     *                                         // pOutParam = NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_ADD
     * 	    NET_COURSECOMPOSITE_MODE_DELETE,   // 删除模式,对应结构体
     * 	                                       // pInParam = NET_IN_COURSECOMPOSITE_CHANNEL_MODE_DELETE,
     * 	                                       // pOutParam = NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_DELETE
     * 	    NET_COURSECOMPOSITE_MODE_MODIFY,   // 修改模式,对应结构体
     * 	                                       // pInParam = NET_IN_COURSECOMPOSITE_CHANNEL_MODE_MODIFY,
     * 	                                       // pOutParam = NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_MODIFY
     * 	    NET_COURSECOMPOSITE_MODE_GET,      // 获取模式,对应结构体
     * 	                                       // pInParam = NET_IN_COURSECOMPOSITE_CHANNEL_MODE_GET,
     * 	                                       // pOutParam = NET_OUT_COURSECOMPOSITE_CHANNEL_MODE_GET
     * }
     */
    public boolean CLIENT_OperateCourseCompositeChannelMode(LLong lLoginID,int emOperateType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取录播主机默认真实通道号,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_GET_DEFAULT_REAL_CHANNEL* pInBuf, NET_OUT_GET_DEFAULT_REAL_CHANNEL* pOutBuf
     */
    public boolean CLIENT_GetDefaultRealChannel(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 获取录播主机逻辑通道号,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_GET_COURSE_LOGIC_CHANNEL* pInBuf,NET_OUT_GET_COURSE_LOGIC_CHANNEL* pOutBuf
     */
    public boolean CLIENT_GetLogicChannel(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 设置逻辑通道号和真实通道号的绑定关系,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_SET_BLIND_REAL_CHANNEL* pInBuf,NET_OUT_SET_BLIND_REAL_CHANNEL* pOutBuf
     */
    public boolean CLIENT_SetBlindRealChannel(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 设置课程录像模式,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_SET_COURSE_RECORD_MODE* pInBuf, NET_OUT_SET_COURSE_RECORD_MODE* pOutBuf
     */
    public boolean CLIENT_SetCourseRecordMode(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 获取课程录像模式,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_GET_COURSE_RECORD_MODE* pInBuf, NET_OUT_GET_COURSE_RECORD_MODE* pOutBuf
     */
    public boolean CLIENT_GetCourseRecordMode(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 获取录播主机通道输入媒体介质,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_GET_INPUT_CHANNEL_MEDIA* pInBuf,NET_OUT_GET_INPUT_CHANNEL_MEDIA* pOutBuf
     */
    public boolean CLIENT_GetInputChannelMedia(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 开启/关闭指定通道录像
     * NET_IN_SET_COURSE_RECORD_STATE *pInBuf, NET_OUT_SET_COURSE_RECORD_STATE *pOutBuf
     */
    public boolean CLIENT_SetCourseRecordState(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 开始查询课程视频信息,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_QUERY_COURSEMEDIA_FILEOPEN *pInBuf, NET_OUT_QUERY_COURSEMEDIA_FILEOPEN *pOutBuf
     */
    public boolean CLIENT_OpenQueryCourseMediaFile(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 查询课程视频信息,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_QUERY_COURSEMEDIA_FILE *pInBuf, NET_OUT_QUERY_COURSEMEDIA_FILE *pOutBuf
     */
    public boolean CLIENT_DoQueryCourseMediaFile(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 关闭课程视频查询,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_QUERY_COURSEMEDIA_FILECLOSE *pInBuf, NET_OUT_QUERY_COURSEMEDIA_FILECLOSE *pOutBuf
     */
    public boolean CLIENT_CloseQueryCourseMediaFile(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

    /**
     * 录播主机录像信息操作接口 EM_COURSERECORD_OPERATE_TYPE emOperateType
     * {
     *         EM_COURSERECORDE_TYPE_GET_INFO,    //获取教室录像信息,对应结构体
     *              pInParam = NET_IN_COURSERECORD_GETINFO,pOutParam = NET_OUT_COURSERECORD_GETINFO
     *         EM_COURSERECORDE_TYPE_SET_INFO,    //设置教室录像信息,对应结构体
     *              pInParam = NET_IN_COURSERECORD_SETINFO,pOutParam = NET_OUT_COURSERECORD_SETINFO
     *         EM_COURSERECORDE_TYPE_UPDATE_INFO, //将录像信息更新到time时的信息,对应结构体
     *              pInParam = NET_IN_COURSERECORD_UPDATE_INFO, pOutParam = NET_OUT_COURSERECORD_UPDATE_INFO
     *         EM_COURSERECORDE_TYPE_GET_TIME,     //获取当前课程教室已录制时间,对应结构体
     *              pInParam = NET_IN_COURSERECORD_GET_TIME, pOutParam = NET_OUT_COURSERECORD_GET_TIME
     *     }
     */
    public boolean CLIENT_OperateCourseRecordManager(LLong lLoginID,int emOperateType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     *  录播主机组合通道操作 EM_COURSECOMPOSITE_OPERATE_TYPE
     *  {
     *         EM_COURSECOMPOSITE_TYPE_LOCK_CONTROL,                   //控制组合通道与逻辑通道，对应结构体
     *              pInParam = NET_IN_COURSECOMPOSITE_LOCK_CONTROL,pOutParam = NET_OUT_COURSECOMPOSITE_LOCK_CONTROL
     *         EM_COURSECOMPOSITE_TYPE_GET_LOCKINFO,                   //获取组合通道与逻辑通道的锁定信息，对应结构体
     *              pInParam = NET_IN_COURSECOMPOSITE_GET_LOCKINFO，pOutParam = NET_OUT_COURSECOMPOSITE_GET_LOCKINFO
     *         EM_COURSECOMPOSITE_TYPE_GET_INFO,                       //获取组合通道信息,对应结构体
     *              pInParam = NET_IN_COURSECOMPOSITE_GET_INFO,pOutParam = NET_OUT_COURSECOMPOSITE_GET_INFO
     *         EM_COURSECOMPOSITE_TYPE_SET_INFO,                       //设置组合通道信息,对应结构体
     *              pInParam = NET_IN_COURSECOMPOSITE_SET_INFO,pOutParam = NET_OUT_COURSECOMPOSITE_SET_INFO
     *         EM_COURSECOMPOSITE_TYPE_UPDATE_INFO,                    //将组合通道信息更新到time时的信息，对应结构体
     *              pInParam = NET_IN_COURSECOMPOSITE_UPDATE_INFO, pOutParam = NET_OUT_COURSECOMPOSITE_UPDATE_INFO
     *     }
     */
    public boolean CLIENT_OperateCourseCompositeChannel(LLong lLoginID,int emOperateType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 获取录像状态
     * NET_IN_GET_RECORD_STATE *pInParam, NET_OUT_GET_RECORD_STATE *pOutParam
     */
    public boolean CLIENT_GetRecordState(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 电视墙操作
     * NET_MONITORWALL_OPERATE_TYPE emType, void* pInParam, void* pOutParam
     */
    public boolean CLIENT_OperateMonitorWall(LLong lLoginID,int emType,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    /**
     * 分页获取信息标注信息列表
     * NET_IN_SCENICSPOT_GETPOINTINFOS_INFO *pInstuParam, NET_OUT_SCENICSPOT_GETPOINTINFOS_INFO *pstuOutParam
     */
    public boolean CLIENT_ScenicSpotGetPointInfos(LLong lLoginID,Pointer pInstuParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 设置景物点，原编号的景物点将会被覆盖
     * NET_IN_SCENICSPOT_SETPOINTINFO_INFO *pInstuParam, NET_OUT_SCENICSPOT_SETPOINTINFO_INFO *pstuOutParam
     */
    public boolean CLIENT_ScenicSpotSetPointInfo(LLong lLoginID,Pointer pInstuParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 获取景物点支持的能力
     * NET_IN_SCENICSPOT_GETCAPS_INFO *pInstuParam, NET_OUT_SCENICSPOT_GETCAPS_INFO *pstuOutParam
     */
    public boolean CLIENT_ScenicSpotGetCaps(LLong lLoginID,Pointer pInstuParam,Pointer pstuOutParam,int nWaitTime);

    /**
     * 以景物标注点为中心，进行三维定位(倍率不变)
     * NET_IN_SCENICSPOT_TURNTOPOINT_INFO *pInParam, NET_OUT_SCENICSPOT_TURNTOPOINT_INFO *pOutParam
     */
    public boolean CLIENT_ScenicSpotTurnToPoint(LLong lLoginID,Pointer pInstuParam,Pointer pstuOutParam,int nWaitTime);

    /////////////////////////////////////////新增接口 ///////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 设置停车车位状态
     * NET_IN_SET_PARKINGSPACE_STATE_INFO *pInParam, NET_OUT_SET_PARKINGSPACE_STATE_INFO *pOutParam
     */
    public boolean CLIENT_SetParkingSpaceState(LLong lLoginID,Pointer pstInParm,Pointer pstOutParam,int nWaitTime);

    /**
     * 修改停车记录信息
     * NET_IN_MODIFY_PARKINGRECORD_INFO *pInParam, NET_OUT_MODIFY_PARKINGRECORD_INFO *pOutParam
     */
    public boolean CLIENT_ModifyParkingRecord(LLong lLoginID,Pointer pstInParm,Pointer pstOutParam,int nWaitTime);

    /**
     * 按照事件类型抓图（配合CLIENT_RealLoadPic()、CLIENT_RealLoadPicEx()接口使用, 按照手动抓拍模式(Manual)订阅,图片通过回调给用户）(pInParam, pOutParam内存由用户申请释放)
     * NET_IN_SNAP_BY_EVENT *pInParam, NET_OUT_SNAP_BY_EVENT *pOutParam
     */
    public boolean CLIENT_SnapPictureByEvent(LLong lLoginID,Pointer pInParam,Pointer pOutParam,int nWaitTime);

    public static class NET_IN_SET_MARK_FILE_BY_TIME extends SdkStructure
    {
        public int              dwSize;
        public int              nChannel;                             //需要锁定的通道号,从0开始,元素为-1时,表示全通道。
        public NET_TIME_EX      stuStartTime;                         //开始时间
        public NET_TIME_EX      stuEndTime;                           //结束时间
        public int              bFlag;                                // 标记动作	true : 标记, false : 清除
        public int              bLockTimeFlag;                        //nLockTime字段标志位， 为 TRUE 时使用nLockTime
        public int              nLockTime;                            //锁定时长，以加锁时间为起点 ，单位为小时

        public NET_IN_SET_MARK_FILE_BY_TIME() {
            this.dwSize = this.size();
        }
    }

    public static class NET_OUT_SET_MARK_FILE_BY_TIME extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SET_MARK_FILE_BY_TIME() {
            this.dwSize = this.size();
        }
    }

    // 按时间标记录像
    public boolean CLIENT_SetMarkFileByTime(LLong lUpgradeID,NET_IN_SET_MARK_FILE_BY_TIME pInParam,NET_OUT_SET_MARK_FILE_BY_TIME pOutParam,int nWaitTime);

    public static class NET_IN_SET_MARK_FILE extends SdkStructure
    {
        public int              dwSize;
        public int              emLockMode;                           // 录像加锁方式，详见EM_MARKFILE_MODE
        public int              emFileNameMadeType;                   // 文件名产生的方式，详见EM_MARKFILE_NAMEMADE_TYPE
        public int              nChannelID;                           // 通道号
        public byte[]           szFilename = new byte[MAX_PATH];      // 文件名
        public int              nFramenum;                            // 文件总帧数
        public int              nSize;                                // 文件长度
        public NET_TIME         stuStartTime;                         // 开始时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public int              nDriveNo;                             // 磁盘号(区分网络录像和本地录像的类型,0－127表示本地录像,其中64表示光盘1,128表示网络录像)
        public int              nStartCluster;                        // 起始簇号
        public byte             byRecordFileType;                     // 录象文件类型  0：普通录象；1：报警录象；2：移动检测；3：卡号录象；4：图片, 5: 智能录像
        public byte             byImportantRecID;                     // 0:普通录像 1:重要录像
        public byte             byHint;                               // 文件定位索引(nRecordFileType==4<图片>时,bImportantRecID<<8 +bHint ,组成图片定位索引 )
        public byte             byRecType;                            // 0-主码流录像  1-辅码流1录像 2-辅码流2录像 3-辅码流3录像
        public int              nLockTime;                            // 锁定时长，以加锁时间为起点，单位为小时

        public NET_IN_SET_MARK_FILE() {
            this.dwSize = this.size();
        }
    }

    // 录像加锁方式
    public static class EM_MARKFILE_MODE extends SdkStructure
    {
        public static final int   EM_MARK_FILE_BY_TIME_MODE = 0;        // 通过时间方式对录像加锁
        public static final int   EM_MARK_FILE_BY_NAME_MODE = 1;        // 通过文件名方式对录像加锁
    }

    // 文件名产生的方式
    public static class EM_MARKFILE_NAMEMADE_TYPE extends SdkStructure
    {
        public static final int   EM_MARKFILE_NAMEMADE_DEFAULT = 0;     // 默认方式：需要用户传递录像文件名参数szFilename
        public static final int   EM_MARKFILE_NAMEMADE_JOINT = 1;       // 拼接文件名方式：用户传递磁盘号(nDriveNo)、起始簇号(nStartCluster)，不需要传递录像文件名
    }

    public static class NET_OUT_SET_MARK_FILE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_SET_MARK_FILE() {
            this.dwSize = this.size();
        }
    }

    // 按文件标记录像
    public boolean CLIENT_SetMarkFile(LLong lLoginID,NET_IN_SET_MARK_FILE pInParam,NET_OUT_SET_MARK_FILE pOutParam,int nWaitTime);

    public static class NET_IN_DEV_GPS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 该结构体大小
        public int              nChannel;

        public NET_IN_DEV_GPS_INFO() {
            this.dwSize =this.size();
        }
    }

    public static class NET_OUT_DEV_GPS_INFO extends SdkStructure
    {
        public int              dwSize;                               // 该结构体大小
        public NET_TIME         stuLocalTime;                         // 当前时间
        public double           dbLongitude;                          // 经度(单位是百万分之度,范围0-360度)
        public double           dbLatitude;                           // 纬度(单位是百万分之度,范围0-180度)
        public double           dbAltitude;                           // 高度(单位:米)
        public double           dbSpeed;                              // 速度(单位:km/H)
        public double           dbBearing;                            // 方向角(单位:度)
        public int              emAntennasStatus;                     // 天线状态(0:坏 1:好)
        public int              emPositioningResult;                  // 定位状态(0:不定位 1:定位)
        public int              dwSatelliteCount;                     // 卫星个数
        public int              emworkStatus;                         // 工作状态
        public int              nAlarmCount;                          // 报警个数
        public int[]            nAlarmState = new int[128];           // 发生的报警位置,值可能多个
        public float            fHDOP;                                // 水平精度因子

        public NET_OUT_DEV_GPS_INFO() {
            this.dwSize = this.size();
        }
    }

    // 实时抽帧配置,EVS
    public static class CFG_BACKUP_LIVE_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否启动抽帧
        public int              nBackupRate;                          // 抽帧备份比率，如为0表示只保留I帧，其它情况下表示保留I帧以及紧邻其后的若干P帧
        // 单位：百分比
        // 如果GOP为50，20表示保留50*20%=10帧数据(即1个I帧和9个P帧)。如果计算结果带小数，则取整
        public CFG_TIME_SECTION stuTimeSection;                       // 抽帧时间段
    }

    // 定时录像配置信息
    public static class CFG_RECORD_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号(0开始)
        public TIME_SECTION_WEEK_DAY_6[] stuTimeSection = (TIME_SECTION_WEEK_DAY_6[])new TIME_SECTION_WEEK_DAY_6().toArray(WEEK_DAY_NUM); // 时间表
        public int              nPreRecTime;                          // 预录时间，为零时表示关闭(0~300)
        public int              bRedundancyEn;                        // 录像冗余开关
        public int              nStreamType;                          // 0－主码流，1－辅码流1，2－辅码流2，3－辅码流3
        public int              nProtocolVer;                         // 协议版本号, 只读
        public int              abHolidaySchedule;                    // 为true时有假日配置信息，bHolidayEn、stuHolTimeSection才有效;
        public int              bHolidayEn;                           // 假日录像使能TRUE:使能,FALSE:未使能
        public TIME_SECTION_WEEK_DAY_6 stuHolTimeSection;             // 假日录像时间表
        public int              nBackupLiveNum;                       // 实时抽帧配置个数
        public CFG_BACKUP_LIVE_INFO[] stuBackupLiveInfo = (CFG_BACKUP_LIVE_INFO[]) new CFG_BACKUP_LIVE_INFO().toArray(8); // 实时抽帧配置,EVS
        public int              bSaveVideo;                           // 是否录制视频帧
        public int              bSaveAudio;                           // 录像时是否保存音频数据
        public int              bEnable;                              //录像时是否保存音频数据
        public int              nMaxRecordTime;                       //报警输入使能
        public byte[]           szReserved = new byte[1024];          //单次Pir录像时长限制（单位秒），0表示无限制
    }

    //获取云升级信息入参
    public static class NET_IN_UPGRADER_GETSERIAL extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小

        public NET_IN_UPGRADER_GETSERIAL() {
            this.dwSize = this.size();
        }
    }

    //云升级信息
    public static class NET_UPGRADER_SERIAL_INO extends SdkStructure
    {
        public int              emVendor;                             // 厂商,详见ENUM_VENDOR_TYPE
        public int              emStandard;                           // 视频制式, 详见ENUM_STANDARD_TYPE
        public NET_TIME_EX      stuBuild;                             // 发布日期
        public byte[]           szChip = new byte[NET_COMMON_STRING_16]; // 可升级的程序名
        public byte[]           szSerial = new byte[NET_COMMON_STRING_256]; // 内部型号
        public byte[]           szLanguage = new byte[NET_COMMON_STRING_128]; // 语言
        public byte[]           szSn = new byte[NET_COMMON_STRING_64]; // 序列号
        public byte[]           szSWVersion = new byte[NET_COMMON_STRING_64]; // 软件版本
        public byte[]           szTag = new byte[NET_COMMON_STRING_256]; // 自定义标记
        public byte[]           szTag2 = new byte[NET_COMMON_STRING_256]; // 自定义标记2
        public byte[]           reserved = new byte[1024];
    }

    //获取云升级信息出参
    public static class NET_OUT_UPGRADER_GETSERIAL extends SdkStructure
    {
        public int              dwSize;                               // 此结构体大小
        public NET_UPGRADER_SERIAL_INO[] stuSerialInfo = new NET_UPGRADER_SERIAL_INO[MAX_UPGRADER_SERIAL_INFO]; // 云升级信息
        public int              nRetNum;                              // 返回个数

        public NET_OUT_UPGRADER_GETSERIAL() {
            this.dwSize = this.size();

            for (int i = 0; i < stuSerialInfo.length; ++i) {
                stuSerialInfo[i] = new NET_UPGRADER_SERIAL_INO();
            }
        }
    }

    //从设备获取信息，用于向DH云确认是否有升级包
    public boolean CLIENT_GetUpdateSerial(LLong lLoginID,NET_IN_UPGRADER_GETSERIAL pstuInGetSerial,NET_OUT_UPGRADER_GETSERIAL pstuOutGetSerial,int nWaitTime);

    // 云升级软件检查入参
    public static class NET_IN_CLOUD_UPGRADER_CHECK extends SdkStructure
    {
        public int              dwSize;
        public int              emVendor;                             // 厂商,详见ENUM_VENDOR_TYPE
        public int              emStandard;                           // 视频制式, 详见ENUM_STANDARD_TYPE
        public NET_TIME_EX      stuBuild;                             // 编译时间，用于比较版本
        public byte[]           szUrl = new byte[NET_COMMON_STRING_1024]; // 云URL
        public byte[]           szClass = new byte[NET_COMMON_STRING_64]; // 设备大类
        public byte[]           szSerial = new byte[NET_COMMON_STRING_256]; // 设备硬件信号系列
        public byte[]           szLanguage = new byte[NET_COMMON_STRING_128]; // 语言
        public byte[]           szSN = new byte[NET_COMMON_STRING_64]; // 设备序列号
        public byte[]           szSWVersion = new byte[NET_COMMON_STRING_64]; // 版本号，用于显示
        public byte[]           szTag1 = new byte[NET_COMMON_STRING_256]; // 预留字段，可用于后续或扩展
        public byte[]           szTag2 = new byte[NET_COMMON_STRING_256]; // 预留字段，可用于后续或扩展
        public byte[]           szAccessKeyId = new byte[NET_COMMON_STRING_128]; //Access Key ID
        public byte[]           szSecretAccessKey = new byte[NET_COMMON_STRING_128]; //Secret Access Key

        public NET_IN_CLOUD_UPGRADER_CHECK() {
            this.dwSize = this.size();
        }
    }

    //设备制造商
    public static class ENUM_VENDOR_TYPE extends SdkStructure
    {
        public static final int   ENUM_VENDOR_TYPE_UNKNOWN = 0;         // 未知
        public static final int   ENUM_VENDOR_TYPE_GENERAL = 1;         // General
        public static final int   ENUM_VENDOR_TYPE_DH = 2;              // DH
        public static final int   ENUM_VENDOR_TYPE_OEM = 3;             // OEM
        public static final int   ENUM_VENDOR_TYPE_LC = 4;              // LC
        public static final int   ENUM_VENDOR_TYPE_EZIP = 5;            // EZIP
    }

    //视频制式
    public static class ENUM_STANDARD_TYPE extends SdkStructure
    {
        public static final int   ENUM_STANDARD_TYPE_UNKNOWN = 0;       // 未知
        public static final int   ENUM_STANDARD_TYPE_PAL = 1;           // P-PAL
        public static final int   ENUM_STANDARD_TYPE_NTSC = 2;          // N-NTSC
        public static final int   ENUM_STANDARD_TYPE_PAL_NTSC = 3;      // PN-PAL/NTSC默认P制
        public static final int   ENUM_STANDARD_TYPE_NTSC_PAL = 4;      // NP-NTSC/PAL默认N制
        public static final int   ENUM_STANDARD_TYPE_SECAM = 5;         // S-SECAM
    }

    // 云升级软件检查出参
    public static class NET_OUT_CLOUD_UPGRADER_CHECK extends SdkStructure
    {
        public int              dwSize;
        public int              bHasNewVersion;                       // 是否有可升级版本
        public byte[]           szVersion = new byte[NET_COMMON_STRING_64]; // 版本信息
        public byte[]           szAttention = new byte[NET_COMMON_STRING_1024]; // 提醒设备升级的提示信息
        public byte[]           szPackageUrl = new byte[NET_COMMON_STRING_1024]; // 设备升级包的URL
        public byte[]           szPackageId = new byte[NET_COMMON_STRING_64]; // 升级包ID

        public NET_OUT_CLOUD_UPGRADER_CHECK() {
            this.dwSize = this.size();
        }
    }

    // 检查云端是否有可升级软件, 使用HTTP协议
    public boolean CLIENT_CloudUpgraderCheck(NET_IN_CLOUD_UPGRADER_CHECK pIn,NET_OUT_CLOUD_UPGRADER_CHECK pOut,int dwWaitTime);

    //云下载状态
    public static class emCloudDownloadState extends SdkStructure
    {
        public static final int   emCloudDownloadState_Unknown = 0;     // 未知
        public static final int   emCloudDownloadState_Success = 1;     // 云下载成功(需要关闭句柄)
        public static final int   emCloudDownloadState_Failed = 2;      // 云下载失败(不需要关闭句柄，会不断尝试下载)
        public static final int   emCloudDownloadState_Downloading = 3; // 正在下载中
        public static final int   emCloudDownloadState_NoEnoughDiskSpace = 4; // 磁盘空间不足
    }

    // 云下载回调函数
    public interface fCloudDownload_Process_callback extends Callback {
        public void invoke(LLong lDownHandle,int emState,double dwDownloadSpeed,int dwProgressPercentage,Pointer dwUser);
    }

    // 云升级下载升级包入参
    public static class NET_IN_CLOUD_UPGRADER_DOWN extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szPackageUrl = new byte[NET_COMMON_STRING_1024]; // 设备升级包的URL
        public byte[]           szSaveFile = new byte[NET_COMMON_STRING_1024]; // 保存文件名
        public Callback         pfProcessCallback;                    // 进度回调，实现fCloudDownload_Process_callback
        public Pointer          dwUser;                               // 回调用户数据

        public NET_IN_CLOUD_UPGRADER_DOWN() {
            this.dwSize = this.size();
        }
    }

    // 云升级下载升级包出参
    public static class NET_OUT_CLOUD_UPGRADER_DOWN extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_CLOUD_UPGRADER_DOWN() {
            this.dwSize = this.size();
        }
    }

    // 云 下载升级软件, 使用HTTP协议
    public LLong CLIENT_CloudUpgraderDownLoad(NET_IN_CLOUD_UPGRADER_DOWN pIn,NET_OUT_CLOUD_UPGRADER_DOWN pOut);

    // 停止云下载, 使用HTTP协议
    public boolean CLIENT_CloudUpgraderStop(LLong lDownloadHandle);

    // 暂停云下载, 使用HTTP协议
    public boolean CLIENT_CloudUpgraderPause(LLong lDownloadHandle,int bPause);

    //升级结果
    public static class NET_UPGRADE_REPORT_RESULT extends SdkStructure
    {
        public static final int   NET_UPGRADE_REPORT_RESULT_UNKNWON = 0; // 未知
        public static final int   NET_UPGRADE_REPORT_RESULT_SUCCESS = 1; // 成功
        public static final int   NET_UPGRADE_REPORT_RESULT_FAILED = 2; // 失败
    }

    //上报升级结果结构体
    public static class NET_UPGRADE_REPORT extends SdkStructure
    {
        public int              nDeviceNum;
        public DEVICE_SERIAL[]  szDevSerialArr = (DEVICE_SERIAL[])new DEVICE_SERIAL().toArray(NET_UPGRADE_COUNT_MAX); // 序列号
        public byte[]           szPacketID = new byte[MAX_COMMON_STRING_128]; // 升级包ID
        public int              emResult;                             // 升级结果,详见NET_UPGRADE_REPORT_RESULT
        public byte[]           szCode = new byte[MAX_COMMON_STRING_128]; // 错误码信息
        public byte[]           reserved = new byte[256];
    }

    public static class DEVICE_SERIAL extends SdkStructure
    {
        public byte[]           szDevSerial = new byte[MAX_COMMON_STRING_64]; // 序列号
    }

    //上报升级结果入参
    public static class NET_IN_UPGRADE_REPORT extends SdkStructure
    {
        public int              dwSize;
        public int              nCount;                               // 升级包个数
        public Pointer          pstuUpgradeReport;                    // 升级结果信息 , 大小 nCount * sizeof(NET_UPGRADE_REPORT)
        public byte[]           szAccessKeyId = new byte[NET_COMMON_STRING_128]; //访问ID
        public byte[]           szSecretAccessKey = new byte[NET_COMMON_STRING_128]; //访问秘钥
        public byte[]           szUrl = new byte[NET_COMMON_STRING_1024]; // 云URL

        public NET_IN_UPGRADE_REPORT() {
            this.dwSize = this.size();
        }
    }

    //上报升级结果出参
    public static class NET_OUT_UPGRADE_REPORT extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_UPGRADE_REPORT() {
            this.dwSize = this.size();
        }
    }

    //上报升级结果, 使用HTTP协议
    public boolean CLIENT_CloudUpgraderReport(NET_IN_UPGRADE_REPORT pIn,NET_OUT_UPGRADE_REPORT pOut,int dwWaitTime);

    // 升级状态回调结构体
    public static class NET_CLOUD_UPGRADER_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              emState;                              // 升级状态,详见EM_UPGRADE_STATE
        public int              nProgress;                            // 升级百分比
        public byte[]           szFileName = new byte[NET_COMMON_STRING_128]; // 升级文件
        public long             nTotalLen;                            // 升级文件总大小，单位字节
        public byte[]           szFileNameEx = new byte[256];         // 升级文件扩展
        public byte[]           szReserved = new byte[1024];          // 扩展字段

        public NET_CLOUD_UPGRADER_STATE() {
            this.dwSize = this.size();
        }
    }

    // 升级状态回调函数
    public interface fUpgraderStateCallback extends Callback {
        public void invoke(LLong lLoginId,LLong lAttachHandle,NET_CLOUD_UPGRADER_STATE pBuf,int dwBufLen,Pointer pReserved,Pointer dwUser);
    }

    // 订阅升级状态入参
    public static class NET_IN_CLOUD_UPGRADER_ATTACH_STATE extends SdkStructure
    {
        public int              dwSize;
        public Callback         cbUpgraderState;                      // 升级状态回调实现fUpgraderStateCallback
        public Pointer          dwUser;

        public NET_IN_CLOUD_UPGRADER_ATTACH_STATE() {
            this.dwSize = this.size();
        }
    }

    // 订阅升级状态出参
    public static class NET_OUT_CLOUD_UPGRADER_ATTACH_STATE extends SdkStructure
    {
        public int              dwSize;

        public NET_OUT_CLOUD_UPGRADER_ATTACH_STATE() {
            this.dwSize = this.size();
        }
    }

    // 获取升级状态入参
    public static class NET_IN_CLOUD_UPGRADER_GET_STATE extends SdkStructure
    {
        public int              dwSize;

        public NET_IN_CLOUD_UPGRADER_GET_STATE() {
            this.dwSize = this.size();
        }
    }

    // 获取升级状态出参
    public static class NET_OUT_CLOUD_UPGRADER_GET_STATE extends SdkStructure
    {
        public int              dwSize;
        public int              emState;                              // 升级状态,详见EM_UPGRADE_STATE
        public int              nProgress;                            // 升级进度
        public byte[]           szFileName = new byte[NET_COMMON_STRING_128]; // 升级文

        public NET_OUT_CLOUD_UPGRADER_GET_STATE() {
            this.dwSize = this.size();
        }
    }

    // 升级包和升级状态
    public static class EM_UPGRADE_STATE extends SdkStructure
    {
        public static final int   EM_UPGRADE_STATE_UNKNOWN = 0;         // 未知状态
        public static final int   EM_UPGRADE_STATE_NONE = 1;            // 没有检测到更新状态
        public static final int   EM_UPGRADE_STATE_INVALID = 2;         // 升级包不正确
        public static final int   EM_UPGRADE_STATE_NOT_ENOUGH_MEMORY = 3; // 内存不够
        public static final int   EM_UPGRADE_STATE_DOWNLOADING = 4;     // 正在下载数据
        public static final int   EM_UPGRADE_STATE_DOWNLOAD_FAILED = 5; // 下载失败
        public static final int   EM_UPGRADE_STATE_DOWNLOAD_SUCCESSED = 6; // 下载成功
        public static final int   EM_UPGRADE_STATE_PREPARING = 7;       // 准备升级
        public static final int   EM_UPGRADE_STATE_UPGRADING = 8;       // 升级中
        public static final int   EM_UPGRADE_STATE_UPGRADE_FAILED = 9;  // 升级失败
        public static final int   EM_UPGRADE_STATE_UPGRADE_SUCCESSED = 10; // 升级成功
        public static final int   EM_UPGRADE_STATE_UPGRADE_CANCELLED = 11; // 取消升级
        public static final int   EM_UPGRADE_STATE_FILE_UNMATCH = 12;   // 升级包不匹配
    }

    // 订阅升级状态观察接口
    public LLong CLIENT_CloudUpgraderAttachState(LLong lLoginID,NET_IN_CLOUD_UPGRADER_ATTACH_STATE pInParam,NET_OUT_CLOUD_UPGRADER_ATTACH_STATE pOutParam,int nWaitTime);

    // 退订升级状态观察接口
    public boolean CLIENT_CloudUpgraderDetachState(LLong lAttachHandle);

    // 获取升级状态
    public boolean CLIENT_CloudUpgraderGetState(LLong lLoginID,NET_IN_CLOUD_UPGRADER_GET_STATE pInParam,NET_OUT_CLOUD_UPGRADER_GET_STATE pOutParam,int nWaitTime);

    // 代理服务器地址
    public static class NET_PROXY_SERVER_INFO extends SdkStructure
    {
        public byte[]           szIP = new byte[NET_MAX_IPADDR_LEN_EX]; // IP地址
        public int              nPort;                                // 端口
        public byte[]           byReserved = new byte[84];
    }

    // CLIENT_CheckCloudUpgrader 入参
    public static class NET_IN_CHECK_CLOUD_UPGRADER extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nWay;                                 // 检测路径, 0-直连升级服务器检测, 1-通过代理服务器检测, 2-获取缓存的检测结果
        public NET_PROXY_SERVER_INFO stProxy;                         // 代理服务器地址, way==1时有意义

        public NET_IN_CHECK_CLOUD_UPGRADER() {
            this.dwSize = this.size();
        }
    }

    // 云升级状态
    public static class EM_CLOUD_UPGRADER_CHECK_STATE extends SdkStructure
    {
        public static final int   EM_CLOUD_UPGRADER_CHECK_STATE_UNKNOWN = 0; // 未知
        public static final int   EM_CLOUD_UPGRADER_CHECK_STATE_NONE = 1; // 没有检测到更新
        public static final int   EM_CLOUD_UPGRADER_CHECK_STATE_REGULAR = 2; // 一般升级 (需要用户确认, 只能向高版本)
        public static final int   EM_CLOUD_UPGRADER_CHECK_STATE_EMERGENCY = 3; // 强制升级 (设备自动检测执行, 可以向低版本)
        public static final int   EM_CLOUD_UPGRADER_CHECK_STATE_AUTOMATIC = 4; // 自动升级 (有新升级包, 自动升级, 当前为使用, 需使能页面自动升级选项)
    }

    // 云升级新版本升级包类型
    public static class EM_CLOUD_UPGRADER_PACKAGE_TYPE extends SdkStructure
    {
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_UNKNOWN = 0; // 未知
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_ALL = 1; // 整包
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_PTZ = 2; // 云台主控包
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_WEB = 3; // Web
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_LOGO = 4; // Logo
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_CUSTOM = 5; // Custom
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_GUI = 6; // Gui
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_PD = 7; // PD
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_DATA = 8; // Data
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_PTZ_POWER = 9; // 云台电源
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_PTZ_LIGHT = 10; // 云台灯光
        public static final int   EM_CLOUD_UPGRADER_PACKAGE_TYPE_PTZ_HEATER = 11; // 云台加热器
    }

    // CLIENT_CheckCloudUpgrader 出参
    public static class NET_OUT_CHECK_CLOUD_UPGRADER extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emState;                              // 升级状态,详见EM_CLOUD_UPGRADER_CHECK_STATE
        public int              emPackageType;                        // 新版本升级包类型, State不为None需要返回,详见EM_CLOUD_UPGRADER_PACKAGE_TYPE
        public byte[]           szOldVersion = new byte[MAX_COMMON_STRING_64]; // 旧版本号, State不为None需要返回
        public byte[]           szNewVersion = new byte[MAX_COMMON_STRING_64]; // 新版本号,State不为None需要返回
        public byte[]           szAttention = new byte[NET_COMMON_STRING_2048]; // 新的升级包更新内容
        public byte[]           szPackageURL = new byte[NET_COMMON_STRING_256]; // 升级包下载地址(代理升级需要)
        public byte[]           szPackageID = new byte[MAX_COMMON_STRING_64]; // 升级包ID
        public byte[]           szCheckSum = new byte[MAX_COMMON_STRING_64]; // 升级包的SHA-256校验和
        public byte[]           szBuildTime = new byte[MAX_COMMON_STRING_32]; // 升级包构建时间

        public NET_OUT_CHECK_CLOUD_UPGRADER() {
            this.dwSize = this.size();
        }
    }

    // 在线升级检查是否有可用升级包, pInParam和pOutParam内存由用户申请和释放
    public boolean CLIENT_CheckCloudUpgrader(LLong lLoginID,NET_IN_CHECK_CLOUD_UPGRADER pInParam,NET_OUT_CHECK_CLOUD_UPGRADER pOutParam,int nWaitTime);

    // 升级包信息
    public static class NET_CLOUD_UPGRADER_INFO extends SdkStructure
    {
        public byte[]           szPackageURL = new byte[NET_COMMON_STRING_256]; // 升级包下载地址(代理升级需要)
        public byte[]           szPackageID = new byte[MAX_COMMON_STRING_64]; // 升级包ID
        public byte[]           szCheckSum = new byte[MAX_COMMON_STRING_64]; // 升级包的SHA-256校验和
        public byte[]           byReserved = new byte[1024];          // 保留字节
    }

    // CLIENT_ExecuteCloudUpgrader 入参
    public static class NET_IN_EXECUTE_CLOUD_UPGRADER extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public byte[]           szNewVersion = new byte[MAX_COMMON_STRING_64]; // 上一次check得到的新版本号
        public int              nWay;                                 // 检测路径, 0-直连升级服务器检测, 1-通过代理服务器检测
        public NET_PROXY_SERVER_INFO stProxy;                         // 代理服务器地址, nWay==1时有意义
        public NET_CLOUD_UPGRADER_INFO stInfo;                        // 升级包信息

        public NET_IN_EXECUTE_CLOUD_UPGRADER() {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ExecuteCloudUpgrader 出参
    public static class NET_OUT_EXECUTE_CLOUD_UPGRADER extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_EXECUTE_CLOUD_UPGRADER() {
            this.dwSize = this.size();
        }
    }

    // 执行在线云升级, pInParam和pOutParam内存由用户申请和释放
    public boolean CLIENT_ExecuteCloudUpgrader(LLong lLoginID,NET_IN_EXECUTE_CLOUD_UPGRADER pInParam,NET_OUT_EXECUTE_CLOUD_UPGRADER pOutParam,int nWaitTime);

    // CLIENT_GetCloudUpgraderState 入参
    public static class NET_IN_GET_CLOUD_UPGRADER_STATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_GET_CLOUD_UPGRADER_STATE() {
            this.dwSize = this.size();
        }
    }

    // 在线升级状态
    public static class EM_CLOUD_UPGRADER_STATE extends SdkStructure
    {
        public static final int   EM_CLOUD_UPGRADER_STATE_UNKNOWN = 0;  // 未知
        public static final int   EM_CLOUD_UPGRADER_STATE_NOUPGRADE = 1; // "Noupgrade"-未进行升级
        public static final int   EM_CLOUD_UPGRADER_STATE_PREPARING = 2; // "Preparing"-准备升级
        public static final int   EM_CLOUD_UPGRADER_STATE_DOWNLOADING = 3; // "Downloading"-正在下载数据
        public static final int   EM_CLOUD_UPGRADER_STATE_DOWNLOADFAILED = 4; // "DownloadFailed"-下载失败
        public static final int   EM_CLOUD_UPGRADER_STATE_UPGRADING = 5; // "Upgrading"-正在升级
        public static final int   EM_CLOUD_UPGRADER_STATE_INVALID = 6;  // "Invalid"-升级包不正确
        public static final int   EM_CLOUD_UPGRADER_STATE_FAILED = 7;   // "Failed"-升级包写入Flash失败
        public static final int   EM_CLOUD_UPGRADER_STATE_SUCCEEDED = 8; // "Succeeded"-升级包写入Flash成功
    }

    // CLIENT_GetCloudUpgraderState 出参
    public static class NET_OUT_GET_CLOUD_UPGRADER_STATE extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              emState;                              // 升级状态，详见EM_CLOUD_UPGRADER_STATE
        public int              nProgress;                            // 升级进度, 状态是Downloading/Upgrading时有意义

        public NET_OUT_GET_CLOUD_UPGRADER_STATE() {
            this.dwSize = this.size();
        }
    }

    // 获取云升级在线升级状态, pInParam和pOutParam内存由用户申请和释放
    public boolean CLIENT_GetCloudUpgraderState(LLong lLoginID,NET_IN_GET_CLOUD_UPGRADER_STATE pInParam,NET_OUT_GET_CLOUD_UPGRADER_STATE pOutParam,int nWaitTime);

    // 事件类型 EVENT_IVS_PHONECALL_DETECT(打电话检测事件) 对应的数据块描述信息
    public static class DEV_EVENT_PHONECALL_DETECT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[MAX_EVENT_NAME];    // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nGroupID;                             // 事件组ID
        public int              nCountInGroup;                        // 一个事件组内的抓拍张数
        public int              nIndexInGroup;                        // 一个事件组内的抓拍序号，从1开始
        public int              UTCMS;                                // UTC对应的毫秒数
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      stuDetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public int              nRuleID;                              // 智能事件规则编号
        public int              nObjectNum;                           // 检测到的物体数量
        public DH_MSG_OBJECT[]  stuObjects = (DH_MSG_OBJECT[])new DH_MSG_OBJECT().toArray(128); // 多个检测到的物体信息
        public int              nSerialUUIDNum;                       // 智能物体数量
        public byte[]           szSerialUUID = new byte[128*22];      // 智能物体全局唯一物体标识
        // 有效数据位21位，包含’\0’
        // 前2位%d%d:01-视频片段, 02-图片, 03-文件, 99-其他
        // 中间14位YYYYMMDDhhmmss:年月日时分秒
        // 后5位%u%u%u%u%u：物体ID，如00001
        public int              bSceneImage;                          // stuSceneImage 是否有效
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景广角图
        public byte[]           szUserName = new byte[32];            // 用户名称
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReserved = new byte[188];           // 保留字节
    }

    // 事件类型 EVENT_IVS_SMOKING_DETECT(吸烟检测事件)对应的数据块描述信息
    public static class DEV_EVENT_SMOKING_DETECT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public byte[]           szName = new byte[MAX_EVENT_NAME];    // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nGroupID;                             // 事件组ID
        public int              nCountInGroup;                        // 一个事件组内的抓拍张数
        public int              nIndexInGroup;                        // 一个事件组内的抓拍序号，从1开始
        public int              UTCMS;                                // UTC对应的毫秒数
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      stuDetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public byte             szSerialUUID[] = new byte[22];        //智能物体全局唯一物体标识
        public SCENE_IMAGE_INFO stuSceneImageInfo = new SCENE_IMAGE_INFO(); //全景广角图
        public byte             szUserName[] = new byte[32];          //用户名称
        public NET_IMAGE_INFO_EX2 stuImageInfo[] = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        //图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte             byReserved[] = new byte[1024];        //预留字节
    }

    // 事件类型 EVENT_IVS_FIREWARNING(火警事件) 对应的数据块描述信息
    public static class DEV_EVENT_FIREWARNING_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nAction;                              // 1:开始 2:停止
        public int              nFSID;                                // Uint32	火情编号ID
        public int              emPicType;                            // 图片类型,详见 EM_FIREWARNING_PIC_TYPE
        public int              bIsLeaveFireDetect;                   // 是否属于离岗火点检测(TRUE:是 FALSE:不是)
        public Pointer          pstuImageInfo;                        // 图片信息数组,指针对应结构体NET_IMAGE_INFO_EX2的数组
        public int              nImageInfoNum;                        // 图片信息个数
        public byte[]           byReserved = new byte[1008 - POINTERSIZE]; // 保留字节
    }

    // 图片类型
    public static class EM_FIREWARNING_PIC_TYPE extends SdkStructure
    {
        public static final int   EM_PIC_UNKNOWN = 0;                   // 未知
        public static final int   EM_PIC_NONE = 1;                      // 无
        public static final int   EM_PIC_OVERVIEW = 2;                  // 全景图
        public static final int   EM_PIC_DETAIL = 3;                    // 细节图
    }

    // 事件类型EVENT_IVS_LEFTDETECTION(物品遗留事件)对应的数据块描述信息
    public static class DEV_EVENT_LEFT_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public short            nPreserID;                            // 事件触发的预置点号，从1开始（没有表示未知）
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public byte[]           byReserved2 = new byte[2];            // 字节对齐
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景广角图
        public NET_IMAGE_INFO_EX2[] stuImageInfo = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体

        public DEV_EVENT_LEFT_INFO()
        {
            for (int i = 0; i < DetectRegion.length; ++i) {
                DetectRegion[i] = new NET_POINT();
            }
        }
    }

    // 事件类型 EVENT_IVS_RIOTERDETECTION (聚众事件)对应的数据块描述信息
    public static class DEV_EVENT_RIOTERL_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nObjectNum;                           // 检测到的物体个数
        public NET_MSG_OBJECT[] stuObjectIDs = new NET_MSG_OBJECT[NET_MAX_OBJECT_LIST]; // 检测到的物体列表
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;
        public byte[]           byReserved = new byte[2];             // 保留字节
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public byte[]           szSourceID = new byte[32];            // 事件关联ID。应用场景是同一个物体或者同一张图片做不同分析，产生的多个事件的SourceID相同
        // 缺省时为空字符串，表示无此信息
        // 格式：类型+时间+序列号，其中类型2位，时间14位，序列号5位
        public NET_IMAGE_INFO_EX2[] stuImageInfo = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); // 图片信息数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[328];            // 保留字节,留待扩展.

        public DEV_EVENT_RIOTERL_INFO()
        {
            for (int i = 0; i < stuObjectIDs.length; ++i) {
                stuObjectIDs[i] = new NET_MSG_OBJECT();
            }

            for (int i = 0; i < DetectRegion.length; ++i) {
                DetectRegion[i] = new NET_POINT();
            }
        }
    }

    // 事件类型EVENT_IVS_TAKENAWAYDETECTION(物品搬移事件)对应的数据块描述信息
    public static class DEV_EVENT_TAKENAWAYDETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public short            nPreserID;                            // 事件触发的预置点号，从1开始（没有表示未知）
        public byte[]           szPresetName = new byte[64];          // 事件触发的预置名称
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public NET_IMAGE_INFO_EX2[] stuImageInfo = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); // 图片信息数组
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[418];            // 保留字节,留待扩展.

        public DEV_EVENT_TAKENAWAYDETECTION_INFO()
        {
            for (int i = 0; i < DetectRegion.length; ++i) {
                DetectRegion[i] = new NET_POINT();
            }
        }
    }

    // 事件类型EVENT_IVS_PARKINGDETECTION(非法停车事件)对应的数据块描述信息
    public static class DEV_EVENT_PARKINGDETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public byte[]           szCustomParkNo = new byte[64];        // 车位名称
        public NET_PRESET_POSITION stPosition;                        // 预置点的坐标和放大倍数 是一个数组，每个成员是int类型
																				    // 第一个参数是水平坐标，范围[0,3599]，表示0度到359.9度，度数扩大10倍表示。
																				    // 第二个参数是垂直坐标，范围[-1800,1800]，表示-180.0度到+180.0度，度数扩大10倍表示。
																				    // 第三个参数是放大参数，范围[0,127]，表示最小倍到最大倍的变倍位置
        public int              nCurChannelHFOV;                      // 当前报警通道的横向视场角，单位度，实际角度乘以100
        public int              nCurChannelVFOV;                      // 当前报警通道的纵向视场角，单位度，实际角度乘以100
        public SCENE_IMAGE_INFO_EX stuSceneImage;                     // 全景广角图
        public NET_IMAGE_INFO_EX2[] stuImageInfo = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); // 图片信息数组
        public int              nImageInfoNum;                        // 图片信息个数
        public int              nObjectBoatsNum;                      // 船只物体个数
        public NET_BOAT_OBJECT[] stuBoatObjects = new NET_BOAT_OBJECT[100]; // 船只物品信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[400];            // 保留字节,留待扩展.

        public DEV_EVENT_PARKINGDETECTION_INFO()
        {
            for (int i = 0; i < DetectRegion.length; ++i) {
                DetectRegion[i] = new NET_POINT();
            }
			for (int i = 0; i < stuBoatObjects.length; i++) {
			    stuBoatObjects[i] = new NET_BOAT_OBJECT();
			}
        }
    }

    // 事件类型EVENT_IVS_ABNORMALRUNDETECTION(异常奔跑事件)对应的数据块描述信息
    public static class DEV_EVENT_ABNORMALRUNDETECTION_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public double           dbSpeed;                              // 物体运动速度,km/h
        public double           dbTriggerSpeed;                       // 触发速度,km/h
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              nTrackLineNum;                        // 物体运动轨迹顶点数
        public NET_POINT[]      TrackLine = new NET_POINT[NET_MAX_TRACK_LINE_NUM]; // 物体运动轨迹
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;
        public byte             bRunType;                             // 异常奔跑类型, 0-快速奔跑, 1-突然加速, 2-突然减速
        public byte[]           byReserved = new byte[1];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public Pointer          pstuImageInfo;                        // 图片信息数组 ,结构体NET_IMAGE_INFO_EX2数组指针
        public int              nImageInfoNum;                        // 图片信息个数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           szReserved3 = new byte[4];            //字节对齐
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           bReserved = new byte[600-POINTERSIZE]; // 保留字节,留待扩展.

        public DEV_EVENT_ABNORMALRUNDETECTION_INFO()
        {
            for (int i = 0; i < DetectRegion.length; ++i) {
                DetectRegion[i] = new NET_POINT();
            }

            for (int i = 0; i < TrackLine.length; ++i) {
                TrackLine[i] = new NET_POINT();
            }
        }
    }

    // 设置停车信息,对应CTRLTYPE_CTRL_SET_PARK_INFO命令参数
    public static class NET_CTRL_SET_PARK_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szPlateNumber = new byte[MAX_PLATE_NUMBER_LEN]; // 车牌号码
        public int              nParkTime;                            // 停车时长,单位:分钟
        public byte[]           szMasterofCar = new byte[MAX_MASTER_OF_CAR_LEN]; // 车主姓名
        public byte[]           szUserType = new byte[MAX_USER_TYPE_LEN]; // 用户类型,非通用,用于出入口抓拍一体机
        // monthlyCardUser表示月卡用户,yearlyCardUser表示年卡用户,longTimeUser表示长期用户/,casualUser表示临时用户/Visitor
        public int              nRemainDay;                           // 到期天数
        public byte[]           szParkCharge = new byte[MAX_PARK_CHARGE_LEN]; // 停车费
        public int              nRemainSpace;                         // 停车库余位数
        public int              nPassEnable;                          // 0:不允许车辆通过 1:允许车辆通过
        public NET_TIME         stuInTime;                            // 车辆入场时间
        public NET_TIME         stuOutTime;                           // 车辆出场时间
        public int              emCarStatus;                          // 过车状态 详见EM_CARPASS_STATUS
        public byte[]           szCustom = new byte[MAX_CUSTOM_LEN];  // 自定义显示字段，默认空
        public byte[]           szSubUserType = new byte[MAX_SUB_USER_TYPE_LEN]; // 用户类型（szUserType字段）的子类型
        public byte[]           szRemarks = new byte[MAX_REMARKS_LEN]; // 备注信息
        public byte[]           szResource = new byte[MAX_RESOURCE_LEN]; // 资源文件（视频或图片）视频支持:mp4格式; 图片支持:BMP/jpg/JPG/jpeg/JPEG/png/PNG格式
        public int              nParkTimeout;                         // 停车超时时间，单位分钟。为0表示未超时，不为0表示超时时间。
        public int              nChannel;                             //通道号

        public NET_CTRL_SET_PARK_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 过车状态
    public static class EM_CARPASS_STATUS extends SdkStructure
    {
        public static final int   EM_CARPASS_STATUS_UNKNOWN = 0;        // 未知状态
        public static final int   EM_CARPASS_STATUS_CARPASS = 1;        // 过车状态
        public static final int   EM_CARPASS_STATUS_NORMAL = 2;         // 无车状态
    }

    // 事件类型EVENT_IVS_MOVEDETECTION(移动事件)对应的数据块描述信息
    public static class DEV_EVENT_MOVE_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public byte             bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int              nDetectRegionNum;                     // 规则检测区域顶点数
        public NET_POINT[]      DetectRegion = new NET_POINT[NET_MAX_DETECT_REGION_NUM]; // 规则检测区域
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public int              nSourceIndex;                         // 事件源设备上的index,-1表示数据无效
        public byte[]           szSourceDevice = new byte[MAX_PATH];  // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int              nTrackLineNum;                        // 物体运动轨迹顶点数
        public NET_POINT[]      stuTrackLine = new NET_POINT[NET_MAX_TRACK_LINE_NUM]; // 物体运动轨迹
        public int              nOccurrenceCount;                     // 事件触发累计次数
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;            // 智能事件公共信息
        public NET_EXTENSION_INFO stuExtensionInfo;                   // 扩展信息
        public int              nAnimalNum;                           // 动物个数
        public Pointer          pstuAnimals;                          // 动物信息,结构体VA_OBJECT_ANIMAL数组指针
        public int              nMsgObjArrayCount;                    // 检测到的物体信息个数
        public Pointer          pMsgObjArray;                         // 检测到的物体信息数组指针，结构体NET_MSG_OBJECT_EX数组指针
        public int              nImageNum;                            // 图片信息个数
        public Pointer          pImageArray;                          // 图片信息数组，结构体NET_IMAGE_INFO_EX2数组指针
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           bReserved = new byte[236];            // 保留字节,留待扩展.

        public DEV_EVENT_MOVE_INFO()
        {
            for (int i = 0; i < DetectRegion.length; ++i) {
                DetectRegion[i] = new NET_POINT();
            }

            for (int i = 0; i < stuTrackLine.length; ++i) {
                stuTrackLine[i] = new NET_POINT();
            }
        }
    }

    // 监测控制和数据采集设备的点位表路径信息输入参数, 查询条件
    public static class NET_IN_SCADA_POINT_LIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDevType = new byte[NET_COMMON_STRING_64]; // 设备类型

        public NET_IN_SCADA_POINT_LIST_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 点位表路径信息
    public static class NET_SCADA_POINT_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              nIndexValidNum;                       // 有效的配置下标个数
        public int[]            nIndex = new int[MAX_SCADA_POINT_LIST_INDEX]; // SCADADev配置下标值, 从0开始
        public byte[]           szPath = new byte[NET_COMMON_STRING_256]; // 点表的完整路径

        public NET_SCADA_POINT_LIST()
        {
            this.dwSize = this.size();
        }
    }

    // 监测控制和数据采集设备的点位表路径信息输出参数, 查询结果
    public static class NET_OUT_SCADA_POINT_LIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nList;                                // 有效点位表路径信息个数
        public NET_SCADA_POINT_LIST[] stuList = new NET_SCADA_POINT_LIST[MAX_SCADA_POINT_LIST_INFO_NUM]; // 点位表路径信息

        public NET_OUT_SCADA_POINT_LIST_INFO()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuList.length; ++i) {
                stuList[i] = new NET_SCADA_POINT_LIST();
            }
        }
    }

    // 监测控制和数据采集设备的点位表信息, (对应 DH_DEVSTATE_SCADA_POINT_LIST 命令)
    public static class NET_SCADA_POINT_LIST_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_IN_SCADA_POINT_LIST_INFO stuIn;                    // 查询条件
        public NET_OUT_SCADA_POINT_LIST_INFO stuOut;                  // 查询结果

        public NET_SCADA_POINT_LIST_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // SCADA监测点位查询条件
    public static class NET_IN_SCADA_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emPointType;                          // 待查询的点位类型，详见EM_NET_SCADA_POINT_TYPE

        public NET_IN_SCADA_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 点表信息
    public static class NET_SCADA_POINT_INFO extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDevName = new byte[NET_COMMON_STRING_64]; // 设备名称
        public int              nYX;                                  // 有效遥信个数
        public int[]            anYX = new int[MAX_SCADA_YX_NUM];     // 遥信信息
        public int              nYC;                                  // 有效遥测个数
        public float[]          afYC = new float[MAX_SCADA_YC_NUM];   // 遥测信息

        public NET_SCADA_POINT_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // SCADA监测点位查询结果
    public static class NET_OUT_SCADA_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              nPointInfoNum;                        // 有效点表个数
        public NET_SCADA_POINT_INFO[] stuPointInfo = new NET_SCADA_POINT_INFO[MAX_SCADA_POINT_INFO_NUM]; // 点表信息

        public NET_OUT_SCADA_INFO()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuPointInfo.length; ++i) {
                stuPointInfo[i] = new NET_SCADA_POINT_INFO();
            }
        }
    }

    // 监测控制和数据采集设备的监测点位信息(对应 DH_DEVSTATE_SCADA_INFO 命令)
    public static class NET_SCADA_INFO extends SdkStructure
    {
        public int              dwSize;
        public NET_IN_SCADA_INFO stuIn;                               // 查询条件
        public NET_OUT_SCADA_INFO stuOut;                             // 查询结果

        public NET_SCADA_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // SCADA能力名称
    public static class EM_NET_SCADA_CAPS_TYPE extends SdkStructure
    {
        public static final int   EM_NET_SCADA_CAPS_TYPE_UNKNOWN = 0;
        public static final int   EM_NET_SCADA_CAPS_TYPE_ALL = 1;       // 所有类型
        public static final int   EM_NET_SCADA_CAPS_TYPE_DEV = 2;       // DevInfo
    }

    // 监测控制和数据采集设备能力信息查询条件
    public static class NET_IN_SCADA_CAPS extends SdkStructure
    {
        public int              dwSize;
        public int              emType;                               // 查询类型，详见EM_NET_SCADA_CAPS_TYPE

        public NET_IN_SCADA_CAPS()
        {
            this.dwSize = this.size();
        }
    }

    // 监测控制和数据采集设备类型能力信息
    public static class NET_OUT_SCADA_CAPS_ITEM extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDevType = new byte[NET_COMMON_STRING_32]; // 设备类型
        public int              nValidName;                           // 有效设备名称个数
        public SCADA_DEVICE_NAME[] stuScadaDevNames = new SCADA_DEVICE_NAME[MAX_NET_SCADA_CAPS_NAME]; // 设备名称, 唯一标示设备

        public NET_OUT_SCADA_CAPS_ITEM()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuScadaDevNames.length; ++i) {
                stuScadaDevNames[i] = new SCADA_DEVICE_NAME();
            }
        }
    }

    public static class SCADA_DEVICE_NAME extends SdkStructure
    {
        public byte[]           szDevName = new byte[NET_COMMON_STRING_32]; // 数据采集设备名称
    }

    // 监测控制和数据采集设备能力信息查询结果
    public static class NET_OUT_SCADA_CAPS extends SdkStructure
    {
        public int              dwSize;
        public int              nValidType;                           // 有效设备类型个数
        public NET_OUT_SCADA_CAPS_ITEM[] stuItems = new NET_OUT_SCADA_CAPS_ITEM[MAX_NET_SCADA_CAPS_TYPE]; // 最多16个类型

        public NET_OUT_SCADA_CAPS()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuItems.length; ++i) {
                stuItems[i] = new NET_OUT_SCADA_CAPS_ITEM();
            }
        }
    }

    // 监测控制和数据采集设备能力信息(对应 DH_DEVSTATE_SCADA_CAPS 命令)
    public static class NET_SCADA_CAPS extends SdkStructure
    {
        public int              dwSize;
        public NET_IN_SCADA_CAPS stuIn;                               // 查询条件
        public NET_OUT_SCADA_CAPS stuOut;                             // 查询结果

        public NET_SCADA_CAPS()
        {
            this.dwSize = this.size();
        }
    }

    // 点位信息(通过设备、传感器点位获取)
    public static class NET_SCADA_POINT_BY_ID_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emType;                               // 点位类型，详见EM_NET_SCADA_POINT_TYPE
        public byte[]           szID = new byte[NET_COMMON_STRING_64]; // 监测点位ID
        public int              nMeasuredVal;                         // 点位类型为YX时有效
        public float            fMeasureVal;                          // 点位类型为YC时有效
        public int              nSetupVal;                            // 点位类型为YK时有效
        public float            fSetupVal;                            // 点位类型为YT时有效
        public int              nStatus;                              // 数据状态, -1:未知, 0:正常, 1:1级告警, 2:2级告警, 3:3级告警, 4:4级告警, 5:操作事件, 6:无效数据
        public NET_TIME         stuTime;                              // 记录时间
        public byte[]           szPointName = new byte[32];           // 点位名称

        public NET_SCADA_POINT_BY_ID_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 通过设备、获取监测点位信息(对应 NET_SCADA_INFO_BY_ID)
    public static class NET_SCADA_INFO_BY_ID extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szSensorID = new byte[NET_COMMON_STRING_64]; // 输入参数, 探测器ID
        public int              nIDs;                                 // 输入参数, 有效点位ID个数
        public SCADA_ID_EX[]    stuIDs = new SCADA_ID_EX[MAX_SCADA_ID_OF_SENSOR_NUM]; // 输入参数, 点位ID
        public int              bIsHandle;                            // 输入参数，返回数据是否经过处理（无效数据过滤等）:"false"：未处理，"true"：处理
        public int              nMaxCount;                            // 输入参数, pstuInfo对应数组个数
        public int              nRetCount;                            // 输出参数, pstInfo实际返回有效个数, 可能大于用户分配个数nMaxCount
        public Pointer          pstuInfo;                             // 输入输出参数, 用户分配缓存,大小为sizeof(NET_SCADA_POINT_BY_ID_INFO)*nMaxCount，指向NET_SCADA_POINT_BY_ID_INFO

        public NET_SCADA_INFO_BY_ID()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuIDs.length; ++i) {
                stuIDs[i] = new SCADA_ID_EX();
            }
        }
    }

    public static class SCADA_ID_EX extends SdkStructure
    {
        public byte[]           szID = new byte[NET_COMMON_STRING_64]; // 监测点位ID
    }

    // 监测设备信息
    public static class NET_SCADA_DEVICE_ID_INFO extends SdkStructure
    {
        public byte[]           szDeviceID = new byte[NET_COMMON_STRING_64]; // 设备id
        public byte[]           szDevName = new byte[NET_COMMON_STRING_64]; // 设备名称, 和CFG_SCADA_DEV_INFO配置中的szDevName一致
        public byte[]           reserve = new byte[1024];
    }

    // 获取当前主机所接入的外部设备ID
    public static class NET_SCADA_DEVICE_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              nMax;                                 // 用户分配的结构体个数
        public int              nRet;                                 // 设备实际返回的有效结构体个数
        public Pointer          pstuDeviceIDInfo;                     // 监测设备信息,用户分配内存,大小为sizeof(NET_SCADA_DEVICE_ID_INFO)*nMax，指向NET_SCADA_DEVICE_ID_INFO

        public NET_SCADA_DEVICE_LIST()
        {
            this.dwSize = this.size();
        }
    }

    // 点位阈值信息
    public static class NET_SCADA_ID_THRESHOLD_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emPointType;                          // 点位类型，详见EM_NET_SCADA_POINT_TYPE
        public byte[]           szID = new byte[NET_COMMON_STRING_32]; // 点位ID
        public float            fThreshold;                           // 告警门限
        public float            fAbsoluteValue;                       // 绝对阈值
        public float            fRelativeValue;                       // 相对阈值
        public int              nStatus;                              // 数据状态, -1:未知, 0:正常, 1:1级告警, 2:2级告警, 3:3级告警, 4:4级告警, 5:操作事件, 6:无效数据

        public NET_SCADA_ID_THRESHOLD_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SCADAGetThreshold输入参数
    public static class NET_IN_SCADA_GET_THRESHOLD extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDeviceID = new byte[NET_COMMON_STRING_64]; // 外接设备id
        public int              nIDs;                                 // 有效监测点位个数
        public SCADA_ID[]       stuIDs = new SCADA_ID[MAX_SCADA_ID_NUM]; // 待获取的监测点位ID

        public NET_IN_SCADA_GET_THRESHOLD()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuIDs.length; ++i) {
                stuIDs[i] = new SCADA_ID();
            }
        }
    }

    public static class SCADA_ID extends SdkStructure
    {
        public byte[]           szID = new byte[NET_COMMON_STRING_32]; // 监测点位ID
    }

    // CLIENT_SCADAGetThreshold输出参数
    public static class NET_OUT_SCADA_GET_THRESHOLD extends SdkStructure
    {
        public int              dwSize;
        public int              nMax;                                 // 用户分配的点位阈值信息个数
        public int              nRet;                                 // 实际返回的点位阈值信息
        public Pointer          pstuThresholdInfo;                    // 点位阈值信息, 用户分配内存,大小为sizeof(NET_SCADA_ID_THRESHOLD_INFO)*nMax，指向NET_SCADA_ID_THRESHOLD_INFO

        public NET_OUT_SCADA_GET_THRESHOLD()
        {
            this.dwSize = this.size();
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // CLIENT_SCADASetThreshold输入参数
    public static class NET_IN_SCADA_SET_THRESHOLD extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDeviceID = new byte[NET_COMMON_STRING_64]; // 外接设备id
        public int              nMax;                                 // 用户分配的点位阈值信息个数
        public Pointer          pstuThresholdInfo;                    // 点位阈值信息, 用户分配内存,大小为sizeof(NET_SCADA_ID_THRESHOLD_INFO)*nMax，指向NET_SCADA_ID_THRESHOLD_INFO

        public NET_IN_SCADA_SET_THRESHOLD()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_SCADASetThreshold输出参数
    public static class NET_OUT_SCADA_SET_THRESHOLD extends SdkStructure
    {
        public int              dwSize;
        public int              nSuccess;                             // 有效的存放设置阈值成功的id个数
        public SCADA_ID[]       stuSuccessIDs = new SCADA_ID[MAX_SCADA_ID_NUM]; // 设置阈值成功的id,用户分配内存
        public int              nFail;                                // 用户分配的存放设置阈值失败的id个数
        public SCADA_ID[]       stuFailIDs = new SCADA_ID[MAX_SCADA_ID_NUM]; // 设置阈值失败的id, 用户分配内存

        public NET_OUT_SCADA_SET_THRESHOLD()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuSuccessIDs.length; ++i) {
                stuSuccessIDs[i] = new SCADA_ID();
            }

            for (int i = 0; i < stuFailIDs.length; ++i) {
                stuFailIDs[i] = new SCADA_ID();
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // CLIENT_StartFindSCADA输入参数
    public static class NET_IN_SCADA_START_FIND extends SdkStructure
    {
        public int              dwSize;
        public NET_TIME         stuStartTime;                         // 开始时间, 必填
        public int              bEndTime;                             // 是否限制结束时间, TRUE: 必填stuEndTime, FLASE: 不限制结束时间
        public NET_TIME         stuEndTime;                           // 结束时间
        public byte[]           szDeviceID = new byte[NET_COMMON_STRING_64]; // DeviceID, 必填
        public byte[]           szID = new byte[NET_COMMON_STRING_32]; // 监测点位ID, 必填
        public int              nIDsNum;                              // 监测点ID数组长度
        public SCADA_ID[]       stuIDs = new SCADA_ID[32];            // 监控点ID号数组，SDT离网供电扩展字段

        public NET_IN_SCADA_START_FIND()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuIDs.length; ++i) {
                stuIDs[i] = new SCADA_ID();
            }
        }
    }

    // CLIENT_StartFindSCADA输出参数
    public static class NET_OUT_SCADA_START_FIND extends SdkStructure
    {
        public int              dwSize;
        public int              dwTotalCount;                         // 符合查询条件的总数

        public NET_OUT_SCADA_START_FIND()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindSCADA输入参数
    public static class NET_IN_SCADA_DO_FIND extends SdkStructure
    {
        public int              dwSize;
        public int              nStartNo;                             // 起始序号
        public int              nCount;                               // 本次欲获得结果的个数

        public NET_IN_SCADA_DO_FIND()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_DoFindSCADA输出参数
    public static class NET_OUT_SCADA_DO_FIND extends SdkStructure
    {
        public int              dwSize;
        public int              nRetNum;                              // 本次查询到的个数
        public Pointer          pstuInfo;                             // 查询结果, 用户分配内存,大小为sizeof(NET_SCADA_POINT_BY_ID_INFO)*nMaxNum，指向NET_SCADA_POINT_BY_ID_INFO
        public int              nMaxNum;                              // 用户分配内存的个数

        public NET_OUT_SCADA_DO_FIND()
        {
            this.dwSize = this.size();
        }
    }

    // 监控点值设置参数
    public static class NET_SCADA_POINT_SET_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emType;                               // 监控点位类型,取YK、YT两种类型，详见EM_NET_SCADA_POINT_TYPE
        public byte[]           szPointID = new byte[NET_COMMON_STRING_64]; // 监控点位ID
        public int              nSetupVal;                            // 点位类型为YK时有效
        public float            fSetupVal;                            // 点位类型为YT时有效

        public NET_SCADA_POINT_SET_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 监控点值设置参数列表,CLIENT_SetSCADAInfo()接口输入参数
    public static class NET_IN_SCADA_POINT_SET_INFO_LIST extends SdkStructure
    {
        public int              dwSize;
        public byte[]           szDevID = new byte[NET_COMMON_STRING_32]; // 设备ID
        public int              nPointNum;                            // 监控点个数
        public NET_SCADA_POINT_SET_INFO[] stuList = new NET_SCADA_POINT_SET_INFO[MAX_SCADA_ID_OF_SENSOR_NUM]; // 监控点列表信息

        public NET_IN_SCADA_POINT_SET_INFO_LIST()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuList.length; ++i) {
                stuList[i] = new NET_SCADA_POINT_SET_INFO();
            }
        }
    }

    // 设置监控点值返回的结果列表,CLIENT_SetSCADAInfo()接口输出参数
    public static class NET_OUT_SCADA_POINT_SET_INFO_LIST extends SdkStructure
    {
        public int              dwSize;
        public int              nSuccess;                             // 有效的控制或调节成功的ID个数
        public SCADA_ID[]       stuSuccessIDs = new SCADA_ID[MAX_SCADA_ID_OF_SENSOR_NUM]; // 控制或调节成功的ID的列表
        public int              nFail;                                // 有效的控制或调节失败的ID个数
        public SCADA_ID[]       stuFailIDs = new SCADA_ID[MAX_SCADA_ID_OF_SENSOR_NUM]; // 控制或调节失败的ID的列表

        public NET_OUT_SCADA_POINT_SET_INFO_LIST()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuSuccessIDs.length; ++i) {
                stuSuccessIDs[i] = new SCADA_ID();
            }

            for (int i = 0; i < stuFailIDs.length; ++i) {
                stuFailIDs[i] = new SCADA_ID();
            }
        }
    }

    // 获取阈值,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_SCADAGetThreshold(LLong lLoginID,NET_IN_SCADA_GET_THRESHOLD pInParam,NET_OUT_SCADA_GET_THRESHOLD pOutParam,int nWaitTime);

    // 设置阈值,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_SCADASetThreshold(LLong lLoginID,NET_IN_SCADA_SET_THRESHOLD pInParam,NET_OUT_SCADA_SET_THRESHOLD pOutParam,int nWaitTime);

    // 开始查询SCADA点位历史数据,pInParam与pOutParam内存由用户申请释放
    public LLong CLIENT_StartFindSCADA(LLong lLoginID,NET_IN_SCADA_START_FIND pInParam,NET_OUT_SCADA_START_FIND pOutParam,int nWaitTime);

    // 获取SCADA点位历史数据,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_DoFindSCADA(LLong lFindHandle,NET_IN_SCADA_DO_FIND pInParam,NET_OUT_SCADA_DO_FIND pOutParam,int nWaitTime);

    // 停止查询SCADA点位历史数据
    public boolean CLIENT_StopFindSCADA(LLong lFindHandle);

    // 设置监测点位信息,pInParam与pOutParam内存由用户申请释放
    public boolean CLIENT_SCADASetInfo(LLong lLoginID,NET_IN_SCADA_POINT_SET_INFO_LIST pInParam,NET_OUT_SCADA_POINT_SET_INFO_LIST pOutParam,int nWaitTime);

    public static class CFG_SCADA_DEV_INFO extends SdkStructure
    {
        public int              bEnable;                              // 是否启用
        public byte[]           szDevType = new byte[CFG_COMMON_STRING_64]; // 设备类型
        public byte[]           szDevName = new byte[CFG_COMMON_STRING_64]; // 设备名称, 唯一标示设备用
        public int              nSlot;                                // 虚拟槽位号, 详见AlarmSlotBond配置
        public int              nLevel;                               // 如果Slot绑定的是NetCollection类型的话，该字段为-1
        public CFG_ALARM_MSG_HANDLE stuEventHandler;                  // 报警联动
    }

    // 高频次报警
    public static class CFG_HIGH_FREQUENCY extends SdkStructure
    {
        public int              nPeriod;                              // 统计周期, 以秒为单位, 默认30分钟(1800s)
        public int              nMaxCount;                            // 在对应统计周期内最大允许上报报警数
    }

    // 告警屏蔽规则配置(对应 CFG_CMD_ALARM_SHIELD_RULE)
    public static class CFG_ALARM_SHIELD_RULE_INFO extends SdkStructure
    {
        public CFG_HIGH_FREQUENCY stuHighFreq;                        // 高频次报警, 在一定周期内允许上报的报警次数，以此过滤对于报警的频繁上报导致信息干扰
    }

    // 获取车位锁状态接口，CLIENT_GetParkingLockState 入参
    public static class NET_IN_GET_PARKINGLOCK_STATE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_IN_GET_PARKINGLOCK_STATE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 车位锁状态
    public static class EM_STATE_TYPE extends SdkStructure
    {
        public static final int   EM_STATE_TYPE_UNKNOW = 0;             // 未知
        public static final int   EM_STATE_TYPE_LOCKRISE = 1;           // 车位锁升起
        public static final int   EM_STATE_TYPE_LOCKDOWN = 2;           // 车位锁降下
        public static final int   EM_STATE_TYPE_LOCKERROR = 3;          // 车位锁异常
    }

    // 车位锁状态通信接口参数
    public static class NET_STATE_LIST_INFO extends SdkStructure
    {
        public int              nLane;                                // 车位号
        public int              emState;                              // 车位锁状态，详见EM_STATE_TYPE
        public byte[]           byReserved = new byte[256];           // 保留
    }

    // 获取车位锁状态， CLIENT_GetParkingLockState 出参
    public static class NET_OUT_GET_PARKINGLOCK_STATE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nStateListNum;                        // 车位锁状态个数
        public NET_STATE_LIST_INFO[] stuStateList = new NET_STATE_LIST_INFO[MAX_PARKINGLOCK_STATE_NUM]; // 车位锁状态

        public NET_OUT_GET_PARKINGLOCK_STATE_INFO()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuStateList.length; ++i) {
                stuStateList[i] = new NET_STATE_LIST_INFO();
            }
        }
    }

    // 设置车位锁状态接口，CLIENT_SetParkingLockState 入参
    public static class NET_IN_SET_PARKINGLOCK_STATE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nStateListNum;                        // 车位锁状态个数
        public NET_STATE_LIST_INFO[] stuStateList = new NET_STATE_LIST_INFO[MAX_PARKINGLOCK_STATE_NUM]; // 车位锁状态
        public int              nControlType;                         // 控制车位锁状态类型, 0:未知, 1:平台正常控制, 2:平台手动控制

        public NET_IN_SET_PARKINGLOCK_STATE_INFO()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuStateList.length; ++i) {
                stuStateList[i] = new NET_STATE_LIST_INFO();
            }
        }
    }

    // 设置车位锁状态接口，CLIENT_SetParkingLockState 出参
    public static class NET_OUT_SET_PARKINGLOCK_STATE_INFO extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_SET_PARKINGLOCK_STATE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 获取车位锁状态
    public boolean CLIENT_GetParkingLockState(LLong lLoginID,NET_IN_GET_PARKINGLOCK_STATE_INFO pstInParam,NET_OUT_GET_PARKINGLOCK_STATE_INFO pstOutParam,int nWaitTime);

    // 设置车位锁状态
    public boolean CLIENT_SetParkingLockState(LLong lLoginID,NET_IN_SET_PARKINGLOCK_STATE_INFO pstInParm,NET_OUT_SET_PARKINGLOCK_STATE_INFO pstOutParam,int nWaitTIme);

    // 刻录配置
    public static class CFG_JUDICATURE_INFO extends SdkStructure
    {
        public byte[]           szDiskPassword = new byte[MAX_PASSWORD_LEN]; // 光盘密码(废弃, 使用szPassword和nPasswordLen)
        public byte[]           szCaseNo = new byte[MAX_OSD_SUMMARY_LEN]; // 案件编号
        public int              bCaseNoOsdEn;                         // 案件编号叠加使能
        public byte[]           szCaseTitle = new byte[MAX_OSD_SUMMARY_LEN]; // 案件名称
        public int              bCaseTitleOsdEn;                      // 案件名称叠加使能
        public byte[]           szOfficer = new byte[MAX_OSD_SUMMARY_LEN]; // 办案人员
        public int              bOfficerOsdEn;                        // 办案人员叠加使能
        public byte[]           szLocation = new byte[MAX_OSD_SUMMARY_LEN]; // 办案地点
        public int              bLocationOsdEn;                       // 办案地点叠加使能
        public byte[]           szRelatedMan = new byte[MAX_OSD_SUMMARY_LEN]; // 涉案人员
        public int              bRelatedManOsdEn;                     // 涉案人员叠加使能
        public byte[]           szDiskNo = new byte[MAX_OSD_SUMMARY_LEN]; // 光盘编号
        public int              bDiskNoOsdEn;                         // 光盘编号叠加使能
        public int              bCustomCase;                          // TRUE:自定义案件信息,FALSE: 上边szCaseNo等字段有效
        public int              nCustomCase;                          // 实际CFG_CUSTOMCASE个数
        public CFG_CUSTOMCASE[] stuCustomCases = new CFG_CUSTOMCASE[MAX_CUSTOMCASE_NUM]; // 自定义案件信息
        public int              bDataCheckOsdEn;                      // 光盘刻录数据校验配置 叠加使能
        public int              bAttachFileEn;                        // 附件上传使能
        public byte[]           szPassword = new byte[MAX_PASSWORD_LEN]; // 密码, 刻录光盘时、配置读保护密码
        public int              nPasswordLen;                         // 密码长度
        public CFG_NET_TIME     stuStartTime;                         // 片头信息叠加开始时间
        public int              nPeriod;                              // 片头信息叠加时间长度，单位：分钟

        public CFG_JUDICATURE_INFO()
        {
            for (int i = 0; i < stuCustomCases.length; ++i) {
                stuCustomCases[i] = new CFG_CUSTOMCASE();
            }
        }
    }

    // 自定义案件信息
    public static class CFG_CUSTOMCASE extends SdkStructure
    {
        public byte[]           szCaseTitle = new byte[MAX_OSD_TITLE_LEN]; // 案件名称
        public byte[]           szCaseContent = new byte[MAX_OSD_SUMMARY_LEN]; // 案件名称
        public int              bCaseNoOsdEn;                         // 案件编号叠加使能
    }

    // 叠加类型
    public static class NET_EM_OSD_BLEND_TYPE extends SdkStructure
    {
        public static final int   NET_EM_OSD_BLEND_TYPE_UNKNOWN = 0;    // 未知叠加类型
        public static final int   NET_EM_OSD_BLEND_TYPE_MAIN = 1;       // 叠加到主码流
        public static final int   NET_EM_OSD_BLEND_TYPE_EXTRA1 = 2;     // 叠加到辅码流1
        public static final int   NET_EM_OSD_BLEND_TYPE_EXTRA2 = 3;     // 叠加到辅码流2
        public static final int   NET_EM_OSD_BLEND_TYPE_EXTRA3 = 4;     // 叠加到辅码流3
        public static final int   NET_EM_OSD_BLEND_TYPE_SNAPSHOT = 5;   // 叠加到抓图
        public static final int   NET_EM_OSD_BLEND_TYPE_PREVIEW = 6;    // 叠加到预览视频
    }

    // 编码物件-通道标题
    public static class NET_OSD_CHANNEL_TITLE extends SdkStructure
    {
        public int              dwSize;
        public int              emOsdBlendType;                       // 叠加类型，不管是获取还是设置都要设置该字段，详见NET_EM_OSD_BLEND_TYPE
        public int              bEncodeBlend;                         // 是否叠加
        public NET_COLOR_RGBA   stuFrontColor;                        // 前景色
        public NET_COLOR_RGBA   stuBackColor;                         // 背景色
        public NET_RECT         stuRect;                              // 区域, 坐标取值[0~8191], 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public int              emTextAlign;                          // 文本对齐方式 ,参考枚举{@link EM_TITLE_TEXT_ALIGNTYPE}

        public NET_OSD_CHANNEL_TITLE()
        {
            this.dwSize = this.size();
        }
    }

    // 编码物件-时间标题
    public static class NET_OSD_TIME_TITLE extends SdkStructure
    {
        public int              dwSize;
        public int              emOsdBlendType;                       // 叠加类型，不管是获取还是设置都要设置该字段，详见NET_EM_OSD_BLEND_TYPE
        public int              bEncodeBlend;                         // 是否叠加
        public NET_COLOR_RGBA   stuFrontColor;                        // 前景色
        public NET_COLOR_RGBA   stuBackColor;                         // 背景色
        public NET_RECT         stuRect;                              // 区域, 坐标取值[0~8191], 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public int              bShowWeek;                            // 是否显示星期

        public NET_OSD_TIME_TITLE()
        {
            this.dwSize = this.size();
        }
    }

    // 编码物件-自定义标题信息
    public static class NET_CUSTOM_TITLE_INFO extends SdkStructure
    {
        public int              bEncodeBlend;                         // 是否叠加
        public NET_COLOR_RGBA   stuFrontColor;                        // 前景色
        public NET_COLOR_RGBA   stuBackColor;                         // 背景色
        public NET_RECT         stuRect;                              // 区域, 坐标取值[0~8191], 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public byte[]           szText = new byte[CUSTOM_TITLE_LEN];  // 标题内容
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // 编码物件-自定义标题
    public static class NET_OSD_CUSTOM_TITLE extends SdkStructure
    {
        public int              dwSize;
        public int              emOsdBlendType;                       // 叠加类型，不管是获取还是设置都要设置该字段，详见NET_EM_OSD_BLEND_TYPE
        public int              nCustomTitleNum;                      // 自定义标题数量
        public NET_CUSTOM_TITLE_INFO[] stuCustomTitle = new NET_CUSTOM_TITLE_INFO[MAX_CUSTOM_TITLE_NUM]; // 自定义标题

        public NET_OSD_CUSTOM_TITLE()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuCustomTitle.length; ++i) {
                stuCustomTitle[i] = new NET_CUSTOM_TITLE_INFO();
            }
        }
    }

    // 标题文本对齐方式
    public static class EM_TITLE_TEXT_ALIGNTYPE extends SdkStructure
    {
        public static final int   EM_TEXT_ALIGNTYPE_INVALID = 0;        // 无效的对齐方式
        public static final int   EM_TEXT_ALIGNTYPE_LEFT = 1;           // 左对齐
        public static final int   EM_TEXT_ALIGNTYPE_XCENTER = 2;        // X坐标中对齐
        public static final int   EM_TEXT_ALIGNTYPE_YCENTER = 3;        // Y坐标中对齐
        public static final int   EM_TEXT_ALIGNTYPE_CENTER = 4;         // 居中
        public static final int   EM_TEXT_ALIGNTYPE_RIGHT = 5;          // 右对齐
        public static final int   EM_TEXT_ALIGNTYPE_TOP = 6;            // 按照顶部对齐
        public static final int   EM_TEXT_ALIGNTYPE_BOTTOM = 7;         // 按照底部对齐
        public static final int   EM_TEXT_ALIGNTYPE_LEFTTOP = 8;        // 按照左上角对齐
        public static final int   EM_TEXT_ALIGNTYPE_CHANGELINE = 9;     // 换行对齐
    }

    // 自定义标题文本对齐
    public static class NET_OSD_CUSTOM_TITLE_TEXT_ALIGN extends SdkStructure
    {
        public int              dwSize;
        public int              nCustomTitleNum;                      // 自定义标题数量
        public int[]            emTextAlign = new int[MAX_CUSTOM_TITLE_NUM]; // 自定义标题文本对齐方式，详见EM_TITLE_TEXT_ALIGNTYPE

        public NET_OSD_CUSTOM_TITLE_TEXT_ALIGN()
        {
            this.dwSize = this.size();
        }
    }

    //编码物件-公共配置信息
    public static class NET_OSD_COMM_INFO extends SdkStructure
    {
        public int              dwSize;
        public double           fFontSizeScale;                       // 叠加字体大小放大比例
        //当fFontSizeScale≠0时,nFontSize不起作用
        //当fFontSizeScale=0时,nFontSize起作用
        //设备默认fFontSizeScale=1.0
        //如果需要修改倍数，修改该值
        //如果需要按照像素设置，则置该值为0，nFontSize的值生效
        public int              nFontSize;                            // 叠加到主码流上的全局字体大小,单位 px, 默认24.
        //和fFontSizeScale共同作用
        public int              nFontSizeExtra1;                      // 叠加到辅码流1上的全局字体大小,单位 px
        public int              nFontSizeExtra2;                      // 叠加到辅码流2上的全局字体大小,单位 px
        public int              nFontSizeExtra3;                      // 叠加到辅码流3上的全局字体大小,单位 px
        public int              nFontSizeSnapshot;                    // 叠加到抓图流上的全局字体大小, 单位 px
        public int              nFontSizeMergeSnapshot;               // 叠加到抓图流上合成图片的字体大小,单位 px
        public int              emFontSolution;                       // 叠加到主码流上的字体方案,参考枚举{@link EM_OSD_FONT_SOLUTION}
        public int              emFontSolutionExtra1;                 // 叠加到辅码流1上的字体方案,参考枚举{@link EM_OSD_FONT_SOLUTION}
        public int              emFontSolutionExtra2;                 // 叠加到辅码流2上的字体方案,参考枚举{@link EM_OSD_FONT_SOLUTION}
        public int              emFontSolutionExtra3;                 // 叠加到辅码流3上的字体方案,参考枚举{@link EM_OSD_FONT_SOLUTION}
        public int              emFontSolutionSnapshot;               // 叠加到抓图码流上的字体方案,参考枚举{@link EM_OSD_FONT_SOLUTION}
        public int              emFontSolutionMergeSnapshot;          // 叠加到合成抓图流上的字体方案,参考枚举{@link EM_OSD_FONT_SOLUTION}

        public NET_OSD_COMM_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 播报单元类型
    public static class NET_PLAYAUDIO_TYPE extends SdkStructure
    {
        public static final int   NET_PLAYAUDIO_TYPE_UNKNOWN = 0;
        public static final int   NET_PLAYAUDIO_TYPE_PHRASE = 1;        // 短语类型,不进行解析,依次读每个字,有该字的语音文件支持
        public static final int   NET_PLAYAUDIO_TYPE_FILE = 2;          // 播放指定路径的语音文件(设备端完整路径)
        public static final int   NET_PLAYAUDIO_TYPE_PLATERNUM = 3;     // 播报车牌号码,按车牌号码格式读出
        public static final int   NET_PLAYAUDIO_TYPE_MONEY = 4;         // 播报金额,按金额形式读出
        public static final int   NET_PLAYAUDIO_TYPE_DATE = 5;          // 播报日期,按日期形式读出
        public static final int   NET_PLAYAUDIO_TYPE_TIME = 6;          // 播报时间,按时间形式读出
        public static final int   NET_PLAYAUDIO_TYPE_EMPTY = 7;         // 空类型,停顿一个字符时间
    }

    // 语音播报内容
    public static class NET_CTRL_PLAYAUDIO_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              emPlayAudioType;                      // 播报单元类型,详见NET_PLAYAUDIO_TYPE，详见NET_PLAYAUDIO_TYPE
        public byte[]           szDetail = new byte[NET_COMMON_STRING_128]; // 详细内容各类型详细内容：
        // Phrase类型:"欢迎"
        // File类型: "/home/停车.pcm"
        // PlateNumbe类型: "浙A12345"
        // Money类型: "80.12元"
        // Date类型: "2014年4月10日"
        // Time类型: "1天10小时20分5秒
        public int              nRepeatTimes;                         // 播放重复次数
        public int              emPriority;                           // 播放优先级,对应枚举EM_PLAYAUDIO_PRIORITY
        public byte[]           szPlayID = new byte[16];              //本次投放唯一标识符
        public int              nDuration;                            //播放持续时间, 单位:秒

        public NET_CTRL_PLAYAUDIO_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // CLIENT_ControlDevice接口的 DH_CTRL_START_PLAYAUDIOEX 命令参数
    public static class NET_CTRL_START_PLAYAUDIOEX extends SdkStructure
    {
        public int              dwSize;
        public int              nAudioCount;                          // 播报内容数目
        public NET_CTRL_PLAYAUDIO_INFO[] stuAudioInfos = new NET_CTRL_PLAYAUDIO_INFO[NET_MAX_PLAYAUDIO_COUNT]; // 语音播报内容
        public int              nListRepeatTimes;                     // 语音播报内容重复次数, 描述所有播报单元

        public NET_CTRL_START_PLAYAUDIOEX()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuAudioInfos.length; ++i) {
                stuAudioInfos[i] = new NET_CTRL_PLAYAUDIO_INFO();
            }
        }
    }

    // 串口基本属性
    public static class CFG_COMM_PROP extends SdkStructure
    {
        public byte             byDataBit;                            // 数据位；0：5，1：6，2：7，3：8
        public byte             byStopBit;                            // 停止位；0：1位，1：1.5位，2：2位
        public byte             byParity;                             // 校验位；0：无校验，1：奇校验；2：偶校验
        public byte             byBaudRate;                           // 波特率；0：300，1：600，2：1200，3：2400，4：4800，
        // 5：9600，6：19200，7：38400，8：57600，9：115200
    }

    // 归位预置点配置
    public static class CFG_PRESET_HOMING extends SdkStructure
    {
        public int              nPtzPresetId;                         // 云台预置点编号	0~65535
        //-1表示无效
        public int              nFreeSec;                             // 空闲的时间，单位为秒
    }

    // 云台配置
    public static class CFG_PTZ_INFO extends SdkStructure
    {
        // 能力
        public byte             abMartixID;
        public byte             abCamID;
        public byte             abPTZType;
        // 信息
        public int              nChannelID;                           // 通道号(0开始)
        public int              bEnable;                              // 使能开关
        public byte[]           szProName = new byte[MAX_NAME_LEN];   // 协议名称
        public int              nDecoderAddress;                      // 解码器地址；0 - 255
        public CFG_COMM_PROP    struComm;
        public int              nMartixID;                            // 矩阵号
        public int              nPTZType;                             // 云台类型0-兼容，本地云台 1-远程网络云台
        public int              nCamID;                               // 摄像头ID
        public int              nPort;                                // 使用的串口端口号
        public CFG_PRESET_HOMING stuPresetHoming;                     // 一段时间不操作云台，自动归位到某个预置点
        public int              nControlMode;                         // 控制模式, 0:"RS485"串口控制(默认);1:"Coaxial" 同轴口控制
    }

    // 抓拍物体信息
    public static class NET_SNAP_OBJECT_INFO extends SdkStructure
    {
        public NET_RECT         stuBoundingBox;                       // 物体包围盒, 点坐标归一化到[0, 8192]坐标
        public byte[]           byReserved = new byte[512];           // 保留字节
    }

    // CLIENT_SnapPictureByAnalyseObject 接口输入参数
    public static class NET_IN_SNAP_BY_ANALYSE_OBJECT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小
        public int              nChannelID;                           // 通道号
        public byte[]           byReserved = new byte[4];             // 字节对齐
        public int              nSnapObjectNum;                       // 抓拍物体个数
        public NET_SNAP_OBJECT_INFO[] stuSnapObjects = new NET_SNAP_OBJECT_INFO[32]; // 抓拍物体信息

        public NET_IN_SNAP_BY_ANALYSE_OBJECT()
        {
            this.dwSize = this.size();

            for (int i = 0; i < stuSnapObjects.length; ++i) {
                stuSnapObjects[i] = new NET_SNAP_OBJECT_INFO();
            }
        }
    }

    // CLIENT_SnapPictureByAnalyseObject 接口输出参数
    public static class NET_OUT_SNAP_BY_ANALYSE_OBJECT extends SdkStructure
    {
        public int              dwSize;                               // 结构体大小

        public NET_OUT_SNAP_BY_ANALYSE_OBJECT()
        {
            this.dwSize = this.size();
        }
    }

    // 选中目标进行抓拍
    public boolean CLIENT_SnapPictureByAnalyseObject(LLong lLoginID,NET_IN_SNAP_BY_ANALYSE_OBJECT pInParam,NET_OUT_SNAP_BY_ANALYSE_OBJECT pOutParam,int nWaitTime);

    // 热成像着火点报警
    public static class ALARM_FIREWARNING_INFO extends SdkStructure
    {
        public int              nPresetId;                            // 该字段废弃，请由NET_ALARM_FIREWARNING_INFO事件获取此信息
        public int              nState;                               // 0 - 开始,1 - 结束,-1:无意义
        public DH_RECT          stBoundingBox;                        // 该字段废弃,请由NET_ALARM_FIREWARNING_INFO事件获取此信息
        public int              nTemperatureUnit;                     // 该字段废弃,请由NET_ALARM_FIREWARNING_INFO事件获取此信息
        public float            fTemperature;                         // 该字段废弃,请由NET_ALARM_FIREWARNING_INFO事件获取此信息
        public int              nDistance;                            // 该字段废弃,请由DH_ALARM_FIREWARNING_INFO事件获取此信息
        public GPS_POINT        stGpsPoint;                           // 该字段废弃,请由NET_ALARM_FIREWARNING_INFO事件获取此信息
        public int              nChannel;                             // 对应视频通道号
        public byte[]           reserved = new byte[252];
    }

    // 时间类型
    public static class EM_TIME_TYPE extends SdkStructure
    {
        public static final int   NET_TIME_TYPE_ABSLUTE = 0;            // 绝对时间
        public static final int   NET_TIME_TYPE_RELATIVE = 1;           // 相对时间,相对于视频文件头帧为时间基点,头帧对应于UTC(0000-00-00 00:00:00)
    }

    // 卡号省份
    public static class EM_CARD_PROVINCE extends SdkStructure
    {
        public static final int   EM_CARD_UNKNOWN = 10;                 // 解析出错，未知省份
        public static final int   EM_CARD_BEIJING = 11;                 // 北京
        public static final int   EM_CARD_TIANJIN = 12;                 // 天津
        public static final int   EM_CARD_HEBEI = 13;                   // 河北
        public static final int   EM_CARD_SHANXI_TAIYUAN = 14;          // 山西
        public static final int   EM_CARD_NEIMENGGU = 15;               // 内蒙古
        public static final int   EM_CARD_LIAONING = 21;                // 辽宁
        public static final int   EM_CARD_JILIN = 22;                   // 吉林
        public static final int   EM_CARD_HEILONGJIANG = 23;            // 黑龙江
        public static final int   EM_CARD_SHANGHAI = 31;                // 上海
        public static final int   EM_CARD_JIANGSU = 32;                 // 江苏
        public static final int   EM_CARD_ZHEJIANG = 33;                // 浙江
        public static final int   EM_CARD_ANHUI = 34;                   // 安徽
        public static final int   EM_CARD_FUJIAN = 35;                  // 福建
        public static final int   EM_CARD_JIANGXI = 36;                 // 江西
        public static final int   EM_CARD_SHANDONG = 37;                // 山东
        public static final int   EM_CARD_HENAN = 41;                   // 河南
        public static final int   EM_CARD_HUBEI = 42;                   // 湖北
        public static final int   EM_CARD_HUNAN = 43;                   // 湖南
        public static final int   EM_CARD_GUANGDONG = 44;               // 广东
        public static final int   EM_CARD_GUANGXI = 45;                 // 广西
        public static final int   EM_CARD_HAINAN = 46;                  // 海南
        public static final int   EM_CARD_CHONGQING = 50;               // 重庆
        public static final int   EM_CARD_SICHUAN = 51;                 // 四川
        public static final int   EM_CARD_GUIZHOU = 52;                 // 贵州
        public static final int   EM_CARD_YUNNAN = 53;                  // 云南
        public static final int   EM_CARD_XIZANG = 54;                  // 西藏
        public static final int   EM_CARD_SHANXI_XIAN = 61;             // 陕西
        public static final int   EM_CARD_GANSU = 62;                   // 甘肃
        public static final int   EM_CARD_QINGHAI = 63;                 // 青海
        public static final int   EM_CARD_NINGXIA = 64;                 // 宁夏
        public static final int   EM_CARD_XINJIANG = 65;                // 新疆
        public static final int   EM_CARD_XIANGGANG = 71;               // 香港
        public static final int   EM_CARD_AOMEN = 82;                   // 澳门
        public static final int   EM_CARD_TAIWAN = 83;                  // 台湾
    }

    // 车辆类型
    public static class EM_CAR_TYPE extends SdkStructure
    {
        public static final int   EM_CAR_0 = 0;                         // 其他车辆
        public static final int   EM_CAR_1 = 1;                         // 大型普通客车
        public static final int   EM_CAR_2 = 2;                         // 大型双层客车
        public static final int   EM_CAR_3 = 3;                         // 大型卧铺客车
        public static final int   EM_CAR_4 = 4;                         // 大型铰接客车
        public static final int   EM_CAR_5 = 5;                         // 大型越野客车
        public static final int   EM_CAR_6 = 6;                         // 大型轿车
        public static final int   EM_CAR_7 = 7;                         // 大型专用客车
        public static final int   EM_CAR_8 = 8;                         // 大型专用校车
        public static final int   EM_CAR_9 = 9;                         // 中型普通客车
        public static final int   EM_CAR_10 = 10;                       // 中型双层客车
        public static final int   EM_CAR_11 = 11;                       // 中型卧铺客车
        public static final int   EM_CAR_12 = 12;                       // 中型铰接客车
        public static final int   EM_CAR_13 = 13;                       // 中型越野客车
        public static final int   EM_CAR_14 = 14;                       // 中型轿车
        public static final int   EM_CAR_15 = 15;                       // 中型专用客车
        public static final int   EM_CAR_16 = 16;                       // 中型专用校车
        public static final int   EM_CAR_17 = 17;                       // 小型普通客车
        public static final int   EM_CAR_18 = 18;                       // 小型越野客车
        public static final int   EM_CAR_19 = 19;                       // 小型轿车
        public static final int   EM_CAR_20 = 20;                       // 小型专用客车
        public static final int   EM_CAR_21 = 21;                       // 小型专用校车
        public static final int   EM_CAR_22 = 22;                       // 小型面包车
        public static final int   EM_CAR_23 = 23;                       // 微型普通客车
        public static final int   EM_CAR_24 = 24;                       // 微型越野客车
        public static final int   EM_CAR_25 = 25;                       // 微型轿车
        public static final int   EM_CAR_26 = 26;                       // 微型面包车
        public static final int   EM_CAR_27 = 27;                       // 重型半挂牵引车
        public static final int   EM_CAR_28 = 28;                       // 重型全挂牵引车
        public static final int   EM_CAR_29 = 29;                       // 中型半挂牵引车
        public static final int   EM_CAR_30 = 30;                       // 中型全挂牵引车
        public static final int   EM_CAR_31 = 31;                       // 轻型半挂牵引车
        public static final int   EM_CAR_32 = 32;                       // 轻型全挂牵引车
        public static final int   EM_CAR_33 = 33;                       // 大型非载货专项作业车
        public static final int   EM_CAR_34 = 34;                       // 大型载货专项作业车
        public static final int   EM_CAR_35 = 35;                       // 中型非载货专项作业车
        public static final int   EM_CAR_36 = 36;                       // 中型载货专项作业车
        public static final int   EM_CAR_37 = 37;                       // 小型非载货专项作业车
        public static final int   EM_CAR_38 = 38;                       // 小型载货专项作业车
        public static final int   EM_CAR_39 = 39;                       // 微型非载货专项作业车
        public static final int   EM_CAR_40 = 40;                       // 微型载货专项作业车
        public static final int   EM_CAR_41 = 41;                       // 重型非载货专项作业车
        public static final int   EM_CAR_42 = 42;                       // 重型载货专项作业车
        public static final int   EM_CAR_43 = 43;                       // 轻型非载货专项作业车
        public static final int   EM_CAR_44 = 44;                       // 轻型载货专项作业车
        public static final int   EM_CAR_45 = 45;                       // 普通正三轮摩托车
        public static final int   EM_CAR_46 = 46;                       // 轻便正三轮摩托车
        public static final int   EM_CAR_47 = 47;                       // 正三轮载客摩托车
        public static final int   EM_CAR_48 = 48;                       // 正三轮载货摩托车
        public static final int   EM_CAR_49 = 49;                       // 侧三轮摩托车
        public static final int   EM_CAR_50 = 50;                       // 普通二轮摩托车
        public static final int   EM_CAR_51 = 51;                       // 轻便二轮摩托车
        public static final int   EM_CAR_52 = 52;                       // 无轨电车
        public static final int   EM_CAR_53 = 53;                       // 有轨电车
        public static final int   EM_CAR_54 = 54;                       // 三轮汽车
        public static final int   EM_CAR_55 = 55;                       // 轮式装载机械
        public static final int   EM_CAR_56 = 56;                       // 轮式挖掘机械
        public static final int   EM_CAR_57 = 57;                       // 轮式平地机械
        public static final int   EM_CAR_58 = 58;                       // 重型普通货车
        public static final int   EM_CAR_59 = 59;                       // 重型厢式货车
        public static final int   EM_CAR_60 = 60;                       // 重型封闭货车
        public static final int   EM_CAR_61 = 61;                       // 重型罐式货车
        public static final int   EM_CAR_62 = 62;                       // 重型平板货车
        public static final int   EM_CAR_63 = 63;                       // 重型集装箱车
        public static final int   EM_CAR_64 = 64;                       // 重型自卸货车
        public static final int   EM_CAR_65 = 65;                       // 重型特殊结构货车
        public static final int   EM_CAR_66 = 66;                       // 重型仓栅式货车
        public static final int   EM_CAR_67 = 67;                       // 重型车辆运输车
        public static final int   EM_CAR_68 = 68;                       // 重型厢式自卸货车
        public static final int   EM_CAR_69 = 69;                       // 重型罐式自卸货车
        public static final int   EM_CAR_70 = 70;                       // 重型平板自卸货车
        public static final int   EM_CAR_71 = 71;                       // 重型集装箱自卸货车
        public static final int   EM_CAR_72 = 72;                       // 重型特殊结构自卸货车
        public static final int   EM_CAR_73 = 73;                       // 重型仓栅式自卸货车
        public static final int   EM_CAR_74 = 74;                       // 中型普通货车
        public static final int   EM_CAR_75 = 75;                       // 中型厢式货车
        public static final int   EM_CAR_76 = 76;                       // 中型封闭货车
        public static final int   EM_CAR_77 = 77;                       // 中型罐式货车
        public static final int   EM_CAR_78 = 78;                       // 中型平板货车
        public static final int   EM_CAR_79 = 79;                       // 中型集装箱车
        public static final int   EM_CAR_80 = 80;                       // 中型自卸货车
        public static final int   EM_CAR_81 = 81;                       // 中型特殊结构货车
        public static final int   EM_CAR_82 = 82;                       // 中型仓栅式货车
        public static final int   EM_CAR_83 = 83;                       // 中型车辆运输车
        public static final int   EM_CAR_84 = 84;                       // 中型厢式自卸货车
        public static final int   EM_CAR_85 = 85;                       // 中型罐式自卸货车
        public static final int   EM_CAR_86 = 86;                       // 中型平板自卸货车
        public static final int   EM_CAR_87 = 87;                       // 中型集装箱自卸货车
        public static final int   EM_CAR_88 = 88;                       // 中型特殊结构自卸货车
        public static final int   EM_CAR_89 = 89;                       // 中型仓栅式自卸货车
        public static final int   EM_CAR_90 = 90;                       // 轻型普通货车
        public static final int   EM_CAR_91 = 91;                       // 轻型厢式货车
        public static final int   EM_CAR_92 = 92;                       // 轻型封闭货车
        public static final int   EM_CAR_93 = 93;                       // 轻型罐式货车
        public static final int   EM_CAR_94 = 94;                       // 轻型平板货车
        public static final int   EM_CAR_95 = 95;                       // 轻型自卸货车
        public static final int   EM_CAR_96 = 96;                       // 轻型特殊结构货车
        public static final int   EM_CAR_97 = 97;                       // 轻型仓栅式货车
        public static final int   EM_CAR_98 = 98;                       // 轻型车辆运输车
        public static final int   EM_CAR_99 = 99;                       // 轻型厢式自卸货车
        public static final int   EM_CAR_100 = 100;                     // 轻型罐式自卸货车
        public static final int   EM_CAR_101 = 101;                     // 轻型平板自卸货车
        public static final int   EM_CAR_102 = 102;                     // 轻型特殊结构自卸货车
        public static final int   EM_CAR_103 = 103;                     // 轻型仓栅式自卸货车
        public static final int   EM_CAR_104 = 104;                     // 微型普通货车
        public static final int   EM_CAR_105 = 105;                     // 微型厢式货车
        public static final int   EM_CAR_106 = 106;                     // 微型封闭货车
        public static final int   EM_CAR_107 = 107;                     // 微型罐式货车
        public static final int   EM_CAR_108 = 108;                     // 微型自卸货车
        public static final int   EM_CAR_109 = 109;                     // 微型特殊结构货车
        public static final int   EM_CAR_110 = 110;                     // 微型仓栅式货车
        public static final int   EM_CAR_111 = 111;                     // 微型车辆运输车
        public static final int   EM_CAR_112 = 112;                     // 微型厢式自卸货车
        public static final int   EM_CAR_113 = 113;                     // 微型罐式自卸货车
        public static final int   EM_CAR_114 = 114;                     // 微型特殊结构自卸货车
        public static final int   EM_CAR_115 = 115;                     // 微型仓栅式自卸货车
        public static final int   EM_CAR_116 = 116;                     // 普通低速货车
        public static final int   EM_CAR_117 = 117;                     // 厢式低速货车
        public static final int   EM_CAR_118 = 118;                     // 罐式低速货车
        public static final int   EM_CAR_119 = 119;                     // 自卸低速货车
        public static final int   EM_CAR_120 = 120;                     // 仓栅式低速货车
        public static final int   EM_CAR_121 = 121;                     // 厢式自卸低速货车
        public static final int   EM_CAR_122 = 122;                     // 罐式自卸低速货车
        public static final int   EM_CAR_123 = 123;                     // 重型普通全挂车
        public static final int   EM_CAR_124 = 124;                     // 重型厢式全挂车
        public static final int   EM_CAR_125 = 125;                     // 重型罐式全挂车
        public static final int   EM_CAR_126 = 126;                     // 重型平板全挂车
        public static final int   EM_CAR_127 = 127;                     // 重型集装箱全挂车
        public static final int   EM_CAR_128 = 128;                     // 重型自卸全挂车
        public static final int   EM_CAR_129 = 129;                     // 重型仓栅式全挂车
        public static final int   EM_CAR_130 = 130;                     // 重型旅居全挂车
        public static final int   EM_CAR_131 = 131;                     // 重型专项作业全挂车
        public static final int   EM_CAR_132 = 132;                     // 重型厢式自卸全挂车
        public static final int   EM_CAR_133 = 133;                     // 重型罐式自卸全挂车
        public static final int   EM_CAR_134 = 134;                     // 重型平板自卸全挂车
        public static final int   EM_CAR_135 = 135;                     // 重型集装箱自卸全挂车
        public static final int   EM_CAR_136 = 136;                     // 重型仓栅式自卸全挂车
        public static final int   EM_CAR_137 = 137;                     // 重型专项作业自卸全挂车
        public static final int   EM_CAR_138 = 138;                     // 中型普通全挂车
        public static final int   EM_CAR_139 = 139;                     // 中型厢式全挂车
        public static final int   EM_CAR_140 = 140;                     // 中型罐式全挂车
        public static final int   EM_CAR_141 = 141;                     // 中型平板全挂车
        public static final int   EM_CAR_142 = 142;                     // 中型集装箱全挂车
        public static final int   EM_CAR_143 = 143;                     // 中型自卸全挂车
        public static final int   EM_CAR_144 = 144;                     // 中型仓栅式全挂车
        public static final int   EM_CAR_145 = 145;                     // 中型旅居全挂车
        public static final int   EM_CAR_146 = 146;                     // 中型专项作业全挂车
        public static final int   EM_CAR_147 = 147;                     // 中型厢式自卸全挂车
        public static final int   EM_CAR_148 = 148;                     // 中型罐式自卸全挂车
        public static final int   EM_CAR_149 = 149;                     // 中型平板自卸全挂车
        public static final int   EM_CAR_150 = 150;                     // 中型集装箱自卸全挂车
        public static final int   EM_CAR_151 = 151;                     // 中型仓栅式自卸全挂车
        public static final int   EM_CAR_152 = 152;                     // 中型专项作业自卸全挂车
        public static final int   EM_CAR_153 = 153;                     // 轻型普通全挂车
        public static final int   EM_CAR_154 = 154;                     // 轻型厢式全挂车
        public static final int   EM_CAR_155 = 155;                     // 轻型罐式全挂车
        public static final int   EM_CAR_156 = 156;                     // 轻型平板全挂车
        public static final int   EM_CAR_157 = 157;                     // 轻型自卸全挂车
        public static final int   EM_CAR_158 = 158;                     // 轻型仓栅式全挂车
        public static final int   EM_CAR_159 = 159;                     // 轻型旅居全挂车
        public static final int   EM_CAR_160 = 160;                     // 轻型专项作业全挂车
        public static final int   EM_CAR_161 = 161;                     // 轻型厢式自卸全挂车
        public static final int   EM_CAR_162 = 162;                     // 轻型罐式自卸全挂车
        public static final int   EM_CAR_163 = 163;                     // 轻型平板自卸全挂车
        public static final int   EM_CAR_164 = 164;                     // 轻型集装箱自卸全挂车
        public static final int   EM_CAR_165 = 165;                     // 轻型仓栅式自卸全挂车
        public static final int   EM_CAR_166 = 166;                     // 轻型专项作业自卸全挂车
        public static final int   EM_CAR_167 = 167;                     // 重型普通半挂车
        public static final int   EM_CAR_168 = 168;                     // 重型厢式半挂车
        public static final int   EM_CAR_169 = 169;                     // 重型罐式半挂车
        public static final int   EM_CAR_170 = 170;                     // 重型平板半挂车
        public static final int   EM_CAR_171 = 171;                     // 重型集装箱半挂车
        public static final int   EM_CAR_172 = 172;                     // 重型自卸半挂车
        public static final int   EM_CAR_173 = 173;                     // 重型特殊结构半挂车
        public static final int   EM_CAR_174 = 174;                     // 重型仓栅式半挂车
        public static final int   EM_CAR_175 = 175;                     // 重型旅居半挂车
        public static final int   EM_CAR_176 = 176;                     // 重型专项作业半挂车
        public static final int   EM_CAR_177 = 177;                     // 重型低平板半挂车
        public static final int   EM_CAR_178 = 178;                     // 重型车辆运输半挂车
        public static final int   EM_CAR_179 = 179;                     // 重型罐式自卸半挂车
        public static final int   EM_CAR_180 = 180;                     // 重型平板自卸半挂车
        public static final int   EM_CAR_181 = 181;                     // 重型集装箱自卸半挂车
        public static final int   EM_CAR_182 = 182;                     // 重型特殊结构自卸半挂车
        public static final int   EM_CAR_183 = 183;                     // 重型仓栅式自卸半挂车
        public static final int   EM_CAR_184 = 184;                     // 重型专项作业自卸半挂车
        public static final int   EM_CAR_185 = 185;                     // 重型低平板自卸半挂车
        public static final int   EM_CAR_186 = 186;                     // 重型中置轴旅居挂车
        public static final int   EM_CAR_187 = 187;                     // 重型中置轴车辆运输车
        public static final int   EM_CAR_188 = 188;                     // 重型中置轴普通挂车
        public static final int   EM_CAR_189 = 189;                     // 中型普通半挂车
        public static final int   EM_CAR_190 = 190;                     // 中型厢式半挂车
        public static final int   EM_CAR_191 = 191;                     // 中型罐式半挂车
        public static final int   EM_CAR_192 = 192;                     // 中型平板半挂车
        public static final int   EM_CAR_193 = 193;                     // 中型集装箱半挂车
        public static final int   EM_CAR_194 = 194;                     // 中型自卸半挂车
        public static final int   EM_CAR_195 = 195;                     // 中型特殊结构半挂车
        public static final int   EM_CAR_196 = 196;                     // 中型仓栅式半挂车
        public static final int   EM_CAR_197 = 197;                     // 中型旅居半挂车
        public static final int   EM_CAR_198 = 198;                     // 中型专项作业半挂车
        public static final int   EM_CAR_199 = 199;                     // 中型低平板半挂车
        public static final int   EM_CAR_200 = 200;                     // 中型车辆运输半挂车
        public static final int   EM_CAR_201 = 201;                     // 中型罐式自卸半挂车
        public static final int   EM_CAR_202 = 202;                     // 中型平板自卸半挂车
        public static final int   EM_CAR_203 = 203;                     // 中型集装箱自卸半挂车
        public static final int   EM_CAR_204 = 204;                     // 中型特殊结构自卸挂车
        public static final int   EM_CAR_205 = 205;                     // 中型仓栅式自卸半挂车
        public static final int   EM_CAR_206 = 206;                     // 中型专项作业自卸半挂车
        public static final int   EM_CAR_207 = 207;                     // 中型低平板自卸半挂车
        public static final int   EM_CAR_208 = 208;                     // 中型中置轴旅居挂车
        public static final int   EM_CAR_209 = 209;                     // 中型中置轴车辆运输车
        public static final int   EM_CAR_210 = 210;                     // 中型中置轴普通挂车
        public static final int   EM_CAR_211 = 211;                     // 轻型普通半挂车
        public static final int   EM_CAR_212 = 212;                     // 轻型厢式半挂车
        public static final int   EM_CAR_213 = 213;                     // 轻型罐式半挂车
        public static final int   EM_CAR_214 = 214;                     // 轻型平板半挂车
        public static final int   EM_CAR_215 = 215;                     // 轻型自卸半挂车
        public static final int   EM_CAR_216 = 216;                     // 轻型仓栅式半挂车
        public static final int   EM_CAR_217 = 217;                     // 轻型旅居半挂车
        public static final int   EM_CAR_218 = 218;                     // 轻型专项作业半挂车
        public static final int   EM_CAR_219 = 219;                     // 轻型低平板半挂车
        public static final int   EM_CAR_220 = 220;                     // 轻型车辆运输半挂车
        public static final int   EM_CAR_221 = 221;                     // 轻型罐式自卸半挂车
        public static final int   EM_CAR_222 = 222;                     // 轻型平板自卸半挂车
        public static final int   EM_CAR_223 = 223;                     // 轻型集装箱自卸半挂车
        public static final int   EM_CAR_224 = 224;                     // 轻型特殊结构自卸挂车
        public static final int   EM_CAR_225 = 225;                     // 轻型仓栅式自卸半挂车
        public static final int   EM_CAR_226 = 226;                     // 轻型专项作业自卸半挂车
        public static final int   EM_CAR_227 = 227;                     // 轻型低平板自卸半挂车
        public static final int   EM_CAR_228 = 228;                     // 轻型中置轴旅居挂车
        public static final int   EM_CAR_229 = 229;                     // 轻型中置轴车辆运输车
        public static final int   EM_CAR_230 = 230;                     // 轻型中置轴普通挂车
    }

    // 号牌类型
    public static class EM_PLATE_TYPE extends SdkStructure
    {
        public static final int   EM_PLATE_OTHER = 0;                   // 其他车
        public static final int   EM_PLATE_BIG_CAR = 1;                 // 大型汽车
        public static final int   EM_PLATE_SMALL_CAR = 2;               // 小型汽车
        public static final int   EM_PLATE_EMBASSY_CAR = 3;             // 使馆汽车
        public static final int   EM_PLATE_CONSULATE_CAR = 4;           // 领馆汽车
        public static final int   EM_PLATE_ABROAD_CAR = 5;              // 境外汽车
        public static final int   EM_PLATE_FOREIGN_CAR = 6;             // 外籍汽车
        public static final int   EM_PLATE_LOW_SPEED_CAR = 7;           // 低速车
        public static final int   EM_PLATE_COACH_CAR = 8;               // 教练车
        public static final int   EM_PLATE_MOTORCYCLE = 9;              // 摩托车
        public static final int   EM_PLATE_NEW_POWER_CAR = 10;          // 新能源车
        public static final int   EM_PLATE_POLICE_CAR = 11;             // 警用车
        public static final int   EM_PLATE_HONGKONG_MACAO_CAR = 12;     // 港澳两地车
        public static final int   EM_PLATE_WJPOLICE_CAR = 13;           //
        public static final int   EM_PLATE_OUTERGUARD_CAR = 14;         //
    }

    // 车身颜色
    public static class EM_CAR_COLOR_TYPE extends SdkStructure
    {
        public static final int   EM_CAR_COLOR_WHITE = 0;               // 白色
        public static final int   EM_CAR_COLOR_BLACK = 1;               // 黑色
        public static final int   EM_CAR_COLOR_RED = 2;                 // 红色
        public static final int   EM_CAR_COLOR_YELLOW = 3;              // 黄色
        public static final int   EM_CAR_COLOR_GRAY = 4;                // 灰色
        public static final int   EM_CAR_COLOR_BLUE = 5;                // 蓝色
        public static final int   EM_CAR_COLOR_GREEN = 6;               // 绿色
        public static final int   EM_CAR_COLOR_PINK = 7;                // 粉色
        public static final int   EM_CAR_COLOR_PURPLE = 8;              // 紫色
        public static final int   EM_CAR_COLOR_DARK_PURPLE = 9;         // 暗紫色
        public static final int   EM_CAR_COLOR_BROWN = 10;              // 棕色
        public static final int   EM_CAR_COLOR_MAROON = 11;             // 粟色
        public static final int   EM_CAR_COLOR_SILVER_GRAY = 12;        // 银灰色
        public static final int   EM_CAR_COLOR_DARK_GRAY = 13;          // 暗灰色
        public static final int   EM_CAR_COLOR_WHITE_SMOKE = 14;        // 白烟色
        public static final int   EM_CAR_COLOR_DEEP_ORANGE = 15;        // 深橙色
        public static final int   EM_CAR_COLOR_LIGHT_ROSE = 16;         // 浅玫瑰色
        public static final int   EM_CAR_COLOR_TOMATO_RED = 17;         // 番茄红色
        public static final int   EM_CAR_COLOR_OLIVE = 18;              // 橄榄色
        public static final int   EM_CAR_COLOR_GOLDEN = 19;             // 金色
        public static final int   EM_CAR_COLOR_DARK_OLIVE = 20;         // 暗橄榄色
        public static final int   EM_CAR_COLOR_YELLOW_GREEN = 21;       // 黄绿色
        public static final int   EM_CAR_COLOR_GREEN_YELLOW = 22;       // 绿黄色
        public static final int   EM_CAR_COLOR_FOREST_GREEN = 23;       // 森林绿
        public static final int   EM_CAR_COLOR_OCEAN_BLUE = 24;         // 海洋绿
        public static final int   EM_CAR_COLOR_DEEP_SKYBLUE = 25;       // 深天蓝
        public static final int   EM_CAR_COLOR_CYAN = 26;               // 青色
        public static final int   EM_CAR_COLOR_DEEP_BLUE = 27;          // 深蓝色
        public static final int   EM_CAR_COLOR_DEEP_RED = 28;           // 深红色
        public static final int   EM_CAR_COLOR_DEEP_GREEN = 29;         // 深绿色
        public static final int   EM_CAR_COLOR_DEEP_YELLOW = 30;        // 深黄色
        public static final int   EM_CAR_COLOR_DEEP_PINK = 31;          // 深粉色
        public static final int   EM_CAR_COLOR_DEEP_PURPLE = 32;        // 深紫色
        public static final int   EM_CAR_COLOR_DEEP_BROWN = 33;         // 深棕色
        public static final int   EM_CAR_COLOR_DEEP_CYAN = 34;          // 深青色
        public static final int   EM_CAR_COLOR_ORANGE = 35;             // 橙色
        public static final int   EM_CAR_COLOR_DEEP_GOLDEN = 36;        // 深金色
        public static final int   EM_CAR_COLOR_OTHER = 255;             // 未识别、其他
    }

    // 使用性质
    public static class EM_USE_PROPERTY_TYPE extends SdkStructure
    {
        public static final int   EM_USE_PROPERTY_NONOPERATING = 0;     // 非营运
        public static final int   EM_USE_PROPERTY_HIGWAY = 1;           // 公路客运,旅游客运
        public static final int   EM_USE_PROPERTY_BUS = 2;              // 公交客运
        public static final int   EM_USE_PROPERTY_TAXI = 3;             // 出租客运
        public static final int   EM_USE_PROPERTY_FREIGHT = 4;          // 货运
        public static final int   EM_USE_PROPERTY_LEASE = 5;            // 租赁
        public static final int   EM_USE_PROPERTY_SECURITY = 6;         // 警用,消防,救护,工程救险
        public static final int   EM_USE_PROPERTY_COACH = 7;            // 教练
        public static final int   EM_USE_PROPERTY_SCHOOLBUS = 8;        // 幼儿校车,小学生校车,其他校车
        public static final int   EM_USE_PROPERTY_FOR_DANGE_VEHICLE = 9; // 危化品运输
        public static final int   EM_USE_PROPERTY_OTHER = 10;           // 其他
        public static final int   EM_USE_PROPERTY_ONLINE_CAR_HAILING = 11; // 网约车
    }

    // 大类业务方案
    public static class EM_CLASS_TYPE extends SdkStructure
    {
        public static final int   EM_CLASS_UNKNOWN = 0;                 // 未知业务
        public static final int   EM_CLASS_VIDEO_SYNOPSIS = 1;          // 视频浓缩
        public static final int   EM_CLASS_TRAFFIV_GATE = 2;            // 卡口
        public static final int   EM_CLASS_ELECTRONIC_POLICE = 3;       // 电警
        public static final int   EM_CLASS_SINGLE_PTZ_PARKING = 4;      // 单球违停
        public static final int   EM_CLASS_PTZ_PARKINBG = 5;            // 主从违停
        public static final int   EM_CLASS_TRAFFIC = 6;                 // 交通事件"Traffic"
        public static final int   EM_CLASS_NORMAL = 7;                  // 通用行为分析"Normal"
        public static final int   EM_CLASS_PS = 8;
        public static final int   EM_CLASS_ATM = 9;                     // 金融行为分析"ATM"
        public static final int   EM_CLASS_METRO = 10;                  // 地铁行为分析
        public static final int   EM_CLASS_FACE_DETECTION = 11;         // 目标检测"FaceDetection"
        public static final int   EM_CLASS_FACE_RECOGNITION = 12;       // 目标识别"FaceRecognition"
        public static final int   EM_CLASS_NUMBER_STAT = 13;            // 人数统计"NumberStat"
        public static final int   EM_CLASS_HEAT_MAP = 14;               // 热度图"HeatMap"
        public static final int   EM_CLASS_VIDEO_DIAGNOSIS = 15;        // 视频诊断"VideoDiagnosis"
        public static final int   EM_CLASS_VIDEO_ENHANCE = 16;          // 视频增强
        public static final int   EM_CLASS_SMOKEFIRE_DETECT = 17;       // 烟火检测
        public static final int   EM_CLASS_VEHICLE_ANALYSE = 18;        // 车辆特征识别"VehicleAnalyse"
        public static final int   EM_CLASS_PERSON_FEATURE = 19;         // 人员特征识别
        public static final int   EM_CLASS_SDFACEDETECTION = 20;        // 多预置点目标检测"SDFaceDetect"
        //配置一条规则但可以在不同预置点下生效
        public static final int   EM_CLASS_HEAT_MAP_PLAN = 21;          // 球机热度图计划"HeatMapPlan"
        public static final int   EM_CLASS_NUMBERSTAT_PLAN = 22;        // 球机客流量统计计划 "NumberStatPlan"
        public static final int   EM_CLASS_ATMFD = 23;                  // 金融目标检测，包括正常人脸、异常人脸、相邻人脸、头盔人脸等针对ATM场景特殊优化
        public static final int   EM_CLASS_HIGHWAY = 24;                // 高速交通事件检测"Highway"
        public static final int   EM_CLASS_CITY = 25;                   // 城市交通事件检测 "City"
        public static final int   EM_CLASS_LETRACK = 26;                // 民用简易跟踪"LeTrack"
        public static final int   EM_CLASS_SCR = 27;                    // 打靶相机"SCR"
        public static final int   EM_CLASS_STEREO_VISION = 28;          // 立体视觉(双目)"StereoVision"
        public static final int   EM_CLASS_HUMANDETECT = 29;            // 人体检测"HumanDetect"
        public static final int   EM_CLASS_FACE_ANALYSIS = 30;          // 人脸分析 "FaceAnalysis"
        public static final int   EM_CALSS_XRAY_DETECTION = 31;         // X光检测 "XRayDetection"
        public static final int   EM_CLASS_STEREO_NUMBER = 32;          // 双目相机客流量统计 "StereoNumber"
        public static final int   EM_CLASS_CROWDDISTRIMAP = 33;         // 人群分布图
        public static final int   EM_CLASS_OBJECTDETECT = 34;           // 目标检测
        public static final int   EM_CLASS_FACEATTRIBUTE = 35;          // IVSS目标检测 "FaceAttribute"
        public static final int   EM_CLASS_FACECOMPARE = 36;            // IVSS目标识别 "FaceCompare"
        public static final int   EM_CALSS_STEREO_BEHAVIOR = 37;        // 立体行为分析 "StereoBehavior"
        public static final int   EM_CALSS_INTELLICITYMANAGER = 38;     // 智慧城管 "IntelliCityMgr"
        public static final int   EM_CALSS_PROTECTIVECABIN = 39;        // 防护舱（ATM舱内）"ProtectiveCabin"
        public static final int   EM_CALSS_AIRPLANEDETECT = 40;         // 飞机行为检测 "AirplaneDetect"
        public static final int   EM_CALSS_CROWDPOSTURE = 41;           // 人群态势（人群分布图服务）"CrowdPosture"
        public static final int   EM_CLASS_PHONECALLDETECT = 42;        // 打电话检测 "PhoneCallDetect"
        public static final int   EM_CLASS_SMOKEDETECTION = 43;         // 烟雾检测 "SmokeDetection"
        public static final int   EM_CLASS_BOATDETECTION = 44;          // 船只检测 "BoatDetection"
        public static final int   EM_CLASS_SMOKINGDETECT = 45;          // 吸烟检测 "SmokingDetect"
        public static final int   EM_CLASS_WATERMONITOR = 46;           // 水利监测 "WaterMonitor"
        public static final int   EM_CLASS_GENERATEGRAPHDETECTION = 47; // 生成图规则 "GenerateGraphDetection"
        public static final int   EM_CLASS_TRAFFIC_PARK = 48;           // 交通停车 "TrafficPark"
        public static final int   EM_CLASS_OPERATEMONITOR = 49;         // 作业检测 "OperateMonitor"
        public static final int   EM_CLASS_INTELLI_RETAIL = 50;         // 智慧零售大类 "IntelliRetail"
        public static final int   EM_CLASS_CLASSROOM_ANALYSE = 51;      // 教育智慧课堂"ClassroomAnalyse"
        public static final int   EM_CLASS_FEATURE_ABSTRACT = 52;       // 特征向量提取大类 "FeatureAbstract"
        public static final int   EM_CLASS_CROWD_ABNORMAL = 62;         // 人群异常检测 "CrowdAbnormal"
        public static final int   EM_CLASS_ANATOMY_TEMP_DETECT = 63;    // 人体温智能检测 "AnatomyTempDetect"
        public static final int   EM_CLASS_WEATHER_MONITOR = 64;        // 天气监控 "WeatherMonitor"
        public static final int   EM_CLASS_ELEVATOR_ACCESS_CONTROL = 65; // 电梯门禁 "ElevatorAccessControl"
        public static final int   EM_CLASS_BREAK_RULE_BUILDING = 66;    // 违章建筑	"BreakRuleBuilding"
        public static final int   EM_CLASS_FOREIGN_DETECT = 67;         // 异物检测 "ForeignDetection"
        public static final int   EM_CLASS_PANORAMA_TRAFFIC = 68;       // 全景交通 "PanoramaTraffic"
        public static final int   EM_CLASS_CONVEY_OR_BLOCK = 69;        // 传送带阻塞 "ConveyorBlock"
        public static final int   EM_CLASS_KITCHEN_ANIMAL = 70;         // 厨房有害动物检测 "KitchenAnimal"
        public static final int   EM_CLASS_ALLSEEINGEYE = 71;           // 万物检测 "AllSeeingEye"
        public static final int   EM_CLASS_INTELLI_FIRE_CONTROL = 72;   // 智慧消防 "IntelliFireControl"
        public static final int   EM_CLASS_CONVERYER_BELT = 73;         // 传送带检测 "ConveyerBelt"
        public static final int   EM_CLASS_INTELLI_LOGISTICS = 74;      // 智慧物流 "IntelliLogistics"
        public static final int   EM_CLASS_SMOKE_FIRE = 75;             // 烟火检测"SmokeFire"
        public static final int   EM_CLASS_OBJECT_MONITOR = 76;         // 物品监控"ObjectMonitor"
        public static final int   EM_CLASS_INTELLI_PARKING = 77;        // 智能停车"IntelliParking"
    }

    // 交通车辆行驶方向类型
    public static class EM_TRAFFICCAR_MOVE_DIRECTION extends SdkStructure
    {
        public static final int   EM_TRAFFICCAR_MOVE_DIRECTION_UNKNOWN = 0; // 未知的
        public static final int   EM_TRAFFICCAR_MOVE_DIRECTION_STRAIGHT = 1; // 直行
        public static final int   EM_TRAFFICCAR_MOVE_DIRECTION_TURN_LEFT = 2; // 左转
        public static final int   EM_TRAFFICCAR_MOVE_DIRECTION_TURN_RIGHT = 3; // 右转
        public static final int   EM_TRAFFICCAR_MOVE_DIRECTION_TURN_AROUND = 4; // 掉头
    }

    // 货物通道信息（物流）
    public static class NET_CUSTOM_INFO extends SdkStructure
    {
        public int              nCargoChannelNum;                     // 货物通道个数
        public float[]          fCoverageRate = new float[MAX_CARGO_CHANNEL_NUM]; // 货物覆盖率
        public byte[]           byReserved = new byte[40];            // 保留字节
    }

    // 车辆物件类型
    public static class EM_COMM_ATTACHMENT_TYPE extends SdkStructure
    {
        public static final int   COMM_ATTACHMENT_TYPE_UNKNOWN = 0;     // 未知类型
        public static final int   COMM_ATTACHMENT_TYPE_FURNITURE = 1;   // 摆件
        public static final int   COMM_ATTACHMENT_TYPE_PENDANT = 2;     // 挂件
        public static final int   COMM_ATTACHMENT_TYPE_TISSUEBOX = 3;   // 纸巾盒
        public static final int   COMM_ATTACHMENT_TYPE_DANGER = 4;      // 危险品
        public static final int   COMM_ATTACHMENT_TYPE_PERFUMEBOX = 5;  // 香水
    }

    // 按功能划分的车辆类型
    public static class EM_VEHICLE_TYPE_BY_FUNC extends SdkStructure
    {
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_UNKNOWN = 0;  // 未知
        /*以下为特种车辆类型*/
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_TANK_CAR = 1; // 危化品车辆
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_SLOT_TANK_CAR = 2; // 槽罐车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_DREGS_CAR = 3; // 渣土车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_CONCRETE_MIXER_TRUCK = 4; // 混凝土搅拌车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_TAXI = 5;     // 出租车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_POLICE = 6;   // 警车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_AMBULANCE = 7; // 救护车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_GENERAL = 8;  // 普通车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_WATERING_CAR = 9; // 洒水车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_FIRE_ENGINE = 10; // 消防车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_MACHINESHOP_TRUCK = 11; // 工程车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_POWER_LOT_VEHICLE = 12; // 粉粒物料车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_SUCTION_SEWAGE_TRUCK = 13; // 吸污车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_NORMAL_TANK_TRUCK = 14; // 普通罐车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_SCHOOL_BUS = 15; // 校车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_EXCAVATOR = 16; // 挖掘机
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_BULLDOZER = 17; // 推土车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_CRANE = 18;   // 吊车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_PUMP_TRUCK = 19; // 泵车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_POULTRY = 20; // 禽畜车
        public static final int   EM_VEHICLE_TYPE_BY_FUNC_TRACTOR = 21; // 拖拉机
    }

    // 标准车辆类型
    public static class EM_STANDARD_VEHICLE_TYPE extends SdkStructure
    {
        public static final int   EM_STANDARD_VEHICLE_UNKNOWN = 0;      // 未知
        public static final int   EM_STANDARD_VEHICLE_MOTOR = 1;        // 机动车
        public static final int   EM_STANDARD_VEHICLE_BUS = 2;          // 公交车
        public static final int   EM_STANDARD_VEHICLE_UNLICENSED_MOTOR = 3; // 无牌机动车
        public static final int   EM_STANDARD_VEHICLE_LARGE_CAR = 4;    // 大型汽车
        public static final int   EM_STANDARD_VEHICLE_MICRO_CAR = 5;    // 小型汽车
        public static final int   EM_STANDARD_VEHICLE_EMBASSY_CAR = 6;  // 使馆汽车
        public static final int   EM_STANDARD_VEHICLE_MARGINAL_CAR = 7; // 领馆汽车
        public static final int   EM_STANDARD_VEHICLE_AREAOUT_CAR = 8;  // 境外汽车
        public static final int   EM_STANDARD_VEHICLE_FOREIGN_CAR = 9;  // 外籍汽车
        public static final int   EM_STANDARD_VEHICLE_FARM_TRANS_CAR = 10; // 农用运输车
        public static final int   EM_STANDARD_VEHICLE_TRACTOR = 11;     // 拖拉机
        public static final int   EM_STANDARD_VEHICLE_TRAILER = 12;     // 挂车
        public static final int   EM_STANDARD_VEHICLE_COACH_CAR = 13;   // 教练汽车
        public static final int   EM_STANDARD_VEHICLE_TRIAL_CAR = 14;   // 试验汽车
        public static final int   EM_STANDARD_VEHICLE_TEMPORARYENTRY_CAR = 15; // 临时入境汽车
        public static final int   EM_STANDARD_VEHICLE_TEMPORARYENTRY_MOTORCYCLE = 16; // 临时入境摩托
        public static final int   EM_STANDARD_VEHICLE_TEMPORARY_STEER_CAR = 17; // 临时行驶车
        public static final int   EM_STANDARD_VEHICLE_LARGE_TRUCK = 18; // 大货车
        public static final int   EM_STANDARD_VEHICLE_MID_TRUCK = 19;   // 中货车
        public static final int   EM_STANDARD_VEHICLE_MICRO_TRUCK = 20; // 小货车
        public static final int   EM_STANDARD_VEHICLE_MICROBUS = 21;    // 面包车
        public static final int   EM_STANDARD_VEHICLE_SALOON_CAR = 22;  // 轿车
        public static final int   EM_STANDARD_VEHICLE_CARRIAGE = 23;    // 小轿车
        public static final int   EM_STANDARD_VEHICLE_MINI_CARRIAGE = 24; // 微型轿车
        public static final int   EM_STANDARD_VEHICLE_SUV_MPV = 25;     // SUV或者MPV
        public static final int   EM_STANDARD_VEHICLE_SUV = 26;         // SUV
        public static final int   EM_STANDARD_VEHICLE_MPV = 27;         // MPV
        public static final int   EM_STANDARD_VEHICLE_PASSENGER_CAR = 28; // 客车
        public static final int   EM_STANDARD_VEHICLE_MOTOR_BUS = 29;   // 大客车
        public static final int   EM_STANDARD_VEHICLE_MID_PASSENGER_CAR = 30; // 中客车
        public static final int   EM_STANDARD_VEHICLE_MINI_BUS = 31;    // 小客车
        public static final int   EM_STANDARD_VEHICLE_PICKUP = 32;      // 皮卡车
        public static final int   EM_STANDARD_VEHICLE_OILTANK_TRUCK = 33; // 油罐车
    }

    // 报警事件类型 EVENT_ALARM_LOCALALARM(外部报警),EVENT_ALARM_MOTIONALARM(动检报警)报警)
    public static class DEV_EVENT_ALARM_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           Reserved = new byte[4];               // 保留字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public byte             byEventAction;                        // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]           byReserved = new byte[3];             // 保留字节
        public int              emSenseType;                          // 传感器类型,参考NET_SENSE_METHOD
        public int              emDefenceAreaType;                    // 防区类型 ,参考EM_NET_DEFENCE_AREA_TYPE
        public NET_GPS_STATUS_INFO stuGPS;                            // GPS信息
        public byte[]           szUserID = new byte[32];              // 本地报警时登陆的用户ID
        public byte[]           szUserName = new byte[128];           // 本地报警时登陆的用户名
        public byte[]           szSN = new byte[32];                  // 设备序列号
        public int              bExAlarmIn;                           // 外部输入报警
        public NET_FILE_PROCESS_INFO stuFileProcessInfo;              // 图片与智能事件信息
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           byReservedEx = new byte[512];         // 保留字节
    }

    // 报警事件类型 EVENT_ALARM_VIDEOBLIND(视频遮挡报警)
    public static class DEV_EVENT_ALARM_VIDEOBLIND extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[128];               // 事件名称
        public byte[]           Reserved = new byte[4];               // 保留字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_TIME_EX      stuTime;                              // 事件发生的时间, (设备时间, 不一定是utc时间)
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public NET_GPS_STATUS_INFO stuGPSStatus;                      // GPS信息
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public NET_IMAGE_INFO_EX stuImageInfo[] = (NET_IMAGE_INFO_EX[])new NET_IMAGE_INFO_EX().toArray(6); //图片信息扩展
        public int              nImageInfo;                           //图片信息扩展的个数
        public NET_IMAGE_INFO_EX2 stuImageInfoEx2[] = (NET_IMAGE_INFO_EX2[])new NET_IMAGE_INFO_EX2().toArray(32); //图片信息数组
        public int              nImageInfoEx2Num;                     //图片信息个数
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public int              bEventAction;                         // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte             byReserved[] = new byte[1023];        //预留字节
    }

    // 事件类型 EVENT_IVS_HIGHSPEED(车辆超速报警事件）对应的数据块描述信息
    public static class DEV_EVENT_HIGHSPEED_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nAction;                              // 事件动作,1表示持续性事件开始,2表示持续性事件结束;
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public byte[]           byReserved = new byte[4];             // 保留字节
        public double           dbPTS;                                // 时间戳(单位是毫秒)
        public NET_GPS_STATUS_INFO stGPSStatusInfo;                   // GPS信息
        public int              nSpeedLimit;                          // 车连限速值(km/h)
        public int              nCurSpeed;                            // 当前车辆速度(km/h)
        public int              nMaxSpeed;                            // 最高速度(Km/h)
        public NET_TIME_EX      stuStartTime;                         // 开始时间(需求),nAction为2时上报此字段
        public byte[]           byReserved1 = new byte[1024];         // 保留字节
    }

    // 事件类型EVENT_IVS_TIREDPHYSIOLOGICAL(生理疲劳驾驶事件)对应的数据块描述信息
    public static class DEV_EVENT_TIREDPHYSIOLOGICAL_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public NET_GPS_STATUS_INFO stuGPSStatus;                      // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public byte[]           bReserved = new byte[736];            // 保留字节,留待扩展.

        public DEV_EVENT_TIREDPHYSIOLOGICAL_INFO() {
        	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
        		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
        }
    }

    // 事件类型EVENT_IVS_TRAFFIC_TIREDLOWERHEAD(开车低头报警事件)对应的数据块描述信息
    public static class DEV_EVENT_TIREDLOWERHEAD_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_GPS_STATUS_INFO stuGPSStatus;                      // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public byte[]           bReserved = new byte[736];            // 保留字节,留待扩展.

        public DEV_EVENT_TIREDLOWERHEAD_INFO() {
        	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
        		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
        }
    }

    // 事件类型EVENT_IVS_TRAFFIC_DRIVERLEAVEPOST(开车离岗报警事件)对应的数据块描述信息
    public static class DEV_EVENT_DRIVERLEAVEPOST_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_GPS_STATUS_INFO stuGPSStatus;                      // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public byte[]           bReserved = new byte[736];            // 保留字节,留待扩展.

        public DEV_EVENT_DRIVERLEAVEPOST_INFO() {
        	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
        		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
        }
    }

    // 事件类型 EVENT_IVS_TRAFFIC_DRIVERYAWN (开车打哈欠事件) 对应的数据块描述信息
    public static class DEV_EVENT_DRIVERYAWN_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           szReserved1 = new byte[4];            // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_GPS_STATUS_INFO stuGPSStatus;                      // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public byte[]           bReserved = new byte[736];            // 保留字节,留待扩展.

        public DEV_EVENT_DRIVERYAWN_INFO() {
        	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
        		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
        }
    }

    // 事件类型 EVENT_IVS_FORWARDCOLLISION_WARNNING(前向碰撞预警) 对应的数据块描述信息
    public static class DEV_EVENT_FORWARDCOLLISION_WARNNING_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nEventID;                             // 事件ID
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public NET_GPS_STATUS_INFO stuGPSStatusInfo;                  // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public byte[]           bReserved = new byte[736];            // 保留字节

        public DEV_EVENT_FORWARDCOLLISION_WARNNING_INFO() {
        	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
        		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
        }
    }

    // 事件类型 EVNET_IVS_LANEDEPARTURE_WARNNING(车道偏移预警) 对应的数据块描述信息
    public static class DEV_EVENT_LANEDEPARTURE_WARNNING_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public int              nEventID;                             // 事件ID
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nAction;                              // 0:脉冲 1:开始 2:停止
        public NET_GPS_STATUS_INFO stuGPSStatusInfo;                  // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public byte[]           bReserved = new byte[736];            // 保留字节,留待扩展.

        public DEV_EVENT_LANEDEPARTURE_WARNNING_INFO() {
        	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
        		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
        }
    }

    //图片路径类型
    public static class NET_PICTURE_PATH_TYPE extends SdkStructure
    {
        public static final int   NET_PATH_LOCAL_PATH = 0;              // 本地硬盘或者sd卡成功写入路径
        public static final int   NET_PATH_FTP_PATH = 1;                // 设备成功写到ftp服务器的路径
        public static final int   NET_PATH_VIDEO_PATH = 2;              // 当前接入需要获取当前违章的关联视频的FTP上传路径
    }

    public static class NET_RESERVED_PATH extends SdkStructure
    {
        public int              nMaxPathNum;                          // 图片路径总数,为0时采用设备默认路径
        public int[]            emPictruePaths = new int[MAX_PIC_PATH_NUM]; // 图片路径类型，详见NET_PICTURE_PATH_TYPE
    }

    //离线传输参数
    public static class NET_OFFLINE_PARAM extends SdkStructure
    {
        public byte[]           szClientID = new byte[20];            // 客户端mac地址，冒号分隔形式
        public byte[]           szUUID = new byte[64];                //客户端惟一标识
        public byte[]           byReserved = new byte[44];            // 保留
    }

    public static class NET_RESERVED_COMMON extends SdkStructure
    {
        public int              dwStructSize;
        public Pointer          pIntelBox;                            // 兼容RESERVED_TYPE_FOR_INTEL_BOX，指向ReservedDataIntelBox
        public int              dwSnapFlagMask;                       // 抓图标志(按位),0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public Pointer          pstuOfflineParam;                     // 离线传输参数，指向NET_OFFLINE_PARAM
        public Pointer          pstuPath;                             // 兼容RESERVED_TYPE_FOR_PATH，指向NET_RESERVED_PATH
        public int              emPathMode;                           // 返回的图片存储路径模式,对应枚举EM_PATH_MODE
        /**
         * 对应结构体{@link EM_FILTER_IMAGE_TYPE}
         */
        public Pointer          pImageType;                           // 返回的图片类型, 由用户申请释放
        public int              nImageTypeNum;                        // pImageType 有效个数
        public int              bFlagCustomInfo;                      // szCustomInfo 标志位 TRUE时 使用szCustomInfo字段
        public byte[]           szCustomInfo = new byte[512];         // 客户自定义信息 customInfo是getFiltercaps中能力对应的订阅参数的格式化字符串表示,字符串格式为：订阅参数以&分隔，订阅参数的名字和值用=连接
        public int              bSetEventsType;                       // 是否设置事件类型
        public int              nVOLayer;                             // 视频输出口图层, 0:未知 1:视频层 2:GUI层 3:动态跑马显示
        public int              emOrder;                              // 设备给客户端传离线图片需要按照的顺序，如果是倒序上传:越晚产生的优先上传，越早产生的后传,参考枚举EM_EVENT_ORDER
        public int              nVOImageType;                         //输出口抓屏图片格式  0:未知 1:NV12 2:YUV422层 3:RGB565 4:XRGB8888 5:JPEG
        public int              bNeedFeatureVectorVaild;              //bNeedFeatureVector字段是否有效
        public int              bNeedFeatureVector;                   //是否需要上传特征向量，只有在NeedData为false时有效

    	public NET_RESERVED_COMMON()
        {
            this.dwStructSize = this.size();
        }
    }

    // 事件类型EVENT_IVS_TRAFFIC_DRIVER_SMOKING (驾驶员抽烟事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_DRIVER_SMOKING extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public int              nTriggerType;                         // TriggerType:触发类型,0车检器,1雷达,2视频
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             byEventAction;                        // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;    BYTE                    byReserved1[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public byte[]           byReserved1 = new byte[2];
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLane;                                // 对应车道号
        public int              nMark;                                // 底层产生的触发抓拍帧标记
        public int              nFrameSequence;                       // 视频分析帧序号
        public int              nSource;                              // 视频分析的数据源地址
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar;       // 交通车辆信息
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           byReserved = new byte[948-2*POINTERSIZE]; // 保留字节
    }

    // 事件类型EVENT_IVS_TRAFFIC_DRIVER_CALLING(驾驶员打电话事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_DRIVER_CALLING extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public int              nTriggerType;                         // TriggerType:触发类型,0车检器,1雷达,2视频
        public int              PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public int              nSequence;                            // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte             byEventAction;                        // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;    BYTE                    byReserved1[2];
        public byte             byImageIndex;                         // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public byte[]           byReserved1 = new byte[2];
        public NET_EVENT_FILE_INFO stuFileInfo;                       // 事件对应文件信息
        public int              nLane;                                // 对应车道号
        public int              nMark;                                // 底层产生的触发抓拍帧标记
        public int              nFrameSequence;                       // 视频分析帧序号
        public int              nSource;                              // 视频分析的数据源地址
        public NET_MSG_OBJECT   stuObject;                            // 检测到的物体
        public NET_MSG_OBJECT   stuVehicle;                           // 车身信息
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stuTrafficCar;       // 交通车辆信息
        public int              nSpeed;                               // 车辆实际速度,Km/h
        public int              dwSnapFlagMask;                       // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public NET_RESOLUTION_INFO stuResolution;                     // 对应图片的分辨率
        public EVENT_COMM_INFO  stCommInfo;                           // 公共信息
        public NET_GPS_INFO     stuGPSInfo;                           // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public Pointer          pstuImageInfo;                        //图片信息数组,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.structure.NET_IMAGE_INFO_EX3}
        public int              nImageInfoNum;                        //图片信息个数
        public Pointer          pstuSceneImage;                       //全景图图片信息,参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.SCENE_IMAGE_INFO_EX}
        public byte[]           byReserved = new byte[948-POINTERSIZE*2]; // 保留字节

		public DEV_EVENT_TRAFFIC_DRIVER_CALLING() {
			for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
				stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
		}
    }

    // 事件类型EVENT_IVS_TRAFFIC_DRIVERLOOKAROUND(开车左顾右盼报警事件)对应的数据块描述信息
    public static class DEV_EVENT_DRIVERLOOKAROUND_INFO extends SdkStructure
    {
        public int              nChannelID;                           // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN]; // 事件名称
        public byte[]           bReserved1 = new byte[4];             // 字节对齐
        public double           PTS;                                  // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                  // 事件发生的时间
        public int              nEventID;                             // 事件ID
        public NET_GPS_STATUS_INFO stuGPSStatus;                      // GPS信息
        public byte[]           szDriverID = new byte[32];            // 司机ID
        public byte[]           szVideoPath = new byte[256];          // 违章关联视频FTP上传路径
        public int              nRelatingVideoInfoNum;                // 违章关联的多个视频信息个数
        public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16]; // 违章关联的多个视频信息数组，最多支持16个视频
        public byte[]           bReserved = new byte[736];            // 保留字节,留待扩展.

        public DEV_EVENT_DRIVERLOOKAROUND_INFO() {
        	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
        		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
			}
        }
    }

    // 进站离站状态
    public static class NET_BUS_STATE extends SdkStructure
    {
        public static final int   NET_BUS_STATE_UNKNOWN = 0;            // 未知
        public static final int   NET_BUS_STATE_ILLEGAL = 1;            // 非法
        public static final int   NET_BUS_STATE_LEGAL = 2;              // 合法
    }

    // 报警事件类型NET_ALARM_ENCLOSURE_ALARM(电子围栏事件)对应的数据描述信息
    public static class ALARM_ENCLOSURE_ALARM_INFO extends SdkStructure
    {
        public int              dwSize;
        public int              dwAlarmType;                          // 报警类型, 按位分别表示,
        // 0:LimitSpeed, 1:DriveAllow, 2:ForbidDrive, 3:LoadGoods, 4:UploadGoods
        public int              dwAlarmDetail;                        // 报警描述, 按位分别表示,
        // 0:DriveIn, 1:DriveOut, 2:Overspeed, 3:SpeedClear
        public int              emState;                              // 是否按规定时间触发事件，详见NET_BUS_STATE
        public int              dwDriverNo;                           // 司机编号
        public int              dwEnclosureID;                        // 围栏ID
        public int              dwLimitSpeed;                         // 限速
        public int              dwCurrentSpeed;                       // 当前速度
        public NET_TIME_EX      stuTime;                              // 当前时间
        public NET_GPS_STATUS_INFO stuGPSStatusInfo;                  // GPS信息
        public int              bRealUTC;                             //stuRealUTC 是否有效，bRealUTC 为 TRUE 时，用 stuRealUTC，否则用 stuTime 字段
        public NET_TIME_EX      stuRealUTC = new NET_TIME_EX();       //事件发生的时间(标准UTC时间),参见结构体定义 {@link org.springblade.modules.iot.dahua.lib.NetSDKLib.NET_TIME_EX}

        public ALARM_ENCLOSURE_ALARM_INFO()
        {
            this.dwSize = this.size();
        }
    }

    public static class DEV_SET_RESULT extends SdkStructure
    {
        public int              dwType;                               // 类型(即GetDevConfig和SetDevConfig的类型)
        public short            wResultCode;                          // 返回码；0：成功,1：失败,2：数据不合法,3：暂时无法设置,4：没有权限
        public short            wRebootSign;                          // 重启标志；0：不需要重启,1：需要重启才生效
        public int[]            dwReserved = new int[2];              // 保留
    }

    // ALARM_ENCLOSURE_INFO
    // 电子围栏报警
    public static class ALARM_ENCLOSURE_INFO extends SdkStructure
    {
        public int              nTypeNumber;                          // 有效电子围栏类型个数
        public byte[]           bType = new byte[16];                 // 电子围栏类型, 见 ENCLOSURE_TYPE
        public int              nAlarmTypeNumber;                     // 有效报警类型个数
        public byte[]           bAlarmType = new byte[16];            // 报警类型,见ENCLOSURE_ALARM_TYPE
        public byte[]           szDriverId = new byte[32];            // 司机工号
        public int              unEnclosureId;                        // 电子围栏ID
        public int              unLimitSpeed;                         // 限速,单位km/h
        public int              unCurrentSpeed;                       // 当前速度
        public NET_TIME         stAlarmTime;                          // 报警发生时间
        public int              dwLongitude;                          // 经度(单位是百万分之度,范围0-360度)如东经120.178274度表示为300178274
        public int              dwLatidude;                           // 纬度(单位是百万分之度,范围0-180度)如北纬30.183382度表示为12018338
        // 经纬度的具体转换方式可以参考结构体 NET_WIFI_GPS_INFO 中的注释
        public byte             bOffline;                             // 0-实时 1-补传
        public byte[]           reserve = new byte[3];                // 字节对齐
        public int              unTriggerCount;                       // 围栏触发次数
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public int              bIsAlarmEnclosureInfoEx;              // 该值为TRUE时应使用 ALARM_ENCLOSURE_INFO_EX 结构体中字段
        public ALARM_ENCLOSURE_INFO_EX stuAlarmEnclosureInfoEx = new ALARM_ENCLOSURE_INFO_EX(); // 当走三代事件时数据存在该结构体
        public byte[]           byReserved = new byte[128];           // 保留字节
    }

    // NETDEV_3GFLOW_EXCEED_STATE_INFO
    // 3G流量超出阈值状态信息
    public static class NETDEV_3GFLOW_EXCEED_STATE_INFO extends SdkStructure
    {
        public byte             bState;                               // 3G流量超出阈值状态,0表示未超出阀值,1表示超出阀值
        public byte[]           reserve1 = new byte[3];               // 字节对齐
        public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND(); //事件公共扩展字段结构体
        public byte[]           reserve = new byte[32];               // 保留字节
    }

    // 飞行器类型
    public static class ENUM_UAV_TYPE extends SdkStructure
    {
        public static final int   ENUM_UAV_TYPE_GENERIC = 0;            // 通用
        public static final int   ENUM_UAV_TYPE_FIXED_WING = 1;         // 固定翼
        public static final int   ENUM_UAV_TYPE_QUADROTOR = 2;          // 四轴
        public static final int   ENUM_UAV_TYPE_COAXIAL = 3;            // 共轴
        public static final int   ENUM_UAV_TYPE_HELICOPTER = 4;         // 直机
        public static final int   ENUM_UAV_TYPE_ANTENNA_TRACKER = 5;    // 地面跟踪天线
        public static final int   ENUM_UAV_TYPE_GCS = 6;                // 地面站
        public static final int   ENUM_UAV_TYPE_AIRSHIP = 7;            // 有控飞艇
        public static final int   ENUM_UAV_TYPE_FREE_BALLOON = 8;       // 自由飞气球
        public static final int   ENUM_UAV_TYPE_ROCKET = 9;             // 火箭
        public static final int   ENUM_UAV_TYPE_GROUND_ROVER = 10;      // 地面车辆
        public static final int   ENUM_UAV_TYPE_SURFACE_BOAT = 11;      // 水面船艇
        public static final int   ENUM_UAV_TYPE_SUBMARINE = 12;         // 潜艇
        public static final int   ENUM_UAV_TYPE_HEXAROTOR = 13;         // 六轴
        public static final int   ENUM_UAV_TYPE_OCTOROTOR = 14;         // 八轴
        public static final int   ENUM_UAV_TYPE_TRICOPTER = 15;         // 三轴
        public static final int   ENUM_UAV_TYPE_FLAPPING_WING = 16;     // 扑翼机
        public static final int   ENUM_UAV_TYPE_KITE = 17;              // 风筝
        public static final int   ENUM_UAV_TYPE_ONBOARD_CONTROLLER = 18; // 控制器
        public static final int   ENUM_UAV_TYPE_VTOL_DUOROTOR = 19;     // 两翼VTOL
        public static final int   ENUM_UAV_TYPE_VTOL_QUADROTOR = 20;    // 四翼VTOL
        public static final int   ENUM_UAV_TYPE_VTOL_TILTROTOR = 21;    // 倾转旋翼机
        public static final int   ENUM_UAV_TYPE_VTOL_RESERVED2 = 22;    // VTOL 保留2
        public static final int   ENUM_UAV_TYPE_VTOL_RESERVED3 = 23;    // VTOL 保留3
        public static final int   ENUM_UAV_TYPE_VTOL_RESERVED4 = 24;    // VTOL 保留4
        public static final int   ENUM_UAV_TYPE_VTOL_RESERVED5 = 25;    // VTOL 保留5
        public static final int   ENUM_UAV_TYPE_GIMBAL = 26;            // 常平架
        public static final int   ENUM_UAV_TYPE_ADSB = 27;              // ADSB
    }

    // 飞行器模式
    public static class ENUM_UAV_MODE extends SdkStructure
    {
        public static final int   ENUM_UAV_MODE_UNKNOWN = 0;            // 未知模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_MANUAL = 0;  // 固定翼 手动模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_CIRCLE = 1;  // 固定翼 绕圈模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_STABILIZE = 2; // 固定翼 自稳模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_TRAINING = 3; // 固定翼 训练模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_ACRO = 4;    // 固定翼 特技模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_FLY_BY_WIRE_A = 5; // 固定翼 A翼飞行模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_FLY_BY_WIRE_B = 6; // 固定翼 B翼飞行模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_CRUISE = 7;  // 固定翼 巡航模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_AUTOTUNE = 8; // 固定翼 自动统调
        public static final int   ENUM_UAV_MODE_FIXED_WING_AUTO = 10;   // 固定翼 智能模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_RTL = 11;    // 固定翼 返航模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_LOITER = 12; // 固定翼 定点模式
        public static final int   ENUM_UAV_MODE_FIXED_WING_GUIDED = 15; // 固定翼 引导模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_STABILIZE = 100; // 四轴 自稳模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_ACRO = 101;   // 四轴 特技模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_ALT_HOLD = 102; // 四轴 定高模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_AUTO = 103;   // 四轴 智能模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_GUIDED = 104; // 四轴 引导模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_LOITER = 105; // 四轴 定点模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_RTL = 106;    // 四轴 返航模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_CIRCLE = 107; // 四轴 绕圈模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_LAND = 109;   // 四轴 降落模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_OF_LOITER = 110; // 四轴 启用光流的悬停模式需要光流传感器来保持位置和高度
        public static final int   ENUM_UAV_MODE_QUADROTOR_TOY = 111;    // 四轴 飘移模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_SPORT = 113;  // 四轴 运动模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_AUTOTUNE = 115; // 四轴 自动统调
        public static final int   ENUM_UAV_MODE_QUADROTOR_POSHOLD = 116; // 四轴 保持模式
        public static final int   ENUM_UAV_MODE_QUADROTOR_BRAKE = 117;  // 四轴 制动模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_MANUAL = 200; // 地面车辆 手动模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_LEARNING = 202; // 地面车辆 学习模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_STEERING = 203; // 地面车辆 驾驶模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_HOLD = 204; // 地面车辆 锁定模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_AUTO = 210; // 地面车辆 智能模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_RTL = 211; // 地面车辆 返航模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_GUIDED = 215; // 地面车辆 引导模式
        public static final int   ENUM_UAV_MODE_GROUND_ROVER_INITIALIZING = 216; // 地面车辆 初始化模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_STABILIZE = 300; // 六轴 自稳模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_ACRO = 301;   // 六轴 特技模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_ALT_HOLD = 302; // 六轴 定高模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_AUTO = 303;   // 六轴 智能模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_GUIDED = 304; // 六轴 引导模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_LOITER = 305; // 六轴 定点模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_RTL = 306;    // 六轴 返航模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_CIRCLE = 307; // 六轴 绕圈模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_LAND = 309;   // 六轴 降落模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_OF_LOITER = 310; // 六轴 启用光流的悬停模式需要光流传感器来保持位置和高度
        public static final int   ENUM_UAV_MODE_HEXAROTOR_DRIFT = 311;  // 六轴 飘移模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_SPORT = 313;  // 六轴 运动模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_AUTOTUNE = 315; // 六轴 自动统调
        public static final int   ENUM_UAV_MODE_HEXAROTOR_POSHOLD = 316; // 六轴 保持模式
        public static final int   ENUM_UAV_MODE_HEXAROTOR_BRAKE = 317;  // 六轴 制动模式
    }

    // 无人机系统状态
    public static class ENUM_UAV_STATE extends SdkStructure
    {
        public static final int   ENUM_UAV_STATE_UNINIT = 0;            // 未初始化, 状态未知
        public static final int   ENUM_UAV_STATE_BOOT = 1;              // 正在启动
        public static final int   ENUM_UAV_STATE_CALIBRATING = 2;       // 正在校准,未准备好起飞.
        public static final int   ENUM_UAV_STATE_STANDBY = 3;           // 系统地面待命,随时可以起飞.
        public static final int   ENUM_UAV_STATE_ACTIVE = 4;            // 开车/开航. 发动机已经启动.
        public static final int   ENUM_UAV_STATE_CRITICAL = 5;          // 系统处于失常飞行状态,仍能导航.
        public static final int   ENUM_UAV_STATE_EMERGENCY = 6;         // 系统处于失常飞行状态,若干设备失灵,坠落状态.
        public static final int   ENUM_UAV_STATE_POWEROFF = 7;          // 系统刚执行了关机指令,正在关闭.
    }

    // UAV 系统当前模式
    public static class NET_UAV_SYS_MODE_STATE extends SdkStructure
    {
        public int              bSafetyArmedEnabled;                  // 主发动机使能, 准备好起飞.
        public int              bManualInputEnabled;                  // 遥控输入信号使能.
        public int              bHILEnabled;                          // HIL硬件环在线模拟使能.所有发动机, 舵机及其他动作设备阻断, 但内部软件处于全部可操作状态.
        public int              bStabilizeEnabled;                    // 高度/位置电子增稳使能.在此状态下,飞行器仍需要外部操作指令以实现操作
        public int              bGuidedEnabled;                       // 导航使能.导航数据和指令来自导航/航点指令表文件
        public int              bAutoEnabled;                         // 全自主航行模式使能.系统自行决定目的地.前一项“导航使能”可以设置为TURE或FLASE状态
        public int              bTestEnabled;                         // 测试模式使能.本标识仅供临时的系统测试之用,不应该用于实际航行的应用中.
        public int              bReserved;                            // 保留模式
    }

    // 心跳状态信息
    public static class NET_UAV_HEARTBEAT extends SdkStructure
    {
        public int              emUAVMode;                            // 飞行模式和飞行器形态类型相关，详见ENUM_UAV_MODE
        public int              emUAVType;                            // 飞行器形态类型，详见ENUM_UAV_TYPE
        public int              emSystemStatus;                       // 系统状态，详见ENUM_UAV_STATE
        public NET_UAV_SYS_MODE_STATE stuBaseMode;                    // 系统当前模式
        public byte[]           byReserved = new byte[8];             // 保留字节
    }

    // 传感器
    public static class ENUM_UAV_SENSOR extends SdkStructure
    {
        public static final int   ENUM_UAV_SENSOR_UNKNOWN = 0;          // 未知类型
        public static final int   ENUM_UAV_SENSOR_3D_GYRO = 1;          // 三轴陀螺
        public static final int   ENUM_UAV_SENSOR_3D_ACCEL = 2;         // 三轴加速度/倾角仪
        public static final int   ENUM_UAV_SENSOR_3D_MAG = 3;           // 三轴磁罗盘
        public static final int   ENUM_UAV_SENSOR_ABSOLUTE_PRESSURE = 4; // 绝对气压计
        public static final int   ENUM_UAV_SENSOR_DIFFERENTIAL_PRESSURE = 5; // 相对气压计
        public static final int   ENUM_UAV_SENSOR_GPS = 6;              // GPS
        public static final int   ENUM_UAV_SENSOR_OPTICAL_FLOW = 7;     // 光测设备
        public static final int   ENUM_UAV_SENSOR_VISION_POSITION = 8;  // 计算机视觉定位仪
        public static final int   ENUM_UAV_SENSOR_LASER_POSITION = 9;   // 激光定位
        public static final int   ENUM_UAV_SENSOR_EXTERNAL_GROUND_TRUTH = 10; // 外部激光定位（Vicon 或徕卡）
        public static final int   ENUM_UAV_SENSOR_ANGULAR_RATE_CONTROL = 11; // 三轴角速度控制器
        public static final int   ENUM_UAV_SENSOR_ATTITUDE_STABILIZATION = 12; // 高度稳定器
        public static final int   ENUM_UAV_SENSOR_YAW_POSITION = 13;    // 方向稳定器（锁尾等）
        public static final int   ENUM_UAV_SENSOR_Z_ALTITUDE_CONTROL = 14; // 高度控制器
        public static final int   ENUM_UAV_SENSOR_XY_POSITION_CONTROL = 15; // X/Y位置控制器
        public static final int   ENUM_UAV_SENSOR_MOTOR_OUTPUTS = 16;   // 马达输出控制器
        public static final int   ENUM_UAV_SENSOR_RC_RECEIVER = 17;     // RC 接收器
        public static final int   ENUM_UAV_SENSOR_3D_GYRO2 = 18;        // 2nd 三轴陀螺
        public static final int   ENUM_UAV_SENSOR_3D_ACCEL2 = 19;       // 2nd 三轴加速度/倾角仪
        public static final int   ENUM_UAV_SENSOR_3D_MAG2 = 20;         // 2nd 三轴磁罗盘
        public static final int   ENUM_UAV_GEOFENCE = 21;               // 地理围栏
        public static final int   ENUM_UAV_AHRS = 22;                   // 姿态子系统运行状况
        public static final int   ENUM_UAV_TERRAIN = 23;                // 地形子系统运行状况
        public static final int   ENUM_UAV_REVERSE_MOTOR = 24;          // 保留马达
    }

    // 传感器信息
    public static class NET_UAV_SENSOR extends SdkStructure
    {
        public int              emType;                               // 传感器类型，详见ENUM_UAV_SENSOR
        public int              bEnabled;                             // 使能状态
        public int              bHealthy;                             // 传感器状态
    }

    // 系统状态信息
    public static class NET_UAV_SYS_STATUS extends SdkStructure
    {
        public int              nPresentSensorNum;                    // 可见传感器个数, 最大支持32
        public NET_UAV_SENSOR[] stuSensors = new NET_UAV_SENSOR[UAV_MAX_SENSOR_NUM]; // 传感器信息
        public int              nBatteryVoltage;                      // 电池电压, 单位: 毫伏
        public int              nBatteryCurrent;                      // 电池电流, 单位: 10毫安
        public int              nChargeDischargeNum;                  // 电池充放电次数
        public int              nHomeDistance;                        // 距离Home的距离, 单位: 米
        public int              nRemainingFlightTime;                 // 剩余飞行时间, 单位: 秒
        public int              nRemainingBattery;                    // 剩余电量百分比 -1: 正在估测剩余电量
        public byte[]           byReserverd = new byte[16];           // 保留字节

        public NET_UAV_SYS_STATUS()
        {
            for (int i = 0; i < stuSensors.length; ++i) {
                stuSensors[i] = new NET_UAV_SENSOR();
            }
        }
    }

    // 卫星信息
    public static class NET_SATELLITE_STATUS extends SdkStructure
    {
        public int              bUsed;                                // 卫星使用状态 FLASE: 未使用 TURE: 使用
        public int              nID;                                  // 卫星ID
        public int              nElevation;                           // 卫星在天空中的仰角 单位: 度
        public int              nDireciton;                           // 卫星方位角 单位: 度
        public int              nSNR;                                 // 信噪比, 信号强度百分比
    }

    // GPS 可见卫星的状态信息
    public static class NET_UAV_GPS_STATUS extends SdkStructure
    {
        public int              nVisibleNum;                          // 可见卫星个数, 最多支持20个
        public NET_SATELLITE_STATUS[] stuSatellites = new NET_SATELLITE_STATUS[UAV_MAX_SATELLITE_NUM]; // 卫星信息

        public NET_UAV_GPS_STATUS()
        {
            for (int i = 0; i < stuSatellites.length; ++i) {
                stuSatellites[i] = new NET_SATELLITE_STATUS();
            }
        }
    }

    // 姿态信息
    public static class NET_UAV_ATTITUDE extends SdkStructure
    {
        public float            fRollAngle;                           // 滚转角, 单位: 度
        public float            fPitchAngle;                          // 俯仰角, 单位: 度
        public float            fYawAngle;                            // 偏航角, 单位: 度
        public byte[]           bReserved = new byte[16];             // 保留字节
    }

    // 遥控通道信息
    public static class NET_UAV_RC_CHANNELS extends SdkStructure
    {
        public int              nControllerSignal;                    // 遥控器信号百分比, 255: 非法未知
        public byte[]           byReserved = new byte[80];            // 保留字节
    }

    // 平视显示信息
    public static class NET_UAV_VFR_HUD extends SdkStructure
    {
        public float            fGroundSpeed;                         // 水平速度, 单位: 米/秒
        public float            fAltitude;                            // 高度, 单位: 米
        public float            fClimbSpeed;                          // 垂直速度, 单位: 米/秒
        public byte[]           byReserved = new byte[12];
    }

    // 故障等级
    public static class ENUM_UAV_SEVERITY extends SdkStructure
    {
        public static final int   ENUM_UAV_SEVERITY_EMERGENCY = 0;      // 系统不可用, 最紧急状态
        public static final int   ENUM_UAV_SEVERITY_ALERT = 1;          // 警报. 非致命性系统故障, 应立即应对.
        public static final int   ENUM_UAV_SEVERITY_CRITICAL = 2;       // 警报: 主要系统故障, 应立即应对
        public static final int   ENUM_UAV_SEVERITY_ERROR = 3;          // 故障: 次系统故障/备份系统故障
        public static final int   ENUM_UAV_SEVERITY_WARNING = 4;        // 警告
        public static final int   ENUM_UAV_SEVERITY_NOTICE = 5;         // 注意: 出现失常现象, 单非错误故障. 应该排查其现象根源
        public static final int   ENUM_UAV_SEVERITY_INFO = 6;           // 提示: 一般性操作消息, 可用于日志. 此消息不需要应对行动
        public static final int   ENUM_UAV_SEVERITY_DEBUG = 7;          // 调试信息。正常操作的时候不该出现
    }

    // 报警文本信息
    public static class NET_UAV_STATUSTEXT extends SdkStructure
    {
        public int              emSeverity;                           // 故障等级，详见ENUM_UAV_SEVERITY
        public byte[]           szText = new byte[60];                // 文本信息
        public byte[]           byReserved = new byte[4];             // 保留字节
    }

    // 全球定位数据
    public static class NET_UAV_GLOBAL_POSITION extends SdkStructure
    {
        public float            fLatitude;                            // 纬度, 单位: 角度
        public float            fLongitude;                           // 经度, 单位: 角度
        public int              nAltitude;                            // 海拔高度, 单位: 厘米
        public int              nRelativeAltitude;                    // 相对高度, 单位: 厘米
        public int              nXSpeed;                              // X速度（绝对速度、北东地坐标系）, 单位: 厘米每秒
        public int              nYSpeed;                              // Y速度（绝对速度、北东地坐标系）, 单位: 厘米每秒
        public int              nZSpeed;                              // Z速度（绝对速度、北东地坐标系）, 单位: 厘米每秒
        public byte[]           byReserved = new byte[12];
    }

    //  GPS原始数据
    public static class NET_UAV_GPS_RAW extends SdkStructure
    {
        public int              nDHOP;                                // GPS水平定位经度因子, 单位厘米. 65535 表示未知
        public int              nGroudSpeed;                          // GPS地速, 厘米每秒. 65535 表示未知
        public int              nVisibleStatellites;                  // 卫星数, 255 表示未知
        public int              nVDOP;                                // GPS 垂直定位因子，单位厘米。65535表示未知
        public int              nCourseOverGround;                    // 整体移动方向, 非机头移动方向. 单位: 100*度
        public int              nFixType;                             // 定位类型.  0 或 1 尚未定位, 2: 2D 定位, 3: 3D 定位
        public byte[]           byReserved = new byte[20];
    }

    // 系统时间
    public static class NET_UAV_SYS_TIME extends SdkStructure
    {
        public NET_TIME_EX      UTC;                                  // UTC 时间
        public int              dwBootTime;                           // 启动时间, 单位毫秒
    }

    // 当前航点
    public static class NET_UAV_MISSION_CURRENT extends SdkStructure
    {
        public int              nSequence;                            // 序号 0 ~ 700
        public byte[]           byReserved = new byte[16];            // 保留字节
    }

    // 到达航点
    public static class NET_UAV_MISSION_REACHED extends SdkStructure
    {
        public int              nSequence;                            // 序号 0 ~ 700
        public byte[]           byReserved = new byte[16];            // 保留字节
    }

    // 云台姿态
    public static class NET_UAV_MOUNT_STATUS extends SdkStructure
    {
        public float            fRollAngle;                           // 滚转角, 单位: 度
        public float            fPitchAngle;                          // 俯仰角, 单位: 度
        public float            fYawAngle;                            // 偏航角, 单位: 度
        public int              nTargetSystem;                        // 目标系统
        public int              nTargetComponent;                     // 目标部件
        public int              nMountMode;                           // 云台模式, 参照 NET_UAVCMD_MOUNT_CONFIGURE
        public byte[]           byReserved = new byte[8];             // 保留字节
    }

    // Home点位置信息
    public static class NET_UAV_HOME_POSITION extends SdkStructure
    {
        public float            fLatitude;                            // 纬度, 单位: 角度
        public float            fLongitude;                           // 经度, 单位: 角度
        public int              nAltitude;                            // 海拔高度, 单位: 厘米
        public float            fLocalX;                              // X 点
        public float            fLocalY;                              // Y 点
        public float            fLocalZ;                              // Z 点
        public float            fApproachX;                           // 本地 x 矢量点
        public float            fApproachY;
        public float            fApproachZ;
        public byte[]           byReserved = new byte[16];
    }

    // 无人机实时消息类型
    public static class EM_UAVINFO_TYPE extends SdkStructure
    {
        public static final int   EM_UAVINFO_TYPE_UNKNOWN = 0;          // 未知类型
        public static final int   EM_UAVINFO_TYPE_HEARTBEAT = 1;        // 心跳状态 *pInfo = NET_UAV_HEARTBEAT
        public static final int   EM_UAVINFO_TYPE_SYS_STATUS = 2;       // 系统状态 *pInfo = NET_UAV_SYS_STATUS
        public static final int   EM_UAVINFO_TYPE_GPS_STATUS = 3;       // GPS状态 *pInfo = NET_UAV_GPS_STATUS
        public static final int   EM_UAVINFO_TYPE_ATTITUDE = 4;         // 姿态信息 *pInfo = NET_UAV_ATTITUDE
        public static final int   EM_UAVINFO_TYPE_RC_CHANNELS = 5;      // 遥控通道信息 *pInfo = NET_UAV_RC_CHANNELS
        public static final int   EM_UAVINFO_TYPE_VFR_HUD = 6;          // 平视显示信息 *pInfo = NET_UAV_VFR_HUD
        public static final int   EM_UAVINFO_TYPE_STATUSTEXT = 7;       // 报警文本信息 *pInfo = NET_UAV_STATUSTEXT
        public static final int   EM_UAVINFO_TYPE_GLOBAL_POSITION = 8;  // 全球定位数据 *pInfo = NET_UAV_GLOBAL_POSITION
        public static final int   EM_UAVINFO_TYPE_GPS_RAW = 9;          // GPS原始数据 *pInfo = NET_UAV_GPS_RAW
        public static final int   EM_UAVINFO_TYPE_SYS_TIME = 10;        // 系统时间 *pInfo = NET_UAV_SYS_TIME
}
