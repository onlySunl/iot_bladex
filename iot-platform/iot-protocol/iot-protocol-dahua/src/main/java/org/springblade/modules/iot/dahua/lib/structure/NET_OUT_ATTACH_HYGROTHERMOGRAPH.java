package org.springblade.modules.iot.dahua.lib.structure;


import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/** 
* @author 291189
* @description  接口 CLIENT_AttachHygrothermograph 的输出参数 
* @origin autoTool
* @date 2023/12/02 10:25:56
*/
public class NET_OUT_ATTACH_HYGROTHERMOGRAPH extends SdkStructure {
/** 
/ 此结构体大小,必须赋值
*/
    public			int            dwSize;

public			NET_OUT_ATTACH_HYGROTHERMOGRAPH(){
		this.dwSize=this.size();
}
}

