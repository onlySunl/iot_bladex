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
 * 场景联动 实体
 *
 * @author pmc
 */
@Data
@TableName("iot_scene_linkage")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SceneLinkage对象")
public class SceneLinkage extends CustomBaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 场景名称
	 */
	@TableField(value = "scene_name")
	@AutoColumn(comment = "场景名称", length = 128, defaultValueType = DefaultValueEnum.NULL)
	private String sceneName;

	/**
	 * 场景描述
	 */
	@TableField(value = "scene_description")
	@AutoColumn(comment = "场景描述", length = 512, defaultValueType = DefaultValueEnum.NULL)
	private String sceneDescription;

	/**
	 * 触发类型: DEVICE-设备触发, TIMER-定时触发, MANUAL-手动触发
	 */
	@TableField(value = "trigger_type")
	@AutoColumn(comment = "触发类型", length = 32, defaultValueType = DefaultValueEnum.NULL)
	private String triggerType;

	/**
	 * 触发配置（JSON）
	 */
	@TableField(value = "trigger_config")
	@AutoColumn(comment = "触发配置", defaultValueType = DefaultValueEnum.NULL)
	private String triggerConfig;

	/**
	 * 执行动作配置（JSON）
	 */
	@TableField(value = "action_config")
	@AutoColumn(comment = "执行动作配置", defaultValueType = DefaultValueEnum.NULL)
	private String actionConfig;


}
