package org.springblade.modules.iot.service.script.infra.groovy

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springblade.modules.iot.dto.script.ProductInfoDTO
import org.springblade.modules.iot.service.script.GroovyScriptProductService
import org.springframework.context.ApplicationContext

/**
 * 测试从spring ioc容器中获取bean，并调用bean的方法
 *
 */
class GetApplicationContext extends Script {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    Object run() {
        // 调用方法
        ApplicationContext context = getContext();
        // 获取容器中的bean
        GroovyScriptProductService groovyScriptProductService = context.getBean(GroovyScriptProductService.class);
        // 调用bean的方法
        Random random = new Random();
        ProductInfoDTO productInfoDTO = groovyScriptProductService.getProductById(random.nextInt(1000));
        logger.info("productInfoDTO is : {}", productInfoDTO);

        // 调用bean 的修改方法
        groovyScriptProductService.updateProduct(productInfoDTO);
        logger.info("updated productInfoDTO is : {}", productInfoDTO);
        return productInfoDTO;
    }

    // 获取spring容器
    ApplicationContext getContext() {
        // 获取spring IOC容器
        ApplicationContext context = applicationContext;
        return context;
    }
}
