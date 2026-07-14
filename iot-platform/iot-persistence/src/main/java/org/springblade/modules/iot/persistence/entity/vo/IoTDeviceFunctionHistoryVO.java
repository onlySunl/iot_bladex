

package org.springblade.modules.iot.persistence.entity.vo;

import org.springblade.modules.iot.pojo.entity.IoTDeviceFunctionHistory;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
public class IoTDeviceFunctionHistoryVO extends IoTDeviceFunctionHistory {

  private String commandData;
}
