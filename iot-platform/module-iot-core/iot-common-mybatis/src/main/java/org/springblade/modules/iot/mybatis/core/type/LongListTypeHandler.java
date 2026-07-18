package org.springblade.modules.iot.mybatis.core.type;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = true)
public class LongListTypeHandler implements TypeHandler<List<Long>> {

    private static final String SEPARATOR = ",";

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Long> parameters, JdbcType jdbcType) throws SQLException {
        ps.setString(i, CollUtil.join(parameters, SEPARATOR));
    }

    @Override
    public List<Long> getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return convert(value);
    }

    @Override
    public List<Long> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return convert(value);
    }

    @Override
    public List<Long> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return convert(value);
    }

    private List<Long> convert(String value) {
        if (StrUtil.isBlank(value)) {
            return CollUtil.newArrayList();
        }
        return CollUtil.convertToLongArray(StrUtil.split(value, SEPARATOR));
    }
}
