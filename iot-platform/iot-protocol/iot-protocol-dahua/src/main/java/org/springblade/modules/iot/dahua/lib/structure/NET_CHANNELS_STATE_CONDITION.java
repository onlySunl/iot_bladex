package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/** 
* @author 291189
* @description  获取通道状态查询条件 
* @date 2022/10/09 11:22:21
*/
public class NET_CHANNELS_STATE_CONDITION extends SdkStructure {
/** 
通道类型 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_CHANNELS_STATE_TYPE}
*/
    public			int            emType;
/** 
保留字节
*/
    public			byte[]         byReserved = new byte[1020];

public NET_CHANNELS_STATE_CONDITION(){
}
}

