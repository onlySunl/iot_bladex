 package org.springblade.modules.iot.dahua.lib.structure;

 /**LED屏幕配置  */
public class NET_CFG_VSP_LXSJ_LEDCONFIG extends SdkStructure {
/** 车位个数*/
    public			int            nParkingNum;
/** 车位配置*/
    public			NET_CFG_VSP_LXSJ_PARKING[] stuParking = (NET_CFG_VSP_LXSJ_PARKING[])new NET_CFG_VSP_LXSJ_PARKING().toArray(256);
}

