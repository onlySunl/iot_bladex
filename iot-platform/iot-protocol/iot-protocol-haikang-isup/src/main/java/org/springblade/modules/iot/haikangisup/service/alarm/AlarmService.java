package org.springblade.modules.iot.haikangisup.service.alarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.callback.EHomeMsgCallBack;
import org.springblade.modules.iot.haikangisup.config.HaiKangIsupConfig;
import org.springframework.stereotype.Service;

/**
 * 报警服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {
	public static HCISUPAlarm hcEHomeAlarm = null;
	public int AlarmHandle = -1;

	private final EHomeMsgCallBack cbEHomeMsgCallBack;
	private final HaiKangIsupConfig haiKangIsupConfig;

	public void eAlarm_Init() {
		log.info("报警服务初始化");
	}

	public void startAlarmListen() {
		log.info("启动报警监听");
	}

	public void stopAlarmListen() {
		if (hcEHomeAlarm != null) {
			hcEHomeAlarm.NET_EALARM_Fini();
		}
	}
}
