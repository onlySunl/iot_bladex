
package org.springblade.modules.iot.controller.admin.alert;


import org.springblade.modules.iot.framework.common.pojo.CommonResult;
import org.springblade.modules.iot.framework.common.pojo.PageResult;
import org.springblade.modules.iot.framework.common.util.object.BeanUtils;
import org.springblade.modules.iot.api.IdReqVo;
import org.springblade.modules.iot.api.alert.dto.AlertConfig;
import org.springblade.modules.iot.api.alert.dto.AlertConfigPageReqVO;
import org.springblade.modules.iot.api.alert.dto.AlertRecord;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigRespVO;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertConfigSaveReqVO;
import org.springblade.modules.iot.controller.admin.alertconfig.vo.AlertRecordPageReq;
import org.springblade.modules.iot.service.alert.AlertConfigService;
import org.springblade.modules.iot.service.alert.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import static org.springblade.modules.iot.framework.common.pojo.CommonResult.success;


@Tag(name = "告警中心")
@Slf4j
@RestController
@RequestMapping("/alert")
public class AlertController {

    @Resource
    private AlertService alertService;

    @Resource
    private AlertConfigService alertConfigService;

    @Operation(summary ="新增告警中心配置")
    @PreAuthorize("@ss.hasPermission('iot:alertConfig:add')")
    @PostMapping("/createAlertConfig")
    public CommonResult<Long> createAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO createReqVO) {
        return success(alertConfigService.createAlertConfig(createReqVO));
    }

    @Operation(summary ="编辑告警中心配置")
    @PreAuthorize("@ss.hasPermission('iot:alertConfig:edit')")
    @PostMapping("/updateAlertConfig")
    public CommonResult<Boolean> updateAlertConfig(@Valid @RequestBody AlertConfigSaveReqVO updateReqVO) {
        alertConfigService.updateAlertConfig(updateReqVO);
        return success(true);
    }
    @Operation(summary ="删除告警中心配置")
    @PreAuthorize("@ss.hasPermission('iot:alertConfig:remove')")
    @PostMapping("/deleteAlertConfigById")
    public CommonResult<Boolean> deleteAlertConfig(@RequestBody IdReqVo reqVo) {
        alertConfigService.deleteAlertConfig(reqVo.getId());
        return success(true);
    }

    @Operation(summary ="查询告警中心配置分页")
    @PreAuthorize("@ss.hasPermission('iot:alertConfig:query')")
    @PostMapping("/selectAlertConfigPage")
    public CommonResult<PageResult<AlertConfigRespVO>> getAlertConfigPage(@Valid @RequestBody AlertConfigPageReqVO pageReqVO) {
        PageResult<AlertConfig> pageResult = alertConfigService.getAlertConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlertConfigRespVO.class));
    }


    @Operation(summary ="查询告警消息分页")
    @PreAuthorize("@ss.hasPermission('iot:alert:query')")
    @PostMapping("/selectAlertRecordPage")
    public CommonResult<PageResult<AlertRecord>> selectAlertRecordPage(@RequestBody @Validated AlertRecordPageReq request) {
        return success(alertService.selectAlertRecordPage(request));
    }


}
