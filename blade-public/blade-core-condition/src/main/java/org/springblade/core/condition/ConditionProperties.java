package org.springblade.core.condition;

import lombok.Data;
import org.springblade.basic.constant.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.springblade.core.condition.ConditionProperties.PREFIX;

/**
 * 条件组件参数
 **/
@Data
@ConfigurationProperties(prefix = ConditionProperties.PREFIX)
public class ConditionProperties {

    public static final String PREFIX = Constants.PROJECT_PREFIX + ".condition";

    private boolean enable = true;
}
