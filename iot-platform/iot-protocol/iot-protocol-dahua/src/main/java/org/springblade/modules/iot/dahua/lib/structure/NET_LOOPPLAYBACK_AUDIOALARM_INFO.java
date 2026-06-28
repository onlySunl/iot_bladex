package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
音频循环播放报警配置
*/
public class NET_LOOPPLAYBACK_AUDIOALARM_INFO extends SdkStructure {
/** 
结构体大小
*/
    public			int            dwSize;
/** 
使能,开启：TRUE 关闭：FALSE
*/
    public			int            bEnable;
/** 
布撤防时间段
*/
    public			NET_TSECT[] stuTimeSection = new NET_TSECT[8*6];
/** 
语音播报文件路径
*/
    public			byte[]         szAudioFilePath = new byte[256];
/** 
开始时间
*/
    public			byte[]         szStartTime = new byte[20];
/** 
结束时间
*/
    public			byte[]         szEndTime = new byte[20];

    public NET_LOOPPLAYBACK_AUDIOALARM_INFO(){
                for(int i=0;i<stuTimeSection.length;i++){
                    stuTimeSection[i]=new NET_TSECT();
                }
        this.dwSize = this.size();
    }
}

