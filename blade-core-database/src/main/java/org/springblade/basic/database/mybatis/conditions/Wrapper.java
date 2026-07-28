package org.springblade.basic.database.mybatis.conditions;


import org.springblade.basic.database.mybatis.tkmybatis.SeeyonExample;

public class Wrapper extends SeeyonExample {

    /**
     * 默认exists为true：如果字段不存在就抛出异常
     * 默认notNull为false：如果值为空就不使用该字段的条件
     *
     * @param entityClass
     */
    public Wrapper(Class<?> entityClass) {
        super(entityClass);
    }

    /**
     * 带exists参数的构造方法
     * 默认notNull为false：如果值为空就不使用该字段的条件
     *
     * @param entityClass
     * @param exists      - true时，如果字段不存在就抛出异常，false时，如果不存在就不使用该字段的条件
     */
    public Wrapper(Class<?> entityClass, boolean exists) {
        super(entityClass, exists);
    }

    /**
     * 带exists、notNull参数的构造方法
     *
     * @param entityClass
     * @param exists      - true时，如果字段不存在就抛出异常，false时，如果不存在就不使用该字段的条件
     * @param notNull     - true时，如果值为空就会抛出异常，false时，如果为空就不使用该字段的条件
     */
    public Wrapper(Class<?> entityClass, boolean exists, boolean notNull) {
        super(entityClass, exists, notNull);
    }

}
