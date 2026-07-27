package org.springblade.modules.iot.cacert.mapper.audit;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.cacert.entity.audit.CaCertAuditLog;
import org.springframework.stereotype.Repository;

/**
 * CA 证书审计日志 Mapper。
 *
 * @author mqttsnet
 */
@Repository
public interface CaCertAuditLogMapper extends BladeMapper<CaCertAuditLog> {
}
