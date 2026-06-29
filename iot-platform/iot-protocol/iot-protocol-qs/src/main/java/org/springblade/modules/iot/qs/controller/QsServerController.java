package org.springblade.modules.iot.qs.controller;


import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.qs.common.SystemAllInfo;
import org.springblade.modules.iot.qs.domain.DeviceStats;
import org.springblade.modules.iot.qs.service.IQsDeviceService;
import org.springblade.modules.iot.qs.service.IRedisCatchStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务控制
 *
 * @FileName QsServerController
 * @Description
 * @Author fengcheng
 * @date 2026-04-15
 **/
@Slf4j
@RestController
@RequestMapping("/server")
public class QsServerController extends BladeController {

    @Autowired
    private IRedisCatchStorageService redisCatchStorageService;

    @Autowired
    private IQsDeviceService qsDeviceService;

    /**
     * 获取系统信息
     *
     * @return
     */
    @GetMapping(value = "/system/info")
    public R getSystemInfo() {
        SystemAllInfo systemAllInfo = redisCatchStorageService.getSystemInfo();
        return R.data(systemAllInfo);
    }

    /**
     * 获取设备统计信息
     *
     * @return
     */
    @GetMapping(value = "/system/deviceStatist")
    public R getDeviceStatistics() {
        DeviceStats deviceStats = qsDeviceService.getDeviceStatistics();
        return R.data(deviceStats);
    }

}
