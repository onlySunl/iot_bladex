package org.springblade.modules.iot.system.service.system.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperCacheServiceImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.model.enumeration.system.DataTypeEnum;
import org.springblade.modules.iot.system.entity.system.DefParameter;
import org.springblade.modules.iot.system.manager.system.DefParameterManager;
import org.springblade.modules.iot.system.service.system.DefParameterService;
import org.springblade.modules.iot.system.vo.save.system.DefParameterSaveVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 参数配置
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@DS(DsConstant.DEFAULTS)
public class DefParameterServiceImpl extends SuperCacheServiceImpl<DefParameterManager, Long, DefParameter> implements DefParameterService {

    @Override
    protected <SaveVO> DefParameter saveBefore(SaveVO saveVO) {
        DefParameterSaveVO defParameterSaveVO = (DefParameterSaveVO) saveVO;
        DefParameter defParameter = super.saveBefore(defParameterSaveVO);
        defParameter.setParamType(DataTypeEnum.SYSTEM.getCode());
        return defParameter;
    }

    @Override
    public Boolean checkKey(String key, Long id) {
        ArgumentAssert.notEmpty(key, "请填写参数健");
        return superManager.count(Wraps.<DefParameter>lbQ().eq(DefParameter::getKey, key).ne(DefParameter::getId, id)) > 0;
    }
}
