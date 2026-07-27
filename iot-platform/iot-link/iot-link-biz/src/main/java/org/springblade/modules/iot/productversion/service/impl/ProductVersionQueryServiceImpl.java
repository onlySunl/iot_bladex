package org.springblade.modules.iot.productversion.service.impl;

import java.util.Optional;

import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.productversion.entity.ProductVersion;
import org.springblade.modules.iot.productversion.service.ProductVersionQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 产品版本只读查询 Service 实现。
 *
 * <p>仅持有 {@link ProductVersionManager},零下游 Service 依赖,类图天然为 DAG。</p>
 *
 * @author mqttsnet
 * @since 2026-05-28
 */
@Slf4j
@AllArgsConstructor
@Service
public class ProductVersionQueryServiceImpl implements ProductVersionQueryService {

    private final ProductVersionManager productVersionManager;

    @Override
    public Optional<ProductVersion> findByProductIdentificationAndVersionNo(String productIdentification, String versionNo) {
        return productVersionManager.findByProductIdentificationAndVersionNo(productIdentification, versionNo);
    }
}
