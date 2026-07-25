package org.springblade.modules.iot.dal.mysql.alertconfig;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.entity.AlertConfigDO;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 报警配置 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface AlertConfigMapper extends BladeMapper<AlertConfigDO> {

    IPage<AlertConfigDO> selectPage(IPage<AlertConfigDO> page, @Param("reqVO") AlertConfigPageReqVO reqVO);

    Long selectCountByChannelTemplateId(@Param("templateId") Long templateId);

}
