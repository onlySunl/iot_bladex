package org.springblade.core.tds.model;

import lombok.Data;

/**
 * 字段 VO
 *
 * @author Chill
 */
@Data
public class FieldsVO {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段长度
     */
    private Integer length;

    /**
     * 是否为标签
     */
    private Boolean isTag;
}
