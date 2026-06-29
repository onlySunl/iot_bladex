package org.springblade.modules.iot.zlm.impl;

import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.domain.RTPServerParam;
import org.springblade.modules.iot.zlm.common.InviteErrorCode;
import org.springblade.modules.iot.zlm.config.DynamicTask;
import org.springblade.modules.iot.zlm.config.UserSetting;

import org.springblade.modules.iot.zlm.domain.OpenRTPServerResult;
import org.springblade.modules.iot.zlm.domain.SSRCInfo;
import org.springblade.modules.iot.zlm.hook.Hook;
import org.springblade.modules.iot.zlm.hook.HookSubscribe;
import org.springblade.modules.iot.zlm.hook.HookType;
import org.springblade.modules.iot.zlm.service.ErrorCallback;
import org.springblade.modules.iot.zlm.service.IMediaServerService;
import org.springblade.modules.iot.zlm.service.IReceiveRtpServerService;
import org.springblade.modules.iot.zlm.session.SSRCFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @FileName RtpServerServiceImpl
 * @Description
 * @Author fengcheng
 * @date 2026-04-07
 **/
@Slf4j
@Service
public class RtpServerServiceImpl implements IReceiveRtpServerService {

    @Autowired
    private SSRCFactory ssrcFactory;

    @Autowired
    private DynamicTask dynamicTask;

    @Autowired
    private HookSubscribe subscribe;

    @Autowired
    private UserSetting userSetting;

    @Autowired
    @Lazy
    private IMediaServerService mediaServerService;

    @Override
    public SSRCInfo openRTPServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback) {
        if (callback == null) {
            log.warn("[开启RTP收流] 失败，回调为NULL");
            return null;
        }
        if (rtpServerParam.getMediaServer() == null) {
            log.warn("[开启RTP收流] 失败，媒体节点为NULL");
            return null;
        }

        if (rtpServerParam.isSsrcCheck() && rtpServerParam.getTcpMode() > 0) {
            // 目前zlm不支持 tcp模式更新ssrc，暂时关闭ssrc校验
            log.warn("[openRTPServer] 平台对接时下级可能自定义ssrc，但是tcp模式zlm收流目前无法更新ssrc，可能收流超时，此时请使用udp收流或者关闭ssrc校验");
        }

        int rtpServerPort = mediaServerService.createRTPServer(
                rtpServerParam.getMediaServer(),
                rtpServerParam.getApp(),
                rtpServerParam.getStreamId(),
                rtpServerParam.isSsrcCheck() ?
                        Long.parseLong(rtpServerParam.getSsrc()) : 0,
                rtpServerParam.getPort(),
                rtpServerParam.isOnlyAuto(),
                rtpServerParam.isDisableAudio(),
                rtpServerParam.isReUsePort(),
                rtpServerParam.getTcpMode());

        if (rtpServerPort == 0) {
            callback.run(InviteErrorCode.ERROR_FOR_RESOURCE_EXHAUSTION.getCode(), "开启RTPServer失败", null);
            // 释放ssrc
            if (rtpServerParam.getPresetSsrc() == null) {
                ssrcFactory.releaseSsrc(rtpServerParam.getMediaServer().getId(), rtpServerParam.getSsrc());
            }
            return null;
        }

        rtpServerParam.setPort(rtpServerPort);

        // 设置流超时的定时任务
        String timeOutTaskKey = UUID.randomUUID().toString();

        SSRCInfo ssrcInfo = new SSRCInfo(rtpServerPort, rtpServerParam.getSsrc(), rtpServerParam.getApp(), rtpServerParam.getStreamId(), timeOutTaskKey);
        OpenRTPServerResult openRTPServerResult = new OpenRTPServerResult();
        openRTPServerResult.setSsrcInfo(ssrcInfo);

        Hook rtpHook = Hook.getInstance(HookType.on_media_arrival, ssrcInfo.getApp(), rtpServerParam.getStreamId(), Func.toStr(rtpServerParam.getMediaServer().getId()));


        dynamicTask.startDelay(timeOutTaskKey, () -> {
            // 收流超时
            // 释放ssrc
            if (rtpServerParam.getPresetSsrc() == null) {
                ssrcFactory.releaseSsrc(rtpServerParam.getMediaServer().getId(), rtpServerParam.getSsrc());
            }
            // 关闭收流端口
            mediaServerService.closeRTPServer(rtpServerParam.getMediaServer(), rtpServerParam.getStreamId());
            subscribe.removeSubscribe(rtpHook);
            callback.run(InviteErrorCode.ERROR_FOR_STREAM_TIMEOUT.getCode(), InviteErrorCode.ERROR_FOR_STREAM_TIMEOUT.getMsg(), openRTPServerResult);
        }, userSetting.getPlayTimeout());

        // 开启流到来的监听
        subscribe.addSubscribe(rtpHook, (hookData) -> {
            dynamicTask.stop(timeOutTaskKey);
            // hook响应
            openRTPServerResult.setHookData(hookData);
            callback.run(InviteErrorCode.SUCCESS.getCode(), InviteErrorCode.SUCCESS.getMsg(), openRTPServerResult);
            subscribe.removeSubscribe(rtpHook);
        });

        return ssrcInfo;
    }
}
