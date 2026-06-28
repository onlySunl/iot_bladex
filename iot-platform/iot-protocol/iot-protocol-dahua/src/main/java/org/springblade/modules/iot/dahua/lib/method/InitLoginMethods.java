package org.springblade.modules.iot.dahua.lib.method;

import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.constant.NetSDKConstants;
import org.springblade.modules.iot.dahua.lib.Utils;
import org.springblade.modules.iot.dahua.lib.LastError;
import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 初始化登录相关方法
 */
public interface InitLoginMethods extends NetSDKLib {

    public boolean CLIENT_Init(Callback cbDisConnect, Pointer dwUser);

    public void CLIENT_Cleanup();

    public void CLIENT_SetAutoReconnect(Callback cbAutoConnect, Pointer dwUser);

    public int CLIENT_GetLastError();

    public void CLIENT_SetConnectTime(int nWaitTime, int nTryTimes);

    public void CLIENT_SetNetworkParam(NET_PARAM pNetParam);

    public boolean CLIENT_SetDeviceSearchParam(NET_DEVICE_SEARCH_PARAM pstParam);

    public int CLIENT_GetSDKVersion();

    // 登录接口
    public LLong CLIENT_LoginEx(String pchDVRIP, int wDVRPort, String pchUserName, String pchPassword, int nSpecCap, Pointer pCapParam, NET_DEVICEINFO lpDeviceInfo, IntByReference error);

    public LLong CLIENT_LoginEx2(String pchDVRIP, int wDVRPort, String pchUserName, String pchPassword, int nSpecCap, Pointer pCapParam, NET_DEVICEINFO_Ex lpDeviceInfo, IntByReference error);

    // 注销
    public boolean CLIENT_Logout(LLong lLoginID);

    // 配置
    public boolean CLIENT_GetNewDevConfig(LLong lLoginID, String szCommand, int nChannelID, byte[] szOutBuffer, int dwOutBufferSize, IntByReference error, int waiitime, Pointer pReserved);

    public boolean CLIENT_SetNewDevConfig(LLong lLoginID, String szCommand, int nChannelID, byte[] szInBuffer, int dwInBufferSize, IntByReference error, IntByReference restart, int waittime);

    public boolean CLIENT_DeleteDevConfig(LLong lLoginID, NET_IN_DELETECFG pInParam, NET_OUT_DELETECFG pOutParam, int waittime);

    public boolean CLIENT_GetMemberNames(LLong lLoginID, NET_IN_MEMBERNAME pInParam, NET_OUT_MEMBERNAME pOutParam, int waittime);

    public boolean CLIENT_ParseData(String szCommand, byte[] szInBuffer, Pointer lpOutBuffer, int dwOutBufferSize, Pointer pReserved);

    public boolean CLIENT_PacketData(String szCommand, Pointer lpInBuffer, int dwInBufferSize, byte[] szOutBuffer, int dwOutBufferSize);

    // 报警回调
    public void CLIENT_SetDVRMessCallBack(Callback cbMessage, Pointer dwUser);

    public void CLIENT_SetDVRMessCallBackEx1(fMessCallBackEx1 cbMessage, Pointer dwUser);

    // 报警订阅
    public boolean CLIENT_StartListenEx(LLong lLoginID);

    public boolean CLIENT_StopListen(LLong lLoginID);

    // 人脸识别
    public boolean CLIENT_OperateFaceRecognitionDB(LLong lLoginID, NET_IN_OPERATE_FACERECONGNITIONDB pstInParam, NET_OUT_OPERATE_FACERECONGNITIONDB pstOutParam, int nWaitTime);

    public boolean CLIENT_StartFindFaceRecognition(LLong lLoginID, NET_IN_STARTFIND_FACERECONGNITION pstInParam, NET_OUT_STARTFIND_FACERECONGNITION pstOutParam, int nWaitTime);

    public boolean CLIENT_DoFindFaceRecognition(final NET_IN_DOFIND_FACERECONGNITION pstInParam, NET_OUT_DOFIND_FACERECONGNITION pstOutParam, int nWaitTime);

    public boolean CLIENT_StopFindFaceRecognition(LLong lFindHandle);

    public boolean CLIENT_DetectFace(LLong lLoginID, NET_IN_DETECT_FACE pstInParam, NET_OUT_DETECT_FACE pstOutParam, int nWaitTime);

    public boolean CLIENT_OperateFaceRecognitionGroup(LLong lLoginID, NET_IN_OPERATE_FACERECONGNITION_GROUP pstInParam, NET_OUT_OPERATE_FACERECONGNITION_GROUP pstOutParam, int nWaitTime);

    public boolean CLIENT_FindGroupInfo(LLong lLoginID, NET_IN_FIND_GROUP_INFO pstInParam, NET_OUT_FIND_GROUP_INFO pstOutParam, int nWaitTime);

    public boolean CLIENT_GetGroupInfoForChannel(LLong lLoginID, NET_IN_GET_GROUPINFO_FOR_CHANNEL pstInParam, NET_OUT_GET_GROUPINFO_FOR_CHANNEL pstOutParam, int nWaitTime);

    public boolean CLIENT_SetGroupInfoForChannel(LLong lLoginID, NET_IN_SET_GROUPINFO_FOR_CHANNEL pstInParam, NET_OUT_SET_GROUPINFO_FOR_CHANNEL pstOutParam, int nWaitTime);

    public boolean CLIENT_FaceRecognitionPutDisposition(LLong lLoginID, NET_IN_FACE_RECOGNITION_PUT_DISPOSITION_INFO pstInParam, NET_OUT_FACE_RECOGNITION_PUT_DISPOSITION_INFO pstOutParam, int nWaitTime);

    public boolean CLIENT_FaceRecognitionDelDisposition(LLong lLoginID, NET_IN_FACE_RECOGNITION_DEL_DISPOSITION_INFO pstInParam, NET_OUT_FACE_RECOGNITION_DEL_DISPOSITION_INFO pstOutParam, int nWaitTime);

    public LLong CLIENT_AttachFaceFindState(LLong lLoginID, NET_IN_FACE_FIND_STATE pstInParam, NET_OUT_FACE_FIND_STATE pstOutParam, int nWaitTime);

    public boolean CLIENT_DetachFaceFindState(LLong lAttachHandle);

    // 文件下载
    public boolean CLIENT_DownloadRemoteFile(LLong lLoginID, NET_IN_DOWNLOAD_REMOTE_FILE pInParam, NET_OUT_DOWNLOAD_REMOTE_FILE pOutParam, int nWaitTime);

    // 日志
    public boolean CLIENT_LogOpen(LOG_SET_PRINT_INFO pstLogPrintInfo);

    public boolean CLIENT_LogClose();

    public boolean CLIENT_GetTotalFileCount(LLong lFindHandle, IntByReference pTotalCount, Pointer reserved, int waittime);

    public boolean CLIENT_SetFindingJumpOption(LLong lFindHandle, NET_FINDING_JUMP_OPTION_INFO pOption, Pointer reserved, int waittime);

    public LLong CLIENT_FindFileEx(LLong lLoginID, int emType, Pointer pQueryCondition, Pointer reserved, int waittime);

    public int CLIENT_FindNextFileEx(LLong lFindHandle, int nFilecount, Pointer pMediaFileInfo, int maxlen, Pointer reserved, int waittime);

    public boolean CLIENT_FindCloseEx(LLong lFindHandle);

    // 智能分析
    public LLong CLIENT_RealLoadPictureEx(LLong lLoginID, int nChannelID, int dwAlarmType, int bNeedPicFile, Callback cbAnalyzerData, Pointer dwUser, Pointer Reserved);

    public boolean CLIENT_StopLoadPic(LLong lAnalyzerHandle);

    // 抓图
    public void CLIENT_SetSnapRevCallBack(Callback OnSnapRevMessage, Pointer dwUser);

    public boolean CLIENT_SnapPictureEx(LLong lLoginID, SNAP_PARAMS stParam, IntByReference reserved);

    // 设备搜索
    public LLong CLIENT_StartSearchDevices(Callback cbSearchDevices, Pointer pUserData, String szLocalIp);

    public boolean CLIENT_StopSearchDevices(LLong lSearchHandle);

    public boolean CLIENT_SearchDevicesByIPs(NET_DEVICE_IP_SEARCH_INFO pstDevSearchInfo, Pointer szLocalIp, Pointer reserved);

    // 远程配置
    public LLong CLIENT_StartRemoteConfig(LLong lLoginID, int dwCommand, Pointer pInBuf, int nInLen, Pointer pOutBuf, int nOutLen, Pointer cbStateCallback, Pointer dwUser);

    public boolean CLIENT_SendRemoteConfig(LLong lRemoteConfigHandle, int dwCommand, Pointer pInBuf, int nInLen);

    public boolean CLIENT_StopRemoteConfig(LLong lRemoteConfigHandle);

    // 远程升级
    public LLong CLIENT_StartUpgrade(LLong lLoginID, String pchFileName);

    public boolean CLIENT_SendUpgrade(LLong lUpgradeHandle, byte[] pSendBuf, int nBufLen);

    public boolean CLIENT_StopUpgrade(LLong lUpgradeHandle);

    public int CLIENT_Upgrade(LLong lLoginID, String pchFileName, Callback pcbProc);
}
