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
import org.springblade.modules.iot.pojo.entity.IoTDeviceFunctionTask;
import org.springblade.modules.iot.pojo.bo.IoTDeviceFunctionHistoryBO;
import org.springblade.modules.iot.pojo.bo.IoTDeviceFunctionTaskBO;
import org.springblade.modules.iot.pojo.vo.IoTDeviceFunctionHistoryVO;
import org.springblade.modules.iot.pojo.vo.IoTDeviceFunctionTaskVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 设备功能下发任务Mapper接口
 *
 * @since 2025-12-30
 */
public interface IoTDeviceFunctionTaskMapper extends BaseMapper<IoTDeviceFunctionTask> {

  List<IoTDeviceFunctionTaskVO> selectTaskList(
      @Param("bo") IoTDeviceFunctionTaskBO bo, @Param("unionId") String unionId);

  List<IoTDeviceFunctionHistoryVO> queryFunctionListByTaskId(
      @Param("bo") IoTDeviceFunctionHistoryBO bo, @Param("unionId") String unionId);

  int retryTask(@Param("id") Long taskId);

  IoTDeviceFunctionTask selectOneTask(@Param("bo") IoTDeviceFunctionTask bo);
}
