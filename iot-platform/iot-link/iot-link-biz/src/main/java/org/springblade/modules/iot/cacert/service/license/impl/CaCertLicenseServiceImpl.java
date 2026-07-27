package org.springblade.modules.iot.cacert.service.license.impl;
import org.springblade.modules.iot.D:workspaceIOTiot_bladex_v1.0iot-platformiot-linkiot-link-bizsrcmainjavaorgspringblademodulesiotcacertservicelicenseimplCaCertLicenseServiceImpl.java.mapper.CaCertLicenseMapper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.mp.base.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.modules.iot.cacert.dto.SubjectObjectDN;
import org.springblade.modules.iot.cacert.entity.license.CaCertLicense;
import org.springblade.modules.iot.cacert.event.CaRevokedEvent;
import org.springblade.modules.iot.cacert.enumeration.CaCertAlgorithmEnum;
import org.springblade.modules.iot.cacert.enumeration.CaCertAuditTypeEnum;
import org.springblade.modules.iot.cacert.enumeration.CaCertSignAlgorithmEnum;
import org.springblade.modules.iot.cacert.enumeration.CaCertStatusEnum;
import org.springblade.modules.iot.cacert.service.audit.CaCertAuditLogService;
import org.springblade.modules.iot.cacert.service.license.CaCertLicenseService;
import org.springblade.modules.iot.cacert.vo.result.license.CaCertLicenseImpactResultVO;
import org.springblade.modules.iot.cacert.vo.result.license.CaCertLicenseResultVO;
import org.springblade.modules.iot.cacert.vo.save.license.CaCertLicenseSaveVO;
import org.springblade.modules.iot.cacert.vo.save.license.CaCertPemImportSaveVO;
import org.springblade.modules.iot.cacert.vo.update.license.CaCertLicenseUpdateVO;
import org.springblade.modules.iot.common.constant.DsConstant;
import org.springblade.modules.iot.device.entity.Device;
import org.springblade.modules.iot.device.service.DeviceQueryService;
import org.springblade.modules.iot.common.utils.FileUploadUtils;
import org.springblade.modules.iot.common.utils.FreeMarkerUtil;
import org.springblade.modules.iot.utils.x509.CertSerialNumberUtil;
import org.springblade.modules.iot.utils.x509.X509Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 涓氬姟瀹炵幇绫?
 * CA璁稿彲璇佽瘉涔﹁〃
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-27 15:48:10
 */
@Slf4j
@AllArgsConstructor
@Service
public class CaCertLicenseServiceImpl extends BaseServiceImpl<CaCertLicenseMapper, CaCertLicense> implements CaCertLicenseService {

    private final FileFacade fileApi;

    private final DeviceQueryService deviceQueryService;

    private final ApplicationEventPublisher eventPublisher;

    private final CaCertAuditLogService auditLogService;

    @Override
    protected <UpdateVO> CaCertLicense updateBefore(UpdateVO vo) {
        CaCertLicenseUpdateVO updateVO = (CaCertLicenseUpdateVO) vo;

        checkUpdateVO(updateVO);

        return super.updateBefore(updateVO);
    }

    private void checkUpdateVO(CaCertLicenseUpdateVO updateVO) {
        CaCertLicense caCertLicense = superManager.getById(updateVO.getId());
        ArgumentAssert.notNull(caCertLicense, "璇佷功涓嶅瓨鍦?");
    }

    @Override
    protected <SaveVO> CaCertLicense saveBefore(SaveVO vo) {
        CaCertLicenseSaveVO saveVO = (CaCertLicenseSaveVO) vo;

        checkSaveVO(saveVO);

        saveVO.setState(CaCertStatusEnum.PENDING.getValue());
        return super.saveBefore(saveVO);
    }

    private void checkSaveVO(CaCertLicenseSaveVO saveVO) {
        if (superManager.count(Wrappers.<CaCertLicense>lbQ()
                .eq(CaCertLicense::getCommonName, saveVO.getCommonName())
                .eq(CaCertLicense::getOrganization, saveVO.getOrganization())
                .eq(CaCertLicense::getOrganizationalUnit, saveVO.getOrganizationalUnit())
                .eq(CaCertLicense::getCountryName, saveVO.getCountryName())
                .eq(CaCertLicense::getProvinceName, saveVO.getProvinceName())
                .eq(CaCertLicense::getLocalityName, saveVO.getLocalityName())
                .eq(CaCertLicense::getState, CaCertStatusEnum.ISSUED.getValue())
        ) > 0) {
            throw BizException.wrap("璇ヤ富浣撳凡瀛樺湪宸查鍙戠殑璇佷功!");
        }
    }

    @Override
    protected <SaveVO> void saveAfter(SaveVO saveVO, CaCertLicense entity) {
//        superManager.refreshCache(Collections.singletonList(entity));
    }

    @Override
    protected <UpdateVO> void updateAfter(UpdateVO updateVO, CaCertLicense entity) {
//        superManager.refreshCache(Collections.singletonList(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CaCertLicenseResultVO importPemCertificate(CaCertPemImportSaveVO caCertPemImportSaveVO) {
        try {
            // 瑙ｆ瀽CA璇佷功
            X509Certificate rootCert = X509Util.parseRootCertificate(caCertPemImportSaveVO.getCaCertPem());

            // 鏋勫缓瀛樺偍瀹炰綋
            CaCertLicense entity = buildCertificateEntity(caCertPemImportSaveVO.getCertName(), rootCert, caCertPemImportSaveVO.getRemark());

            // 淇濆瓨鍒版暟鎹簱
            superManager.save(entity);

            // 瀹¤
            auditLogService.record(CaCertAuditTypeEnum.IMPORT, entity.getId(), entity.getSerialNumber(),
                    "name=" + entity.getCertName());

            //杩斿洖鏍囧噯鍖朧O
            return BeanUtil.toBean(entity, CaCertLicenseResultVO.class);
        } catch (Exception e) {
            throw BizException.wrap(e.getMessage());
        }

    }

    /**
     * 鏋勫缓璇佷功瀛樺偍瀹炰綋
     *
     * @param certName 璇佷功鍚嶇О
     * @param rootCert 鏍硅瘉涔?
     * @param remark   澶囨敞淇℃伅
     * @return 鏋勫缓濂界殑璇佷功瀹炰綋
     */
    private CaCertLicense buildCertificateEntity(String certName, X509Certificate rootCert, String remark) {
        CaCertLicense entity = new CaCertLicense();
        log.info("璇佷功鍚嶇О: {}, 鍝佺墝: {},version: {}", certName, rootCert.getIssuerX500Principal().getName(), rootCert.getVersion());
        String serialHex = CertSerialNumberUtil.getOpenSSLSerial(rootCert);
        CaCertLicense caCertLicense = superManager.getByCertSerialNumber(serialHex);
        ArgumentAssert.isNull(caCertLicense, "璇佷功宸插瓨鍦紝璇佷功搴忓垪鍙? {}", serialHex);
        try {
            PublicKey publicKey = rootCert.getPublicKey();
            // 鍩虹淇℃伅
            entity.setCertName(certName);
            entity.setIssuerCommonName(rootCert.getIssuerX500Principal().getName());
            entity.setSerialNumber(serialHex);
            entity.setCaCertPem(X509Util.certificateToPem(rootCert));
            entity.setThumbprint(X509Util.getFingerPrint(rootCert));
            entity.setLicenseBase64(X509Util.toBase64(rootCert));
            entity.setRemark(remark);
            entity.setNotBefore(DateUtils.date2LocalDateTime(rootCert.getNotBefore()));
            entity.setNotAfter(DateUtils.date2LocalDateTime(rootCert.getNotAfter()));
            SubjectObjectDN subjectObjectDN = SubjectObjectDN.parseSubjectDN(rootCert);
            entity.setCommonName(subjectObjectDN.getCommonName());
            entity.setOrganization(subjectObjectDN.getOrganization());
            entity.setOrganizationalUnit(subjectObjectDN.getOrganizationalUnit());
            entity.setCountryName(subjectObjectDN.getCountryName());
            entity.setProvinceName(subjectObjectDN.getProvinceName());
            entity.setLocalityName(subjectObjectDN.getLocalityName());
            entity.setEmail(subjectObjectDN.getEmail());
            entity.setState(CaCertStatusEnum.ISSUED.getValue());

            // 鏍规嵁绠楁硶绫诲瀷鎻愬彇瀵嗛挜鍙傛暟
            String algorithm = publicKey.getAlgorithm();
            CaCertAlgorithmEnum algorithmEnum = CaCertAlgorithmEnum.fromDesc(algorithm)
                    .orElseThrow(() -> new BizException("涓嶆敮鎸佺殑瀵嗛挜绠楁硶: " + algorithm));
            entity.setAlgorithm(algorithmEnum.getValue());
            CaCertSignAlgorithmEnum signAlgorithmEnum = CaCertSignAlgorithmEnum.fromDesc(rootCert.getSigAlgName())
                    .orElseThrow(() -> new BizException("涓嶆敮鎸佺殑绛惧悕绠楁硶: " + rootCert.getSigAlgName()));
            entity.setSignAlgorithm(signAlgorithmEnum.getValue());

        } catch (Exception e) {
            log.error("buildCertificateEntity 璇佷功瀹炰綋鏋勫缓澶辫触:{}", e.getMessage(), e);
            throw new BizException("璇佷功瀹炰綋鏋勫缓澶辫触: " + e.getMessage());
        }
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CaCertLicenseResultVO issueCertificate(Long id, LocalDateTime notAfter) {
        CaCertLicense caCertLicense = getById(id);
        ArgumentAssert.notNull(caCertLicense, "璇佷功涓嶅瓨鍦?");
        ArgumentAssert.isTrue(CaCertStatusEnum.PENDING.getValue().equals(caCertLicense.getState()), "璇佷功鐘舵€佷笉鍚堟硶!");

        // 鑾峰彇绠楁硶鏋氫妇
        CaCertAlgorithmEnum algorithm = CaCertAlgorithmEnum.fromValue(caCertLicense.getAlgorithm())
                .orElseThrow(() -> new BizException("涓嶆敮鎸佺殑璇佷功绠楁硶绫诲瀷"));

        LocalDateTime notBefore = LocalDateTime.now();
        File tempCertFile = null;
        try {
            // 鏋勫缓CA璇佷功涓婚淇℃伅
            SubjectObjectDN subjectDN = buildSubjectDN(caCertLicense);

            PublicKey publicKey;
            // 鑷畾涔塕SA鍏挜
            if (CaCertAlgorithmEnum.RSA.equals(algorithm)) {
                publicKey = X509Util.customRSAPublicKey(caCertLicense.getParam1(), caCertLicense.getParam2());
            } else {
                publicKey = X509Util.customECPublicKey(caCertLicense.getParam1(), caCertLicense.getParam2());
            }

            // 鐢熸垚璇佷功锛堜娇鐢↗caX509v3CertificateBuilder锛?
            X509Certificate rootCert = X509Util.generateRootCert(3, subjectDN, publicKey,
                    DateUtils.localDateTime2Date(notBefore),
                    DateUtils.localDateTime2Date(notAfter));

            tempCertFile = FileUploadUtils.createTempFile("root_", ".cer");
            FileUtil.writeBytes(rootCert.getEncoded(), tempCertFile);
            MultipartFile multipartFile = FileUploadUtils.toMultipartFile(tempCertFile);
            FileResultVO uploadResult = fileApi.upload(
                    multipartFile,
                    AppendixType.Link.BASE__CA__CERT__CONTENT,
                    StringPool.EMPTY,
                    null
            );

            ArgumentAssert.notNull(uploadResult, "璇佷功鏂囦欢涓婁紶澶辫触,璇烽噸璇?");

            // 鏇存柊璇佷功淇℃伅
            CaCertLicenseUpdateVO caCertLicenseUpdateVO = new CaCertLicenseUpdateVO();
            caCertLicenseUpdateVO.setId(caCertLicense.getId());
            caCertLicenseUpdateVO.setSerialNumber(rootCert.getSerialNumber().toString());
            caCertLicenseUpdateVO.setNotBefore(notBefore);
            caCertLicenseUpdateVO.setNotAfter(notAfter);
            caCertLicenseUpdateVO.setState(CaCertStatusEnum.ISSUED.getValue());
            caCertLicenseUpdateVO.setLicenseBase64(X509Util.toBase64(rootCert));
            caCertLicenseUpdateVO.setCertFileid(uploadResult.getId().toString());
            caCertLicenseUpdateVO.setThumbprint(X509Util.getFingerPrint(rootCert));
            updateById(caCertLicenseUpdateVO);
            return BeanUtil.toBeanIgnoreError(getById(caCertLicense.getId()), CaCertLicenseResultVO.class);
        } catch (Exception e) {
            log.error("璇佷功鐢熸垚澶辫触", e);
            throw new BizException("璇佷功鐢熸垚澶辫触: " + e.getMessage());
        } finally {
            // 鍒犻櫎涓存椂鏂囦欢
            if (tempCertFile != null) {
                FileUtil.del(tempCertFile);
            }
        }
    }

    /**
     * 鏋勫缓璇佷功涓讳綋淇℃伅
     * Country (C) 鈫?State/Province (ST) 鈫?Locality (L) 鈫?Organization (O) 鈫?Organizational Unit (OU) 鈫?Common Name (CN)
     *
     * @param license 璇佷功淇℃伅
     * @return {@link SubjectObjectDN} 璇佷功涓讳綋淇℃伅
     */
    private SubjectObjectDN buildSubjectDN(CaCertLicense license) {
        return SubjectObjectDN.builder()
                .countryName(license.getCountryName())
                .provinceName(license.getProvinceName())
                .localityName(license.getLocalityName())
                .organization(license.getOrganization())
                .organizationalUnit(license.getOrganizationalUnit())
                .commonName(license.getCommonName())
                .build();
    }

    /**
     * 鑾峰彇璁稿彲璇乁RL
     *
     * @param caCertLicense 璇佷功淇℃伅
     * @return {@link String} 璁稿彲璇乁RL
     */
    private String getLicenseUrl(CaCertLicense caCertLicense) {
        // 鑾峰彇鏈夋晥鐨勬枃浠禝D鍒楄〃
        List<Long> fileIdList = Stream.of(
                        Optional.ofNullable(caCertLicense.getAuthorizationCertFileid()).orElse(""),
                        Optional.ofNullable(caCertLicense.getBusinessLicenseFileid()).orElse(""))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    try {
                        return Long.valueOf(s);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (fileIdList.isEmpty()) {
            return "";
        }

        //  鑾峰彇鏂囦欢URL
        R<Map<Long, String>> fileUrlMap = fileApi.findUrlFromDefById(fileIdList);
        if (!fileUrlMap.getIsSuccess() || fileUrlMap.getData() == null) {
            log.error("Failed to retrieve file URLs, result is null or empty");
            return "";
        }
        return fileIdList.stream()
                .map(fileUrlMap.getData()::get)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.joining(StringPool.COMMA));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean revokeCertificate(Long id, String revocationReason) {
        CaCertLicense ca = getById(id);
        ArgumentAssert.notNull(ca, "CA 璇佷功涓嶅瓨鍦? id=" + id);
        if (CaCertStatusEnum.REVOKED.getValue().equals(ca.getState())) {
            log.warn("[CaCert] revoke skipped: ca already revoked id={}", id);
            return true;
        }
        ca.setState(CaCertStatusEnum.REVOKED.getValue());
        ca.setRevokeTime(LocalDateTime.now());
        ca.setRevokeReason(revocationReason);
        boolean ok = superManager.updateById(ca);
        if (!ok) {
            throw new BizException("CA 璇佷功鐘舵€佹洿鏂板け璐?);
        }
        log.info("[CaCert] revoked id={} serialNumber={} reason={}",
                id, ca.getSerialNumber(), revocationReason);
        // 鍙戝竷浜嬩欢 鈫?瑙﹀彂鍏宠仈璁惧 cache 澶辨晥
        eventPublisher.publishEvent(new CaRevokedEvent(this, id, ca.getSerialNumber(), revocationReason));
        // 瀹¤
        auditLogService.record(CaCertAuditTypeEnum.REVOKE, id, ca.getSerialNumber(),
                "reason=" + revocationReason);
        return true;
    }

    @Override
    public CaCertLicenseResultVO getByCertSerialNumber(String certSerialNumber) {
        CaCertLicense caCertLicense = superManager.getByCertSerialNumber(certSerialNumber);
        return BeanUtil.toBeanIgnoreError(caCertLicense, CaCertLicenseResultVO.class);
    }

    @Override
    public CaCertLicenseImpactResultVO getImpact(Long id) {
        CaCertLicense ca = getById(id);
        if (ca == null) {
            return null;
        }
        String serialNumber = ca.getSerialNumber();

        long bound = deviceQueryService.countByCertSerialNumber(serialNumber);
        long online = deviceQueryService.countOnlineByCertSerialNumber(serialNumber);

        List<Device> top = deviceQueryService.listTopBoundDevicesByCertSerialNumber(serialNumber, 50);

        List<Map<String, Object>> topBrief = top.stream()
                .map(d -> {
                    Map<String, Object> m = new HashMap<>(6);
                    m.put("id", d.getId());
                    m.put("deviceIdentification", d.getDeviceIdentification());
                    m.put("deviceName", d.getDeviceName());
                    m.put("productIdentification", d.getProductIdentification());
                    m.put("connectStatus", d.getConnectStatus());
                    m.put("lastHeartbeatTime", d.getLastHeartbeatTime());
                    return m;
                })
                .collect(Collectors.toList());

        return CaCertLicenseImpactResultVO.builder()
                .caId(id)
                .caSerialNumber(serialNumber)
                .caName(ca.getCertName())
                .boundDeviceCount(bound)
                .onlineDeviceCount(online)
                .topDevices(topBrief)
                .build();
    }

    @Override
    public File generateClientCertPackage(Long id, LocalDateTime notAfter) throws Exception {
        // 楠岃瘉鏍笴A璇佷功
        CaCertLicense caCertLicense = getById(id);
        ArgumentAssert.notNull(caCertLicense, "鏍笴A璇佷功涓嶅瓨鍦?");
        ArgumentAssert.isTrue(CaCertStatusEnum.ISSUED.getValue().equals(caCertLicense.getState()),
                "鏍笴A璇佷功鐘舵€佹棤鏁堬紝鏃犳硶鐢ㄤ簬绛惧彂");

        CaCertAlgorithmEnum algorithm = CaCertAlgorithmEnum.fromValue(caCertLicense.getAlgorithm())
                .orElseThrow(() -> new BizException("涓嶆敮鎸佺殑璇佷功绠楁硶绫诲瀷"));

        // 鍑嗗涓存椂鐩綍
        Path tempDir = Files.createTempDirectory(caCertLicense.getSerialNumber() + "client_cert_");

        try {
            // 鍔犺浇鏍笴A璇佷功
            X509Certificate caCert = loadCACertificate(caCertLicense);

            // 鐢熸垚瀹㈡埛绔瘑閽ュ锛堜笌鏍笴A鍚岀畻娉曪級
            KeyPair clientKeyPair = X509Util.generateKeyPair(algorithm.getDesc());

            // 瀵嗛挜瀵瑰簲鐨勫叕閽?
            PublicKey clientPublicKey = clientKeyPair.getPublic();

            // CA璇佷功鐨凞N
            SubjectObjectDN issuerDN = buildSubjectDN(caCertLicense);

            // 鏋勫缓瀹㈡埛绔疍N锛堝熀浜庢牴CA淇℃伅鐢熸垚锛?
            SubjectObjectDN clientDN = buildSubjectDN(caCertLicense);

            // 绛惧彂瀹㈡埛绔瘉涔?
            X509Certificate clientCert = X509Util.generateUserCert(3,
                    issuerDN.getX500Principal(),
                    clientDN,
                    clientPublicKey,
                    caCert,
                    DateUtils.localDateTime2Date(LocalDateTime.now()),
                    DateUtils.localDateTime2Date(notAfter));

            // 鐢熸垚鏂囦欢鍖?
            File zipFile = createCertPackage(tempDir, clientCert, clientKeyPair, caCert);
            // 瀹¤:涓嬭浇瀹㈡埛绔瘉涔﹀寘
            auditLogService.record(CaCertAuditTypeEnum.DOWNLOAD_PACK, id, caCertLicense.getSerialNumber(),
                    "notAfter=" + notAfter);
            return zipFile;
        } finally {
            if (tempDir != null) {
                FileUtils.deleteQuietly(tempDir.toFile());
            }
        }
    }

    /**
     * 鍔犺浇鏍笴A璇佷功
     */
    private X509Certificate loadCACertificate(CaCertLicense caCert) throws Exception {
        byte[] certData = Base64.getDecoder().decode(caCert.getLicenseBase64());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(certData));
    }

    private File createCertPackage(Path tempDir,
                                   X509Certificate clientCert,
                                   KeyPair clientKeyPair,
                                   X509Certificate caCert) throws IOException {
        try {
            // 1. 瀹㈡埛绔瘉涔︼紙PEM锛?
            Files.writeString(tempDir.resolve("client.crt"),
                    X509Util.X509CertificateToPem(clientCert));

            // 2. 瀹㈡埛绔閽ワ紙PKCS#8鏍囧噯PEM锛?
            Files.writeString(tempDir.resolve("client.key"), "-----BEGIN PRIVATE KEY-----\n" +
                    Base64.getMimeEncoder(64, "\n".getBytes())
                            .encodeToString(clientKeyPair.getPrivate().getEncoded()) +
                    "\n-----END PRIVATE KEY-----\n");

            // 3. CA璇佷功閾撅紙鍖呭惈鏍笴A锛?
            Files.writeString(tempDir.resolve("ca.crt"),
                    X509Util.X509CertificateToPem(caCert));

            //  README鏂囦欢
            File readmeFile = FileUtil.file(tempDir.toFile(), "README.txt");
            FileUtil.writeUtf8String(buildReadme(clientCert), readmeFile);

            // ZIP鎵撳寘锛堟敼鐢?Hutool 鐨?ZipUtil锛?
            return ZipUtil.zip(tempDir.toString());
        } catch (CertificateException e) {
            throw new IOException("璇佷功鏍煎紡杞崲澶辫触", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 浣跨敤瀛楃涓叉ā鏉挎瀯寤篟EADME鏂囦欢鍐呭
     */
    private String buildReadme(X509Certificate clientCert) throws Exception {
        // 鎻愬彇璇佷功鍏抽敭淇℃伅
        Map<String, Object> params = new HashMap<>();
        params.put("clientDn", clientCert.getSubjectX500Principal().getName());
        params.put("caDn", clientCert.getIssuerX500Principal().getName());
        params.put("notAfter", clientCert.getNotAfter().toString());
        params.put("serialNumber", clientCert.getSerialNumber().toString());
        params.put("fingerprint", X509Util.getFingerPrint(clientCert));
        params.put("algorithm", clientCert.getPublicKey().getAlgorithm());
        params.put("keyLength", X509Util.getKeyLength(clientCert.getPublicKey()));

        // 浣跨敤瀛楃涓叉ā鏉跨洿鎺ユ覆鏌?
        String template = "瀹㈡埛绔瘉涔︿俊鎭細\n" +
                "================================\n" +
                "鈥?浣跨敤鑰匘N: ${clientDn}\n" +
                "鈥?棰佸彂鑰匘N: ${caDn}\n" +
                "鈥?鏈夋晥鏈熻嚦: ${notAfter}\n" +
                "鈥?搴忓垪鍙? ${serialNumber}\n" +
                "鈥?鎸囩汗(SHA-256): ${fingerprint}\n" +
                "鈥?瀵嗛挜绠楁硶: ${algorithm}\n" +
                "鈥?瀵嗛挜闀垮害: ${keyLength} bits\n" +
                "================================\n" +
                "娉ㄦ剰浜嬮」锛歕n" +
                "1. 璇峰Ε鍠勪繚绠＄閽ユ枃浠禱n" +
                "2. 璇佷功杩囨湡鍓嶈鍙婃椂鏇存柊\n" +
                "3. 绉侀挜娉勯湶璇风珛鍗冲悐閿€璇佷功";

        return FreeMarkerUtil.generateString(template, params);
    }

}

