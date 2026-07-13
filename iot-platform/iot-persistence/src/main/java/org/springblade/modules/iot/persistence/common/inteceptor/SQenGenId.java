

package org.springblade.modules.iot.persistence.common.inteceptor;

import cn.hutool.core.lang.Snowflake;
import tk.mybatis.mapper.genid.GenId;

/** 雪花Id生成器 */
public class SQenGenId implements GenId<Long> {

  public static final Snowflake snowflake = new Snowflake();

  @Override
  public Long genId(String s, String s1) {
    return snowflake.nextId();
  }
}
