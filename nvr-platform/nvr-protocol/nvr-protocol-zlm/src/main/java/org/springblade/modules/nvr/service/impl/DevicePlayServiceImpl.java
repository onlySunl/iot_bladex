package org.springblade.modules.nvr.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.nvr.common.enums.LiveStreamType;
import org.springblade.modules.nvr.domain.QsDevice;
import org.springblade.modules.nvr.domain.StreamInfo;
import org.springblade.modules.nvr.service.ErrorCallback;
import org.springblade.modules.nvr.service.IDevicePlayService;
import org.springblade.modules.nvr.service.IMediaServerService;
import org.springblade.modules.nvr.service.ISourcePlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @FileName DevicePlayServiceImpl
 * @Description
 * @Author fengcheng
 * @date 2026-04-12
 **/
@Slf4j
@Service
public class DevicePlayServiceImpl implements IDevicePlayService {

    @Autowired
    private Map<String, ISourcePlayService> sourcePlayServiceMap;

    @Autowired
    private IMediaServerService mediaServerService;

    public final static String PLAY_SERVICE = "sourceDevicePlayService";

    @Override
    public void play(QsDevice device, Boolean record, ErrorCallback<StreamInfo> callback) {
        log.info("[设备] 播放， 类型： {}", LiveStreamType.getByCode(device.getType()));
        mediaServerService.play(device, record, callback);
    }
}
