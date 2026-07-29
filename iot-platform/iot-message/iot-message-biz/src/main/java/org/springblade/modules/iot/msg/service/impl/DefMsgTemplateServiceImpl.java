package org.springblade.modules.iot.msg.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.mqttsnet.basic.base.service.impl.SuperServiceImpl;
import com.mqttsnet.basic.database.mybatis.conditions.Wraps;
import com.mqttsnet.basic.jackson.JsonUtil;
import com.mqttsnet.basic.model.Kv;
import com.mqttsnet.basic.utils.ArgumentAssert;
import com.mqttsnet.basic.utils.StrPool;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.msg.entity.DefMsgTemplate;
import org.springblade.modules.iot.msg.manager.DefMsgTemplateManager;
import org.springblade.modules.iot.msg.service.DefMsgTemplateService;
import org.springblade.modules.iot.msg.vo.save.DefMsgTemplateSaveVO;
import org.springblade.modules.iot.msg.vo.update.DefMsgTemplateUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@DS(DsConstant.DEFAULTS)
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefMsgTemplateServiceImpl extends SuperServiceImpl<DefMsgTemplateManager, Long, DefMsgTemplate> implements DefMsgTemplateService {
    /**
     * 解析占位符 ${xxx}
     */
    private static final Pattern REG_EX = Pattern.compile("\\$\\{([^}]+)}");

    private static String getParamByContent(String title, String content) {


        // 查找字符串中是否有匹配正则表达式的字符/字符串//有序， 目的是为了兼容 腾讯云参数
        Set<Kv> list = new LinkedHashSet<>();
        if (StrUtil.isNotEmpty(title)) {
            //编译正则表达式
            //忽略大小写的写法:
            Matcher matcher = REG_EX.matcher(title);
            while (matcher.find()) {
                String key = matcher.group(1);
                list.add(Kv.builder().key(key).value(StrPool.EMPTY).build());
            }
        }

        if (StrUtil.isNotEmpty(content)) {
            //编译正则表达式
            //忽略大小写的写法:
            Matcher matcher = REG_EX.matcher(content);
            while (matcher.find()) {
                String key = matcher.group(1);
                list.add(Kv.builder().key(key).value(StrPool.EMPTY).build());
            }
        }

        return JsonUtil.toJson(list);
    }

    @Override
    public DefMsgTemplate getByCode(String code) {
        return superManager.getByCode(code);
    }

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写模板标识");
        return superManager.count(Wraps.<DefMsgTemplate>lbQ().eq(DefMsgTemplate::getCode, code)
                .ne(DefMsgTemplate::getId, id)) > 0;
    }

    @Override
    protected <SaveVO> DefMsgTemplate saveBefore(SaveVO saveVO) {
        DefMsgTemplateSaveVO extendMsgTemplateSaveVO = (DefMsgTemplateSaveVO) saveVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateSaveVO.getCode()) &&
                               check(extendMsgTemplateSaveVO.getCode(), null), "模板标识{}已存在", extendMsgTemplateSaveVO.getCode());
        extendMsgTemplateSaveVO.setParam(getParamByContent(extendMsgTemplateSaveVO.getTitle(), extendMsgTemplateSaveVO.getContent()));
        return super.saveBefore(extendMsgTemplateSaveVO);
    }

    @Override
    protected <UpdateVO> DefMsgTemplate updateBefore(UpdateVO updateVO) {
        DefMsgTemplateUpdateVO extendMsgTemplateUpdateVO = (DefMsgTemplateUpdateVO) updateVO;
        ArgumentAssert.isFalse(StrUtil.isNotBlank(extendMsgTemplateUpdateVO.getCode()) &&
                               check(extendMsgTemplateUpdateVO.getCode(), extendMsgTemplateUpdateVO.getId()),
                "模板标识{}已存在", extendMsgTemplateUpdateVO.getCode());
        extendMsgTemplateUpdateVO.setParam(getParamByContent(extendMsgTemplateUpdateVO.getTitle(), extendMsgTemplateUpdateVO.getContent()));
        return super.updateBefore(extendMsgTemplateUpdateVO);
    }
}


