

package org.springblade.modules.iot.persistence.entity.vo;

import org.springblade.modules.iot.pojo.entity.IoTUserApplication;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTUserApplicationVO extends IoTUserApplication {

  private String appUniqueId;

  private Integer devNum;

}
