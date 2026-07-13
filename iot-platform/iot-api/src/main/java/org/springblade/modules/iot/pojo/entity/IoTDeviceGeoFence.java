/*
 *
 *
 *
 *
 */

package org.springblade.modules.iot.pojo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.common.entity.CustomBaseEntity;
@TableName("iot_device_geo_fence")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGeoFence extends CustomBaseEntity {

  /** 围栏名称 */
  private String name;

  /** 触发模式 in.进入 out.离开 all.进入&离开 */
  private String touchWay;

  /** 范围 */
  private String fence;

  /** 类型 circle.圆 polygon.多边形 */
  private String type;

  /** 圆形中心点 */
  private String point;

  /** 半径 */
  private BigDecimal radius;

  /** 创建人 */
  private String creatorId;

  /** 周触发(天) */
  private String weekTime;

  /** 天触发开始时间(时) */
  private String beginTime;

  /** 天触发结束时间(时) */
  private String endTime;

  /** 归属第三方应用 */
  private String creatorUser;

  /** 创建时间 */
  private Date createDate;

  /** 更新时间 */
  private Date updateDate;

  /** 不触发时间 */
  private String noTriggerTime;

  /** 延迟时间 分钟 */
  private Integer delayTime;
}
