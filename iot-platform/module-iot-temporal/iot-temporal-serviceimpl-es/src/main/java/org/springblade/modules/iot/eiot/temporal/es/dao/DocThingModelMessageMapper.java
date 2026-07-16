package org.springblade.modules.iot.temporal.es.dao;

import org.springblade.modules.iot.temporal.es.document.DocThingModelMessage;
import org.dromara.easyes.core.kernel.BaseEsMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface DocThingModelMessageMapper extends BaseEsMapper<DocThingModelMessage> {
}
