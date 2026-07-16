
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.service.component;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
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
import static org.springblade.modules.iot.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 组件配置 Service 实现类
 */
@Service
@Validated
public class ComponentServiceImpl implements ComponentService {

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
        return componentMapper.selectPage(pageReqVO);
    }

    @Override
    public ComponentInfo getComponent(String type) {
        return ComponentConvert.INSTANCE.convertInfo(componentMapper.selectOne(ComponentDO::getType, type));
    }

}