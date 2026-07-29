package org.springblade.modules.iot.system.facade.impl;

import cn.hutool.core.collection.CollUtil;
import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.utils.BeanPlusUtil;
import org.springblade.modules.iot.model.constant.EchoApi;
import org.springblade.modules.iot.system.entity.tenant.DefUser;
import org.springblade.modules.iot.system.facade.DefUserFacade;
import org.springblade.modules.iot.system.service.tenant.DefUserService;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserDetailsResultVO;
import org.springblade.modules.iot.system.vo.result.tenant.DefUserResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tangyh
 * @since 2024/9/20 23:33
 */
@Service(EchoApi.DEF_USER_ID_CLASS)
@RequiredArgsConstructor
public class DefUserFacadeImpl implements DefUserFacade {
    private final DefUserService defUserService;

    @Override
    public R<List<Long>> findAllUserId() {
        return R.success(defUserService.findUserIdList(null));
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return defUserService.findByIds(ids);
    }

    @Override
    public R<List<DefUserDetailsResultVO>> getDefUserByIds(List<Long> ids) {
        return R.success(defUserService.getDefUserByIds(ids));
    }

    @Override
    public R<List<DefUserResultVO>> queryIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return R.success(Collections.emptyList());
        }
        List<DefUser> entities = defUserService.listByIds(ids.stream().distinct().collect(Collectors.toList()));
        return R.success(BeanPlusUtil.toBeanList(entities, DefUserResultVO.class));
    }
}
