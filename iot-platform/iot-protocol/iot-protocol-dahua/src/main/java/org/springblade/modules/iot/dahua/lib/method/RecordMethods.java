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
 * 录像相关方法
 */
public interface RecordMethods extends NetSDKLib {

    // 查询设备当前时间
    public boolean CLIENT_QueryDeviceTime(LLong lLoginID, NET_TIME pDeviceTime, int waittime);

    // 设置设备当前时间
    public boolean CLIENT_SetupDeviceTime(LLong lLoginID, NET_TIME pDeviceTime);

    // 获得亮度、色度、对比度、饱和度的参数
    public boolean CLIENT_ClientGetVideoEffect(LLong lPlayHandle, byte[] nBrightness, byte[] nContrast, byte[] nHue, byte[] nSaturation);

    // 设置亮度、色度、对比度、饱和度的参数
    public boolean CLIENT_ClientSetVideoEffect(LLong lPlayHandle, byte nBrightness, byte nContrast, byte nHue, byte nSaturation);

    // 查询用户信息
    public boolean CLIENT_QueryUserInfoEx(LLong lLoginID, USER_MANAGE_INFO_EX info, int waittime);

    // 查询用户信息--最大支持64通道设备
    public boolean CLIENT_QueryUserInfoNew(LLong lLoginID, USER_MANAGE_INFO_NEW info, Pointer pReserved, int nWaittime);

    // 设置用户信息
    public boolean CLIENT_OperateUserInfoNew(LLong lLoginID, int nOperateType, Pointer opParam, Pointer subParam, Pointer pReserved, int nWaittime);

    // 查询设备日志
    public boolean CLIENT_QueryDeviceLog(LLong lLoginID, Pointer pQueryParam, Pointer pLogBuffer, int nLogBufferLen, IntByReference pRecLogNum, int waittime);

    // 向设备发起语音对讲请求
    public LLong CLIENT_StartTalkEx(LLong lLoginID, Callback pfcb, Pointer dwUser);

    // 停止语音对讲
    public boolean CLIENT_StopTalkEx(LLong lTalkHandle);

    // 启动本地录音功能
    public boolean CLIENT_RecordStartEx(LLong lLoginID);

    // 开始PC端录音
    public boolean CLIENT_RecordStart();

    // 结束PC端录音
    public boolean CLIENT_RecordStop();

    // 停止本地录音
    public boolean CLIENT_RecordStopEx(LLong lLoginID);

    // 向设备发送用户的音频数据
    public LLong CLIENT_TalkSendData(LLong lTalkHandle, Pointer pSendBuf, int dwBufSize);

    // 解码音频数据
    public void CLIENT_AudioDec(Pointer pAudioDataBuf, int dwBufSize);

    public boolean CLIENT_AudioDecEx(LLong lTalkHandle, Pointer pAudioDataBuf, int dwBufSize);

    // 音频格式信息
    public class NET_AUDIO_FORMAT extends SdkStructure {
        public byte byFormatTag;
        public short nChannels;
        public short wBitsPerSample;
        public int nSamplesPerSec;
    }

    // 音频编码--初始化
    public int CLIENT_InitAudioEncode(NET_AUDIO_FORMAT aft);

    // 音频编码--数据编码
    public int CLIENT_AudioEncode(LLong lTalkHandle, Pointer lpInBuf, IntByReference lpInLen, Pointer lpOutBuf, IntByReference lpOutLen);

    // 音频编码--完成退出
    public int CLIENT_ReleaseAudioEncode();

    // 打开语音对讲
    public LLong CLIENT_StartTalkByDataType(LLong lLoginID, Pointer pInParam, Pointer pOutParam, int nWaittime);

    // 发送语音数据到设备
    public LLong CLIENT_TalkSendDataByStream(LLong lTalkHandle, Pointer pInParam, Pointer pOutParam);

    // 发送语音文件中的音频数据到设备
    public LLong CLIENT_TalkSendDataByFile(LLong lTalkHandle, Pointer pInParam, Pointer pOutParam);

    // 停止发送音频文件
    public boolean CLIENT_StopTalkSendDataByFile(LLong lTalkHandle);

    // 音频数据回调
    public interface fAudioDataCallBackEx extends Callback {
        public void invoke(LLong lTalkHandle, NET_AUDIO_DATA_CB_INFO stAudioInfo, int emAudioFlag, Pointer dwUser);
    }

    // 语音文件发送进度回调
    public interface fTalkSendPosCallBack extends Callback {
        public void invoke(LLong lTalkHandle, int dwTotalSize, int dwSendSize, Pointer dwUser);
    }

    // 查询记录
    public boolean CLIENT_FindRecord(LLong lLoginID, NET_IN_FIND_RECORD_PARAM pInParam, NET_OUT_FIND_RECORD_PARAM pOutParam, int waittime);

    // 查找下一条记录
    public boolean CLIENT_FindNextRecord(NET_IN_FIND_NEXT_RECORD_PARAM pInParam, NET_OUT_FIND_NEXT_RECORD_PARAM pOutParam, int waittime);

    // 结束记录查找
    public boolean CLIENT_FindRecordClose(LLong lFindHandle);

    // 查询记录条数
    public boolean CLIENT_QueryRecordCount(NET_IN_QUEYT_RECORD_COUNT_PARAM pInParam, NET_OUT_QUEYT_RECORD_COUNT_PARAM pOutParam, int waittime);

    // 禁止/允许名单操作
    public boolean CLIENT_OperateTrafficList(LLong lLoginID, NET_IN_OPERATE_TRAFFIC_LIST_RECORD pstInParam, NET_OUT_OPERATE_TRAFFIC_LIST_RECORD pstOutParam, int waittime);

    // 文件传输
    public LLong CLIENT_FileTransmit(LLong lLoginID, int nTransType, Pointer szInBuf, int nInBufLen, Callback cbTransFile, Pointer dwUserData, int waittime);

    // 查询设备信息
    public boolean CLIENT_QueryDevInfo(LLong lLoginID, int nQueryType, Pointer pInBuf, Pointer pOutBuf, Pointer pReserved, int nWaitTime);

    // GPS订阅
    public void CLIENT_SetSubcribeGPSCallBackEX(Callback OnGPSMessage, Pointer dwUser);

    public void CLIENT_SetSubcribeGPSCallBackEX2(Callback OnGPSMessage, Pointer dwUser);

    public boolean CLIENT_SubcribeGPS(LLong lLoginID, int bStart, int KeepTime, int InterTime);

    // 文件操作
    public boolean CLIENT_PreUploadRemoteFile(LLong lLoginID, NET_IN_PRE_UPLOAD_REMOTE_FILE pInParam, NET_OUT_PRE_UPLOAD_REMOTE_FILE pOutParam, int nWaitTime);

    public boolean CLIENT_UploadRemoteFile(LLong lLoginID, NET_IN_UPLOAD_REMOTE_FILE pInParam, NET_OUT_UPLOAD_REMOTE_FILE pOutParam, int nWaitTime);

    public boolean CLIENT_ListRemoteFile(LLong lLoginID, NET_IN_LIST_REMOTE_FILE pInParam, NET_OUT_LIST_REMOTE_FILE pOutParam, int nWaitTime);

    public boolean CLIENT_RemoveRemoteFiles(LLong lLoginID, NET_IN_REMOVE_REMOTE_FILES pInParam, NET_OUT_REMOVE_REMOTE_FILES pOutParam, int nWaitTime);

    // 过车记录订阅
    public LLong CLIENT_ParkingControlAttachRecord(LLong lLoginID, NET_IN_PARKING_CONTROL_PARAM pInParam, NET_OUT_PARKING_CONTROL_PARAM pOutParam, int nWaitTime);

    public boolean CLIENT_ParkingControlDetachRecord(LLong lAttachHandle);
}
