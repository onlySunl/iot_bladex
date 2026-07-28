package org.springblade.modules.iot.device.manager;

import org.springblade.core.database.mybatis.BladeService;
import org.springblade.modules.iot.device.entity.DeviceAclRule;

/**
 * 设备访问控制(ACL)规则 ── 数据访问层。
 * 缓存逻辑全部下沉到 LinkCacheDataHelper + DeviceAclRuleCacheService。
 */
public interface DeviceAclRuleManager extends BladeService<DeviceAclRule> {
}
