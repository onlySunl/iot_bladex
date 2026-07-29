package org.springblade.modules.iot.device.service;

import java.util.List;

import org.springblade.core.mvc.service.SuperService;
import org.springblade.modules.iot.cache.vo.device.DeviceAclRuleCacheVO;
import org.springblade.modules.iot.device.entity.DeviceAclRule;
import org.springblade.modules.iot.device.vo.query.DeviceAclCheckQuery;
import org.springblade.modules.iot.protocol.vo.result.DeviceAclCheckResultVO;
import jakarta.validation.Valid;


/**
 * <p>
 * 业务接口
 * 设备访问控制(ACL)规则表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-06-11 19:57:46
 * @create [2025-06-11 19:57:46] [mqttsnet]
 */
public interface DeviceAclRuleService extends SuperService<Long, DeviceAclRule> {


    /**
     * Checks the ACL (Access Control List) permission for a given device.
     *
     * @param deviceAclCheckQuery The query object containing the device ID and the user ID for which the ACL permission needs to be checked.
     * @return {@link DeviceAclCheckResultVO} The result of the ACL permission check.
     */
    DeviceAclCheckResultVO checkAclPermission(@Valid DeviceAclCheckQuery deviceAclCheckQuery);


    /**
     * 拉指定设备生效的全部 ACL 规则(产品级 + 设备级)
     *
     * @param productIdentification 产品标识
     * @param deviceIdentificationr 设备标识
     * @return 排序后的规则列表;设备未注册 / 无规则返空 List
     */
    List<DeviceAclRuleCacheVO> getDeviceAclRuleCacheVOList(String productIdentification, String deviceIdentificationr);
}


