package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 机箱入侵报警(防拆报警)配置
 * @date 2023/03/15 21:57:49
 */
public class CFG_CHASSISINTRUSION_INFO extends SdkStructure {
	/**
	 * 使能开关
	 */
    public int              bEnable;
	/**
	 * 报警联动
	 */
    public CFG_ALARM_MSG_HANDLE stuEventHandler = new CFG_ALARM_MSG_HANDLE();

	public CFG_CHASSISINTRUSION_INFO() {
	}
}

