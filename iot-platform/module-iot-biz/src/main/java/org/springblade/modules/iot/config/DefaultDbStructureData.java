package org.springblade.modules.iot.config;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.IDbStructureData;
import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认数据库结构配置
 * 当没有配置时序存储模块时使用此配置
 */
@Slf4j
@Configuration
public class DefaultDbStructureData {

    @Bean
    @ConditionalOnMissingBean(IDbStructureData.class)
    public IDbStructureData defaultDbStructureData() {
        return new IDbStructureData() {
            @Override
            public void defineThingModel(ThingModel thingModel) {
                log.warn("时序存储模块未配置，无法定义物模型。请配置 iot-temporal 模块（如 TDengine、IoTDB、Elasticsearch 等）");
            }

            @Override
            public void updateThingModel(ThingModel thingModel) {
                log.warn("时序存储模块未配置，无法更新物模型。请配置 iot-temporal 模块（如 TDengine、IoTDB、Elasticsearch 等）");
            }

            @Override
            public void initDbStructure() {
                log.warn("时序存储模块未配置，无法初始化数据库结构。请配置 iot-temporal 模块（如 TDengine、IoTDB、Elasticsearch 等）");
            }
        };
    }
}
