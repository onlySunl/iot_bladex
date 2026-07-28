package org.springblade.modules.iot.ota.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.basic.base.manager.SuperManager;
import org.springblade.basic.base.request.PageParams;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTasks;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTasksPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * OTA升级任务表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:40:04
 * @create [2024-01-12 22:40:04] [mqttsnet]
 */
public interface OtaUpgradeTasksManager extends SuperManager<OtaUpgradeTasks> {

    /**
     * Queries the paginated details of OTA upgrade tasks.
     *
     * @param params Pagination parameters
     * @return {@link IPage <OtaUpgradeTasks>} Paginated details of OTA upgrade tasks
     */
    IPage<OtaUpgradeTasks> getOtaUpgradeTasksPage(PageParams<OtaUpgradeTasksPageQuery> params);

    /**
     * Queries the list of OTA upgrade tasks.
     *
     * @param query Query parameters
     * @return {@link List <OtaUpgradeTasks>} List of OTA upgrade tasks
     */
    List<OtaUpgradeTasks> getOtaUpgradeTasksList(OtaUpgradeTasksPageQuery query);
}


