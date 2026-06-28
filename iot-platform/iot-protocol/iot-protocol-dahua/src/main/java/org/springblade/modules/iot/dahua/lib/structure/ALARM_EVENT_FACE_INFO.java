package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 目标检测事件(对应事件 DH_EVENT_FACE_DETECTION)
 */
public class ALARM_EVENT_FACE_INFO extends SdkStructure {
    public int dwSize;
    /**
     * 通道号
     */
    public int nChannelID;
    /**
     * 时间戳(单位是毫秒)
     */
    public double PTS;
    /**
     * 事件发生的时间
     */
    public NET_TIME_EX UTC = new NET_TIME_EX();
    /**
     * 事件ID
     */
    public int nEventID;
    /**
     * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
     */
    public int nEventAction;
    /**
     * 目标个数
     */
    public int nFaceCount;
    /**
     * 目标信息
     */
    public NET_EVENT_WHOLE_FACE_INFO[] stuFaces = new NET_EVENT_WHOLE_FACE_INFO[10];
    /**
     * 事件触发的预置点号, 从1开始
     */
    public int nPresetID;
    /**
     * 事件公共扩展字段结构体
     */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();

    public ALARM_EVENT_FACE_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuFaces.length; i++){
            stuFaces[i] = new NET_EVENT_WHOLE_FACE_INFO();
        }
    }
}
