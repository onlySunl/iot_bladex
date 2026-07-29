package org.springblade.modules.iot.system.api.hystrix;

import com.mqttsnet.basic.base.R;
import org.springblade.modules.iot.model.entity.system.SysUser;
import org.springblade.modules.iot.model.vo.result.UserQuery;
import org.springblade.modules.iot.system.api.DefUserApi;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserDetailsResultVO;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserResultVO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户API熔断
 *
 * @author zuihou
 * @date 2019/07/23
 */
@Component
public class DefUserApiFallback implements DefUserApi {
    @Override
    public R<List<Long>> findAllUserId() {
        return R.timeout();
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return Map.of();
    }

    @Override
    public R<SysUser> getById(UserQuery userQuery) {
        return R.timeout();
    }

    @Override
    public R<List<DefUserDetailsResultVO>> getDefUserByIds(List<Long> ids) {
        return R.timeout();
    }

    @Override
    public R<List<DefUserResultVO>> queryIds(List<?> ids) {
        return R.timeout();
    }
}
