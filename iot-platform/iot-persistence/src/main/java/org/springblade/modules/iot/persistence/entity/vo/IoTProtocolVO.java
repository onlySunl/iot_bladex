

package org.springblade.modules.iot.persistence.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IoTProtocolVO implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private String description;
  private Byte state;
  private String type;
  private String configuration;
  private String example;
  private String fileName;
  private String url;
  private String jscript;
  private String needBs4Decode;

  /** 创建时间 */

  /** 版本号 */
    private String version;
}
