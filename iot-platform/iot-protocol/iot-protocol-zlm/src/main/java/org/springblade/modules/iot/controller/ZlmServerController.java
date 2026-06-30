package org.springblade.modules.iot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.api.R;
import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springblade.modules.iot.service.IMediaServerService;
import org.springblade.modules.iot.domain.MediaServerLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName ZlmServerController
 * @Description
 * @Author fengcheng
 * @date 2026-04-15
 **/
@Slf4j
@RestController
@RequestMapping("/server")
public class ZlmServerController extends BladeController {

    @Autowired
    private IMediaServerService mediaServerService;

    /**
     * 获取负载信息
     *
     * @return
     */
    @GetMapping(value = "/media_server/load")
    public R getMediaLoad() {
        List<MediaServerLoad> result = new ArrayList<>();
        List<ZlmMediaServer> allOnline = mediaServerService.getAllOnlineMediaServe();
        if (allOnline.isEmpty()) {
            return R.data(result);
        } else {
            for (ZlmMediaServer mediaServerItem : allOnline) {
                result.add(mediaServerService.getLoad(mediaServerItem));
            }
        }
        return R.data(result);
    }
}
