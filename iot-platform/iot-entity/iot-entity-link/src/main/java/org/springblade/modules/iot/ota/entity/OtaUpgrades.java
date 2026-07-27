package org.springblade.modules.iot.ota.entity;
import org.springblade.common.entity.CustomBaseEntity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;

/**
 * <p>
 * 实体类
 * OTA升级包
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:36:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "ota_upgrades", comment = "OtaUpgrades table")
public class OtaUpgrades extends CustomBaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 包名称
     */
    @AutoColumn(value = "package_name", comment = "包名称")
    private String packageName;
    /**
     * 升级包类型(0:软件包、1:固件包)
     */
    @AutoColumn(value = "package_type", comment = "升级包类型(0:软件包、1:固件包)")
    private Integer packageType;
    /**
     * 产品标识
     */
    @AutoColumn(value = "product_identification", comment = "产品标识")
    private String productIdentification;
    /**
     * 产品版本序号
     */
    @AutoColumn(value = "product_version_no", comment = "产品版本序号")
    private String productVersionNo;
    /**
     * 升级包版本号
     */
    @AutoColumn(value = "version", comment = "升级包版本号")
    private String version;
    /**
     * 升级包的位置
     */
    @AutoColumn(value = "file_location", comment = "升级包的位置")
    private String fileLocation;

    /**
     * 签名方法
     */
    @AutoColumn(value = "sign_method", comment = "签名方法")
    private Integer signMethod;
    /**
     * 状态 (0:禁用 1:启用)
     */
    @AutoColumn(value = "status", comment = "状态(0:禁用 1:启用)")
    private Integer status;

    /**
     * 升级包功能描述
     */
    @AutoColumn(value = "description", comment = "升级包功能描述")
    private String description;
    /**
     * 自定义信息
     */
    @AutoColumn(value = "custom_info", comment = "自定义信息")
    private String customInfo;
    /**
     * 描述
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
