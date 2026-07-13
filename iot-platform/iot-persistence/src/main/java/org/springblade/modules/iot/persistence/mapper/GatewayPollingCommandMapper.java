

package org.springblade.modules.iot.persistence.mapper;

import org.springblade.modules.iot.pojo.entity.GatewayPollingCommand;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;

/**
 * 网关轮询指令 Mapper
 * 
 * @author Aleo
 * @date 2025-10-26
 */
@Mapper
public interface GatewayPollingCommandMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<GatewayPollingCommand> {

  /**
   * 根据网关设备查询指令列表
   * 
   * @param gatewayProductKey 网关产品KEY
   * @param gatewayDeviceId 网关设备ID
   * @return 指令列表(按执行顺序排序)
   */
  List<GatewayPollingCommand> selectByGateway(@Param("gatewayProductKey") String gatewayProductKey,
                                               @Param("gatewayDeviceId") String gatewayDeviceId);

  /**
   * 根据网关设备删除指令
   * 
   * @param gatewayProductKey 网关产品KEY
   * @param gatewayDeviceId 网关设备ID
   * @return 删除行数
   */
  int deleteByGateway(@Param("gatewayProductKey") String gatewayProductKey,
                      @Param("gatewayDeviceId") String gatewayDeviceId);

  /**
   * 批量插入指令
   * 
   * @param commands 指令列表
   * @return 插入行数
   */
  int batchInsert(@Param("commands") List<GatewayPollingCommand> commands);
}
