

package org.springblade.modules.iot.service.alert;

import jakarta.annotation.Resource;
import org.springblade.modules.iot.api.alert.dto.AlertRecord;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertRecordPageReq;
import org.springblade.modules.iot.convert.AlertRecordConvert;
import org.springblade.modules.iot.dal.mysql.AlertRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {
    @Resource
    private AlertRecordMapper alertRecordMapper;

    @Override
    public PageResult<AlertRecord> selectAlertRecordPage(AlertRecordPageReq request) {
        return AlertRecordConvert.INSTANCE.convertPage(alertRecordMapper.selectPage(request));
    }
}
