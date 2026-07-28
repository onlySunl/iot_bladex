package org.springblade.core.tds.model;

import lombok.Data;
import java.util.List;

/**
 * 超级表 DTO
 *
 * @author Chill
 */
@Data
public class SuperTableDTO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列字段
     */
    private List<FieldsVO> columns;

    /**
     * 标签字段
     */
    private List<FieldsVO> tags;
}
