package org.springblade.common.tds;

import lombok.Data;

/**
 * TDengine 标签查询 DTO
 */
@Data
public class TagsSelectDTO {
    private String tagName;
    private String tagValue;
}
