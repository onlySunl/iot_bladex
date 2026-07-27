package org.springblade.modules.iot.mqs.api;

import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.modules.iot.mqs.api.hystrix.MqsApiFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * mqs 远程接口
 *
 * @author xiaonannet
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:thinglinks-mqs-server}", fallback = MqsApiFallback.class, path = "/mqs")
public interface MqsApi {


}
