package org.springblade.modules.iot.cacert.vo.query.license;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 表单查询条件VO
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
@EqualsAndHashCode
@Builder
@Schema(description = "CA许可证证书")
public class CaCertLicensePageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    /**
    * 证书名称
    */
    @Schema(description = "证书名称")
    private String certName;
    /**
    * 证书序列号
    */
    @Schema(description = "证书序列号")
    private String serialNumber;
    /**
    * 通用名称
    */
    @Schema(description = "通用名称")
    private String commonName;
    /**
    * 组织名称
    */
    @Schema(description = "组织名称")
    private String organization;
    /**
    * 组织单位名称
    */
    @Schema(description = "组织单位名称")
    private String organizationalUnit;
    /**
    * 国家
    */
    @Schema(description = "国家")
    private String countryName;
    /**
    * 省份/州
    */
    @Schema(description = "省份/州")
    private String provinceName;
    /**
    * 城市
    */
    @Schema(description = "城市")
    private String localityName;
    /**
    * 邮箱
    */
    @Schema(description = "邮箱")
    private String email;
    /**
    * License文件内容(Base64编码)
    */
    @Schema(description = "License文件内容(Base64编码)")
    private String licenseBase64;
    /**
    * 营业执照文件ID
    */
    @Schema(description = "营业执照文件ID")
    private String businessLicenseFileid;
    /**
    * 授权证书文件ID
    */
    @Schema(description = "授权证书文件ID")
    private String authorizationCertFileid;
    /**
    * 算法(0-RSA、1-EC)
    */
    @Schema(description = "算法(0-RSA、1-EC)")
    private Integer algorithm;
    /**
    * RSA公钥n或ECC Point x
    */
    @Schema(description = "RSA公钥n或ECC Point x")
    private String param1;
    /**
    * RSA公钥e或ECC Point y
    */
    @Schema(description = "RSA公钥e或ECC Point y")
    private String param2;
    /**
    * 扩展信息
    */
    @Schema(description = "扩展信息")
    private String extendParams;
    /**
    * 证书颁发时间
    */
    @Schema(description = "证书颁发时间")
    private LocalDateTime notBefore;
    /**
    * 证书过期时间
    */
    @Schema(description = "证书过期时间")
    private LocalDateTime notAfter;
    /**
    * 证书撤销时间
    */
    @Schema(description = "证书撤销时间")
    private LocalDateTime revokeTime;
    /**
    * 证书状态(0-待完善、1-已颁发、2-已撤销)
    */
    @Schema(description = "证书状态(0-待完善、1-已颁发、2-已撤销)")
    private Integer state;
    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;
    /**
    * 创建人组织
    */
    @Schema(description = "创建人组织")
    private Long createdOrgId;


}
