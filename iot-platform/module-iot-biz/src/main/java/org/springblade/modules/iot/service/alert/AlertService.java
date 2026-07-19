package org.springblade.modules.iot.service.alert;

import org.springblade.modules.iot.api.alert.dto.AlertConfig;

import java.util.List;

/**
 * 报警 Service 接口
 */
public interface AlertService {

    /**
     * 触发报警
     *
     * @param config  报警配置
     * @param content 报警内容
     */
    void triggerAlert(AlertConfig config, String content);

    /**
     * 获取设备的报警配置
     *
     * @param productId 产品ID
     * @return 报警配置列表
     */
    List<AlertConfig> getDeviceAlertConfigs(Long productId);
}
