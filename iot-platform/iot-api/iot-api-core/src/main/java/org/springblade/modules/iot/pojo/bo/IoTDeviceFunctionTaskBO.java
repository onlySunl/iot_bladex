

package org.springblade.modules.iot.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/3/21
 */
@Data
public class IoTDeviceFunctionTaskBO {

  private String taskName;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date beginTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;

  private String[] ids;
  private String command;
  private String commandData;
  private String productKey;
  private String applicationId;
  private Long taskId;
  private String[] removeIds;
  private Boolean chooseAll;
  private List<String> extParam;

  /** 请求参数 */
  private Map<String, Object> params = new HashMap<>();
}
