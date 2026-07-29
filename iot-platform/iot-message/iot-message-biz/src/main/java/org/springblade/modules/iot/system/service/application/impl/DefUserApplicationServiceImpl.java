package org.springblade.modules.iot.system.service.application.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.system.entity.application.DefUserApplication;
import org.springblade.modules.iot.system.manager.application.DefUserApplicationManager;
import org.springblade.modules.iot.system.service.application.DefUserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 用户的默认应用
 * </p>
 *
 * @author mqttsnet
 * @date 2022-03-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@DS(DsConstant.DEFAULTS)
public class DefUserApplicationServiceImpl extends SuperServiceImpl<DefUserApplicationManager, Long, DefUserApplication> implements DefUserApplicationService {
    @Override
    public Long getMyDefAppByUserId(Long userId) {
        DefUserApplication userApplication = superManager.getOne(Wraps.<DefUserApplication>lbQ().eq(DefUserApplication::getUserId, userId), false);
        return userApplication != null ? userApplication.getApplicationId() : null;
    }
}
