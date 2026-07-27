package org.springblade.modules.iot.tds.model;

import lombok.Data;

/**
 * Fields VO
 * Compatibility class for thinglinks migration
 */
@Data
public class FieldsVO {
    private String name;
    private String type;
    private Integer length;
    private Boolean isTag;
}
