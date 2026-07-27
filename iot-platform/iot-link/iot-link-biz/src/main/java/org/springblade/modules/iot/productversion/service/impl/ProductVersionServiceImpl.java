package org.springblade.modules.iot.productversion.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotproductversionserviceimplProductVersionServiceImpl.java.mapper.ProductVersionMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.product.service.ProductQueryService;
import org.springblade.modules.iot.product.service.ProductService;
import org.springblade.modules.iot.product.vo.param.ProductParamVO;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.modules.iot.productpublishrecord.entity.ProductPublishRecord;
import org.springblade.modules.iot.productpublishrecord.service.ProductPublishRecordService;
import org.springblade.modules.iot.productversion.converter.ProductSnapshotConverter;
import org.springblade.modules.iot.productversion.diff.ProductSnapshotDiffCalculator;
import org.springblade.modules.iot.productversion.entity.ProductVersion;
import org.springblade.modules.iot.productversion.enumeration.ProductPublishStrategyEnum;
import org.springblade.modules.iot.productversion.enumeration.ProductVersionStatusEnum;
import org.springblade.modules.iot.productversion.event.publisher.ProductVersionEventPublisher;
import org.springblade.modules.iot.productversion.event.source.ProductVersionLifecycleEventSource;
import org.springblade.modules.iot.productversion.service.ProductVersionService;
import org.springblade.modules.iot.productversion.vo.diff.ProductVersionDiffSummaryVO;
import org.springblade.modules.iot.productversion.vo.diff.ProductVersionDiffVO;
import org.springblade.modules.iot.productversion.vo.result.ProductVersionStatisticsResultVO;
import org.springblade.modules.iot.productversion.vo.save.ProductVersionPublishVO;
import org.springblade.modules.iot.productversion.vo.save.ProductVersionPurgeVO;
import org.springblade.modules.iot.productversion.vo.save.ProductVersionRollbackVO;
import org.springblade.modules.iot.productversion.vo.snapshot.ProductSnapshotVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 浜у搧鐗╂ā鍨嬬増鏈笟鍔″疄鐜般€?
 *
 * @author mqttsnet
 * @see ProductVersionService
 */
@Slf4j
@Service
public class ProductVersionServiceImpl
    extends BaseServiceImpl<ProductVersionMapper, ProductVersion>
    implements ProductVersionService {

    private final ProductVersionManager productVersionManager;
    private final ProductQueryService productQueryService;
    /**
     * 璺ㄥ煙浜у搧鍐欏叆(鍒囨崲 active_version_no 鎸囬拡)璧?Service 鑰岄潪鐩存帴璋?ProductManager,瑙﹀彂 @DS(BASE_TENANT)
     * 鍒囩鎴峰簱 + 婊¤冻"绂佹璺ㄥ眰绾ц皟鐢?銆侤Lazy 蹇呰鎬?ProductServiceImpl 宸蹭緷璧?ProductVersionService
     * (浜у搧 CRUD 鍒疯崏绋?+ 鍒犻櫎绾ц仈杞垹),鍙嶅悜鐩存帴娉ㄥ叆浼氬舰鎴愭瀯閫犳湡寰幆渚濊禆,@Lazy 娉ㄥ叆浠ｇ悊鎸夐渶鍒涘缓鐪熷疄 bean銆?
     * (鏈被涓嶈兘鐢?@AllArgsConstructor 鈹€鈹€ Lombok 榛樿涓嶅鍒?@Lazy 鍒版瀯閫犲櫒鍙傛暟銆?
     */
    private final ProductService productService;
    private final ProductPublishRecordService productPublishRecordService;
    private final ProductSnapshotConverter productSnapshotConverter;
    private final ProductSnapshotDiffCalculator productSnapshotDiffCalculator;
    private final ProductVersionEventPublisher productVersionEventPublisher;

    @Autowired
    public ProductVersionServiceImpl(ProductVersionManager productVersionManager,
                                     ProductQueryService productQueryService,
                                     @Lazy ProductService productService,
                                     ProductPublishRecordService productPublishRecordService,
                                     ProductSnapshotConverter productSnapshotConverter,
                                     ProductSnapshotDiffCalculator productSnapshotDiffCalculator,
                                     ProductVersionEventPublisher productVersionEventPublisher) {
        this.productVersionManager = productVersionManager;
        this.productQueryService = productQueryService;
        this.productService = productService;
        this.productPublishRecordService = productPublishRecordService;
        this.productSnapshotConverter = productSnapshotConverter;
        this.productSnapshotDiffCalculator = productSnapshotDiffCalculator;
        this.productVersionEventPublisher = productVersionEventPublisher;
    }

    @Override
    public Optional<ProductVersion> findByProductIdentificationAndVersionNo(String productIdentification, String versionNo) {
        return productVersionManager.findByProductIdentificationAndVersionNo(productIdentification, versionNo);
    }

    @Override
    public Optional<ProductVersion> findDraft(String productIdentification) {
        return productVersionManager.findDraft(productIdentification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductVersion upsertDraft(String productIdentification) {
        ArgumentAssert.notBlank(productIdentification, "productIdentification must not be blank");

        // 1. 鎷夊綋鍓嶄骇鍝佸畬鏁存爲(鍚仠鐢ㄦ湇鍔?鈹€鈹€ 蹇収椤讳负瀹屾暣鐗╂ā鍨?services 浠嶅彲鑳戒负绌?浜у搧鍒?create 鏃跺嵆濡傛)
        ProductParamVO fullTree = productQueryService.selectFullProductByProductIdentification(productIdentification, true);
        if (fullTree == null) {
            throw BizException.wrap("Product not found: " + productIdentification);
        }

        // 2. 鎵惧綋鍓嶆墍鏈?DRAFT 琛?鈹€鈹€ 姝ｅ父鍙湁 1 涓?>1 璇存槑鍘嗗彶骞跺彂婕忕綉 / 閿佸け鏁?鍋氫竴娆¤嚜鎰堛€?
        //    listByProductIdentificationAndStatus 宸叉寜 created_time 鍊掑簭,鍙?[0] 鍗虫渶鏂般€?
        List<ProductVersion> drafts = productVersionManager.listByProductIdentificationAndStatus(
            productIdentification, ProductVersionStatusEnum.DRAFT.getValue());
        ProductVersion draft;
        boolean isNew = drafts.isEmpty();
        if (isNew) {
            draft = ProductVersion.builder()
                .productIdentification(productIdentification)
                .versionNo(nextVersion())
                .versionStatus(ProductVersionStatusEnum.DRAFT.getValue())
                .build();
        } else {
            draft = drafts.get(0);
            // 鑷剤:澶氫綑 DRAFT 杞垹,淇濊瘉"姣忎骇鍝佸彧鏈変竴涓?DRAFT"涓嶅彉閲?
            if (drafts.size() > 1) {
                List<Long> dupIds = drafts.stream().skip(1).map(ProductVersion::getId).toList();
                productVersionManager.removeByIds(dupIds);
                log.warn("[ProductVersion] cleaned {} duplicate DRAFT rows productIdentification={} kept={}",
                    dupIds.size(), productIdentification, draft.getVersionNo());
            }
        }

        // 3. 搴忓垪鍖栨渶鏂?snapshot 鍐欏洖(snapshot 鍐呴儴 activeVersionNo 瀛楁 = draft 鑷繁鐨?version)
        ProductSnapshotVO snapshot = productSnapshotConverter.toSnapshot(fullTree, draft);
        snapshot.setActiveVersionNo(draft.getVersionNo());
        String newSnapshotJson = productSnapshotConverter.serialize(snapshot);
        draft.setProductSnapshotJson(newSnapshotJson);

        if (isNew) {
            productVersionManager.save(draft);
            log.info("[ProductVersion] draft created productIdentification={} version={}",
                productIdentification, draft.getVersionNo());
        } else {
            productVersionManager.updateById(draft);
            log.debug("[ProductVersion] draft refreshed productIdentification={} version={}",
                productIdentification, draft.getVersionNo());
        }
        return draft;
    }

    /**
     * {@inheritDoc}
     *
     * <p>REQUIRES_NEW:鏈柟娉曠敱 ProductChangeLogListener 鍦?AFTER_COMMIT 鍚屾闃舵璋冪敤,姝ゆ椂鍘熶簨鍔″凡鎻愪氦;
     * 蹇呴』璧风嫭绔嬩簨鍔?鍚﹀垯鑽夌鍒涘缓澶嶇敤鍒氭彁浜ょ殑杩炴帴涓嶄細钀藉簱銆?/p>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public String resolveDraftVersion(String productIdentification) {
        return findDraft(productIdentification)
            .map(ProductVersion::getVersionNo)
            .orElseGet(() -> upsertDraft(productIdentification).getVersionNo());
    }

    /**
     * 鐗堟湰鍙风敓鎴愬崟涓€鍏ュ彛 鈹€鈹€ 绯荤粺鎺ョ,16 浣嶇煭闆姳銆傛墍鏈?product_version.version_no 閮戒粠杩欓噷浜у嚭,鏀圭瓥鐣ュ彧鍔ㄨ繖涓€澶勩€?
     *
     * @return 鏂扮敓鎴愮殑鐗堟湰鍙?
     */
    private String nextVersion() {
        return SnowflakeIdUtil.nextId();
    }

    @Override
    public List<ProductVersion> listByProductIdentification(String productIdentification) {
        return productVersionManager.listByProductIdentification(productIdentification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int softDeleteAllByProductIdentification(String productIdentification) {
        if (StrUtil.isBlank(productIdentification)) {
            return 0;
        }
        List<ProductVersion> rows = productVersionManager.listByProductIdentification(productIdentification);
        if (rows.isEmpty()) {
            return 0;
        }
        int affected = productVersionManager.removeByIds(
            rows.stream().map(ProductVersion::getId).toList()) ? rows.size() : 0;
        log.info("[ProductVersion] cascade soft-delete productIdentification={} affected={}", productIdentification, affected);
        return affected;
    }

    /**
     * {@inheritDoc}
     *
     * <p>鑽夌鍗囩骇妯″瀷:鍙戝竷涓嶆柊寤虹増鏈,鑰屾槸鎶婂綋鍓?DRAFT 鍗囩骇涓虹洰鏍囩姸鎬?PUBLISHED/CANARY/SHADOW),鍐嶈捣涓€涓?
     * 鏂?DRAFT 浣滀负涓嬩竴杞紪杈戝熀绾?snapshot 鎷疯礉鑷垰鍙戝竷鐨勫揩鐓?銆侳ULL / CANARY 鎵嶆妸 version 鍥炲啓
     * product.active_version_no(SHADOW 涓嶅垏鎸囬拡);CANARY 杩樿鎶婂垏鎹㈠墠鐨?activeVersionNo 璁板叆
     * product.previous_full_version_no 渚涘洖婊?/ 鐏板害璺敱銆傝惤 RUNNING 璁板綍鍚庡彂浜嬩欢,寮傛璧?TD DDL + 璁惧鏀圭粦銆?/p>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductVersion publish(ProductVersionPublishVO vo) {
        ArgumentAssert.notNull(vo, "publish VO must not be null");
        String productIdentification = vo.getProductIdentification();

        ProductResultVO product = Optional.ofNullable(
                productQueryService.findOneByProductIdentification(productIdentification))
            .orElseThrow(() -> BizException.wrap("Product not found: " + productIdentification));

        ProductPublishStrategyEnum strategy = ProductPublishStrategyEnum.fromValue(vo.getPublishStrategy())
            .orElse(ProductPublishStrategyEnum.FULL);

        // 1. 鍙戝竷鍓嶅啀鍒蜂竴娆?DRAFT,淇濊瘉 snapshot 鏄渶鏂颁骇鍝佹爲
        ProductVersion draft = upsertDraft(productIdentification);
        String publishedVersion = draft.getVersionNo();

        // 2. DRAFT 鍗囩骇涓虹洰鏍囩姸鎬?
        draft.setVersionStatus(resolveStatusByStrategy(strategy).getValue());
        draft.setPublishStrategy(strategy.getValue());
        draft.setCanaryConfigJson(vo.getCanaryConfigJson());
        draft.setRemark(vo.getPublishRemark());
        draft.setPublishTime(LocalDateTime.now());
        productVersionManager.updateById(draft);

        // 3. 鍒囦骇鍝佹寚閽?SHADOW 涓嶅姩鎸囬拡,淇濈暀鏃佽矾璇箟)
        // 璺ㄥ煙浜у搧鍐欏叆璧?ProductService(婊¤冻"绂佹璺ㄥ眰绾?+ Service AOP @DS 鍒囩鎴峰簱),
        // 涓嶇洿鎺ヨ皟 ProductManager;previousVersion 鍦?service 鍐呴儴浠?DB 璇诲嚭鏉ユ洿鏂板埌 previousFullVersionNo
        String previousVersion = product.getActiveVersionNo();
        boolean shouldSwitchPointer = Optional.ofNullable(strategy)
            .filter(ProductPublishStrategyEnum.SHADOW::equals)
            .isEmpty();
        if (shouldSwitchPointer) {
            boolean isCanary = Optional.ofNullable(strategy)
                .filter(ProductPublishStrategyEnum.CANARY::equals)
                .isPresent();
            productService.switchActiveVersionForPublish(productIdentification, publishedVersion, isCanary);
            // 琚彇浠ｇ殑涓婁竴涓?active 鐗堟湰鑻ヤ粛鏄?CANARY(鐬€?,demote 涓?PUBLISHED(鍘嗗彶鎬?:鍚﹀垯鐏板害鏅嬪崌 /
            // 鏀鹃噺鍚庣増鏈垪琛ㄤ細娈嬬暀 CANARY 鏍囩,璇"浠嶅湪鐏板害涓?(瀹為檯 active 鎸囬拡宸叉寚鍚戞柊鐗堟湰)銆?
            demoteSupersededCanary(productIdentification, previousVersion);
        }

        // 4. 璧锋柊鐨?DRAFT 琛?snapshot 鎷疯礉鑷垰鍙戝竷鐨勭増鏈?鍚庣画 CRUD 鍦ㄦ鍩虹涓婄疮绉?
        ProductVersion nextDraft = ProductVersion.builder()
            .productIdentification(productIdentification)
            .versionNo(nextVersion())
            .versionStatus(ProductVersionStatusEnum.DRAFT.getValue())
            .productSnapshotJson(draft.getProductSnapshotJson())
            .build();
        productVersionManager.save(nextDraft);

        // 5. 钀藉彂甯冭褰?RUNNING),寮傛鐩戝惉鍣ㄦ墽琛?TD DDL 鍚庡洖鍐?SUCCESS / FAILED;
        //    鏈€澶у厹搴曢噸璇曟鏁板彇鐢ㄦ埛閰嶇疆(clamp 鍒?1~10,缂虹渷 PUBLISH_RETRY_DEFAULT)
        Integer maxRetry = resolvePublishMaxRetry(vo.getMaxRetryCount());
        ProductPublishRecord record = productPublishRecordService.recordPublish(
            productIdentification, previousVersion, publishedVersion, maxRetry);

        // 6. 鍙戜簨浠?寮傛鎵ц TD DDL + 璁惧鏀圭粦 + 缂撳瓨鍒锋柊
        productVersionEventPublisher.publishPublished(ProductVersionLifecycleEventSource.builder()
            .productIdentification(productIdentification)
            .sourceVersion(previousVersion)
            .targetVersion(publishedVersion)
            .publishStrategy(strategy)
            .publishRecordId(record.getId())
            .build());

        log.info("[ProductVersion] publish accepted productIdentification={} publishedVersion={} nextDraft={} strategy={} switchPointer={} by={} recordId={}",
            productIdentification, publishedVersion, nextDraft.getVersionNo(), strategy,
            shouldSwitchPointer, AuthUtil.getUserId(), record.getId());
        return draft;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductVersion rollback(ProductVersionRollbackVO vo) {
        ArgumentAssert.notNull(vo, "rollback VO must not be null");
        String productIdentification = vo.getProductIdentification();
        String targetVersion = vo.getTargetVersion();

        ProductResultVO product = Optional.ofNullable(
                productQueryService.findOneByProductIdentification(productIdentification))
            .orElseThrow(() -> BizException.wrap("Product not found: " + productIdentification));

        ProductVersion targetRow = productVersionManager
            .findByProductIdentificationAndVersionNo(productIdentification, targetVersion)
            .orElseThrow(() -> BizException.wrap("Target version not found: " + targetVersion));

        if (StrUtil.equals(product.getActiveVersionNo(), targetVersion)) {
            throw BizException.wrap("Already on target version, no rollback needed");
        }

        String fromVersion = product.getActiveVersionNo();
        // 璺ㄥ煙浜у搧鍐欏叆璧?ProductService 鑰岄潪 ProductManager(婊¤冻"绂佹璺ㄥ眰绾? + Service AOP 鍒囧簱)
        productService.rollbackActiveVersion(productIdentification, targetVersion);

        targetRow.setVersionStatus(ProductVersionStatusEnum.PUBLISHED.getValue());
        targetRow.setRemark(Optional.ofNullable(vo.getRollbackRemark()).orElse(targetRow.getRemark()));
        productVersionManager.updateById(targetRow);

        // 鎶婂師 active 鐗堟湰(琚洖婊氳蛋鐨?鏍囪涓?ROLLED_BACK 鈹€鈹€ 鍚﹀垯鐗堟湰鍒楄〃閲屽畠杩樻樉绀?PUBLISHED
        // 浣嗗疄闄呭凡涓嶆槸 active,鍓嶇 status tab 璁℃暟閿欍€佹渶鏂扮敓鏁堝窘绔犻敊閰?鐢ㄦ埛鎰熺煡娣蜂贡
        Optional.ofNullable(fromVersion)
            .flatMap(v -> productVersionManager.findByProductIdentificationAndVersionNo(productIdentification, v))
            .ifPresent(fromRow -> {
                fromRow.setVersionStatus(ProductVersionStatusEnum.ROLLED_BACK.getValue());
                productVersionManager.updateById(fromRow);
            });

        ProductPublishRecord record = productPublishRecordService.recordRollback(
            productIdentification, fromVersion, targetVersion);

        productVersionEventPublisher.publishRolledBack(ProductVersionLifecycleEventSource.builder()
            .productIdentification(productIdentification)
            .sourceVersion(fromVersion)
            .targetVersion(targetVersion)
            .publishRecordId(record.getId())
            .build());

        log.info("[ProductVersion] rollback accepted productIdentification={} from={} to={} by={} recordId={}",
            productIdentification, fromVersion, targetVersion, AuthUtil.getUserId(), record.getId());
        return targetRow;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductVersion purgeHistory(ProductVersionPurgeVO vo) {
        ArgumentAssert.notNull(vo, "purge VO must not be null");
        String productIdentification = vo.getProductIdentification();
        String version = vo.getVersionNo();

        ProductResultVO product = Optional.ofNullable(
                productQueryService.findOneByProductIdentification(productIdentification))
            .orElseThrow(() -> BizException.wrap("Product not found: " + productIdentification));

        if (StrUtil.equals(product.getActiveVersionNo(), version)) {
            throw BizException.wrap("Cannot purge the current effective version");
        }
        if (StrUtil.equals(product.getPreviousFullVersionNo(), version)) {
            throw BizException.wrap("Cannot purge the canary previous full version");
        }

        ProductVersion versionRow = productVersionManager
            .findByProductIdentificationAndVersionNo(productIdentification, version)
            .orElseThrow(() -> BizException.wrap("Version not found: " + version));

        versionRow.setVersionStatus(ProductVersionStatusEnum.ARCHIVED.getValue());
        versionRow.setRemark(Optional.ofNullable(vo.getPurgeRemark()).orElse(versionRow.getRemark()));
        productVersionManager.updateById(versionRow);

        ProductPublishRecord record = productPublishRecordService.recordPurge(productIdentification, version);

        productVersionEventPublisher.publishPurgeRequested(ProductVersionLifecycleEventSource.builder()
            .productIdentification(productIdentification)
            .sourceVersion(version)
            .targetVersion(version)
            .publishRecordId(record.getId())
            .build());

        log.info("[ProductVersion] purgeHistory accepted productIdentification={} version={} by={} recordId={}",
            productIdentification, version, AuthUtil.getUserId(), record.getId());
        return versionRow;
    }

    /**
     * {@inheritDoc}
     *
     * <p>澶氫釜 count 鏌ヨ鎷艰,缁熻绮掑害鎸夌鎴?@DS 宸插垏鍒板綋鍓嶇鎴峰簱),涓嶈法绉熸埛鑱氬悎銆?/p>
     */
    @Override
    public ProductVersionStatisticsResultVO statistics() {
        // 鍏ㄩ儴 count 璧?Service / 鏈煙 Manager 灞?婊¤冻"绂佹璺ㄥ眰绾ц皟鐢?+ @DS 鍒囩鎴峰簱鐢熸晥);
        // 姣忛」鐢?safeCount 鍏滃簳:浠讳竴鏌ヨ寮傚父(绉熸埛搴撶己琛?/ SQL 鎶ラ敊 / DB 鎶栧姩)浠呰椤瑰綊 0,
        // 缁濅笉璁╂暣涓粺璁℃帴鍙ｅ鍓嶇 500 鈹€鈹€ 鐪嬫澘瀹佸彲鏄剧ず 0,涔熶笉鑳芥暣椤垫姤閿欍€?
        Long total = safeCount(productQueryService::findProductTotal);
        Long published = safeCount(productQueryService::countPublishedProducts);
        Long canary = safeCount(productQueryService::countCanaryInProgressProducts);
        long unpublished = Math.max(0L, total - published);
        Long recent7d = safeCount(() -> productPublishRecordService.countSuccessfulPublishesInLastDays(7));
        // 鐗╂ā鍨嬫湇鍔℃暟(寤烘ā娣卞害)鈹€鈹€ 璧?ProductQueryService 缁熻 product_service
        Long thingModelServiceCount = safeCount(productQueryService::countThingModelServices);
        // 鍙戝竷鐗堟湰鎬婚噺 鈹€鈹€ 鏈煙 Manager 鎸?version_status=PUBLISHED 缁熻 product_version
        Long publishedVersionTotal = safeCount(() ->
            productVersionManager.countByVersionStatus(ProductVersionStatusEnum.PUBLISHED.getValue()));
        return ProductVersionStatisticsResultVO.builder()
            .productTotal(total)
            .publishedProductCount(published)
            .canaryProductCount(canary)
            .unpublishedProductCount(unpublished)
            .recentPublishCount7d(recent7d)
            .thingModelServiceCount(thingModelServiceCount)
            .publishedVersionTotal(publishedVersionTotal)
            .build();
    }

    /**
     * 缁熻椤瑰畨鍏ㄨ鏁?鈹€鈹€ 鍗曢」鏌ヨ寮傚父褰?0 骞跺憡璀︿笉鍚戜笂鎶?淇濊瘉缁熻鎺ュ彛瀵瑰墠绔案杩滄垚鍔熻繑鍥?鐪嬫澘鍏滃簳 0,涓嶆暣椤?500)銆?
     *
     * @param supplier 璁℃暟鏌ヨ
     * @return 璁℃暟缁撴灉;鏌ヨ寮傚父鎴栦负 null 鏃惰繑鍥?0
     */
    private Long safeCount(java.util.function.Supplier<Long> supplier) {
        try {
            Long v = supplier.get();
            return v == null ? 0L : v;
        } catch (Exception e) {
            log.warn("[ProductVersion.statistics] count failed, fallback 0", e);
            return 0L;
        }
    }

    @Override
    public ProductVersionDiffVO diff(String productIdentification, String sourceVersion, String targetVersion) {
        ArgumentAssert.notNull(productIdentification, "productIdentification must not be null");
        ArgumentAssert.notNull(targetVersion, "targetVersion must not be null");

        // 鍚屽彿鐭矾 鈹€鈹€ 鑷瘮鏃剁洿鎺ヨ繑鍥炵┖ diff,閬垮厤鍙嶅皠璁＄畻 + 闃叉 changeType 琚粯璁ゆ帹涓?UPDATE(璇箟閿?
        if (StrUtil.isNotBlank(sourceVersion) && sourceVersion.equals(targetVersion)) {
            return ProductVersionDiffVO.builder()
                .sourceVersion(sourceVersion)
                .targetVersion(targetVersion)
                .summary(new ProductVersionDiffSummaryVO())
                .nodes(List.of())
                .build();
        }

        ProductSnapshotVO targetSnapshot = loadSnapshot(productIdentification, targetVersion)
            .orElseThrow(() -> BizException.wrap("Target version not found: " + targetVersion));

        // source 鏄惧紡浼犱簡浣嗘壘涓嶅埌 鈫?蹇呴』鎶涢敊,閬垮厤闈欓粯閫€鍖栦负"鍏ㄩ儴鏂板"璇鐢ㄦ埛(棣栨鍙戝竷鍦烘櫙鎵嶅厑璁?source 涓虹┖)
        ProductSnapshotVO sourceSnapshot = null;
        if (StrUtil.isNotBlank(sourceVersion)) {
            sourceSnapshot = loadSnapshot(productIdentification, sourceVersion)
                .orElseThrow(() -> BizException.wrap("Source version not found: " + sourceVersion));
        }

        return productSnapshotDiffCalculator.diff(sourceSnapshot, targetSnapshot);
    }

    // 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€ 绉佹湁 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€

    private Optional<ProductSnapshotVO> loadSnapshot(String productIdentification, String versionNo) {
        return productVersionManager.findByProductIdentificationAndVersionNo(productIdentification, versionNo)
            .map(ProductVersion::getProductSnapshotJson)
            .flatMap(productSnapshotConverter::deserialize);
    }

    private ProductVersionStatusEnum resolveStatusByStrategy(ProductPublishStrategyEnum strategy) {
        return switch (strategy == null ? ProductPublishStrategyEnum.FULL : strategy) {
            case FULL -> ProductVersionStatusEnum.PUBLISHED;
            case CANARY -> ProductVersionStatusEnum.CANARY;
            case SHADOW -> ProductVersionStatusEnum.SHADOW;
        };
    }

    /** 鏈€澶у厹搴曢噸璇曟鏁扮己鐪佸€?鐢ㄦ埛鏈～鏃?銆?*/
    private static final int PUBLISH_RETRY_DEFAULT = 3;
    /** 鏈€澶у厹搴曢噸璇曟鏁颁笂闄?鍓嶇 max 涓庡悗绔?clamp 鍏辩敤姝ゅ€?銆?*/
    private static final int PUBLISH_RETRY_MAX = 10;

    /**
     * 瑙ｆ瀽鐢ㄦ埛閰嶇疆鐨勬渶澶у厹搴曢噸璇曟鏁?null 鍙栫己鐪?{@value #PUBLISH_RETRY_DEFAULT},鍚﹀垯 clamp 鍒?[1, {@value #PUBLISH_RETRY_MAX}]銆?
     * 鍚庣鍏滃簳鏍￠獙(VO 宸插甫 {@code @Min/@Max},姝ゅ鍐?clamp 闃茬洿杩炴帴鍙ｇ粫杩?銆?
     *
     * @param input 鐢ㄦ埛杈撳叆(鍙┖)
     * @return 1~{@value #PUBLISH_RETRY_MAX} 鐨勬湁鏁堝€?
     */
    private Integer resolvePublishMaxRetry(Integer input) {
        if (input == null) {
            return PUBLISH_RETRY_DEFAULT;
        }
        return Math.min(Math.max(input, 1), PUBLISH_RETRY_MAX);
    }

    /**
     * 鎶婅鍙栦唬鐨勪笂涓€涓?active 鐗堟湰浠?CANARY(鐬€?demote 涓?PUBLISHED(鍘嗗彶鎬?銆?
     * 浠?CANARY 闇€瑕?鐏板害鐗堟湰涓€鏃﹁鏂板彂甯冨彇浠ｅ氨涓嶅啀鏄?杩涜涓殑鐏板害",娈嬬暀 CANARY 鏍囩浼氳鐗堟湰鍒楄〃璇;
     * PUBLISHED 鍘嗗彶鐗堟湰淇濇寔鍘熺姸(鏈氨鏄悎娉曞巻鍙叉€?;DRAFT/ROLLED_BACK/ARCHIVED/SHADOW 涓嶄細鎴愪负 active,鏃犻渶鑰冭檻銆?
     * 骞傜瓑:浠呭綋鍓嶇姸鎬佺‘涓?CANARY 鎵嶆敼,閲嶈窇鏃犲壇浣滅敤銆?
     *
     * @param productIdentification 浜у搧鏍囪瘑
     * @param supersededVersion     琚彇浠ｇ殑涓婁竴涓?active 鐗堟湰鍙?绌虹櫧鍒欒烦杩?濡傞娆″彂甯?
     */
    private void demoteSupersededCanary(String productIdentification, String supersededVersion) {
        if (StrUtil.isBlank(supersededVersion)) {
            return;
        }
        productVersionManager.findByProductIdentificationAndVersionNo(productIdentification, supersededVersion)
            .filter(v -> ProductVersionStatusEnum.CANARY.getValue().equals(v.getVersionStatus()))
            .ifPresent(v -> {
                v.setVersionStatus(ProductVersionStatusEnum.PUBLISHED.getValue());
                productVersionManager.updateById(v);
                log.info("[ProductVersion] demoted superseded canary version {} -> PUBLISHED, product={}",
                    supersededVersion, productIdentification);
            });
    }
}
