

package org.springblade.modules.iot.persistence.entity.vo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.Data;

/**
 * 设备型号视图对象 iot_device_model @Author ruoyi
 *
 * @since 2025-09-09
 */
@Data
@Schema(description = "设备型号视图对象")
public class IoTDeviceModelVO {

  private static final long serialVersionUID = 1L;

  /** 设备型号 */
  @Schema(description = "设备型号")
  private String deviceModel;

  /** 型号名称 */
  @Schema(description = "型号名称")
  private String deviceModelName;

  /** 设备图标 */
  @Schema(description = "设备图标")
  private String icon;

  /** 网络协议 */
  @Schema(description = "网络协议")
  private String protocol;

  /** 产品编号 */
  @Schema(description = "产品编号")
  private String productKey;

  /** 页面配置 */
  @Schema(description = "页面配置")
  private Map<String, Object> pageCtrl;

  @Schema(description = "后台内部扩展字段")
  private JSONObject extConf;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getDeviceModel() {
    return deviceModel;
  }

  public void setDeviceModel(String deviceModel) {
    this.deviceModel = deviceModel;
  }

  public String getDeviceModelName() {
    return deviceModelName;
  }

  public void setDeviceModelName(String deviceModelName) {
    this.deviceModelName = deviceModelName;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getProductKey() {
    return productKey;
  }

  public void setProductKey(String productKey) {
    this.productKey = productKey;
  }

  public Map<String, Object> getPageCtrl() {
    return pageCtrl;
  }

  public void setPageCtrl(String pageCtrl) {
    this.pageCtrl = JSONUtil.parseObj(pageCtrl);
  }

  public JSONObject getExtConf() {
    return extConf;
  }

  public void setExtConf(String extConf) {
    JSONObject map = JSONUtil.parseObj(extConf);
    this.extConf = map;
  }
}
