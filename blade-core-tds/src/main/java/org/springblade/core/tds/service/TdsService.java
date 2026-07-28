package org.springblade.core.tds.service;

import org.springblade.core.tds.model.SuperTableDTO;

import java.util.List;
import java.util.Map;

/**
 * TDS 服务接口
 *
 * @author Chill
 */
public interface TdsService {

    /**
     * 创建超级表
     */
    void createSuperTable(SuperTableDTO superTable);

    /**
     * 创建子表
     */
    void createSubTable(String superTableName, String subTableName, Map<String, Object> tags);

    /**
     * 插入数据
     */
    void insertData(String tableName, Map<String, Object> data);

    /**
     * 查询数据
     */
    List<Map<String, Object>> queryData(String sql, Object[] args);

    /**
     * 删除超级表
     */
    void dropSuperTable(String tableName);
}
