

package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.common.entity.PageResult;

import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.AlertRecord;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigSaveReqVO;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertRecordPageReq;
import org.springblade.modules.iot.convert.AlertConfigConvert;
import org.springblade.modules.iot.convert.AlertRecordConvert;
import org.springblade.modules.iot.dal.mysql.AlertRecordMapper;
import org.springblade.modules.iot.dal.mysql.alertconfig.AlertConfigMapper;
import org.springblade.modules.iot.entity.AlertConfigDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 报警配置 Service 实现类
 *
 * @author EnjoyIot
 */
@Service
@Validated
public class AlertConfigServiceImpl implements AlertConfigService {

    @Resource
    private AlertConfigMapper alertConfigMapper;

    @Resource
    private AlertRecordMapper alertRecordMapper;

    @Override
    public Long createAlertConfig(AlertConfigSaveReqVO createReqVO) {
        // 插入
        AlertConfigDO alertConfig = BeanUtil.copy(createReqVO, AlertConfigDO.class);
        alertConfigMapper.insert(alertConfig);
        // 返回
        return alertConfig.getId();
    }

    @Override
    public void updateAlertConfig(AlertConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateAlertConfigExists(updateReqVO.getId());
        // 更新
        AlertConfigDO updateObj = BeanUtil.copy(updateReqVO, AlertConfigDO.class);
        alertConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlertConfig(Long id) {
        // 校验存在
        validateAlertConfigExists(id);
        // 删除
        alertConfigMapper.deleteById(id);
    }

    @Override
    public AlertConfig getAlertConfig(Long id) {
        return AlertConfigConvert.INSTANCE.convert(alertConfigMapper.selectById(id));
    }

    @Override
    public PageResult<AlertConfig> getAlertConfigPage(AlertConfigPageReqVO pageReqVO) {
        return AlertConfigConvert.INSTANCE.convertPage(alertConfigMapper.selectPage(pageReqVO));
    }

    public PageResult<AlertRecord> selectAlertRecordPage(AlertRecordPageReq request) {
        return AlertRecordConvert.INSTANCE.convertPage(alertRecordMapper.selectPage(request));
    }

    public void addAlertRecord(AlertConfig config, String content) {
        AlertRecord record = AlertRecord.builder()
                .level(config.getLevel())
                .name(config.getName())
                .readFlg(false)
                .alertTime(System.currentTimeMillis())
                .details(content).tenantId(config.getTenantId())
                .build();

        if (record.getId() == null) {
            alertRecordMapper.insert(AlertRecordConvert.INSTANCE.convertDO(record));
        } else {
            alertRecordMapper.updateById(AlertRecordConvert.INSTANCE.convertDO(record));
        }
    }

    private void validateAlertConfigExists(Long id) {
        if (alertConfigMapper.selectById(id) == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NOT_EXISTS);
        }
    }

}
