package org.springblade.modules.iot;

import org.springblade.common.validator.annotation.EnableFormValidator;
import org.springblade.modules.iot.common.ServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.spring.annotation.EnableDynamicTp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.UnknownHostException;

import static org.springblade.modules.iot.common.constant.BizConstant.BUSINESS_PACKAGE;
import static org.springblade.modules.iot.common.constant.BizConstant.UTIL_PACKAGE;

/**
 * 设备集成服务启动类
 *
 * @author mqttsnet
 * @date 2023-03-09 22:37:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@ComponentScan({UTIL_PACKAGE, BUSINESS_PACKAGE})
@EnableFeignClients(value = {UTIL_PACKAGE, BUSINESS_PACKAGE})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@Slf4j
@EnableFormValidator
@EnableAsync
@EnableDynamicTp
public class LinkServerApplication extends ServerApplication {
    public static void main(String[] args) throws UnknownHostException {
        start(LinkServerApplication.class, args);
    }
}
