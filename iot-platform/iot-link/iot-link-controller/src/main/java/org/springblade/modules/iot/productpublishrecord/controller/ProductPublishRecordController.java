package org.springblade.modules.iot.productpublishrecord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mvc.controller.BaseController;
import org.springblade.common.interfaces.echo.EchoService;
import org.springblade.modules.iot.productpublishrecord.entity.ProductPublishRecord;
import org.springblade.modules.iot.productpublishrecord.service.ProductPublishRecordService;
import org.springblade.modules.iot.productpublishrecord.vo.query.ProductPublishRecordPageQuery;
import org.springblade.modules.iot.productpublishrecord.vo.result.ProductPublishRecordResultVO;
import org.springblade.modules.iot.productpublishrecord.vo.save.ProductPublishRecordSaveVO;
import org.springblade.modules.iot.productpublishrecord.vo.update.ProductPublishRecordUpdateVO;
import org.springblade.modules.iot.productversion.entity.ProductVersion;
import org.springblade.modules.iot.productversion.service.ProductVersionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品发布记录前端控制器。写入由 publish / rollback / purgeHistory 流程自动产生,不开放外部 save。
 *
 * @author mqttsnet
 * @see ProductPublishRecordService
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/productPublishRecord")
@Tag(name = "产品发布记录")
public class ProductPublishRecordController extends BaseController<ProductPublishRecordService, Long,
        ProductPublishRecord, ProductPublishRecordSaveVO, ProductPublishRecordUpdateVO,
        ProductPublishRecordPageQuery, ProductPublishRecordResultVO> {

    private final EchoService echoService;
    /**
     * 跨域读 product_version 富化策略字段 ── 走 Service 边界(@DS 切租户库),
     * 不直接调 Manager(满足"禁止跨层级")。
     */
    private final ProductVersionService productVersionService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    /**
     * 富化每一页发布记录的 publishStrategy / canaryConfigJson 字段:策略存在 product_version 表
     * (发布动作写到 targetVersion 那一行),本表不冗余,这里回包前按 (productIdentification, targetVersion) 批量反查填充,
     * 再触发 EchoService 处理 @Echo 字典回显。
     */
    @Override
    public void handlerResult(IPage<ProductPublishRecordResultVO> page) {
        List<ProductPublishRecordResultVO> records = page.getRecords();
        if (records != null && !records.isEmpty()) {
            enrichWithStrategy(records);
        }
        // 主动触发 @Echo 字典回显(等价 PageController 默认实现 ── 内联避免 default method super 调用复杂度)
        if (echoService != null) {
            echoService.action(page);
        }
    }

    /**
     * 按 productIdentification 分组批量反查 product_version,把策略 / 灰度配置回填到 record 上。
     */
    private void enrichWithStrategy(List<ProductPublishRecordResultVO> records) {
        // 按 productIdentification 分组,每组只查一次版本列表
        Map<String, List<ProductPublishRecordResultVO>> byProduct = records.stream()
                .filter(r -> StrUtil.isNotBlank(r.getProductIdentification())
                        && StrUtil.isNotBlank(r.getTargetVersion()))
                .collect(Collectors.groupingBy(ProductPublishRecordResultVO::getProductIdentification));

        for (Map.Entry<String, List<ProductPublishRecordResultVO>> entry : byProduct.entrySet()) {
            String productIdentification = entry.getKey();
            List<ProductVersion> versions;
            try {
                versions = productVersionService.listByProductIdentification(productIdentification);
            } catch (Exception e) {
                log.warn("[publish-record] enrichStrategy failed productIdentification={}",
                        productIdentification, e);
                continue;
            }
            if (versions == null || versions.isEmpty()) {
                continue;
            }
            Map<String, ProductVersion> byVersionNo = versions.stream()
                    .filter(v -> StrUtil.isNotBlank(v.getVersionNo()))
                    .collect(Collectors.toMap(
                            ProductVersion::getVersionNo,
                            v -> v,
                            (a, b) -> a));

            for (ProductPublishRecordResultVO record : entry.getValue()) {
                ProductVersion v = byVersionNo.get(record.getTargetVersion());
                Optional.ofNullable(v).ifPresent(ver -> {
                    record.setPublishStrategy(ver.getPublishStrategy());
                    record.setCanaryConfigJson(ver.getCanaryConfigJson());
                });
            }
        }

        // 兜底:无 productIdentification / targetVersion 的脏数据保留原样(null 策略)
        Objects.requireNonNull(records, "records must not be null after enrich");
    }
}
