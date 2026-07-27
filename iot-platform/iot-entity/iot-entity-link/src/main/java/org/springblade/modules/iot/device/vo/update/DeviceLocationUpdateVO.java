package org.springblade.modules.iot.device.vo.update;

import org.springblade.common.entity.CustomBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 表单修改方法VO
 * 设备位置表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-05-30 23:05:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Builder
@Schema(title = "DeviceLocationUpdateVO", description = "设备位置表")
public class DeviceLocationUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = CustomBaseEntity.Update.class)
    private Long id;

    /**
     * 设备标识
     */
    @Schema(description = "设备标识", required = true)
    @Size(max = 255, message = "设备标识长度不能超过{max}")
    private String deviceIdentification;

    /**
     * 纬度
     */
    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "请填写纬度")
    private BigDecimal latitude;
    /**
     * 经度
     */
    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "请填写经度")
    private BigDecimal longitude;
    /**
     * 位置名称
     */
    @Schema(description = "位置名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "请填写位置名称")
    @Size(max = 500, message = "位置名称长度不能超过{max}")
    private String fullName;
    /**
     * 省,直辖市编码
     */
    @Schema(description = "省,直辖市编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "请填写省,直辖市编码")
    @Size(max = 50, message = "省,直辖市编码长度不能超过{max}")
    private String provinceCode;
    /**
     * 市编码
     */
    @Schema(description = "市编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "请填写市编码")
    @Size(max = 50, message = "市编码长度不能超过{max}")
    private String cityCode;
    /**
     * 区县
     */
    @Schema(description = "区县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "请填写区县")
    @Size(max = 50, message = "区县长度不能超过{max}")
    private String regionCode;
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
