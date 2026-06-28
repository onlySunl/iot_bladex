package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/** 
* @author 260611
* @description  视频分析物体信息扩展结构体,扩展版本2 
* @date 2022/07/29 11:20:53
*/
public class DH_SIG_CARWAY_INFO_EX extends SdkStructure {
/** 
由车检器产生抓拍信号冗余信息
*/
    public			byte[]         byRedundance = new byte[8];
/** 
保留字段
*/
    public			byte[]         bReserved = new byte[120];

public DH_SIG_CARWAY_INFO_EX(){
}
}

