package org.springblade.modules.iot.cacert.entity.audit;
import org.springblade.common.entity.CustomBaseEntity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;

/**
 * CA 证书审计日志 ── 记录证书全生命周期操作(导入/颁发/吊销/下载/SSL 测试)。
 *
 * @author mqttsnet
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "ca_cert_audit_log", comment = "CaCertAuditLog table")
public class CaCertAuditLog extends CustomBaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 关联 CA 证书 ID */
    @AutoColumn(value = "ca_id", comment = "关联 CA 证书 ID")
    private Long caId;

    /** CA 证书序列号 */
    @AutoColumn(value = "ca_serial_number", comment = "CA 证书序列号")
    private String caSerialNumber;

    /** 动作类型,见 {@code CaCertAuditTypeEnum} */
    @AutoColumn(value = "type", comment = "动作类型,见 {@code CaCertAuditTypeEnum}")
    private String type;

    /** 详情(JSON 或自由文本) */
    @AutoColumn(value = "detail", comment = "详情(JSON 或自由文本)")
    private String detail;
}
