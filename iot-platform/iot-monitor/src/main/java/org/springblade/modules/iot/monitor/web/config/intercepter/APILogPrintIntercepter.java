

package org.springblade.modules.iot.monitor.web.config.intercepter;

import cn.hutool.json.JSONObject;
import org.springblade.modules.iot.common.constant.IoTConstant;
import org.springblade.modules.iot.monitor.web.config.log.WebLog;
import org.springblade.modules.iot.monitor.web.config.log.WebLogKits;
import org.springblade.modules.iot.monitor.web.context.TtlAuthContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Order(100)
public class APILogPrintIntercepter implements HandlerInterceptor {

  private static final Logger log = LoggerFactory.getLogger(APILogPrintIntercepter.class);

  // 使用ScopedValue替代ThreadLocal，自动管理生命周期
  private static final ThreadLocal<WebLog> WEB_LOG_SCOPE = ThreadLocal.withInitial(() -> null);

  @Value(value = "${logback.enable:true}")
  private boolean logEnable;

  @Value(value = "${logback.weblog.resp:true}")
  private boolean logRespEnable;

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    long startTime = System.currentTimeMillis();
    String principal = TtlAuthContextHolder.getInstance().getContext();
    WebLog webLog = WebLog.builder().entryTime(startTime).build();
    JSONObject reqParams = WebLogKits.getParameters(request);
    JSONObject reqJsonData = WebLogKits.getJsonData(request);
    reqParams.putAll(reqJsonData);
    webLog.setReqParams(reqParams.toString());
    JSONObject headersInfo = WebLogKits.getHeadersInfo(request);
    headersInfo.set("principal", principal);
    webLog.setHeaderInfo(headersInfo.toString());

    // 使用ScopedValue设置WebLog
    WEB_LOG_SCOPE.set(webLog);

    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    try {
      WebLog webLog = WEB_LOG_SCOPE.get();
      webLog.setReturnTime(System.currentTimeMillis());
      webLog.setTraceId(MDC.get(IoTConstant.TRACE_ID));
      webLog.log();
    } catch (IllegalStateException e) {
      log.warn("WebLog未设置，跳过日志记录");
    } finally {
      WEB_LOG_SCOPE.remove();
    }
    // ScopedValue会自动清理，无需手动remove
  }
}
