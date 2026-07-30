package org.springblade.modules.iot.msg.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.core.database.mybatis.conditions.Wraps;
import org.springblade.core.mvc.service.impl.SuperServiceImpl;
import org.springblade.basic.utils.ArgumentAssert;
import org.springblade.modules.iot.msg.entity.ExtendMsgTemplate;
import org.springblade.modules.iot.msg.manager.ExtendMsgTemplateManager;
import org.springblade.modules.iot.msg.service.ExtendMsgTemplateService;
import org.springblade.modules.iot.msg.vo.save.ExtendMsgTemplateSaveVO;
import org.springblade.modules.iot.msg.vo.update.ExtendMsgTemplateUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 消息模板
 * </p>
 *
 * @author mqttsnet
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [mqttsnet] 
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendMsgTemplateServiceImpl extends SuperServiceImpl<ExtendMsgTemplateManager, Long, ExtendMsgTemplate> implements ExtendMsgTemplateService {
    @Override
    public ExtendMsgTemplate getByCode(String code) {
        return superManager.getByCode(code);
    }

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写模板标识");
        return superManager.count(Wraps.<ExtendMsgTemplate>lbQ().eq(ExtendMsgTemplate::getCode, code)
                .ne(ExtendMsgTemplate::getId, id)) > 0;
    }

    @Override
    protected <SaveVO> ExtendMsgTemplate saveBefore(SaveVO saveVO) {
        ExtendMsgTemplateSaveVO extendMsgTemplateSaveVO = (ExtendMsgTemplateSaveVO) saveVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateSaveVO.getCode()) &&
                               check(extendMsgTemplateSaveVO.getCode(), null), "模板标识{}已存在", extendMsgTemplateSaveVO.getCode());

        return super.saveBefore(extendMsgTemplateSaveVO);
    }

    @Override
    protected <UpdateVO> ExtendMsgTemplate updateBefore(UpdateVO updateVO) {
        ExtendMsgTemplateUpdateVO extendMsgTemplateUpdateVO = (ExtendMsgTemplateUpdateVO) updateVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateUpdateVO.getCode()) &&
                               check(extendMsgTemplateUpdateVO.getCode(), extendMsgTemplateUpdateVO.getId()),
                "模板标识{}已存在", extendMsgTemplateUpdateVO.getCode());

        return super.updateBefore(extendMsgTemplateUpdateVO);
    }
}


