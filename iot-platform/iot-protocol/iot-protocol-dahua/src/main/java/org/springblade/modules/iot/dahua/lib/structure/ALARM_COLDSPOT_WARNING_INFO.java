package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 冷点报警事件
 */
public class ALARM_COLDSPOT_WARNING_INFO extends SdkStructure {
    /**
     * 0:开始 1:停止
     */
    public int nAction;
    /**
     * 视频通道号
     */
    public int nChannelID;
    /**
     * 冷点的坐标,坐标值 0~8192
     */
    public NET_POINT stuCoordinate = new NET_POINT();
    /**
     * 冷点温度值
     */
    public float fColdSpotValue;
    /**
     * 温度单位
     */
    public int nTemperatureUnit;
    /**
     * 事件公共扩展字段结构体
     */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
}
