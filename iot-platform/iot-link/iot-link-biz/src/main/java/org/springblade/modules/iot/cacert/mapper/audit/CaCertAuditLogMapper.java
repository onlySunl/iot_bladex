package org.springblade.modules.iot.cacert.mapper.audit;

import org.springblade.basic.base.mapper.SuperMapper;
import org.springblade.modules.iot.cacert.entity.audit.CaCertAuditLog;
import org.springframework.stereotype.Repository;

/**
 * CA 证书审计日志 Mapper。
 *
 * @author mqttsnet
 */
@Repository
public interface CaCertAuditLogMapper extends SuperMapper<CaCertAuditLog> {
}
