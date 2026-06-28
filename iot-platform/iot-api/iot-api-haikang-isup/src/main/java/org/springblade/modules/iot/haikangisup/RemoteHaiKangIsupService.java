package org.springblade.modules.iot.haikangisup;

import org.springblade.core.launch.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 海康ISUP服务
 *
 * @author fengcheng
 */
@FeignClient(contextId = "remoteHaiKangIsupService", value = AppConstant.APPLICATION_BLADE_SYSTEM)
public interface RemoteHaiKangIsupService {

}
