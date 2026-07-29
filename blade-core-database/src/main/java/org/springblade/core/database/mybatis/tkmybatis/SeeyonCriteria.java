package org.springblade.core.database.mybatis.tkmybatis;

import com.alibaba.druid.DbType;
import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.utils.StringUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

public class SeeyonCriteria extends Example.Criteria {
    protected SeeyonCriteria(Map<String, EntityColumn> propertyMap, boolean exists, boolean notNull) {
        super(propertyMap, exists, notNull);
    }

    @Override
    public Example.Criteria andEqualTo(String property, Object value) {
        // 数据库兼容处理，oracle数据库(达梦和pg不会自动把""转为null)，EQ操作如果值为""，全部转换为is null
        if (ContextUtil.getDataBase() == DbType.oracle.name()
            && propertyMap.containsKey(property)
            && propertyMap.get(property).getJavaType().isAssignableFrom(String.class)
            && StringUtils.isEmpty(value + "")) {
            return super.andIsNull(property);
        } else {
            return super.andEqualTo(property, value);
        }
    }

    @Override
    public Example.Criteria andNotEqualTo(String property, Object value) {
        // 数据库兼容处理，oracle数据库(达梦和pg不会自动把""转为null)，EQ操作如果值为""，全部转换为is null
        if (ContextUtil.getDataBase() == DbType.oracle.name()
            && propertyMap.containsKey(property)
            && propertyMap.get(property).getJavaType().isAssignableFrom(String.class)
            && StringUtils.isEmpty(value + "")) {
            return super.andIsNotNull(property);
        } else {
            return super.andNotEqualTo(property, value);
        }
    }

    @Override
    public Example.Criteria orEqualTo(String property, Object value) {
        // 数据库兼容处理，oracle数据库(达梦和pg不会自动把""转为null)，EQ操作如果值为""，全部转换为is null
        if (ContextUtil.getDataBase() == DbType.oracle.name()
            && propertyMap.containsKey(property)
            && propertyMap.get(property).getJavaType().isAssignableFrom(String.class)
            && StringUtils.isEmpty(value + "")) {
            return super.orIsNull(property);
        } else {
            return super.orEqualTo(property, value);
        }
    }

    @Override
    public Example.Criteria orNotEqualTo(String property, Object value) {
        // 数据库兼容处理，oracle数据库(达梦和pg不会自动把""转为null)，EQ操作如果值为""，全部转换为is null
        if (ContextUtil.getDataBase() == DbType.oracle.name()
            && propertyMap.containsKey(property)
            && propertyMap.get(property).getJavaType().isAssignableFrom(String.class)
            && StringUtils.isEmpty(value + "")) {
            return super.orIsNotNull(property);
        } else {
            return super.orNotEqualTo(property, value);
        }
    }

}
