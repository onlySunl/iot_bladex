

package org.springblade.modules.iot.api.alert;

import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.Message;
import org.springblade.modules.iot.common.entity.PageResult;

public interface AlertApi {

    PageResult<AlertConfig> getAlertConfigPage(AlertConfigPageReqVO reqVO);

    Message getNotifyMessage(AlertConfig alertConfig);

}
