package org.springblade.core.condition.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序项
 *
 * @author Chill
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortItem {

    /**
     * 列名
     */
    private String column;

    /**
     * 是否升序
     */
    private Boolean asc;

}
