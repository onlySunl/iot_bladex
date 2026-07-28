package org.springblade.modules.iot.device.entity;
import org.springblade.basic.base.entity.Entity;
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
import java.math.BigDecimal;

/**
 * <p>
 * 实体类
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
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@AutoTable(value = "iot_device_location", comment = "DeviceLocation table")
public class DeviceLocation extends Entity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备标识
     */
    @AutoColumn(value = "device_identification", comment = "设备标识")
    private String deviceIdentification;
    /**
     * 纬度
     */
    @AutoColumn(value = "latitude", comment = "纬度")
    private BigDecimal latitude;
    /**
     * 经度
     */
    @AutoColumn(value = "longitude", comment = "经度")
    private BigDecimal longitude;
    /**
     * 位置名称
     */
    @AutoColumn(value = "full_name", comment = "位置名称")
    private String fullName;
    /**
     * 省,直辖市编码
     */
    @AutoColumn(value = "province_code", comment = "省,直辖市编码")
    private String provinceCode;
    /**
     * 市编码
     */
    @AutoColumn(value = "city_code", comment = "市编码")
    private String cityCode;
    /**
     * 区县
     */
    @AutoColumn(value = "region_code", comment = "区县")
    private String regionCode;
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
