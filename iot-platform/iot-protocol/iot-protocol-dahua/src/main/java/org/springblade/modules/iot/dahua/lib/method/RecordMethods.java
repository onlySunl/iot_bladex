package org.springblade.modules.iot.dahua.lib.method;

import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.structure.*;
import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NetSDK 方法定义
 */
public interface RecordMethods extends NetSDKLib {

import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.constant.NetSDKConstants;
import org.springblade.modules.iot.dahua.lib.Utils;
import org.springblade.modules.iot.dahua.lib.LastError;

 */
public interface RecordMethods {


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
}
