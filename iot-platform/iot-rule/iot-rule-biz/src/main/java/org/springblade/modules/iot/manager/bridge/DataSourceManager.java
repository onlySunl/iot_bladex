package org.springblade.modules.iot.manager.bridge;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.iot.entity.bridge.DataSource;
import org.springblade.modules.iot.vo.query.bridge.DataSourcePageQuery;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 数据桥接-数据源
 * </p>
 *
 * @author mqttsnet
 * @date 2026-04-28
 */
public interface DataSourceManager extends BladeMapper<DataSource> {

    /**
     * 按查询条件取数据源列表
     */
    List<DataSource> getDataSourceList(DataSourcePageQuery query);

    /**
     * 按业务编码查唯一数据源
     */
    DataSource getByCode(String dataSourceCode);
}
