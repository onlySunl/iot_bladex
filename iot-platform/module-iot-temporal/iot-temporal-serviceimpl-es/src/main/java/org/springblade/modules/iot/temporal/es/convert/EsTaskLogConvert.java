

package org.springblade.modules.iot.temporal.es.convert;



import org.springblade.modules.iot.temporal.es.document.DocRuleLog;
import org.springblade.modules.iot.temporal.es.document.DocTaskLog;
import org.springblade.modules.iot.api.rule.dto.RuleLog;
import org.springblade.modules.iot.api.task.dto.TaskLog;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: EnjoyIot
 * @Date: 2024/12/24 19:02
 * @Version: V1.0
 * @Description:
 */
@Mapper(builder = @Builder(disableBuilder = true))

public interface EsTaskLogConvert {
    EsTaskLogConvert INSTANCE = Mappers.getMapper(EsTaskLogConvert.class);

    TaskLog convert(DocTaskLog o);

    DocTaskLog convertDoc(TaskLog log);
}

