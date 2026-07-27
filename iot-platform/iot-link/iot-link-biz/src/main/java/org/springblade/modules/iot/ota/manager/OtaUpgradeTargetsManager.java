package org.springblade.modules.iot.ota.manager;

import java.util.List;

import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.iot.ota.entity.OtaUpgradeTargets;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradeTargetsPageQuery;

/**
 * <p>
 * 通用业务接口
 * OTA升级目标表
 * </p>
 *
 * @author mqttsnet
 * @date 2025-10-19 16:28:50
 * @create [2025-10-19 16:28:50] [mqttsnet] [代码生成器生成]
 */
public interface OtaUpgradeTargetsManager extends BaseService<OtaUpgradeTargets> {

    /**
     * 根据查询条件分页查询OTA升级目标列表
     *
     * @param query 查询条件
     * @return {@link List<OtaUpgradeTargets>} OTA升级目标列表
     */
    List<OtaUpgradeTargets> getOtaUpgradeTargetsList(OtaUpgradeTargetsPageQuery query);
}


