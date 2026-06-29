package org.springblade.modules.iot.qs.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.common.constants.SecurityConstants;
import org.springblade.modules.iot.domain.QsGb28181Platform;
import org.springblade.modules.iot.qs.service.IQsGb28181PlatformChannelService;
import org.springblade.modules.iot.qs.service.IQsGb28181PlatformService;
import org.springblade.modules.iot.service.RemoteGb28181Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 国标GB28181平台配置Controller
 *
 * @author ruoyi
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/platform")
public class QsGb28181PlatformController extends BladeController {

    private final IQsGb28181PlatformService qsGb28181PlatformService;
    private final IQsGb28181PlatformChannelService qsGb28181PlatformChannelService;
    private final RemoteGb28181Service remoteGb28181Service;

    /**
     * 查询国标GB28181平台配置列表
     */
    @GetMapping("/list")
    public IPage<List<QsGb28181Platform>> list(QsGb28181Platform qsGb28181Platform) {
        List<QsGb28181Platform> list = qsGb28181PlatformService.selectQsGb28181PlatformList(qsGb28181Platform);
        
        // 填充关联设备数量
        if (!CollectionUtils.isEmpty(list)) {
            List<Long> platformIds = list.stream().map(QsGb28181Platform::getId).collect(Collectors.toList());
            Map<Long, Integer> deviceCountMap = qsGb28181PlatformChannelService.countDeviceByPlatformIds(platformIds);
            list.forEach(platform -> platform.setDeviceCount(deviceCountMap.getOrDefault(platform.getId(), 0)));
        }
        
        return new Page<>();
    }

    /**
     * 导出国标GB28181平台配置列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, QsGb28181Platform qsGb28181Platform) {
        List<QsGb28181Platform> list = qsGb28181PlatformService.selectQsGb28181PlatformList(qsGb28181Platform);
        //ExcelUtil util = new ExcelUtil(QsGb28181Platform.class);
        //util.exportExcel(response, list, "国标GB28181平台配置数据");
    }

    /**
     * 获取国标GB28181平台配置详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        QsGb28181Platform platform = qsGb28181PlatformService.selectQsGb28181PlatformById(id);
        if (platform != null) {
            int deviceCount = qsGb28181PlatformChannelService.countDeviceByPlatformId(id);
            platform.setDeviceCount(deviceCount);
        }
        return R.data(platform);
    }

    /**
     * 新增国标GB28181平台配置
     */
    @PostMapping
    public R add(@RequestBody QsGb28181Platform qsGb28181Platform) {
        int result = qsGb28181PlatformService.insertQsGb28181Platform(qsGb28181Platform);
        if (result > 0 && qsGb28181Platform.getEnable() != null && qsGb28181Platform.getEnable() == 1) {
            remoteGb28181Service.startPlatformCascade(qsGb28181Platform.getId(), SecurityConstants.INNER);
        }
        return R.data(result);
    }

    /**
     * 修改国标GB28181平台配置
     */
    @PutMapping
    public R edit(@RequestBody QsGb28181Platform qsGb28181Platform) {
        int result = qsGb28181PlatformService.updateQsGb28181Platform(qsGb28181Platform);
        if (result > 0) {
            remoteGb28181Service.restartPlatformCascade(qsGb28181Platform.getId(), SecurityConstants.INNER);
        }
        return R.data(result);
    }

    /**
     * 删除国标GB28181平台配置
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids) {
        for (Long id : ids) {
            remoteGb28181Service.stopPlatformCascade(id, SecurityConstants.INNER);
        }
        return R.data(qsGb28181PlatformService.deleteQsGb28181PlatformByIds(ids));
    }

    /**
     * 启动平台级联
     */
    @PostMapping("/cascade/start/{id}")
    public R startCascade(@PathVariable Long id) {
        QsGb28181Platform platform = qsGb28181PlatformService.selectQsGb28181PlatformById(id);
        if (platform == null) {
            return R.fail("平台不存在");
        }
        if (platform.getEnable() == null || platform.getEnable() != 1) {
            return R.fail("平台未启用，请先启用平台");
        }
        log.info("启动平台级联: id={}, name={}", id, platform.getName());
        remoteGb28181Service.startPlatformCascade(id, SecurityConstants.INNER);
        log.info("启动平台级联完成: id={}", id);
        return R.success();
    }

    /**
     * 停止平台级联
     */
    @PostMapping("/cascade/stop/{id}")
    public R stopCascade(@PathVariable Long id) {
        QsGb28181Platform platform = qsGb28181PlatformService.selectQsGb28181PlatformById(id);
        if (platform != null) {
            log.info("停止平台级联: id={}, name={}", id, platform.getName());
        }
        remoteGb28181Service.stopPlatformCascade(id, SecurityConstants.INNER);
        log.info("停止平台级联完成: id={}", id);
        return R.success();
    }

    /**
     * 重启平台级联
     */
    @PostMapping("/cascade/restart/{id}")
    public R restartCascade(@PathVariable Long id) {
        remoteGb28181Service.restartPlatformCascade(id, SecurityConstants.INNER);
        return R.success();
    }

    /**
     * 推送目录到上级平台
     */
    @PostMapping("/cascade/pushCatalog/{id}")
    public R pushCatalog(@PathVariable Long id) {
        QsGb28181Platform platform = qsGb28181PlatformService.selectQsGb28181PlatformById(id);
        if (platform == null) {
            return R.fail("平台不存在");
        }
        remoteGb28181Service.pushCatalog(id, SecurityConstants.INNER);
        return R.success();
    }


}
