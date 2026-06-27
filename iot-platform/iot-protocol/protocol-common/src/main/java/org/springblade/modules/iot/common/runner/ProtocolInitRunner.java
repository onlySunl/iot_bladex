package org.springblade.modules.iot.common.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 协议初始化启动器
 * 按顺序加载各个协议模块
 *
 * @author BladeX
 */
@Slf4j
@Component
@AllArgsConstructor
@Order(AppConstant.ORDER_PROTOCOL)
public class ProtocolInitRunner implements ApplicationRunner {

	/**
	 * 协议启动顺序配置
	 *
	 * 顺序值越小，越先启动
	 */
	public interface ProtocolOrder {
		/**
		 * QS设备协议 - 设备基础数据，需最早启动
		 */
		int QS = 1;

		/**
		 * ZLM流媒体协议 - 流媒体服务
		 */
		int ZLM = 2;

		/**
		 * GB28181国标协议 - 国标平台接入
		 */
		int GB28181 = 3;

		/**
		 * 海康设备协议
		 */
		int HAIKANG = 10;

		/**
		 * 海康ISUP协议
		 */
		int HAIKANG_ISUP = 11;

		/**
		 * 大华设备协议
		 */
		int DAHUA = 20;

		/**
		 * ONVIF协议
		 */
		int ONVIF = 30;

		/**
		 * JT1078部标协议
		 */
		int JT1078 = 40;
	}

	@Override
	public void run(ApplicationArguments args) {
		log.info("========== IoT 协议初始化启动 ==========");
		log.info("协议启动顺序:");
		log.info("  [{}] QS设备协议", ProtocolOrder.QS);
		log.info("  [{}] ZLM流媒体协议", ProtocolOrder.ZLM);
		log.info("  [{}] GB28181国标协议", ProtocolOrder.GB28181);
		log.info("  [{}] 海康设备协议", ProtocolOrder.HAIKANG);
		log.info("  [{}] 海康ISUP协议", ProtocolOrder.HAIKANG_ISUP);
		log.info("  [{}] 大华设备协议", ProtocolOrder.DAHUA);
		log.info("  [{}] ONVIF协议", ProtocolOrder.ONVIF);
		log.info("  [{}] JT1078部标协议", ProtocolOrder.JT1078);
		log.info("========================================");
	}
}
