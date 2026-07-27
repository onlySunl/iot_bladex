package org.springblade.model.vo;

import cn.hutool.core.collection.CollUtil;
import com.mqttsnet.basic.context.ContextUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mqttsnet
 * @version v1.0
 * @date 2022/7/29 10:04 PM
 * @create [2022/7/29 10:04 PM ] [mqttsnet] [初始创建]
 */
@Data
@Accessors(chain = true)
public class BaseEventVO {
    private Map<String, String> map;

    /**
     * 将线程变量副 暂存到map
     * 适用于：异步调用前
     *
     * @return com.mqttsnet.thinglinks.model.vo.BaseEventVO
     * @author mqttsnet
     * @date 2022/7/29 11:12 PM
     * @create [2022/7/29 11:12 PM ] [mqttsnet] [初始创建]
     */
    public BaseEventVO copy() {
        if (map == null) {
            map = new HashMap<>();
        }
        map.clear();
        map.putAll(ContextUtil.getLocalMap());
        return this;
    }

    /**
     * 将map写入线程变量
     * 适用于：异步执行一开始
     *
     * @return com.mqttsnet.thinglinks.model.vo.BaseEventVO
     * @author mqttsnet
     * @date 2022/7/29 11:12 PM
     * @create [2022/7/29 11:12 PM ] [mqttsnet] [初始创建]
     */
    public BaseEventVO write() {
        if (CollUtil.isNotEmpty(map)) {
            ContextUtil.setLocalMap(map);
        }
        return this;
    }

}
