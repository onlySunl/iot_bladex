package org.springblade.modules.iot.haikangisup.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 海康ISUP配置
 *
 * @author fengcheng
 */
@Data
@Component
@ConfigurationProperties(prefix = "blade.haikang-isup")
public class HaiKangIsupConfig {

	/**
	 * CMS服务器配置
	 */
	private CmsServer cmsServer = new CmsServer();

	/**
	 * DAS服务器配置
	 */
	private DasServer dasServer = new DasServer();

	/**
	 * SMS取流服务器配置
	 */
	private SmsServer smsServer = new SmsServer();

	/**
	 * 回放流媒体服务器配置
	 */
	private SmsBackServer smsBackServer = new SmsBackServer();

	/**
	 * 语音流媒体服务器配置
	 */
	private VoiceSmsServer voiceSmsServer = new VoiceSmsServer();

	/**
	 * 报警服务器配置
	 */
	private AlarmServer alarmServer = new AlarmServer();

	/**
	 * 图片存储服务器配置
	 */
	private PicServer picServer = new PicServer();

	/**
	 * ISUP5.0登录秘钥
	 */
	private String isupKey;

	/**
	 * 事件打印模式: file=保存到文件, console=打印到控制台
	 */
	private String eventInfoPrintType;

	@Data
	public static class CmsServer {
		private String ip;
		private int port;
		private String listenIp;
		private int listenPort;
	}

	@Data
	public static class DasServer {
		private String ip;
		private int port;
	}

	@Data
	public static class SmsServer {
		private String ip;
		private int port;
		private String listenIp;
		private int listenPort;
	}

	@Data
	public static class SmsBackServer {
		private String ip;
		private int port;
		private String listenIp;
		private int listenPort;
	}

	@Data
	public static class VoiceSmsServer {
		private String ip;
		private int port;
		private String listenIp;
		private int listenPort;
	}

	@Data
	public static class AlarmServer {
		private String ip;
		private int tcpPort;
		private int udpPort;
		private Boolean alarmStorage;
		private int type; // 0=UDP, 1=UDP+TCP, 2=MQTT
		private String listenIp;
		private int listenTcpPort;
		private int listenUdpPort;
	}

	@Data
	public static class PicServer {
		private String ip;
		private int port;
		private int type; // 0=Tomcat, 1=VRB, 2=云存储, 3=KMS, 4=ISUP5.0
		private String listenIp;
		private int listenPort;
	}
}
