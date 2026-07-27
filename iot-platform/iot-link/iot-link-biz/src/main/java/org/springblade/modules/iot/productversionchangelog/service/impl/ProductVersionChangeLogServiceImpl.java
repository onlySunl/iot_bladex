package org.springblade.modules.iot.productversionchangelog.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductversionchangelogserviceimplProductVersionChangeLogServiceImpl.java.mapper.ProductVersionChangeLogMapper;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.productversionchangelog.entity.ProductVersionChangeLog;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductChangeTargetTypeEnum;
import org.springblade.modules.iot.productversionchangelog.enumeration.ProductVersionChangeTypeEnum;
import org.springblade.modules.iot.productversionchangelog.service.ProductVersionChangeLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 浜у搧鐗╂ā鍨嬬増鏈彉鏇存棩蹇椾笟鍔″疄鐜般€?
 *
 * @author mqttsnet
 * @see ProductVersionChangeLogService
 */
@Slf4j
@AllArgsConstructor
@Service
public class ProductVersionChangeLogServiceImpl
    extends BaseServiceImpl<ProductVersionChangeLogMapper, ProductVersionChangeLog>
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
