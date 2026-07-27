package org.springblade.modules.iot.device.easyexcel;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceExportData
 * -----------------------------------------------------------------------------
 * Description:
 * 设备档案导出
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/21       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/21 23:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "DeviceExportData", description = "设备导出数据")
public class DeviceExportData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty(index = 0, value = "客户端标识")
    @ColumnWidth(30)
    @Schema(description = "客户端标识")
    private String clientId;

    @ExcelProperty(index = 1, value = "用户名")
    @ColumnWidth(20)
    @Schema(description = "用户名")
    private String userName;

    @ExcelProperty(index = 2, value = "密码")
    @ColumnWidth(20)
    @Schema(description = "密码")
    private String password;

    @ExcelProperty(index = 3, value = "应用ID")
    @ColumnWidth(20)
    @Schema(description = "应用ID")
    private String appId;

    @ExcelProperty(index = 4, value = "认证方式")
    @ColumnWidth(20)
    @Schema(description = "认证方式0-用户名密码，1-ssl证书")
    private Integer authMode;

    @ExcelProperty(index = 5, value = "加密密钥")
    @ColumnWidth(30)
    @Schema(description = "加密密钥")
    private String encryptKey;

    @ExcelProperty(index = 6, value = "加密向量")
    @ColumnWidth(30)
    @Schema(description = "加密向量")
    private String encryptVector;

    @ExcelProperty(index = 7, value = "签名密钥")
    @ColumnWidth(30)
    @Schema(description = "签名密钥")
    private String signKey;

    @ExcelProperty(index = 8, value = "传输协议的加密方式")
    @ColumnWidth(30)
    @Schema(description = "传输协议的加密方式：0-明文传输、1-SM4、2-AES")
    private Integer encryptMethod;

    @ExcelProperty(index = 9, value = "设备标识")
    @ColumnWidth(30)
    @Schema(description = "设备标识")
    private String deviceIdentification;

    @ExcelProperty(index = 10, value = "设备名称")
    @ColumnWidth(30)
    @Schema(description = "设备名称")
    private String deviceName;

    @ExcelProperty(index = 11, value = "连接实例")
    @ColumnWidth(30)
    @Schema(description = "连接实例")
    private String connector;

    @ExcelProperty(index = 12, value = "设备描述")
    @ColumnWidth(30)
    @Schema(description = "设备描述")
    private String description;

    @ExcelProperty(index = 13, value = "设备状态")
    @ColumnWidth(30)
    @Schema(description = "设备状态:1启用ENABLE || 2禁用DISABLE||未激活NOTACTIVE 0")
    private Integer deviceStatus;

    @ExcelProperty(index = 14, value = "连接状态")
    @ColumnWidth(30)
    @Schema(description = "连接状态:在线：1ONLINE || 离线：2OFFLINE || 未连接：INIT 0")
    private Integer connectStatus;

    @ExcelProperty(index = 15, value = "设备标签")
    @ColumnWidth(30)
    @Schema(description = "设备标签")
    private String deviceTags;

    @ExcelProperty(index = 16, value = "产品标识")
    @ColumnWidth(30)
    @Schema(description = "产品标识")
    private String productIdentification;

    @ExcelProperty(index = 17, value = "软件版本")
    @ColumnWidth(30)
    @Schema(description = "软件版本")
    private String swVersion;

    @ExcelProperty(index = 18, value = "固件版本")
    @ColumnWidth(30)
    @Schema(description = "固件版本")
    private String fwVersion;

    @ExcelProperty(index = 19, value = "sdk版本")
    @ColumnWidth(30)
    @Schema(description = "sdk版本")
    private String deviceSdkVersion;

    @ExcelProperty(index = 20, value = "网关设备id")
    @ColumnWidth(30)
    @Schema(description = "网关设备id")
    private String gatewayId;

    @ExcelProperty(index = 21, value = "设备类型")
    @ColumnWidth(30)
    @Schema(description = "设备类型:0普通设备 || 1网关设备 || 2子设备 ")
    private Integer nodeType;

    @ExcelProperty(index = 22, value = "备注")
    @ColumnWidth(30)
    @Schema(description = "备注")
    private String remark;

    @ExcelProperty(index = 23, value = "创建人组织")
    @ColumnWidth(30)
    @Schema(description = "创建人组织")
    private Long createdOrgId;

    @ExcelProperty(index = 24, value = "创建时间")
    @ColumnWidth(30)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
