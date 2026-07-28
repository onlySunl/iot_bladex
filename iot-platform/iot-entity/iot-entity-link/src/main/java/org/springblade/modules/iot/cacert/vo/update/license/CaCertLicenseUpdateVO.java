package org.springblade.modules.iot.cacert.vo.update.license;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.springblade.basic.base.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * 表单修改方法VO
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
public class CaCertLicenseUpdateVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @NotNull(message = "请填写id", groups = Entity.Update.class)
    private Long id;

    /**
     * 证书名称
     */
    @Schema(description = "证书名称")
    @Size(max = 50, message = "证书名称长度不能超过{max}")
    private String certName;
    /**
     * 证书序列号
     */
    @Schema(description = "证书序列号")
    @Size(max = 100, message = "证书序列号长度不能超过{max}")
    private String serialNumber;
    /**
     * 通用名称
     */
    @Schema(description = "通用名称")
    @Size(max = 50, message = "通用名称长度不能超过{max}")
    private String commonName;
    /**
     * 组织名称
     */
    @Schema(description = "组织名称")
    @Size(max = 50, message = "组织名称长度不能超过{max}")
    private String organization;
    /**
     * 组织单位名称
     */
    @Schema(description = "组织单位名称")
    @Size(max = 50, message = "组织单位名称长度不能超过{max}")
    private String organizationalUnit;
    /**
     * 国家
     */
    @Schema(description = "国家")
    @Size(max = 50, message = "国家长度不能超过{max}")
    private String countryName;
    /**
     * 省份/州
     */
    @Schema(description = "省份/州")
    @Size(max = 50, message = "省份/州长度不能超过{max}")
    private String provinceName;
    /**
     * 城市
     */
    @Schema(description = "城市")
    @Size(max = 50, message = "城市长度不能超过{max}")
    private String localityName;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Size(max = 50, message = "邮箱长度不能超过{max}")
    private String email;
    /**
     * License文件内容(Base64编码)
     */
    @Schema(description = "License文件内容(Base64编码)")
    @Size(max = 2147483647, message = "License文件内容(Base64编码)长度不能超过{max}")
    private String licenseBase64;
    /**
     * 营业执照文件ID
     */
    @Schema(description = "营业执照文件ID")
    @Size(max = 100, message = "营业执照文件ID长度不能超过{max}")
    private String businessLicenseFileid;
    /**
     * 授权证书文件ID
     */
    @Schema(description = "授权证书文件ID")
    @Size(max = 100, message = "授权证书文件ID长度不能超过{max}")
    private String authorizationCertFileid;
    /**
     * 证书文件ID
     */
    @Schema(description = "证书文件ID")
    @Size(max = 100, message = "证书文件ID长度不能超过{max}")
    private String certFileid;
    /**
     * 算法(0-RSA、1-EC)
     */
    @Schema(description = "算法(0-RSA、1-EC)")
    @NotNull(message = "请填写算法(0-RSA、1-EC)")
    private Integer algorithm;
    /**
     * RSA公钥n或ECC Point x
     */
    @Schema(description = "RSA公钥n或ECC Point x")
    @Size(max = 1024, message = "RSA公钥n或ECC Point x长度不能超过{max}")
    private String param1;
    /**
     * RSA公钥e或ECC Point y
     */
    @Schema(description = "RSA公钥e或ECC Point y")
    @Size(max = 1024, message = "RSA公钥e或ECC Point y长度不能超过{max}")
    private String param2;
    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    @Size(max = 2147483647, message = "扩展信息长度不能超过{max}")
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
    @NotNull(message = "请填写证书状态(0-待完善、1-已颁发、2-已撤销)")
    private Integer state;

    /**
     * 证书指纹(SHA-256)
     */
    @Schema(description = " 证书指纹(SHA-256)")
    private String thumbprint;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 创建人组织
     */
    @Schema(description = "创建人组织")
    private Long createdOrgId;

}
