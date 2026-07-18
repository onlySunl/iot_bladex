

package org.springblade.modules.iot.convert;


import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelRespVO;
import org.springblade.modules.iot.entity.ShowModelDO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 物模型 Convert
 *
 * @author EnjoyIot
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface ShowModelConvert {

    ShowModelConvert INSTANCE = Mappers.getMapper(ShowModelConvert.class);

    ShowModelRespVO convert(ShowModelDO objDO);

    List<ShowModelRespVO> convertList(List<ShowModelDO> thingModelDO);

}
