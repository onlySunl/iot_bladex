package org.springblade.modules.nvr.haikangisup.haikang.alarm;


import com.sun.jna.Native;
import lombok.RequiredArgsConstructor;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.modules.nvr.haikangisup.callBack.EHomeMsgCallBack;
import org.springblade.modules.nvr.haikangisup.config.HaikangIsupConfig;
import org.springblade.modules.nvr.haikangisup.haikang.cms.HCISUPCMS;
import org.springblade.modules.nvr.haikangisup.utils.IsupNativeLibLoader;
import org.springblade.modules.nvr.haikangisup.utils.OsSelect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AlarmService {

    public static HCISUPAlarm hcEHomeAlarm = null;

    public int AlarmHandle = -1;

    private final EHomeMsgCallBack cbEHomeMsgCallBack;

    private final HaikangIsupConfig haikangIsupConfig;
    @PostConstruct
    public void init() {
        log.warn(">>>>>> AlarmService Bean initialized <<<<<<");
    }


    static HCISUPAlarm.NET_EHOME_ALARM_LISTEN_PARAM net_ehome_alarm_listen_param = new HCISUPAlarm.NET_EHOME_ALARM_LISTEN_PARAM();

    private boolean CreateSDKInstance() {
        if (hcEHomeAlarm == null) {
            synchronized (HCISUPAlarm.class) {
                String strDllPath = "";
                try {
                    if (OsSelect.isWindows()) {
                        strDllPath = IsupNativeLibLoader.resolveLibPath("HCISUPAlarm.dll");
                    } else if (OsSelect.isLinux()) {
                        strDllPath = IsupNativeLibLoader.resolveLibPath("libHCISUPAlarm.so");
                    }
                    hcEHomeAlarm = (HCISUPAlarm) Native.loadLibrary(strDllPath, HCISUPAlarm.class);
                } catch (Exception ex) {
                    log.error("loadLibrary: " + strDllPath + " Error: " + ex.getMessage());
                    return false;
                }
            }
        }
        return true;
    }

    public void eAlarm_Init() {
        if (hcEHomeAlarm == null) {
            if (!CreateSDKInstance()) {
                log.error("加载Alarm SDK失败");
                return;
            }
        }
        if (OsSelect.isWindows()) {
            HCISUPCMS.BYTE_ARRAY ptrByteArrayCrypto = new HCISUPCMS.BYTE_ARRAY(256);
            String strPathCrypto = IsupNativeLibLoader.resolveLibPath("libeay32.dll");
            System.arraycopy(strPathCrypto.getBytes(), 0, ptrByteArrayCrypto.byValue, 0, strPathCrypto.length());
            ptrByteArrayCrypto.write();
            hcEHomeAlarm.NET_EALARM_SetSDKInitCfg(0, ptrByteArrayCrypto.getPointer());

            HCISUPCMS.BYTE_ARRAY ptrByteArraySsl = new HCISUPCMS.BYTE_ARRAY(256);
            String strPathSsl = IsupNativeLibLoader.resolveLibPath("ssleay32.dll");
            System.arraycopy(strPathSsl.getBytes(), 0, ptrByteArraySsl.byValue, 0, strPathSsl.length());
            ptrByteArraySsl.write();
            hcEHomeAlarm.NET_EALARM_SetSDKInitCfg(1, ptrByteArraySsl.getPointer());

            boolean bRet = hcEHomeAlarm.NET_EALARM_Init();
            if (!bRet) {
                log.error("NET_EALARM_Init 失败!");
            }

            HCISUPCMS.BYTE_ARRAY ptrByteArrayCom = new HCISUPCMS.BYTE_ARRAY(256);
            String strPathCom = IsupNativeLibLoader.resolveLibPath("HCAapSDKCom");
            System.arraycopy(strPathCom.getBytes(), 0, ptrByteArrayCom.byValue, 0, strPathCom.length());
            ptrByteArrayCom.write();
            hcEHomeAlarm.NET_EALARM_SetSDKLocalCfg(5, ptrByteArrayCom.getPointer());
        } else if (OsSelect.isLinux()) {
            HCISUPCMS.BYTE_ARRAY ptrByteArrayCrypto = new HCISUPCMS.BYTE_ARRAY(256);
            String strPathCrypto = IsupNativeLibLoader.resolveLibPath("libcrypto.so");
            System.arraycopy(strPathCrypto.getBytes(), 0, ptrByteArrayCrypto.byValue, 0, strPathCrypto.length());
            ptrByteArrayCrypto.write();
            hcEHomeAlarm.NET_EALARM_SetSDKInitCfg(0, ptrByteArrayCrypto.getPointer());

            HCISUPCMS.BYTE_ARRAY ptrByteArraySsl = new HCISUPCMS.BYTE_ARRAY(256);
            String strPathSsl = IsupNativeLibLoader.resolveLibPath("libssl.so");
            System.arraycopy(strPathSsl.getBytes(), 0, ptrByteArraySsl.byValue, 0, strPathSsl.length());
            ptrByteArraySsl.write();
            hcEHomeAlarm.NET_EALARM_SetSDKInitCfg(1, ptrByteArraySsl.getPointer());

            boolean bRet = hcEHomeAlarm.NET_EALARM_Init();
            if (!bRet) {
                log.error("NET_EALARM_Init 失败!");
            }

            HCISUPCMS.BYTE_ARRAY ptrByteArrayCom = new HCISUPCMS.BYTE_ARRAY(256);
            String strPathCom = IsupNativeLibLoader.resolveLibPath("HCAapSDKCom/");
            System.arraycopy(strPathCom.getBytes(), 0, ptrByteArrayCom.byValue, 0, strPathCom.length());
            ptrByteArrayCom.write();
            hcEHomeAlarm.NET_EALARM_SetSDKLocalCfg(5, ptrByteArrayCom.getPointer());
        }

        boolean logToFile = hcEHomeAlarm.NET_EALARM_SetLogToFile(3, System.getProperty("user.dir") + "/haikang-isup/EHomeSDKLog", false);
    }

    public void startAlarmListen() {
        // 公网环境：listenIp 为本地监听地址，ip 为公网地址（设备连接用）
        String listenIp = StringUtils.isNotBlank(haikangIsupConfig.getAlarmServer().getListenIp())
                ? haikangIsupConfig.getAlarmServer().getListenIp()
                : haikangIsupConfig.getAlarmServer().getIp();
        int listenTcpPort = haikangIsupConfig.getAlarmServer().getListenTcpPort() > 0
                ? haikangIsupConfig.getAlarmServer().getListenTcpPort()
                : haikangIsupConfig.getAlarmServer().getTcpPort();

        System.arraycopy(listenIp.getBytes(), 0, net_ehome_alarm_listen_param.struAddress.szIP, 0, listenIp.length());

        net_ehome_alarm_listen_param.struAddress.wPort = (short) listenTcpPort;
        net_ehome_alarm_listen_param.byProtocolType = 2;
        net_ehome_alarm_listen_param.fnMsgCb = cbEHomeMsgCallBack;
        net_ehome_alarm_listen_param.byUseCmsPort = 0;
        net_ehome_alarm_listen_param.write();

        AlarmHandle = hcEHomeAlarm.NET_EALARM_StartListen(net_ehome_alarm_listen_param);
        if (AlarmHandle < 0) {
            log.error("NET_EALARM_StartListen失败，错误:" + hcEHomeAlarm.NET_EALARM_GetLastError());
            hcEHomeAlarm.NET_EALARM_Fini();
        } else {
            String AlarmListenInfo = new String(net_ehome_alarm_listen_param.struAddress.szIP).trim() + "_" + net_ehome_alarm_listen_param.struAddress.wPort;
            log.info("报警服务器：" + AlarmListenInfo + ",NET_EALARM_StartListen 成功");
        }
    }

    public void stopAlarmListen() {
        if (AlarmHandle >= 0) {
            hcEHomeAlarm.NET_EALARM_StopListen(AlarmHandle);
            AlarmHandle = -1;
            log.info("NET_EALARM_StopListen 成功");
        }
    }
}
