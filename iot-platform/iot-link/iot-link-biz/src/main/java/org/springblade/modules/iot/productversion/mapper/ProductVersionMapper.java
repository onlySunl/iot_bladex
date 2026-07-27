package org.springblade.modules.iot.productversion.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.productversion.entity.ProductVersion;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品物模型版本快照 Mapper。
 *
 * @author mqttsnet
 * @see ProductVersion
 */
@Mapper
public interface ProductVersionMapper extends BladeMapper<ProductVersion> {
}
