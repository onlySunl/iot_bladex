package org.springblade.core.tds.service;

import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tds.model.FieldsVO;
import org.springblade.core.tds.model.SuperTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TDS 服务实现
 *
 * @author Chill
 */
@Slf4j
@Service
public class TdsServiceImpl implements TdsService {

    @Autowired
    @Qualifier("tdsJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createSuperTable(SuperTableDTO superTable) {
        StringBuilder sql = new StringBuilder("CREATE STABLE IF NOT EXISTS ");
        sql.append(superTable.getTableName()).append(" (ts TIMESTAMP");

        // 添加列字段
        if (superTable.getColumns() != null) {
            for (FieldsVO field : superTable.getColumns()) {
                sql.append(", ").append(field.getName()).append(" ").append(field.getType());
            }
        }
        sql.append(") TAGS (");

        // 添加标签字段
        if (superTable.getTags() != null) {
            String tags = superTable.getTags().stream()
                .map(tag -> tag.getName() + " " + tag.getType())
                .collect(Collectors.joining(", "));
            sql.append(tags);
        }
        sql.append(")");

        log.info("创建超级表: {}", sql);
        jdbcTemplate.execute(sql.toString());
    }

    @Override
    public void createSubTable(String superTableName, String subTableName, Map<String, Object> tags) {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sql.append(subTableName).append(" USING ").append(superTableName);
        sql.append(" TAGS (");

        String tagValues = tags.values().stream()
            .map(v -> v instanceof String ? "'" + v + "'" : v.toString())
            .collect(Collectors.joining(", "));
        sql.append(tagValues).append(")");

        log.info("创建子表: {}", sql);
        jdbcTemplate.execute(sql.toString());
    }

    @Override
    public void insertData(String tableName, Map<String, Object> data) {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName).append(" VALUES (");

        String values = data.values().stream()
            .map(v -> v instanceof String ? "'" + v + "'" : v.toString())
            .collect(Collectors.joining(", "));
        sql.append(values).append(")");

        log.debug("插入数据: {}", sql);
        jdbcTemplate.execute(sql.toString());
    }

    @Override
    public List<Map<String, Object>> queryData(String sql, Object[] args) {
        log.debug("查询数据: {}", sql);
        return jdbcTemplate.queryForList(sql, args);
    }

    @Override
    public void dropSuperTable(String tableName) {
        String sql = "DROP STABLE IF EXISTS " + tableName;
        log.info("删除超级表: {}", sql);
        jdbcTemplate.execute(sql);
    }
}
