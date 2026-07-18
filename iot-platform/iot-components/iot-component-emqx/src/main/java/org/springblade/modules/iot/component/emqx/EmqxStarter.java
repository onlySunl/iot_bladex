

package org.springblade.modules.iot.component.emqx;


import org.springblade.modules.iot.component.emqx.service.EmqxVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

@Slf4j
@Component
public class EmqxStarter {

    @Resource
    private EmqxVerticle emqxVerticle;

    @PostConstruct
    public void init() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(emqxVerticle, ar -> {
            if (ar.succeeded()) {
                log.info("start emqx component success!");
            } else {
                log.error("start emqx component failed", ar.cause());
            }
        });
    }
}