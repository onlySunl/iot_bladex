package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 事件类型 EVENT_IVS_QSYTRAFFICCARWEIGHT (交通卡口称重事件)对应的数据块描述信息
 * @date 2023/04/07 17:12:26
 */
public class DEV_EVENT_QSYTRAFFICCARWEIGHT_INFO extends SdkStructure {
	/**
	 * 称重系统车辆信息
	 */
    public NET_CAR_WEIGHT_INFO stCarWeightInfo = new NET_CAR_WEIGHT_INFO();
	/**
	 * 交通卡口信息
	 */
    public DEV_EVENT_TRAFFICJUNCTION_INFO stJunctionInfo = new DEV_EVENT_TRAFFICJUNCTION_INFO();

	public DEV_EVENT_QSYTRAFFICCARWEIGHT_INFO() {
	}
}

