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
public interface InitLoginMethods extends NetSDKLib {

import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.constant.NetSDKConstants;
import org.springblade.modules.iot.dahua.lib.Utils;
import org.springblade.modules.iot.dahua.lib.LastError;

public interface InitLoginMethods {

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
}
