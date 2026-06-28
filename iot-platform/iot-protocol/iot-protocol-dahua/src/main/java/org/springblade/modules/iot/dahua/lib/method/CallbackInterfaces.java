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
 * 回调接口定义
 */
public interface CallbackInterfaces extends NetSDKLib {

    // 断线回调
    public interface fDisConnect extends Callback {
        public void invoke(LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser);
    }

    // 网络连接恢复回调
    public interface fHaveReConnect extends Callback {
        public void invoke(LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser);
    }

    // 消息回调
    public interface fMessCallBack extends Callback {
        public boolean invoke(int lCommand, LLong lLoginID, Pointer pStuEvent, int dwBufLen, String strDeviceIP, NativeLong nDevicePort, Pointer dwUser);
    }

    // 消息回调扩展1
    public interface fMessCallBackEx1 extends Callback {
        public boolean invoke(int lCommand, LLong lLoginID, Pointer pStuEvent, int dwBufLen, String strDeviceIP, NativeLong nDevicePort, int bAlarmAckFlag, NativeLong nEventID, Pointer dwUser);
    }

    // 人脸回调
    public interface fFaceFindState extends Callback {
        public void invoke(LLong lLoginID, LLong lAttachHandle, Pointer pstStates, int nStateNum, Pointer dwUser);
    }

    // 智能分析数据回调
    public interface fAnalyzerDataCallBack extends Callback {
        public int invoke(LLong lAnalyzerHandle, int dwAlarmType, Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize, Pointer dwUser, int nSequence, Pointer reserved) throws UnsupportedEncodingException;
    }

    // 抓图回调
    public interface fSnapRev extends Callback {
        public void invoke(LLong lLoginID, Pointer pBuf, int RevLen, int EncodeType, int CmdSerial, Pointer dwUser);
    }

    // 搜索设备回调
    public interface fSearchDevicesCB extends Callback {
        public void invoke(Pointer pDevNetInfo, Pointer pUserData);
    }

    // 回放进度回调
    public interface fTimeDownLoadPosCallBack extends Callback {
        public void invoke(LLong lPlayHandle, int dwTotalSize, int dwDownLoadSize, int index, NET_RECORDFILE_INFO.ByValue recordfileinfo, Pointer dwUser);
    }

    // 数据回调
    public interface fDataCallBack extends Callback {
        public int invoke(LLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, Pointer dwUser);
    }

    // 下载进度回调
    public interface fDownLoadPosCallBack extends Callback {
        public void invoke(LLong lPlayHandle, int dwTotalSize, int dwDownLoadSize, Pointer dwUser);
    }

    // 视频统计摘要回调
    public interface fVideoStatSumCallBack extends Callback {
        public void invoke(LLong lAttachHandle, NET_VIDEOSTAT_SUMMARY pBuf, int dwBufLen, Pointer dwUser);
    }

    // 数据回调扩展
    public interface fDataCallBackEx extends Callback {
        public int invoke(LLong lRealHandle, NET_DATA_CALL_BACK_INFO pDataCallBack, Pointer dwUser);
    }

    // 音频数据回调
    public interface pfAudioDataCallBack extends Callback {
        public void invoke(LLong lTalkHandle, Pointer pDataBuf, int dwBufSize, byte byAudioFlag, Pointer dwUser);
    }

    // 文件传输回调
    public interface fTransFileCallBack extends Callback {
        public void invoke(LLong lHandle, int nTransType, int nState, int nSendSize, int nTotalSize, Pointer dwUser);
    }

    // GPS信息回调扩展
    public interface fGPSRevEx extends Callback {
        public void invoke(LLong lLoginID, GPS_Info.ByValue GpsInfo, ALARM_STATE_INFO.ByValue stAlarmInfo, Pointer dwUserData, Pointer reserved);
    }

    // GPS信息回调扩展2
    public interface fGPSRevEx2 extends Callback {
        public void invoke(LLong lLoginID, NET_GPS_LOCATION_INFO lpData, Pointer dwUserData, Pointer reserved);
    }

    // 实时预览数据回调扩展
    public interface fRealDataCallBackEx extends Callback {
        public void invoke(LLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, int param, Pointer dwUser);
    }

    // 实时预览数据回调扩展2
    public interface fRealDataCallBackEx2 extends Callback {
        void invoke(LLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, LLong param, Pointer dwUser);
    }

    // 视频预览断开回调
    public interface fRealPlayDisConnect extends Callback {
        public void invoke(LLong lOperateHandle, int dwEventType, Pointer param, Pointer dwUser);
    }

    // 过车记录回调
    public interface fParkingControlRecordCallBack extends Callback {
        public void invoke(LLong lLoginID, LLong lAttachHandle, NET_CAR_PASS_ITEM pInfo, int nBufLen, Pointer dwUser);
    }

    // 车位信息回调
    public interface fParkInfoCallBack extends Callback {
        public void invoke(LLong lLoginID, LLong lAttachHandle, NET_PARK_INFO_ITEM pInfo, int nBufLen, Pointer dwUser);
    }

    // SCADA回调
    public interface fSCADAAttachInfoCallBack extends Callback {
        public void invoke(LLong lLoginID, LLong lAttachHandle, NET_SCADA_NOTIFY_POINT_INFO_LIST pInfo, int nBufLen, Pointer dwUser);
    }

    // 透明串口回调
    public interface fTransComCallBack extends Callback {
        public void invoke(LLong lLoginID, LLong lTransComChannel, Pointer pBuffer, int dwBufSize, Pointer dwUser);
    }

    // 视频分析状态回调
    public interface fVideoAnalyseState extends Callback {
        public int invoke(LLong lAttachHandle, NET_VIDEOANALYSE_STATE pAnalyseStateInfos, Pointer dwUser, Pointer pReserved);
    }

    // 侦听服务回调
    public interface fServiceCallBack extends Callback {
        public int invoke(LLong lHandle, String pIp, int wPort, int lCommand, Pointer pParam, int dwParamLen, Pointer dwUserData);
    }

    // 雷达RFID回调
    public interface fRadarRFIDCardInfoCallBack extends Callback {
        public int invoke(LLong lLoginID, LLong lAttachHandle, NET_RADAR_NOTIFY_RFIDCARD_INFO pBuf, int dwBufLen, Pointer pReserved, Pointer dwUser);
    }

    // 监听类型
    public static class EM_LISTEN_TYPE {
        public static final int NET_DVR_DISCONNECT = -1;
        public static final int NET_DVR_SERIAL_RETURN = 1;
        public static final int NET_DEV_AUTOREGISTER_RETURN = 2;
        public static final int NET_DEV_NOTIFY_IP_RETURN = 3;
    }

    // Bus状态回调
    public interface fBusStateCallBack extends Callback {
        public void invoke(LLong lAttachHandle, int lCommand, Pointer pBuf, int dwBufLen, Pointer dwUser);
    }

    // GPS温湿度回调
    public interface fGPSTempHumidityRev extends Callback {
        public void invoke(LLong lLoginID, GPS_TEMP_HUMIDITY_INFO.ByValue GpsTHInfo, Pointer dwUserData);
    }

    // 设备状态回调
    public interface fDeviceStateCallBack extends Callback {
        public void invoke(LLong lAttachHandle, NET_CB_ATTACH_DEVICE_STATE pstDeviceState, Pointer dwUser);
    }

    // 添加设备回调
    public interface fAddDeviceCallBack extends Callback {
        public void invoke(LLong lAttachHandle, NET_CB_ATTACH_ADD_DEVICE pstAddDevice, Pointer dwUser);
    }

    // SCADA报警回调
    public interface fSCADAAlarmAttachInfoCallBack extends Callback {
        public void invoke(LLong lAttachHandle, NET_SCADA_NOTIFY_POINT_ALARM_INFO_LIST pInfo, int nBufLen, Pointer dwUser);
    }

    // 视频诊断回调
    public interface fRealVideoDiagnosis extends Callback {
        public int invoke(LLong lDiagnosisHandle, NET_REAL_DIAGNOSIS_RESULT pDiagnosisInfo, Pointer pBuf, int nBufLen, Pointer dwUser);
    }

    // 温度分布回调
    public interface fRadiometryAttachCB extends Callback {
        public void invoke(LLong lAttachHandle, NET_RADIOMETRY_DATA pBuf, int nBufLen, Pointer dwUser);
    }

    // 刻录状态回调
    public interface fAttachBurnStateCB extends Callback {
        public void invoke(LLong lLoginID, LLong lAttachHandle, NET_CB_BURNSTATE pBuf, int nBufLen, Pointer dwUser);
    }

    // 刻录状态回调扩展
    public interface fAttachBurnStateCBEx extends Callback {
        public void invoke(LLong lLoginID, LLong lAttachHandle, NET_OUT_BURN_GET_STATE pBuf, int nBufLen, Pointer dwUser);
    }

    // 刻录文件回调
    public interface fBurnFileCallBack extends Callback {
        public void invoke(LLong lLoginID, LLong lUploadHandle, int nTotalSize, int nSendSize, Pointer dwUser);
    }

    // 升级回调
    public interface fUpgradeCallBackEx extends Callback {
        public void invoke(LLong lLoginID, LLong lUpgradechannel, int nTotalSize, int nSendSize, Pointer dwUserData);
    }
}
