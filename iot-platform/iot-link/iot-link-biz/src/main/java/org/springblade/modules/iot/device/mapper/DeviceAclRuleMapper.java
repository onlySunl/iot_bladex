package org.springblade.modules.iot.device.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.device.entity.DeviceAclRule;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 设备访问控制(ACL)规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-11 19:57:46
 * @create [2025-06-11 19:57:46] [mqttsnet]
 */
@Mapper
public interface DeviceAclRuleMapper extends BladeMapper<DeviceAclRule> {

}

