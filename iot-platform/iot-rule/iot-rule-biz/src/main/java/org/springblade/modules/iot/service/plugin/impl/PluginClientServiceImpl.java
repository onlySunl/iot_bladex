package org.springblade.modules.iot.service.plugin.impl;

import cn.hutool.core.net.NetUtil;
import org.springblade.modules.iot.service.plugin.PluginClientService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springblade.open.plugin.manager.PluginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * -----------------------------------------------------------------------------
 * File Name: PluginClientServiceImpl
 * -----------------------------------------------------------------------------
 * Description:
 * Plugin Client Service Implementation
 * -----------------------------------------------------------------------------
 *
 * @author mqttsnet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/26       mqttsnet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/26 10:01
 */
@Slf4j
@Service
public class PluginClientServiceImpl implements PluginClientService {

    @Autowired
    PluginServer pluginServer;

    @Value("${plugin.server.port:8080}")
    String serverPort;

    @Value("${plugin.application.name:defname}")
    String applicationName;

    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public String getIp() {
        return NetUtil.getLocalhostStr();
    }

    @Override
    public String getPort() {
        return serverPort;
    }

    @PostConstruct
    @Override
    public void start() {
        // 使用ThreadPoolExecutor创建线程池，避免Executors方法带来的资源问题
        scheduledExecutorService = new ScheduledThreadPoolExecutor(10, new BasicThreadFactory.Builder()
                .namingPattern("rule-plugin-schedule-pool-%d")
                .daemon(true)
                .build());

        log.info("Starting scheduled task for sending heartbeat at {}", LocalDateTime.now());

        scheduledExecutorService.scheduleAtFixedRate(() -> {
                    try {
                        String ip = getIp();
                        String port = getPort();
                        String applicationName = getApplicationName();

                        log.info("Preparing to send heartbeat at {} with IP: {}, Port: {}, Application Name: {}",
                                LocalDateTime.now(), ip, port, applicationName);

                        pluginServer.heartbeat(ip, port, applicationName);

                        log.info("Heartbeat sent successfully at {}", LocalDateTime.now());
                    } catch (Exception e) {
                        log.error("Failed to send heartbeat at {}", LocalDateTime.now(), e);
                    }
                },
                30, 30, TimeUnit.SECONDS);
    }

    @PreDestroy
    @Override
    public void stop() {
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            log.info("Shutting down scheduled task for sending heartbeat at {}", LocalDateTime.now());
            scheduledExecutorService.shutdown();
            log.info("Scheduled task for sending heartbeat has been stopped at {}", LocalDateTime.now());
        }
    }

    @Override
    public String startPlugin(String pluginId) {
        return "";
    }

    @Override
    public String stopPlugin(String pluginId) {
        return "";
    }
}