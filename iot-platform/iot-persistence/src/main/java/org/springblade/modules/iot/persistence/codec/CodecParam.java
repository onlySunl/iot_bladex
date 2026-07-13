

package org.springblade.modules.iot.persistence.codec;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 请求参数实体 @Author gitee.com/NexIoT
 *
 * @since 2023/11/23 9:10
 */
@Data
@AllArgsConstructor
public class CodecParam {

  /** 唯一编号 */
  private String codeKey;

  /** 编解码内容 */
  private Object codeBody;
}
