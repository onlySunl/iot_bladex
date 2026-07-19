package org.springblade.modules.iot.service.alert;

import jakarta.validation.Valid;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigSaveReqVO;

/**
 * 报警配置 Service 接口
 */
public interface AlertConfigService {

    /**
     * 创建报警配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlertConfig(@Valid AlertConfigSaveReqVO createReqVO);

    /**
     * 更新报警配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAlertConfig(@Valid AlertConfigSaveReqVO updateReqVO);

    /**
     * 删除报警配置
     *
     * @param id 编号
     */
    void deleteAlertConfig(Long id);

    /**
     * 获得报警配置
     *
     * @param id 编号
     * @return 报警配置
     */
    AlertConfig getAlertConfig(Long id);

    /**
     * 获得报警配置分页
     *
     * @param pageReqVO 分页查询
     * @return 报警配置分页
     */
    PageResult<AlertConfig> getAlertConfigPage(AlertConfigPageReqVO pageReqVO);

    void addAlertRecord(AlertConfig config, String content);
}
