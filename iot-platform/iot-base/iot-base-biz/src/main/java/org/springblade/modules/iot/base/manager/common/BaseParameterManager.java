package org.springblade.modules.iot.base.manager.common;

import com.mqttsnet.basic.base.manager.SuperManager;
import com.mqttsnet.basic.interfaces.echo.LoadService;
import org.springblade.modules.iot.base.entity.common.BaseParameter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通用业务接口
 * 个性参数
 * </p>
 *
 * @author mqttsnet
 * @date 2021-11-08
 */
public interface BaseParameterManager extends SuperManager<BaseParameter>, LoadService {
    /**
     * 根据参数key查参数值
     * <p>
     * 1. 先查询租户自己的参数。
     * 2. 若不存在，则查询系统默认的参数。
     *
     * @param paramsKeys 参数key
     * @return key： 参数key  value: 参数值
     */
    Map<String, String> findParamMapByKey(List<String> paramsKeys);
}
