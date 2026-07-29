package org.springblade.modules.iot.bus.hook;

import org.springblade.modules.iot.bus.stage.DeviceEventStage;
import org.springblade.modules.iot.entity.protocol.DeviceProtocolEvent;

/**
 * Stage 守卫 SPI,stage 执行前判断是否放行(灰度 / 租户级开关 / 维护模式)。
 * 多 guard 全部 AND,任一返 false 即跳过。
 *
 * @author mqttsnet
 * @since 2026-05-10
 */
public interface StageGuard {

    boolean shouldExecute(DeviceEventStage stage, DeviceProtocolEvent event);
}
