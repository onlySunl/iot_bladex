package org.springblade.modules.iot.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 场景联动 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_scene_linkage")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SceneLinkage对象")
public class SceneLinkage extends CustomBaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "场景名称")
	private String sceneName;

	@Schema(description = "场景描述")
	private String sceneDescription;

	@Schema(description = "触发类型: DEVICE-设备触发, TIMER-定时触发, MANUAL-手动触发")
	private String triggerType;

	@Schema(description = "触发配置（JSON）")
	private String triggerConfig;

	@Schema(description = "执行动作配置（JSON）")
	private String actionConfig;

	@Schema(description = "状态: 0-停用, 1-启用")
	private Integer status;

}
