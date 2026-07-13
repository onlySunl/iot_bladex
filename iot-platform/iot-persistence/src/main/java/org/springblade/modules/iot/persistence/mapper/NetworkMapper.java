

package org.springblade.modules.iot.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.iot.pojo.entity.Network;
import org.springblade.modules.iot.pojo.bo.NetworkBO;
import org.springblade.modules.iot.pojo.vo.NetworkVO;
import org.springblade.modules.iot.persistence.query.NetworkQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网络组件Mapper接口
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/20
 */
@Mapper
public interface NetworkMapper extends BaseMapper<Network> {

  int selectNetWorkByPort(@Param("port") Integer port, @Param("productKey") String productKey);

  /**
   * 根据端口查询TCP网络组件（排除指定ID）
   *
   * @param port 端口
   * @param excludeId 排除的网络组件ID
   * @return 网络组件列表
   */
  List<Network> selectTcpNetworkByPort(
      @Param("port") Integer port, @Param("excludeId") Integer excludeId);

  /**
   * 根据主机和用户名查询MQTT网络组件（排除指定ID）
   *
   * @param host 主机地址
   * @param username 用户名
   * @param excludeId 排除的网络组件ID
   * @return 网络组件列表
   */
  List<Network> selectMqttNetworkByHostAndUsername(
      @Param("host") String host,
      @Param("username") String username,
      @Param("excludeId") Integer excludeId);

  List<NetworkVO> selectNetworkListV1(@Param("bo") NetworkBO bo);

  NetworkVO selectById(@Param("id") Long id);

  /**
   * 查询网络组件列表
   *
   * @param query 查询条件
   * @return 网络组件列表
   */
  List<NetworkBO> selectNetworkList(NetworkQuery query);

  /**
   * 查询网络组件总数
   *
   * @param query 查询条件
   * @return 总数
   */
  int selectNetworkCount(NetworkQuery query);

  /**
   * 根据ID查询网络组件
   *
   * @param id 网络组件ID
   * @return 网络组件
   */
  Network selectNetworkById(@Param("id") Integer id);

  /**
   * 根据类型和唯一标识查询网络组件
   *
   * @param type 类型
   * @param unionId 唯一标识
   * @return 网络组件
   */
  Network selectNetworkByTypeAndUnionId(
      @Param("type") String type, @Param("unionId") String unionId);

  /**
   * 新增网络组件
   *
   * @param network 网络组件
   * @return 影响行数
   */
  int insertNetwork(Network network);

  /**
   * 修改网络组件
   *
   * @param network 网络组件
   * @return 影响行数
   */
  int updateNetwork(Network network);

  /**
   * 删除网络组件
   *
   * @param id 网络组件ID
   * @return 影响行数
   */
  int deleteNetworkById(@Param("id") Integer id);

  /**
   * 批量删除网络组件
   *
   * @param ids 网络组件ID数组
   * @return 影响行数
   */
  int deleteNetworkByIds(@Param("ids") Integer[] ids);

  /**
   * 更新网络组件状态
   *
   * @param id 网络组件ID
   * @param state 状态
   * @return 影响行数
   */
  int updateNetworkState(@Param("id") Integer id, @Param("state") Boolean state);

  /**
   * 根据端口查询TCP网络组件数量（排除指定ID）
   *
   * @param port 端口
   * @param excludeId 排除的网络组件ID
   * @return 数量
   */
  int selectTcpNetworkByPortCount(
      @Param("port") Integer port, @Param("excludeId") Integer excludeId);

  /**
   * 根据唯一标识查询网络组件
   *
   * @param unionId 唯一标识
   * @return 网络组件列表
   */
  List<Network> selectByUnionId(@Param("unionId") String unionId);
}
