package org.springblade.modules.iot.qs.service;


import org.springblade.core.mp.service.BladeService;
import org.springblade.modules.iot.domain.QsGroup;
import org.springblade.modules.iot.domain.QsGroupTree;

import java.util.List;

/**
 * 分组管理服务接口
 *
 * @FileName IQsGroupService
 * @Description
 * @Author fengcheng
 * @date 2026-04-14
 **/
public interface IQsGroupService extends BladeService<QsGroup> {

    /**
     * 添加分组
     *
     * @param group 分组信息
     */
    void add(QsGroup group);

    /**
     * 更新分组
     *
     * @param group 分组信息
     */
    void update(QsGroup group);

    /**
     * 删除分组
     *
     * @param id 分组id
     */
    boolean delete(Long id);

    /**
     * 查询分组树
     *
     * @param query     查询条件
     * @param parentId  父节点
     * @param hasDevice 是否包含设备
     * @return
     */
    List<QsGroupTree> queryForTree(String query, Long parent, Boolean hasDevice);

    /**
     * 查询所有分组
     *
     * @param query 查询条件
     * @return
     */
    List<QsGroupTree> queryAllGroups(String query);

    /**
     * 查询分组节点设备
     *
     * @return
     */
    List<QsGroupTree> queryForDevice();

    /**
     * 查询分组
     *
     * @param query 要搜索的内容
     * @return
     */
    List<QsGroup> queryList(String query);
}
