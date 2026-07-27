package org.springblade.modules.iot.ota.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTasks;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * OTA升级任务表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:40:04
 * @create [2024-01-12 22:40:04] [mqttsnet]
 */
@Mapper
public interface OtaUpgradeTasksMapper extends BladeMapper<OtaUpgradeTasks> {

}

