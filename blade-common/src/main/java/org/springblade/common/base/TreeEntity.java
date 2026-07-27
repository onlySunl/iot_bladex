package org.springblade.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树形实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TreeEntity extends Entity {
    private Long parentId;
    private Integer sortOrder;
}
