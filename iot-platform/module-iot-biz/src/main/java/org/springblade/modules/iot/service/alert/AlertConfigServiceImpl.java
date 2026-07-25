package org.springblade.modules.iot.service.alert;

import jakarta.annotation.Resource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.AlertRecord;
import org.springblade.modules.iot.api.enums.ErrorCodeConstants;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.common.utils.ServiceExceptionUtil;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigSaveReqVO;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertRecordPageReq;
import org.springblade.modules.iot.convert.AlertConfigConvert;
import org.springblade.modules.iot.convert.AlertRecordConvert;
import org.springblade.modules.iot.entity.AlertConfigDO;
import org.springblade.modules.iot.entity.AlertRecordDO;
import org.springblade.modules.iot.dal.mysql.alertconfig.AlertRecordMapper;
import org.springblade.modules.iot.dal.mysql.alertconfig.AlertConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.entity.AlertConfigDO;
import org.springblade.modules.iot.entity.AlertRecordDO;
import org.springblade.modules.iot.dal.mysql.alertconfig.AlertConfigMapper;

/**
 * 报警配置 Service 实现类
 */
@Service
@Validated
public class AlertConfigServiceImpl extends BaseServiceImpl<AlertConfigMapper, AlertConfigDO> implements IAlertConfigService {

    @Resource
    private AlertConfigMapper alertConfigMapper;

    @Resource
    private AlertRecordMapper alertRecordMapper;

    @Override
    public Long createAlertConfig(AlertConfigSaveReqVO createReqVO) {
        // 插入
        AlertConfigDO alertConfig = BeanUtils.toBean(createReqVO, AlertConfigDO.class);
        alertConfigMapper.insert(alertConfig);
        // 返回
        return alertConfig.getId();
    }

    @Override
    public void updateAlertConfig(AlertConfigSaveReqVO updateReqVO) {
        // 校验存在
        validateAlertConfigExists(updateReqVO.getId());
        // 更新
        AlertConfigDO updateObj = BeanUtils.toBean(updateReqVO, AlertConfigDO.class);
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
        return AlertConfigConvert.INSTANCE.convertPage(PageResult.from(alertConfigMapper.selectPage(new Page<AlertConfigDO>(pageReqVO.getPageNo(), pageReqVO.getPageSize()), pageReqVO)));
    }

    public PageResult<AlertRecord> selectAlertRecordPage(AlertRecordPageReq request) {
        return AlertRecordConvert.INSTANCE.convertPage(PageResult.from(alertRecordMapper.selectPage(new Page<AlertRecordDO>(request.getPageNo(), request.getPageSize()), request)));
    }

    @Override
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
