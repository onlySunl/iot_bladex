package org.springblade.modules.iot.system.manager.application;

import com.mqttsnet.basic.base.manager.SuperManager;
import org.springblade.modules.iot.system.entity.application.DefTenantResourceRel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通用业务层
 * 租户的资源
 * </p>
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
public interface DefTenantResourceRelManager extends SuperManager<DefTenantResourceRel> {
    /**
     * 查询指定企业的应用和资源
     *
     * @param tenantId 企业id
     * @return
     */
    Map<Long, Collection<Long>> findResourceIdByTenantId(Long tenantId);

    /**
     * 删除指定租户的资源
     *
     * @param ids ids
     * @author mqttsnet
     * @date 2022/10/28 4:56 PM
     * @create [2022/10/28 4:56 PM ] [mqttsnet] [初始创建]
     */
    void deleteByTenantId(List<Long> ids);
}
