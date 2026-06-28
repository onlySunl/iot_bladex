package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 车辆进出信息
*/
public class NET_CAR_PASSING_INFO extends SdkStructure
{
    /**
     * 车辆物体ID
    */
    public int              nCarId;
    /**
     * 车道号
    */
    public int              nLane;
    /**
     * 车牌号码
    */
    public byte[]           szPlateNumber = new byte[32];
    /**
     * 车牌颜色
    */
    public byte[]           szPlateColor = new byte[32];
    /**
     * 车速，单位:km/h，255表示无测速
    */
    public int              nSpeed;
    /**
     * 大小车类型, 0大车 1小车
    */
    public int              nCarSize;
    /**
     * 物体进入还是离开,参见枚举定义 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_CAR_PASSING_MOVE_STATE}
    */
    public int              emMoveState;
    /**
     * 车辆进出时间，设备本地时间,参见结构体定义 {@link NET_TIME_EX}
    */
    public NET_TIME_EX stuTime = new NET_TIME_EX();
    /**
     * 自定义车道号
    */
    public int              nRoadwayNumber;
    /**
     * 线圈编号，范围：0-65535
    */
    public int              nCoilID;
    /**
     * 车辆与所有车道的中心点(中心点在设备所处位置，且垂直于车道方向的直线上)，X轴方向(垂直于车道方向)的距离
    */
    public double           dbCarX;
    /**
     * 车辆与所有车道的中心点(中心点在设备所处位置，且垂直于车道方向的直线上)，Y轴方向(平行于车道方向)的距离
    */
    public double           dbCarY;
    /**
     * 车辆与所有车道的中心点(中心点在设备所处位置且垂直于车道方向的直线上)的角度
    */
    public double           dbCarAngle;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[128];

    public NET_CAR_PASSING_INFO() {
    }
}

