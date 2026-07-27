package org.springblade.modules.iot.mapper.plugin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.entity.plugin.PluginInstanceMapping;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 插件与实例及端口管理表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-27 16:30:09
 * @create [2024-08-27 16:30:09] [mqttsnet]
 */
@Mapper
public interface PluginInstanceMappingMapper extends BladeMapper<PluginInstanceMapping> {

}


