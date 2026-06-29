package org.springblade.modules.iot.qs.api;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsDeviceSnapshot;
import org.springblade.modules.iot.qs.service.IQsDeviceSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备抓图Api接口
 *
 * @author ruoyi
 * @date 2026-05-17
 */
@RestController
@RequestMapping("/api/snapshot")
public class QsDeviceSnapshotApiController {
    @Autowired
    private IQsDeviceSnapshotService qsDeviceSnapshotService;

    /**
     * 查询设备抓图列表
     */
    @PostMapping("/list")
    public R<List<QsDeviceSnapshot>> list(@RequestBody QsDeviceSnapshot qsDeviceSnapshot) {
        List<QsDeviceSnapshot> list = qsDeviceSnapshotService.selectQsDeviceSnapshotList(qsDeviceSnapshot);
        return R.data(list);
    }

    /**
     * 获取设备抓图详细信息
     */
    @GetMapping("/getInfo/{id}")
    public R<QsDeviceSnapshot> getInfo(@PathVariable("id") Long id) {
        return R.data(qsDeviceSnapshotService.selectQsDeviceSnapshotById(id));
    }

    /**
     * 新增设备抓图(内部调用)
     */
    @PostMapping("/add")
    public R<Long> add(@RequestBody QsDeviceSnapshot qsDeviceSnapshot) {
        int result = qsDeviceSnapshotService.insertQsDeviceSnapshot(qsDeviceSnapshot);
        return result > 0 ? R.data(qsDeviceSnapshot.getId()) : R.fail("保存失败");
    }

    /**
     * 修改设备抓图
     */
    @PutMapping("/edit")
    public R<Boolean> edit(@RequestBody QsDeviceSnapshot qsDeviceSnapshot) {
        return qsDeviceSnapshotService.updateQsDeviceSnapshot(qsDeviceSnapshot) > 0 ? R.data(true) : R.fail("更新失败");
    }

    /**
     * 删除设备抓图
     */
    @DeleteMapping("/remove/{ids}")
    public R<Boolean> remove(@PathVariable Long[] ids) {
        return qsDeviceSnapshotService.deleteQsDeviceSnapshotByIds(ids) > 0 ? R.data(true) : R.fail("删除失败");
    }
}