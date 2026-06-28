package org.springblade.modules.iot.dahua.lib.structure;


/**
 * @author 251823
 * @description CLIENT_GetRtscGlobalParam 接口输出参数
 * @date 2021/09/28
 */
public class NET_OUT_GET_GLOBAL_PARAMETER extends SdkStructure {
	 /**
     *  结构体大小
     */
    public int              dwSize;
    /**
     *  全局信息
     */
    public GLOBAL_INFO      stuGlobalInfo;

    public NET_OUT_GET_GLOBAL_PARAMETER(){
        this.dwSize = this.size();
    }
}

