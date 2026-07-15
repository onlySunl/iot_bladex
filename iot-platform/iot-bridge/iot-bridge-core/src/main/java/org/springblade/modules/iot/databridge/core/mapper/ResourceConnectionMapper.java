

package org.springblade.modules.iot.databridge.core.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.iot.pojo.bridge.entity.ResourceConnection;

import java.util.List;

/**
 * 资源连接Mapper接口
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/1/15
 */
@Mapper
public interface ResourceConnectionMapper extends BladeMapper<ResourceConnection> {

    /**
     * 根据资源类型获取活跃连接
     *
     * @param type 资源类型
     * @return 活跃连接列表
     */
    List<ResourceConnection> selectActiveConnectionsByType(@Param("type") String type);

    /**
     * 根据主机和端口查询连接
     *
     * @param host 主机地址
     * @param port 端口号
     * @param excludeId 排除的ID（用于更新时检查）
     * @return 连接
     */
    ResourceConnection selectByHostAndPort(@Param("host") String host, @Param("port") Integer port, @Param("excludeId") Long excludeId);

    /**
     * 根据名称查询连接（用于重名检查）
     *
     * @param name 连接名称
     * @param excludeId 排除的ID（用于更新时检查）
     * @return 连接
     */
    ResourceConnection selectByName(@Param("name") String name, @Param("excludeId") Long excludeId);

    /**
     * 批量更新连接状态
     *
     * @param ids 连接ID列表
     * @param status 状态
     * @param updateBy 更新者
     * @return 更新数量
     */
    int batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") Integer status, @Param("updateBy") String updateBy);

    /**
     * 测试连接可用性（通过查询系统表）
     *
     * @param id 连接ID
     * @return 是否可用
     */
    int testConnection(@Param("id") Long id);
}
