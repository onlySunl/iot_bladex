package org.springblade.modules.iot.protocol.common.runner;

import lombok.extern.slf4j.Slf4j;
import org.springboot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 协议启动顺序配置
 * 
 * 启动顺序:
 * 1. QS协议 - 设备基础数据 (Order 1)
 * 2. ZLM流媒体服务 - 流媒体服务 (Order 2)
 * 3. GB28181国标协议 - 国标平台接入 (Order 3)
 * 4. 海康/大华/ONVIF等设备协议 (Order 4+)
 *
 * @author BladeX
 */
@Slf4j
@Component
public class ProtocolInitRunner {

    /**
     * QS协议初始化
     */
    @Component
    @Order(1)
    @ConditionalOnProperty(name = "blade.iot.protocol.qs.enabled", havingValue = "true", matchIfMissing = true)
    public static class QsProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== QS协议模块初始化 ===");
            // QS设备管理服务初始化
        }
    }

    /**
     * ZLM流媒体协议初始化
     */
    @Component
    @Order(2)
    @ConditionalOnProperty(name = "blade.iot.protocol.zlm.enabled", havingValue = "true", matchIfMissing = true)
    public static class ZlmProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== ZLM流媒体协议初始化 ===");
            // ZLM流媒体服务初始化
        }
    }

    /**
     * GB28181国标协议初始化
     */
    @Component
    @Order(3)
    @ConditionalOnProperty(name = "blade.iot.protocol.gb28181.enabled", havingValue = "true", matchIfMissing = true)
    public static class Gb28181ProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== GB28181国标协议初始化 ===");
            // GB28181 SIP服务初始化
        }
    }

    /**
     * 海康设备协议初始化
     */
    @Component
    @Order(10)
    @ConditionalOnProperty(name = "blade.iot.protocol.haikang.enabled", havingValue = "true", matchIfMissing = true)
    public static class HaikangProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== 海康设备协议初始化 ===");
        }
    }

    /**
     * 海康ISUP协议初始化
     */
    @Component
    @Order(11)
    @ConditionalOnProperty(name = "blade.iot.protocol.haikang-isup.enabled", havingValue = "true", matchIfMissing = true)
    public static class HaikangIsupProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== 海康ISUP协议初始化 ===");
        }
    }

    /**
     * 大华设备协议初始化
     */
    @Component
    @Order(20)
    @ConditionalOnProperty(name = "blade.iot.protocol.dahua.enabled", havingValue = "true", matchIfMissing = true)
    public static class DahuaProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== 大华设备协议初始化 ===");
        }
    }

    /**
     * ONVIF协议初始化
     */
    @Component
    @Order(30)
    @ConditionalOnProperty(name = "blade.iot.protocol.onvif.enabled", havingValue = "true", matchIfMissing = true)
    public static class OnvifProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== ONVIF协议初始化 ===");
        }
    }

    /**
     * JT1078部标协议初始化
     */
    @Component
    @Order(40)
    @ConditionalOnProperty(name = "blade.iot.protocol.jt1078.enabled", havingValue = "true", matchIfMissing = true)
    public static class Jt1078ProtocolRunner implements ApplicationRunner {
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            log.info("=== JT1078部标协议初始化 ===");
        }
    }

}
