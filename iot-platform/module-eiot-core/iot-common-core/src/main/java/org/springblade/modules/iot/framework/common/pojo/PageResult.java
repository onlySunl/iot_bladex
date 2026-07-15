package org.springblade.modules.iot.framework.common.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * PageResult adapter - wraps IPage for compatibility.
 */
@Data
public class PageResult<T> implements Serializable {

    private List<T> list;
    private Long total;

    public PageResult() {
        this.list = new ArrayList<>();
        this.total = 0L;
    }

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public static <T> PageResult<T> of(List<T> list, Long total) {
        return new PageResult<>(list, total);
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(new ArrayList<>(), 0L);
    }
}
