package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 291189
 * @version 1.0
 * @description  CLIENT_ModifyVehicleForVehicleRegisterDB 接口输出参数
 * @date 2022/10/22 10:26
 */
public class NET_OUT_MODIFY_VEHICLE_FOR_VEHICLE_REG_DB extends SdkStructure {
    public  int             dwSize;                               // 结构体大小

    public NET_OUT_MODIFY_VEHICLE_FOR_VEHICLE_REG_DB(){
        dwSize=this.size();
    }
}

