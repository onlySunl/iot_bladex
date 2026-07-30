package org.springblade.core.dinger.constant;

/**
 * 全局基础常量
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2022/5/19
 * @create [2022/5/19] [mqttsnet] [初始创建]
 */
public interface DingerConstants {

    /**
     * 钉钉预置模版
     */
    String DINGTALK_ALARM = "DINGTALK_ALARM";

    /**
     * 企业微信预置模版
     */
    String WX_ALARM = "WECHAT_ALARM";

    /**
     * 飞书预置模版
     */
    String FS_ALARM = "FS_ALARM";

    /**
     * [飞书]根据手机号获取用户ID
     */
    String BATCH_GET_ID_URL = "https://open.feishu.cn/open-apis/contact/v3/users/batch_get_id?user_id_type=user_id";

    /**
     * [飞书]认证获取token
     */
    String TENANT_ACCESS_TOKEN_URL = "https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal";

}
