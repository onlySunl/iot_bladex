package org.springblade.modules.iot.mapper.bridge;

import org.springblade.core.database.mybatis.BladeMapper;
import org.springblade.modules.iot.entity.bridge.BridgeExecutionStep;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 桥接执行步骤明细
 * </p>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Repository
public interface BridgeExecutionStepMapper extends BladeMapper<BridgeExecutionStep> {
}
