package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.enumeration.NET_RADIOMETRY_ALARMCONTION;
import org.springblade.modules.iot.dahua.lib.enumeration.NET_RADIOMETRY_RESULT;

/**
 * 热成像测温点温度异常报警事件
 */
public class ALARM_HEATIMG_TEMPER_INFO extends SdkStructure {
    /**
     * 温度异常点名称 从测温规则配置项中选择
     */
    public byte[] szName = new byte[64];
    /**
     * 报警项编号
     */
    public int nAlarmId;
    /**
     * 报警结果值 fTemperatureValue 的类型
     */
    public int nResult;
    /**
     * 报警条件
     */
    public int nAlarmContion;
    /**
     * 报警温度值
     */
    public float fTemperatureValue;
    /**
     * 温度单位
     */
    public int nTemperatureUnit;
    /**
     * 报警点的坐标 相对坐标体系,取值均为 0~8191
     */
    public NET_POINT stCoordinate = new NET_POINT();
    /**
     * 预置点
     */
    public int nPresetID;
    /**
     * 通道号
     */
    public int nChannel;
    /**
     * 0:开始 1:停止 -1:无意义
     */
    public int nAction;
    /**
     * 报警坐标, 其类型可以是点，线或多边形
     */
    public NET_POLY_POINTS stuAlarmCoordinates = new NET_POLY_POINTS();
    /**
     * 报警最高的温度值
     */
    public double dTemperatureMaxValue;
    /**
     * 报警最低的温度值
     */
    public double dTemperatureMinValue;
    /**
     * 事件公共扩展字段结构体
     */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 保留字节
     */
    public byte[] reserved = new byte[140];
}
