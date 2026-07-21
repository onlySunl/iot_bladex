package org.springblade.modules.iot.api.rule.service;

import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.rule.dto.RuleInfo;
import org.springblade.modules.iot.api.rule.dto.RuleInfoPageReqVO;
import org.springblade.modules.iot.api.rule.factory.RemoteIotRuleFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IOT设备远程Feign调用接口，对齐 {@link DeviceApi} 全部能力
 *
 * @FileName RemoteIotDeviceService
 * @Description 跨服务调用iot设备相关缓存/注册/鉴权/子设备/属性配置接口
 * @Author fengcheng
 * @date 2026-03-28
 **/
@FeignClient(contextId = "remoteIotRuleService",
        value = IotServiceNameConstants.IOT_RULE,
        fallbackFactory = RemoteIotRuleFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotRuleService {
    /**
     * 分页查询规则信息
     */
    @PostMapping("/api/ruleApi/selectPage")
    PageResult<RuleInfo> selectPage(@RequestBody RuleInfoPageReqVO reqVO);
}