

package org.springblade.modules.iot.persistence.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "iot_device_protocol")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IoTDeviceProtocolBO implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private String description;
  private Byte state;
  @Id private String id;
  private String type;
  private String configuration;
  private String fileName;
  private String url;
  private String jscript;
  private String payload;
  private Boolean decode;
  private Boolean needBs4Decode;
  private String example;
  private String version;

  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  //  private String parentUnionId;

}
