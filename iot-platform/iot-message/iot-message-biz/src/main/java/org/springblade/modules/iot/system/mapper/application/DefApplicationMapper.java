package org.springblade.modules.iot.system.mapper.application;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.application.DefApplication;
import org.springblade.modules.iot.system.vo.result.application.DefApplicationResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 应用
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-15
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefApplicationMapper extends SuperMapper<DefApplication> {
    /**
     * 查询指定租户 拥有的应用
     *
     * @param tenantId 租户id
     * @return 拥有的资源
     */
    List<DefApplication> findApplicationListByTenantId(@Param("tenantId") Long tenantId);

    /**
     * 查询我的应用
     *
     * @param tenantId 租户id
     * @param name     应用名
     * @return
     */
    List<DefApplicationResultVO> findMyApplication(@Param("tenantId") Long tenantId, @Param("name") String name);

    /**
     * 查询推荐应用
     *
     * @param tenantId 租户id
     * @param name     应用名
     * @return
     */
    List<DefApplicationResultVO> findRecommendApplication(@Param("tenantId") Long tenantId, @Param("name") String name);

    /**
     * 查询员工拥有的应用
     *
     * @param employeeId 员工id
     * @param now        当前时间
     * @return
     */
    List<Long> findApplicationByEmployeeId(@Param("employeeId") Long employeeId, @Param("now") LocalDateTime now);
}
