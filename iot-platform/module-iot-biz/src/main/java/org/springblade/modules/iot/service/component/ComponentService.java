

package org.springblade.modules.iot.service.component;

import org.springblade.modules.iot.api.component.dto.ComponentInfo;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentCreateReqVO;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentUpdateReqVO;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentPageReqVO;
import org.springblade.modules.iot.entity.ComponentDO;

import jakarta.validation.Valid;

public interface ComponentService {

    /**
     * 创建组件配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createComponent(@Valid ComponentCreateReqVO createReqVO);

    /**
     * 更新组件配置
     *
     * @param updateReqVO 更新信息
     */
    void updateComponent(@Valid ComponentUpdateReqVO updateReqVO);

    /**
     * 删除组件配置
     *
     * @param id 编号
     */
    void deleteComponent(Long id);

    /**
     * 获得组件配置
     *
     * @param id 编号
     * @return 组件配置
     */
    ComponentDO getComponent(Long id);

    /**
     * 获得组件配置分页
     *
     * @param pageReqVO 分页查询
     * @return 组件配置分页
     */
    PageResult<ComponentDO> getComponentPage(ComponentPageReqVO pageReqVO);

    /**
     * 按组件类型获取
     *
     * @param type 组件类型
     * @return 组件信息
     */
    ComponentInfo getComponent(String type);

}