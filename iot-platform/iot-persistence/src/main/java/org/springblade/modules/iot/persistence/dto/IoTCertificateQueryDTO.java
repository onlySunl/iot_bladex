package org.springblade.modules.iot.persistence.dto;

import org.springblade.modules.iot.persistence.query.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IoTCertificateQueryDTO extends BasePage {

  private String name;
  private String sslKey;
  private String expireStart;
  private String expireEnd;
  private Integer pageNum = 1;
  private Integer pageSize = 10;
}
