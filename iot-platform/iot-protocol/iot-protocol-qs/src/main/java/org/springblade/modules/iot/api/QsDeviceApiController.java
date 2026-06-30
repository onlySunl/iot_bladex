package org.springblade.modules.iot.api;


import jakarta.annotation.Resource;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.service.IQsDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 视频监控设备Controller
 *
 * @author fengcheng
 * @date 2026-03-27
 */
@RestController
@RequestMapping("/api/device")
public class QsDeviceApiController {
    @Resource
    private IQsDeviceService qsDeviceService;

    /**
     * 查询视频监控设备
     */
    @PostMapping("/allList")
    public R<List<QsDevice>> list(@RequestBody QsDevice qsDevice) {
        List<QsDevice> list = qsDeviceService.selectQsDeviceList(qsDevice);
        return R.data(list);
    }

    /**
     * 更新设备在线状态
     *
     * @param onlineDeviceSet 在线设备集合
     * @param deviceStatus    设备状态
     * @return
     */
    @PostMapping("/updateDeviceStatusList/{deviceStatus}")
    public R<Boolean> updateQsDeviceStatusList(@RequestBody Set<Long> onlineDeviceSet, @PathVariable String deviceStatus) {
        Boolean b = qsDeviceService.updateQsDeviceStatusList(onlineDeviceSet, deviceStatus);
        return R.data(b);
    }


    /**
     * 修改视频监控设备
     */

    @PutMapping("/updateQsDevice")
    public R<Boolean> updateQsDevice(@RequestBody QsDevice qsDevice) {
        return qsDeviceService.editQsDevice(qsDevice) > 0 ? R.data(true) : R.data(false);
    }

    /**
     * 更具流id获取视频监控设备
     */
    @GetMapping("/getQsDeviceStream/{stream}")
    public R<QsDevice> getQsDeviceStream(@PathVariable String stream) {
        return R.data(qsDeviceService.getQsDeviceStream(stream));
    }

    /**
     * 获取视频监控设备详细信息
     */
    @GetMapping("/getQsDeviceInfo/{id}")
    public R<QsDevice> getQsDeviceInfo(@PathVariable Long id) {
        return R.data(qsDeviceService.selectQsDeviceById(id));
    }

    /**
     * 设备状态任务
     */
    @GetMapping("/task")
    public R<Void> task() {
        qsDeviceService.task();
        return R.success();
    }

    /**
     * 清理设备计划id
     *
     * @param planId 设备id
     * @return
     */
    @GetMapping("/cleanRecordPlanId/{planId}")
    public R<Void> cleanRecordPlanId(@PathVariable Long planId) {
        qsDeviceService.cleanRecordPlanId(planId);
        return R.success();
    }

    /**
     * 根据设备id集合查询设备信息
     *
     * @param startDeviceIdList 设备id集合
     * @return
     */
    @GetMapping("/queryByIds/{startDeviceIdList}")
    public R<List<QsDevice>> queryByIds(@PathVariable List<Long> startDeviceIdList) {
        List<QsDevice> deviceList = qsDeviceService.queryByIds(startDeviceIdList);
        return R.data(deviceList);
    }

    /**
     * 根据计划id查询设备数量
     *
     * @param planId 设备id
     * @return
     */
    @GetMapping("/countRecordPlanDevice/{planId}")
    public R<Integer> countRecordPlanDevice(@PathVariable Long planId) {
        Integer i = qsDeviceService.countRecordPlanDevice(planId);
        return R.data(i);
    }

    /**
     * 新增视频监控设备
     */
    @PostMapping("/addQsDevice")
    public R<Boolean> addQsDevice(@RequestBody QsDevice qsDevice) {
        return qsDeviceService.addQsDevice(qsDevice) > 0 ? R.data(true) : R.data(false);
    }

    /**
     * 根据 gbDeviceId 更新设备在线状态
     *
     * @param gbDeviceId   国标设备编号
     * @param deviceStatus 设备状态
     * @return
     */
    @PostMapping("/updateDeviceStatusByGbDeviceId/{gbDeviceId}/{deviceStatus}")
    public R<Boolean> updateDeviceStatusByGbDeviceId(@PathVariable String gbDeviceId, @PathVariable String deviceStatus) {
        Boolean result = qsDeviceService.updateDeviceStatusByGbDeviceId(gbDeviceId, deviceStatus);
        return R.data(result);
    }

    /**
     * 根据 jtMobileNo 更新设备在线状态
     *
     * @param jtMobileNo   设备手机号
     * @param deviceStatus 设备状态
     * @return
     */
    @PostMapping("/updateDeviceStatusByJtMobileNo/{jtMobileNo}/{deviceStatus}")
    public R<Boolean> updateDeviceStatusByJtMobileNo(@PathVariable String jtMobileNo, @PathVariable String deviceStatus) {
        Boolean result = qsDeviceService.updateDeviceStatusByJtMobileNo(jtMobileNo, deviceStatus);
        return R.data(result);
    }

    /**
     * 根据 gbCode 查询设备
     *
     * @param gbCode 国标设备编号
     * @return
     */
    @GetMapping("/getDeviceByGbCode/{gbCode}")
    public R<QsDevice> getDeviceByGbCode(@PathVariable String gbCode) {
        QsDevice qsDevice = qsDeviceService.getDeviceByGbCode(gbCode);
        if (qsDevice == null){
            return R.fail("设备不存在");
        }
        return R.data(qsDevice);
    }

    /**
     * 根据 deviceCode 查询设备
     *
     * @param deviceCode 设备编号
     * @return
     */
    @GetMapping("/getDeviceByDeviceCode/{deviceCode}")
    public R<QsDevice> getDeviceByDeviceCode(@PathVariable String deviceCode) {
        QsDevice qsDevice = qsDeviceService.getDeviceByDeviceCode(deviceCode);
        if (qsDevice == null) {
            return R.fail("设备不存在");
        }
        return R.data(qsDevice);
    }

    /**
     * 根据 id 更新订阅状态
     *
     * @param id                   设备主键ID
     * @param subscribeCatalogStatus 目录订阅状态
     * @param subscribeAlarmStatus   报警订阅状态
     * @param subscribeTime          订阅时间
     * @return
     */
    @PostMapping("/updateSubscribeStatus/{id}")
    public R<Boolean> updateSubscribeStatus(
            @PathVariable Long id,
            @RequestParam(required = false) Integer subscribeCatalogStatus,
            @RequestParam(required = false) Integer subscribeAlarmStatus,
            @RequestParam(required = false) String subscribeTime
    ) {
        Boolean result = qsDeviceService.updateSubscribeStatusById(
                id,
                subscribeCatalogStatus,
                subscribeAlarmStatus,
                subscribeTime
        );
        return R.data(result);
    }
}
