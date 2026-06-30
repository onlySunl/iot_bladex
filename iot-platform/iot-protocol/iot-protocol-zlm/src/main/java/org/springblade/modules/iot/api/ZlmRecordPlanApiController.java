package org.springblade.modules.iot.api;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.service.IZlmRecordPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 录像计划api Controller
 *
 * @FileName ZlmRecordPlanApiController
 * @Description
 * @Author fengcheng
 * @date 2026-04-12
 **/
@Slf4j
@RestController
@RequestMapping("/api/recordPlan")
public class ZlmRecordPlanApiController {

    @Autowired
    private IZlmRecordPlanService zlmRecordPlanService;

    /**
     * 录像计划任务
     *
     */
    @GetMapping("/task")
    public R<Void> task() {
        zlmRecordPlanService.task();
        return R.success();
    }
}
