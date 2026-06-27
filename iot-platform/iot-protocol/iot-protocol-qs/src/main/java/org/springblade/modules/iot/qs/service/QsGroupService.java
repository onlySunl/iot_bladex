package org.springblade.modules.iot.qs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.QsGroup;
import org.springblade.modules.iot.qs.mapper.QsGroupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QS分组Service
 *
 * @author BladeX
 */
@Service
public class QsGroupService extends ServiceImpl<QsGroupMapper, QsGroup> implements IService<QsGroup> {

    /**
     * 根据区域编码获取分组
     */
    public List<QsGroup> listByRegionCode(String regionCode) {
        return list(new LambdaQueryWrapper<QsGroup>()
            .eq(QsGroup::getRegionCode, regionCode)
            .orderByAsc(QsGroup::getSort));
    }

}
