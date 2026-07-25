

package org.springblade.modules.iot.service.component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.api.component.dto.ComponentInfo;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentCreateReqVO;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentPageReqVO;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentUpdateReqVO;
import org.springblade.modules.iot.convert.ComponentConvert;
import org.springblade.modules.iot.entity.ComponentDO;
import org.springblade.modules.iot.dal.mysql.component.ComponentMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

import static org.springblade.modules.iot.common.enums.ErrorCodeConstants.COMPONENT_NOT_EXISTS;
import static org.springblade.modules.iot.common.utils.ServiceExceptionUtil.exception;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.ComponentDO;
import org.springblade.modules.iot.dal.mysql.component.ComponentMapper;

/**
 * 组件配置 Service 实现类
 */
@Service
@Validated
public class ComponentServiceImpl extends BaseServiceImpl<ComponentMapper, ComponentDO> implements IComponentService {

    @Resource
    private ComponentMapper componentMapper;

    @Override
    public Long createComponent(ComponentCreateReqVO createReqVO) {
        // 插入
        ComponentDO component = ComponentConvert.INSTANCE.convert(createReqVO);
        componentMapper.insert(component);
        // 返回
        return component.getId();
    }

    @Override
    public void updateComponent(ComponentUpdateReqVO updateReqVO) {
        // 校验存在
        validateComponentExists(updateReqVO.getId());
        // 更新
        ComponentDO updateObj = ComponentConvert.INSTANCE.convert(updateReqVO);
        componentMapper.updateById(updateObj);
    }

    @Override
    public void deleteComponent(Long id) {
        // 校验存在
        validateComponentExists(id);
        // 删除
        componentMapper.deleteById(id);
    }

    private void validateComponentExists(Long id) {
        if (componentMapper.selectById(id) == null) {
            throw exception(COMPONENT_NOT_EXISTS);
        }
    }

    @Override
    public ComponentDO getComponent(Long id) {
        return componentMapper.selectById(id);
    }

    @Override
    public PageResult<ComponentDO> getComponentPage(ComponentPageReqVO pageReqVO) {
        return PageResult.from(componentMapper.selectPage(new Page<ComponentDO>(pageReqVO.getPageNo(), pageReqVO.getPageSize()), pageReqVO));
    }

    @Override
    public ComponentInfo getComponent(String type) {
        return ComponentConvert.INSTANCE.convertInfo(componentMapper.selectOne(
                new LambdaQueryWrapper<ComponentDO>()
                        .eq(ComponentDO::getType, type)));
    }

}
