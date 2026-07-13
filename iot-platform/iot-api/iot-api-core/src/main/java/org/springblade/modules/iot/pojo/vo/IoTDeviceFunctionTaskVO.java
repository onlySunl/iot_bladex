

package org.springblade.modules.iot.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
public class IoTDeviceFunctionTaskVO implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private String taskName;
  private String productKey;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date beginTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;

  private String creator;
  private String creatorId;
  private String command;
  private String commandData;
  private Integer successNum;
  private Integer totalNum;
  private Integer status;
}
