

package org.springblade.modules.iot.persistence.entity.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IoTUserVO extends IoTUser {
  /** 账号状态（0正常，1停用） */

  /** 创建者 */

  private List<Long> roleIds;

  

  public IoTUserVO(Long userId) {
    this.id = userId;
  }

  public boolean isAdmin() {
    return identity != null && identity == 0;
  }
}
