package org.springblade.modules.iot.ota.controller;

import java.util.List;

import org.springblade.core.tool.api.R;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Query;
import org.springblade.modules.iot.datascope.DataScopeHelper;
import org.springblade.modules.iot.ota.entity.OtaUpgrades;
import org.springblade.modules.iot.ota.service.OtaUpgradesService;
import org.springblade.modules.iot.ota.vo.query.OtaUpgradesPageQuery;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesDetailsResultVO;
import org.springblade.modules.iot.ota.vo.result.OtaUpgradesResultVO;
import org.springblade.modules.iot.ota.vo.save.OtaUpgradesSaveVO;
import org.springblade.modules.iot.ota.vo.update.OtaUpgradesUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * OTA升级包
 * </p>
 *
 * @author mqttsnet
 * @date 2024-01-12 22:36:27
 * @create [2024-01-12 22:36:27] [mqttsnet]
 */
@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/otaUpgrades")
@Tag(name = "OTA升级包")
public class OtaUpgradesController extends BladeController<OtaUpgradesService, Long, OtaUpgrades, OtaUpgradesSaveVO,
        OtaUpgradesUpdateVO, OtaUpgradesPageQuery, OtaUpgradesResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<OtaUpgrades> handlerWrapper(OtaUpgrades model, Query params) {
        QueryWrap<OtaUpgrades> queryWrap = super.handlerWrapper(model, params);
        // 开启数据权限
        DataScopeHelper.startDataScope("ota_upgrades");
        return queryWrap;
    }

    @Operation(summary = "保存OTA升级包", description = "保存一个新的OTA升级包")
    @PostMapping("/saveUpgradePackage")
    public R<OtaUpgradesSaveVO> saveUpgradePackage(@Valid @RequestBody OtaUpgradesSaveVO saveVO) {
        OtaUpgradesSaveVO createdSaveVO = superService.saveUpgradePackage(saveVO);
        return R.success(createdSaveVO);
    }

    @Operation(summary = "更新OTA升级包", description = "更新一个现有的OTA升级包")
    @PutMapping("/updateUpgradePackage")
    public R<OtaUpgradesUpdateVO> updateUpgradePackage(@Valid @RequestBody OtaUpgradesUpdateVO updateVO) {
        OtaUpgradesUpdateVO updatedSaveVO = superService.updateUpgradePackage(updateVO);
        return R.success(updatedSaveVO);
    }

    @Operation(summary = "更新OTA升级包状态", description = "更新OTA升级包的状态")
    @Parameters({
            @Parameter(name = "id", description = "OTA升级包ID", required = true),
            @Parameter(name = "status", description = "OTA升级包状态", required = true)
    })
    @PutMapping("/updateOtaUpgradeStatus/{id}")
    public R<Boolean> updateOtaUpgradeStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        log.info("更新OTA升级状态 id:{}, 状态:{}", id, status);
        return R.success(superService.updateOtaUpgradeStatus(id, status));
    }

    @Operation(summary = "删除OTA升级包", description = "通过其ID删除一个OTA升级包")
    @Parameters({
            @Parameter(name = "id", description = "OTA升级包ID", required = true)
    })
    @DeleteMapping("/deleteOtaUpgrade/{id}")
    public R<Boolean> deleteOtaUpgrade(@PathVariable("id") Long id) {
        log.info("删除OTA升级包 id: {}", id);
        return R.success(superService.deleteOtaUpgrade(id));
    }

    @Operation(summary = "获取OTA升级包详情", description = "通过ID检索OTA升级包的详细信息")
    @Parameters({@Parameter(name = "id", description = "OTA升级包ID", required = true)})
    @GetMapping("/details/{id}")
    public R<OtaUpgradesDetailsResultVO> getUpgradePackageDetails(@PathVariable Long id) {
        OtaUpgradesDetailsResultVO upgradePackageDetails = superService.getUpgradePackageDetails(id);
        echoService.action(upgradePackageDetails);
        return R.success(upgradePackageDetails);
    }

    @Operation(summary = "批量删除OTA升级包", description = "通过它们的ID批量删除OTA升级包")
    @DeleteMapping("/deleteOtaUpgrades")
    public R<Boolean> deleteOtaUpgrades(@RequestBody List<Long> ids) {
        log.info("批量删除OTA升级包 ids: {}", ids);
        boolean allDeleted = ids.stream().distinct().allMatch(id -> superService.deleteOtaUpgrade(id));
        return R.success(allDeleted);
    }

}