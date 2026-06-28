package org.springblade.modules.iot.haikangisup.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 海康ISUP辅助设备控制枚举
 *
 * @author fengcheng
 */
@Getter
@AllArgsConstructor
public enum HCIsupCameraAuxEnum {

	LIGHT(10, "灯光", "light"),
	WIPER(14, "雨刮", "wiper"),
	FAN(11, "风扇", "fan"),
	HEATER(12, "加热器", "heater"),
	AUX1(15, "辅助设备1", "aux1"),
	AUX2(16, "辅助设备2", "aux2");

	private final int code;
	private final String desc;
	private final String value;

	public static HCIsupCameraAuxEnum fromValue(String value) {
		for (HCIsupCameraAuxEnum enumValue : HCIsupCameraAuxEnum.values()) {
			if (enumValue.getValue().equals(value)) {
				return enumValue;
			}
		}
		return null;
	}
}
