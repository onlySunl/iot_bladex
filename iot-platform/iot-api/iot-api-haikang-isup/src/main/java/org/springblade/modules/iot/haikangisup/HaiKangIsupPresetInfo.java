package org.springblade.modules.iot.haikangisup;

import lombok.Data;

import java.io.Serializable;

/**
 * 海康ISUP预置点信息
 *
 * @author fengcheng
 */
@Data
public class HaiKangIsupPresetInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 预置点编号
	 */
	private int presetIndex;

	/**
	 * 预置点名称
	 */
	private String presetName;

	/**
	 * 是否有效
	 */
	private boolean valid;
}
