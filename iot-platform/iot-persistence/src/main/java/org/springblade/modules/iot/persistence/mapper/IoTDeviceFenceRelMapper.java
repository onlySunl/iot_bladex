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

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.modules.iot.persistence.common.BaseMapper;
import org.springblade.modules.iot.persistence.entity.IoTDeviceFenceRel;
import org.springblade.modules.iot.persistence.entity.vo.IoTDeviceGeoFenceVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 设备和围栏中间表 @Author gitee.com/NexIoT
 *
 * @since 2023/8/5 8:54
 */
public interface IoTDeviceFenceRelMapper extends BaseMapper<IoTDeviceFenceRel> {

  int deleteFenceInstance(@Param("iotId") String iotId);

  int deleteDeviceIdAndFenceId(@Param("deviceId") String deviceId, @Param("fenceId") Long fenceId);

  List<IoTDeviceGeoFenceVO> selectFenceByIotId(@Param("iotId") String iotId);
}
