

package org.springblade.modules.iot.core.downlink.interceptor;

import cn.hutool.core.util.StrUtil;
import org.springblade.modules.iot.core.downlink.DownlinkContext;
import org.springblade.modules.iot.core.downlink.DownlinkInterceptor;
import org.springblade.modules.iot.core.downlink.InterceptorPhase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 下行数据校验拦截器
 * 校验下行消息的必要参数
 *
 * @version 1.0
 * @since 2025/10/24
 */
@Slf4j
@Component
@Order(50) // 校验拦截器优先级较高，在业务处理前执行
public class DownlinkValidationInterceptor implements DownlinkInterceptor {

  @Override
  public String getName() {
    return "下行数据校验拦截器";
  }

  @Override
  public int getOrder() {
    return 50;
  }

  @Override
  public InterceptorPhase getPhase() {
    return InterceptorPhase.PRE;
  }

  @Override
  public boolean preHandle(DownlinkContext<?> context) {
    // 校验协议代码
    if (StrUtil.isBlank(context.getProtocolCode())) {
      log.warn("[校验拦截器] 协议代码为空");
      return false;
    }

    // 校验原始消息
    if (context.getRawMessage() == null && context.getJsonMessage() == null) {
      log.warn("[校验拦截器] 原始消息为空");
      return false;
    }

    log.debug("[校验拦截器] 数据校验通过: protocol={}", context.getProtocolCode());

    return true;
  }

  @Override
  public void postHandle(DownlinkContext<?> context) {
    // 后置处理：可以校验处理结果
    if (context.getResult() == null) {
      log.warn("[校验拦截器] 处理结果为空: protocol={}", context.getProtocolCode());
    }
  }
}
