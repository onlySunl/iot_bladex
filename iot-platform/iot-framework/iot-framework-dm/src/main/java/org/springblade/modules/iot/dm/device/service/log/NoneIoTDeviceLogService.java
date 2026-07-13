

package org.springblade.modules.iot.dm.device.service.log;

import cn.hutool.json.JSONObject;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.pojo.entity.IoTDeviceEvents;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogMetadataVO;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogVO;
import org.springblade.modules.iot.persistence.query.LogQuery;
import org.springblade.modules.iot.persistence.query.PageBean;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 不存储任何设备日志
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/9/22 16:10
 */
@Component
@Slf4j
public class NoneIoTDeviceLogService extends AbstractIoTDeviceLogService {

  private String storePolicy = "none";

  @Override
  public String getPolicy() {
    return storePolicy;
  }

  @Override
  public void saveDeviceLog(
      BaseUPRequest upRequest, IoTDeviceDTO ioTDeviceDTO, IoTProduct ioTProduct) {}

  @Override
  public PageBean<IoTDeviceLogVO> pageList(LogQuery logQuery) {
    return new PageBean(
        new ArrayList(), Long.parseLong(0 + ""), logQuery.getPageSize(), logQuery.getPageNum());
  }

  @Override
  public IoTDeviceLogVO queryById(LogQuery logQuery) {
    return new IoTDeviceLogVO();
  }

  @Override
  public PageBean<IoTDeviceEvents> queryEventTotal(String productKey, String iotId) {
    return new PageBean(new ArrayList(), Long.parseLong(0 + ""), 10, 1);
  }

  @Override
  public PageBean<IoTDeviceLogMetadataVO> queryLogMeta(LogQuery logQuery) {
    return new PageBean(
        new ArrayList(), Long.parseLong(0 + ""), logQuery.getPageSize(), logQuery.getPageNum());
  }

  @Override
  public JSONObject configMetadata() {
    return null;
  }
}
