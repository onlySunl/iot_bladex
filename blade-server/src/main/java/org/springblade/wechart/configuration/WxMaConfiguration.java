package org.springblade.wechart.configuration;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springblade.wechart.pay.entity.WxPayBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WxPayBean.class)
public class WxMaConfiguration {

	@Bean
	public WxMaService wxMaService(WxPayBean wxPayBean) {
		WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
		config.setAppid(wxPayBean.getAppId());
		config.setSecret(wxPayBean.getAppSecret());
		config.setMsgDataFormat(wxPayBean.getMsgDataFormat());

		WxMaService service = new WxMaServiceImpl();
		service.setWxMaConfig(config);
		return service;
	}

}
