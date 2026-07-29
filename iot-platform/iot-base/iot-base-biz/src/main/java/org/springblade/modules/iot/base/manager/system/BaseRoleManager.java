package org.springblade.modules.iot.base.manager.system;

import com.mqttsnet.basic.base.manager.SuperCacheManager;
import org.springblade.modules.iot.base.entity.system.BaseRole;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 角色
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 */
public interface BaseRoleManager extends SuperCacheManager<BaseRole> {
    /**
     * 查询员工的所有角色ID
     *
     * @param employeeId employeeId
     * @return java.util.List<org.springblade.modules.iot.base.entity.system.BaseRole>
     * @author mqttsnet
     * @date 2022/10/27 3:21 PM
     * @create [2022/10/27 3:21 PM ] [mqttsnet] [初始创建]
     */
    List<Long> findRoleIdByEmployeeId(Long employeeId);

    /**
     * 查询员工的所有角色
     *
     * @param employeeId employeeId
     * @return java.util.List<org.springblade.modules.iot.base.entity.system.BaseRole>
     * @author mqttsnet
     * @date 2022/10/27 3:21 PM
     * @create [2022/10/27 3:21 PM ] [mqttsnet] [初始创建]
     */
    List<BaseRole> findRoleByEmployeeId(Long employeeId);

    /**
     * 根据角色编码查询角色
     *
     * @param code
     * @return
     */
    BaseRole getRoleByCode(String code);


    /**
     * 根据角色id查询员工id
     *
     * @param roleIds roleIds
     * @return java.util.List<java.lang.Long>
     * @author mqttsnet
     * @date 2021/12/28 12:22 上午
     * @create [2021/12/28 12:22 上午 ] [mqttsnet] [初始创建]
     */
    List<Long> listEmployeeIdByRoleId(List<Long> roleIds);

    /**
     * 查询员工拥有的资源
     *
     * @param employeeId    员工ID
     * @param applicationId 应用ID
     * @return java.util.List<java.lang.Long>
     * @author mqttsnet
     * @date 2022/10/20 5:26 PM
     * @create [2022/10/20 5:26 PM ] [mqttsnet] [初始创建]
     */
    List<Long> findResourceIdByEmployeeId(Long applicationId, Long employeeId);

    /**
     * 检查某个员工{employeeId}是否拥有任意一个角色{codes}
     *
     * @param employeeId employeeId
     * @param codes      codes
     * @return boolean
     * @author mqttsnet
     * @date 2022/4/24 2:49 PM
     * @create [2022/4/24 2:49 PM ] [mqttsnet] [初始创建]
     */
    boolean checkRole(Long employeeId, String... codes);

}
