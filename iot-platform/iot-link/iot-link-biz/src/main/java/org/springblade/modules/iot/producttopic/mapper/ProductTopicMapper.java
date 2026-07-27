package org.springblade.modules.iot.producttopic.mapper;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.producttopic.entity.ProductTopic;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * 产品Topic信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet] 
 */
@Mapper
public interface ProductTopicMapper extends BladeMapper<ProductTopic> {

}

