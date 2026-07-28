package org.springblade.modules.iot.ota.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.springblade.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.ota.dto.OtaUpgradeRecordsSummaryResultDTO;
import org.springblade.modules.iot.ota.entity.OtaUpgradeRecords;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * OTA升级记录表
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:42:04
 * @create [2024-01-12 22:42:04] [mqttsnet]
 */
@Repository
public interface OtaUpgradeRecordsMapper extends SuperMapper<OtaUpgradeRecords> {

    /**
     * 查询OTA升级记录统计信息
     *
     * @param wrapper 查询条件
     * @return OTA升级记录统计结果
     */
    OtaUpgradeRecordsSummaryResultDTO selectOtaUpgradeRecordsSummary(@Param(Constants.WRAPPER) Wrapper<OtaUpgradeRecords> wrapper);


}


