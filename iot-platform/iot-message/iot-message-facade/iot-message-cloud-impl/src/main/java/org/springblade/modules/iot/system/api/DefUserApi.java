package org.springblade.modules.iot.system.api;

import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.constant.Constants;
import org.springblade.modules.iot.model.entity.system.SysUser;
import org.springblade.modules.iot.model.vo.result.UserQuery;
import org.springblade.modules.iot.system.api.hystrix.DefUserApiFallback;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserDetailsResultVO;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserResultVO;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:thinglinks-system-server}", fallback = DefUserApiFallback.class)
public interface DefUserApi {
    /**
     * 查询所有的用户id
     *
     * @return 用户id
     */

    @PostMapping(value = "/defUser/findAllUserId")
    R<List<Long>> findAllUserId();


    /**
     * 根据id查询实体
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    @PostMapping("/echo/user/findByIds")
    Map<Serializable, Object> findByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    @PostMapping(value = "/queryIds")
    R<List<DefUserResultVO>> queryIds(@RequestBody @NotEmpty(message = "ID集合不能为空") List<?> ids);

    /**
     * 根据id 查询用户详情
     *
     * @param userQuery 查询条件
     * @return 系统用户
     */
    @PostMapping(value = "/anyone/getSysUserById")
    R<SysUser> getById(@RequestBody UserQuery userQuery);


    @PostMapping(value = "/defUser/getDefUserByIds")
    R<List<DefUserDetailsResultVO>> getDefUserByIds(@RequestBody @NotEmpty(message = "ID集合不能为空") List<Long> ids);
}
