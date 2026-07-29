package org.springblade.modules.iot.ota.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.basic.base.manager.SuperManager;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.modules.iot.ota.entity.OtaUpgrades;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradesPageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * OTA升级包
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:36:27
 * @create [2024-01-12 22:36:27] [mqttsnet]
 */
public interface OtaUpgradesManager extends SuperManager<OtaUpgrades> {

    /**
     * Queries the paginated details of OTA upgrade packages.
     *
     * @param params Pagination parameters
     * @return {@link IPage <OtaUpgrades>} Paginated details of OTA upgrade packages
     */
    IPage<OtaUpgrades> getOtaUpgradesPage(PageParams<OtaUpgradesPageQuery> params);


    /**
     * Retrieves a list of OTA (Over-The-Air) upgrade records based on the specified query criteria.
     * This method supports filtering by a single ID or multiple IDs.
     *
     * @param query The {@link OtaUpgradesPageQuery} object containing the search criteria,
     *              which may include a single ID or multiple IDs for OTA upgrades.
     * @return A {@link List} of {@link OtaUpgrades} that match the given query criteria. Returns an empty list
     *         if no records match the criteria.
     */
    List<OtaUpgrades> getOtaUpgradesList(OtaUpgradesPageQuery query);
}


