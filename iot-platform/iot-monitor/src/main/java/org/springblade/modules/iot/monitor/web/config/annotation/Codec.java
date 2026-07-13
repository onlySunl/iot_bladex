

package org.springblade.modules.iot.monitor.web.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 指定要执行编解码的方法 @Author gitee.com/NexIoT */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Codec {

  /**
   * 指定编解码唯一编号
   *
   * @return 解码唯一编号
   */
  String codeKey() default "";
}
