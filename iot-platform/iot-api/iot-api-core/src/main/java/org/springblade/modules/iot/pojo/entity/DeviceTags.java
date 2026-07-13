package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.Index;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serial;

/**
 * 设备标签 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_device_tags")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DeviceTags对象")
public class DeviceTags extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 设备ID
	 */
	@TableField(value = "device_id")
	@AutoColumn(comment = "设备ID", defaultValueType = DefaultValueEnum.NULL)
	@Index
	private Long deviceId;

	/**
	 * 标签Key
	 */
	@TableField(value = "tag_key")
	@AutoColumn(comment = "标签Key", length = 64, defaultValueType = DefaultValueEnum.NULL)
	private String tagKey;

	/**
	 * 标签Value
	 */
	@TableField(value = "tag_value")
	@AutoColumn(comment = "标签Value", length = 256, defaultValueType = DefaultValueEnum.NULL)
	private String tagValue;

	/**
	 * 标签名称
	 */
	@TableField(value = "tag_name")
	@AutoColumn(comment = "标签名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String tagName;

	/**
	 * 标签备注
	 */
	@TableField(value = "tag_remark")
	@AutoColumn(comment = "标签备注", length = 256, defaultValueType = DefaultValueEnum.NULL)
	private String tagRemark;

}
