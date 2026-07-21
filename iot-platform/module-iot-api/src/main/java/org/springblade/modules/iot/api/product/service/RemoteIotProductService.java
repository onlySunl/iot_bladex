package org.springblade.modules.iot.api.product.service;

import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.product.factory.RemoteIotProductFallbackFactory;
import org.springblade.modules.iot.common.constant.IotServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IOT设备远程Feign调用接口，对齐 {@link DeviceApi} 全部能力
 *
 * @FileName RemoteIotDeviceService
 * @Description 跨服务调用iot设备相关缓存/注册/鉴权/子设备/属性配置接口
 * @Author fengcheng
 * @date 2026-03-28
 **/
@FeignClient(contextId = "remoteIotProductService",
        value = IotServiceNameConstants.IOT_PRODUCT,
        fallbackFactory = RemoteIotProductFallbackFactory.class,
        url = IotServiceNameConstants.SERVICE_URL
)
public interface RemoteIotProductService {

    /** 根据产品Key查询产品信息（数据库） */
    @GetMapping("/api/productApi/getProduct")
    Product getProduct(@RequestParam("pk") String pk);

    /** 根据产品Key从缓存查询产品信息 */
    @GetMapping("/api/productApi/getProductByPkFromCache")
    Product getProductByPkFromCache(@RequestParam("pk") String pk);
}