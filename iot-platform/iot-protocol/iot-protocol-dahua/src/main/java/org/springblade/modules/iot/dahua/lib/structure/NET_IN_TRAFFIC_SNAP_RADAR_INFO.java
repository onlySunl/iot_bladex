package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 251823
 * @description 智能交通外接雷达信息入参
 * @date 2020/12/14
 */
public class NET_IN_TRAFFIC_SNAP_RADAR_INFO extends SdkStructure {
	/**
	 * 结构体大小
	 * */
    public int              dwSize;
	/**
	 * 通道号
	 * */ 
    public int              nChannel;

	public NET_IN_TRAFFIC_SNAP_RADAR_INFO() {
	  this.dwSize = this.size();
	}
}

