package org.springblade.modules.iot.productversion.mapper;

import org.springblade.core.database.mybatis.BladeMapper;
import org.springblade.modules.iot.productversion.entity.ProductVersion;
import org.springframework.stereotype.Repository;

/**
 * 产品物模型版本快照 Mapper。
 *
 * @author mqttsnet
 * @see ProductVersion
 */
@Repository
public interface ProductVersionMapper extends BladeMapper<ProductVersion> {
}
