package org.springblade.modules.nvr.domain.redis;

import org.springblade.modules.nvr.domain.dto.RpcController;
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
