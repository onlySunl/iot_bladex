package org.springblade.modules.iot.cacert.vo.save.license;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * Description:
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/7/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(description = "PEM格式证书导入请求")
public class CaCertPemImportSaveVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 证书名称
     */
    @Schema(description = "证书名称")
    @Size(max = 50, message = "证书名称长度不能超过{max}")
    private String certName;

    @Schema(description = "CA证书PEM格式内容（根CA证书）", example = "-----BEGIN CERTIFICATE-----...")
    @NotBlank
    private String caCertPem;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;

}
