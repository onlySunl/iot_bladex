package org.springblade.core.dinger.properties;

import org.springblade.basic.utils.StrPool;
import org.springblade.core.dinger.enums.MsgTypeEnum;
import lombok.Data;

import java.util.List;

import static org.springblade.core.dinger.enums.MsgTypeEnum.TEXT;


/**
 * 配置基类
 */
@Data
public class NoticeProperties {

    /**
     * token
     */
    private String token;

    /**
     * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
     */
    private List<String> atMobiles;

    /**
     * webHook地址
     */
    private String webHook;

    /**
     * 消息类型 暂只支持text和markdown
     */
    private MsgTypeEnum msgType = TEXT;

    /**
     * 验签密钥
     */
    private String secret;

    /**
     * 是否@所有人
     */
    private String isAtAll = StrPool.NO;
}
