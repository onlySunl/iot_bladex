
package org.springblade.modules.iot.temporal.es.dao;

import org.springblade.modules.iot.temporal.es.document.DocThingModelMessage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ThingModelMessageRepository extends ElasticsearchRepository<DocThingModelMessage, String> {
}
