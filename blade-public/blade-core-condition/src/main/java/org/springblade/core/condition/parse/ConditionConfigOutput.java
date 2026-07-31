package org.springblade.core.condition.parse;

import org.springblade.core.condition.model.dto.ConditionOutputParam;

/**
 * 条件配置输出接口
 **/
public interface ConditionConfigOutput<T> {

    /**
     * 获取对象内容
     *
     * @param param 条件输出所需参数
     */
    T getContent(ConditionOutputParam param);
}
