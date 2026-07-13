

package org.springblade.modules.iot.persistence.consistent;

import cn.hutool.core.util.StrUtil;

public interface ITableShardingStrategy {

  /**
   * @param tableNamePrefix 表前缀名
   * @param value 值
   */
  String generateTableName(String tableNamePrefix, Object value);

  /** 验证tableNamePrefix */
  default void verificationTableNamePrefix(String tableNamePrefix) {
    if (StrUtil.isBlank(tableNamePrefix)) {
      throw new RuntimeException("tableNamePrefix is null");
    }
  }
}
