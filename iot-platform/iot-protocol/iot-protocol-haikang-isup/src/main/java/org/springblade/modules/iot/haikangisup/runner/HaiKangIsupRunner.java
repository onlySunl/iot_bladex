package org.springblade.modules.iot.haikangisup.runner;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.haikangisup.service.cms.CmsService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 海康ISUP初始化启动器
 */
@Slf4j
@Component
@Order(100)
public class HaiKangIsupRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("海康ISUP模块初始化启动...");
		// CMS已在CmsService静态块中初始化
		log.info("海康ISUP模块初始化完成");
	}
}
