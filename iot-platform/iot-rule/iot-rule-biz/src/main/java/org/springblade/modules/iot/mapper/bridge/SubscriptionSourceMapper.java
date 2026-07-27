package org.springblade.modules.iot.mapper.bridge;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.entity.bridge.SubscriptionSource;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 数据桥接-订阅源
 * </p>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
@Mapper
public interface SubscriptionSourceMapper extends BladeMapper<SubscriptionSource> {
}
