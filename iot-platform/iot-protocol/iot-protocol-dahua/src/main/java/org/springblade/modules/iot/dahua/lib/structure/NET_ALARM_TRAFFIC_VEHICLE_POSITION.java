package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 车辆位置报警信息
 */
public class NET_ALARM_TRAFFIC_VEHICLE_POSITION extends SdkStructure {
    /**
     * 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束
     */
    public int              nAction;
    /**
     * 事件名称
     */
    public byte[]           szEventName = new byte[128];
    /**
     * 物体ID
     */
    public int              nObjectID;
    /**
     * 车牌号
     */
    public byte[]           szPlateNumber = new byte[128];
    /**
     * 相对距离
     */
    public int              nPosition;
    /**
     * 开闸状态
     */
    public byte             byOpenStrobeState;
    /**
     * 车牌置信度
     */
    public int              nPlateConfidence;
    /**
     * 车牌颜色
     */
    public byte[]           szPlateColor = new byte[32];
    /**
     * 车牌类型
     */
    public byte[]           szPlateType = new byte[32];
    /**
     * 保留字段
     */
    public byte[]           byReserved = new byte[128];
}
