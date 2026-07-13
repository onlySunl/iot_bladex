

package org.springblade.modules.iot.pojo.vo;

import org.springblade.modules.iot.pojo.entity.IoTDevice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTDeviceVO extends IoTDevice {





  /** 派生元数据,有的设备的属性，功能，事件可能会动态的添加 */
  private String metadata;




  /** 别名 */
  private String nickName;

  private String latlng;


  private String topic;
  private String subMsgType;
  private String subType;
  private String subUrl;
  private String deviceTypeName;
  private String deviceModel;
  private String classifiedName;
  private String name;

  private String deviceNode;


  private String thirdPlatform;

}
