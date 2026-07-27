package org.springblade.modules.iot.device.service.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotdeviceserviceimplDeviceServiceImpl.java.mapper.DeviceMapper;

import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.mp.support.Query;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.cacert.enumeration.CaCertAuditTypeEnum;
import org.springblade.modules.iot.cacert.enumeration.CaCertStatusEnum;
import org.springblade.modules.iot.cacert.service.audit.CaCertAuditLogService;
import org.springblade.modules.iot.cacert.service.license.CaCertLicenseService;
import org.springblade.modules.iot.cacert.vo.result.license.CaCertLicenseResultVO;
import org.springblade.modules.iot.cache.helper.LinkCacheDataHelper;
import org.springblade.modules.iot.cache.vo.device.DeviceCacheVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.Device;
import org.springblade.modules.iot.device.entity.DeviceAction;
import org.springblade.modules.iot.device.enumeration.DeviceActionStatusEnum;
import org.springblade.modules.iot.common.enums.DeviceActionTypeEnum;
import org.springblade.modules.iot.device.enumeration.DeviceAuthModeEnum;
import org.springblade.modules.iot.device.enumeration.DeviceConnectStatusEnum;
import org.springblade.modules.iot.device.enumeration.DeviceEncryptMethodEnum;
import org.springblade.modules.iot.device.enumeration.DeviceNodeTypeEnum;
import org.springblade.modules.iot.device.enumeration.DeviceSslTestStepEnum;
import org.springblade.modules.iot.device.enumeration.DeviceSslTestStepStatusEnum;
import org.springblade.modules.iot.device.enumeration.DeviceStatusEnum;
import org.springblade.modules.iot.device.event.publisher.DeviceEventPublisher;
import org.springblade.modules.iot.device.event.source.DeviceDeletedEventSource;
import org.springblade.modules.iot.device.event.source.DeviceInfoUpdatedEventSource;
import org.springblade.modules.iot.device.event.source.DeviceRebindEventSource;
import org.springblade.modules.iot.device.service.DeviceActionService;
import org.springblade.modules.iot.device.service.DeviceLocationService;
import org.springblade.modules.iot.device.service.DeviceService;
import org.springblade.modules.iot.device.vo.query.DeviceAuthenticationQuery;
import org.springblade.modules.iot.device.vo.query.DeviceDetailsPageQuery;
import org.springblade.modules.iot.device.vo.query.DeviceLocationPageQuery;
import org.springblade.modules.iot.device.vo.query.DevicePageQuery;
import org.springblade.modules.iot.device.vo.query.DeviceSslTestQuery;
import org.springblade.modules.iot.device.vo.result.DeviceDetailsResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceLocationResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceOverviewResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceSslTestResultVO;
import org.springblade.modules.iot.device.vo.result.DeviceSslTestStepVO;
import org.springblade.modules.iot.device.vo.result.DeviceVersionResultVO;
import org.springblade.modules.iot.device.vo.save.DeviceActionSaveVO;
import org.springblade.modules.iot.device.vo.save.DeviceLocationSaveVO;
import org.springblade.modules.iot.device.vo.save.DeviceSaveVO;
import org.springblade.modules.iot.device.vo.update.DeviceUpdateVO;
import org.springblade.modules.iot.product.service.ProductQueryService;
import org.springblade.modules.iot.product.vo.query.ProductPageQuery;
import org.springblade.modules.iot.product.vo.result.ProductResultVO;
import org.springblade.modules.iot.productversion.entity.ProductVersion;
import org.springblade.modules.iot.productversion.enumeration.ProductVersionStatusEnum;
import org.springblade.modules.iot.productversion.service.ProductVersionQueryService;
import org.springblade.modules.iot.device.vo.result.DeviceVersionDistributionVO;
import org.springblade.modules.iot.protocol.enumeration.MqttProtocolTopoStatusEnum;
import org.springblade.modules.iot.protocol.vo.param.TopoAddSubDeviceParam;
import org.springblade.modules.iot.protocol.vo.param.TopoDeleteSubDeviceParam;
import org.springblade.modules.iot.protocol.vo.param.TopoDeviceDataReportParam;
import org.springblade.modules.iot.protocol.vo.param.TopoQueryDeviceParam;
import org.springblade.modules.iot.protocol.vo.param.TopoUpdateSubDeviceStatusParam;
import org.springblade.modules.iot.protocol.vo.result.DeviceAuthenticationResultVO;
import org.springblade.modules.iot.protocol.vo.result.DeviceInfoResultVO;
import org.springblade.modules.iot.protocol.vo.result.TopoAddDeviceResultVO;
import org.springblade.modules.iot.protocol.vo.result.TopoDeviceOperationResultVO;
import org.springblade.modules.iot.protocol.vo.result.TopoQueryDeviceResultVO;
import org.springblade.modules.iot.utils.cacert.CertificateVerifierUtil;
import org.springblade.modules.iot.utils.x509.CertSerialNumberUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * 璁惧妗ｆ淇℃伅琛?
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl extends BaseServiceImpl<DeviceMapper, Device> implements DeviceService {

    /**
     * 娉ㄥ叆鍙 {@link ProductQueryService}(鐙珛 bean,闆朵笅娓?Service 渚濊禆),鍒囧簱缁忚繃 Service AOP 杈圭晫銆?
     * 绫诲浘涓?DAG,瑙勯伩 device鈫攑roduct 鍙嶅悜渚濊禆寰幆銆?
     */
    private final ProductQueryService productQueryService;

    /**
     * 娉ㄥ叆鍙 {@link ProductVersionQueryService}(leaf bean,闆朵笅娓?Service 渚濊禆),鏍￠獙"鍒囨崲鐩爣鐗堟湰"鏄惁瀛樺湪 / 鐘舵€佸彲鍒?
     * 涓?{@link #productQueryService} 鍚岀悊缁?Service AOP 杈圭晫瑙勯伩 device鈫攑roductversion 鍙嶅悜渚濊禆寰幆銆?
     */
    private final ProductVersionQueryService productVersionQueryService;

    private final DeviceLocationService deviceLocationService;

    private final DeviceActionService deviceActionService;

    private final CaCertLicenseService caCertLicenseService;

    private final CaCertAuditLogService caCertAuditLogService;

    private final DeviceEventPublisher deviceEventPublisher;

    private final LinkCacheDataHelper linkCacheDataHelper;

    @Override
    public IPage<DeviceResultVO> getPage(Query params) {
        IPage<Device> page = superManager.getPage(params);
        return BeanUtil.toBeanPage(page, DeviceResultVO.class);
    }

    @Override
    public Long findDeviceTotal() {
        return superManager.findDeviceTotal();
    }

    @Override
    protected <SaveVO> void saveAfter(SaveVO saveVO, Device entity) {
        // 鍙戝竷璁惧淇℃伅鏇存柊浜嬩欢
        deviceEventPublisher.publishDeviceInfoUpdatedEvent(DeviceInfoUpdatedEventSource.builder()
                .deviceIdentificationList(Collections.singletonList(entity.getDeviceIdentification()))
                .build());
    }

    @Override
    protected <UpdateVO> void updateAfter(UpdateVO updateVO, Device entity) {
        // 鍙戝竷璁惧淇℃伅鏇存柊浜嬩欢
        deviceEventPublisher.publishDeviceInfoUpdatedEvent(DeviceInfoUpdatedEventSource.builder()
                .deviceIdentificationList(Collections.singletonList(entity.getDeviceIdentification()))
                .build());
    }

    /**
     * 瀹㈡埛绔璇?
     *
     * @param deviceAuthenticationQuery 璁惧璁よ瘉鏌ヨ瀵硅薄
     * @return {@link DeviceAuthenticationResultVO} 璁よ瘉缁撴灉
     */
    @Override
    public DeviceAuthenticationResultVO authClient(DeviceAuthenticationQuery deviceAuthenticationQuery) {
        log.info("璁惧璁よ瘉璇锋眰: clientIdentifier={}, authMode={}",
                deviceAuthenticationQuery.getClientIdentifier(), deviceAuthenticationQuery.getAuthMode());

        String clientIdentifier = deviceAuthenticationQuery.getClientIdentifier();
        try {
            // 鍙傛暟鏍￠獙
            ArgumentAssert.notBlank(clientIdentifier, "clientIdentifier涓嶈兘涓虹┖");
            ArgumentAssert.notBlank(deviceAuthenticationQuery.getUsername(), "username涓嶈兘涓虹┖");
            ArgumentAssert.notBlank(deviceAuthenticationQuery.getPassword(), "password涓嶈兘涓虹┖");
            ArgumentAssert.notNull(deviceAuthenticationQuery.getAuthMode(), "authMode涓嶈兘涓虹┖");

            // 鏌ヨ璁惧缂撳瓨
            Optional<DeviceCacheVO> deviceResultVOOptional = linkCacheDataHelper.getDeviceCacheVO(clientIdentifier);
            if (deviceResultVOOptional.isEmpty()) {
                log.warn("璁惧璁よ瘉澶辫触: 璁惧涓嶅瓨鍦? clientIdentifier={}", clientIdentifier);
                return buildFailureResult("璁惧涓嶅瓨鍦?);
            }

            DeviceResultVO device = BeanUtil.toBeanIgnoreError(deviceResultVOOptional.get(), DeviceResultVO.class);

            // 鏍￠獙璁よ瘉妯″紡
            DeviceAuthModeEnum deviceAuthMode = DeviceAuthModeEnum.fromValue(device.getAuthMode())
                    .orElseThrow(() -> new BizException("鏃犳晥鐨勮澶囪璇佹ā寮? " + device.getAuthMode()));

            if (!deviceAuthMode.getValue().equals(deviceAuthenticationQuery.getAuthMode())) {
                log.warn("璁惧璁よ瘉澶辫触: 璁よ瘉妯″紡涓嶅尮閰? 鏈熸湜:{}, 瀹為檯:{}",
                        deviceAuthMode.getDesc(), deviceAuthenticationQuery.getAuthMode());
                return buildFailureResult("璁よ瘉妯″紡涓嶅尮閰? 鏈熸湜: " + deviceAuthMode.getDesc());
            }

            // 妫€鏌ヨ澶囩姸鎬?
            if (DeviceStatusEnum.DISCONNECTION_STATE_COLLECTION.contains(device.getDeviceStatus())) {
                log.warn("璁惧璁よ瘉澶辫触: 璁惧宸查攣瀹?鏈縺娲? clientIdentifier={}", clientIdentifier);
                return buildFailureResult("璁惧宸查攣瀹氭垨鏈縺娲?);
            }

            // 鐢ㄦ埛鍚嶅瘑鐮佽璇?
            if (!CharSequenceUtil.equals(deviceAuthenticationQuery.getUsername(), device.getUserName()) ||
                    !CharSequenceUtil.equals(deviceAuthenticationQuery.getPassword(), device.getPassword())) {
                log.warn("璁惧璁よ瘉澶辫触: 鐢ㄦ埛鍚嶆垨瀵嗙爜鏃犳晥, clientIdentifier={}", clientIdentifier);
                return buildFailureResult("鐢ㄦ埛鍚嶆垨瀵嗙爜鏃犳晥");
            }

            // SSL妯″紡棰濆楠岃瘉璇佷功
            if (DeviceAuthModeEnum.SSL_MODE.getValue().equals(deviceAuthenticationQuery.getAuthMode())) {
                return verifySslCertificate(deviceAuthenticationQuery, device);
            }

            // 璁よ瘉鎴愬姛
            log.info("璁惧璁よ瘉鎴愬姛: clientIdentifier={}", clientIdentifier);
            return buildSuccessResult(device);
        } catch (BizException e) {
            log.warn("璁惧璁よ瘉澶辫触: {}", e.getMessage());
            return buildFailureResult(e.getMessage());
        } catch (Exception e) {
            log.error("璁惧璁よ瘉寮傚父: clientIdentifier={}", clientIdentifier, e);
            return buildFailureResult("璁よ瘉绯荤粺寮傚父");
        }
    }

    /**
     * 楠岃瘉SSL璇佷功
     *
     * @param query  璁惧璁よ瘉鏌ヨ鍙傛暟
     * @param device 璁惧淇℃伅
     * @return {@link DeviceAuthenticationResultVO} 璁よ瘉缁撴灉
     */
    private DeviceAuthenticationResultVO verifySslCertificate(DeviceAuthenticationQuery query, DeviceResultVO device) {
        if (StrUtil.isBlank(query.getClientCertificate())) {
            log.warn("SSL璁よ瘉澶辫触: 瀹㈡埛绔瘉涔︿负绌? clientIdentifier={}", query.getClientIdentifier());
            return buildFailureResult("SSL妯″紡闇€瑕佸鎴风璇佷功");
        }

        Optional<String> caOpt = getCaCertificate(device.getCertSerialNumber());
        if (caOpt.isEmpty()) {
            log.warn("SSL璁よ瘉澶辫触: 璁惧鏈粦瀹氭湁鏁?CA 璇佷功, clientIdentifier={} certSerialNumber={}",
                    query.getClientIdentifier(), device.getCertSerialNumber());
            return buildFailureResult("璁惧鏈粦瀹氭湁鏁堢殑 CA 璇佷功");
        }

        if (!CertificateVerifierUtil.verify(caOpt.get(), query.getClientCertificate())) {
            log.warn("SSL璁よ瘉澶辫触: 璇佷功鏍￠獙涓嶉€氳繃, clientIdentifier={}", query.getClientIdentifier());
            // 閿欒淇℃伅鑴辨晱 鈹€鈹€ 鍏蜂綋鍘熷洜璧版棩蹇?涓嶉€忎紶缁欏鎴风
            return buildFailureResult("鏃犳晥鐨凷SL璇佷功");
        }
        log.info("SSL璇佷功楠岃瘉鎴愬姛: clientIdentifier={}", query.getClientIdentifier());
        return buildSuccessResult(device);
    }

    /**
     * 鏋勫缓璁よ瘉鎴愬姛缁撴灉銆傛垚鍔熸棩蹇楃敱璋冪敤鏂规寜鍦烘櫙杈撳嚭,鏈柟娉曚笉鎵?log 閬垮厤閲嶅銆?
     *
     * @param device 宸查€氳繃璁よ瘉鐨勮澶?
     * @return {@link DeviceAuthenticationResultVO} 璁よ瘉鎴愬姛缁撴灉
     */
    private DeviceAuthenticationResultVO buildSuccessResult(DeviceResultVO device) {
        return DeviceAuthenticationResultVO.builder()
                .certificationResult(true)
                .deviceInfoResult(BeanUtil.toBeanIgnoreError(device, DeviceInfoResultVO.class))
                .tenantId(AuthUtil.getTenantId())
                .build();
    }

    /**
     * 鏋勫缓璁よ瘉澶辫触缁撴灉
     *
     * @param errorMessage 閿欒淇℃伅
     * @return {@link DeviceAuthenticationResultVO} 璁よ瘉缁撴灉
     */
    private DeviceAuthenticationResultVO buildFailureResult(String errorMessage) {
        return DeviceAuthenticationResultVO.builder()
                .certificationResult(false)
                .errorMessage(errorMessage)
                .tenantId(AuthUtil.getTenantId())
                .build();
    }

    /**
     * 鑾峰彇璁惧缁戝畾鐨?CA 璇佷功 Base64,**浠?{@link CaCertStatusEnum#ISSUED} 鐘舵€佺殑 CA 鎵嶈繑鍥?*銆?
     * 搴忓垪鍙风┖鐧?/ CA 涓嶅瓨鍦?/ CA 宸插悐閿€鎴栨湭棰佸彂 / Base64 涓虹┖ 鈫?{@link Optional#empty()}銆?
     */
    private Optional<String> getCaCertificate(String certSerialNumber) {
        if (StrUtil.isBlank(certSerialNumber)) {
            return Optional.empty();
        }
        return Optional.ofNullable(caCertLicenseService.getByCertSerialNumber(certSerialNumber))
                .filter(vo -> CaCertStatusEnum.ISSUED.getValue().equals(vo.getState()))
                .map(CaCertLicenseResultVO::getLicenseBase64)
                .filter(StrUtil::isNotBlank);
    }

    // ============================== SSL 娴嬭瘯鍣?绔埌绔?PKI 閾捐矾楠岃瘉)==============================

    @Override
    public DeviceSslTestResultVO sslTest(DeviceSslTestQuery query) {
        long start = System.currentTimeMillis();
        List<DeviceSslTestStepVO> steps = new ArrayList<>(6);
        // 瀹¤涓婁笅鏂?鍦?FIND_CA 姝ラ鍚庤濉厖,渚?finish() 鍐欏璁℃棩蹇楀叧鑱斿埌鍏蜂綋 CA
        SslTestAuditCtx audit = new SslTestAuditCtx();
        audit.clientIdentifier = query.getClientIdentifier();
        audit.caSerialNumber = query.getCaSerialNumber();

        // 鈶?瑙ｆ瀽 client 璇佷功
        X509Certificate clientCert;
        try {
            long t0 = System.currentTimeMillis();
            clientCert = CertificateVerifierUtil.decode(query.getClientCertBase64());
            steps.add(buildStep(DeviceSslTestStepEnum.PARSE_CLIENT_CERT,
                    DeviceSslTestStepStatusEnum.PASS, certDetail(clientCert), null,
                    System.currentTimeMillis() - t0));
        } catch (Exception e) {
            steps.add(buildStep(DeviceSslTestStepEnum.PARSE_CLIENT_CERT,
                    DeviceSslTestStepStatusEnum.FAIL, null,
                    "瀹㈡埛绔瘉涔﹁В鏋愬け璐? " + e.getMessage(), 0L));
            return finishWithSkip(steps, start, "瀹㈡埛绔瘉涔﹁В鏋愬け璐?鍚庣画姝ラ璺宠繃", audit);
        }

        // 鈶?鏈夋晥鏈?
        long t1 = System.currentTimeMillis();
        try {
            clientCert.checkValidity();
            steps.add(buildStep(DeviceSslTestStepEnum.VALIDITY_CHECK,
                    DeviceSslTestStepStatusEnum.PASS, null, null,
                    System.currentTimeMillis() - t1));
        } catch (Exception e) {
            steps.add(buildStep(DeviceSslTestStepEnum.VALIDITY_CHECK,
                    DeviceSslTestStepStatusEnum.FAIL, null,
                    "璇佷功宸茶繃鏈熸垨灏氭湭鐢熸晥: " + e.getMessage(),
                    System.currentTimeMillis() - t1));
            return finishWithSkip(steps, start, "璇佷功鏈夋晥鏈熸牎楠屾湭閫氳繃", audit);
        }

        // 鈶?CA 鏌ユ壘(浼樺厛鎸?caSerialNumber,绌哄垯鎸?clientId 鍙嶆煡璁惧缁戝畾 CA)
        long t2 = System.currentTimeMillis();
        CaCertLicenseResultVO caVo = resolveCa(query);
        if (caVo == null) {
            steps.add(buildStep(DeviceSslTestStepEnum.FIND_CA,
                    DeviceSslTestStepStatusEnum.FAIL, null,
                    "鏈壘鍒扮洰鏍?CA;璇锋鏌?CA 搴忓垪鍙锋垨璁惧缁戝畾鍏崇郴",
                    System.currentTimeMillis() - t2));
            return finishWithSkip(steps, start, "CA 鏌ユ壘澶辫触", audit);
        }
        // CA 鏌ユ壘鎴愬姛 鈫?濉璁′笂涓嬫枃(鍚庣画姝ラ鍗充娇澶辫触涔熻兘鍏宠仈鍒版 CA 鐨勫璁?Tab)
        audit.caId = caVo.getId();
        audit.caSerialNumber = caVo.getSerialNumber();
        audit.caName = caVo.getCertName();
        steps.add(buildStep(DeviceSslTestStepEnum.FIND_CA,
                DeviceSslTestStepStatusEnum.PASS,
                MapUtil.builder(new java.util.LinkedHashMap<String, Object>())
                        .put("caName", caVo.getCertName())
                        .put("caSerialNumber", caVo.getSerialNumber())
                        .put("caState", caVo.getState())
                        .build(),
                null, System.currentTimeMillis() - t2));

        // 鈶?CA 鐘舵€佹牎楠?
        long t3 = System.currentTimeMillis();
        if (!CaCertStatusEnum.ISSUED.getValue().equals(caVo.getState())) {
            steps.add(buildStep(DeviceSslTestStepEnum.CA_STATE_CHECK,
                    DeviceSslTestStepStatusEnum.FAIL,
                    MapUtil.of("caState", caVo.getState()),
                    "CA 褰撳墠鐘舵€侀潪銆屽凡棰佸彂銆?涓嶅厑璁稿弬涓庤璇?,
                    System.currentTimeMillis() - t3));
            return finishWithSkip(steps, start, "CA 鐘舵€侀潪宸查鍙?, audit);
        }
        steps.add(buildStep(DeviceSslTestStepEnum.CA_STATE_CHECK,
                DeviceSslTestStepStatusEnum.PASS, null, null,
                System.currentTimeMillis() - t3));

        // 鈶?Issuer DN 鍖归厤
        long t4 = System.currentTimeMillis();
        X509Certificate caCert;
        try {
            caCert = CertificateVerifierUtil.decode(caVo.getLicenseBase64());
        } catch (Exception e) {
            steps.add(buildStep(DeviceSslTestStepEnum.ISSUER_MATCH,
                    DeviceSslTestStepStatusEnum.FAIL, null,
                    "CA 璇佷功 Base64 瑙ｆ瀽澶辫触: " + e.getMessage(),
                    System.currentTimeMillis() - t4));
            return finishWithSkip(steps, start, "CA 璇佷功瑙ｆ瀽澶辫触", audit);
        }
        if (!clientCert.getIssuerX500Principal().equals(caCert.getSubjectX500Principal())) {
            steps.add(buildStep(DeviceSslTestStepEnum.ISSUER_MATCH,
                    DeviceSslTestStepStatusEnum.FAIL,
                    MapUtil.builder(new java.util.LinkedHashMap<String, Object>())
                            .put("clientIssuer", clientCert.getIssuerX500Principal().toString())
                            .put("caSubject", caCert.getSubjectX500Principal().toString())
                            .build(),
                    "瀹㈡埛绔瘉涔︾殑绛惧彂鑰?Issuer)涓庣洰鏍?CA 涓婚(Subject)涓嶅尮閰?,
                    System.currentTimeMillis() - t4));
            return finishWithSkip(steps, start, "Issuer DN 涓嶅尮閰?鈹€鈹€ 瀹㈡埛绔瘉涔﹂潪璇?CA 绛惧彂", audit);
        }
        steps.add(buildStep(DeviceSslTestStepEnum.ISSUER_MATCH,
                DeviceSslTestStepStatusEnum.PASS, null, null,
                System.currentTimeMillis() - t4));

        // 鈶?瀵嗙爜瀛︾鍚嶉獙璇?缁堟瀬)
        long t5 = System.currentTimeMillis();
        try {
            clientCert.verify(caCert.getPublicKey());
            steps.add(buildStep(DeviceSslTestStepEnum.SIGNATURE_VERIFY,
                    DeviceSslTestStepStatusEnum.PASS, null, null,
                    System.currentTimeMillis() - t5));
        } catch (Exception e) {
            steps.add(buildStep(DeviceSslTestStepEnum.SIGNATURE_VERIFY,
                    DeviceSslTestStepStatusEnum.FAIL, null,
                    "瀵嗙爜瀛︾鍚嶆牎楠屽け璐? 瀹㈡埛绔瘉涔︿笉鏄 CA 绛惧彂鐨?,
                    System.currentTimeMillis() - t5));
            return finish(steps, false, start, "绛惧悕楠岃瘉澶辫触 鈹€鈹€ 瀹㈡埛绔瘉涔︿笉鏄 CA 绛惧彂鐨?, audit);
        }
        return finish(steps, true, start, "娴嬭瘯閫氳繃", audit);
    }

    /** SSL 娴嬭瘯瀹¤涓婁笅鏂?鈹€鈹€ 鍦?FIND_CA 姝ラ鍚庤濉厖,渚?finish() 鍐欏璁℃棩蹇楀叧鑱斿埌鍏蜂綋 CA */
    private static class SslTestAuditCtx {
        Long caId;
        String caSerialNumber;
        String caName;
        String clientIdentifier;
    }

    /**
     * 鏌?CA 璇佷功:浼樺厛鎸?caSerialNumber 鐩存煡,涓虹┖鏃跺洖閫€鎸?clientId 鍙嶆煡璁惧缁戝畾鐨?CA;鍧囨湭鍛戒腑杩?null銆?
     *
     * @param query SSL 娴嬭瘯鍏ュ弬(鍚?caSerialNumber / clientIdentifier 浜岄€変竴)
     * @return {@link CaCertLicenseResultVO} CA 璇佷功缁撴灉;鍧囨湭鍛戒腑杩斿洖 {@code null}
     */
    private CaCertLicenseResultVO resolveCa(DeviceSslTestQuery query) {
        if (StrUtil.isNotBlank(query.getCaSerialNumber())) {
            return caCertLicenseService.getByCertSerialNumber(query.getCaSerialNumber());
        }
        if (StrUtil.isBlank(query.getClientIdentifier())) {
            return null;
        }
        return linkCacheDataHelper.getDeviceCacheVO(query.getClientIdentifier())
                .map(DeviceCacheVO::getCertSerialNumber)
                .filter(StrUtil::isNotBlank)
                .map(caCertLicenseService::getByCertSerialNumber)
                .orElse(null);
    }

    /**
     * 鏋勯€犲崟姝?SSL 娴嬭瘯缁撴灉銆?
     *
     * @param step   褰撳墠姝ラ鏋氫妇
     * @param status 姝ラ鎵ц鐘舵€?
     * @param detail 姝ラ璇︽儏(澶辫触鍘熷洜 / 璇佷功鍏冧俊鎭瓑)
     * @param reason 姝ラ鎽樿鏂囨
     * @param costMs 鏈楠よ€楁椂姣
     * @return {@link DeviceSslTestStepVO} 鍗曟缁撴灉 VO
     */
    private DeviceSslTestStepVO buildStep(DeviceSslTestStepEnum step,
                                          DeviceSslTestStepStatusEnum status,
                                          Map<String, Object> detail,
                                          String reason,
                                          long costMs) {
        return DeviceSslTestStepVO.builder()
                .step(step.getValue())
                .name(step.getDesc())
                .status(status.getValue())
                .detail(detail)
                .reason(reason)
                .costMs(costMs)
                .build();
    }

    /**
     * 鎻愬彇璇佷功鏍稿績鍏冧俊鎭?subject / issuer / serial / 鏈夋晥鏈熺瓑)浣?step.detail 钀藉湴,渚涘璁″洖鏀炬煡鐪嬨€?
     *
     * @param cert 褰撳墠娴嬭瘯鐨?X.509 璇佷功
     * @return 璇佷功鍏冧俊鎭?map(subject / issuer / serialNumber / notBefore / notAfter / sigAlg / pubKeyAlg)
     */
    private Map<String, Object> certDetail(X509Certificate cert) {
        Map<String, Object> m = MapUtil.newHashMap();
        m.put("subject", cert.getSubjectX500Principal().toString());
        m.put("issuer", cert.getIssuerX500Principal().toString());
        m.put("serialNumber", CertSerialNumberUtil.getOpenSSLSerial(cert));
        m.put("notBefore", cert.getNotBefore());
        m.put("notAfter", cert.getNotAfter());
        m.put("sigAlg", cert.getSigAlgName());
        m.put("pubKeyAlg", cert.getPublicKey().getAlgorithm());
        return m;
    }

    /**
     * 澶辫触鎻愬墠缁堟鏃剁殑鍏滃簳鏀跺熬:鍓╀綑鏈墽琛屾楠や竴寰嬭ˉ {@link DeviceSslTestStepStatusEnum#SKIP},
     * 淇濊瘉杩斿洖鐨?steps 鍒楄〃涓?step 鏋氫妇涓€涓€瀵归綈銆?
     *
     * @param steps   宸插畬鎴愮殑姝ラ鍒楄〃
     * @param start   娴嬭瘯璧峰鏃堕棿鎴?姣,鐢ㄤ簬鎬昏€楁椂缁熻)
     * @param summary 缁堟鍘熷洜鎽樿
     * @param audit   瀹¤涓婁笅鏂?鍙┖,鐢ㄤ簬鍏宠仈 CA + clientId)
     * @return {@link DeviceSslTestResultVO} success=false 鐨勫畬鏁寸粨鏋?
     */
    private DeviceSslTestResultVO finishWithSkip(List<DeviceSslTestStepVO> steps, long start,
                                                  String summary, SslTestAuditCtx audit) {
        DeviceSslTestStepEnum[] all = DeviceSslTestStepEnum.values();
        for (int i = steps.size(); i < all.length; i++) {
            steps.add(buildStep(all[i], DeviceSslTestStepStatusEnum.SKIP, null,
                    "鍓嶇疆姝ラ鏈€氳繃,璺宠繃", 0L));
        }
        return finish(steps, false, start, summary, audit);
    }

    private DeviceSslTestResultVO finish(List<DeviceSslTestStepVO> steps, boolean success,
                                          long start, String summary, SslTestAuditCtx audit) {
        DeviceSslTestResultVO result = DeviceSslTestResultVO.builder()
                .success(success)
                .steps(steps)
                .summary(summary)
                .totalCostMs(System.currentTimeMillis() - start)
                .build();
        // 瀹¤:SSL 娴嬭瘯鍔ㄤ綔 鈹€鈹€ 鍏宠仈 CA + clientId,渚夸簬璇︽儏椤?audit Tab 鏃堕棿绾垮睍绀?
        try {
            String detail = "success=" + success
                    + (audit.clientIdentifier != null ? " clientId=" + audit.clientIdentifier : "")
                    + (audit.caName != null ? " caName=" + audit.caName : "")
                    + " summary=" + summary;
            caCertAuditLogService.record(CaCertAuditTypeEnum.SSL_TEST,
                    audit.caId, audit.caSerialNumber, detail);
        } catch (Exception ignore) {
            // 瀹¤澶辫触闈欓粯,涓嶅奖鍝嶆祴璇曠粨鏋滆繑鍥?
        }
        return result;
    }

    /**
     * 淇濆瓨璁惧妗ｆ
     *
     * @param saveVO 淇濆瓨鍙傛暟
     * @return {@link DeviceSaveVO} 瀹炰綋
     */
    @Override
    public DeviceSaveVO saveDevice(DeviceSaveVO saveVO) {
        log.info("saveDevice saveVO:{}", saveVO);
        //鏍￠獙鍙傛暟(椤哄甫鎶?ProductResultVO 鎷垮洖鏉ョ敤浜庡洖濉?boundProductVersionNo)
        ProductResultVO productResultVO = checkedDeviceSaveVO(saveVO);

        //鏋勫缓鍙傛暟(娉ㄥ唽鏃舵妸 boundProductVersionNo 榛樿缁戝畾涓轰骇鍝佸綋鍓嶇増鏈?
        Device device = builderDeviceSaveVO(saveVO, productResultVO);

        //淇濆瓨璁惧浣嶇疆淇℃伅
        if (null != saveVO.getDeviceLocationSaveVO()) {
            saveVO.getDeviceLocationSaveVO().setDeviceIdentification(device.getDeviceIdentification());
            DeviceLocationSaveVO deviceLocationSaveVO = deviceLocationService.saveDeviceLocation(saveVO.getDeviceLocationSaveVO());
            log.info("saveDevice deviceLocationSaveVO:{}", deviceLocationSaveVO);
        }
        //淇濆瓨璁惧妗ｆ
        superManager.save(device);

        // 鍙戝竷璁惧淇℃伅鏇存柊浜嬩欢
        deviceEventPublisher.publishDeviceInfoUpdatedEvent(DeviceInfoUpdatedEventSource.builder()
                .deviceIdentificationList(Collections.singletonList(device.getDeviceIdentification()))
                .build());

        return BeanUtil.toBeanIgnoreError(device, DeviceSaveVO.class);
    }

    /**
     * 鍖楀悜API淇濆瓨璁惧妗ｆ,淇濆瓨璁惧骞惰繑鍥炲畬鏁寸殑璁惧淇℃伅銆?
     *
     * @param saveVO 淇濆瓨鍙傛暟
     * @return {@link DeviceResultVO} 璁惧缁撴灉淇℃伅
     */
    @Override
    public DeviceResultVO saveDeviceByNorthbound(DeviceSaveVO saveVO) {
        log.info("saveDeviceByNorthbound saveVO:{}", JSON.toJSONString(saveVO));
        //鏍￠獙鍙傛暟(椤哄甫鎶?ProductResultVO 鎷垮洖鏉ョ敤浜庡洖濉?boundProductVersionNo)
        ProductResultVO productResultVO = checkedDeviceSaveVO(saveVO);
        // 绉熸埛涓€鑷存€ф牎楠岋紙蹇呴』鏄綋鍓嶇鎴?ContextUtil锛?
        if (!TenantUtil.validateTenantConsistency(saveVO.getClientId())) {
            throw BizException.wrap("Tenant information does not match. No authority to operate resources.");
        }

        Device device = BeanUtil.copyProperties(saveVO, Device.class);
        device.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        fillBoundProductVersionIfBlank(device, productResultVO);

        //淇濆瓨璁惧妗ｆ
        superManager.save(device);
        // 鍙戝竷璁惧淇℃伅鏇存柊浜嬩欢
        deviceEventPublisher.publishDeviceInfoUpdatedEvent(DeviceInfoUpdatedEventSource.builder()
                .deviceIdentificationList(Collections.singletonList(device.getDeviceIdentification()))
                .build());

        return findByDeviceIdentification(device.getDeviceIdentification());
    }

    /**
     * 淇敼璁惧妗ｆ
     *
     * @param updateVO 鏇存柊鍙傛暟
     * @return {@link DeviceUpdateVO} 鏇存柊缁撴灉
     */
    @Override
    public DeviceUpdateVO updateDevice(DeviceUpdateVO updateVO) {
        log.info("updateDevice updateVO:{}", updateVO);

        //鏍￠獙鍙傛暟
        checkedDeviceUpdateVO(updateVO);

        // 浠庢暟鎹簱鏌ヨ璁惧淇℃伅锛岀‘淇濊澶囧瓨鍦?
        Device existingDevice = superManager.getById(updateVO.getId());
        if (existingDevice == null) {
            throw BizException.wrap("Device not found for ID:{}", updateVO.getId());
        }

        //鏋勫缓鍙傛暟
        Device device = buildDeviceWithBuilder(updateVO);

        // 鏇存柊鎴栨柊澧炶澶囦綅缃俊鎭?
        updateOrInsertDeviceLocation(updateVO, existingDevice);

        //鏇存柊
        superManager.updateById(device);

        // 鍙戝竷璁惧淇℃伅鏇存柊浜嬩欢
        deviceEventPublisher.publishDeviceInfoUpdatedEvent(DeviceInfoUpdatedEventSource.builder()
                .deviceIdentificationList(Collections.singletonList(existingDevice.getDeviceIdentification()))
                .build());

        // 缁戝畾鐗堟湰鍙樻洿(鍚垏鍒板奖瀛?鈫?璧版牎楠?+ 鏀圭粦閾捐矾:杩炲甫缃戝叧瀛愯澶?+ 鍙?DeviceRebindEvent 澶辨晥缂撳瓨銆?
        // 閫氱敤 update(builderDeviceUpdateVO)鍒绘剰涓嶆槧灏?boundProductVersionNo,鐗堟湰鍙敱杩欐潯鍙楁帶璺緞鏀广€?
        String newBoundVersion = updateVO.getBoundProductVersionNo();
        if (StrUtil.isNotBlank(newBoundVersion) && !newBoundVersion.equals(existingDevice.getBoundProductVersionNo())) {
            switchBoundProductVersion(updateVO.getProductIdentification(),
                    Collections.singletonList(existingDevice.getDeviceIdentification()), newBoundVersion);
        }

        return updateVO;
    }

    /**
     * 浣跨敤 Builder 鏋勫缓璁惧瀵硅薄
     *
     * @param updateVO 鏇存柊鍙傛暟
     * @return {@link Device} 璁惧淇℃伅
     */
    private Device buildDeviceWithBuilder(DeviceUpdateVO updateVO) {
        return builderDeviceUpdateVO(updateVO)
                .with(Device::setId, updateVO.getId())
                .build();
    }

    /**
     * 鏇存柊鎴栨柊澧炶澶囦綅缃俊鎭?
     *
     * @param updateVO       鏇存柊鍙傛暟
     * @param existingDevice 鐜版湁璁惧淇℃伅
     */
    private void updateOrInsertDeviceLocation(DeviceUpdateVO updateVO, Device existingDevice) {
        Optional.ofNullable(updateVO.getDeviceLocationUpdateVO()).ifPresent(locationVO -> {
            locationVO.setDeviceIdentification(existingDevice.getDeviceIdentification());
            if (locationVO.getId() == null) {
                deviceLocationService.saveDeviceLocation(
                        BeanUtil.toBeanIgnoreError(locationVO, DeviceLocationSaveVO.class)
                );
            } else {
                deviceLocationService.updateDeviceLocation(locationVO);
            }
        });
    }

    /**
     * 鏍规嵁璁惧ID鏇存柊璁惧鐘舵€?
     *
     * @param id     璁惧ID
     * @param status 璁惧鐘舵€?
     * @return {@link Boolean} 鏇存柊缁撴灉
     */
    @Override
    public Boolean updateDeviceStatus(Long id, Integer status) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        ArgumentAssert.notNull(status, "status Cannot be null");
        Device device = superManager.findOneById(id);
        if (Objects.isNull(device)) {
            throw BizException.wrap("The device does not exist");
        }
        if (status.equals(device.getDeviceStatus())) {
            throw BizException.wrap("The device status is the same as the current status");
        }
        // 鏇存柊璁惧杩炴帴鐘舵€?
        UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(Device::getId, device.getId())
                .set(Device::getDeviceStatus, status);
        return superManager.update(updateWrapper);
    }

    /**
     * 鏍规嵁璁惧ID鍒犻櫎璁惧銆傚垹闄ゆ垚鍔熷悗鍙戝竷 {@link org.springblade.modules.iot.device.event.DeviceDeletedEvent},
     * 鐢卞悇涓嬫父妯″潡鐨勫悓姝ョ洃鍚櫒鍦ㄥ悓涓€浜嬪姟鍐呮竻鐞嗘畫鐣欏紩鐢?閬垮厤"璁惧宸插垹浣嗗叾瀹冭〃浠嶆寔鏈夋寚閽?鐨勫鍎挎暟鎹€?
     *
     * @param id 璁惧ID
     * @return {@link Boolean} 鍒犻櫎缁撴灉
     */
    @Override
    public Boolean deleteDevice(Long id) {
        ArgumentAssert.notNull(id, "id Cannot be null");
        Device device = Optional.ofNullable(superManager.findOneById(id))
                .orElseThrow(() -> BizException.wrap("The device does not exist"));
        Boolean removed = superManager.removeById(id);
        if (removed) {
            // 鍙戝竷璁惧鍒犻櫎浜嬩欢瑙﹀彂涓嬫父娓呯悊(鍒嗙粍鍏崇郴绛夊悓姝ョ洃鍚櫒鍚屼簨鍔℃墽琛?;璁惧缂撳瓨鐢辩洃鍚櫒 AFTER_COMMIT 澶辨晥銆?
            deviceEventPublisher.publishDeviceDeletedEvent(DeviceDeletedEventSource.builder()
                    .deviceId(device.getId())
                    .deviceIdentification(device.getDeviceIdentification())
                    .clientId(device.getClientId())
                    .contextMap(AuthUtil.getLocalMap())
                    .build());
        }
        return removed;
    }

    /**
     * 鎵归噺鍒犻櫎璁惧(鏁存壒浜嬪姟)銆傜被绾?@Transactional 宸茶鐩?浠讳竴鏉?{@link #deleteDevice(Long)} 鎶涘紓甯告暣鎵瑰洖婊氥€?
     * 寰幆璧?this 鑷皟鐢?鈹€鈹€ 涓嶈Е鍙戞柊浜嬪姟(鍚屽疄渚嬭皟鐢ㄤ笉璧?Spring 浠ｇ悊),鍏ㄩ儴 SQL 鍏变韩澶栧眰浜嬪姟,姝ｆ槸鎵€闇€銆?
     */
    @Override
    public Boolean deleteDevices(List<Long> ids) {
        ArgumentAssert.notEmpty(ids, "ids cannot be empty");
        // 鍘婚噸 鈹€鈹€ 闃叉涓婃父璇紶閲嶅 ID 瀵艰嚧 deleteDevice 绗簩娆℃姤"璁惧涓嶅瓨鍦?
        ids.stream().distinct().forEach(this::deleteDevice);
        return Boolean.TRUE;
    }

    @Override
    public DeviceResultVO findOneByClientId(String clientId) {
        ArgumentAssert.notBlank(clientId, "clientId Cannot be null");
        Device device = superManager.findOneByClientId(clientId);
        return BeanUtil.copyProperties(device, DeviceResultVO.class);
    }

    /**
     * 鏍规嵁璁惧鏍囪瘑鏌ヨ璁惧淇℃伅(杩?{@link DeviceDetailsResultVO})
     *
     * @param deviceIdentification 璁惧鏍囪瘑
     * @return {@link DeviceDetailsResultVO} 璁惧淇℃伅
     */
    @Override
    public DeviceDetailsResultVO findOneByDeviceIdentification(String deviceIdentification) {
        ArgumentAssert.notBlank(deviceIdentification, "deviceIdentification Cannot be null");
        Device device = superManager.findOneByDeviceIdentification(deviceIdentification);
        if (device == null) {
            throw BizException.wrap("Device not exist");
        }

        // 灏咲evice杞崲涓篋eviceDetailsResultVO
        DeviceDetailsResultVO deviceDetailsResultVO =
                BeanUtil.toBeanIgnoreError(device, DeviceDetailsResultVO.class);

        // 鏌ヨ浜у搧淇℃伅锛屽鏋滃瓨鍦ㄥ垯娣诲姞鍒扮粨鏋滀腑
        Optional.ofNullable(device.getProductIdentification())
                .flatMap(this::queryProductInfo)
                .ifPresent(deviceDetailsResultVO::setProductResultVO);

        // 鏌ヨ瀛愯澶囷紝濡傛灉鏄綉鍏冲垯鏌ヨ瀛愯澶囧垪琛紝鍚﹀垯璁剧疆涓虹┖鍒楄〃
        Optional.ofNullable(device.getNodeType())
                .filter(DeviceNodeTypeEnum.GATEWAY.getValue()::equals)
                .ifPresentOrElse(
                        type -> deviceDetailsResultVO.setSubDeviceResultVOList(
                                querySubDevices(device.getDeviceIdentification())),
                        () -> deviceDetailsResultVO.setSubDeviceResultVOList(Collections.emptyList())
                );

        // 鏌ヨ璁惧浣嶇疆淇℃伅
        Optional.ofNullable(device.getDeviceIdentification())
                .flatMap(this::queryDeviceLocation)
                .ifPresent(deviceDetailsResultVO::setDeviceLocationResultVO);

        return deviceDetailsResultVO;
    }

    @Override
    public DeviceResultVO findByDeviceIdentification(String deviceIdentification) {
        ArgumentAssert.notBlank(deviceIdentification, "deviceIdentification Cannot be null");
        Device device = superManager.findOneByDeviceIdentification(deviceIdentification);
        if (Objects.isNull(device)) {
            throw BizException.wrap("Device not exist");
        }
        return BeanUtil.toBean(device, DeviceResultVO.class);
    }

    @Override
    public boolean updateDeviceConnectionStatusById(Long id, Integer connectionStatus) throws IllegalArgumentException {
        // 鏍￠獙璁惧ID
        ArgumentAssert.notNull(id, "id cannot be null");

        // 鏍￠獙杩炴帴鐘舵€佸€?
        Optional<DeviceConnectStatusEnum> connectStatusEnumOptional = DeviceConnectStatusEnum.fromValue(connectionStatus);
        if (connectStatusEnumOptional.isEmpty()) {
            throw BizException.wrap("Invalid connection status value");
        }

        // 鏇存柊璁惧杩炴帴鐘舵€?
        updateDeviceConnectionStatus(id, connectStatusEnumOptional.get());

        // 鏌ヨ鏈€鏂拌澶囦俊鎭?
        Device device = superManager.findOneById(id);
        if (Objects.isNull(device)) {
            throw BizException.wrap("The device does not exist");
        }

        // 鑾峰彇璁惧绫诲瀷
        Optional<DeviceNodeTypeEnum> deviceNodeTypeEnumOptional = DeviceNodeTypeEnum.fromValue(device.getNodeType());

        // 妫€鏌ヨ澶囨槸鍚︿负缃戝叧
        if (deviceNodeTypeEnumOptional.isPresent() && DeviceNodeTypeEnum.GATEWAY.equals(deviceNodeTypeEnumOptional.get())) {
            // 濡傛灉璁惧涓虹綉鍏充笖璁惧鐘舵€佷负绂荤嚎锛屽垯鏇存柊瀛愯澶囩姸鎬?
            if (DeviceConnectStatusEnum.OFFLINE.getValue().equals(connectStatusEnumOptional.get().getValue())) {
                // gatewayId 鍒楁槸 String,鐩存帴浼?deviceIdentification;涓嶈 Long.valueOf
                // (鍚?"_" 鐨勬爣璇嗕細鎶?NumberFormatException 瀵艰嚧鑱斿姩澶辨晥)
                updateSubDevicesConnectionStatus(device.getDeviceIdentification(), connectStatusEnumOptional.get().getValue());
            }
        }
        return true;
    }

    @Override
    public boolean updateDeviceConnectionStatusByEvent(String clientId, Integer status, Long eventHlc) {
        ArgumentAssert.notBlank(clientId, "clientId cannot be blank");
        ArgumentAssert.notNull(status, "status cannot be null");
        ArgumentAssert.isTrue(eventHlc != null && eventHlc > 0, "eventHlc must be > 0");

        // CAS 鍗曡皟鍐?浠呭綋 DB 鍐?last_status_event_hlc 涓ユ牸灏忎簬鏂颁簨浠?hlc 鏃舵墠瑕嗙洊
        // (瀛楁 NOT NULL DEFAULT 0,瀛橀噺琛?0 < 浠讳綍鍚堟硶 hlc 鈫?棣栨浜嬩欢鎬昏兘鍐欏叆)
        UpdateWrapper<Device> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .eq(Device::getClientId, clientId)
                .lt(Device::getLastStatusEventHlc, eventHlc)
                .set(Device::getConnectStatus, status)
                .set(Device::getLastStatusEventHlc, eventHlc);
        boolean affected = superManager.update(wrapper);
        if (!affected) {
            // CAS 鎷掔粷 鈹€鈹€ 鑰佷簨浠惰繜鍒?DB 宸叉湁鏇存柊 hlc;info 绾у埆渚夸簬杩愮淮鏍告煡鎶栧姩 / 涔卞簭鍦烘櫙
            log.info("[Device.updateByEvent] CAS rejected (stale event) clientId={} hlc={} status={}",
                    clientId, eventHlc, status);
            return false;
        }
        // 鎴愬姛璺緞涔熸墦涓€鏉℃棩蹇?鈹€鈹€ 璺?mqs `[bus.lifecycle]` 瀵瑰簲,渚夸簬鎺掓煡"DB 鏄惁鐪熷啓鍏?
        log.info("[Device.updateByEvent] applied clientId={} status={} hlc={}",
                clientId, status, eventHlc);

        // 缃戝叧璁惧 OFFLINE 鈫?瀛愯澶囪仈鍔?OFFLINE(涓庡師 updateDeviceConnectionStatusById 琛屼负涓€鑷?
        if (DeviceConnectStatusEnum.OFFLINE.getValue().equals(status)) {
            Device device = superManager.lambdaQuery()
                    .eq(Device::getClientId, clientId)
                    .one();
            if (device != null) {
                DeviceNodeTypeEnum.fromValue(device.getNodeType())
                        .filter(DeviceNodeTypeEnum.GATEWAY::equals)
                        // gatewayId 鍒楁槸 String,鐩存帴浼?deviceIdentification;涓嶈 Long.valueOf
                        .ifPresent(nt -> updateSubDevicesConnectionStatus(
                                device.getDeviceIdentification(), status));
            }
        }
        return true;
    }

    /**
     * 鏇存柊鍗曚釜璁惧鐨勮繛鎺ョ姸鎬?
     *
     * @param deviceId          璁惧ID
     * @param connectStatusEnum 杩炴帴鐘舵€?
     */
    private void updateDeviceConnectionStatus(Long deviceId, DeviceConnectStatusEnum connectStatusEnum) {
        UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(Device::getId, deviceId)
                .set(Device::getConnectStatus, connectStatusEnum.getValue());

        superManager.update(updateWrapper);
    }

    /**
     * 鎶婃寚瀹氱綉鍏充笅鎵€鏈?宸叉縺娲?瀛愯澶囩殑杩炴帴鐘舵€佹壒閲忔洿鏂颁负鐩爣鍊笺€?
     *
     * <p>gatewayId 鍒楁槸 varchar,瀛樼殑灏辨槸鐖剁綉鍏?deviceIdentification(鍙惈涓嬪垝绾?,鏁呯洿鎺ユ帴 String,
     * 涓嶈寮鸿浆 Long:鍚潪鏁板瓧瀛楃浼氭姏 {@link NumberFormatException} 瀵艰嚧鑱斿姩澶辨晥銆?/p>
     *
     * @param gatewayDeviceIdentification 鐖剁綉鍏崇殑 deviceIdentification(绛変簬瀛愯澶?device.gatewayId 鍒楀瓨鐨勫€?
     * @param connectStatus               鐩爣杩炴帴鐘舵€?
     */
    private void updateSubDevicesConnectionStatus(String gatewayDeviceIdentification, Integer connectStatus) {
        UpdateWrapper<Device> updateSubDeviceWrapper = new UpdateWrapper<>();
        updateSubDeviceWrapper.lambda()
                .eq(Device::getGatewayId, gatewayDeviceIdentification)
                .eq(Device::getDeviceStatus, DeviceStatusEnum.ACTIVATED.getValue())
                .set(Device::getConnectStatus, connectStatus);

        superManager.update(updateSubDeviceWrapper);
    }

    /**
     * 鏌ヨ璁惧淇℃伅VO鍒楄〃
     *
     * @param query 鏌ヨ鍙傛暟
     * @return {@link List<DeviceResultVO>} 璁惧淇℃伅VO鍒楄〃
     */
    @Override
    public List<DeviceResultVO> getDeviceResultVOList(DevicePageQuery query) {
        List<Device> deviceList = superManager.getDevicList(query);
        return BeanUtil.toBeanList(deviceList, DeviceResultVO.class);
    }

    /**
     * 鏌ヨ璁惧淇℃伅VO璇︽儏鍒楄〃
     *
     * @param query 鏌ヨ鍙傛暟
     * @return {@link List<DeviceDetailsResultVO>} 璁惧淇℃伅VO鍒楄〃
     */
    @Override
    public List<DeviceDetailsResultVO> getDeviceDetailsResultVOList(DevicePageQuery query) {
        // 鑾峰彇璁惧鍒楄〃
        List<Device> deviceList = superManager.getDevicList(query);

        List<DeviceDetailsResultVO> deviceResultVOS = Optional.ofNullable(deviceList)
                .filter(CollUtil::isNotEmpty)
                .map(list -> BeanUtil.toBeanList(list, DeviceDetailsResultVO.class))
                .orElseGet(Collections::emptyList);

        if (deviceResultVOS.isEmpty()) {
            return Collections.emptyList();
        }

        // 鎻愬彇璁惧鏍囪瘑绗﹀垪琛紙杩囨护 null 鍊硷級
        List<String> deviceIdentificationList = deviceResultVOS.stream()
                .map(DeviceDetailsResultVO::getDeviceIdentification)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // 鏍规嵁璁惧鏍囪瘑绗﹀垪琛ㄦ煡璇㈣澶囦綅缃俊鎭?
        Map<String, DeviceLocationResultVO> deviceLocationResultVOMap = queryDeviceLocationForDevices(deviceIdentificationList);

        // 灏嗚澶囦綅缃俊鎭皝瑁呭埌璁惧淇℃伅涓?
        deviceResultVOS.forEach(deviceResultVO ->
                Optional.ofNullable(deviceResultVO.getDeviceIdentification())
                        .map(deviceLocationResultVOMap::get)
                        .ifPresent(deviceResultVO::setDeviceLocationResultVO)
        );

        return deviceResultVOS;
    }

    @Override
    public DeviceOverviewResultVO getDeviceOverview() {
        Query params = new Query<>();
        params.setModel(new DevicePageQuery());
        DeviceOverviewResultVO resultVO = BeanUtil.toBeanIgnoreError(
                superManager.selectDeviceOverview(params), DeviceOverviewResultVO.class);
        // 澧為暱鎸囨爣:浠婃棩 / 杩?澶?/ 杩?0澶?鏂板璁惧鏁?鎸?created_time,3 娆¤交閲?count)
        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        resultVO.setTodayNewCount(countCreatedSince(todayStart));
        resultVO.setWeekNewCount(countCreatedSince(todayStart.minusDays(6)));
        resultVO.setMonthNewCount(countCreatedSince(todayStart.minusDays(29)));
        return resultVO;
    }

    /**
     * 缁熻 created_time &gt;= since 鐨勮澶囨暟閲?閫昏緫鍒犻櫎鐢?MyBatis-Plus 鑷姩杩囨护)銆?
     */
    private Integer countCreatedSince(LocalDateTime since) {
        return Math.toIntExact(superManager.count(
                Wrappers.<Device>lambdaQuery().ge(Device::getCreatedTime, since)));
    }

    @Override
    public DeviceVersionResultVO getDeviceVersionByProduct(String productIdentification) {
        if (StrUtil.isBlank(productIdentification)) {
            return DeviceVersionResultVO.builder()
                    .swVersionList(Collections.emptyList())
                    .fwVersionList(Collections.emptyList())
                    .build();
        }
        return superManager.selectDeviceVersionsByProduct(productIdentification)
                .map(deviceVersionDTO -> {
                    List<String> swVersionList = StrUtil.isNotBlank(deviceVersionDTO.getSwVersions())
                            ? Arrays.asList(deviceVersionDTO.getSwVersions().split(StrUtil.COMMA))
                            : Collections.emptyList();
                    List<String> fwVersionList = StrUtil.isNotBlank(deviceVersionDTO.getFwVersions())
                            ? Arrays.asList(deviceVersionDTO.getFwVersions().split(StrUtil.COMMA))
                            : Collections.emptyList();
                    return DeviceVersionResultVO.builder()
                            .swVersionList(swVersionList)
                            .fwVersionList(fwVersionList)
                            .build();
                })
                .orElse(DeviceVersionResultVO.builder()
                        .swVersionList(Collections.emptyList())
                        .fwVersionList(Collections.emptyList())
                        .build());
    }

    /**
     * MQTT鍗忚涓嬫坊鍔犲瓙璁惧
     *
     * @param topoAddSubDeviceParam 瀛愯澶囧弬鏁?
     * @return {@link TopoAddDeviceResultVO} 娣诲姞缁撴灉
     */
    @Override
    public TopoAddDeviceResultVO saveSubDeviceByMqtt(TopoAddSubDeviceParam topoAddSubDeviceParam) {
        return saveSubDevice(topoAddSubDeviceParam);
    }

    /**
     * 鍖楀悜API娣诲姞瀛愯澶?
     *
     * @param topoAddSubDeviceParam 瀛愯澶囧弬鏁?
     * @return {@link TopoAddDeviceResultVO} 娣诲姞缁撴灉
     */
    @Override
    public TopoAddDeviceResultVO saveSubDeviceByNorthbound(TopoAddSubDeviceParam topoAddSubDeviceParam) {
        return saveSubDevice(topoAddSubDeviceParam);
    }

    /**
     * MQTT鍗忚涓嬫洿鏂板瓙璁惧杩炴帴鐘舵€?
     *
     * @param topoUpdateSubDeviceStatusParam 鏇存柊鍙傛暟
     * @return {@link TopoDeviceOperationResultVO} 鏇存柊缁撴灉
     */
    @Override
    public TopoDeviceOperationResultVO updateSubDeviceConnectStatusByMqtt(TopoUpdateSubDeviceStatusParam topoUpdateSubDeviceStatusParam) {
        return updateSubDeviceConnectStatus(topoUpdateSubDeviceStatusParam);
    }

    /**
     * 鍖楀悜API鏇存柊瀛愯澶囪繛鎺ョ姸鎬?
     *
     * @param topoUpdateSubDeviceStatusParam 鏇存柊鍙傛暟
     * @return {@link TopoDeviceOperationResultVO} 鏇存柊缁撴灉
     */
    @Override
    public TopoDeviceOperationResultVO updateSubDeviceConnectStatusByNorthbound(TopoUpdateSubDeviceStatusParam topoUpdateSubDeviceStatusParam) {
        return updateSubDeviceConnectStatus(topoUpdateSubDeviceStatusParam);
    }

    /** MQTT鍗忚涓嬪垹闄ゅ瓙璁惧 */
    @Override
    public TopoDeviceOperationResultVO deleteSubDeviceByMqtt(TopoDeleteSubDeviceParam topoDeleteSubDeviceParam) {
        return deleteSubDevice(topoDeleteSubDeviceParam);
    }

    /** 鍖楀悜API鍒犻櫎瀛愯澶?*/
    @Override
    public TopoDeviceOperationResultVO deleteSubDeviceByNorthbound(TopoDeleteSubDeviceParam topoDeleteSubDeviceParam) {
        return deleteSubDevice(topoDeleteSubDeviceParam);
    }

    /** MQTT鍗忚涓嬩笂鎶ヨ澶囨暟鎹?*/
    @Override
    public TopoDeviceOperationResultVO deviceDataReportByMqtt(TopoDeviceDataReportParam topoDeviceDataReportParam) {
        return deviceDataReport(topoDeviceDataReportParam);
    }

    /** 鍖楀悜API涓婃姤璁惧鏁版嵁 */
    @Override
    public TopoDeviceOperationResultVO deviceDataReportByNorthbound(TopoDeviceDataReportParam topoDeviceDataReportParam) {
        return deviceDataReport(topoDeviceDataReportParam);
    }

    /** 鏍规嵁璁惧ID鏌ヨ璁惧璇︽儏 */
    @Override
    public DeviceDetailsResultVO getDeviceDetails(Long id) {
        if (id == null) {
            throw BizException.wrap("Device ID cannot be null");
        }

        Device device = superManager.findOneById(id);
        if (Objects.isNull(device)) {
            throw BizException.wrap("The device does not exist");
        }

        // 灏咲evice杞崲涓篋eviceDetailsResultVO
        DeviceDetailsResultVO deviceDetailsResultVO =
                BeanUtil.toBeanIgnoreError(device, DeviceDetailsResultVO.class);

        // 鏌ヨ浜у搧淇℃伅锛屽鏋滃瓨鍦ㄥ垯娣诲姞鍒扮粨鏋滀腑
        Optional.ofNullable(device.getProductIdentification())
                .flatMap(this::queryProductInfo)
                .ifPresent(deviceDetailsResultVO::setProductResultVO);

        // 鏌ヨ瀛愯澶囷紝濡傛灉鏄綉鍏冲垯鏌ヨ瀛愯澶囧垪琛紝鍚﹀垯璁剧疆涓虹┖鍒楄〃
        Optional.ofNullable(device.getNodeType())
                .filter(DeviceNodeTypeEnum.GATEWAY.getValue()::equals)
                .ifPresentOrElse(
                        type -> deviceDetailsResultVO.setSubDeviceResultVOList(
                                querySubDevices(device.getDeviceIdentification())),
                        () -> deviceDetailsResultVO.setSubDeviceResultVOList(Collections.emptyList())
                );

        // 鏌ヨ璁惧浣嶇疆淇℃伅
        Optional.ofNullable(device.getDeviceIdentification())
                .flatMap(this::queryDeviceLocation)
                .ifPresent(deviceDetailsResultVO::setDeviceLocationResultVO);

        return deviceDetailsResultVO;
    }

    /** 鑾峰彇璁惧璇︽儏鍒嗛〉淇℃伅 */
    @Override
    public IPage<DeviceDetailsResultVO> getDeviceDetailsPage(Query params) {
        // 鑾峰彇璁惧鍒嗛〉淇℃伅
        IPage<Device> deviceIPage = superManager.getDeviceDetailsPage(params);

        // 灏?Device 杞崲涓?DeviceDetailsResultVO 鍒楄〃锛堥槻绌哄鐞嗭級
        List<DeviceDetailsResultVO> deviceDetailsResultVOS = Optional.ofNullable(deviceIPage.getRecords())
                .filter(CollUtil::isNotEmpty)
                .map(records -> BeanUtil.toBeanList(records, DeviceDetailsResultVO.class))
                .orElseGet(Collections::emptyList);

        if (deviceDetailsResultVOS.isEmpty()) {
            return new Page<>(deviceIPage.getCurrent(), deviceIPage.getSize(), 0);
        }

        // 鎻愬彇璁惧鏍囪瘑绗﹀垪琛紙杩囨护 null 鍊硷級
        List<String> deviceIdentificationList = deviceDetailsResultVOS.stream()
                .map(DeviceDetailsResultVO::getDeviceIdentification)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // 鎻愬彇浜у搧鏍囪瘑鍒楄〃锛堣繃婊?null 鍊硷級
        List<String> productIdentificationList = deviceDetailsResultVOS.stream()
                .map(DeviceDetailsResultVO::getProductIdentification)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // 鏌ヨ浜у搧淇℃伅鍜岃澶囦綅缃俊鎭?
        Map<String, ProductResultVO> productResultVOMap = queryProductInfoForDevices(productIdentificationList);
        Map<String, DeviceLocationResultVO> deviceLocationResultVOMap = queryDeviceLocationForDevices(deviceIdentificationList);

        // 鎵归噺鏌ヨ鎵€鏈夌綉鍏宠澶囩殑瀛愯澶囷紙1娆℃煡璇唬鏇縉娆★紝瑙ｅ喅N+1闂锛?
        Map<String, List<DeviceResultVO>> subDeviceMap = querySubDevicesForGateways(deviceDetailsResultVOS);

        // 澶勭悊姣忎釜璁惧鐨勫瓙璁惧銆佷骇鍝佷俊鎭拰浣嶇疆淇℃伅锛堜笉浣跨敤 parallelStream锛岄伩鍏?@DS 鏁版嵁婧愪笂涓嬫枃涓㈠け锛?
        deviceDetailsResultVOS.forEach(device ->
                processDeviceSubDetails(device, productResultVOMap, deviceLocationResultVOMap, subDeviceMap)
        );

        // 澶嶇敤鍒嗛〉淇℃伅锛岄伩鍏嶉噸澶?bean 杞崲
        Page<DeviceDetailsResultVO> resultPage = new Page<>(deviceIPage.getCurrent(), deviceIPage.getSize(), deviceIPage.getTotal());
        resultPage.setRecords(deviceDetailsResultVOS);
        return resultPage;
    }

    /**
     * 妫€鏌ユ槸鍚︽湁璁惧姝ｅ湪浣跨敤璇ヤ骇鍝?浜у搧鍒犻櫎 / 淇敼鍓嶇殑鍗犵敤鏍￠獙)銆?
     *
     * @throws IllegalArgumentException if the productIdentification is null or empty.
     */
    @Override
    public boolean isProductInUseByDevices(String productIdentification) {
        if (StrUtil.isBlank(productIdentification)) {
            throw BizException.wrap("Product identification cannot be null or empty.");
        }

        Query params = new Query<>();
        params.setModel(new DeviceDetailsPageQuery().setProductIdentification(productIdentification));
        IPage<Device> deviceIPage = superManager.getDeviceDetailsPage(params);
        return deviceIPage != null && !deviceIPage.getRecords().isEmpty();
    }

    /** MQTT鍗忚涓嬫煡璇㈣澶囦俊鎭?*/
    @Override
    public TopoQueryDeviceResultVO queryDeviceByMqtt(TopoQueryDeviceParam topoQueryDeviceParam) {
        return queryDeviceInfo(topoQueryDeviceParam);
    }

    /** 鍖楀悜API鏌ヨ璁惧淇℃伅 */
    @Override
    public TopoQueryDeviceResultVO queryDeviceByNorthbound(TopoQueryDeviceParam topoQueryDeviceParam) {
        return queryDeviceInfo(topoQueryDeviceParam);
    }

    /** 鏍规嵁鍙傛暟鏌ヨ璁惧淇℃伅 */
    private TopoQueryDeviceResultVO queryDeviceInfo(TopoQueryDeviceParam topoQueryDeviceParam) {
        TopoQueryDeviceResultVO topoQueryDeviceResultVO = new TopoQueryDeviceResultVO();

        List<String> deviceIds = Optional.ofNullable(topoQueryDeviceParam)
                .map(TopoQueryDeviceParam::getDeviceIds)
                .orElseGet(Collections::emptyList);

        // 鎵归噺鏌ヨ鎵€鏈夎澶囷紝閬垮厤 N+1
        List<String> distinctDeviceIds = deviceIds.stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<String, Device> deviceMap = Optional.of(distinctDeviceIds)
                .filter(CollUtil::isNotEmpty)
                .map(ids -> {
                    DevicePageQuery query = new DevicePageQuery();
                    query.setDeviceIdentificationList(ids);
                    List<Device> devices = superManager.getDevicList(query);
                    return Optional.ofNullable(devices).orElseGet(Collections::emptyList);
                })
                .map(devices -> devices.stream()
                        .filter(d -> d != null && d.getDeviceIdentification() != null)
                        .collect(Collectors.toMap(Device::getDeviceIdentification, Function.identity(), (a, b) -> a)))
                .orElseGet(Collections::emptyMap);

        List<TopoQueryDeviceResultVO.DataItem> deviceInfoList = distinctDeviceIds.stream()
                .map(deviceIdentification -> {
                    TopoQueryDeviceResultVO.DataItem dataItem = new TopoQueryDeviceResultVO.DataItem();
                    try {
                        dataItem.setDeviceId(deviceIdentification);
                        Optional<Device> optionalDevice = Optional.ofNullable(deviceMap.get(deviceIdentification));
                        TopoQueryDeviceResultVO.DataItem.DeviceInfo deviceInfo = optionalDevice
                                .map(device -> BeanUtil.toBean(device, TopoQueryDeviceResultVO.DataItem.DeviceInfo.class))
                                .orElse(new TopoQueryDeviceResultVO.DataItem.DeviceInfo());

                        dataItem.setDeviceInfo(deviceInfo)
                                .setStatusCode(optionalDevice.isPresent() ? MqttProtocolTopoStatusEnum.SUCCESS.getValue() : MqttProtocolTopoStatusEnum.FAILURE.getValue())
                                .setStatusDesc(optionalDevice.isPresent() ? MqttProtocolTopoStatusEnum.SUCCESS.getDesc() : "Device not found");
                    } catch (Exception e) {
                        dataItem.setStatusCode(MqttProtocolTopoStatusEnum.FAILURE.getValue())
                                .setStatusDesc("Error querying device: " + e.getMessage());
                    }
                    return dataItem;
                })
                .collect(Collectors.toList());

        topoQueryDeviceResultVO.setData(deviceInfoList)
                .setStatusCode(MqttProtocolTopoStatusEnum.SUCCESS.getValue())
                .setStatusDesc("Query completed");
        return topoQueryDeviceResultVO;
    }

    private void processDeviceSubDetails(DeviceDetailsResultVO device,
                                         Map<String, ProductResultVO> productResultVOMap,
                                         Map<String, DeviceLocationResultVO> deviceLocationResultVOMap,
                                         Map<String, List<DeviceResultVO>> subDeviceMap) {
        Optional.ofNullable(device)
                .ifPresent(d -> {
                    Optional.ofNullable(d.getProductIdentification())
                            .map(productResultVOMap::get)
                            .ifPresent(d::setProductResultVO);

                    Optional.ofNullable(d.getDeviceIdentification())
                            .map(deviceLocationResultVOMap::get)
                            .ifPresent(d::setDeviceLocationResultVO);

                    Optional.ofNullable(d.getNodeType())
                            .filter(DeviceNodeTypeEnum.GATEWAY.getValue()::equals)
                            .ifPresent(type ->
                                    d.setSubDeviceResultVOList(
                                            Optional.ofNullable(d.getDeviceIdentification())
                                                    .map(subDeviceMap::get)
                                                    .orElseGet(Collections::emptyList)
                                    )
                            );
                });
    }

    private Map<String, ProductResultVO> queryProductInfoForDevices(List<String> productIdentificationList) {
        return Optional.ofNullable(productIdentificationList)
                .filter(CollUtil::isNotEmpty)
                .map(productIdentification -> {
                    ProductPageQuery query = new ProductPageQuery().setProductIdentificationList(productIdentification);
                    return productQueryService.getProductResultVOList(query);
                })
                .map(list -> list.stream()
                        .filter(p -> p != null && p.getProductIdentification() != null)
                        .collect(Collectors.toMap(
                                ProductResultVO::getProductIdentification,
                                Function.identity(),
                                (a, b) -> {
                                    if (a.getCreatedTime() == null) return b;
                                    if (b.getCreatedTime() == null) return a;
                                    return a.getCreatedTime().isAfter(b.getCreatedTime()) ? a : b;
                                }
                        ))
                )
                .orElseGet(Collections::emptyMap);
    }

    private Map<String, DeviceLocationResultVO> queryDeviceLocationForDevices(List<String> deviceIdentificationList) {
        return Optional.ofNullable(deviceIdentificationList)
                .filter(CollUtil::isNotEmpty)
                .map(deviceIdentification -> deviceLocationService.getDeviceLocationResultVOList(
                        new DeviceLocationPageQuery().setDeviceIdentificationList(deviceIdentification)
                ))
                .map(list -> list.stream()
                        .filter(d -> d != null && d.getDeviceIdentification() != null)
                        .collect(Collectors.toMap(
                                DeviceLocationResultVO::getDeviceIdentification,
                                Function.identity(),
                                (a, b) -> {
                                    if (a.getCreatedTime() == null) return b;
                                    if (b.getCreatedTime() == null) return a;
                                    return a.getCreatedTime().isAfter(b.getCreatedTime()) ? a : b;
                                }
                        ))
                )
                .orElseGet(Collections::emptyMap);
    }

    /**
     * 鎵归噺鏌ヨ鎵€鏈夌綉鍏宠澶囩殑瀛愯澶囷紙瑙ｅ喅N+1鏌ヨ闂锛?
     */
    private Map<String, List<DeviceResultVO>> querySubDevicesForGateways(List<DeviceDetailsResultVO> deviceList) {
        return Optional.ofNullable(deviceList)
                .map(list -> list.stream()
                        .filter(d -> d != null && DeviceNodeTypeEnum.GATEWAY.getValue().equals(d.getNodeType()))
                        .map(DeviceDetailsResultVO::getDeviceIdentification)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList()))
                .filter(CollUtil::isNotEmpty)
                .map(gatewayIds -> {
                    DevicePageQuery query = new DevicePageQuery();
                    query.setGatewayIdList(gatewayIds);
                    query.setNodeType(DeviceNodeTypeEnum.SUBDEVICE.getValue());
                    return getDeviceResultVOList(query);
                })
                .map(subDevices -> subDevices.stream()
                        .filter(d -> d != null && d.getGatewayId() != null)
                        .collect(Collectors.groupingBy(DeviceResultVO::getGatewayId)))
                .orElseGet(Collections::emptyMap);
    }

    private List<DeviceResultVO> querySubDevices(String gatewayId) {
        DevicePageQuery devicePageQuery = new DevicePageQuery();
        devicePageQuery.setGatewayId(gatewayId);
        devicePageQuery.setNodeType(DeviceNodeTypeEnum.SUBDEVICE.getValue());
        return getDeviceResultVOList(devicePageQuery);
    }

    private Optional<DeviceLocationResultVO> queryDeviceLocation(String deviceIdentification) {
        DeviceLocationPageQuery deviceLocationPageQuery = new DeviceLocationPageQuery();
        deviceLocationPageQuery.setDeviceIdentification(deviceIdentification);
        List<DeviceLocationResultVO> deviceLocationResultVOList =
                deviceLocationService.getDeviceLocationResultVOList(deviceLocationPageQuery);
        return deviceLocationResultVOList.isEmpty() ? Optional.empty() : Optional.of(deviceLocationResultVOList.get(0));
    }

    /**
     * 鍗曡澶囪鎯呴〉鎷変骇鍝佷俊鎭?鈹€鈹€ 璧?{@link LinkCacheDataHelper#getProductCacheVO} 缂撳瓨璺緞
     * (read-through DB 鍏滃簳),閬垮厤姣忔璇︽儏璇锋眰閮界洿鏌?DB銆?
     *
     * <p>鍐欏墠缃牎楠?saveDevice / updateDevice 閲岀殑浜у搧瀛樺湪鎬ф牎楠?浠嶇洿璋?
     * {@link ProductQueryService#findOneByProductIdentification},纭繚 DB-fresh銆?/p>
     */
    private Optional<ProductResultVO> queryProductInfo(String productIdentification) {
        return linkCacheDataHelper.getProductCacheVO(productIdentification)
                .map(p -> BeanUtil.toBeanIgnoreError(p, ProductResultVO.class));
    }

    private TopoDeviceOperationResultVO deviceDataReport(TopoDeviceDataReportParam topoDeviceDataReportParam) {
        // 鎮ㄧ殑澶勭悊閫昏緫

        return TopoDeviceOperationResultVO.builder()
                .statusCode(MqttProtocolTopoStatusEnum.SUCCESS.getValue())
                .statusDesc(MqttProtocolTopoStatusEnum.SUCCESS.getDesc()).build();
    }

    private TopoDeviceOperationResultVO deleteSubDevice(TopoDeleteSubDeviceParam topoDeleteSubDeviceParam) {
        List<String> deviceIds = Optional.ofNullable(topoDeleteSubDeviceParam)
                .map(TopoDeleteSubDeviceParam::getDeviceIds)
                .orElseGet(Collections::emptyList);

        // 鎵归噺鏌ヨ鎵€鏈夎澶囷紝閬垮厤 N+1
        List<String> distinctDeviceIds = deviceIds.stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<String, Device> deviceMap = Optional.of(distinctDeviceIds)
                .filter(CollUtil::isNotEmpty)
                .map(ids -> {
                    DevicePageQuery query = new DevicePageQuery();
                    query.setDeviceIdentificationList(ids);
                    List<Device> devices = superManager.getDevicList(query);
                    return Optional.ofNullable(devices).orElseGet(Collections::emptyList);
                })
                .map(devices -> devices.stream()
                        .filter(d -> d != null && d.getDeviceIdentification() != null)
                        .collect(Collectors.toMap(Device::getDeviceIdentification, Function.identity(), (a, b) -> a)))
                .orElseGet(Collections::emptyMap);

        // 閫愪釜鍒犻櫎骞惰褰曠粨鏋?
        List<TopoDeviceOperationResultVO.OperationRsp> operationResultList = distinctDeviceIds.stream()
                .map(deviceId -> {
                    TopoDeviceOperationResultVO.OperationRsp operationRsp = new TopoDeviceOperationResultVO.OperationRsp()
                            .setDeviceId(deviceId);

                    Optional.ofNullable(deviceMap.get(deviceId))
                            .ifPresentOrElse(
                                    subDevice -> {
                                        boolean deleteFlag = superManager.removeById(subDevice);
                                        if (deleteFlag) {
                                            // 瀛愯澶?MQTT 鎷撴墤鍒犻櫎璺緞锛氫笌 deleteDevice(Long) 鍏辩敤浜嬩欢锛?
                                            // 鐢卞悇涓嬫父鍚屾鐩戝惉鍣ㄥ湪鍚屼竴浜嬪姟娓呯悊娈嬬暀寮曠敤銆?
                                            deviceEventPublisher.publishDeviceDeletedEvent(DeviceDeletedEventSource.builder()
                                                    .deviceId(subDevice.getId())
                                                    .deviceIdentification(subDevice.getDeviceIdentification())
                                                    .clientId(subDevice.getClientId())
                                                    .contextMap(AuthUtil.getLocalMap())
                                                    .build());
                                        }
                                        MqttProtocolTopoStatusEnum statusEnum = deleteFlag
                                                ? MqttProtocolTopoStatusEnum.SUCCESS
                                                : MqttProtocolTopoStatusEnum.FAILURE;
                                        operationRsp.setStatusCode(statusEnum.getValue())
                                                .setStatusDesc(statusEnum.getDesc());
                                    },
                                    () -> operationRsp.setStatusCode(MqttProtocolTopoStatusEnum.FAILURE.getValue())
                                            .setStatusDesc(MqttProtocolTopoStatusEnum.FAILURE.getDesc())
                            );

                    return operationRsp;
                })
                .collect(Collectors.toList());

        return TopoDeviceOperationResultVO.builder()
                .statusCode(MqttProtocolTopoStatusEnum.SUCCESS.getValue())
                .statusDesc(MqttProtocolTopoStatusEnum.SUCCESS.getDesc())
                .data(operationResultList)
                .build();
    }

    /** 鎵归噺鏇存柊瀛愯澶囪繛鎺ョ姸鎬佸苟璁板綍璁惧鍔ㄤ綔銆?*/
    private TopoDeviceOperationResultVO updateSubDeviceConnectStatus(TopoUpdateSubDeviceStatusParam topoUpdateSubDeviceStatusParam) {
        List<TopoDeviceOperationResultVO.OperationRsp> operationRsp = topoUpdateSubDeviceStatusParam.getDeviceStatuses().stream()
                .map(this::processSubDeviceStatus)
                .collect(Collectors.toList());

        return TopoDeviceOperationResultVO.builder()
                .statusCode(MqttProtocolTopoStatusEnum.SUCCESS.getValue())
                .statusDesc(MqttProtocolTopoStatusEnum.SUCCESS.getDesc())
                .data(operationRsp)
                .build();
    }

    /** 澶勭悊鍗曚釜瀛愯澶囩殑鐘舵€?鏇存柊骞惰褰曞姩浣溿€?*/
    private TopoDeviceOperationResultVO.OperationRsp processSubDeviceStatus(TopoUpdateSubDeviceStatusParam.DeviceStatus subDeviceStatus) {
        Device subDevice = superManager.findOneByDeviceIdentification(subDeviceStatus.getDeviceId());
        TopoDeviceOperationResultVO.OperationRsp dataItem = new TopoDeviceOperationResultVO.OperationRsp()
                .setDeviceId(subDeviceStatus.getDeviceId());

        if (subDevice != null) {
            // 鏇存柊璁惧杩炴帴鐘舵€?
            UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda()
                    .eq(Device::getId, subDevice.getId())
                    .set(Device::getConnectStatus, subDeviceStatus.getStatus().getValue())
                    .set(Device::getLastHeartbeatTime, LocalDateTime.now());
            boolean updateFlag = superManager.update(updateWrapper);
            recordDeviceAction(subDevice, subDeviceStatus.getStatus());

            MqttProtocolTopoStatusEnum updateStatusEnum = updateFlag ? MqttProtocolTopoStatusEnum.SUCCESS : MqttProtocolTopoStatusEnum.FAILURE;
            dataItem.setStatusCode(updateStatusEnum.getValue())
                    .setStatusDesc(updateStatusEnum.getDesc());
        } else {
            dataItem.setStatusCode(MqttProtocolTopoStatusEnum.FAILURE.getValue())
                    .setStatusDesc(MqttProtocolTopoStatusEnum.FAILURE.getDesc());
        }

        return dataItem;
    }

    /** 璁板綍璁惧鍔ㄤ綔骞惰惤搴撱€?*/
    private void recordDeviceAction(Device device, DeviceConnectStatusEnum connectStatus) {
        // 鏋勫缓璁惧鍔ㄤ綔鎻忚堪鍜岀被鍨?
        String describable = getDescriptionForStatus(connectStatus);
        DeviceActionTypeEnum actionType = getActionTypeForStatus(connectStatus);

        // 鏋勫缓骞朵繚瀛樿澶囧姩浣滆褰?
        DeviceActionSaveVO deviceActionSaveVO = new DeviceActionSaveVO();
        deviceActionSaveVO.setDeviceIdentification(device.getDeviceIdentification());
        deviceActionSaveVO.setActionType(actionType.getValue());
        deviceActionSaveVO.setMessage(actionType.getDesc());
        deviceActionSaveVO.setStatus(DeviceActionStatusEnum.SUCCESSFUL.getValue());
        deviceActionSaveVO.setRemark(describable);

        try {
            DeviceAction deviceAction = deviceActionService.saveDeviceAction(deviceActionSaveVO);
            log.info("Device action saved: {}", deviceAction);
        } catch (Exception e) {
            log.error("Failed to save device action for device ID: {}", device.getDeviceIdentification(), e);
        }
    }

    /** 鏍规嵁杩炴帴鐘舵€佺敓鎴愭弿杩版枃妗堛€?*/
    private String getDescriptionForStatus(DeviceConnectStatusEnum status) {
        String desc = Optional.ofNullable(status)
                .map(DeviceConnectStatusEnum::getDesc)
                .orElse("Unknown Status");

        return "The device connection status is updated to " + desc;
    }

    /** 鏍规嵁杩炴帴鐘舵€佺‘瀹氬姩浣滅被鍨嬨€?*/
    private DeviceActionTypeEnum getActionTypeForStatus(DeviceConnectStatusEnum status) {
        if (DeviceConnectStatusEnum.OFFLINE.equals(status)) {
            return DeviceActionTypeEnum.CLOSE;
        } else if (DeviceConnectStatusEnum.ONLINE.equals(status)) {
            return DeviceActionTypeEnum.CONNECT;
        } else {
            // Handle unexpected status here
            log.warn("Unexpected status: {}", status);
            return DeviceActionTypeEnum.UNKNOWN;
        }
    }

    /** 娣诲姞缃戝叧瀛愯澶?*/
    private TopoAddDeviceResultVO saveSubDevice(TopoAddSubDeviceParam topoAddSubDeviceParam) {
        // 鏍规嵁缃戝叧ID鏌ユ壘璁惧
        Device gatewayDevice = superManager.findOneByDeviceIdentification(topoAddSubDeviceParam.getGatewayIdentification());

        // 鍋囪 gatewayDevice.getType() 鏂规硶杩斿洖璁惧绫诲瀷锛屼笖 DeviceType.GATEWAY 浠ｈ〃缃戝叧璁惧绫诲瀷
        MqttProtocolTopoStatusEnum statusEnum = (gatewayDevice != null && DeviceNodeTypeEnum.GATEWAY.getValue().equals(gatewayDevice.getNodeType()))
                ? MqttProtocolTopoStatusEnum.SUCCESS
                : MqttProtocolTopoStatusEnum.FAILURE;

        // 鍒涘缓杩斿洖缁撴灉瀹炰緥骞惰缃姸鎬佺爜鍜岀姸鎬佹弿杩?
        TopoAddDeviceResultVO mqttTopoAddDeviceResultVO = TopoAddDeviceResultVO.builder()
                .statusCode(statusEnum.getValue())
                .statusDesc(statusEnum.getDesc())
                .build();

        // 鍒涘缓涓€涓澶囧垪琛ㄧ敤浜庡瓨鍌ㄥ鐞嗙粨鏋?
        List<TopoAddDeviceResultVO.DataItem> deviceList = new ArrayList<>();

        // 妫€鏌ヨ澶囦俊鎭垪琛ㄦ槸鍚︿负绌?
        List<TopoAddSubDeviceParam.DeviceInfos> deviceInfos = topoAddSubDeviceParam.getDeviceInfos();
        if (deviceInfos != null) {
            // 閬嶅巻娣诲姞璁惧鐨勫弬鏁颁俊鎭垪琛?
            for (TopoAddSubDeviceParam.DeviceInfos item : deviceInfos) {
                try {
                    // 鍒涘缓鏁版嵁椤瑰疄渚嬪苟楠岃瘉璁惧鍙傛暟
                    TopoAddDeviceResultVO.DataItem dataItem = new TopoAddDeviceResultVO.DataItem();
                    checkedTopoAddDeviceParam(item, dataItem);
                    // 灏嗗弬鏁板璞¤浆鎹负璁惧淇℃伅瀵硅薄骞惰缃埌鏁版嵁椤逛腑
                    dataItem.setDeviceInfo(BeanUtil.toBean(item, TopoAddDeviceResultVO.DataItem.DeviceInfo.class, CopyOptions.create().ignoreError()));

                    // 濡傛灉璁惧鍙傛暟楠岃瘉涓嶉€氳繃锛屾坊鍔犲埌璁惧鍒楄〃骞剁户缁笅涓€娆″惊鐜?
                    if (!MqttProtocolTopoStatusEnum.SUCCESS.getValue().equals(dataItem.getStatusCode())) {
                        deviceList.add(dataItem);
                        continue;
                    }

                    // 杞崲骞朵繚瀛樺瓙璁惧淇℃伅
                    Device subDeviceDO = conversionDeviceBySaveSubDevice(gatewayDevice, item);
                    boolean saveFlag = superManager.save(subDeviceDO);

                    // 瀛樺偍瀛愯澶囩粡绾害淇℃伅
                    DeviceLocationPageQuery deviceLocationPageQuery = new DeviceLocationPageQuery();
                    deviceLocationPageQuery.setDeviceIdentification(gatewayDevice.getDeviceIdentification());

                    List<DeviceLocationResultVO> deviceLocationResultVOList = deviceLocationService.getDeviceLocationResultVOList(deviceLocationPageQuery);

                    Optional.ofNullable(deviceLocationResultVOList)
                            .filter(list -> !list.isEmpty())
                            .map(list -> list.get(0))
                            .map(deviceLocationResultVO -> BeanUtil.toBeanIgnoreError(deviceLocationResultVO, DeviceLocationSaveVO.class))
                            .ifPresent(deviceLocationSaveVO -> {
                                deviceLocationSaveVO.setDeviceIdentification(subDeviceDO.getDeviceIdentification());
                                deviceLocationService.saveDeviceLocation(deviceLocationSaveVO);
                            });

                    // 璁剧疆骞冲彴鐢熸垚鐨勮澶囨爣璇?
                    dataItem.getDeviceInfo().setDeviceId(subDeviceDO.getDeviceIdentification());

                    // 鏍规嵁淇濆瓨缁撴灉璁剧疆鐘舵€佺爜鍜岀姸鎬佹弿杩?
                    MqttProtocolTopoStatusEnum saveStatusEnum = saveFlag ? MqttProtocolTopoStatusEnum.SUCCESS : MqttProtocolTopoStatusEnum.FAILURE;
                    dataItem.setStatusCode(saveStatusEnum.getValue())
                            .setStatusDesc(saveStatusEnum.getDesc());

                    // 娣诲姞鏁版嵁椤瑰埌璁惧鍒楄〃
                    deviceList.add(dataItem);

                    if (saveFlag) {
                        // 鍙戝竷璁惧淇℃伅鏇存柊浜嬩欢
                        deviceEventPublisher.publishDeviceInfoUpdatedEvent(DeviceInfoUpdatedEventSource.builder()
                                .deviceIdentificationList(Collections.singletonList(subDeviceDO.getDeviceIdentification()))
                                .build());
                    }
                } catch (Exception e) {
                    // 澶勭悊寮傚父鎯呭喌锛屽皢寮傚父淇℃伅璁剧疆鍒版暟鎹」涓?
                    TopoAddDeviceResultVO.DataItem dataItem = new TopoAddDeviceResultVO.DataItem();
                    dataItem.setStatusCode(MqttProtocolTopoStatusEnum.FAILURE.getValue())
                            .setStatusDesc(e.getMessage());
                    deviceList.add(dataItem);
                }
            }
        }

        // 灏嗚澶囧垪琛ㄨ缃埌杩斿洖缁撴灉瀹炰緥涓?
        mqttTopoAddDeviceResultVO.setData(deviceList);
        return mqttTopoAddDeviceResultVO;
    }

    /** 楠岃瘉 Topo 娣诲姞璁惧鍙傛暟,骞惰缃搴旂殑鐘舵€佺爜鍜岀姸鎬佹弿杩般€?*/
    private void checkedTopoAddDeviceParam(TopoAddSubDeviceParam.DeviceInfos item,
                                           TopoAddDeviceResultVO.DataItem dataItem) {
        // 鏍规嵁璁惧鏍囪瘑鏌ユ壘瀛愯澶?
        Device subDevice = superManager.findOneByDeviceIdentification(item.getNodeId());
        // 鐢ㄤ簬鎷兼帴閿欒娑堟伅鐨凷tringBuilder
        StringBuilder errorMessage = new StringBuilder();

        // 妫€鏌ュ悇鍙傛暟鏄惁涓虹┖锛屽苟灏嗛敊璇秷鎭拷鍔犲埌StringBuilder涓?
        appendErrorMessageIfEmpty(errorMessage, item.getName(), "name is null; ");
        appendErrorMessageIfEmpty(errorMessage, item.getNodeId(), "nodeId is null; ");
        appendErrorMessageIfEmpty(errorMessage, item.getManufacturerId(), "manufacturerId is null; ");
        appendErrorMessageIfEmpty(errorMessage, item.getModel(), "model is null; ");

        // 妫€鏌ヨ澶囪妭鐐笽D鏄惁宸茬粡瀛樺湪
        if (subDevice != null) {
            errorMessage.append("nodeId is exist; ");
        }

        // 鏍规嵁閿欒娑堟伅闀垮害鍒ゆ柇鏄惁鏈夐敊璇紝骞惰缃浉搴旂殑鐘舵€佺爜鍜岀姸鎬佹弿杩?
        if (!errorMessage.isEmpty()) {
            dataItem.setStatusCode(MqttProtocolTopoStatusEnum.FAILURE.getValue())
                    .setStatusDesc(errorMessage.toString());
        } else {
            dataItem.setStatusCode(MqttProtocolTopoStatusEnum.SUCCESS.getValue())
                    .setStatusDesc(MqttProtocolTopoStatusEnum.SUCCESS.getDesc());
        }
    }

    /** 鍙傛暟鍊间负绌烘椂鎶婇敊璇秷鎭拷鍔犲埌 StringBuilder銆?*/
    private void appendErrorMessageIfEmpty(StringBuilder errorMessage, CharSequence value, String message) {
        if (CharSequenceUtil.isEmpty(value)) {
            errorMessage.append(message);
        }
    }

    /** 缃戝叧瀛愯澶囪浆鎹负 Device DO */
    private Device conversionDeviceBySaveSubDevice(Device gatewayDevice, TopoAddSubDeviceParam.DeviceInfos item) {
        Device device = new Device();
        BeanUtil.copyProperties(gatewayDevice, device, CopyOptions.create().setIgnoreProperties("id"));
        device.setDeviceName(item.getName());
        device.setClientId(TenantUtil.buildOptionalItem(SnowflakeIdUtil.nextId(), TenantUtil.extractTenantId(device.getClientId())));
        device.setDeviceIdentification(item.getNodeId());
        device.setNodeType(DeviceNodeTypeEnum.SUBDEVICE.getValue());
        device.setGatewayId(gatewayDevice.getDeviceIdentification());
        device.setConnectStatus(DeviceConnectStatusEnum.UNCONNECTED.getValue());
        device.setDeviceStatus(DeviceStatusEnum.ACTIVATED.getValue());
        device.setPassword(gatewayDevice.getPassword());
        // TODO 浜у搧鍏宠仈澶勭悊,鏀寔澶氫骇鍝佸叧鑱旈粯璁や笉鍏宠仈缃戝叧璁惧浜у搧
        return device;
    }

    private Builder<Device> builderDeviceUpdateVO(DeviceUpdateVO updateVO) {

        return new Device()
                .with(Device::setUserName, updateVO.getUserName())
                .with(Device::setPassword, updateVO.getPassword())
                .with(Device::setCertSerialNumber, updateVO.getCertSerialNumber())
                .with(Device::setAppId, updateVO.getAppId())
                .with(Device::setAuthMode, updateVO.getAuthMode())
                .with(Device::setEncryptKey, updateVO.getEncryptKey())
                .with(Device::setEncryptVector, updateVO.getEncryptVector())
                .with(Device::setEncryptMethod, updateVO.getEncryptMethod())
                .with(Device::setSignKey, updateVO.getSignKey())
                .with(Device::setDeviceName, updateVO.getDeviceName())
                .with(Device::setConnector, updateVO.getConnector())
                .with(Device::setDescription, updateVO.getDescription())
                .with(Device::setDeviceStatus, updateVO.getDeviceStatus())
                .with(Device::setDeviceTags, updateVO.getDeviceTags())
                .with(Device::setSwVersion, updateVO.getSwVersion())
                .with(Device::setFwVersion, updateVO.getFwVersion())
                .with(Device::setDeviceSdkVersion, updateVO.getDeviceSdkVersion())
                .with(Device::setGatewayId, updateVO.getGatewayId())
                .with(Device::setProductIdentification, updateVO.getProductIdentification())
                .with(Device::setNodeType, updateVO.getNodeType())
                .with(Device::setRemark, updateVO.getRemark())
                .with(Device::setCreatedOrgId, AuthUtil.getCurrentDeptId());
    }

    /**
     * 鏋勫缓淇濆瓨鍙傛暟銆傝澶囨敞鍐屾椂榛樿鎶婁骇鍝?activeVersionNo 浣滀负 boundProductVersionNo 鍐欏叆,
     * 渚涘悗缁?TD 瓒呯骇琛ㄥ鍧€ / 鐗╂ā鍨嬭В鏋?saveVO 鏄惧紡甯︿簡 boundProductVersionNo(鐏板害鐧藉悕鍗曞鍏?鍒欎笉瑕嗙洊銆?
     *
     * @param productResultVO 宸叉牎楠岄€氳繃鐨勪骇鍝佷俊鎭?鐢ㄤ簬鍥炲～ boundProductVersionNo)
     */
    private Device builderDeviceSaveVO(DeviceSaveVO saveVO, ProductResultVO productResultVO) {
        Device device = BeanUtil.copyProperties(saveVO, Device.class);
        //璁惧clientId 鐢熸垚瑙勫垯: 鍞竴鏍囪瘑 + @ + 绉熸埛ID
        device.setClientId(TenantUtil.buildOptionalItem(SnowflakeIdUtil.nextId(), AuthUtil.getTenantIdStr()));
        //璁惧鏍囪瘑鐢熸垚瑙勫垯: 闆姳绠楁硶鐢熸垚
        device.setDeviceIdentification(String.valueOf(SnowflakeIdUtil.nextId()));
        device.setCreatedOrgId(AuthUtil.getCurrentDeptId());
        fillBoundProductVersionIfBlank(device, productResultVO);
        return device;
    }

    /**
     * 鎶婅澶?boundProductVersionNo 鍏滃簳鍥炲～涓轰骇鍝佸綋鍓?activeVersionNo銆?
     * 浠呭綋璁惧鏈樉寮忓甫鐗堟湰鍙锋椂鐢熸晥,閬垮厤瑕嗙洊澶栭儴浼犲叆鐨勭伆搴﹀彂甯冪櫧鍚嶅崟鍊笺€?
     *
     * @param productResultVO 浜у搧淇℃伅(鍙┖,绌烘椂璺宠繃濉厖)
     */
    private void fillBoundProductVersionIfBlank(Device device, ProductResultVO productResultVO) {
        if (device == null || productResultVO == null) {
            return;
        }
        // 璁惧宸叉樉寮忓甫鐗堟湰鍙?渚嬪鏂板 / 缂栬緫琛ㄥ崟鐢ㄦ埛涓诲姩閫?鎴栫伆搴︾櫧鍚嶅崟瀵煎叆) 鈫?淇濈暀鍏ュ弬涓嶈鐩?
        if (StrUtil.isNotBlank(device.getBoundProductVersionNo())) {
            return;
        }
        device.setBoundProductVersionNo(resolveBindVersionForNewDevice(productResultVO));
    }

    /**
     * 瑙ｆ瀽鏂拌澶囧簲缁戝畾鐨勭増鏈彿:鐏板害鍙戝竷杩涜鏈熼棿({@code previousFullVersionNo} 闈炵┖,鎸佹湁鍒囨崲鍓嶇殑绋冲畾鐗?
     * 缁?鏈€鏂板叏閲忕増鏈?(绋冲畾鐗?,閬垮厤鏂拌澶囪嚜鍔ㄨ惤杩涙湭楠岃瘉鐨勭伆搴︾粍銆佹妸鐏板害姣斾緥绋€閲?鏃犵伆搴︽椂缁戝綋鍓嶇敓鏁堢増鏈?
     * {@code activeVersionNo}銆傜伆搴︽檵鍗囦负鍏ㄩ噺鍚?{@code previousFullVersionNo} 琚竻绌?
     * (瑙?{@code ProductServiceImpl#switchActiveVersionForPublish}),鏂拌澶囬殢鍗宠嚜鍔ㄨ窡鍒版柊鐗堟湰銆?
     *
     * @param productResultVO 浜у搧淇℃伅(鎻愪緵 activeVersionNo / previousFullVersionNo)
     * @return 鏂拌澶囧簲缁戝畾鐨勭増鏈彿
     */
    private String resolveBindVersionForNewDevice(ProductResultVO productResultVO) {
        // previousFullVersionNo 闈炵┖(鐏板害鎬?鍙栫ǔ瀹氱増,鍚﹀垯鍙?activeVersionNo
        return StrUtil.blankToDefault(productResultVO.getPreviousFullVersionNo(), productResultVO.getActiveVersionNo());
    }

    /**
     * 鏍￠獙鏂板鍙傛暟銆?
     *
     * @return 鏍￠獙閫氳繃鐨勪骇鍝佷俊鎭?渚涜皟鐢ㄦ柟鍥炲～ boundProductVersionNo 浣跨敤)
     */
    private ProductResultVO checkedDeviceSaveVO(DeviceSaveVO saveVO) {
        //璁惧璁よ瘉妯″紡
        ArgumentAssert.notNull(saveVO.getAuthMode(), "authMode Cannot be null");
        ArgumentAssert.notBlank(saveVO.getUserName(), "userName Cannot be null");
        ArgumentAssert.notBlank(saveVO.getPassword(), "password Cannot be null");
        if (DeviceAuthModeEnum.SSL_MODE.getValue().equals(saveVO.getAuthMode())) {
            ArgumentAssert.notBlank(saveVO.getCertSerialNumber(), "certSerialNumber Cannot be null");
        }

        //搴旂敤ID
        ArgumentAssert.notBlank(saveVO.getAppId(), "appId Cannot be null");

        //璁惧鍗忚鍔犲瘑鏂瑰紡
        ArgumentAssert.notBlank(saveVO.getSignKey(), "signKey Cannot be null");
        ArgumentAssert.notNull(saveVO.getEncryptMethod(), "encryptMethod Cannot be null");
        if (DeviceEncryptMethodEnum.AES256.getValue().equals(saveVO.getEncryptMethod()) || DeviceEncryptMethodEnum.SM4.getValue().equals(saveVO.getEncryptMethod())) {
            ArgumentAssert.notBlank(saveVO.getEncryptKey(), "encryptKey Cannot be null");
            ArgumentAssert.notBlank(saveVO.getEncryptVector(), "The key vector cannot be empty.");

        }

        //璁惧鐘舵€?
        ArgumentAssert.notNull(saveVO.getDeviceStatus(), "deviceStatus Cannot be null");
        if (!DeviceStatusEnum.ALL_STATE_COLLECTION.contains(saveVO.getDeviceStatus())) {
            throw BizException.wrap("DeviceStatusEnum is not exist");
        }

        //璁惧绫诲瀷
        DeviceNodeTypeEnum.fromValue(saveVO.getNodeType()).orElseThrow(() -> BizException.wrap("deviceNodeType is not exist"));

        //瀛愯澶囨牎楠岋細濡傛灉鏄瓙璁惧锛岀綉鍏宠澶嘔D涓嶈兘涓虹┖
        if (DeviceNodeTypeEnum.SUBDEVICE.getValue().equals(saveVO.getNodeType())) {
            ArgumentAssert.notBlank(saveVO.getGatewayId(), "The gateway device ID of the sub-device cannot be empty.");
        }

        //浜у搧鏍囪瘑鏍￠獙锛氭牎楠屼骇鍝佹槸鍚﹀瓨鍦?
        ArgumentAssert.notBlank(saveVO.getProductIdentification(), "productIdentification Cannot be null");
        ProductResultVO productResultVO = productQueryService.findOneByProductIdentification(saveVO.getProductIdentification());
        ArgumentAssert.notNull(productResultVO, "productIdentification is not exist");
        // 鏂板缓鏃剁敤鎴?澶栭儴鏄惧紡閫変簡缁戝畾鐗堟湰(鍚奖瀛?鈫?鏍￠獙鍙敤;鐣欑┖璧?fillBoundProductVersionIfBlank 榛樿(婵€娲荤増/绋冲畾鐗?
        if (StrUtil.isNotBlank(saveVO.getBoundProductVersionNo())) {
            assertSwitchableTargetVersion(saveVO.getProductIdentification(), saveVO.getBoundProductVersionNo());
        }
        return productResultVO;
    }

    /** 鏍￠獙鏇存柊鍙傛暟 */
    private void checkedDeviceUpdateVO(DeviceUpdateVO updateVO) {

        ArgumentAssert.notNull(updateVO.getId(), "id Cannot be null");

        ArgumentAssert.notBlank(updateVO.getUserName(), "userName Cannot be null");
        ArgumentAssert.notBlank(updateVO.getPassword(), "password Cannot be null");
        if (DeviceAuthModeEnum.SSL_MODE.getValue().equals(updateVO.getAuthMode())) {
            ArgumentAssert.notBlank(updateVO.getCertSerialNumber(), "certSerialNumber Cannot be null");
        }

        //搴旂敤ID
        ArgumentAssert.notBlank(updateVO.getAppId(), "appId Cannot be null");

        //璁惧鍗忚鍔犲瘑鏂瑰紡
        ArgumentAssert.notBlank(updateVO.getSignKey(), "signKey Cannot be null");
        ArgumentAssert.notNull(updateVO.getEncryptMethod(), "encryptMethod Cannot be null");
        if (DeviceEncryptMethodEnum.AES256.getValue().equals(updateVO.getEncryptMethod()) || DeviceEncryptMethodEnum.SM4.getValue().equals(updateVO.getEncryptMethod())) {
            ArgumentAssert.notBlank(updateVO.getEncryptKey(), "encryptKey Cannot be null");
            ArgumentAssert.notBlank(updateVO.getEncryptVector(), "encryptVector Cannot be null");
        }

        //璁惧鐘舵€?
        ArgumentAssert.notNull(updateVO.getDeviceStatus(), "deviceStatus Cannot be null");
        if (!DeviceStatusEnum.ALL_STATE_COLLECTION.contains(updateVO.getDeviceStatus())) {
            throw BizException.wrap("DeviceStatusEnum is not exist");
        }

        //璁惧绫诲瀷
        DeviceNodeTypeEnum.fromValue(updateVO.getNodeType()).orElseThrow(() -> BizException.wrap("deviceNodeType is not exist"));

        //瀛愯澶囨牎楠岋細濡傛灉鏄瓙璁惧锛岀綉鍏宠澶嘔D涓嶈兘涓虹┖
        if (DeviceNodeTypeEnum.SUBDEVICE.getValue().equals(updateVO.getNodeType())) {
            ArgumentAssert.notBlank(updateVO.getGatewayId(), "The gateway device ID of the sub-device cannot be empty.");
        }

        //浜у搧鏍囪瘑鏍￠獙锛氭牎楠屼骇鍝佹槸鍚﹀瓨鍦?
        ArgumentAssert.notBlank(updateVO.getProductIdentification(), "productIdentification Cannot be null");
        ProductResultVO productResultVO = productQueryService.findOneByProductIdentification(updateVO.getProductIdentification());
        ArgumentAssert.notNull(productResultVO, "productIdentification is not exist");

    }

    @Override
    public Boolean reportDeviceHeartbeat(String clientIdentifier, Long heartbeatTime, Long eventHlc) {
        //鏍规嵁瀹㈡埛绔爣璇嗙鏌ヨ璁惧缂撳瓨淇℃伅
        Device device = superManager.findOneByClientId(clientIdentifier);
        if (Objects.isNull(device)) {
            throw BizException.wrap("瀹㈡埛绔爣璇?{} 璁惧妗ｆ淇℃伅涓嶅瓨鍦?, clientIdentifier);
        }
        try {
            // 1. 蹇冭烦鏃堕棿:鏃犳潯浠舵洿鏂?姣忔潯蹇冭烦閮界画 last_heartbeat_time)
            Device updateDO = new Device();
            updateDO.setId(device.getId());
            LocalDateTime heartbeatDateTime = (heartbeatTime != null)
                    ? DateUtil.date(heartbeatTime).toLocalDateTime()
                    : LocalDateTime.now();
            updateDO.setLastHeartbeatTime(heartbeatDateTime);
            superManager.updateById(updateDO);
            // 2. 鍦ㄧ嚎鐘舵€?璧?eventHlc HLC CAS 鍗曡皟鍐欑疆 ONLINE(鏇夸唬鍘熺洿鍐?闃茶繜鍒?涔卞簭浜嬩欢鎶婂凡绂荤嚎缈诲洖鍦ㄧ嚎);
            //    eventHlc 缂哄け/闈炴硶鍒欎笉鍔ㄧ姸鎬?浜ょ敱鍏跺畠甯?hlc 鐨勭敓鍛藉懆鏈熶簨浠剁淮鎶?CONNECT/DISCONNECT 绛?銆?
            //    绫荤骇 @DS/@Transactional 瑕嗙洊,鍐呴儴 this 璋冪敤鍚屾暟鎹簮銆佸悓浜嬪姟銆?
            if (eventHlc != null && eventHlc > 0) {
                updateDeviceConnectionStatusByEvent(clientIdentifier, DeviceConnectStatusEnum.ONLINE.getValue(), eventHlc);
            }
            return true;
        } catch (Exception e) {
            log.error("涓婃姤璁惧蹇冭烦澶辫触,clientIdentifier:{}", clientIdentifier, e);
            return false;
        }
    }

    @Override
    public Long countByCertSerialNumber(String certSerialNumber) {
        return superManager.count(Wrappers.<Device>lbQ()
                .eq(Device::getCertSerialNumber, certSerialNumber));
    }

    @Override
    public Long countOnlineByCertSerialNumber(String certSerialNumber) {
        return superManager.count(Wrappers.<Device>lbQ()
                .eq(Device::getCertSerialNumber, certSerialNumber)
                .eq(Device::getConnectStatus, DeviceConnectStatusEnum.ONLINE.getValue()));
    }

    @Override
    public List<Device> listTopBoundDevicesByCertSerialNumber(String certSerialNumber, int limit) {
        return superManager.list(Wrappers.<Device>lbQ()
                .eq(Device::getCertSerialNumber, certSerialNumber)
                .orderByDesc(Device::getLastHeartbeatTime)
                .last("LIMIT " + limit));
    }

    // 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€ 浜у搧鐗堟湰鍙戝竷:璁惧鏀圭粦 service 鍏ュ彛 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€
    // 杩?3 涓柟娉曡蛋 service 鑰岄潪鐩存帴 Manager 鐨勫師鍥?
    //   1. @DS(BASE_TENANT) 鍒囧簱 AOP 鍦?Service 灞?鈹€鈹€ Manager 鏃?@DS,璺ㄥ煙鐩磋皟浼?fallback 鍒伴粯璁ゅ簱,
    //      UPDATE 璺ㄧ鎴蜂覆鍛虫垨鎶?"Table 'thinglinks_ds_c_defaults.device' doesn't exist"
    //   2. 涓嶅姞 @Transactional 鈹€鈹€ dynamic-datasource 璺?@Transactional 鍐茬獊:浜嬪姟寮€鍚細閿佸畾褰撳墠 DS,
    //      鍚庣画 @DS SPEL 閲嶆柊姹傚€间笉鐢熸晥銆傝繖閲屾槸鍗?UPDATE,InnoDB 鍗?SQL 鏈韩鍘熷瓙,鏃犻渶浜嬪姟鍖呰９

    @Override
    public int bulkRebindByIdentificationsIncludingSubDevices(List<String> rootIdentifications,
                                                              String productIdentification, String toVersion) {
        int affected = superManager.bulkRebindByIdentificationsIncludingSubDevices(
                rootIdentifications, productIdentification, toVersion);
        // 鐏板害鎸夌綉鍏崇矑搴︽敼缁戜細杩炲甫瀛愯澶?鏈湪 rootIdentifications 鍐?,鎸?productIdentification 澶辨晥缂撳瓨浠ヨ鐩栧叏閮?
        deviceEventPublisher.publishDeviceRebindEvent(DeviceRebindEventSource.builder()
                .productIdentification(productIdentification)
                .toVersion(toVersion)
                .contextMap(AuthUtil.getLocalMap())
                .build());
        return affected;
    }

    @Override
    public int bulkRebindByIds(List<Long> ids, List<String> identifications, String toVersion) {
        int affected = superManager.bulkRebindByIds(ids, toVersion);
        // 浠呭け鏁堟湰鎵规爣璇嗙紦瀛?鎸?identifications 绮剧‘澶辨晥,閬垮厤鎸変骇鍝佸垪鍏ㄩ噺鍐嶉€愪釜澶辨晥鐨勬斁澶?
        if (CollUtil.isNotEmpty(identifications)) {
            deviceEventPublisher.publishDeviceRebindEvent(DeviceRebindEventSource.builder()
                    .deviceIdentifications(identifications)
                    .toVersion(toVersion)
                    .contextMap(AuthUtil.getLocalMap())
                    .build());
        }
        return affected;
    }

    @Override
    public int bulkRebindByProductAndVersion(String productIdentification, String fromVersion, String toVersion) {
        int affected = superManager.bulkRebindByProductAndVersion(productIdentification, fromVersion, toVersion);
        // 鍥炴粴 / 鐏板害鏅嬪崌鏀圭粦:鍙戞敼缁戜簨浠?鐩戝惉鍣ㄥけ鏁堣浜у搧涓嬭澶囩紦瀛?
        deviceEventPublisher.publishDeviceRebindEvent(DeviceRebindEventSource.builder()
                .productIdentification(productIdentification)
                .toVersion(toVersion)
                .contextMap(AuthUtil.getLocalMap())
                .build());
        return affected;
    }

    @Override
    public int switchBoundProductVersion(String productIdentification, List<String> deviceIdentifications, String targetVersionNo) {
        if (StrUtil.isBlank(productIdentification) || CollUtil.isEmpty(deviceIdentifications) || StrUtil.isBlank(targetVersionNo)) {
            throw BizException.wrap("鍒囨崲璁惧缁戝畾鐗堟湰鍙傛暟涓嶅畬鏁?productIdentification / deviceIdentifications / targetVersionNo 鍧囧繀濉?);
        }
        assertSwitchableTargetVersion(productIdentification, targetVersionNo);
        // 澶嶇敤鐜版垚"鎸夋爣璇嗘敼缁?杩炲甫瀛愯澶?"閾捐矾:UPDATE 鏀跺彛 product_identification,鏀圭粦鍚庡彂 DeviceRebindEvent 澶辨晥缂撳瓨
        int affected = bulkRebindByIdentificationsIncludingSubDevices(deviceIdentifications, productIdentification, targetVersionNo);
        if (affected == 0) {
            log.warn("[switch-bound-version] no device matched product={} identifications={} toVersion={}",
                    productIdentification, deviceIdentifications, targetVersionNo);
        } else {
            log.info("[switch-bound-version] ok product={} toVersion={} affected={}", productIdentification, targetVersionNo, affected);
        }
        return affected;
    }

    /**
     * 鏍￠獙"璁惧缁戝畾鐩爣鐗堟湰"鍙敤:椤诲瓨鍦ㄤ簬璇ヤ骇鍝?涓斿浜?宸插彂甯?鐏板害/褰卞瓙 鐘舵€?鈹€鈹€ 浠呰繖浜涚姸鎬佺殑 TD 瓒呰〃宸插缓濂?
     * 缁戣繃鍘绘墠鏈夎〃鍙啓;DRAFT 鏈缓琛ㄣ€丷OLLED_BACK/ARCHIVED 鍙兘宸茶 purge 娓呯悊(drop stable),缁戣繃鍘讳笂鎶ヤ細
     * 寤哄瓙琛ㄥけ璐?/ 钀界┖琛ㄣ€備笉鍚堟硶鎶?{@link BizException}銆傛柊寤?/ 缂栬緫 / 鍒囨崲涓夊澶嶇敤鍚屼竴鏍￠獙鍙ｅ緞銆?
     *
     * @param productIdentification 浜у搧鏍囪瘑
     * @param versionNo             鐩爣鐗堟湰鍙?
     */
    private void assertSwitchableTargetVersion(String productIdentification, String versionNo) {
        Integer versionStatus = productVersionQueryService
                .findByProductIdentificationAndVersionNo(productIdentification, versionNo)
                .map(ProductVersion::getVersionStatus)
                .orElseThrow(() -> BizException.wrap(
                        "鐩爣鐗堟湰涓嶅瓨鍦?productIdentification=" + productIdentification + ", versionNo=" + versionNo));
        boolean switchable = ProductVersionStatusEnum.PUBLISHED.getValue().equals(versionStatus)
                || ProductVersionStatusEnum.CANARY.getValue().equals(versionStatus)
                || ProductVersionStatusEnum.SHADOW.getValue().equals(versionStatus);
        if (!switchable) {
            String desc = ProductVersionStatusEnum.fromValue(versionStatus)
                    .map(ProductVersionStatusEnum::getDesc).orElse(String.valueOf(versionStatus));
            throw BizException.wrap("鐩爣鐗堟湰鐘舵€佷笉鍙垏鎹?闇€ 宸插彂甯?鐏板害/褰卞瓙,褰撳墠=" + desc + "):versionNo=" + versionNo);
        }
    }

    @Override
    public DeviceVersionDistributionVO countDeviceVersionDistribution(String productIdentification) {
        Map<String, Long> versionCounts = new HashMap<>();
        long total = 0L;
        if (StrUtil.isNotBlank(productIdentification)) {
            // 涓€娆″垎缁勭粺璁¤浜у搧涓嬪悇 bound_product_version_no 鐨勮澶囨暟(MyBatis-Plus listMaps 鑷姩甯﹂€昏緫鍒犻櫎鏉′欢)
            List<Map<String, Object>> rows = superManager.listMaps(
                    Wrappers.<Device>query()
                            .select("bound_product_version_no AS versionNo", "COUNT(*) AS deviceCount")
                            .eq("product_identification", productIdentification)
                            .groupBy("bound_product_version_no"));
            for (Map<String, Object> row : rows) {
                Object versionVal = rowValueIgnoreCase(row, "versionNo");
                long cnt = toLongValue(rowValueIgnoreCase(row, "deviceCount"));
                versionCounts.put(versionVal == null ? "" : String.valueOf(versionVal), cnt);
                total += cnt;
            }
        }
        DeviceVersionDistributionVO vo = new DeviceVersionDistributionVO();
        vo.setTotal(total);
        vo.setVersionCounts(versionCounts);
        return vo;
    }

    /** 澶у皬鍐欎笉鏁忔劅鍦颁粠 listMaps 琛屽彇鍒楀€?鈹€鈹€ 鍒楀埆鍚嶅ぇ灏忓啓鍙兘闅忔暟鎹簱鏂硅█娉㈠姩銆?*/
    private static Object rowValueIgnoreCase(Map<String, Object> row, String key) {
        Object v = row.get(key);
        if (v != null) {
            return v;
        }
        for (Map.Entry<String, Object> e : row.entrySet()) {
            if (e.getKey() != null && e.getKey().equalsIgnoreCase(key)) {
                return e.getValue();
            }
        }
        return null;
    }

    /** count(*) 鍦ㄤ笉鍚岄┍鍔ㄥ彲鑳借繑鍥?Long / BigInteger / BigDecimal,缁熶竴杞?long銆?*/
    private static long toLongValue(Object v) {
        return v instanceof Number ? ((Number) v).longValue() : 0L;
    }

}
