package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.SdkStructure;
/**
 * @description 速度限制
 * @author 119178
 * @date 2021/3/11
 */
public class NET_SPEED_LIMIT extends SdkStructure {
	/**
	 * 速度上限
	 */
    public int              nSpeedUpperLimit;
	/**
	 * 速度下限
	 */
    public int              nSpeedLowerLimit;
}

