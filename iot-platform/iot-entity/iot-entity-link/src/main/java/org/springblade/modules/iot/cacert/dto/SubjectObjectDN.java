package org.springblade.modules.iot.cacert.dto;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.security.auth.x500.X500Principal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;

/**
 * 证书主体
 * 必须严格遵循 `C→ST→L→O→OU→CN`
 *
 * @author mqttsnet
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class SubjectObjectDN {

    private static final ASN1ObjectIdentifier EMAIL_OID = new ASN1ObjectIdentifier("1.2.840.113549.1.9.1");

    /**
     * 通用名称 (Common Name)。这是证书的名称，通常是主机名或域名。
     */
    private String commonName;
    /**
     * 组织名称 (Organization Name)。通常是您的公司名称或组织名称。
     */
    private String organization;
    /**
     * 组织单位名称 (Organizational Unit Name)。通常是您所在的部门或团队。
     */
    private String organizationalUnit;
    /**
     * 国家名称 (Country Name)。使用两位字母代码表示，如 CN 代表中国。
     */
    private String countryName;
    /**
     * 省份 (State or Province Name)。可以是全名或缩写。
     */
    private String provinceName;

    /**
     * 城市 (Locality Name)。例如，北京市。
     */
    private String localityName;

    /**
     * 电子邮件地址 (Email Address)
     */
    private String email;
    /**
     * 额外信息（邮箱，营业执照）
     */
    @Builder.Default
    private final List<GeneralName> subjectAlternativeNames = new CopyOnWriteArrayList<>();

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public void setCountryName(String countryName) {
        // 国家代码强制大写
        this.countryName = countryName != null ? countryName.trim().toUpperCase() : null;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public void setEmail(String email) {
        this.subjectAlternativeNames.add(new GeneralName(GeneralName.rfc822Name, email));
    }

    public void setLicenseUrl(String licenseUrl) {
        subjectAlternativeNames.add(new GeneralName(GeneralName.uniformResourceIdentifier, licenseUrl));
    }

    public GeneralNames getSubjectAlternativeNames() {
        return new GeneralNames(subjectAlternativeNames.toArray(new GeneralName[0]));
    }

    public X500Name getX500Name() {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        if (countryName != null) {
            // 国家（必须首位）
            builder.addRDN(BCStyle.C, countryName);
        }
        if (provinceName != null) {
            // 省/州
            builder.addRDN(BCStyle.ST, provinceName);
        }
        if (localityName != null) {
            // 地区
            builder.addRDN(BCStyle.L, localityName);
        }
        if (organization != null) {
            // 组织
            builder.addRDN(BCStyle.O, organization);
        }
        if (organizationalUnit != null) {
            // 部门
            builder.addRDN(BCStyle.OU, organizationalUnit);
        }
        if (commonName != null) {
            // 通用名（必须末位）
            builder.addRDN(BCStyle.CN, commonName);
        }
        return builder.build();
    }

    public X500Principal getX500Principal() {
        return new X500Principal(getX500Name().toString());
    }

    /**
     * 安全解析X500主体名称（兼容各种证书格式）
     * 特性：
     * 1. 全链路Optional防NPE
     * 2. 支持BCStyle和自定义OID
     * 3. 自动处理值编码
     * 4. 兼容RFC2253/OpenSSL格式
     */
    public static SubjectObjectDN parseSubjectDN(X509Certificate cert) {
        return Optional.ofNullable(cert)
                .map(X509Certificate::getSubjectX500Principal)
                .map(SubjectObjectDN::parseSubjectDN)
                .orElseGet(SubjectObjectDN::new);
    }

    public static SubjectObjectDN parseSubjectDN(X500Principal principal) {
        SubjectObjectDN.SubjectObjectDNBuilder builder = SubjectObjectDN.builder();
        if (principal == null) {
            return builder.build();
        }

        try {
            // 明确使用RFC2253格式
            X500Name x500name = new X500Name(principal.getName(X500Principal.RFC2253));

            // 处理所有RDN和所有属性
            for (RDN rdn : x500name.getRDNs()) {
                for (AttributeTypeAndValue attr : rdn.getTypesAndValues()) {
                    ASN1ObjectIdentifier type = attr.getType();
                    String value = attr.getValue().toString();

                    if (BCStyle.CN.equals(type)) {
                        builder.commonName(value);
                    } else if (BCStyle.O.equals(type)) {
                        builder.organization(value);
                    } else if (BCStyle.OU.equals(type)) {
                        builder.organizationalUnit(value);
                    } else if (BCStyle.C.equals(type)) {
                        builder.countryName(value);
                    } else if (BCStyle.ST.equals(type)) {
                        builder.provinceName(value);
                    } else if (BCStyle.L.equals(type)) {
                        builder.localityName(value);
                    } else if (isEmailOID(type)) {
                        builder.email(value);
                    }
                }
            }
        } catch (Exception e) {
            return fallbackParse(principal.getName());
        }

        return builder.build();
    }

    private static boolean isEmailOID(ASN1ObjectIdentifier oid) {
        return BCStyle.E.equals(oid) || EMAIL_OID.equals(oid);
    }

    /**
     * 降级解析方案（当标准解析失败时使用）
     */
    private static SubjectObjectDN fallbackParse(String dn) {
        SubjectObjectDN.SubjectObjectDNBuilder builder = SubjectObjectDN.builder();
        Arrays.stream(dn.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"))
                .map(pair -> pair.split("=", 2))
                .filter(parts -> parts.length == 2)
                .forEach(parts -> {
                    String key = parts[0].trim().toUpperCase();
                    String value = parts[1].trim().replaceAll("^\"|\"$", "");

                    switch (key) {
                        case "CN":
                            builder.commonName(value);
                            break;
                        case "O":
                            builder.organization(value);
                            break;
                        case "OU":
                            builder.organizationalUnit(value);
                            break;
                        case "C":
                            builder.countryName(value);
                            break;
                        case "ST":
                            builder.provinceName(value);
                            break;
                        case "L":
                            builder.localityName(value);
                            break;
                        case "E":
                        case "EMAILADDRESS":
                            builder.email(value);
                            break;
                    }
                });

        return builder.build();
    }

}