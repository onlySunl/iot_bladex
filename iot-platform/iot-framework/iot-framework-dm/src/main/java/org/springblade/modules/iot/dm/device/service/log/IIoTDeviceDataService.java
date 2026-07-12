/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.dm.device.service.log;

import org.springblade.modules.iot.persistence.base.BaseUPRequest;
import org.springblade.modules.iot.persistence.dto.IoTDeviceDTO;
import org.springblade.modules.iot.persistence.entity.IoTDeviceEvents;
import org.springblade.modules.iot.persistence.entity.IoTDeviceLog;
import org.springblade.modules.iot.persistence.entity.IoTProduct;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceLogMetadataVO;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceLogVO;
import org.springblade.modules.iot.persistence.query.LogQuery;
import org.springblade.modules.iot.persistence.query.PageBean;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/9/23
 */
public interface IIoTDeviceDataService {

  /**
   * 保存日志消息
   *
   * @param upRequest 上行消息
   * @param ioTDeviceDTO 设备信息
   * @param ioTProduct 产品信息
   */
  void saveDeviceLog(BaseUPRequest upRequest, IoTDeviceDTO ioTDeviceDTO, IoTProduct ioTProduct);

  default void saveDeviceLog(
      IoTDeviceLog ioTDeviceLog, IoTDeviceDTO ioTDeviceDTO, IoTProduct ioTProduct) {}

  /**
   * 查询设备日志
   *
   * @param logQuery 查询条件
   * @return list
   */
  default PageBean<IoTDeviceLogVO> pageList(LogQuery logQuery) {
    return null;
  }

  /** 日志详情 */
  default IoTDeviceLogVO queryById(LogQuery logQuery) {
    return null;
  }

  /**
   * 事件详情
   *
   * @param productKey 产品标志
   */
  default PageBean<IoTDeviceEvents> queryEventTotal(String productKey, String iotId) {
    return null;
  }

  default PageBean<IoTDeviceLogMetadataVO> queryLogMeta(LogQuery logQuery) {
    return null;
  }
}
