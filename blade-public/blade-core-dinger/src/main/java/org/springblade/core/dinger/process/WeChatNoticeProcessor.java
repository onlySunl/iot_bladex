package org.springblade.core.dinger.process;

import cn.hutool.core.bean.BeanUtil;
import org.springblade.core.dinger.abstraction.NoticeAbstract;
import org.springblade.core.dinger.content.WeChatInfoReq;
import org.springblade.core.dinger.content.WxChatResponse;
import org.springblade.core.dinger.properties.WeChatProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 企业微信通知
 */
@Component("weChatNoticeProcessor")
@Slf4j
public class WeChatNoticeProcessor extends NoticeAbstract implements INoticeProcessor {

    private RestTemplate restTemplate;

    public WeChatNoticeProcessor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * @param obj 消息信息
     */
    @Override
    public Object sendNotice(Object obj) {
        WeChatInfoReq weChatInfoReq = (WeChatInfoReq) obj;
        WeChatProperties weChatProperties = weChatInfoReq.getWeChatProperties();
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        getRequiredProperty(weChatProperties::getToken, "token为空,请配置");
        getRequiredProperty(weChatProperties::getWebHook, "url为空,请配置");
        String result = restTemplate.postForObject(weChatProperties.getWebHook() + "?key=" + weChatProperties.getToken(), weChatInfoReq.getWeChatInfo(), String.class);
        return BeanUtil.toBean(result, WxChatResponse.class);
    }
}
