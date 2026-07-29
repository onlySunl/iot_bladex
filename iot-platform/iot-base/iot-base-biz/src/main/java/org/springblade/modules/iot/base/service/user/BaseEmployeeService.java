package org.springblade.modules.iot.base.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mqttsnet.basic.base.request.PageParams;
import com.mqttsnet.basic.base.service.SuperCacheService;
import org.springblade.modules.iot.base.entity.user.BaseEmployee;
import org.springblade.modules.iot.base.vo.query.user.BaseEmployeePageQuery;
import org.springblade.modules.iot.base.vo.result.user.BaseEmployeeResultVO;
import org.springblade.modules.iot.base.vo.save.user.BaseEmployeeRoleRelSaveVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 业务接口
 * 员工
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 */
public interface BaseEmployeeService extends SuperCacheService<Long, BaseEmployee> {
    /**
     * 批量保存
     *
     * @param entityList entityList
     * @return boolean
     * @author mqttsnet
     * @date 2022/10/28 4:38 PM
     * @create [2022/10/28 4:38 PM ] [mqttsnet] [初始创建]
     */
    boolean saveBatch(Collection<BaseEmployee> entityList);

    /**
     * 给员工分配角色
     *
     * @param employeeRoleSaveVO
     * @return
     */
    List<Long> saveEmployeeRole(BaseEmployeeRoleRelSaveVO employeeRoleSaveVO);

    /**
     * 根据员工id查询员工的角色
     *
     * @param employeeId 员工id
     * @return
     */
    List<Long> findEmployeeRoleByEmployeeId(Long employeeId);

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    IPage<BaseEmployeeResultVO> findPageResultVO(PageParams<BaseEmployeePageQuery> params);


    /**
     * 批量保存 基础库员工和系统角色
     *
     * @param employeeList
     * @return
     */
    boolean saveBatchBaseEmployeeAndRole(List<BaseEmployee> employeeList);

    /**
     * 根据ID修改不为空的字段
     *
     * @param baseEmployee baseEmployee
     * @return boolean
     * @author mqttsnet
     * @date 2022/10/28 9:20 AM
     * @create [2022/10/28 9:20 AM ] [mqttsnet] [初始创建]
     */
    boolean updateById(BaseEmployee baseEmployee);

    /**
     * 根据ID修改所有的字段
     *
     * @param baseEmployee baseEmployee
     * @return boolean
     * @author mqttsnet
     * @date 2022/10/28 9:20 AM
     * @create [2022/10/28 9:20 AM ] [mqttsnet] [初始创建]
     */
    boolean updateAllById(BaseEmployee baseEmployee);

    /**
     * 修改员工的上次登录单位和部门id
     * @param id 员工id
     * @param lastCompanyId 上次登录单位id
     * @param lastDeptId 上次登录部门id
     */
    void updateOrgInfo(Long id, Long lastCompanyId, Long lastDeptId);
}
