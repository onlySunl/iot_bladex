package org.springblade.modules.iot.productversionchangelog.manager;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.core.boot.request.PageParam;
import org.springblade.modules.iot.productversionchangelog.entity.ProductVersionChangeLog;
import org.springblade.modules.iot.productversionchangelog.vo.query.ProductVersionChangeLogPageQuery;

/**
 * 产品物模型版本变更日志通用业务接口。
 *
 * @author mqttsnet
 * @see ProductVersionChangeLog
 */
public interface ProductVersionChangeLogManager extends SuperManager<ProductVersionChangeLog> {

    /**
     * 分页查询变更日志(按 created_time 倒序)。
     *
     * @param params 分页参数
     * @return {@link IPage} 分页结果
     */
    IPage<ProductVersionChangeLog> getPage(PageParams<ProductVersionChangeLogPageQuery> params);

    /**
     * 按产品标识查询全部变更日志(按 created_time 倒序)。
     *
     * @param productIdentification 产品标识
     * @return {@link List} 变更日志列表
     */
    List<ProductVersionChangeLog> listByProductIdentification(String productIdentification);
}
