package org.springblade.modules.iot.zlm.controller;

import org.springblade.modules.iot.common.core.web.controller.BaseController;
import org.springblade.modules.iot.common.core.web.domain.AjaxResult;
import org.springblade.modules.iot.common.core.web.page.TableDataInfo;
import org.springblade.modules.iot.common.log.annotation.Log;
import org.springblade.modules.iot.common.log.enums.BusinessType;
import org.springblade.modules.iot.common.security.annotation.RequiresPermissions;
import org.springblade.modules.iot.common.security.utils.SecurityUtils;
import org.springblade.modules.iot.zlm.domain.ZlmRecordPlan;
import org.springblade.modules.iot.zlm.service.IZlmRecordPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 录像计划Controller
 *
 * @author fengcheng
 * @date 2026-04-11
 */
@RestController
@RequestMapping("/recordPlan")
public class ZlmRecordPlanController extends BaseController {
    @Autowired
    private IZlmRecordPlanService zlmRecordPlanService;

    /**
     * 查询录像计划列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ZlmRecordPlan zlmRecordPlan) {
        startPage();
        List<ZlmRecordPlan> list = zlmRecordPlanService.selectZlmRecordPlanList(zlmRecordPlan);
        return getDataTable(list);
    }

    /**
     * 获取录像计划详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(zlmRecordPlanService.selectZlmRecordPlanById(id));
    }

    /**
     * 新增录像计划
     */
    @Log(title = "录像计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZlmRecordPlan zlmRecordPlan) {
        if (zlmRecordPlan.getPlanItemList() == null || zlmRecordPlan.getPlanItemList().isEmpty()) {
            throw new RuntimeException("添加录制计划时，录制计划不可为空");
        }
        return toAjax(zlmRecordPlanService.insertZlmRecordPlan(zlmRecordPlan));
    }

    /**
     * 修改录像计划
     */
    @Log(title = "录像计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZlmRecordPlan zlmRecordPlan) {
        return toAjax(zlmRecordPlanService.updateZlmRecordPlan(zlmRecordPlan));
    }

    /**
     * 删除录像计划
     */
    @Log(title = "录像计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(zlmRecordPlanService.deleteZlmRecordPlanByIds(ids));
    }

    /**
     * 状态修改
     */
    @Log(title = "录像计划", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody ZlmRecordPlan zlmRecordPlan) {
        zlmRecordPlan.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(zlmRecordPlanService.updateZlmRecordPlanStatus(zlmRecordPlan));
    }
}
