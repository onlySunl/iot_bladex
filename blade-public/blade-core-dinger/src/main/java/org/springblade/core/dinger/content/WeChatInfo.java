package org.springblade.core.dinger.content;

import org.springblade.core.dinger.enums.MsgTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static org.springblade.core.dinger.enums.MsgTypeEnum.MARKDOWN;
import static org.springblade.core.dinger.enums.MsgTypeEnum.TEXT;


/**
 * 企业微信数据
 */
@Data
public class WeChatInfo {

    private WeChatText text;
    private WeChatMarkDown markdown;
    private String msgtype;

    public WeChatInfo(MsgTypeEnum msgType, String content, List<String> atMobiles) {
        if (msgType.equals(TEXT)) {
            this.text = new WeChatText(content, atMobiles.toArray(new String[]{}));
        } else if (msgType.equals(MARKDOWN)) {
            this.markdown = new WeChatMarkDown(content);
        }
        this.msgtype = msgType.getMsgType();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class WeChatText {
        private String content;
        private String[] mentioned_mobile_list;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class WeChatMarkDown {
        private String content;
    }

}
