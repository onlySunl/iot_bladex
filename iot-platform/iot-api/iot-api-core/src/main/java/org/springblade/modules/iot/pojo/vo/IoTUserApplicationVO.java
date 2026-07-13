

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTUserApplication;

import org.springblade.modules.iot.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "iot_user_application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTUserApplicationVO extends IoTUserApplication implements Serializable {


  //  @Column(name = "uuid")
  //  private Long uuid;


















  @Excel(name = "设备数量")
  private Integer devNum;

}
