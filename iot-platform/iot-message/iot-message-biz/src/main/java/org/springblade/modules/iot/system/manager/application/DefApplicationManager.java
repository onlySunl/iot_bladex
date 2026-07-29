package org.springblade.modules.iot.system.manager.application;

import com.mqttsnet.basic.base.manager.SuperCacheManager;
import com.mqttsnet.basic.interfaces.echo.LoadService;
import org.springblade.modules.iot.system.entity.application.DefApplication;
import org.springblade.modules.iot.system.vo.result.application.DefApplicationResultVO;

import java.util.List;

/**
 * 应用管理
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
public interface DefApplicationManager extends SuperCacheManager<DefApplication>, LoadService {
    /**
     * 查询指定租户 拥有的应用
     *
     * @param tenantId 租户id
     * @return 拥有的资源
     */
    List<DefApplication> findApplicationListByTenantId(Long tenantId);

    /**
     * 查询我的应用
     *
     * @param tenantId 租户id
     * @param name     应用名
     * @return
     */
    List<DefApplicationResultVO> findMyApplication(Long tenantId, String name);

    /**
     * 查询推荐应用
     *
     * @param tenantId 租户id
     * @param name     应用名
     * @return
     */
    List<DefApplicationResultVO> findRecommendApplication(Long tenantId, String name);

    /**
     * 查询公共应用
     *
     * @return
     */
    List<DefApplication> findGeneral();

}
