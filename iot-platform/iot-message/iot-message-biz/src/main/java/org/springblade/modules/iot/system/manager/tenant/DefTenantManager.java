package org.springblade.modules.iot.system.manager.tenant;

import com.mqttsnet.basic.base.manager.SuperCacheManager;
import com.mqttsnet.basic.interfaces.echo.LoadService;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springblade.modules.iot.system.vo.result.tenant.DefTenantResultVO;

import java.util.List;

/**
 * <p>
 * 通用业务层
 * 企业
 * </p>
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
public interface DefTenantManager extends SuperCacheManager<DefTenant>, LoadService {
    /**
     * 查询所有可用的租户
     *
     * @return
     */
    List<DefTenant> listNormal();

    /**
     * 查询用户的可用企业
     *
     * @param userId 用户id
     * @return
     */
    List<DefTenantResultVO> listTenantByUserId(Long userId);
}
