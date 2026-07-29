package org.springblade.modules.iot.system.service.application.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.system.entity.application.DefTenantApplicationRecord;
import org.springblade.modules.iot.system.manager.application.DefTenantApplicationRecordManager;
import org.springblade.modules.iot.system.service.application.DefTenantApplicationRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 租户应用授权记录
 * </p>
 *
 * @author mqttsnet
 * @date 2021-09-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@DS(DsConstant.DEFAULTS)
public class DefTenantApplicationRecordServiceImpl extends SuperServiceImpl<DefTenantApplicationRecordManager, Long, DefTenantApplicationRecord>
        implements DefTenantApplicationRecordService {
}
