package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_TRAFFIC_TRUST_CAR(信任车辆事件)对应的数据块描述信息
*/
public class NET_DEV_EVENT_TRAFFIC_TRUST_CAR_INFO extends SdkStructure
{
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 0:脉冲,1:开始, 2:停止
    */
    public int              nAction;
    /**
     * 扩展协议字段,参见结构体定义 {@link NET_EVENT_INFO_EXTEND}
    */
    public NET_EVENT_INFO_EXTEND stuEventInfoEx = new NET_EVENT_INFO_EXTEND();
    /**
     * 事件发生的时间,参见结构体定义 {@link NET_TIME_EX}
    */
    public NET_TIME_EX stuUTC = new NET_TIME_EX();
    /**
     * 视频分析的数据源地址
    */
    public int              nSource;
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 检测到的车辆信息,参见结构体定义 {@link NET_MSG_OBJECT}
    */
    public NET_MSG_OBJECT stuVehicle = new NET_MSG_OBJECT();
    /**
     * 检测到的车牌信息,参见结构体定义 {@link NET_MSG_OBJECT}
    */
    public NET_MSG_OBJECT stuObject = new NET_MSG_OBJECT();
    /**
     * 登记的TrafficRedList信息,参见结构体定义 {@link NET_TRAFFIC_LIST_RECORD}
    */
    public NET_TRAFFIC_LIST_RECORD stuCarInfo = new NET_TRAFFIC_LIST_RECORD();
    /**
     * 交通事件公共信息,参见结构体定义 {@link EVENT_COMM_INFO}
    */
    public EVENT_COMM_INFO stuCommInfo = new EVENT_COMM_INFO();
    /**
     * 视频分析帧序号
    */
    public int              nFrameSequence;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_DEV_EVENT_TRAFFIC_TRUST_CAR_INFO() {
    }
}

