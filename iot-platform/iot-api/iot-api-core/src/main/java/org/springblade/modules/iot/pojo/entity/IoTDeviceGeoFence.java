

package org.springblade.modules.iot.pojo.entity;

import org.springblade.modules.iot.persistence.common.inteceptor.SQenGenId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import org.springblade.common.entity.CustomBaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 设备围栏 @Author gitee.com/NexIoT
 *
 * @since 2023/8/5 8:47
 */
@TableName("iot_device_geo_fence")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGeoFence extends CustomBaseEntity {

  @Id
  @KeySql(genId = SQenGenId.class)

  /** 围栏名称 */
@TableField(value = "name")
@AutoColumn(comment = "围栏名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String name;


  /** 触发模式 in.进入 out.离开 all.进入&离开 */
@TableField(value = "touch_way")
@AutoColumn(comment = "触发模式 in.进入 out.离开 all.进入&离开", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String touchWay;

  /** 范围 */
@TableField(value = "fence")
@AutoColumn(comment = "范围", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String fence;

  /** 类型 circle.圆 polygon.多边形 */
@TableField(value = "type")
@AutoColumn(comment = "类型 circle.圆 polygon.多边形", length = 32, defaultValueType = DefaultValueEnum.NULL)
  private String type;

  /** 圆形中心点 */
@TableField(value = "point")
@AutoColumn(comment = "圆形中心点", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String point;

  /** 半径 */
@TableField(value = "radius")
@AutoColumn(comment = "半径", defaultValueType = DefaultValueEnum.NULL)
  private BigDecimal radius;

  /** 创建人 */
@TableField(value = "creator_id")
@AutoColumn(comment = "创建人", length = 128, defaultValueType = DefaultValueEnum.NULL)
  private String creatorId;

  /** 周触发(天) */
@TableField(value = "week_time")
@AutoColumn(comment = "周触发(天)", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String weekTime;

  /** 天触发开始时间(时) */
@TableField(value = "begin_time")
@AutoColumn(comment = "天触发开始时间(时)", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String beginTime;

  /** 天触发结束时间(时) */
@TableField(value = "end_time")
@AutoColumn(comment = "天触发结束时间(时)", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String endTime;

  /** 归属第三方应用 */
@TableField(value = "creator_user")
@AutoColumn(comment = "归属第三方应用", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String creatorUser;

  /** 创建时间 */
@TableField(value = "create_date")
@AutoColumn(comment = "创建时间", defaultValueType = DefaultValueEnum.NULL)
  private Date createDate;

  /** 更新时间 */
@TableField(value = "update_date")
@AutoColumn(comment = "更新时间", defaultValueType = DefaultValueEnum.NULL)
  private Date updateDate;

  /** 不触发时间 */
@TableField(value = "no_trigger_time")
@AutoColumn(comment = "不触发时间", length = 255, defaultValueType = DefaultValueEnum.NULL)
  private String noTriggerTime;

  /** 延迟时间 分钟 */
@TableField(value = "delay_time")
@AutoColumn(comment = "延迟时间 分钟", defaultValueType = DefaultValueEnum.NULL)
  private Integer delayTime;
}
