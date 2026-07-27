package org.springblade.modules.iot.utils.x509;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import javax.security.auth.x500.X500Principal;

import cn.hutool.core.util.StrUtil;
import org.springblade.modules.iot.cacert.dto.SubjectObjectDN;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v2CRLBuilder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.springframework.stereotype.Component;

/**
 * X509证书和CRL生成工具类
 *
 * @author mqttsnet
 */
@Slf4j
@Component
public class X509Util {
    private static final int KEY_SIZE = 2048;
    private static final String PROVIDER = "BC";
    //算法
    private static final String KEY_ALG = "RSA";
    //签名算法
    private static final String SIG_ALG = "SHA256withRSA";

    private static final String CRL_URL = "https://www.example.com/crl";
    private static final String ROOT_DN = "CN=mqttsnet CA,O=HNU,OU=CS,C=CN,ST=海南省";
    private static final DistributionPoint[] DISTRIBUTION_POINTS = new DistributionPoint[1];

    private static final String PRIVATE_KEY_PATH = "/etc/mqttsnet/thinglinks/ssl/root.privateKey";

    static {
        // 添加BouncyCastle支持
        Security.addProvider(new BouncyCastleProvider());
        // 构造CRL distribution points扩展值
        GeneralName generalName = new GeneralName(GeneralName.uniformResourceIdentifier, CRL_URL);
        DistributionPointName distributionPointName = new DistributionPointName(new GeneralNames(generalName));
        DISTRIBUTION_POINTS[0] = new DistributionPoint(distributionPointName, null, null);
    }

    /**
     * 打印所有provider的名称和信息
     */
    public static void printProviders() {
        for (Provider p : Security.getProviders()) {
            System.out.println(p.getName());
            System.out.println(p.getInfo());
        }
    }

    /**
     * 生成RSA密钥对
     *
     * @param keyAlg 密钥算法
     * @return keyPair 密钥对
     */
    public static KeyPair generateKeyPair(String keyAlg) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyAlg);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 根据n和d生成RSA公钥
     *
     * @param n 模数n
     * @param e 公钥e，一般为65537
     * @return publicKey 公钥
     */
    public static PublicKey customRSAPublicKey(String n, String e) throws Exception {
        // 构造RSA公钥参数RSAPublicKeySpec
        RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(n), new BigInteger(e));
        // 使用RSAPublicKeySpec构造RSA公钥
        return KeyFactory.getInstance(KEY_ALG).generatePublic(spec);
    }

    /**
     * 根据参数生成ECC公钥
     *
     * @param x ECPoint:X
     * @param y ECPoint:Y
     * @return publicKey 公钥
     */
    public static PublicKey customECPublicKey(String x, String y) throws Exception {
        // 根据params构造ECPublicKeyParameters
        ECParameterSpec ecParameterSpec = ECNamedCurveTable.getParameterSpec("prime256v1");
        // 根据x和y构造ECPoint
        ECPoint ecPoint = ecParameterSpec.getCurve().createPoint(new BigInteger(x, 16), new BigInteger(y, 16));
        // 根据ECPublicKeyParameters和ECPoint构造ECPublicKeySpec
        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(ecPoint, ecParameterSpec);
        // 使用ECPublicKeySpec构造ECC公钥
        return KeyFactory.getInstance("EC", PROVIDER).generatePublic(ecPublicKeySpec);
    }

    /**
     * 标准化DN构建方法（强制RFC顺序和必填校验）
     *
     * @param commonName         CN (必须，如"www.example.com")
     * @param organization       O (必须，如"Company Inc.")
     * @param countryName        C (必须，大写，2字母代码如"CN")
     * @param provinceName       ST (可选，如"Beijing")
     * @param localityName       L (可选，如"Haidian")
     * @param organizationalUnit OU (可选，如"IT Dept")
     * @param email              邮箱（自动转为SAN扩展）
     * @param licenseUrl         URL（自动转为SAN扩展）
     * @return 符合X.509标准的SubjectObjectDN
     * @throws IllegalArgumentException 必填字段缺失或格式错误时抛出
     */
    public static SubjectObjectDN generateDN(String commonName, String organization, String organizationalUnit, String countryName, String provinceName, String localityName, String email, String licenseUrl) {
        SubjectObjectDN subjectObjectDN = new SubjectObjectDN();
        subjectObjectDN.setCommonName(commonName.toUpperCase());
        subjectObjectDN.setOrganization(organization);
        subjectObjectDN.setOrganizationalUnit(organizationalUnit);
        subjectObjectDN.setCountryName(countryName);
        subjectObjectDN.setProvinceName(provinceName);
        subjectObjectDN.setLocalityName(localityName);
        subjectObjectDN.setEmail(email);
        subjectObjectDN.setLicenseUrl(licenseUrl);
        return subjectObjectDN;
    }

    /**
     * 生成根CA数字证书（支持完全自定义参数）
     *
     * @param api       证书生成API选择：
     *                  <ul>
     *                  <li>1 - 使用X509V3CertificateGenerator（兼容旧版）</li>
     *                  <li>2 - 使用X509v3CertificateBuilder（需注意DN格式问题）</li>
     *                  <li>3 - 使用JcaX509v3CertificateBuilder（推荐）</li>
     *                  </ul>
     * @param subjectDN 证书主题信息（包含CN/O/OU等字段）
     * @param publicKey 可选公钥参数：
     *                  <ul>
     *                  <li>若为null，则自动生成RSA密钥对</li>
     *                  <li>若传入公钥，则使用现有密钥（需自行管理私钥）</li>
     *                  </ul>
     * @param notBefore 证书生效时间（不可为null）
     * @param notAfter  证书过期时间（需晚于notBefore）
     * @return 生成的X509证书对象
     * @throws IllegalArgumentException 参数不合法时抛出
     * @throws Exception                证书生成过程中的其他异常
     * @apiNote 注意不同API的DN处理差异：
     * <ul>
     * <li>方法1和3使用X500Principal，DN字段顺序标准化</li>
     * <li>方法2使用X500Name，可能与标准顺序不一致</li>
     * </ul>
     */
    public static X509Certificate generateRootCert(int api, SubjectObjectDN subjectDN, PublicKey publicKey, Date notBefore, Date notAfter) throws Exception {

        Objects.requireNonNull(subjectDN, "主题DN不能为null");
        Objects.requireNonNull(notBefore, "生效时间不能为null");
        Objects.requireNonNull(notAfter, "过期时间不能为null");
        if (notAfter.before(notBefore)) {
            throw new IllegalArgumentException("过期时间必须晚于生效时间");
        }

        PrivateKey privateKey = readPrivateKey(KEY_ALG);

        // 根据API选择生成器
        return switch (api) {
            case 1 -> genRootWithX509V3CertificateGenerator(subjectDN, publicKey, privateKey, notBefore, notAfter);
            case 2 -> genRootWithX509v3CertificateBuilder(subjectDN, publicKey, privateKey, notBefore, notAfter);
            case 3 -> genRootWithJcaX509v3CertificateBuilder(subjectDN, publicKey, privateKey, notBefore, notAfter);
            default -> throw new IllegalArgumentException("不支持的API类型: " + api);
        };
    }

    /**
     * 使用X509V3CertificateGenerator生成根CA证书（传统方式）
     *
     * @param subjectDN  证书主题信息
     * @param publicKey  公钥
     * @param privateKey 签名私钥
     * @param notBefore  生效时间
     * @param notAfter   过期时间
     * @return 生成的X509证书
     * @implNote 此方法生成的证书：
     * <ul>
     * <li>序列号固定为1（根证书惯例）</li>
     * <li>颁发者DN=使用者DN（自签名特性）</li>
     * <li>必须包含CA基本约束扩展</li>
     * </ul>
     */
    public static X509Certificate genRootWithX509V3CertificateGenerator(SubjectObjectDN subjectDN, PublicKey publicKey, PrivateKey privateKey, Date notBefore, Date notAfter) throws Exception {

        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();

        // 设置证书基本信息
        X500Principal principal = subjectDN.getX500Principal();
        certGen.setIssuerDN(principal);          // 颁发者（自签名）
        certGen.setSubjectDN(principal);         // 使用者
        certGen.setSerialNumber(generateSerialNumber()); // 根证书序列号
        certGen.setNotBefore(notBefore);
        certGen.setNotAfter(notAfter);
        certGen.setPublicKey(publicKey);
        certGen.setSignatureAlgorithm(SIG_ALG);

        // 添加关键扩展
        // 1. 密钥用法：证书签名和CRL签名
        certGen.addExtension(Extension.keyUsage, true, new KeyUsage(KeyUsage.keyCertSign | KeyUsage.cRLSign));

        // 2. 基本约束：标记为CA证书
        certGen.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));

        // 3. CRL分发点
        certGen.addExtension(Extension.cRLDistributionPoints, false, new CRLDistPoint(DISTRIBUTION_POINTS));

        return certGen.generate(privateKey, PROVIDER);
    }

    /**
     * 使用JcaX509v3CertificateBuilder生成根CA证书（推荐方式）
     *
     * @param subjectDN  证书主题信息
     * @param publicKey  公钥
     * @param privateKey 签名私钥
     * @param notBefore  生效时间
     * @param notAfter   过期时间
     * @return 生成的X509证书
     * @implNote 此方法：
     * <ul>
     * <li>使用BouncyCastle的JCA风格构建器</li>
     * <li>生成的证书兼容性更好</li>
     * <li>DN字段顺序符合X.509标准</li>
     * </ul>
     */
    public static X509Certificate genRootWithJcaX509v3CertificateBuilder(SubjectObjectDN subjectDN, PublicKey publicKey, PrivateKey privateKey, Date notBefore, Date notAfter) throws Exception {

        X500Principal principal = subjectDN.getX500Principal();

        // 构建证书模板
        X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
                // 颁发者
                principal,
                // 序列号
                generateSerialNumber(),
                // 生效时间
                notBefore,
                // 过期时间
                notAfter,
                // 使用者（自签名）
                principal,
                // 公钥
                publicKey)

                // 添加扩展项（链式调用）
                .addExtension(Extension.keyUsage, true, new KeyUsage(KeyUsage.keyCertSign | KeyUsage.cRLSign)).addExtension(Extension.basicConstraints, true, new BasicConstraints(true)).addExtension(Extension.cRLDistributionPoints, false, new CRLDistPoint(DISTRIBUTION_POINTS));

        // 创建签名器并生成证书
        ContentSigner sigGen = new JcaContentSignerBuilder(SIG_ALG).setProvider(PROVIDER).build(privateKey);

        return new JcaX509CertificateConverter().setProvider(PROVIDER).getCertificate(certGen.build(sigGen));
    }

    /**
     * 使用X509v3CertificateBuilder生成根CA证书（需注意DN格式）
     *
     * @param subjectDN  证书主题信息
     * @param publicKey  公钥
     * @param privateKey 签名私钥
     * @param notBefore  生效时间
     * @param notAfter   过期时间
     * @return 生成的X509证书
     * @apiNote 重要限制：
     * <ul>
     * <li>此方法使用X500Name构建DN，可能与标准X500Principal的字段顺序不一致</li>
     * <li>生成的证书只能验证通过同样方式生成的子证书</li>
     * <li>建议优先使用API=3的JCA方式</li>
     * </ul>
     * @implNote 典型使用场景：
     * <ul>
     * <li>需要与特定旧系统兼容时</li>
     * <li>证书链全部使用相同生成方式时</li>
     * </ul>
     */
    public static X509Certificate genRootWithX509v3CertificateBuilder(SubjectObjectDN subjectDN, PublicKey publicKey, PrivateKey privateKey, Date notBefore, Date notAfter) throws Exception {

        // 将公钥转换为BC内部格式
        AsymmetricKeyParameter publicKeyParam = PublicKeyFactory.createKey(publicKey.getEncoded());
        SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKeyParam);

        // 构建证书模板（使用X500Name）
        X500Name issuerName = subjectDN.getX500Name();
        X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(
                // 颁发者（自签名）
                issuerName,
                // 序列号
                generateSerialNumber(),
                // 生效时间
                notBefore,
                // 过期时间
                notAfter,
                // 使用者
                issuerName,
                // 公钥信息
                publicKeyInfo)

                // 添加关键扩展
                .addExtension(Extension.keyUsage, true, new KeyUsage(KeyUsage.keyCertSign | KeyUsage.cRLSign)).addExtension(Extension.basicConstraints, true, new BasicConstraints(true)).addExtension(Extension.cRLDistributionPoints, false, new CRLDistPoint(DISTRIBUTION_POINTS));

        // 创建签名器
        ContentSigner sigGen = new JcaContentSignerBuilder(SIG_ALG).setProvider(PROVIDER).build(privateKey);

        // 转换并返回证书
        return new JcaX509CertificateConverter().setProvider(PROVIDER).getCertificate(certGen.build(sigGen));
    }

    /**
     * 生成用户数字证书（增强版）
     *
     * @param api       证书生成API（1/2/3）
     * @param issuerDN  颁发者DN（必须与CA证书的SubjectDN一致）
     * @param subjectDN 使用者DN
     * @param publicKey 用户公钥
     * @param caCert    CA证书
     * @param notBefore 生效时间
     * @param notAfter  过期时间
     * @return 符合X.509标准的用户证书
     * @throws IllegalArgumentException 参数无效时抛出
     * @throws Exception                证书生成异常
     */
    public static X509Certificate generateUserCert(
            int api,
            X500Principal issuerDN,
            SubjectObjectDN subjectDN,
            PublicKey publicKey,
            X509Certificate caCert,
            Date notBefore,
            Date notAfter) throws Exception {

        // 参数校验
        Objects.requireNonNull(issuerDN, "颁发者DN不能为null");
        Objects.requireNonNull(subjectDN, "使用者DN不能为null");
        if (notAfter.before(notBefore)) {
            throw new IllegalArgumentException("过期时间必须晚于生效时间");
        }

        PrivateKey caPrivateKey = readPrivateKey(KEY_ALG);

        return switch (api) {
            case 1 -> genUserWithX509V3CertificateGenerator(
                    issuerDN, subjectDN, publicKey, caPrivateKey, caCert, notBefore, notAfter);
            case 2 -> genUserWithX509v3CertificateBuilder(
                    issuerDN, subjectDN, publicKey, caPrivateKey, caCert, notBefore, notAfter);
            case 3 -> genUserWithJcaX509v3CertificateBuilder(
                    issuerDN, subjectDN, publicKey, caPrivateKey, caCert, notBefore, notAfter);
            default -> throw new IllegalArgumentException("无效API类型: " + api);
        };
    }

    /**
     * 使用传统API生成用户证书
     *
     * @implNote 特点：
     * - 兼容性好
     * - 扩展项设置直观
     */
    public static X509Certificate genUserWithX509V3CertificateGenerator(
            X500Principal issuerDN,
            SubjectObjectDN subjectDN,
            PublicKey publicKey,
            PrivateKey caPrivateKey,
            X509Certificate caCert,
            Date notBefore,
            Date notAfter) throws Exception {

        JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();

        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();

        // 基础信息
        certGen.setIssuerDN(issuerDN);
        certGen.setSerialNumber(generateSerialNumber());
        certGen.setNotBefore(notBefore);
        certGen.setNotAfter(notAfter);
        certGen.setSubjectDN(subjectDN.getX500Principal());
        certGen.setPublicKey(publicKey);
        certGen.setSignatureAlgorithm(SIG_ALG);

        // 扩展项（AuthorityKeyIdentifier改用CA公钥生成）
        certGen.addExtension(Extension.authorityKeyIdentifier, false,
                extUtils.createAuthorityKeyIdentifier(caCert.getPublicKey()));

        // 标准扩展项
        certGen.addExtension(Extension.keyUsage, true,
                new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyEncipherment));

        certGen.addExtension(Extension.extendedKeyUsage, true,
                new ExtendedKeyUsage(KeyPurposeId.id_kp_clientAuth));

        certGen.addExtension(Extension.basicConstraints, false,
                new BasicConstraints(false));

        // 增强扩展项
        certGen.addExtension(Extension.subjectKeyIdentifier, false,
                createSubjectKeyIdentifier(publicKey));

        certGen.addExtension(Extension.cRLDistributionPoints, false,
                new CRLDistPoint(DISTRIBUTION_POINTS));

        certGen.addExtension(Extension.subjectAlternativeName, false,
                subjectDN.getSubjectAlternativeNames());

        return certGen.generate(caPrivateKey, PROVIDER);
    }

    /**
     * 使用BC底层API生成用户证书
     *
     * @apiNote 重要限制：
     * - 必须与相同方式生成的CA证书配合使用
     * - DN字段顺序敏感
     */
    public static X509Certificate genUserWithX509v3CertificateBuilder(
            X500Principal issuerDN,
            SubjectObjectDN subjectDN,
            PublicKey publicKey,
            PrivateKey caPrivateKey,
            X509Certificate caCert,
            Date notBefore,
            Date notAfter) throws Exception {

        JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();

        // 转换公钥格式
        SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfoFactory
                .createSubjectPublicKeyInfo(PublicKeyFactory.createKey(publicKey.getEncoded()));

        // 构造证书（使用X500Name保持兼容）
        X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(
                new X500Name(issuerDN.getName()),
                generateSerialNumber(),
                notBefore,
                notAfter,
                subjectDN.getX500Name(),
                publicKeyInfo)

                // 扩展项（AuthorityKeyIdentifier改用CA公钥生成）
                .addExtension(Extension.authorityKeyIdentifier, false,
                        extUtils.createAuthorityKeyIdentifier(caCert.getPublicKey()))

                // 标准扩展项
                .addExtension(Extension.keyUsage, true,
                        new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyEncipherment))
                .addExtension(Extension.extendedKeyUsage, true,
                        new ExtendedKeyUsage(KeyPurposeId.id_kp_clientAuth))
                .addExtension(Extension.basicConstraints, false,
                        new BasicConstraints(false))
                .addExtension(Extension.subjectKeyIdentifier, false,
                        createSubjectKeyIdentifier(publicKey))
                .addExtension(Extension.cRLDistributionPoints, false,
                        new CRLDistPoint(DISTRIBUTION_POINTS))
                .addExtension(Extension.subjectAlternativeName, false,
                        subjectDN.getSubjectAlternativeNames());

        ContentSigner signer = new JcaContentSignerBuilder(SIG_ALG)
                .setProvider(PROVIDER)
                .build(caPrivateKey);

        return new JcaX509CertificateConverter()
                .setProvider(PROVIDER)
                .getCertificate(certGen.build(signer));
    }

    /**
     * 使用JCA风格生成用户证书（推荐）
     *
     * @implNote 优势：
     * - DN处理标准化
     * - 兼容性最佳
     */
    public static X509Certificate genUserWithJcaX509v3CertificateBuilder(
            X500Principal issuerDN,
            SubjectObjectDN subjectDN,
            PublicKey publicKey,
            PrivateKey caPrivateKey,
            X509Certificate caCert,
            Date notBefore,
            Date notAfter) throws Exception {

        JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();

        X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
                issuerDN,
                generateSerialNumber(),
                notBefore,
                notAfter,
                subjectDN.getX500Principal(),
                publicKey)

                // 扩展项（AuthorityKeyIdentifier改用CA公钥生成）
                .addExtension(Extension.authorityKeyIdentifier, false,
                        extUtils.createAuthorityKeyIdentifier(caCert.getPublicKey()))

                // 其他扩展保持不变
                .addExtension(Extension.keyUsage, true,
                        new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyEncipherment))
                .addExtension(Extension.extendedKeyUsage, true,
                        new ExtendedKeyUsage(KeyPurposeId.id_kp_clientAuth))
                .addExtension(Extension.basicConstraints, false,
                        new BasicConstraints(false))
                .addExtension(Extension.subjectKeyIdentifier, false,
                        extUtils.createSubjectKeyIdentifier(publicKey))
                .addExtension(Extension.cRLDistributionPoints, false,
                        new CRLDistPoint(DISTRIBUTION_POINTS))
                .addExtension(Extension.subjectAlternativeName, false,
                        subjectDN.getSubjectAlternativeNames());

        ContentSigner signer = new JcaContentSignerBuilder(SIG_ALG)
                .setProvider(PROVIDER)
                .build(caPrivateKey);

        return new JcaX509CertificateConverter()
                .setProvider(PROVIDER)
                .getCertificate(certGen.build(signer));
    }

    /**
     * 生成安全的证书序列号
     *
     * @return 安全的证书序列号
     */
    private static BigInteger generateSerialNumber() {
        return new BigInteger(128, new SecureRandom());
    }

    /**
     * 创建SubjectKeyIdentifier
     *
     * @param publicKey 公钥
     * @return SubjectKeyIdentifier
     * @throws Exception 异常
     */
    private static byte[] createSubjectKeyIdentifier(PublicKey publicKey) throws Exception {
        return new JcaX509ExtensionUtils()
                .createSubjectKeyIdentifier(publicKey)
                .getEncoded();
    }

    /**
     * 将certificate转换为PEM格式
     *
     * @param cert 证书
     * @return PEM格式证书
     */
    public static String X509CertificateToPem(X509Certificate cert) throws Exception {
        StringWriter sw = new StringWriter();
        try (JcaPEMWriter pemWriter = new JcaPEMWriter(sw)) {
            pemWriter.writeObject(cert);
            pemWriter.flush();
            // 强制标准化PEM格式
            String pem = sw.toString();
            if (!pem.startsWith("-----BEGIN CERTIFICATE-----")) {
                pem = "-----BEGIN CERTIFICATE-----\n" +
                        Base64.getMimeEncoder(64, "\n".getBytes())
                                .encodeToString(cert.getEncoded()) +
                        "\n-----END CERTIFICATE-----\n";
            }
            return pem;
        }
    }

    /**
     * 生成CRL证书吊销列表
     *
     * @param caPrivateKey CA私钥
     * @param serials      吊销的证书序列号
     * @return X509CRL证书吊销列表
     */
    public static X509CRL generateCRLCert(PrivateKey caPrivateKey, BigInteger[] serials) throws Exception {
        // 证书吊销列表构造器
        JcaX509v2CRLBuilder crlGen = new JcaX509v2CRLBuilder(new X500Principal(ROOT_DN), new Date());
        // 设置下次更新时间
        crlGen.setNextUpdate(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24));
        // 设置吊销证书序列号
        for (BigInteger serial : serials) {
            crlGen.addCRLEntry(serial, new Date(), CRLReason.affiliationChanged);
        }
        // 签名构造器
        ContentSigner sigGen = new JcaContentSignerBuilder(SIG_ALG).setProvider(PROVIDER).build(caPrivateKey);
        // 生成证书吊销列表
        return new JcaX509CRLConverter().setProvider(PROVIDER).getCRL(crlGen.build(sigGen));
    }

    /**
     * 计算X509数字证书的指纹
     * SHA-256算法
     *
     * @param cert 证书
     * @return 证书指纹
     */
    public static String getFingerPrint(X509Certificate cert) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(cert.getEncoded());
        return new String(Hex.encode(digest));
    }

    /**
     * 验证根CA数字证书的完整性
     *
     * @param cert 证书
     * @return 验证结果
     */
    public static boolean verifyCert(X509Certificate cert) throws Exception {
        PublicKey publicKey = cert.getPublicKey();
        cert.checkValidity();
        cert.verify(publicKey);
        return true;
    }

    /**
     * 获取公钥位数
     *
     * @param publicKey 公钥
     * @return 公钥位数
     */
    public static int getKeyLength(PublicKey publicKey) {
        try {
            // 处理 RSA 密钥
            if (publicKey instanceof RSAKey) {
                return ((RSAKey) publicKey).getModulus().bitLength();
            }

            //  处理 EC 密钥
            if (publicKey instanceof ECPublicKey) {
                ECPublicKey ecKey = (ECPublicKey) publicKey;
                // 直接使用 Bouncy Castle 的 ECKey 接口方法
                ECParameterSpec spec = ecKey.getParameters();
                if (spec != null) {
                    // 返回椭圆曲线阶数（order）的位数
                    return spec.getN().bitLength();
                }
                // 通过曲线字段大小估算,返回曲线字段的位数（如 256 对于 secp256r1）
                return ecKey.getQ().getCurve().getFieldSize();
            }
        } catch (Exception e) {
            log.error("Failed to get key length: {}", e.getMessage());
        }
        return 0;
    }

    /**
     * 验证根CA数字证书的完整性
     *
     * @param certPath  证书路径
     * @param publicKey 公钥
     * @return 验证结果
     */
    public static boolean verifyCert(String certPath, PublicKey publicKey) throws Exception {
        X509Certificate cert = readX509Cert(certPath);
        cert.checkValidity();
        cert.verify(publicKey);
        return true;
    }

    /**
     * 从文件中读取X509证书
     *
     * @param certFile 证书文件路径
     * @return X509Certificate证书
     */
    public static X509Certificate readX509Cert(String certFile) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509", PROVIDER);
        return (X509Certificate) cf.generateCertificate(new FileInputStream(certFile));
    }

    /**
     * 从文件中读取私钥
     *
     * @param algorithm 签名算法
     * @return 私钥
     */
    public static PrivateKey readPrivateKey(String algorithm) throws Exception {
        KeyFactory kf = KeyFactory.getInstance(algorithm, PROVIDER);
        return kf.generatePrivate(new PKCS8EncodedKeySpec(readFile(PRIVATE_KEY_PATH)));
    }

    /**
     * 生成并保存根CA私钥到指定目录
     *
     * @param directory   目标目录路径
     * @param keyFileName 私钥文件名（如"root.privateKey"）
     * @return {@link KeyPair} 私钥文件
     */
    public static KeyPair generateAndSaveRootPrivateKey(String directory, String keyFileName) throws Exception {
        // 参数校验
        if (StrUtil.isBlank(directory) || StrUtil.isBlank(keyFileName)) {
            throw new IllegalArgumentException("目录和文件名不能为空");
        }

        // 创建目录（如果不存在）
        Path dirPath = Paths.get(directory);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        // 生成密钥对
        KeyPair keyPair = X509Util.generateKeyPair(KEY_ALG);
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        // 保存私钥文件
        Path keyPath = dirPath.resolve(keyFileName);
        Files.write(keyPath, privateKeyBytes);
        return keyPair;
    }

    /**
     * 读取文件
     *
     * @param path 文件路径
     * @return 文件内容
     * @throws IOException 如果文件不存在或读取失败
     */
    public static byte[] readFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    /**
     * 保存密钥或证书到文件（自动创建目录）
     *
     * @param filePath    文件路径
     * @param encodedFile 编码后的内容
     * @throws IOException 如果写入失败或目录创建失败
     */
    public static void saveEncodedFile(String filePath, byte[] encodedFile) throws IOException {
        Files.write(Paths.get(filePath), encodedFile);
    }

    /**
     * 判断根私钥是否存在
     */
    public static boolean isRootPrivateKeyExist() {
        return Files.exists(Paths.get(PRIVATE_KEY_PATH));
    }

    /**
     * 将X509证书转换为标准Base64字符串（无PEM头尾标记和换行符）
     *
     * @param certificate X509证书对象
     * @return 纯Base64编码的证书内容（RFC 4648标准，无填充换行）
     * @throws IllegalArgumentException 如果证书为null或编码失败
     */
    public static String toBase64(X509Certificate certificate) {
        // 参数校验
        Objects.requireNonNull(certificate, "Certificate cannot be null");

        try {
            // 获取DER编码的证书数据
            byte[] derEncoded = certificate.getEncoded();

            // 使用Base64编码器（配置为无换行符）
            Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
            return encoder.encodeToString(derEncoded);

        } catch (CertificateEncodingException e) {
            throw new IllegalArgumentException("Certificate encoding error", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert certificate to Base64", e);
        }
    }

    /**
     * 将私钥转换为纯Base64字符串
     *
     * @param key 私钥对象
     * @return 纯Base64编码的密钥内容
     * @throws IllegalArgumentException 如果密钥为null
     */
    public static String privateKeyToBase64(PrivateKey key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * 将X509证书转换为PEM格式字符串（带标准头尾标记）
     *
     * @param certificate X509证书对象
     * @return PEM格式的证书内容
     * @throws IllegalArgumentException 如果证书为null
     */
    public static String certificateToPem(X509Certificate certificate) {
        if (certificate == null) {
            throw new IllegalArgumentException("X509Certificate cannot be null");
        }

        StringWriter sw = new StringWriter();
        try (JcaPEMWriter pemWriter = new JcaPEMWriter(sw)) {
            pemWriter.writeObject(certificate);
            pemWriter.flush();
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("证书转换PEM失败", e);
        }
    }

    /**
     * 将私钥转换为PEM格式字符串（带标准头尾标记）
     *
     * @param privateKey 私钥对象
     * @return PEM格式的私钥内容
     * @throws IllegalArgumentException 如果私钥为null
     */
    public static String privateKeyToPem(PrivateKey privateKey) {
        if (privateKey == null) {
            throw new IllegalArgumentException("PrivateKey cannot be null");
        }

        StringWriter sw = new StringWriter();
        try (JcaPEMWriter pemWriter = new JcaPEMWriter(sw)) {
            pemWriter.writeObject(privateKey);
            pemWriter.flush();
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("私钥转换PEM失败", e);
        }
    }

    /**
     * 将PEM格式内容转换为纯Base64字符串（自动清理头尾标记和换行符）
     *
     * @param pemContent PEM格式内容（可含头尾标记）
     * @return 纯Base64编码内容
     * @throws IllegalArgumentException 如果输入为null或空
     */
    public static String pemToBase64(String pemContent) {
        if (pemContent == null || pemContent.trim().isEmpty()) {
            throw new IllegalArgumentException("PEM content cannot be null or empty");
        }
        return pemContent.trim().replaceAll("-----BEGIN [A-Z ]+-----", "").replaceAll("-----END [A-Z ]+-----", "").replaceAll("\\s", "");
    }

    /**
     * 将字节数组转换为Base64字符串
     *
     * @param bytes 原始字节数组
     * @return Base64编码字符串
     * @throws IllegalArgumentException 如果输入为null
     */
    public static String toBase64(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("Byte array cannot be null");
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 将PEM格式的根CA证书解析为X509Certificate对象
     *
     * @param pemContent PEM格式的证书内容（必须包含"-----BEGIN CERTIFICATE-----"）
     * @return X509Certificate对象
     * @throws IllegalArgumentException 如果解析失败或内容无效
     */
    public static X509Certificate parseRootCertificate(String pemContent) {
        if (StrUtil.isBlank(pemContent) || !pemContent.contains("-----BEGIN CERTIFICATE-----")) {
            throw new IllegalArgumentException("无效的PEM证书格式");
        }

        try (PEMParser parser = new PEMParser(new StringReader(pemContent))) {
            Object obj = parser.readObject();
            if (obj instanceof X509CertificateHolder) {
                return new JcaX509CertificateConverter()
                        .getCertificate((X509CertificateHolder) obj);
            }
            throw new IllegalArgumentException("PEM内容未包含有效的X509证书");
        } catch (Exception e) {
            throw new IllegalArgumentException("证书解析失败: " + e.getMessage(), e);
        }
    }
}