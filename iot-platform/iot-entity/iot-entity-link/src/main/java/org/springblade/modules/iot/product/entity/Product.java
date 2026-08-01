package org.springblade.modules.iot.product.entity;
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

/**
 * 产品模型实体。
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_product", comment = "Product table")
public class Product extends Entity<Long> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     */
    @AutoColumn(value = "app_id", comment = "应用ID")
    private String appId;
    /**
     * 模板ID
     */
    @AutoColumn(value = "template_id", comment = "模板ID")
    private Long templateId;
    /**
     * 产品名称:自定义，支持中文、英文大小写、数字、下划线和中划线
     */
    @AutoColumn(value = "product_name", comment = "产品名称:自定义，支持中文、英文大小写、数字、下划线和中划线")
    private String productName;
    /**
     * 产品标识
     */
    @AutoColumn(value = "product_identification", comment = "产品标识")
    private String productIdentification;
    /**
     * 支持以下两种产品类型1•COMMON：普通产品，需直连设备。2•GATEWAY：网关产品，可挂载子设备。 0其他未知产品
     */
    @AutoColumn(value = "product_type", comment = "支持以下两种产品类型1•COMMON：普通产品，需直连设备。2•GATEWAY：网关产品，可挂载子设备。 0其他未知产品")
    private Integer productType;
    /**
     * 厂商ID:支持英文大小写，数字，下划线和中划线
     */
    @AutoColumn(value = "manufacturer_id", comment = "厂商ID:支持英文大小写，数字，下划线和中划线")
    private String manufacturerId;
    /**
     * 厂商名称 :支持中文、英文大小写、数字、下划线和中划线
     */
    @AutoColumn(value = "manufacturer_name", comment = "厂商名称 :支持中文、英文大小写、数字、下划线和中划线")
    private String manufacturerName;
    /**
     * 产品型号，建议包含字母或数字来保证可扩展性。支持英文大小写、数字、下划线和中划线
     */
    @AutoColumn(value = "model", comment = "产品型号，建议包含字母或数字来保证可扩展性。支持英文大小写、数字、下划线和中划线")
    private String model;
    /**
     * 数据格式，默认为JSON无需修改。
     */
    @AutoColumn(value = "data_format", comment = "数据格式，默认为JSON无需修改。")
    private String dataFormat;
    /**
     * 设备类型:支持英文大小写、数字、下划线和中划线
     */
    @AutoColumn(value = "device_type", comment = "设备类型:支持英文大小写、数字、下划线和中划线")
    private String deviceType;
    /**
     * 设备接入平台的协议类型，默认为MQTT无需修改。
     */
    @AutoColumn(value = "protocol_type", comment = "设备接入平台的协议类型，默认为MQTT无需修改。")
    private String protocolType;
    /**
     * 状态(字典值：0启用  1停用)
     */
    @AutoColumn(value = "product_status", comment = "状态(字典值：0启用  1停用)")
    private Integer productStatus;
    /**
     * 产品当前激活的版本序号(系统发布时生成的快照标识,16 位短雪花字符串)。
     */
    @AutoColumn(value = "active_version_no", comment = "产品当前激活的版本序号(系统发布时生成的快照标识,16 位短雪花字符串)。")
    private String activeVersionNo;
    /**
     * 灰度期稳定版本序号(仅灰度态有值,晋升 / 回滚后清空),用于灰度回退定位。
     */
    @AutoColumn(value = "previous_full_version_no", comment = "灰度期稳定版本序号(仅灰度态有值,晋升 / 回滚后清空),用于灰度回退定位。")
    private String previousFullVersionNo;
    /**
     * 图标
     */
    @AutoColumn(value = "icon", comment = "图标")
    private String icon;
    /**
     * 产品描述
     */
    /**
     * 创建人组织
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织")
    private Long createdOrgId;

    /**
     * 逻辑删除标识:0-未删除 1-已删除
     */
    @AutoColumn(value = "deleted", comment = "逻辑删除标识:0-未删除 1-已删除")
    private Integer deleted;
}
