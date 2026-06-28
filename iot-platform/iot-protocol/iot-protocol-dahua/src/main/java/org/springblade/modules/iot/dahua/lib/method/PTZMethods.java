package org.springblade.modules.iot.dahua.lib.method;

import com.sun.jna.*;
import com.sun.jna.ptr.*;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * NetSDK 方法定义 - PTZMethods.java
 */
public interface PTZMethods {


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
     */
}
