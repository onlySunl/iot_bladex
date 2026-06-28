package org.springblade.modules.iot.haikangisup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 海康ISUP云台预置点控制枚举
 *
 * @author fengcheng
 */
@Getter
@AllArgsConstructor
public enum HCIsupPresetControlEnum {

	SET_PRESET(1, "设置预置点", "set_preset"),
	CLEAR_PRESET(2, "删除预置点", "clear_preset"),
	GOTO_PRESET(3, "调用预置点", "goto_preset");

	private final int code;
	private final String desc;
	private final String value;

	public static HCIsupPresetControlEnum fromValue(String value) {
		for (HCIsupPresetControlEnum enumValue : HCIsupPresetControlEnum.values()) {
			if (enumValue.getValue().equals(value)) {
				return enumValue;
			}
		}
		return null;
	}
}
