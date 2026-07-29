package org.springblade.modules.iot.system.manager.application;

import com.mqttsnet.basic.base.manager.SuperManager;
import org.springblade.modules.iot.system.entity.application.DefTenantApplicationRel;

import java.util.List;

/**
 * <p>
 * 通用业务层
 * 租户的应用
 * </p>
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
public interface DefTenantApplicationRelManager extends SuperManager<DefTenantApplicationRel> {
    /**
     * 给指定租户授权 公共应用
     *
     * @param tenantId 租户id
     */
    void grantGeneralApplication(Long tenantId);


    /**
     * 查询员工拥有的应用
     *
     * @param employeeId 员工id
     * @return
     */
    List<Long> findApplicationByEmployeeId(Long employeeId);

    /**
     * 删除指定租户的应用
     *
     * @param ids ids
     * @author mqttsnet
     * @date 2022/10/28 4:54 PM
     * @create [2022/10/28 4:54 PM ] [mqttsnet] [初始创建]
     */
    void deleteByTenantId(List<Long> ids);
}
