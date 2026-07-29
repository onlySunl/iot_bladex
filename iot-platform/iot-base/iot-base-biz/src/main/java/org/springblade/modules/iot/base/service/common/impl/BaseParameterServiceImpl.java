package org.springblade.modules.iot.base.service.common.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import org.springblade.modules.iot.base.entity.common.BaseParameter;
import org.springblade.modules.iot.base.manager.common.BaseParameterManager;
import org.springblade.modules.iot.base.service.common.BaseParameterService;
import org.springblade.modules.iot.base.vo.save.common.BaseParameterSaveVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.model.enumeration.system.DataTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 个性参数
 * </p>
 *
 * @author mqttsnet
 * @date 2021-11-08
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@DS(DsConstant.BASE_TENANT)
public class BaseParameterServiceImpl extends SuperServiceImpl<BaseParameterManager, Long, BaseParameter> implements BaseParameterService {
    @Override
    protected <SaveVO> BaseParameter saveBefore(SaveVO saveVO) {
        BaseParameterSaveVO baseParameterSaveVO = (BaseParameterSaveVO) saveVO;
        BaseParameter baseParameter = super.saveBefore(baseParameterSaveVO);
        baseParameter.setParamType(DataTypeEnum.SYSTEM.getCode());
        return baseParameter;
    }


}
