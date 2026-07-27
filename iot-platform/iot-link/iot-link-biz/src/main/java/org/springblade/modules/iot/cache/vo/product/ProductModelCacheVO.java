package org.springblade.modules.iot.cache.vo.product;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 产品模型缓存 VO
 */
@Data
public class ProductModelCacheVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String productId;
    private String modelName;
    private String modelType;
    private String modelContent;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
