package org.springblade.modules.iot.system.mapper.tenant;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.system.entity.tenant.DefUser;
import org.springblade.modules.iot.system.vo.query.tenant.DefUserPageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 用户
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-09
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefUserMapper extends SuperMapper<DefUser> {
    /**
     * 查询未绑定到该企业的用户
     *
     * @param pageQuery 参数
     * @param page      分页参数
     * @return
     */
    IPage<DefUser> selectNotUserByTenantId(@Param("param") DefUserPageQuery pageQuery, IPage<DefUser> page);

    /**
     * 查找同一企业下的用户
     *
     * @param pageQuery 参数
     * @param page      分页参数
     * @return
     */
    IPage<DefUser> pageUserByTenant(@Param("param") DefUserPageQuery pageQuery, IPage<DefUser> page);

    /**
     * 递增 密码错误次数
     *
     * @param id  用户id
     * @param now 当前时间
     * @return 被修改了几行数据
     * @author mqttsnet
     * @date 2022/10/28 4:57 PM
     * @create [2022/10/28 4:57 PM ] [mqttsnet] [初始创建]
     */
    int incrPasswordErrorNumById(@Param("id") Long id, @Param("now") LocalDateTime now);

    /**
     * 重置 密码错误次数
     *
     * @param id  用户id
     * @param now 当前时间
     * @return 被修改了几行数据
     */
    int resetPassErrorNum(@Param("id") Long id, @Param("now") LocalDateTime now);

}
