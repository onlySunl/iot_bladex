package org.springblade.common.base.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页参数
 */
@Data
@NoArgsConstructor
public class PageParams<T> {
    private int current = 1;
    private int size = 10;
    private T model;
    
    public PageParams(int current, int size) {
        this.current = current;
        this.size = size;
    }
    
    public void setModel(T model) {
        this.model = model;
    }
}
