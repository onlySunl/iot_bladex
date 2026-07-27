package org.springblade.modules.iot.producttopic.manager.impl;

import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.iot.producttopic.entity.ProductTopic;
import org.springblade.modules.iot.producttopic.manager.ProductTopicManager;
import org.springblade.modules.iot.producttopic.mapper.ProductTopicMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用业务实现类
 * 产品Topic信息表
 * </p>
 *
 * @author mqttsnet
 * @date 2023-03-14 19:39:59
 * @create [2023-03-14 19:39:59] [mqttsnet] 
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductTopicManagerImpl extends SuperManagerImpl<ProductTopicMapper, ProductTopic> implements ProductTopicManager {

}


