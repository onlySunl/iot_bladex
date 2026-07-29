package org.springblade.modules.iot.system.strategy;

import com.mqttsnet.basic.database.properties.DatabaseProperties;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.system.vo.save.tenant.DefTenantInitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 初始化系统上下文
 *
 * @author mqttsnet
 * @date 2020年03月15日11:58:47
 */
@Component
public class InitSystemContext {
    @Autowired
    private Map<String, InitSystemStrategy> initSystemStrategyMap;
    @Autowired
    private DatabaseProperties databaseProperties;

    /**
     * 初始化链接
     *
     * @param tenantConnect 链接参数
     */
    public boolean initData(DefTenantInitVO tenantConnect) {
        InitSystemStrategy initSystemStrategy = getInitSystemStrategy();

        return initSystemStrategy.initData(tenantConnect);
    }

    private InitSystemStrategy getInitSystemStrategy() {
        InitSystemStrategy initSystemStrategy = initSystemStrategyMap.get(databaseProperties.getMultiTenantType().name());
        ArgumentAssert.notNull(initSystemStrategy, "您配置的租户模式:{}不可用", databaseProperties.getMultiTenantType().name());
        return initSystemStrategy;
    }

    /**
     * 重置系统
     *
     * @param tenant 租户编码
     */
    public boolean reset(String tenant) {
        InitSystemStrategy initSystemStrategy = getInitSystemStrategy();
        return initSystemStrategy.reset(tenant);
    }

    /**
     * 删除租户数据
     *
     * @param ids 租户id
     */
    public boolean delete(List<Long> ids) {
        InitSystemStrategy initSystemStrategy = getInitSystemStrategy();

        return initSystemStrategy.delete(ids);
    }
}
