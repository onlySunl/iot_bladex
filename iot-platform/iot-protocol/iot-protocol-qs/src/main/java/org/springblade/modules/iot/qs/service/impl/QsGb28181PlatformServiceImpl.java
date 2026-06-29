package org.springblade.modules.iot.qs.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springblade.core.mp.service.impl.BladeServiceImpl;
import org.springblade.modules.iot.domain.QsGb28181Platform;
import org.springblade.modules.iot.qs.mapper.QsGb28181PlatformMapper;
import org.springblade.modules.iot.qs.service.Gb28181PlatformSyncService;
import org.springblade.modules.iot.qs.service.IQsGb28181PlatformService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 国标GB28181平台配置Service业务层处理
 *
 * @author ruoyi
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QsGb28181PlatformServiceImpl extends BladeServiceImpl<QsGb28181PlatformMapper, QsGb28181Platform> implements IQsGb28181PlatformService {

    private final QsGb28181PlatformMapper qsGb28181PlatformMapper;
    private final Gb28181PlatformSyncService gb28181PlatformSyncService;

    /**
     * 查询国标GB28181平台配置列表
     *
     * @param qsGb28181Platform 国标GB28181平台配置
     * @return 国标GB28181平台配置
     */
    @Override
    public List<QsGb28181Platform> selectQsGb28181PlatformList(QsGb28181Platform qsGb28181Platform) {
        return qsGb28181PlatformMapper.selectQsGb28181PlatformList(qsGb28181Platform);
    }

    /**
     * 查询国标GB28181平台配置
     *
     * @param id 国标GB28181平台配置主键
     * @return 国标GB28181平台配置
     */
    @Override
    public QsGb28181Platform selectQsGb28181PlatformById(Long id) {
        return qsGb28181PlatformMapper.selectQsGb28181PlatformById(id);
    }

    /**
     * 新增国标GB28181平台配置
     *
     * @param qsGb28181Platform 国标GB28181平台配置
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertQsGb28181Platform(QsGb28181Platform qsGb28181Platform) {
        Assert.hasText(qsGb28181Platform.getName(), "平台名称不能为空");
        Assert.hasText(qsGb28181Platform.getServerGbId(), "平台国标编码不能为空");

        QsGb28181Platform existing = qsGb28181PlatformMapper.selectQsGb28181PlatformByServerGbId(qsGb28181Platform.getServerGbId());
        Assert.isNull(existing, "平台国标编码已存在");

        if (qsGb28181Platform.getEnable() == null) {
            qsGb28181Platform.setEnable(0);
        }
        if (qsGb28181Platform.getStatus() == null) {
            qsGb28181Platform.setStatus(0);
        }

        return qsGb28181PlatformMapper.insertQsGb28181Platform(qsGb28181Platform);
    }

    /**
     * 修改国标GB28181平台配置
     *
     * @param qsGb28181Platform 国标GB28181平台配置
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateQsGb28181Platform(QsGb28181Platform qsGb28181Platform) {
        Assert.notNull(qsGb28181Platform.getId(), "ID不能为空");

        QsGb28181Platform existing = qsGb28181PlatformMapper.selectQsGb28181PlatformById(qsGb28181Platform.getId());
        Assert.notNull(existing, "平台配置不存在");

        if (StringUtils.isNotBlank(qsGb28181Platform.getServerGbId()) && !qsGb28181Platform.getServerGbId().equals(existing.getServerGbId())) {
            QsGb28181Platform byServerGbId = qsGb28181PlatformMapper.selectQsGb28181PlatformByServerGbId(qsGb28181Platform.getServerGbId());
            Assert.isNull(byServerGbId, "平台国标编码已存在");
        }

        int result = qsGb28181PlatformMapper.updateQsGb28181Platform(qsGb28181Platform);
        // 异步推送目录到所有在线平台（带防抖）
        gb28181PlatformSyncService.triggerPushCatalog();
        return result;
    }

    /**
     * 批量删除国标GB28181平台配置
     *
     * @param ids 需要删除的国标GB28181平台配置主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteQsGb28181PlatformByIds(Long[] ids) {
        Assert.notEmpty(ids, "ID不能为空");
        return qsGb28181PlatformMapper.deleteQsGb28181PlatformByIds(ids);
    }

    /**
     * 删除国标GB28181平台配置信息
     *
     * @param id 国标GB28181平台配置主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteQsGb28181PlatformById(Long id) {
        Assert.notNull(id, "ID不能为空");
        return qsGb28181PlatformMapper.deleteQsGb28181PlatformById(id);
    }
}
