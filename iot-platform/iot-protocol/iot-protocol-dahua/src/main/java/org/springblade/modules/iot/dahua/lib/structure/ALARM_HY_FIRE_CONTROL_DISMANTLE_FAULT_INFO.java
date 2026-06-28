package org.springblade.modules.iot.dahua.lib.structure;

/**
 * 消防设备拆除事件
 */
public class ALARM_HY_FIRE_CONTROL_DISMANTLE_FAULT_INFO extends SdkStructure {
    /**
     * 通道号,从0开始
     */
    public int nChannelID;
    /**
     * 事件动作, 0表示脉冲事件, 1表示开始, 2表示结束, -1表示未知
     */
    public int nEventAction;
    /**
     * 事件名称
     */
    public byte[] szName = new byte[128];
    /**
     * 时间戳(单位是毫秒)
     */
    public double dbPTS;
    /**
     * 事件发生的时间
     */
    public NET_TIME_EX stuUTC = new NET_TIME_EX();
    /**
     * 事件ID
     */
    public int nEventID;
    /**
     * 主机地址
     */
    public byte[] szAddress = new byte[256];
    /**
     * 主机序列号
     */
    public byte[] szSN = new byte[64];
    /**
     * 设备类型
     */
    public int emDeviceType;
    /**
     * 设备接收到事件的时间
     */
    public NET_TIME_EX stuTime = new NET_TIME_EX();
    /**
     * 事件公共扩展字段结构体
     */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 保留字节
     */
    public byte[] byReserved = new byte[1024];

    public ALARM_HY_FIRE_CONTROL_DISMANTLE_FAULT_INFO() {
    }
}
