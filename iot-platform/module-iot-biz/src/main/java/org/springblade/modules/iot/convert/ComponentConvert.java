

package org.springblade.modules.iot.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.api.component.dto.ComponentInfo;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentCreateReqVO;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentRespVO;
import org.springblade.modules.iot.controller.admin.component.vo.ComponentUpdateReqVO;
import org.springblade.modules.iot.entity.ComponentDO;

@Mapper
public interface ComponentConvert {

    ComponentConvert INSTANCE = Mappers.getMapper(ComponentConvert.class);

    ComponentDO convert(ComponentCreateReqVO bean);

    ComponentDO convert(ComponentUpdateReqVO bean);

    ComponentRespVO convert(ComponentDO bean);

    ComponentInfo convertInfo(ComponentDO bean);

    PageResult<ComponentRespVO> convertPage(PageResult<ComponentDO> page);

}