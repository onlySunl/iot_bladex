package org.springblade.modules.iot.zlm.domain.redis;

import org.springblade.modules.iot.zlm.domain.dto.RpcController;
import lombok.Data;

import java.lang.reflect.Method;

@Data
public class RedisRpcClassHandler {

    private RpcController controller;
    private Method method;

    public RedisRpcClassHandler(RpcController controller, Method method) {
        this.controller = controller;
        this.method = method;
    }
}
