package org.springblade.modules.iot;

import com.mqttsnet.basic.validator.annotation.EnableFormValidator;
import org.springblade.modules.iot.common.ServerApplication;
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
 * https://mp.weixin.qq.com/s/zkxI5IQP0jFTjVYe5pTsXw
 * EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true) 配合 @EnableCaching
 * 才能解决在同一个类中通过 AopContext.currentProxy() 调用时，使缓存生效
 *
 * @author mqttsnet
 * @date 2018-01-13 1:34
 */
@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@ComponentScan({
        UTIL_PACKAGE, BUSINESS_PACKAGE
})
@EnableFeignClients(value = {
        UTIL_PACKAGE, BUSINESS_PACKAGE
})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableFormValidator
public class SystemServerApplication extends ServerApplication {
    public static void main(String[] args) throws UnknownHostException {
        start(SystemServerApplication.class, args);
    }

}
