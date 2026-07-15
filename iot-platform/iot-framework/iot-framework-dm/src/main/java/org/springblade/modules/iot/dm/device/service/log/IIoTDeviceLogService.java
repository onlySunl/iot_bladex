

package org.springblade.modules.iot.dm.device.service.log;

import cn.hutool.json.JSONObject;
import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.pojo.entity.IoTDeviceEvents;
import org.springblade.modules.iot.pojo.entity.IoTDeviceLog;
import org.springblade.modules.iot.pojo.entity.IoTProduct;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogMetadataVO;
import org.springblade.modules.iot.pojo.vo.IoTDeviceLogVO;
import org.springblade.modules.iot.persistence.query.LogQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/9/23
 */
public interface IIoTDeviceLogService {

  /**
   * @return 存储策略
   */
  String getPolicy();

  /**
   * 保存日志消息
   *
   * @param upRequest 上行消息
   * @param ioTDeviceDTO 设备信息
   * @param ioTProduct 产品信息
   */
  void saveDeviceLog(BaseUPRequest upRequest, IoTDeviceDTO ioTDeviceDTO, IoTProduct ioTProduct);

  /**
   * 保存日志消息
   *
   * @param ioTDeviceLog 日志消息
   * @param ioTDeviceDTO 设备信息
   * @param ioTProduct 产品信息
   */
  default void saveDeviceLog(
      IoTDeviceLog ioTDeviceLog, IoTDeviceDTO ioTDeviceDTO, IoTProduct ioTProduct) {}

  /**
   * 查询设备日志
   *
   * @param logQuery 查询条件
   * @return list
   */
  IPage<IoTDeviceLogVO> pageList(LogQuery logQuery);

  /** 日志详情 */
  IoTDeviceLogVO queryById(LogQuery logQuery);

  /**
   * 事件详情
   *
   * @param productKey 产品标志
   */
  IPage<IoTDeviceEvents> queryEventTotal(String productKey, String iotId);

  /** 查询单属性或事件日志 */
  IPage<IoTDeviceLogMetadataVO> queryLogMeta(LogQuery logQuery);

  /**
   * @return 存储配置信息
   */
  JSONObject configMetadata();
}
