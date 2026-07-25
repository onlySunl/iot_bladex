package org.springblade.modules.iot.dal.mysql.sip;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelation;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelationPageReqVO;
import org.springblade.modules.iot.entity.SipRelationDO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 监控设备关联 Mapper
 *
 * @author EnjoyIot
 */
@Mapper
public interface SipRelationMapper extends BladeMapper<SipRelationDO> {

    List<SipRelation> selectSipRelationList(@Param("sipRelation") SipRelation sipRelation);

    IPage<SipRelation> selectPage(IPage<SipRelation> page, @Param("reqVO") SipRelationPageReqVO reqVO);

    SipRelationDO selectByChannelId(@Param("channelId") String channelId);

    int updateByChannelId(@Param("sipRelation") SipRelation sipRelation);
}
