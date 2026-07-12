package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 设备证书 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_certificate")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Certificate对象")
public class Certificate extends CustomBaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "证书名称")
	private String name;

	@Schema(description = "证书类型: CA, DEVICE")
	private String type;

	@Schema(description = "证书内容")
	private String certData;

	@Schema(description = "私钥")
	private String privateKey;

	@Schema(description = "公钥")
	private String publicKey;

	@Schema(description = "签名")
	private String signature;

	@Schema(description = "过期时间")
	private Long expireTime;

}
