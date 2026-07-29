package org.springblade.modules.iot.tds.service;

import org.springblade.core.tds.model.Fields;
import org.springblade.core.tds.model.SuperTableDTO;
import org.springblade.core.tds.model.TableDTO;
import org.springblade.core.tds.model.TagsSelectDTO;
import org.springblade.modules.iot.tds.vo.result.SuperTableDescribeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface TdsService {

    /**
     * 创建数据库
     *
     * @param dataBaseName
     */
    default void createDatabase(@Param("dataBaseName") String dataBaseName) {

    }

    /**
     * 创建超级表
     *
     * @param dataBaseName
     * @param superTableName
     */
    default void createSuperTable(@Param("dataBaseName") String dataBaseName, @Param("superTableName") String superTableName) {

    }

    /**
     * 创建超级表及字段
     *
     * @param superTableDTO
     */
    default void createSuperTableAndColumn(SuperTableDTO superTableDTO) {

    }

    /**
     * 创建子表
     *
     * @param tableDTO
     */
    default void createSubTable(TableDTO tableDTO) {

    }

    /**
     * 删除超级表
     *
     * @param dataBaseName
     * @param superTableName
     */
    default void dropSuperTable(@Param("dataBaseName") String dataBaseName, @Param("superTableName") String superTableName) {

    }

    /**
     * 新增字段
     *
     * @param superTableName
     * @param fields
     */
    default void alterSuperTableColumn(@Param("dataBaseName") String dataBaseName, @Param("superTableName") String superTableName,
                                       @Param("fields") Fields fields) {

    }

    /**
     * 删除字段
     *
     * @param dataBaseName
     * @param superTableName
     * @param fields
     */
    default void dropSuperTableColumn(@Param("dataBaseName") String dataBaseName, @Param("superTableName") String superTableName,
                                      @Param("fields") Fields fields) {

    }

    /**
     * 查询表结构
     *
     * @param dataBaseName
     * @param tableName
     */
    default List<SuperTableDescribeVO> describeSuperOrSubTable(@Param("dataBaseName") String dataBaseName, @Param("tableName") String tableName) {

        return null;
    }

    /**
     * 添加标签
     *
     * @param dataBaseName
     * @param superTableName
     * @param fields
     */
    default void alterSuperTableTag(@Param("dataBaseName") String dataBaseName, @Param("superTableName") String superTableName,
                                    @Param("fields") Fields fields) {

    }

    /**
     * 删除标签
     *
     * @param dataBaseName
     * @param superTableName
     * @param fields
     */
    default void dropSuperTableTag(@Param("dataBaseName") String dataBaseName, @Param("superTableName") String superTableName, @Param("fields") Fields fields) {

    }

    /**
     * 修改标签名
     *
     * @param dataBaseName
     * @param superTableName
     * @param oldName
     * @param newName
     */
    default void alterSuperTableTagRename(@Param("dataBaseName") String dataBaseName, @Param("superTableName") String superTableName,
                                          @Param("oldName") String oldName, @Param("newName") String newName) {

    }

    /**
     * 新增数据
     *
     * @param tableDTO
     */
    default void insertTableData(TableDTO tableDTO) {

    }

    /**
     * 查询最新数据
     *
     * @param tagsSelectDTO
     * @return
     */
    default CompletableFuture<Map<String, Map<String, Object>>> getLastDataByTags(TagsSelectDTO tagsSelectDTO) {
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Retrieves the latest data from the specified table within the given database.
     * If both startTime and endTime are provided, it fetches records within that time range.
     * Otherwise, it retrieves the last recorded data.
     *
     * @param dataBaseName The name of the database.
     * @param tableName    The name of the table.
     * @param startTime    The start time for the query range (can be null).
     * @param endTime      The end time for the query range (can be null).
     * @return A {@link List<Map<String,Object>>} containing the latest data.
     */
    default CompletableFuture<List<Map<String, Object>>> getDataInRangeOrLastRecord(String dataBaseName, String tableName, Long startTime, Long endTime) {
        return CompletableFuture.completedFuture(null);
    }
}
