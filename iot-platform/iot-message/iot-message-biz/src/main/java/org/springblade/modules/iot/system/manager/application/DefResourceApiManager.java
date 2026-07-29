package org.springblade.modules.iot.system.manager.application;

import com.mqttsnet.basic.base.manager.SuperCacheManager;
import org.springblade.modules.iot.model.vo.result.ResourceApiVO;
import org.springblade.modules.iot.system.entity.application.DefResourceApi;

import java.util.List;

/**
 * <p>
 * 通用业务层
 * 资源API
 * </p>
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
public interface DefResourceApiManager extends SuperCacheManager<DefResourceApi> {
    /** 查询系统中配置的所有API与资源编码 */
    List<ResourceApiVO> findAllApi();

    /**
     * 根据资源id 删除资源的接口
     *
     * @param resourceIdList 资源id
     * @author mqttsnet
     * @date 2021/9/17 9:42 下午
     * @create [2021/9/17 9:42 下午 ] [mqttsnet] [初始创建]
     */
    void removeByResourceId(List<Long> resourceIdList);

    /**
     * 根据资源id查询资源接口
     *
     * @param resourceId 资源id
     * @return java.util.List<org.springblade.modules.iot.tenant.vo.result.tenant.DefResourceApiResultVO>
     * @author mqttsnet
     * @date 2021/9/20 6:49 下午
     * @create [2021/9/20 6:49 下午 ] [mqttsnet] [初始创建]
     */
    List<DefResourceApi> findByResourceId(Long resourceId);


    /**
     * 根据资源id 批量查询资源接口
     * @param resourceIds 资源id
     * @return
     */
    List<DefResourceApi> findByResourceId(List<Long> resourceIds);

}
