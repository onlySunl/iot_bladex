

package org.springblade.modules.iot.dal.mysql;


import org.springblade.common.query.LambdaQueryWrapperX;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.mybatis.core.mapper.BaseMapperX;
import org.springblade.modules.iot.controller.admin.device.vo.DeviceGroupPageReqVO;
import org.springblade.modules.iot.entity.GroupDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备分组 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface EiotIotGroupMapper extends BaseMapperX<GroupDO> {

    default PageResult<GroupDO> selectPage(DeviceGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupDO>()
                .likeIfPresent(GroupDO::getName, reqVO.getName())
                .eqIfPresent(GroupDO::getUid, reqVO.getUid())
                .eqIfPresent(GroupDO::getTyp, reqVO.getTyp()));
    }


}
