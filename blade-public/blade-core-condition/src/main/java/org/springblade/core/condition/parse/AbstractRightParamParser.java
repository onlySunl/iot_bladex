package org.springblade.core.condition.parse;

/**
 * 条件右值解析器抽象类
 **/
public abstract class AbstractRightParamParser implements RightParamParser {

    @Override
    public int getOrder() {
        return 0;
    }
}
