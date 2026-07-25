

package org.springblade.modules.iot.service.sip;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelation;
import org.springblade.modules.iot.controller.admin.sip.vo.SipRelationPageReqVO;
import org.springblade.modules.iot.convert.SipRelationConvert;
import org.springblade.modules.iot.entity.SipRelationDO;
import org.springblade.modules.iot.dal.mysql.sip.SipRelationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.SipRelationDO;
import org.springblade.modules.iot.dal.mysql.sip.SipRelationMapper;

/**
 * 监控设备关联Service业务层处理

 */
@Service
public class SipRelationServiceImpl extends BaseServiceImpl<SipRelationMapper, SipRelationDO> implements ISipRelationService
{
    @Resource
    private SipRelationMapper sipRelationMapper;

    /**
     * 查询监控设备关联
     *
     * @param id 监控设备关联主键
     * @return 监控设备关联
     */
    @Override
    public SipRelation selectSipRelationById(Long id)
    {
        return SipRelationConvert.INSTANCE.convert(sipRelationMapper.selectById(id));
    }

    @Override
    public List<SipRelation> selectSipRelationByDeviceId(Long deviceId) {
        SipRelation sipRelation = new SipRelation();
        sipRelation.setReDeviceId(deviceId);
        return sipRelationMapper.selectSipRelationList(sipRelation);
    }

    /**
     * 查询监控设备关联列表
     *
     * @param sipRelation 监控设备关联
     * @return 监控设备关联
     */
    @Override
    public List<SipRelation> selectSipRelationList(SipRelation sipRelation)
    {
        return sipRelationMapper.selectSipRelationList(sipRelation);
    }

    /**
     * 根据channelId获取关联关系
     * @param channelId
     * @return
     */
    @Override
     public SipRelation selectByChannelId(String channelId){
        return SipRelationConvert.INSTANCE.convert(sipRelationMapper.selectByChannelId(channelId));
     }

    /**
     * 新增或者更新监控设备关联
     * @param sipRelation
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addOrUpdateSipRelation(SipRelation sipRelation){
        String channelId = sipRelation.getChannelId();
        assert !Objects.isNull(channelId) : "channelId is null";
        SipRelationDO selectObj = sipRelationMapper.selectByChannelId(channelId);
        if (Objects.isNull(selectObj)){
            //新增

            return this.insertSipRelation(sipRelation);
        }else {

            return sipRelationMapper.updateByChannelId(sipRelation);
        }
    }

    /**
     * 新增监控设备关联
     *
     * @param sipRelation 监控设备关联
     * @return 结果
     */
    @Override
    public int insertSipRelation(SipRelation sipRelation)
    {
        SipRelationDO convert = SipRelationConvert.INSTANCE.convertDO(sipRelation);
        return sipRelationMapper.insert(convert);
    }

    /**
     * 修改监控设备关联
     *
     * @param sipRelation 监控设备关联
     * @return 结果
     */
    @Override
    public int updateSipRelation(SipRelation sipRelation)
    {
        SipRelationDO convert = SipRelationConvert.INSTANCE.convertDO(sipRelation);

        return sipRelationMapper.updateById(convert);
    }


    /**
     * 批量删除监控设备关联
     *
     * @param ids 需要删除的监控设备关联主键
     * @return 结果
     */
    @Override
    public int deleteSipRelationByIds(Long[] ids)
    {
        return sipRelationMapper.deleteByIds(Arrays.asList(ids));
    }

    /**
     * 删除监控设备关联信息
     *
     * @param id 监控设备关联主键
     * @return 结果
     */
    @Override
    public int deleteSipRelationById(Long id)
    {
        return sipRelationMapper.deleteById(id);
    }

    @Override
    public PageResult<SipRelation> selectSipRelationPage(SipRelationPageReqVO sipRelation) {
        return PageResult.from(sipRelationMapper.selectPage(new Page<SipRelation>(sipRelation.getPageNo(), sipRelation.getPageSize()), sipRelation));
    }
}
