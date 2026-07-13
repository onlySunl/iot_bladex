package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;

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

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 证书名称
	 */
	@TableField(value = "name")
	@AutoColumn(comment = "证书名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String name;

	/**
	 * 证书类型: CA, DEVICE
	 */
	@TableField(value = "type")
	@AutoColumn(comment = "证书类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
	private String type;

	/**
	 * 证书内容
	 */
	@TableField(value = "cert_data")
	@AutoColumn(comment = "证书内容", defaultValueType = DefaultValueEnum.NULL)
	private String certData;

	/**
	 * 私钥
	 */
	@TableField(value = "private_key")
	@AutoColumn(comment = "私钥", defaultValueType = DefaultValueEnum.NULL)
	private String privateKey;

	/**
	 * 公钥
	 */
	@TableField(value = "public_key")
	@AutoColumn(comment = "公钥", defaultValueType = DefaultValueEnum.NULL)
	private String publicKey;

	/**
	 * 签名
	 */
	@TableField(value = "signature")
	@AutoColumn(comment = "签名", defaultValueType = DefaultValueEnum.NULL)
	private String signature;

	/**
	 * 过期时间
	 */
	@TableField(value = "expire_time")
	@AutoColumn(comment = "过期时间", defaultValueType = DefaultValueEnum.NULL)
	private Long expireTime;

}
