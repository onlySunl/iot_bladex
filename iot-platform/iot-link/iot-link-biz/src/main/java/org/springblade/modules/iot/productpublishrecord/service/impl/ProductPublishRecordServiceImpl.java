package org.springblade.modules.iot.productpublishrecord.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductpublishrecordserviceimplProductPublishRecordServiceImpl.java.mapper.ProductPublishRecordMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.productpublishrecord.entity.ProductPublishRecord;
import org.springblade.modules.iot.productpublishrecord.enumeration.ProductPublishRecordIntentEnum;
import org.springblade.modules.iot.productpublishrecord.enumeration.ProductPublishRecordStatusEnum;
import org.springblade.modules.iot.productpublishrecord.service.ProductPublishRecordService;
import org.springblade.modules.iot.productpublishrecord.vo.ddl.PublishDdlItemVO;
import org.springblade.modules.iot.productpublishrecord.vo.result.StrategyResultDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 浜у搧鍙戝竷璁板綍涓氬姟瀹炵幇銆?
 *
 * @author mqttsnet
 * @see ProductPublishRecordService
 */
@Slf4j
@AllArgsConstructor
@Service
public class ProductPublishRecordServiceImpl
    extends BaseServiceImpl<ProductPublishRecordMapper, ProductPublishRecord>
    implements ProductPublishRecordService {

    private final ProductPublishRecordManager productPublishRecordManager;

    @Override
    public ProductPublishRecord recordPublish(String productIdentification, String sourceVersion, String targetVersion,
                                              Integer maxRetryCount) {
        return persist(productIdentification, sourceVersion, targetVersion,
            ProductPublishRecordIntentEnum.PUBLISH.getValue(),
            ProductPublishRecordStatusEnum.RUNNING.getValue(), maxRetryCount);
    }

    @Override
    public ProductPublishRecord recordRollback(String productIdentification, String sourceVersion, String targetVersion) {
        // 鍥炴粴涓嶆彁渚涚敤鎴烽厤缃叆鍙?maxRetryCount 浼?null 璧?DB 榛樿 3
        return persist(productIdentification, sourceVersion, targetVersion,
            ProductPublishRecordIntentEnum.ROLLBACK.getValue(),
            ProductPublishRecordStatusEnum.RUNNING.getValue(), null);
    }

    @Override
    public ProductPublishRecord recordPurge(String productIdentification, String version) {
        // 鍘嗗彶娓呯悊涓嶆彁渚涚敤鎴烽厤缃叆鍙?maxRetryCount 浼?null 璧?DB 榛樿 3
        return persist(productIdentification, version, version,
            ProductPublishRecordIntentEnum.PURGE_HISTORY.getValue(),
            ProductPublishRecordStatusEnum.RUNNING.getValue(), null);
    }

    @Override
    public void markFailed(Long recordId, String failedReason) {
        Optional.ofNullable(productPublishRecordManager.getById(recordId))
            .ifPresent(record -> {
                record.setStatus(ProductPublishRecordStatusEnum.FAILED.getValue());
                record.setFailedReason(failedReason);
                record.setFinishedTime(LocalDateTime.now());
                productPublishRecordManager.updateById(record);
            });
    }

    @Override
    public void markSuccess(Long recordId) {
        Optional.ofNullable(productPublishRecordManager.getById(recordId))
            .ifPresent(record -> {
                record.setStatus(ProductPublishRecordStatusEnum.SUCCESS.getValue());
                record.setFinishedTime(LocalDateTime.now());
                productPublishRecordManager.updateById(record);
            });
    }

    @Override
    public void incrementRetryCount(Long recordId) {
        productPublishRecordManager.incrementRetryCount(recordId);
    }

    @Override
    public List<ProductPublishRecord> listByStatusSince(Integer status, LocalDateTime sinceTime, int limit) {
        return productPublishRecordManager.listByStatusSince(status, sinceTime, limit);
    }

    @Override
    public Long countSuccessfulPublishesInLastDays(int sinceDays) {
        return productPublishRecordManager.countSuccessfulPublishesInLastDays(sinceDays);
    }

    @Override
    public void attachDdlItems(Long recordId, List<PublishDdlItemVO> items) {
        Optional.ofNullable(productPublishRecordManager.getById(recordId))
            .ifPresent(record -> {
                record.setDdlItems(items);
                productPublishRecordManager.updateById(record);
            });
    }

    @Override
    public void attachStrategyResult(Long recordId, StrategyResultDTO result) {
        if (result == null) {
            return;
        }
        Optional.ofNullable(productPublishRecordManager.getById(recordId))
            .ifPresent(record -> {
                record.setCanaryResult(result);
                productPublishRecordManager.updateById(record);
            });
    }

    @Override
    public void attachRemark(Long recordId, String remark) {
        Optional.ofNullable(productPublishRecordManager.getById(recordId))
            .ifPresent(record -> {
                record.setRemark(remark);
                productPublishRecordManager.updateById(record);
            });
    }

    private ProductPublishRecord persist(String productIdentification, String sourceVersion,
                                         String targetVersion, Integer intent, Integer status,
                                         Integer maxRetryCount) {
        // maxRetryCount=null 鏃朵笉鍏?builder 鈫?MP insert-strategy NOT_NULL 璺宠繃璇ュ垪 鈫?DB 榛樿 3 鐢熸晥
        ProductPublishRecord record = ProductPublishRecord.builder()
            .productIdentification(productIdentification)
            .sourceVersion(sourceVersion)
            .targetVersion(targetVersion)
            .intent(intent)
            .status(status)
            .maxRetryCount(maxRetryCount)
            .startedTime(LocalDateTime.now())
            .build();
        productPublishRecordManager.save(record);
        return record;
    }
}
