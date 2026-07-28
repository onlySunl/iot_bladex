package org.springblade.modules.iot.cacert.entity.license;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 实体类
 * CA许可证证书表
 * </p>
 *
 * @author mqttsnet
 * @since 2025-06-27 15:48:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "ca_cert_license", comment = "CaCertLicense table")
public class CaCertLicense extends Entity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 证书名称
     */
    @AutoColumn(value = "cert_name", comment = "证书名称")
    private String certName;

    /**
     * 颁发者通用名称
     */
    @AutoColumn(value = "issuer_common_name", comment = "颁发者通用名称")
    private String issuerCommonName;

    /**
     * 证书序列号
     * 十六进制大写
     */
    @AutoColumn(value = "serial_number", comment = "证书序列号 十六进制大写")
    private String serialNumber;
    /**
     * 通用名称
     */
    @AutoColumn(value = "common_name", comment = "通用名称")
    private String commonName;
    /**
     * 组织名称
     */
    @AutoColumn(value = "organization", comment = "组织名称")
    private String organization;
    /**
     * 组织单位名称
     */
    @AutoColumn(value = "organizational_unit", comment = "组织单位名称")
    private String organizationalUnit;
    /**
     * 国家
     */
    @AutoColumn(value = "country_name", comment = "国家")
    private String countryName;
    /**
     * 省份/州
     */
    @AutoColumn(value = "province_name", comment = "省份/州")
    private String provinceName;
    /**
     * 城市
     */
    @AutoColumn(value = "locality_name", comment = "城市")
    private String localityName;
    /**
     * 邮箱
     */
    @AutoColumn(value = "email", comment = "邮箱")
    private String email;
    /**
     * License文件内容(Base64编码)
     */
    @AutoColumn(value = "license_base64", comment = "License文件内容(Base64编码)")
    private String licenseBase64;
    /**
     * 营业执照文件ID
     */
    @AutoColumn(value = "business_license_fileid", comment = "营业执照文件ID")
    private String businessLicenseFileid;
    /**
     * 授权证书文件ID
     */
    @AutoColumn(value = "authorization_cert_fileid", comment = "授权证书文件ID")
    private String authorizationCertFileid;

    /**
     * CA证书(PEM格式)
     */
    @AutoColumn(value = "ca_cert_pem", comment = "CA证书(PEM格式)")
    private String caCertPem;

    /**
     * 证书文件ID
     */
    @AutoColumn(value = "cert_fileid", comment = "证书文件ID")
    private String certFileid;
    /**
     * 签名算法
     */
    @AutoColumn(value = "sign_algorithm", comment = "签名算法")
    private Integer signAlgorithm;
    /**
     * 算法(0-RSA、1-EC)
     */
    @AutoColumn(value = "algorithm", comment = "算法(0-RSA、1-EC)")
    private Integer algorithm;
    /**
     * RSA公钥n或ECC Point x
     */
    @AutoColumn(value = "param1", comment = "RSA公钥n或ECC Point x")
    private String param1;
    /**
     * RSA公钥e或ECC Point y
     */
    @AutoColumn(value = "param2", comment = "RSA公钥e或ECC Point y")
    private String param2;
    /**
     * 扩展信息
     */
    @AutoColumn(value = "extend_params", comment = "扩展信息")
    private String extendParams;
    /**
     * 证书颁发时间
     */
    @AutoColumn(value = "not_before", comment = "证书颁发时间")
    private LocalDateTime notBefore;
    /**
     * 证书过期时间
     */
    @AutoColumn(value = "not_after", comment = "证书过期时间")
    private LocalDateTime notAfter;
    /**
     * 证书撤销时间
     */
    @AutoColumn(value = "revoke_time", comment = "证书撤销时间")
    private LocalDateTime revokeTime;
    /**
     * 撤销原因
     */
    @AutoColumn(value = "revoke_reason", comment = "撤销原因")
    private String revokeReason;
    /**
     * 证书状态(0-待完善、1-已颁发、2-已撤销)
     */
    @AutoColumn(value = "state", comment = "证书状态(0-待完善、1-已颁发、2-已撤销)")
    private Integer state;

    /**
     * 证书指纹(SHA-256)
     */
    @AutoColumn(value = "thumbprint", comment = "证书指纹(SHA-256)")
    private String thumbprint;
    /**
     * 备注
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;
}
