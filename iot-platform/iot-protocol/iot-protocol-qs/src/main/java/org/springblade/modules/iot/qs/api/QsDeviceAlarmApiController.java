package org.springblade.modules.iot.qs.api;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsDeviceAlarm;
import org.springblade.modules.iot.qs.service.IQsDeviceAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备告警Api接口
 *
 * @author ruoyi
 * @date 2026-05-18
 */
@RestController
@RequestMapping("/api/alarm")
public class QsDeviceAlarmApiController {
    @Autowired
    private IQsDeviceAlarmService qsDeviceAlarmService;

    /**
     * 查询设备告警列表
     */
    @PostMapping("/list")
    public R<List<QsDeviceAlarm>> list(@RequestBody QsDeviceAlarm qsDeviceAlarm) {
        List<QsDeviceAlarm> list = qsDeviceAlarmService.selectQsDeviceAlarmList(qsDeviceAlarm);
        return R.data(list);
    }

    /**
     * 获取设备告警详细信息
     */
    @GetMapping("/getInfo/{id}")
    public R<QsDeviceAlarm> getInfo(@PathVariable("id") Long id) {
        return R.data(qsDeviceAlarmService.selectQsDeviceAlarmById(id));
    }

    /**
     * 新增设备告警(内部调用)
     */
    @PostMapping("/add")
    public R<Long> add(@RequestBody QsDeviceAlarm qsDeviceAlarm) {
        int result = qsDeviceAlarmService.insertQsDeviceAlarm(qsDeviceAlarm);
        return result > 0 ? R.data(qsDeviceAlarm.getId()) : R.fail("保存失败");
    }

    /**
     * 修改设备告警
     */
    @PutMapping("/edit")
    public R<Boolean> edit(@RequestBody QsDeviceAlarm qsDeviceAlarm) {
        return qsDeviceAlarmService.updateQsDeviceAlarm(qsDeviceAlarm) > 0 ? R.data(true) : R.fail("更新失败");
    }

    /**
     * 删除设备告警
     */
    @DeleteMapping("/remove/{ids}")
    public R<Boolean> remove(@PathVariable Long[] ids) {
        return qsDeviceAlarmService.deleteQsDeviceAlarmByIds(ids) > 0 ? R.data(true) : R.fail("删除失败");
    }
}
