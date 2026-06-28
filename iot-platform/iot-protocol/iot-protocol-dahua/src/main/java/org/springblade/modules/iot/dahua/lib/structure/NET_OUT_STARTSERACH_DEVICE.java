package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

public class NET_OUT_STARTSERACH_DEVICE extends SdkStructure {
	/**
	 *  结构体大小
	 */
    public 	int             dwSize;

	public NET_OUT_STARTSERACH_DEVICE()
	    {
	     this.dwSize = this.size();
	    }// 此结构体大小
}

