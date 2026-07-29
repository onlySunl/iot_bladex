package org.springblade.modules.iot.msg.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.msg.entity.DefInterface;
import org.springblade.modules.iot.msg.entity.DefInterfaceProperty;
import org.springblade.modules.iot.msg.enumeration.InterfaceExecModeEnum;
import org.springblade.modules.iot.msg.manager.DefInterfaceManager;
import org.springblade.modules.iot.msg.manager.DefInterfacePropertyManager;
import org.springblade.modules.iot.msg.service.DefInterfaceService;
import org.springblade.modules.iot.msg.vo.save.DefInterfaceSaveVO;
import org.springblade.modules.iot.msg.vo.update.DefInterfaceUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 业务实现类
 * 接口
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [mqttsnet] 
 */
@DS(DsConstant.DEFAULTS)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefInterfaceServiceImpl extends SuperServiceImpl<DefInterfaceManager, Long, DefInterface> implements DefInterfaceService {
    private final DefInterfacePropertyManager defInterfacePropertyManager;

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写接口编码");
        return superManager.count(Wraps.<DefInterface>lbQ().eq(DefInterface::getCode, code)
                .ne(DefInterface::getId, id)) > 0;
    }

    @Override
    protected <SaveVO> DefInterface saveBefore(SaveVO saveVO) {
        DefInterfaceSaveVO interfaceSaveVO = (DefInterfaceSaveVO) saveVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(interfaceSaveVO.getCode()) &&
                               check(interfaceSaveVO.getCode(), null), "接口编码{}已存在", interfaceSaveVO.getCode());
        if (InterfaceExecModeEnum.IMPL_CLASS.eq(interfaceSaveVO.getExecMode())) {
            ArgumentAssert.notEmpty(interfaceSaveVO.getImplClass(), "请填写实现类");
        } else {
            ArgumentAssert.notEmpty(interfaceSaveVO.getScript(), "请填写实现脚本");
        }
        return super.saveBefore(saveVO);
    }

    @Override
    protected <UpdateVO> DefInterface updateBefore(UpdateVO updateVO) {
        DefInterfaceUpdateVO interfaceUpdateVO = (DefInterfaceUpdateVO) updateVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(interfaceUpdateVO.getCode()) &&
                               check(interfaceUpdateVO.getCode(), interfaceUpdateVO.getId()),
                "接口编码{}已存在", interfaceUpdateVO.getCode());
        if (InterfaceExecModeEnum.IMPL_CLASS.eq(interfaceUpdateVO.getExecMode())) {
            ArgumentAssert.notEmpty(interfaceUpdateVO.getImplClass(), "请填写实现类");
        } else {
            ArgumentAssert.notEmpty(interfaceUpdateVO.getScript(), "请填写实现脚本");
        }
        return super.updateBefore(interfaceUpdateVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<Long> idList) {
        defInterfacePropertyManager.remove(Wraps.<DefInterfaceProperty>lbQ().in(DefInterfaceProperty::getInterfaceId, idList));
        return super.removeByIds(idList);
    }
}


