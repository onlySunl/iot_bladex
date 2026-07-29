package org.springblade.modules.iot.base.service.user.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import org.springblade.modules.iot.base.entity.user.BaseEmployeeRoleRel;
import org.springblade.modules.iot.base.manager.user.BaseEmployeeRoleRelManager;
import org.springblade.modules.iot.base.service.user.BaseEmployeeRoleRelService;
import org.springblade.modules.iot.common.constant.DsConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 员工的角色
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@DS(DsConstant.BASE_TENANT)
public class BaseEmployeeRoleRelServiceImpl extends SuperServiceImpl<BaseEmployeeRoleRelManager, Long, BaseEmployeeRoleRel> implements BaseEmployeeRoleRelService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindRole(List<Long> employeeIdList, String code) {
        return superManager.bindRole(employeeIdList, code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unBindRole(List<Long> employeeIdList, String code) {
        return superManager.unBindRole(employeeIdList, code);
    }
}
