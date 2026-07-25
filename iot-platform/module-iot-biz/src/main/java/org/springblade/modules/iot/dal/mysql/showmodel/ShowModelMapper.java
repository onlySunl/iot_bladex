package org.springblade.modules.iot.dal.mysql.showmodel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.ShowModelDO;
import org.springblade.modules.iot.controller.admin.showmodel.vo.ShowModelPageReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 展示模型 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface ShowModelMapper extends BladeMapper<ShowModelDO> {

    IPage<ShowModelDO> selectPage(IPage<ShowModelDO> page, @Param("reqVO") ShowModelPageReqVO reqVO);

}
