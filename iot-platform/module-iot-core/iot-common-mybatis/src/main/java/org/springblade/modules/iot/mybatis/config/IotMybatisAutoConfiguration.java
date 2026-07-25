package org.springblade.modules.iot.mybatis.config;

import org.springblade.modules.iot.mybatis.core.handler.DefaultDBFieldHandler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * IoT MyBatis 自动配置
 *
 * @author EnjoyIot
 */
@AutoConfiguration
public class IotMybatisAutoConfiguration {

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new DefaultDBFieldHandler();
    }
}
