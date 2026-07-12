package org.springblade.modules.iot.protocol.common.protocol;

import lombok.Data;
import java.io.Serializable;
import java.util.Map;

/**
 * 协议定义 - 描述一个协议的基本信息和配置
 *
 * @author blade-iot
 */
@Data
public class ProtocolDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 协议ID */
    private String id;

    /** 协议名称 */
    private String name;

    /** 协议类型 */
    private ProtocolType type;

    /** 协议描述 */
    private String description;

    /** 协议配置 (JSON) */
    private Map<String, Object> configuration;

    /** 是否启用 */
    private Boolean enabled;

    /** 编解码器类名 */
    private String codecClassName;
}
