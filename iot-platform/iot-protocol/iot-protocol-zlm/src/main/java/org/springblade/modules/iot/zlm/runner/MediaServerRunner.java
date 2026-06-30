package org.springblade.modules.iot.zlm.runner;

import org.springblade.modules.iot.domain.ZlmMediaServer;
import org.springblade.modules.iot.zlm.config.MediaConfig;
import org.springblade.modules.iot.zlm.config.UserSetting;
import org.springblade.modules.iot.zlm.mediaServer.MediaServerChangeEvent;
import org.springblade.modules.iot.zlm.service.IMediaServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Component
@Order(value = 1)
public class MediaServerRunner implements CommandLineRunner {

    @Autowired
    private IMediaServerService mediaServerService;

    @Autowired
    private MediaConfig mediaConfig;

    @Autowired
    private UserSetting userSetting;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void run(String... args) {
        // 关键修复：容器正在关闭/销毁时，直接退出，不执行任何逻辑
        if (!applicationContext.isActive()) {
            log.warn("[媒体节点启动] Spring容器非活跃/正在销毁，跳过节点初始化任务");
            return;
        }

        try {
            log.info("[媒体节点启动] MediaServerRunner 开始执行...");

            // 配置空校验
            if (ObjectUtils.isEmpty(mediaConfig) || ObjectUtils.isEmpty(mediaConfig.getMediaSerItem())
                    || ObjectUtils.isEmpty(userSetting)) {
                log.error("[媒体节点启动失败] 配置文件 MediaConfig / UserSetting 未加载");
                return;
            }

            // 拷贝配置对象，防止修改全局配置Bean脏数据
            ZlmMediaServer configItem = mediaConfig.getMediaSerItem();
            ZlmMediaServer newServer = new ZlmMediaServer();
            newServer.setId(configItem.getId());
            newServer.setServerId(userSetting.getServerId());

            ZlmMediaServer defaultServer = mediaServerService.getDefaultMediaServer();
            if (defaultServer != null && newServer.getId().equals(defaultServer.getId())) {
                mediaServerService.update(newServer);
            } else {
                if (defaultServer != null) {
                    mediaServerService.delete(defaultServer);
                }
                ZlmMediaServer dbItem = mediaServerService.getOneFromDatabase(newServer.getId());
                if (dbItem == null) {
                    mediaServerService.add(newServer);
                } else {
                    mediaServerService.update(newServer);
                }
            }
            //加载节点 & 发布
             List<ZlmMediaServer> nodeList = mediaServerService.getAllFromDatabase();
            log.info("[媒体节点] 初始化完成，共加载{}个节点", nodeList.size());
            MediaServerChangeEvent event = new MediaServerChangeEvent(this);
            event.setMediaServerItemList(nodeList);

            // 二次校验容器状态，防止发布事件时容器销毁
            if (applicationContext.isActive()) {
                applicationEventPublisher.publishEvent(event);
                log.info("[媒体节点启动] 节点变更事件推送成功");
            }

        } catch (IllegalStateException e) {
            log.warn("[媒体节点启动] 容器状态变更，终止任务（正常刷新忽略）");
        } catch (Exception e) {
            log.error("[媒体节点启动] 初始化任务异常", e);
        }
    }
}