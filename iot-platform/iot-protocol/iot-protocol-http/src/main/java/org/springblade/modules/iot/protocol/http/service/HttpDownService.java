/*
 *
 * Copyright (c) 2025, NexIoT. All Rights Reserved.
 *
 * @Description: 本文件由 gitee.com/NexIoT 开发并拥有版权，未经授权严禁擅自商用、复制或传播。
 * @Author: gitee.com/NexIoT
 * @Email: wo8335224@gmail.com
 * @Wechat: outlookFil
 *
 *
 */

package org.springblade.modules.iot.protocol.http.service;

import cn.hutool.json.JSONUtil;
import org.springblade.modules.iot.common.domain.R;
import org.springblade.modules.iot.common.service.IDown;
import org.springblade.modules.iot.dm.device.service.AbstractDownService;
import org.springblade.modules.iot.protocol.http.config.HttpModuleInfo;
import org.springblade.modules.iot.pojo.protocol.http.HttpDownRequest;
import org.springblade.modules.iot.protocol.http.handle.HttpDownHandle;
import org.springblade.modules.iot.protocol.http.processor.HttpDownProcessorChain;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * http下行实现类
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/02/24 11:19
 */
@Service("httpDownService")
@Slf4j
public class HttpDownService extends AbstractDownService<HttpDownRequest> implements IDown {

  @Resource private HttpModuleInfo httpModuleInfo;
  @Resource private HttpDownHandle httpDownHandle;
  @Resource private HttpDownProcessorChain httpDownProcessorChain;
  // ← 注入拦截器链管理器（包含所有拦截器）
  @Resource private org.springblade.modules.iot.common.downlink.DownlinkInterceptorChain downlinkInterceptorChain;

  @Override
  public org.springblade.modules.iot.common.downlink.DownlinkInterceptorChain getInterceptorChain() {
    return downlinkInterceptorChain; // ← 返回给 IDown 接口使用
  }

  /**
   * @deprecated 该方法已废弃，请使用HttpDownRequestConverter进行转换
   * 保留此方法仅为了兼容遗留代码
   */
  @Deprecated
  @Override
  protected HttpDownRequest convert(String request) {
    return JSONUtil.toBean(request, HttpDownRequest.class);
  }

  @Override
  public String code() {
    return httpModuleInfo.getCode();
  }

  @Override
  public String name() {
    return httpModuleInfo.getName();
  }

  @Override
  @SuppressWarnings("unchecked")
  public R doProcess(org.springblade.modules.iot.common.downlink.DownlinkContext<?> context) {
    try {
      // 从上下文获取已转换好的请求对象（由Converter自动转换）
      HttpDownRequest downRequest = (HttpDownRequest) context.getDownRequest();
      
      if (downRequest == null) {
        return R.error("请求对象为空，请检查Converter配置");
      }

      log.info(
          "[HTTP下行] deviceId={} productKey={} cmd={}",
          downRequest.getDeviceId(),
          downRequest.getProductKey(),
          downRequest.getCmd() != null ? downRequest.getCmd().getValue() : null);

      // 执行处理链
      httpDownProcessorChain.process(downRequest);
      return httpDownHandle.httpDown(downRequest);

    } catch (Exception e) {
      log.error("[HTTP下行] 处理异常", e);
      return R.error("处理异常: " + e.getMessage());
    }
  }
}
