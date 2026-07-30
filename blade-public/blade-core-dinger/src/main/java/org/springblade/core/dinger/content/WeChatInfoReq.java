package org.springblade.core.dinger.content;

import org.springblade.core.dinger.properties.WeChatProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企业微信通知请求体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeChatInfoReq {

    private WeChatProperties weChatProperties;

    private WeChatInfo weChatInfo;

}
