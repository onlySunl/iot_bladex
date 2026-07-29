package org.springblade.modules.iot.msg.strategy.domain.mail;

import org.springblade.modules.iot.msg.strategy.domain.BaseProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mqttsnet
 * @date 2022/7/10 0010 18:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TencentMailProperty extends BaseProperty {
    /**
     * 端口
     */
    private String smtpPort;
    /**
     * 是否ssl
     */
    private Boolean ssl;
    /**
     * 字符集
     */
    private String charset;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 邮件服务器地址
     */
    private String hostName;
    /**
     * 发送邮箱名称
     */
    private String fromName;
    /**
     * 发送邮箱地址
     */
    private String fromEmail;

}
