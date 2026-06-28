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
 * 报警相关方法
 */
public interface AlarmMethods extends NetSDKLib {

    public LLong CLIENT_ParkingControlStartFind(LLong lLoginID, NET_IN_PARKING_CONTROL_START_FIND_PARAM pInParam, NET_OUT_PARKING_CONTROL_START_FIND_PARAM pOutParam, int waittime);

    // 获取过车记录
    public boolean CLIENT_ParkingControlDoFind(LLong lFindeHandle, NET_IN_PARKING_CONTROL_DO_FIND_PARAM pInParam, NET_OUT_PARKING_CONTROL_DO_FIND_PARAM pOutParam, int waittime);

    // 结束过车记录查询
    public boolean CLIENT_ParkingControlStopFind(LLong lFindHandle);

    // 车位状态订阅
    public LLong CLIENT_ParkingControlAttachParkInfo(LLong lLoginID, NET_IN_PARK_INFO_PARAM pInParam, NET_OUT_PARK_INFO_PARAM pOutParam, int nWaitTime);

    // 取消车位状态订阅
    public boolean CLIENT_ParkingControlDetachParkInfo(LLong lAttachHandle);

    // 清除异常车位车辆信息
    public boolean CLIENT_RemoveParkingCarInfo(LLong lLoginID, Pointer pInParam, Pointer pOutParam, int nWaitTime);

    // 电源控制
    public boolean CLIENT_PowerControl(LLong lLoginID, NET_IN_WM_POWER_CTRL pInParam, NET_OUT_WM_POWER_CTRL pOutParam, int nWaitTime);

    // 载入/保存预案
    public boolean CLIENT_LoadMonitorWallCollection(LLong lLoginID, NET_IN_WM_LOAD_COLLECTION pInParam, NET_OUT_WM_LOAD_COLLECTION pOutParam, int nWaitTime);

    public boolean CLIENT_SaveMonitorWallCollection(LLong lLoginID, NET_IN_WM_SAVE_COLLECTION pInParam, NET_OUT_WM_SAVE_COLLECTION pOutParam, int nWaitTime);

    // 获取电视墙预案
    public boolean CLIENT_GetMonitorWallCollections(LLong lLoginID, NET_IN_WM_GET_COLLECTIONS pInParam, NET_OUT_WM_GET_COLLECTIONS pOutParam, int nWaitTime);

    // 查询/设置显示源
    public boolean CLIENT_GetSplitSource(LLong lLoginID, int nChannel, int nWindow, NET_SPLIT_SOURCE[] pstuSplitSrc, int nMaxCount, IntByReference pnRetCount, int nWaitTime);

    public boolean CLIENT_SetSplitSource(LLong lLoginID, int nChannel, int nWindow, NET_SPLIT_SOURCE pstuSplitSrc, int nSrcCount, int nWaitTime);

    // 设置显示源
    public boolean CLIENT_SplitSetMultiSource(LLong lLoginID, NET_IN_SPLIT_SET_MULTI_SOURCE pInParam, NET_OUT_SPLIT_SET_MULTI_SOURCE pOutParam, int nWaitTime);

    // 查询矩阵子卡信息
    public boolean CLIENT_QueryMatrixCardInfo(LLong lLoginID, NET_MATRIX_CARD_LIST pstuCardList, int nWaitTime);

    // 开始查找录像文件帧信息
    public boolean CLIENT_FindFrameInfo(LLong lLoginID, NET_IN_FIND_FRAMEINFO_PRAM pInParam, NET_OUT_FIND_FRAMEINFO_PRAM pOutParam, int nWaitTime);

    // 获取标签信息
    public boolean CLIENT_FileStreamGetTags(LLong lFindHandle, NET_IN_FILE_STREAM_GET_TAGS_INFO pInParam, NET_OUT_FILE_STREAM_GET_TAGS_INFO pOutParam, int nWaitTime);

    // 设置标签信息
    public boolean CLIENT_FileStreamSetTags(LLong lFindHandle, NET_IN_FILE_STREAM_TAGS_INFO pInParam, NET_OUT_FILE_STREAM_TAGS_INFO pOutParam, int nWaitTime);

    // 查询/设置分割模式
    public boolean CLIENT_GetSplitMode(LLong lLoginID, int nChannel, NET_SPLIT_MODE_INFO pstuSplitInfo, int nWaitTime);

    public boolean CLIENT_SetSplitMode(LLong lLoginID, int nChannel, NET_SPLIT_MODE_INFO pstuSplitInfo, int nWaitTime);

    // 开窗/关窗
    public boolean CLIENT_OpenSplitWindow(LLong lLoginID, NET_IN_SPLIT_OPEN_WINDOW pInParam, NET_OUT_SPLIT_OPEN_WINDOW pOutParam, int nWaitTime);

    public boolean CLIENT_CloseSplitWindow(LLong lLoginID, NET_IN_SPLIT_CLOSE_WINDOW pInParam, NET_OUT_SPLIT_CLOSE_WINDOW pOutParam, int nWaitTime);

    // 获取当前显示的窗口信息
    public boolean CLIENT_GetSplitWindowsInfo(LLong lLoginID, NET_IN_SPLIT_GET_WINDOWS pInParam, NET_OUT_SPLIT_GET_WINDOWS pOutParam, int nWaitTime);

    // 查询分割能力
    public boolean CLIENT_GetSplitCaps(LLong lLoginID, int nChannel, NET_SPLIT_CAPS pstuCaps, int nWaitTime);

    // 下位矩阵切换
    public boolean CLIENT_MatrixSwitch(LLong lLoginID, NET_IN_MATRIX_SWITCH pInParam, NET_OUT_MATRIX_SWITCH pOutParam, int nWaitTime);

    // 打开刻录会话
    public LLong CLIENT_StartBurnSession(LLong lLoginID, NET_IN_START_BURN_SESSION pstInParam, NET_OUT_START_BURN_SESSION pstOutParam, int nWaitTime);

    // 关闭刻录会话
    public boolean CLIENT_StopBurnSession(LLong lBurnSession);

    // 开始/停止刻录
    public boolean CLIENT_StartBurn(LLong lBurnSession, NET_IN_START_BURN pstInParam, NET_OUT_START_BURN pstOutParam, int nWaitTime);

    public boolean CLIENT_StopBurn(LLong lBurnSession);

    // 暂停/恢复刻录
    public boolean CLIENT_PauseBurn(LLong lBurnSession, int bPause);

    // 获取刻录状态
    public boolean CLIENT_BurnGetState(LLong lBurnSession, NET_IN_BURN_GET_STATE pstInParam, NET_OUT_BURN_GET_STATE pstOutParam, int nWaitTime);

    // 监听刻录状态
    public LLong CLIENT_AttachBurnState(LLong lLoginID, NET_IN_ATTACH_STATE pstInParam, NET_OUT_ATTACH_STATE pstOutParam, int nWaitTime);

    public boolean CLIENT_DetachBurnState(LLong lAttachHandle);

    // 刻录附件上传
    public LLong CLIENT_StartUploadFileBurned(LLong lLoginID, NET_IN_FILEBURNED_START pstInParam, NET_OUT_FILEBURNED_START pstOutParam, int nWaitTime);

    public boolean CLIENT_SendFileBurned(LLong lUploadHandle);

    public boolean CLIENT_StopUploadFileBurned(LLong lUploadHandle);

    // 下载指定的智能分析数据
    public LLong CLIENT_DownloadMediaFile(LLong lLoginID, int emType, Pointer lpMediaFileInfo, String sSavedFileName, Callback cbDownLoadPos, Pointer dwUserData, Pointer reserved);

    public boolean CLIENT_StopDownloadMediaFile(LLong lFileHandle);

    // 下发通知到设备
    public boolean CLIENT_SendNotifyToDev(LLong lLoginID, int emNotifyType, Pointer pInParam, Pointer pOutParam, int nWaitTime);

    // 查询IO状态
    public boolean CLIENT_QueryIOControlState(LLong lLoginID, int emType, Pointer pState, int maxlen, IntByReference nIOCount, int waittime);

    // IO控制
    public boolean CLIENT_IOControl(LLong lLoginID, int emType, Pointer pState, int maxlen);

    // SCADA订阅
    public LLong CLIENT_SCADAAttachInfo(LLong lLoginID, NET_IN_SCADA_ATTACH_INFO pInParam, NET_OUT_SCADA_ATTACH_INFO pOutParam, int nWaitTime);

    public boolean CLIENT_SCADADetachInfo(LLong lAttachHandle);

    // 透明串口
    public LLong CLIENT_CreateTransComChannel(LLong lLoginID, int TransComType, int baudrate, int databits, int stopbits, int parity, Callback cbTransCom, Pointer dwUser);

    public boolean CLIENT_SendTransComData(LLong lTransComChannel, byte[] pBuffer, int dwBufSize);

    public boolean CLIENT_DestroyTransComChannel(LLong lTransComChannel);

    public boolean CLIENT_QueryTransComParams(LLong lLoginID, int TransComType, NET_COMM_STATE pCommState, int nWaitTime);

    // 订阅智能分析进度
    public boolean CLIENT_AttachVideoAnalyseState(LLong lLoginID, NET_IN_ATTACH_VIDEOANALYSE_STATE pstInParam, NET_OUT_ATTACH_VIDEOANALYSE_STATE pstOutParam, int nWaittime);

    public boolean CLIENT_DetachVideoAnalyseState(LLong lAttachHandle);

    // 抓图
    public boolean CLIENT_CapturePicture(LLong hPlayHandle, String pchPicFileName);

    public boolean CLIENT_CapturePictureEx(LLong hPlayHandle, String pchPicFileName, int eFormat);

    // 获取设备自检信息
    public boolean CLIENT_GetSelfCheckInfo(LLong lLoginID, NET_IN_GET_SELTCHECK_INFO pInParam, NET_SELFCHECK_INFO pOutParam, int nWaitTime);

    // 主动注册功能
    public LLong CLIENT_ListenServer(String ip, int port, int nTimeout, Callback cbListen, Pointer dwUserData);

    public boolean CLIENT_StopListenServer(LLong lServerHandle);

    // 指定回调数据类型预览
    public LLong CLIENT_RealPlayByDataType(LLong lLoginID, NET_IN_REALPLAY_BY_DATA_TYPE pstInParam, NET_OUT_REALPLAY_BY_DATA_TYPE pstOutParam, int dwWaitTime);

    // 指定回调数据类型回放
    public LLong CLIENT_PlayBackByDataType(LLong lLoginID, NET_IN_PLAYBACK_BY_DATA_TYPE pstInParam, NET_OUT_PLAYBACK_BY_DATA_TYPE pstOutParam, int dwWaitTime);

    // 指定码流类型下载
    public LLong CLIENT_DownloadByDataType(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int dwWaitTime);

    // BUS订阅
    public LLong CLIENT_AttachBusState(LLong lLoginID, NET_IN_BUS_ATTACH pstuInBus, NET_OUT_BUS_ATTACH pstuOutBus, int nWaitTime);

    public boolean CLIENT_DetachBusState(LLong lAttachHandle);

    // 订阅事件重传
    public LLong CLIENT_AttachEventRestore(LLong lLoginID, NET_IN_ATTACH_EVENT_RESTORE pInParam, int nWaitTime);

    public boolean CLIENT_DetachEventRestore(LLong lAttachHandle);

    // GPS温湿度订阅
    public void CLIENT_SetSubcribeGPSTHCallBack(Callback OnGPSMessage, Pointer dwUser);

    public boolean CLIENT_SubcribeGPSTempHumidity(LLong lLoginID, int bStart, int InterTime, Pointer Reserved);

    // 人脸信息操作
    public boolean CLIENT_FaceInfoOpreate(LLong lLoginID, int emType, Pointer pInParam, Pointer pOutParam, int nWaitTime);

    // 下发人脸图片信息
    public boolean CLIENT_DeliverUserFacePicture(LLong lLoginID, NET_IN_DELIVER_USER_PICTURE pInParam, NET_OUT_DELIVER_USER_PICTURE pOutParam, int nWaitTime);

    // 开始查询人脸信息
    public LLong CLIENT_StartFindFaceInfo(LLong lLoginID, NET_IN_FACEINFO_START_FIND pstIn, NET_OUT_FACEINFO_START_FIND pstOut, int nWaitTime);
}
