package org.springblade.modules.iot.productversionchangelog.service.impl;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springblade.core.mvc.service.impl.SuperServiceImpl;
import org.springblade.common.iot.constant.DsConstant;
import org.springblade.modules.iot.productversionchangelog.entity.ProductVersionChangeLog;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductChangeTargetTypeEnum;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductVersionChangeTypeEnum;
import org.springblade.modules.iot.productversionchangelog.manager.ProductVersionChangeLogManager;
import org.springblade.modules.iot.productversionchangelog.service.ProductVersionChangeLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 产品物模型版本变更日志业务实现。
 *
 * @author mqttsnet
 * @see ProductVersionChangeLogService
 */
@DS(DsConstant.BASE_TENANT)
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductVersionChangeLogServiceImpl
    extends SuperServiceImpl<ProductVersionChangeLogManager, Long, ProductVersionChangeLog>
    implements ProductVersionChangeLogService {

    private final ProductVersionChangeLogManager productVersionChangeLogManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void record(String productIdentification, String versionNo, ProductVersionChangeTypeEnum changeType,
                       ProductChangeTargetTypeEnum targetType, String changeSummary, String changeDetailJson) {
        if (StrUtil.isBlank(productIdentification)) {
            return;
        }
        ProductVersionChangeLog row = ProductVersionChangeLog.builder()
            .productIdentification(productIdentification)
            .versionNo(versionNo)
            .changeType(changeType == null ? null : changeType.getValue())
            .targetType(targetType == null ? null : targetType.getValue())
            .changeSummary(changeSummary)
            .changeDetailJson(changeDetailJson)
            .build();
        productVersionChangeLogManager.save(row);
        log.debug("[ProductVersionChangeLog] recorded productIdentification={} versionNo={} changeType={} targetType={} summary={}",
            productIdentification, versionNo, changeType, targetType, changeSummary);
    }

    @Override
    public List<ProductVersionChangeLog> listByProductIdentification(String productIdentification) {
        return productVersionChangeLogManager.listByProductIdentification(productIdentification);
    }
}
