package org.springblade.modules.iot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsDeviceAlarm;
import org.springblade.modules.iot.service.IQsDeviceAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备告警Controller
 *
 * @author ruoyi
 * @date 2026-05-18
 */
@Slf4j
@RestController
@RequestMapping("/alarm")
public class QsDeviceAlarmController extends BladeController {
    @Autowired
    private IQsDeviceAlarmService qsDeviceAlarmService;

    /**
     * 查询设备告警列表
     */
    @GetMapping("/list")
    public IPage<List<QsDeviceAlarm>> list(QsDeviceAlarm qsDeviceAlarm) {
        List<QsDeviceAlarm> list = qsDeviceAlarmService.selectQsDeviceAlarmList(qsDeviceAlarm);
        return new Page<>();
    }

    /**
     * 导出设备告警列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, QsDeviceAlarm qsDeviceAlarm) {
        List<QsDeviceAlarm> list = qsDeviceAlarmService.selectQsDeviceAlarmList(qsDeviceAlarm);
        //ExcelUtil<QsDeviceAlarm> util = new ExcelUtil<QsDeviceAlarm>(QsDeviceAlarm.class);
        //util.exportExcel(response, list, "设备告警数据");
    }

    /**
     * 获取设备告警详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        return R.data(qsDeviceAlarmService.selectQsDeviceAlarmById(id));
    }

    /**
     * 新增设备告警
     */
    @PostMapping
    public R add(@RequestBody QsDeviceAlarm qsDeviceAlarm) {
        return R.data(qsDeviceAlarmService.insertQsDeviceAlarm(qsDeviceAlarm));
    }

    /**
     * 修改设备告警
     */
    @PutMapping
    public R edit(@RequestBody QsDeviceAlarm qsDeviceAlarm) {
        return R.data(qsDeviceAlarmService.updateQsDeviceAlarm(qsDeviceAlarm));
    }

    /**
     * 删除设备告警
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids) {
        return R.data(qsDeviceAlarmService.deleteQsDeviceAlarmByIds(ids));
    }

    /**
     * 批量处理设备告警
     */
    @PutMapping("/batchHandle")
    public R batchHandle(@RequestBody QsDeviceAlarm qsDeviceAlarm) {
        log.info("=== 批量处理告警请求 ===");
        log.info("请求参数: {}", qsDeviceAlarm);
        // 从请求中获取 ids 和 handler
        Long[] ids = qsDeviceAlarm.getIds();
        String handler = qsDeviceAlarm.getHandler();
        if (ids == null || ids.length == 0) {
            return R.fail("请选择要处理的告警");
        }
        if (handler == null || handler.isEmpty()) {
            try {
                handler = AuthUtil.getUserName();
            } catch (Exception e) {
                handler = "system";
            }
        }
        int result = qsDeviceAlarmService.batchHandleQsDeviceAlarm(ids, handler);
        log.info("批量处理结果: {}", result);
        return R.data(result);
    }
}
