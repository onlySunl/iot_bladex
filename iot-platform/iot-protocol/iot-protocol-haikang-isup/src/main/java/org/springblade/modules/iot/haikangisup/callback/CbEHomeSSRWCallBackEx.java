package org.springblade.modules.iot.haikangisup.callback;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.service.cms.HCISUPCMS;

/**
 * EHome SS 读写回调
 */
@Slf4j
public class CbEHomeSSRWCallBackEx implements HCISUPCMS.EHomeSSRWCallBackEx {

	@Override
	public boolean invoke(int iHandle, HCISUPCMS.NET_EHOME_SS_RW_PARAM pRwParam, HCISUPCMS.NET_EHOME_SS_EX_PARAM pExStruct) {
		log.info("EHome SS 读写回调 - iHandle:{}", iHandle);
		// 注意：回调的处理逻辑超过5s设备会重新上报，需要保证您的读写逻辑在5s内处理完成
		return false;
	}
}
