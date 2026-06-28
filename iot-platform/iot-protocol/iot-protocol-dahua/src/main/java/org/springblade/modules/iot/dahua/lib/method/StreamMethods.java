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
public interface StreamMethods extends NetSDKLib {

import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.structure.*;
import org.springblade.modules.iot.dahua.lib.enumeration.*;
import org.springblade.modules.iot.dahua.lib.constant.NetSDKConstants;
import org.springblade.modules.iot.dahua.lib.Utils;
import org.springblade.modules.iot.dahua.lib.LastError;


public interface StreamMethods {
    /**
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


     * 获取录播主机默认真实通道号,pInBuf与pOutBuf内存由用户申请释放
     * NET_IN_GET_DEFAULT_REAL_CHANNEL* pInBuf, NET_OUT_GET_DEFAULT_REAL_CHANNEL* pOutBuf
     */
    public boolean CLIENT_GetDefaultRealChannel(LLong lLoginID,Pointer pInBuf,Pointer pOutBuf,int nWaitTime);

}
