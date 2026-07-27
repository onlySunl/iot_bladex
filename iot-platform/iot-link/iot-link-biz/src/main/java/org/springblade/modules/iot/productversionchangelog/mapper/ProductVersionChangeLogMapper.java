package org.springblade.modules.iot.productversionchangelog.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.productversionchangelog.entity.ProductVersionChangeLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品物模型版本变更日志 Mapper。
 *
 * @author mqttsnet
 * @see ProductVersionChangeLog
 */
@Mapper
public interface ProductVersionChangeLogMapper extends BladeMapper<ProductVersionChangeLog> {
}
