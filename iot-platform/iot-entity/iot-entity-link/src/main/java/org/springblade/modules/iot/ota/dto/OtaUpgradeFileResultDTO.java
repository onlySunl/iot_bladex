package org.springblade.modules.iot.ota.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import org.springblade.modules.iot.ota.enumeration.OtaPackageSignMethodEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <p>
 * OTA升级文件信息DTO
 * 用于OTA升级过程中传递文件相关详细信息
 * </p>
 *
 * @author mqttsnet
 * @version 1.0.0
 * @since 2025/10/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(title = "OtaUpgradeFileResultDTO", description = "OTA升级文件信息")
public class OtaUpgradeFileResultDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @Schema(description = "文件ID")
    private Long id;

    /**
     * 文件访问地址
     */
    @Schema(description = "文件访问地址")
    private String url;

    /**
     * 文件md5
     */
    @Schema(description = "文件md5")
    private String fileMd5;

    /**
     * 文件SHA256
     */
    @Schema(description = "文件SHA256")
    private String fileSha256;

    /**
     * 原始文件名
     */
    @Schema(description = "原始文件名")
    private String originalFileName;

    /**
     * 文件大小（字节）
     */
    @Schema(description = "文件大小")
    private Long size;

    /**
     * 文件后缀
     */
    @Schema(description = "文件后缀")
    private String suffix;

    /**
     * 根据签名方法获取文件签名
     *
     * @param signMethod 签名方法
     * @return 文件签名的Optional包装，如果签名方法不支持或签名为空则返回Optional.empty()
     */
    public Optional<String> getFileSign(OtaPackageSignMethodEnum signMethod) {
        if (Objects.isNull(signMethod)) {
            return Optional.empty();
        }
        String sign = switch (signMethod) {
            case MD5 -> fileMd5;
            case SHA256 -> fileSha256;
        };
        return Optional.ofNullable(sign);
    }
}