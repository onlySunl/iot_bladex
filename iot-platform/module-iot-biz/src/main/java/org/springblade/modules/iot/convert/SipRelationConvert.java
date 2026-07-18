

package org.springblade.modules.iot.convert;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelation;
import org.springblade.modules.iot.entity.SipRelationDO;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description: 设备信息
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SipRelationConvert {
    SipRelationConvert INSTANCE = Mappers.getMapper(SipRelationConvert.class);

    SipRelation convert(SipRelationDO ylSipRelationDO);

    PageResult<SipRelation> convertPage(PageResult<SipRelationDO> selectPage);

    SipRelationDO convertDO(SipRelation sipRelation);
}
