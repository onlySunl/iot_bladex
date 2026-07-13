

package org.springblade.modules.iot.core.protocol.support;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;

/**
 * 编解码支持类
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2023/5/20 19:08
 */
public abstract class ProtocolCodecSupportWrapper {

  protected String str(Object obj) {
    if (null == obj || "null".equalsIgnoreCase(obj + "")) {
      return CharSequenceUtil.EMPTY;
    } else if (obj instanceof String) {
      return obj.toString();
    } else {
      return JSONUtil.toJsonStr(obj);
    }
  }
}
