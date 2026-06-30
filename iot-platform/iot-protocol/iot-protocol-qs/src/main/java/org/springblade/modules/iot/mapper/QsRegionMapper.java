package org.springblade.modules.iot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.domain.QsRegion;
import org.springblade.modules.iot.domain.QsRegionTree;

import java.util.List;

/**
 * @FileName QsRegionMapper
 * @Description
 * @Author fengcheng
 * @date 2026-04-13
 **/
@Mapper
public interface QsRegionMapper extends BladeMapper<QsRegion> {

    /**
     * 添加区域
     *
     * @param region 区域信息
     */
    void add(QsRegion region);

    /**
     * 查询区域
     *
     * @param id 区域id
     * @return
     */
    QsRegion queryOne(Long id);

    /**
     * 根据设备id查询区域
     *
     * @param deviceId 设备id
     * @return
     */
    QsRegion queryByDeviceId(String deviceId);

    /**
     * 更新父节点区域
     *
     * @param parentId       父节点id
     * @param parentDeviceId 父节点设备id
     */
    void updateChild(@Param("parentId") Long parentId, @Param("parentDeviceId") String parentDeviceId);

    /**
     * 更新区域
     *
     * @param region
     */
    void update(QsRegion region);

    /**
     * 查询子节点
     *
     * @param parentId 父节点id
     * @return
     */
    List<QsRegion> getChildren(@Param("parentId") Long parentId);

    /**
     * 删除所有子节点
     *
     * @param allChildren 所有子节点
     */
    void batchDelete(@Param("allChildren") List<QsRegion> allChildren);

    /**
     * 查询树形结构
     *
     * @param parent 所属行政区划编号
     * @return
     */
    List<QsRegionTree> queryForTree(Long parent);

    /**
     * 查询区域节点设备
     *
     * @return
     */
    List<QsRegionTree> queryForDevice();

    /**
     * 查询区域
     *
     * @param query    要搜索的内容
     * @param parentId 父节点id
     * @return
     */
    List<QsRegion> query(@Param("query") String query, @Param("parentId") String parentId);

    /**
     * 查询所有区域
     *
     * @param query 查询条件
     * @return
     */
    List<QsRegionTree> queryAllRegions(@Param("query") String query);
}
