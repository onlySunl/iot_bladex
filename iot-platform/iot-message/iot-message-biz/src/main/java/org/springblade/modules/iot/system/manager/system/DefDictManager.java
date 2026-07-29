package org.springblade.modules.iot.system.manager.system;

import com.mqttsnet.basic.base.manager.SuperManager;
import org.springblade.modules.iot.model.vo.result.Option;
import org.springblade.modules.iot.system.entity.system.DefDict;
import org.springblade.modules.iot.system.vo.result.system.DefDictItemResultVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 字典管理
 *
 * @author mqttsnet
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [mqttsnet] [初始创建]
 */
public interface DefDictManager extends SuperManager<DefDict> {
    Map<Serializable, DefDict> findByIds(Set<Serializable> dictKeys);

    void syncEnumToDict(Map<Option, List<Option>> ennumMap);

    /**
     * 根据字典key查询系统默认的字典条目
     *
     * @param dictKeys 字典key
     * @return key： 字典key  value: item list
     */
    Map<String, List<DefDictItemResultVO>> findDictMapItemListByKey(List<String> dictKeys);

    /**
     * 删除字典条目
     *
     * @param idList idList
     * @return boolean
     * @author mqttsnet
     * @date 2022/4/18 1:34 PM
     * @create [2022/4/18 1:34 PM ] [mqttsnet] [初始创建]
     */
    boolean removeItemByIds(Collection<Long> idList);
}
