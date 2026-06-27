package org.springblade.modules.iot.protocol.qs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.modules.iot.domain.QsRegion;
import org.springblade.modules.iot.protocol.qs.mapper.QsRegionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QS区域Service
 *
 * @author BladeX
 */
@Service
public class QsRegionService extends ServiceImpl<QsRegionMapper, QsRegion> implements IService<QsRegion> {

    /**
     * 获取所有顶级区域
     */
    public List<QsRegion> listRootRegions() {
        return list(new LambdaQueryWrapper<QsRegion>()
            .isNull(QsRegion::getParentCode)
            .orderByAsc(QsRegion::getSort));
    }

    /**
     * 根据父级编码获取子区域
     */
    public List<QsRegion> listByParentCode(String parentCode) {
        return list(new LambdaQueryWrapper<QsRegion>()
            .eq(QsRegion::getParentCode, parentCode)
            .orderByAsc(QsRegion::getSort));
    }

}
