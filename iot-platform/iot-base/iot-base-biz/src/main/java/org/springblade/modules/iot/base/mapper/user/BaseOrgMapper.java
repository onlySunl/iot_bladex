package org.springblade.modules.iot.base.mapper.user;

import com.mqttsnet.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.base.entity.user.BaseOrg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 组织
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 */
@Repository
public interface BaseOrgMapper extends SuperMapper<BaseOrg> {


    /**
     * 查询员工拥有的机构
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author mqttsnet
     * @date 2022/10/20 3:44 PM
     * @create [2022/10/20 3:44 PM ] [mqttsnet] [初始创建]
     */
    List<Long> selectOrgByEmployeeId(@Param("employeeId") Long employeeId);
}
