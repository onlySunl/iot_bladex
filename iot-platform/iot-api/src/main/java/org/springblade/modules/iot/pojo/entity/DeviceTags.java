package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.Index;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

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

	private static final long serialVersionUID = 1L;

	@Schema(description = "设备ID")
	@Index
	private Long deviceId;

	@Schema(description = "标签Key")
	private String tagKey;

	@Schema(description = "标签Value")
	private String tagValue;

	@Schema(description = "标签名称")
	private String tagName;

	@Schema(description = "标签备注")
	private String tagRemark;

}
