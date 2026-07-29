package org.springblade.modules.iot.base.service.user.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.database.mybatis.conditions.query.LbQueryWrap;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.base.entity.user.BasePosition;
import org.springblade.modules.iot.base.manager.user.BasePositionManager;
import org.springblade.modules.iot.base.service.user.BasePositionService;
import org.springblade.modules.iot.base.vo.save.user.BasePositionSaveVO;
import org.springblade.modules.iot.base.vo.update.user.BasePositionUpdateVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 岗位
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
public class BasePositionServiceImpl extends SuperServiceImpl<BasePositionManager, Long, BasePosition> implements BasePositionService {

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return superManager.findByIds(ids.stream().map(Convert::toLong).collect(Collectors.toSet()));
    }

    @Override
    public boolean check(String name, Long orgId, Long id) {
        ArgumentAssert.notEmpty(name, "请填写名称");
        LbQueryWrap<BasePosition> wrap = Wraps.<BasePosition>lbQ().eq(BasePosition::getName, name).ne(BasePosition::getId, id);
        return superManager.count(wrap) > 0;
    }

    @Override
    protected <SaveVO> BasePosition saveBefore(SaveVO saveVO) {
        BasePositionSaveVO positionSaveVO = (BasePositionSaveVO) saveVO;
        ArgumentAssert.isFalse(check(positionSaveVO.getName(), positionSaveVO.getOrgId(), null), StrUtil.format("岗位[{}]已经存在", positionSaveVO.getName()));
        return super.saveBefore(saveVO);
    }

    @Override
    protected <UpdateVO> BasePosition updateBefore(UpdateVO updateVO) {
        BasePositionUpdateVO positionUpdateVO = (BasePositionUpdateVO) updateVO;
        ArgumentAssert.isFalse(check(positionUpdateVO.getName(), positionUpdateVO.getOrgId(), positionUpdateVO.getId()), StrUtil.format("岗位[{}]已经存在", positionUpdateVO.getName()));
        return super.updateBefore(updateVO);
    }
}
