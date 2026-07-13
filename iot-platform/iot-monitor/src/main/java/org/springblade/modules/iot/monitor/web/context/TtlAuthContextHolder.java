

package org.springblade.modules.iot.monitor.web.context;

import com.alibaba.ttl.TransmittableThreadLocal;

public class TtlAuthContextHolder {

  private TransmittableThreadLocal threadLocal = new TransmittableThreadLocal();
  private static final TtlAuthContextHolder instance = new TtlAuthContextHolder();

  private TtlAuthContextHolder() {}

  public static TtlAuthContextHolder getInstance() {
    return instance;
  }

  public void setContext(Object t) {
    this.threadLocal.set(t);
  }

  public String getContext() {
    return (String) this.threadLocal.get();
  }

  public void clear() {
    this.threadLocal.remove();
  }
}
