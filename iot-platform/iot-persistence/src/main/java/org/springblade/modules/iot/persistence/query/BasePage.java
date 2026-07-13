

package org.springblade.modules.iot.persistence.query;

import lombok.Data;

/**
 * @Author gitee.com/NexIoT
 *
 * @since 2018年12月17日 上午10:59
 */
@Data
public class BasePage {

  private Integer page = 1;
  private Integer size = 10;
  private Integer pageNum = 1;
  private Integer pageSize = 10;
  // 特色场景使用
  private Integer halfSize;

  public Integer getHalfSize() {
    return pageSize / 2;
  }
}
