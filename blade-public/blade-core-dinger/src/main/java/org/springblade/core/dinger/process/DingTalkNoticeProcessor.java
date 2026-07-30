package org.springblade.core.dinger.process;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import org.springblade.basic.utils.StrPool;
import org.springblade.core.dinger.abstraction.NoticeAbstract;
import org.springblade.core.dinger.content.DingtalktInfoReq;
import org.springblade.core.dinger.enums.MsgTypeEnum;
import org.springblade.core.dinger.properties.DingTalkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * 钉钉异常信息通知具体实现
 */
@Component("dingTalkNoticeProcessor")
@Slf4j
public class DingTalkNoticeProcessor extends NoticeAbstract implements INoticeProcessor {

    public DingTalkNoticeProcessor() {

    }

    /**
     * @param obj 消息信息
     */
    @Override
    public Object sendNotice(Object obj) {
        DingtalktInfoReq dingtalktInfo = (DingtalktInfoReq) obj;
        DingTalkProperties dingTalkProperties = dingtalktInfo.getDingTalkProperties();
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        getRequiredProperty(dingTalkProperties::getToken, "token为空,请配置");
        getRequiredProperty(dingTalkProperties::getWebHook, "url为空,请配置");
        getRequiredProperty(dingTalkProperties::getSecret, "签名为空,请配置");
        // 当前时间戳
        long currentTimeMillis = System.currentTimeMillis();
        // 获取签名
        String sign = createSign(String.valueOf(currentTimeMillis), dingTalkProperties.getSecret());
        // 钉钉请求对象
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        // 客户端
        DingTalkClient client = createClient(dingTalkProperties.getWebHook(), sign, currentTimeMillis);
        req.setMsgtype(dingTalkProperties.getMsgType().getMsgType());
        setContent(req, dingTalkProperties.getMsgType(), dingtalktInfo.getContent(), dingtalktInfo.getTitle());
        // 定义@对象
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(dingTalkProperties.getAtMobiles());
        at.setIsAtAll(StrPool.YES.equals(dingTalkProperties.getIsAtAll()));
        req.setAt(at);
        OapiRobotSendResponse rsp = null;
        try {
            // 执行发送钉钉消息
            rsp = client.execute(req, dingTalkProperties.getToken());
        } catch (Exception e) {
            log.error("钉钉消息发送失败：{}", e.getMessage());
        }
        return rsp;
    }

    /**
     * 生成签名
     *
     * @return
     */
    private String createSign(String timestamp, String secret) {
        // 验签
        String stringToSign = timestamp + "\n" + secret;
        String sign = "";
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StrPool.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StrPool.UTF_8));
            sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), StrPool.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * 获取客户端
     *
     * @param webHook
     * @param sign
     * @param timestamp
     * @return
     */
    private DingTalkClient createClient(String webHook, String sign, long timestamp) {
        return new DefaultDingTalkClient(webHook + "?sign=" + sign + "&timestamp=" + timestamp);
    }

    /**
     * 设置消息内容
     *
     * @param req
     * @param msgType
     * @param content
     */
    private void setContent(OapiRobotSendRequest req, MsgTypeEnum msgType, String content, String title) {
        if (msgType == MsgTypeEnum.TEXT) {
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(content);
            req.setText(text);
        } else if (msgType == MsgTypeEnum.MARKDOWN) {
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            markdown.setTitle(title);
            markdown.setText(content);
            req.setMarkdown(markdown);
        }
    }
}
