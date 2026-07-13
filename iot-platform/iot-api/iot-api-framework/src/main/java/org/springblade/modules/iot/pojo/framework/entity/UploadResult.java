

package org.springblade.modules.iot.pojo.framework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/** 上传返回体 @Author Lion Li */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UploadResult {

  /** 文件路径 */
  private String url;

  /** 文件名 */
  private String filename;
}
