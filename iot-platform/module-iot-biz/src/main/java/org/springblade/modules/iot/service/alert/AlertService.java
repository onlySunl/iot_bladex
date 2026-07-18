

package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.api.alert.dto.AlertRecord;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertRecordPageReq;

public interface AlertService {

    PageResult<AlertRecord> selectAlertRecordPage(AlertRecordPageReq request);

}
