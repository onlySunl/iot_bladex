package org.springblade.modules.iot.base.service.common;

import com.mqttsnet.basic.base.service.SuperService;
import org.springblade.modules.iot.base.entity.common.BaseDict;

/**
 * @author mqttsnet
 * @date 2021/10/10 23:14
 */
public interface BaseDictItemService extends SuperService<Long, BaseDict> {

    /**
     * 检查字典项标识是否可用
     *
     * @param key    标识
     * @param dictId 所属字典id
     * @param id     当前id
     * @return
     */
    boolean checkItemByKey(String key, Long dictId, Long id);


}
