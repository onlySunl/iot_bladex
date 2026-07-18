
package org.springblade.modules.iot.temporal.es.dao;

import org.springblade.modules.iot.temporal.es.document.DocTaskLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TaskLogRepository extends ElasticsearchRepository<DocTaskLog, String> {

    void deleteByTaskId(Long taskId);

    Page<DocTaskLog> findByTaskIdOrderByLogAtDesc(Long taskId, Pageable pageable);

}
