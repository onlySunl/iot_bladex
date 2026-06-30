package org.springblade.modules.iot.controller;

import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.ZlmRecordPlan;
import org.springblade.modules.iot.service.IZlmRecordPlanService;
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
public class ZlmRecordPlanController extends BladeController {
    @Autowired
    private IZlmRecordPlanService zlmRecordPlanService;

    /**
     * 查询录像计划列表
     */
    @GetMapping("/list")
    public R list(ZlmRecordPlan zlmRecordPlan) {
        List<ZlmRecordPlan> list = zlmRecordPlanService.selectZlmRecordPlanList(zlmRecordPlan);
        return R.data(list);
    }

    /**
     * 获取录像计划详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        return R.data(zlmRecordPlanService.selectZlmRecordPlanById(id));
    }

    /**
     * 新增录像计划
     */
    @PostMapping
    public R add(@RequestBody ZlmRecordPlan zlmRecordPlan) {
        if (zlmRecordPlan.getPlanItemList() == null || zlmRecordPlan.getPlanItemList().isEmpty()) {
            throw new RuntimeException("添加录制计划时，录制计划不可为空");
        }
        return R.data(zlmRecordPlanService.insertZlmRecordPlan(zlmRecordPlan));
    }

    /**
     * 修改录像计划
     */
    @PutMapping
    public R edit(@RequestBody ZlmRecordPlan zlmRecordPlan) {
        return R.data(zlmRecordPlanService.updateZlmRecordPlan(zlmRecordPlan));
    }

    /**
     * 删除录像计划
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids) {
        return R.data(zlmRecordPlanService.deleteZlmRecordPlanByIds(ids));
    }

    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    public R changeStatus(@RequestBody ZlmRecordPlan zlmRecordPlan) {
        return R.data(zlmRecordPlanService.updateZlmRecordPlanStatus(zlmRecordPlan));
    }
}
