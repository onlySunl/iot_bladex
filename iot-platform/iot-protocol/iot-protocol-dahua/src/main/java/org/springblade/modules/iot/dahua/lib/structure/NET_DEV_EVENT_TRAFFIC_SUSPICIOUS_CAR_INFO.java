package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_TRAFFIC_SUSPICIOUS_CAR(嫌疑车辆事件)对应的数据块描述信息
*/
public class NET_DEV_EVENT_TRAFFIC_SUSPICIOUS_CAR_INFO extends SdkStructure
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
     * 对齐字节
    */
    public byte[]           szReservedUTC = new byte[4];
    /**
     * 检测到的车辆信息,参见结构体定义 {@link NET_MSG_OBJECT}
    */
    public NET_MSG_OBJECT stuVehicle = new NET_MSG_OBJECT();
    /**
     * 禁止名单信息,参见结构体定义 {@link NET_TRAFFIC_LIST_RECORD}
    */
    public NET_TRAFFIC_LIST_RECORD stuCarInfo = new NET_TRAFFIC_LIST_RECORD();
    /**
     * 交通事件公共信息,参见结构体定义 {@link EVENT_COMM_INFO}
    */
    public EVENT_COMM_INFO stuCommInfo = new EVENT_COMM_INFO();
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_DEV_EVENT_TRAFFIC_SUSPICIOUS_CAR_INFO() {
    }
}

