

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTProduct;
import lombok.Data;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/11/16
 */
@Data
public class IoTProductVO extends IoTProduct {

  private String image;
  private int powerModel;
  private String lwm2mEdrxTime;


  private int devNum;

  private String storePolicy;
  private String type;
  private String gwName;
  private String gwPhotoUrl;
}
