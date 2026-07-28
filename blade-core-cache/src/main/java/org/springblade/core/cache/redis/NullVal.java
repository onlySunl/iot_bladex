package org.springblade.core.cache.redis;

import java.io.Serial;
import java.io.Serializable;

/**
 * 空值
 * 解决缓存穿透
 *
 * @author mqttsnet
 * @date 2023/9/17 2:30 下午
 */
public class NullVal implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
