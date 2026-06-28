package org.springblade.modules.iot.haikangisup.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.config.HaiKangIsupConfig;
import org.springblade.modules.iot.haikangisup.service.alarm.AlarmService;
import org.springblade.modules.iot.haikangisup.service.cms.CmsService;
import org.springblade.modules.iot.haikangisup.service.ss.SsService;
import org.springblade.modules.iot.haikangisup.service.stream.StreamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 海康ISUP启动服务
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HaiKangIsupCommandLineRunner implements CommandLineRunner {

	private final CmsService cmsService;
	private final SsService ssService;
	private final StreamService streamService;
	private final AlarmService alarmService;
	private final HaiKangIsupConfig haiKangIsupConfig;

	@Override
	public void run(String... args) throws Exception {
		log.info("=========================  开启海康isup服务监听  =========================");
		ssService.eSS_Init();
		ssService.startSsListen();

		streamService.eStream_Init();

		cmsService.cMS_Init();
		cmsService.startCmsListen();

		Boolean alarmStorage = haiKangIsupConfig.getAlarmServer().getAlarmStorage();
		if (alarmStorage != null && alarmStorage) {
			log.info("海康ISUP报警存储功能已开启，启动报警监听");
			alarmService.eAlarm_Init();
			alarmService.startAlarmListen();
		} else {
			log.info("海康ISUP报警存储功能未开启，跳过报警监听启动");
		}
	}
}
