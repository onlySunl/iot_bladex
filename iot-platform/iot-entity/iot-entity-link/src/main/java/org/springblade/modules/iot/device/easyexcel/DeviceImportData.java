package org.springblade.modules.iot.device.easyexcel;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceImportData
 * -----------------------------------------------------------------------------
 * Description:
 * 设备档案导入数据
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/20       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/6/20 14:28
 */
@Data
@NoArgsConstructor
@Schema(title = "DeviceImportData", description = "设备档案导入数据")
public class DeviceImportData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty(index = 0, value = "应用场景")
    @ColumnWidth(30)
    @NotBlank(message = "应用场景不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9-]{2,64}$", message = "应用场景格式不正确，支持英文大小写,数字和中划线,长度2-64")
    private String appId;

    @ExcelProperty(index = 1, value = "产品标识")
    @ColumnWidth(30)
    @NotBlank(message = "产品标识不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9-]{2,64}$", message = "产品标识格式不正确，支持英文大小写,数字和中划线,长度2-64")
    private String productIdentification;

    @ExcelProperty(index = 2, value = "设备名称")
    @ColumnWidth(30)
    @Pattern(regexp = "^[\u4E00-\u9FA5a-zA-Z0-9-#]{2,64}$", message = "设备名称格式不正确，支持中文，英文大小写，数字，中划线和#，长度2-64")
    private String deviceName;

    @ExcelProperty(index = 3, value = "设备标识")
    @ColumnWidth(30)
    @Pattern(regexp = "^[a-zA-Z0-9-]{2,64}$", message = "设备标识格式不正确，支持英文大小写,数字和中划线,长度2-64")
    private String deviceIdentification;

    @ExcelProperty(index = 4, value = "客户端标识")
    @ColumnWidth(30)
    @Pattern(regexp = "^[a-zA-Z0-9-_]{8,64}$", message = "客户端标识格式不正确，支持英文大小写、数字、下划线和中划线，长度8-64")
    private String clientId;

    @ExcelProperty(index = 5, value = "实例名称")
    @ColumnWidth(30)
    @NotBlank(message = "实例名称不能为空")
    private String connector;

    @ExcelProperty(index = 6, value = "用户名")
    @ColumnWidth(30)
    @Pattern(regexp = "^[a-zA-Z0-9]{5,50}$", message = "用户名格式不正确，只允许输入数字和字母，5个字符以上，50个字符以内")
    private String userName;

    @ExcelProperty(index = 7, value = "密码")
    @ColumnWidth(30)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*()\\-_=+|\\[\\]{};:<>/?]).{8,}$", message = "密码格式不正确，建议满足复杂度要求(至少8位，至少1数字、1大写字母、1小写字母、1特殊字符(~!@#$%^&*()-_=+|[{}];:<>/?))")
    private String password;

    @ExcelProperty(index = 8, value = "设备标签")
    @ColumnWidth(30)
    @Pattern(regexp = "^[a-zA-Z0-9-_]{8,64}$", message = "设备标签格式不正确，只支持英文大小写、数字、下划线和中划线，长度8-64")
    private String deviceTags;

    @ExcelProperty(index = 9, value = "加密密钥")
    @ColumnWidth(30)
    @Pattern(regexp = "^[a-zA-Z0-9-_]{8,64}$", message = "加密密钥格式不正确，只支持英文大小写、数字、下划线和中划线，长度8-64")
    private String encryptKey;

    @ExcelProperty(index = 10, value = "加密向量")
    @ColumnWidth(30)
    @Pattern(regexp = "^[a-zA-Z0-9-_]{8,64}$", message = "加密向量格式不正确，只支持英文大小写、数字、下划线和中划线，长度8-64")
    private String encryptVector;

    @ExcelProperty(index = 11, value = "签名密钥")
    @ColumnWidth(30)
    @Pattern(regexp = "^[a-zA-Z0-9-_]{8,64}$", message = "签名密钥格式不正确，只支持英文大小写、数字、下划线和中划线，长度8-64")
    private String signKey;

    @ExcelProperty(index = 12, value = "传输协议的加密方式")
    @ColumnWidth(30)
    @NotBlank(message = "传输协议的加密方式不能为空")
    @Pattern(regexp = "^[012]$", message = "传输协议的加密方式格式不正确，只能为0、1、2")
    private String encryptMethod;

    @ExcelProperty(index = 13, value = "设备类型")
    @ColumnWidth(30)
    @NotBlank(message = "设备类型不能为空")
    @Pattern(regexp = "^[01]$", message = "设备类型格式不正确，只能为0或1")
    private String nodeType;

    @ExcelProperty(index = 14, value = "设备状态")
    @ColumnWidth(30)
    @NotBlank(message = "设备状态不能为空")
    @Pattern(regexp = "^[012]$", message = "设备类型格式不正确，只能为0、1、2")
    private String deviceStatus;

    @ExcelProperty(index = 15, value = "软件版本")
    @ColumnWidth(30)
    @NotBlank(message = "软件版本不能为空")
    @Pattern(regexp = "^\\d+(\\.\\d+){2}(-[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?(\\+[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?$", message = "软件版本格式不正确，请确保符合版本号格式规则")
    private String swVersion;

    @ExcelProperty(index = 16, value = "固件版本")
    @ColumnWidth(30)
    @NotBlank(message = "固件版本不能为空")
    @Pattern(regexp = "^\\d+(\\.\\d+){2}(-[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?(\\+[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?$", message = "固件版本格式不正确，请确保符合版本号格式规则")
    private String fwVersion;

    @ExcelProperty(index = 17, value = "sdk版本")
    @ColumnWidth(30)
    @NotBlank(message = "sdk版本不能为空")
    private String deviceSdkVersion;

}
