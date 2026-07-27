package org.springblade.modules.iot.productpublishrecord.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.productpublishrecord.entity.ProductPublishRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品发布记录 Mapper。
 *
 * @author mqttsnet
 * @see ProductPublishRecord
 */
@Mapper
public interface ProductPublishRecordMapper extends BladeMapper<ProductPublishRecord> {
}
