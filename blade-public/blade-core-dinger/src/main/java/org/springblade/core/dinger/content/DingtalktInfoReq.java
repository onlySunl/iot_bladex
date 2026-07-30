package org.springblade.core.dinger.content;

import org.springblade.core.dinger.properties.DingTalkProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 钉钉通知请求体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DingtalktInfoReq {

    /**
     * 配置信息
     */
    private DingTalkProperties dingTalkProperties;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息标题
     */
    private String title;

}
