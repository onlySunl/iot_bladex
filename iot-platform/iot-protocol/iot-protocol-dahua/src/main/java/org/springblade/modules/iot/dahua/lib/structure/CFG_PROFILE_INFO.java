package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 情景详细信息
 * @date 2023/03/15 20:39:47
 */
public class CFG_PROFILE_INFO extends SdkStructure {
	/**
	 * 情景ID
	 */
    public int              nSceneID;
	/**
	 * 厂家名称
	 */
    public byte[]           szBrand = new byte[64];
	/**
	 * 情景模式 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_SMARTHOME_SCENE_MODE}
	 */
    public int              emScene;
	/**
	 * 串口地址
	 */
    public CFG_COMMADDR_INFO stuCommAddr = new CFG_COMMADDR_INFO();

	public CFG_PROFILE_INFO() {
	}
}

