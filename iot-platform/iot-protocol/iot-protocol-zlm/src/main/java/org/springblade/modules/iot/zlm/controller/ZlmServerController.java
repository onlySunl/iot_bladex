package org.springblade.modules.iot.zlm.controller;

import org.springblade.modules.iot.common.core.web.controller.BaseController;
import org.springblade.modules.iot.common.core.web.domain.AjaxResult;
import org.springblade.modules.iot.zlm.api.domain.ZlmMediaServer;
import org.springblade.modules.iot.zlm.domain.MediaServerLoad;
import org.springblade.modules.iot.zlm.service.IMediaServerService;
import lombok.extern.slf4j.Slf4j;
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
public class ZlmServerController extends BaseController {

    @Autowired
    private IMediaServerService mediaServerService;

    /**
     * 获取负载信息
     *
     * @return
     */
    @GetMapping(value = "/media_server/load")
    public AjaxResult getMediaLoad() {
        List<MediaServerLoad> result = new ArrayList<>();
        List<ZlmMediaServer> allOnline = mediaServerService.getAllOnlineMediaServe();
        if (allOnline.isEmpty()) {
            return success(result);
        } else {
            for (ZlmMediaServer mediaServerItem : allOnline) {
                result.add(mediaServerService.getLoad(mediaServerItem));
            }
        }
        return success(result);
    }
}
