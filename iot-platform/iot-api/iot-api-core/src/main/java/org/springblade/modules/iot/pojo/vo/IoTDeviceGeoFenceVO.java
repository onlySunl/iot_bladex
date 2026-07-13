

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceGeoFence;

import org.springblade.modules.iot.persistence.common.inteceptor.SQenGenId;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "iot_device_geo_fence")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceGeoFenceVO extends IoTDeviceGeoFence {

  @Id
  @KeySql(genId = SQenGenId.class)
  private Long id;


  /** 围栏状态 0.启用 1.停用 */
  private Integer status;













  private int deviceNum;




}
