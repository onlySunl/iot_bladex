package org.springblade.modules.iot.framework.common.pojo;

import lombok.Data;
import java.io.Serializable;

/**
 * PageParam adapter - pagination request parameters.
 */
@Data
public class PageParam implements Serializable {
    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 10;

    private Integer pageNo = PAGE_NO;
    private Integer pageSize = PAGE_SIZE;
}
