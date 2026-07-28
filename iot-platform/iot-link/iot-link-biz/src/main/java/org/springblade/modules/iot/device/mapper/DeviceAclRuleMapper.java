package org.springblade.modules.iot.device.mapper;

import org.springblade.core.database.mybatis.BladeMapper;
import org.springblade.modules.iot.device.entity.DeviceAclRule;
import org.springframework.stereotype.Repository;

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
@Repository
public interface DeviceAclRuleMapper extends BladeMapper<DeviceAclRule> {

}


