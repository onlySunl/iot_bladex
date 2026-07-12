package org.springblade.modules.iot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.pojo.entity.Certificate;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "CertificateVO")
public class CertificateVO extends Certificate {
	private static final long serialVersionUID = 1L;
}
