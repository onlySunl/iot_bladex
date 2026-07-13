package org.springblade.modules.iot.common.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 通用枚举TypeHandler，等价于JPA @Enumerated(EnumType.STRING)
 * 数据库存储 enum.name()
 * @param <T> 任意枚举类
 */
@MappedTypes(Enum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class UniversalEnumTypeHandler<T extends Enum<T>> extends BaseTypeHandler<T> {

    private final Class<T> enumClass;

    // mybatis通过反射调用构造器传入实际枚举Class
    public UniversalEnumTypeHandler(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return parseEnum(value);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return parseEnum(value);
    }

    @Override
    public T getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return parseEnum(value);
    }

    /**
     * 根据name字符串转换成枚举，忽略异常，返回null
     */
    private T parseEnum(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            // 数据库脏数据返回null，也可以抛出异常按需选择
            return null;
        }
    }
}