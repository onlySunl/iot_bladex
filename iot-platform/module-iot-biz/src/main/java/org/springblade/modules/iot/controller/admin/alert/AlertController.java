package org.springblade.modules.iot.controller.admin.alert;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.common.entity.PageResult;
import org.springblade.modules.iot.common.utils.BeanUtils;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigRespVO;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigSaveReqVO;
import org.springblade.modules.iot.service.alert.IAlertConfigService;
import org.springblade.modules.iot.service.alert.IAlertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springblade.core.boot.ctrl.BladeController;


@Tag(name = "告警中心")
@Slf4j
@RestController
@RequestMapping("/alert")
public class AlertController extends BladeController {

    @Resource
    private IAlertService alertService;

    @Resource
    private IAlertConfigService alertConfigService;

    @Operation(summary ="新增告警中心配置")
    @PostMapping("/createAlertConfig")
    public R<Long> createAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO createReqVO) {
        return R.data(alertConfigService.createAlertConfig(createReqVO));
    }

    @Operation(summary ="编辑告警中心配置")
    @PostMapping("/updateAlertConfig")
    public R<Boolean> updateAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO updateReqVO) {
        alertConfigService.updateAlertConfig(updateReqVO);
        return R.data(true);
    }
    @Operation(summary ="删除告警中心配置")
    @PostMapping("/deleteAlertConfigById")
    public R<Boolean> deleteAlertConfig(@RequestBody IdReqVo reqVo) {
        alertConfigService.deleteAlertConfig(reqVo.getId());
        return R.data(true);
    }

    @Operation(summary ="查询告警中心配置分页")
    @PostMapping("/selectAlertConfigPage")
    public R<PageResult<AlertConfigRespVO>> getAlertConfigPage(@Valid @RequestBody AlertConfigPageReqVO pageReqVO) {
        PageResult<AlertConfig> pageResult = alertConfigService.getAlertConfigPage(pageReqVO);
        return R.data(BeanUtils.toBean(pageResult, AlertConfigRespVO.class));
    }



}
