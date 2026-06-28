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
 * 配置相关方法
 */
public interface ConfigMethods extends NetSDKLib {

    // 打开声音
    public boolean CLIENT_OpenSound(LLong hPlayHandle);

    // 关闭声音
    public boolean CLIENT_CloseSound();

    // 矩阵设置
    public boolean CLIENT_MatrixSetCameras(LLong lLoginID, NET_IN_MATRIX_SET_CAMERAS pInParam, NET_OUT_MATRIX_SET_CAMERAS pOutParam, int nWaitTime);

    public boolean CLIENT_MatrixGetCameras(LLong lLoginID, NET_IN_MATRIX_GET_CAMERAS pInParam, NET_OUT_MATRIX_GET_CAMERAS pOutParam, int nWaitTime);

    // 抓图
    public boolean CLIENT_SnapPictureToFile(LLong lLoginID, Pointer pInParam, Pointer pOutParam, int nWaitTime);

    // 查询录像文件
    public boolean CLIENT_QueryRecordFile(LLong lLoginID, int nChannelId, int nRecordFileType, NET_TIME tmStart, NET_TIME tmEnd, String pchCardid, NET_RECORDFILE_INFO[] stFileInfo, int maxlen, IntByReference filecount, int waittime, boolean bTime);

    public boolean CLIENT_QueryRecordFile(LLong lLoginID, int nChannelId, int nRecordFileType, NET_TIME tmStart, NET_TIME tmEnd, String pchCardid, Pointer pFileInfo, int maxlen, IntByReference filecount, int waittime, boolean bTime);

    public boolean CLIENT_QueryRecordTime(LLong lLoginID, int nChannelId, int nRecordFileType, NET_TIME tmStart, NET_TIME tmEnd, String pchCardid, IntByReference bResult, int waittime);

    // 下载录像
    public LLong CLIENT_DownloadByTimeEx(LLong lLoginID, int nChannelId, int nRecordFileType, NET_TIME tmStart, NET_TIME tmEnd, String sSavedFileName, Callback cbTimeDownLoadPos, Pointer dwUserData, Callback fDownLoadDataCallBack, Pointer dwDataUser, Pointer pReserved);

    public boolean CLIENT_StopDownload(LLong lFileHandle);

    public LLong CLIENT_DownloadByTimeEx2(LLong lLoginID, int nChannelId, int nRecordFileType, NET_TIME tmStart, NET_TIME tmEnd, String sSavedFileName, Callback cbTimeDownLoadPos, Pointer dwUserData, Callback fDownLoadDataCallBack, Pointer dwDataUser, int scType, Pointer pReserved);

    public LLong CLIENT_DownloadByRecordFileEx(LLong lLoginID, LPNET_RECORDFILE_INFO lpRecordFile, Pointer sSavedFileName, Callback cbDownLoadPos, Pointer dwUserData, Callback fDownLoadDataCallBack, Pointer dwDataUser, Pointer pReserved);

    public LLong CLIENT_DownloadByRecordFileEx(LLong lLoginID, NET_RECORDFILE_INFO lpRecordFile, Pointer sSavedFileName, Callback cbDownLoadPos, Pointer dwUserData, Callback fDownLoadDataCallBack, Pointer dwDataUser, Pointer pReserved);

    public LLong CLIENT_DownloadByFileSelfAdapt(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int dwWaitTime);

    public LLong CLIENT_AdaptiveDownloadByTime(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int dwWaitTime);

    // 云台控制
    public boolean CLIENT_DHPTZControlEx(LLong lLoginID, int nChannelID, int dwPTZCommand, int lParam1, int lParam2, int lParam3, int dwStop);

    public boolean CLIENT_DHPTZControlEx2(LLong lLoginID, int nChannelID, int dwPTZCommand, int lParam1, int lParam2, int lParam3, int dwStop, Pointer param4);

    // 设备控制
    public boolean CLIENT_ControlDevice(LLong lLoginID, int emType, Pointer param, int waittime);

    public boolean CLIENT_ControlDeviceEx(LLong lLoginID, int emType, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);

    // 配置查询
    public boolean CLIENT_GetDevConfig(LLong lLoginID, int dwCommand, int lChannel, Pointer lpOutBuffer, int dwOutBufferSize, IntByReference lpBytesReturned, int waittime);

    public boolean CLIENT_SetDevConfig(LLong lLoginID, int dwCommand, int lChannel, Pointer lpInBuffer, int dwInBufferSize, int waittime);

    public boolean CLIENT_QueryDevState(LLong lLoginID, int nType, Pointer pBuf, int nBufLen, IntByReference pRetLen, int waittime);

    public boolean CLIENT_QueryRemotDevState(LLong lLoginID, int nType, int nChannelID, Pointer pBuf, int nBufLen, IntByReference pRetLen, int waittime);

    public boolean CLIENT_GetDevCaps(LLong lLoginID, int nType, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);

    public boolean CLIENT_QueryNewSystemInfo(LLong lLoginID, String szCommand, int nChannelID, byte[] szOutBuffer, int dwOutBufferSize, IntByReference error, int waittime);

    public boolean CLIENT_QueryNewSystemInfoEx(LLong lLoginID, String szCommand, int nChannelID, byte[] szOutBuffer, int dwOutBufferSize, IntByReference error, Pointer pExtendInfo, int waittime);

    public boolean CLIENT_QuerySystemInfo(LLong lLoginID, int nSystemType, String pSysInfoBuffer, int nChannelID, byte[] szOutBuffer, int dwOutBufferSize, IntByReference error, int waittime);

    // 视频统计
    public LLong CLIENT_AttachVideoStatSummary(LLong lLoginID, NET_IN_ATTACH_VIDEOSTAT_SUM pInParam, NET_OUT_ATTACH_VIDEOSTAT_SUM pOutParam, int nWaitTime);

    public boolean CLIENT_DetachVideoStatSummary(LLong lAttachHandle);

    public LLong CLIENT_StartFindNumberStat(LLong lLoginID, NET_IN_FINDNUMBERSTAT pstInParam, NET_OUT_FINDNUMBERSTAT pstOutParam);

    public int CLIENT_DoFindNumberStat(LLong lFindHandle, NET_IN_DOFINDNUMBERSTAT pstInParam, NET_OUT_DOFINDNUMBERSTAT pstOutParam);

    public boolean CLIENT_StopFindNumberStat(LLong lFindHandle);

    public boolean CLIENT_SetDeviceMode(LLong lLoginID, int emType, Pointer pValue);

    // 回放
    public LLong CLIENT_PlayBackByTime(LLong lLoginID, int nChannelID, NET_TIME lpStartTime, NET_TIME lpStopTime, Pointer hWnd, fDownLoadPosCallBack cbDownLoadPos, Pointer dwPosUser);

    public LLong CLIENT_PlayBackByTimeEx(LLong lLoginID, int nChannelID, NET_TIME lpStartTime, NET_TIME lpStopTime, Pointer hWnd, Callback cbDownLoadPos, Pointer dwPosUser, Callback fDownLoadDataCallBack, Pointer dwDataUser);

    public LLong CLIENT_PlayBackByTimeEx2(LLong lLoginID, int nChannelID, NET_IN_PLAY_BACK_BY_TIME_INFO pstNetIn, NET_OUT_PLAY_BACK_BY_TIME_INFO pstNetOut);

    public boolean CLIENT_StopPlayBack(LLong lPlayHandle);

    public boolean CLIENT_GetPlayBackOsdTime(LLong lPlayHandle, NET_TIME lpOsdTime, NET_TIME lpStartTime, NET_TIME lpEndTime);

    public boolean CLIENT_PausePlayBack(LLong lPlayHandle, int bPause);

    public boolean CLIENT_FastPlayBack(LLong lPlayHandle);

    public boolean CLIENT_SlowPlayBack(LLong lPlayHandle);

    public boolean CLIENT_NormalPlayBack(LLong lPlayHandle);

    public boolean CLIENT_SetPlayBackSpeed(LLong lPlayHandle, int emSpeed);
}
