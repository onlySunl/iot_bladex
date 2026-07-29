package org.springblade.modules.iot;

import com.mqttsnet.basic.validator.annotation.EnableFormValidator;
import org.springblade.modules.iot.common.ServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.net.UnknownHostException;

import static org.springblade.modules.iot.common.constant.BizConstant.BUSINESS_PACKAGE;
import static org.springblade.modules.iot.common.constant.BizConstant.UTIL_PACKAGE;

/**
 * 基础服务启动类
 *
 * @author mqttsnet
 * @date 2021-10-08
 */
@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@ComponentScan({UTIL_PACKAGE, BUSINESS_PACKAGE})
@EnableFeignClients(value = {UTIL_PACKAGE, BUSINESS_PACKAGE})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@Slf4j
@EnableFormValidator
public class BaseServerApplication extends ServerApplication {
    public static void main(String[] args) throws UnknownHostException {
        start(BaseServerApplication.class, args);
    }
}
