package org.springblade.modules.iot.system.api;

import com.mqttsnet.basic.base.R;
import com.mqttsnet.basic.constant.Constants;
import org.springblade.modules.iot.system.entity.tenant.DefTenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 租户
 *
 * @author mqttsnet
 * @date 2023/07/02
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:thinglinks-system-server}", path = "/defTenant")
public interface DefTenantApi {


    @GetMapping(value = "/all")
    R<List<DefTenant>> findAllTenant();

}
