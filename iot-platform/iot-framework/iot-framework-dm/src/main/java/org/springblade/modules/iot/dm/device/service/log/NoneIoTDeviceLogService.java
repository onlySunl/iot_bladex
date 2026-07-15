

package org.springblade.modules.iot.dm.device.service.log;

import cn.hutool.json.JSONObject;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.pojo.entity.IoTDeviceEvents;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogMetadataVO;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogVO;
import org.springblade.modules.iot.persistence.query.LogQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.springblade.modules.iot.common.util.PageUtil;
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
  public IPage<IoTDeviceLogVO> pageList(LogQuery logQuery) {
    return PageUtil.initPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(logQuery.getPageNum(), logQuery.getPageSize()), new ArrayList(), Long.parseLong(0 + "")));
  }

  @Override
  public IoTDeviceLogVO queryById(LogQuery logQuery) {
    return new IoTDeviceLogVO();
  }

  @Override
  public IPage<IoTDeviceEvents> queryEventTotal(String productKey, String iotId) {
    return PageUtil.initPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(1, 10), new ArrayList(), Long.parseLong(0 + ""));
  }

  @Override
  public IPage<IoTDeviceLogMetadataVO> queryLogMeta(LogQuery logQuery) {
    return PageUtil.initPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(logQuery.getPageNum(), logQuery.getPageSize()), new ArrayList(), Long.parseLong(0 + "")));
  }

  @Override
  public JSONObject configMetadata() {
    return null;
  }
}
