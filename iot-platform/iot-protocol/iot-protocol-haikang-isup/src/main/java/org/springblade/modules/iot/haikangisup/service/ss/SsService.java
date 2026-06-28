package org.springblade.modules.iot.haikangisup.service.ss;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.callback.PSS_Message_Callback;
import org.springblade.modules.iot.haikangisup.callback.PSS_Storage_Callback;
import org.springblade.modules.iot.haikangisup.config.HaiKangIsupConfig;
import org.springframework.stereotype.Service;

/**
 * SS服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SsService {
	public static HCISUPSS hCEhomeSS = null;
	public static int SsHandle = -1;

	private final HaiKangIsupConfig haiKangIsupConfig;
	private final PSS_Message_Callback pSS_Message_Callback;
	private final PSS_Storage_Callback pSS_Storage_Callback;

	public void eSS_Init() {
		log.info("SS服务初始化");
	}

	public void startSsListen() {
		log.info("启动SS监听");
	}

	public void stopSsListen() {
		if (hCEhomeSS != null) {
			hCEhomeSS.NET_ESS_Fini();
		}
	}
}
