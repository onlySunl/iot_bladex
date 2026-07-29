package org.springblade.modules.iot.system.service.system;

import com.mqttsnet.basic.base.service.SuperService;
import org.springblade.modules.iot.system.entity.system.DefDict;

/**
 * <p>
 * 业务接口
 * 字典
 * </p>
 *
 * @author mqttsnet
 * @date 2021-10-04
 */
public interface DefDictItemService extends SuperService<Long, DefDict> {

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
