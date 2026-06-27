package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;


import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("qs_device_snapshot")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "qs_device_snapshot", comment = "设备抓图记录表")
public class QsDeviceSnapshot extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableField(value = "id")
    @AutoColumn(comment = "主键ID", length = 20)
    private Long id;

    /** 关联设备ID */
    @TableField(value = "device_id")
    @AutoColumn(comment = "关联设备ID", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Long deviceId;

    /** 设备编号 */
    @TableField(value = "device_code")
    @AutoColumn(comment = "设备编号", length = 128, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceCode;

    /** 设备名称 */
    @TableField(value = "device_name")
    @AutoColumn(comment = "设备名称", length = 256, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String deviceName;

    /** 文件访问地址 */
    @TableField(value = "file_url")
    @AutoColumn(comment = "文件访问地址", length = 1024, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String fileUrl;

    /** 文件存储路径 */
    @TableField(value = "file_path")
    @AutoColumn(comment = "文件存储路径", length = 1024, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String filePath;

    /** 文件大小(字节) */
    @TableField(value = "file_size")
    @AutoColumn(comment = "文件大小(字节)", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Long fileSize;

    /** 文件名称 */
    @TableField(value = "file_name")
    @AutoColumn(comment = "文件名称", length = 256, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String fileName;

    /** 文件类型(jpg/png等) */
    @TableField(value = "file_type")
    @AutoColumn(comment = "文件类型", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String fileType;

    /** 抓图类型 */
    @TableField(value = "snapshot_type")
    @AutoColumn(comment = "抓图类型", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String snapshotType;

    /** SDK类型 */
    @TableField(value = "sdk_type")
    @AutoColumn(comment = "SDK类型", length = 64, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String sdkType;

    /** 通道号 */
    @TableField(value = "channel")
    @AutoColumn(comment = "通道号", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Long channel;

    /** 抓图时间 */
    @TableField(value = "capture_time")
    @AutoColumn(comment = "抓图时间", defaultValueType = DefaultValueEnum.NULL)
    private Date captureTime;

    /** 图片宽度 */
    @TableField(value = "width")
    @AutoColumn(comment = "图片宽度", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer width;

    /** 图片高度 */
    @TableField(value = "height")
    @AutoColumn(comment = "图片高度", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer height;

    /** 纬度 */
    @TableField(value = "latitude")
    @AutoColumn(comment = "纬度", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private BigDecimal latitude;

    /** 经度 */
    @TableField(value = "longitude")
    @AutoColumn(comment = "经度", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private BigDecimal longitude;
}
