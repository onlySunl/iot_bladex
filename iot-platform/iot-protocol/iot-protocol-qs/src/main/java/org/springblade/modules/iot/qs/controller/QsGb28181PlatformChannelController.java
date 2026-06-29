package org.springblade.modules.iot.qs.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsDevice;
import org.springblade.modules.iot.domain.QsGb28181PlatformChannel;
import org.springblade.modules.iot.qs.mapper.QsDeviceMapper;
import org.springblade.modules.iot.qs.service.IQsGb28181PlatformChannelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 国标GB28181平台通道关联Controller
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/platformChannel")
public class QsGb28181PlatformChannelController extends BladeController {

    private final IQsGb28181PlatformChannelService qsGb28181PlatformChannelService;
    private final QsDeviceMapper qsDeviceMapper;

    /**
     * 查询平台通道关联列表
     */
    @GetMapping("/list")
    public IPage<List<QsGb28181PlatformChannel>> list(QsGb28181PlatformChannel qsGb28181PlatformChannel) {
        List<QsGb28181PlatformChannel> list = qsGb28181PlatformChannelService.selectQsGb28181PlatformChannelList(qsGb28181PlatformChannel);
        return new Page<>();
    }

    /**
     * 查询设备列表（用于关联）
     */
    @GetMapping("/deviceList")
    public IPage<List<QsDevice>> deviceList(QsDevice qsDevice, @RequestParam(required = false) Long platformId, @RequestParam(required = false) Boolean hasLink) {
        List<QsDevice> result = qsDeviceMapper.selectQsDeviceByPlatform(qsDevice, platformId, hasLink);
        return new Page<>();
    }

    /**
     * 获取平台通道关联详细信息
     */
    @GetMapping("/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        return R.data(qsGb28181PlatformChannelService.selectQsGb28181PlatformChannelById(id));
    }

    /**
     * 批量关联设备
     */
    @PostMapping("/link")
    public R link(@RequestBody LinkRequest request) {
        if (Boolean.TRUE.equals(request.getAllLink())) {
            return R.data(qsGb28181PlatformChannelService.linkAllDevices(request.getPlatformId()));
        }
        return R.data(qsGb28181PlatformChannelService.batchLinkDevice(request.getPlatformId(), request.getDeviceIds()));
    }

    /**
     * 批量取消关联设备
     */
    @PostMapping("/unlink")
    public R unlink(@RequestBody LinkRequest request) {
        if (Boolean.FALSE.equals(request.getAllLink())) {
            return R.data(qsGb28181PlatformChannelService.unlinkAllDevices(request.getPlatformId()));
        }
        return R.data(qsGb28181PlatformChannelService.batchUnlinkDevice(request.getPlatformId(), request.getDeviceIds()));
    }

    /**
     * 删除平台通道关联
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids) {
        return R.data(qsGb28181PlatformChannelService.deleteQsGb28181PlatformChannelByIds(ids));
    }

    @Data
    public static class LinkRequest {
        private Long platformId;
        private List<Long> deviceIds;
        private Boolean allLink;
    }


}
