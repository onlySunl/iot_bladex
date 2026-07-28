package org.springblade.modules.iot.manager.plugin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.basic.base.manager.impl.SuperManagerImpl;
import org.springblade.basic.database.mybatis.conditions.query.QueryWrap;
import org.springblade.basic.utils.StringUtils;
import org.springblade.modules.iot.entity.plugin.PluginInfo;
import org.springblade.modules.iot.manager.plugin.PluginInfoManager;
import org.springblade.modules.iot.mapper.plugin.PluginInfoMapper;
import org.springblade.modules.iot.vo.query.plugin.PluginInfoPageQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 插件信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-25 19:05:11
 * @create [2024-08-25 19:05:11] [mqttsnet]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PluginInfoManagerImpl extends SuperManagerImpl<PluginInfoMapper, PluginInfo> implements PluginInfoManager {

    private final PluginInfoMapper pluginInfoMapper;


    @Override
    public PluginInfo findByPluginIdentification(String pluginIdentification) {
        QueryWrap<PluginInfo> queryWrap = new QueryWrap<>();
        queryWrap.lambda().eq(StringUtils.isNotBlank(pluginIdentification), PluginInfo::getPluginIdentification, pluginIdentification);
        return pluginInfoMapper.selectOne(queryWrap);
    }

    /**
     * 查询插件信息列表
     *
     * @param query 查询条件
     * @return 插件信息列表
     */
    public List<PluginInfo> getPluginInfoList(PluginInfoPageQuery query) {
        QueryWrapper<PluginInfo> queryWrapper = new QueryWrapper<>();

        // 拼接查询条件
        queryWrapper.lambda().eq(query.getId() != null, PluginInfo::getId, query.getId());
        queryWrapper.lambda().eq(query.getAppId() != null, PluginInfo::getAppId, query.getAppId());
        queryWrapper.lambda().eq(query.getPluginIdentification() != null, PluginInfo::getPluginIdentification, query.getPluginIdentification());
        queryWrapper.lambda().eq(query.getPluginCode() != null, PluginInfo::getPluginCode, query.getPluginCode());
        queryWrapper.lambda().like(query.getPluginName() != null, PluginInfo::getPluginName, query.getPluginName());
        queryWrapper.lambda().eq(query.getStatus() != null, PluginInfo::getStatus, query.getStatus());
        queryWrapper.lambda().eq(query.getLevel() != null, PluginInfo::getLevel, query.getLevel());
        queryWrapper.lambda().eq(query.getType() != null, PluginInfo::getType, query.getType());
        queryWrapper.lambda().eq(query.getRunMode() != null, PluginInfo::getRunMode, query.getRunMode());

        return pluginInfoMapper.selectList(queryWrapper);
    }
}


