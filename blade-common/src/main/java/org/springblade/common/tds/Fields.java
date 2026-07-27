package org.springblade.common.tds;

import lombok.Data;

/**
 * TDengine 字段
 */
@Data
public class Fields {
    private String name;
    private String type;
    private Integer length;
    private String comment;
}
