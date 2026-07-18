

package org.springblade.modules.iot.service.sip;


import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelation;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelationPageReqVO;

import java.util.List;

/**
 * 监控设备关联Service接口
 *
 */
public interface SipRelationService
{
    /**
     * 查询监控设备关联
     *
     * @param id 监控设备关联主键
     * @return 监控设备关联
     */
     SipRelation selectSipRelationById(Long id);

     List<SipRelation> selectSipRelationByDeviceId(Long deviceId);
    /**
     * 查询监控设备关联列表
     *
     * @param sipRelation 监控设备关联
     * @return 监控设备关联集合
     */
     List<SipRelation> selectSipRelationList(SipRelation sipRelation);

    /**
     * 根据channelId获取关联关系
     * @param channelId
     * @return
     */
    SipRelation selectByChannelId(String channelId);

    /**
     * 新增或者更新监控设备关联
     * @param sipRelation
     * @return
     */
    int addOrUpdateSipRelation(SipRelation sipRelation);

    /**
     * 新增监控设备关联
     *
     * @param sipRelation 监控设备关联
     * @return 结果
     */
     int insertSipRelation(SipRelation sipRelation);

    /**
     * 修改监控设备关联
     *
     * @param sipRelation 监控设备关联
     * @return 结果
     */
     int updateSipRelation(SipRelation sipRelation);

    /**
     * 批量删除监控设备关联
     *
     * @param ids 需要删除的监控设备关联主键集合
     * @return 结果
     */
     int deleteSipRelationByIds(Long[] ids);

    /**
     * 删除监控设备关联信息
     *
     * @param id 监控设备关联主键
     * @return 结果
     */
     int deleteSipRelationById(Long id);

    PageResult<SipRelation> selectSipRelationPage(SipRelationPageReqVO sipRelation);
}
