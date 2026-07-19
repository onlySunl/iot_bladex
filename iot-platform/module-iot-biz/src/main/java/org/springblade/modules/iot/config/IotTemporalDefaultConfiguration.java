package org.springblade.modules.iot.config;

import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.*;
import org.springblade.modules.iot.api.device.dto.DeviceProperty;
import org.springblade.modules.iot.api.device.dto.DevicePropertyCache;
import org.springblade.modules.iot.api.rule.dto.RuleLog;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.springblade.modules.iot.api.thingmodel.dto.ThingModel;
import org.springblade.modules.iot.api.virtualdevice.dto.VirtualDeviceLog;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.thing.ThingModelMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * IoT 时序存储默认配置
 * 当没有配置时序存储模块时使用此配置提供空实现
 */
@Slf4j
@Configuration
public class IotTemporalDefaultConfiguration {

    private static final String WARN_MSG = "时序存储模块未配置，请配置 iot-temporal 模块（如 TDengine、IoTDB、Elasticsearch 等）";

    @Bean
    @ConditionalOnMissingBean(IDbStructureData.class)
    public IDbStructureData dbStructureData() {
        return new IDbStructureData() {
            @Override
            public void defineThingModel(ThingModel thingModel) {
                log.warn(WARN_MSG);
            }

            @Override
            public void updateThingModel(ThingModel thingModel) {
                log.warn(WARN_MSG);
            }

            @Override
            public void initDbStructure() {
                log.warn(WARN_MSG);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(IDevicePropertyData.class)
    public IDevicePropertyData devicePropertyData() {
        return new IDevicePropertyData() {
            @Override
            public List<DeviceProperty> findDevicePropertyHistory(Long deviceId, String name, long start, long end, int size) {
                log.warn(WARN_MSG);
                return Collections.emptyList();
            }

            @Override
            public void addProperties(Long deviceId, Map<String, DevicePropertyCache> properties, long time) {
                log.warn(WARN_MSG);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(IRuleLogData.class)
    public IRuleLogData ruleLogData() {
        return new IRuleLogData() {
            @Override
            public void deleteByRuleId(Long ruleId) {
                log.warn(WARN_MSG);
            }

            @Override
            public PageResult<RuleLog> findByRuleId(Long ruleId, int page, int size) {
                log.warn(WARN_MSG);
                return PageResult.empty();
            }

            @Override
            public void add(RuleLog log) {
                log.warn(WARN_MSG);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(ITaskLogData.class)
    public ITaskLogData taskLogData() {
        return new ITaskLogData() {
            @Override
            public void deleteByTaskId(Long taskId) {
                log.warn(WARN_MSG);
            }

            @Override
            public PageResult<TaskLog> findByTaskId(Long taskId, int page, int size) {
                log.warn(WARN_MSG);
                return PageResult.empty();
            }

            @Override
            public void add(TaskLog log) {
                log.warn(WARN_MSG);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(IThingModelMessageData.class)
    public IThingModelMessageData thingModelMessageData() {
        return new IThingModelMessageData() {
            @Override
            public PageResult<ThingModelMessage> findByTypeAndIdentifier(Long deviceId, String type, String identifier, int page, int size) {
                log.warn(WARN_MSG);
                return PageResult.empty();
            }

            @Override
            public List<IThingModelMessageData.TimeData> getDeviceMessageStatsWithUid(String uid, long start, long end) {
                log.warn(WARN_MSG);
                return Collections.emptyList();
            }

            @Override
            public List<IThingModelMessageData.TimeData> getDeviceUpMessageStatsWithUid(String uid, Long start, Long end) {
                log.warn(WARN_MSG);
                return Collections.emptyList();
            }

            @Override
            public List<IThingModelMessageData.TimeData> getDeviceDownMessageStatsWithUid(String uid, Long start, Long end) {
                log.warn(WARN_MSG);
                return Collections.emptyList();
            }

            @Override
            public PageResult<ThingModelMessage> findByTypeAndDeviceIds(List<Long> deviceIds, String type, String identifier, int page, int size) {
                log.warn(WARN_MSG);
                return PageResult.empty();
            }

            @Override
            public void add(ThingModelMessage msg) {
                log.warn(WARN_MSG);
            }

            @Override
            public long count() {
                log.warn(WARN_MSG);
                return 0;
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(IVirtualDeviceLogData.class)
    public IVirtualDeviceLogData virtualDeviceLogData() {
        return new IVirtualDeviceLogData() {
            @Override
            public PageResult<VirtualDeviceLog> findByVirtualDeviceId(Long virtualDeviceId, int page, int size) {
                log.warn(WARN_MSG);
                return PageResult.empty();
            }

            @Override
            public void add(VirtualDeviceLog log) {
                log.warn(WARN_MSG);
            }
        };
    }
}
