package org.springblade.modules.iot.tds.model;

import lombok.Data;
import java.util.List;

/**
 * Super Table DTO
 * Compatibility class for IoT migration
 */
@Data
public class SuperTableDTO {
    private String tableName;
    private List<FieldsVO> columns;
    private List<FieldsVO> tags;
}
