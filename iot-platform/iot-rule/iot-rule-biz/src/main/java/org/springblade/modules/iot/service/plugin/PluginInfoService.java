package org.springblade.modules.iot.service.plugin;

import org.springblade.basic.base.service.SuperService;
import org.springblade.modules.iot.entity.plugin.PluginInfo;
import org.springblade.modules.iot.enumeration.plugin.PluginActionStatusEnum;
import org.springblade.modules.iot.vo.query.plugin.PluginInfoPageQuery;
import org.springblade.modules.iot.vo.result.plugin.PluginInfoDetailsResultVO;
import org.springblade.modules.iot.vo.result.plugin.PluginInfoResultVO;
import org.springblade.modules.iot.vo.result.plugin.PluginResultVO;
import org.springblade.modules.iot.vo.save.plugin.PluginInfoSaveVO;
import org.springblade.modules.iot.vo.update.plugin.PluginInfoUpdateVO;

import java.io.IOException;
import java.util.List;


/**
 * <p>
 * 业务接口
 * 插件信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-08-25 19:05:11
 * @create [2024-08-25 19:05:11] [mqttsnet]
 */
public interface PluginInfoService extends SuperService<Long, PluginInfo> {

    /**
     * 扫描插件并保存扫描结果
     *
     * @param pluginIdentification 插件标识
     * @return {@link PluginInfoResultVO} 插件信息
     */
    PluginInfoResultVO scanAndSavePluginResult(String pluginIdentification) throws IOException;

    /**
     * 安装插件
     *
     * @param id         插件ID
     * @param instanceId 实例ID
     * @return {@link Boolean} true: 安装成功、false：安装失败
     */
    Boolean installPlugin(Long id, Long instanceId);

    /**
     * 卸载插件
     *
     * @param id         插件ID
     * @param instanceId 实例ID
     * @return {@link Boolean} true: 卸载成功、false：卸载失败
     */
    Boolean unInstallPlugin(Long id, Long instanceId);


    /**
     * 预加载插件
     *
     * @param id 插件ID
     * @return {@link PluginResultVO} 插件信息
     */
    PluginResultVO preloadPlugin(Long id);

    /**
     * 保存插件信息
     *
     * @param saveVO 保存参数
     * @return {@link PluginInfoSaveVO} 新增实体
     */
    PluginInfoSaveVO savePlugin(PluginInfoSaveVO saveVO);

    /**
     * 更新插件信息
     *
     * @param updateVO 更新参数
     * @return {@link PluginInfoUpdateVO} 更新结果
     */
    PluginInfoUpdateVO updatePlugin(PluginInfoUpdateVO updateVO);


    /**
     * 根据插件ID查询插件详情
     *
     * @param id 插件ID
     * @return {@link PluginInfoDetailsResultVO} 插件详情
     */
    PluginInfoDetailsResultVO getPluginInfoDetails(Long id);


    /**
     * 插件列表
     *
     * @param query 查询条件
     * @return {@link List<PluginInfoResultVO>} 插件列表结果
     */
    List<PluginInfoResultVO> getPluginInfoResultVOList(PluginInfoPageQuery query);


    /**
     * 安装或卸载插件
     *
     * @param pluginId   插件ID
     * @param instanceId 实例ID
     * @param status     插件操作状态
     */
    void installOrUninstallPlugin(Long pluginId, Long instanceId, PluginActionStatusEnum status);


    /**
     * 删除插件
     *
     * @param id
     * @return
     */
    Boolean deletePluginInfo(Long id);

    /**
     * 卸载所有插件实例
     *
     * @param id 插件ID
     */
    void uninstallAllPluginsForInstances(Long id);
}


