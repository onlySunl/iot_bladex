package org.springblade.modules.iot.cacert.mapper.audit;

import org.springblade.core.mp.mapper.BladeMapper;
import org.springblade.modules.iot.cacert.entity.audit.CaCertAuditLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * CA 证书审计日志 Mapper。
 *
 * @author mqttsnet
 */
@Mapper
public interface CaCertAuditLogMapper extends BladeMapper<CaCertAuditLog> {
}
