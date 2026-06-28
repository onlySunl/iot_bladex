package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 就座率检测事件
*/
public class NET_VIDEOSTAT_SEATING_RATE_DETECTION extends SdkStructure
{
    /**
     * 事件名称
    */
    public byte[]           szName = new byte[128];
    /**
     * 智能事件所属大类
    */
    public byte[]           szClass = new byte[16];
    /**
     * 相对事件时间戳,单位毫秒
    */
    public double           dbPTS;
    /**
     * 事件发生时间，带时区偏差的UTC时间，单位秒,参见结构体定义 {@link NET_TIME_EX}
    */
    public NET_TIME_EX stuUTC = new NET_TIME_EX();
    /**
     * 事件编号，用来唯一标志一个事件
    */
    public int              nEventID;
    /**
     * 事件触发的预置点号，从1开始没有该字段，表示预置点未知
    */
    public int              nPresetID;
    /**
     * 总座位数
    */
    public int              nSeatCount;
    /**
     * 检测到的总人数
    */
    public int              nHumanCount;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_VIDEOSTAT_SEATING_RATE_DETECTION() {
    }
}

