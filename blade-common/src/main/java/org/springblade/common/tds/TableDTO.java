package org.springblade.common.tds;

import lombok.Data;
import java.util.List;

/**
 * TDengine 表 DTO
 */
@Data
public class TableDTO {
    private String tableName;
    private String database;
    private List<Fields> columns;
    private List<TagsSelectDTO> tags;
}
