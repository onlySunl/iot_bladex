package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.*;
import com.sun.jna.ptr.*;
import com.sun.jna.win32.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.*;
import com.sun.jna.platform.win32.Kernel32Lib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.constant.NetSDKConstants;
import org.springblade.modules.iot.dahua.lib.Utils;
import org.springblade.modules.iot.dahua.lib.LastError;

 */
public interface ConfigMethods {


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
}
