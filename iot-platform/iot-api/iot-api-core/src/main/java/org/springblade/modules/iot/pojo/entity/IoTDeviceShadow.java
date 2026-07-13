

package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.ColumnType;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("iot_device_shadow")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceShadow extends CustomBaseEntity {


  /** 本平台设备唯一标识符 */
  @TableField(value = "iot_id")
  @AutoColumn(comment = "本平台设备唯一标识符", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String iotId;

  /** 产品KEY */
  @TableField(value = "product_key")
  @AutoColumn(comment = "产品KEY", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String productKey;

  /** 设备自身序号 */
  @TableField(value = "device_id")
  @AutoColumn(comment = "设备自身序号", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String deviceId;

  /** 第三方平台设备ID唯一标识符 */
  @TableField(value = "ext_device_id")
  @AutoColumn(comment = "第三方平台设备ID唯一标识符", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String extDeviceId;

  /** 注册时间 */
  @TableField(value = "active_time")
  @AutoColumn(comment = "注册时间", defaultValueType = DefaultValueEnum.NULL)
  private Date activeTime;

  /** 激活时间 */
  @TableField(value = "online_time")
  @AutoColumn(comment = "激活时间", defaultValueType = DefaultValueEnum.NULL)
  private Date onlineTime;

  /** 最后通信时间 */
  @TableField(value = "last_time")
  @AutoColumn(comment = "最后通信时间", defaultValueType = DefaultValueEnum.NULL)
  private Date lastTime;

  /** 更新时间 */
  @TableField(value = "update_date")
  @AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  private Date updateDate;

  /** 影子数据 */
  private String metadata;

  @TableField(value = "`instance`")
  @AutoColumn(comment = "影子数据", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String instance;

  /** 版本号 */
  @TableField(value = "version")
  @AutoColumn(comment = "版本号", defaultValueType = DefaultValueEnum.NULL)
  private Long version;

  private static final long serialVersionUID = 1L;
}
