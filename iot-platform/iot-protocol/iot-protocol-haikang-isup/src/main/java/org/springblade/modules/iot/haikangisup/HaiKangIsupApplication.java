package org.springblade.modules.iot.haikangisup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 海康ISUP模块
 *
 * @author fengcheng
 */
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class HaiKangIsupApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaiKangIsupApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  海康ISUP模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
	}
}
