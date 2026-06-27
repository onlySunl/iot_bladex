package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.*;
import com.sun.jna.ptr.*;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * NetSDK 方法定义 - AlarmMethods.java
 */
public interface AlarmMethods {

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
}
