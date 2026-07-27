package org.springblade.modules.iot.mapper.bridge;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.entity.bridge.BridgeExecutionStep;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 桥接执行步骤明细
 * </p>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Mapper
public interface BridgeExecutionStepMapper extends BladeMapper<BridgeExecutionStep> {
}
