package org.springblade.modules.iot.device.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * SSL test audit context stub - migration compatibility.
 */
@Data
public class SslTestAuditCtx {
    private String auditId;
    private String step;
    private String status;
    private String summary;
    private LocalDateTime auditTime;
}
