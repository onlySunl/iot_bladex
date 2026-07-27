package org.springblade.common.tds;

import lombok.Data;
import java.util.List;

/**
 * TDengine 超级表 DTO
 */
@Data
public class SuperTableDTO {
    private String tableName;
    private String database;
    private List<Fields> columns;
    private List<Fields> tags;
}
