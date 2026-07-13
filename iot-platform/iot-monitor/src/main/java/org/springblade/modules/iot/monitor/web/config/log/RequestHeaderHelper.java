

package org.springblade.modules.iot.monitor.web.config.log;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/04/19
 */
public class RequestHeaderHelper {

  public static final String AUTHORIZATION = "Authorization";

  public static final Set<String> headers = new HashSet<>();

  static {
    headers.add(AUTHORIZATION);
    headers.add(AUTHORIZATION.toLowerCase());
  }

  public static boolean matchHeader(String key) {
    return headers.contains(key.toLowerCase());
  }
}
