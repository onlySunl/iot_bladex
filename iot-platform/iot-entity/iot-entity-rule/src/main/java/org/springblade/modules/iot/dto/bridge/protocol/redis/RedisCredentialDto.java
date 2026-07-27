package org.springblade.modules.iot.dto.bridge.protocol.redis;

import org.springblade.modules.iot.dto.bridge.protocol.ProtocolCredentialDto;

/**
 * Redis 凭证 DTO。
 *
 * @author mqttsnet
 */
public class RedisCredentialDto implements ProtocolCredentialDto {

    /**
     * Redis 密码（requirepass / masterauth；空则不认证）
     */
    public String password;
}
