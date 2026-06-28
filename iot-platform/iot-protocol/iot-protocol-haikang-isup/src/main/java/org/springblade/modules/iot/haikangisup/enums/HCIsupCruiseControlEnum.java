package org.springblade.modules.iot.haikangisup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 海康ISUP巡航控制枚举
 *
 * @author fengcheng
 */
@Getter
@AllArgsConstructor
public enum HCIsupCruiseControlEnum {

	ADD_TO_CRUISE(17, "加入巡航序列", "add_to_cruise"),
	REMOVE_FROM_CRUISE(18, "从巡航序列删除", "remove_from_cruise"),
	SET_CRUISE_DWELL(19, "设置巡航停顿时间", "set_cruise_dwell"),
	SET_CRUISE_SPEED(20, "设置巡航速度", "set_cruise_speed"),
	START_CRUISE(21, "开始巡航", "start_cruise"),
	STOP_CRUISE(22, "停止巡航", "stop_cruise"),
	START_AUTO_SCAN(23, "开始自动扫描", "start_auto_scan"),
	STOP_AUTO_SCAN(24, "停止自动扫描", "stop_auto_scan");

	private final int code;
	private final String desc;
	private final String value;

	public static HCIsupCruiseControlEnum fromValue(String value) {
		for (HCIsupCruiseControlEnum enumValue : HCIsupCruiseControlEnum.values()) {
			if (enumValue.getValue().equals(value)) {
				return enumValue;
			}
		}
		return null;
	}
}
