package org.springblade.modules.iot.api.product.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.modules.iot.api.device.dto.*;
import org.springblade.modules.iot.api.product.dto.Product;
import org.springblade.modules.iot.api.product.service.RemoteIotProductService;
import org.springblade.modules.iot.common.entity.CommonResult;
import org.springblade.modules.iot.common.thing.ThingService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 视频监控设备服务降级处理
 *
 * @FileName RemoteQsDeviceFallbackFactory
 * @Description
 * @Author fengcheng
 * @date 2026-03-28
 **/
@Component
public class RemoteIotProductFallbackFactory implements FallbackFactory<RemoteIotProductService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteIotProductFallbackFactory.class);

    @Override
    public RemoteIotProductService create(Throwable throwable) {
        log.error("视频监控设备服务调用失败:{}", throwable.getMessage());

        return new RemoteIotProductService() {

            @Override
            public Product getProduct(String pk) {
                return null;
            }

            @Override
            public Product getProductByPkFromCache(String pk) {
                return null;
            }
        };
    }
}
