import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
 package org.springblade.modules.iot.dahua.lib.structure;



public class NET_CFG_VSP_LXSJ_NOPLATE extends SdkStructure {
/** 使能*/
    public			int            bEnable;
/** 无牌车内容*/
    public			byte[]         szText = new byte[128];
/** 预留*/
    public			byte[]         byReserved = new byte[380];
}

