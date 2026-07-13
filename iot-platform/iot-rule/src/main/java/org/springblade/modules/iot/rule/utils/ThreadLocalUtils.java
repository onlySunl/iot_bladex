

package org.springblade.modules.iot.rule.utils;

import java.util.Map;

/**
 * todo @Author gitee.com/NexIoT
 *
 * @since 2025/12/3 14:00
 */
public class ThreadLocalUtils {

  private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

  public static void set(Map<String, Object> param) {
    THREAD_LOCAL.set(param);
  }

  public static void remove() {
    THREAD_LOCAL.remove();
  }

  public static Object get(String key) {
    return THREAD_LOCAL.get().get(key);
  }
}
