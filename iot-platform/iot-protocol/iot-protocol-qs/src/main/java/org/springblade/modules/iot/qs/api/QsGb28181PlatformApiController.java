package org.springblade.modules.iot.qs.api;


import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.QsGb28181Platform;
import org.springblade.modules.iot.domain.QsGb28181PlatformChannel;
import org.springblade.modules.iot.qs.service.IQsGb28181PlatformChannelService;
import org.springblade.modules.iot.qs.service.IQsGb28181PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 国标GB28181平台API Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/api/gb28181")
public class QsGb28181PlatformApiController {

    @Autowired
    private IQsGb28181PlatformService qsGb28181PlatformService;

    @Autowired
    private IQsGb28181PlatformChannelService qsGb28181PlatformChannelService;

    /**
     * 查询平台列表（内部接口）
     */
    @PostMapping("/platform/list")
    public R<List<QsGb28181Platform>> selectPlatformList(@RequestBody QsGb28181Platform platform) {
        return R.data(qsGb28181PlatformService.selectQsGb28181PlatformList(platform));
    }

    /**
     * 根据ID查询平台（内部接口）
     */
    @GetMapping("/platform/{id}")
    public R<QsGb28181Platform> selectPlatformById(@PathVariable Long id) {
        return R.data(qsGb28181PlatformService.selectQsGb28181PlatformById(id));
    }

    /**
     * 查询平台通道列表（内部接口）
     */
    @PostMapping("/platformChannel/list")
    public R<List<QsGb28181PlatformChannel>> selectPlatformChannelList(@RequestBody QsGb28181PlatformChannel channel) {
        return R.data(qsGb28181PlatformChannelService.selectQsGb28181PlatformChannelList(channel));
    }

    /**
     * 更新平台（内部接口）
     */
    @PutMapping("/platform")
    public R<Boolean> updatePlatform(@RequestBody QsGb28181Platform platform) {
        return R.data(qsGb28181PlatformService.updateQsGb28181Platform(platform) > 0);
    }
}
