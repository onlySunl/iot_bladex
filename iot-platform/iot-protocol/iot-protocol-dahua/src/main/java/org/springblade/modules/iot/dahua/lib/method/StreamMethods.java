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
 * 流媒体相关方法
 */
public interface StreamMethods extends NetSDKLib {

    // 视频诊断
    public boolean CLIENT_StartVideoDiagnosis(LLong lLoginID, NET_IN_VIDEODIAGNOSIS pstInParam, NET_OUT_VIDEODIAGNOSIS pstOutParam);

    public boolean CLIENT_StopVideoDiagnosis(LLong hDiagnosisHandle);

    public boolean CLIENT_StartFindDiagnosisResult(LLong lLoginID, NET_IN_FIND_DIAGNOSIS pstInParam, NET_OUT_FIND_DIAGNOSIS pstOutParam);

    public boolean CLIENT_DoFindDiagnosisResult(LLong hFindHandle, NET_IN_DIAGNOSIS_INFO pstInParam, NET_OUT_DIAGNOSIS_INFO pstOutParam);

    public boolean CLIENT_StopFindDiagnosis(LLong hFindHandle);

    public boolean CLIENT_GetVideoDiagnosisState(LLong lLoginID, NET_IN_GET_VIDEODIAGNOSIS_STATE pstInParam, NET_OUT_GET_VIDEODIAGNOSIS_STATE pstOutParam, int nWaitTime);

    // 热成像
    public LLong CLIENT_RadiometryAttach(LLong lLoginID, NET_IN_RADIOMETRY_ATTACH pInParam, NET_OUT_RADIOMETRY_ATTACH pOutParam, int nWaitTime);

    public boolean CLIENT_RadiometryDetach(LLong lAttachHandle);

    public boolean CLIENT_RadiometryFetch(LLong lLoginID, NET_IN_RADIOMETRY_FETCH pInParam, NET_OUT_RADIOMETRY_FETCH pOutParam, int nWaitTime);

    public boolean CLIENT_RadiometryDataParse(NET_RADIOMETRY_DATA pRadiometryData, short[] pGrayImg, float[] pTempForPixels);

    // 查询
    public boolean CLIENT_StartFind(LLong lLoginID, int emType, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);

    public boolean CLIENT_DoFind(LLong lLoginID, int emType, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);

    public boolean CLIENT_StopFind(LLong lLoginID, int emType, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);

    // 智能锁
    public boolean CLIENT_UpdateSmartLockUser(LLong lLoginID, NET_IN_SMARTLOCK_UPDATE_USER_INFO pstInParam, NET_OUT_SMARTLOCK_UPDATE_USER_INFO pstOutParam, int nWaitTime);

    public boolean CLIENT_GetSmartLockRegisterInfo(LLong lLoginID, NET_IN_GET_SMART_LOCK_REGISTER_INFO pstInParam, NET_OUT_GET_SMART_LOCK_REGISTER_INFO pstOutParam, int nWaitTime);

    public boolean CLIENT_SetSmartLockUsername(LLong lLoginID, NET_IN_SET_SMART_LOCK_USERNAME pstInParam, NET_OUT_SET_SMART_LOCK_USERNAME pstOutParam, int nWaitTime);

    public boolean CLIENT_RemoveSmartLockUser(LLong lLoginID, NET_IN_SMARTLOCK_REMOVE_USER_INFO pstInParam, NET_OUT_SMARTLOCK_REMOVE_USER_INFO pstOutParam, int nWaitTime);

    // 加密
    public boolean CLIENT_EncryptString(NET_IN_ENCRYPT_STRING pInParam, NET_OUT_ENCRYPT_STRING pOutParam, int nWaitTime);

    // 门禁
    public boolean CLIENT_OperateAccessUserService(LLong lLoginID, int emtype, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public LLong CLIENT_StartFindUserInfo(LLong lLoginID, NET_IN_USERINFO_START_FIND pstIn, NET_OUT_USERINFO_START_FIND pstOut, int nWaitTime);

    public boolean CLIENT_DoFindUserInfo(LLong lFindHandle, NET_IN_USERINFO_DO_FIND pstIn, NET_OUT_USERINFO_DO_FIND pstOut, int nWaitTime);

    public boolean CLIENT_StopFindUserInfo(LLong lFindHandle);

    public boolean CLIENT_OperateAccessCardService(LLong lLoginID, int emtype, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public LLong CLIENT_StartFindCardInfo(LLong lLoginID, NET_IN_CARDINFO_START_FIND pstIn, NET_OUT_CARDINFO_START_FIND pstOut, int nWaitTime);

    public boolean CLIENT_DoFindCardInfo(LLong lFindHandle, NET_IN_CARDINFO_DO_FIND pstIn, NET_OUT_CARDINFO_DO_FIND pstOut, int nWaitTime);

    public boolean CLIENT_StopFindCardInfo(LLong lFindHandle);

    public boolean CLIENT_OperateAccessFaceService(LLong lLoginID, int emtype, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public boolean CLIENT_OperateAccessFingerprintService(LLong lLoginID, int emtype, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    // 升级
    public LLong CLIENT_StartUpgradeEx(LLong lLoginID, int emtype, String pchFileName, Callback cbUpgrade, Pointer dwUser);

    public boolean CLIENT_SendUpgrade(LLong lUpgradeID);

    public boolean CLIENT_StopUpgrade(LLong lUpgradeID);

    // 产品定义
    public boolean CLIENT_QueryProductionDefinition(LLong lLoginID, NET_PRODUCTION_DEFNITION pstuProdDef, int nWaitTime);

    // 录播
    public boolean CLIENT_AddCourse(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public boolean CLIENT_ModifyCourse(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public boolean CLIENT_DeleteCourse(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public boolean CLIENT_QueryCourseOpen(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public boolean CLIENT_QueryCourse(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    public boolean CLIENT_QueryCourseClose(LLong lLoginID, Pointer pstInParam, Pointer pstOutParam, int nWaitTime);

    // 通道
    public boolean CLIENT_GetRealPreviewChannel(LLong lLoginID, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);

    public boolean CLIENT_OperateCourseCompositeChannelMode(LLong lLoginID, int emOperateType, Pointer pInParam, Pointer pOutParam, int nWaitTime);

    public boolean CLIENT_GetDefaultRealChannel(LLong lLoginID, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);
}
