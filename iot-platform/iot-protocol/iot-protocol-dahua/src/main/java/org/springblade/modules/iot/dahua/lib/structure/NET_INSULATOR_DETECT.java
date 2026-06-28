package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/** 
* @author 291189
* @description  绝缘子检测结果 
* @date 2022/06/28 19:44:56
*/
public class NET_INSULATOR_DETECT extends SdkStructure {
/** 
绝缘子状态 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_INSULATOR_STATE}
*/
    public			int            emInsulatorState;
/** 
包围盒
*/
    public NET_RECT         stuBoundingBox = new NET_RECT();

public			NET_INSULATOR_DETECT(){
}
}

