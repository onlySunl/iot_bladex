

package org.springblade.modules.iot.monitor.web.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Whitelist {

  /**
   * 白名单组，默认则全库匹配
   *
   * @return
   */
  String[] key() default {};
}
