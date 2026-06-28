package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 47081
 * @version 1.0
 * @description 车牌
 * @date 2021/2/22
 */
public class PlateNumber extends SdkStructure {
    public byte[]           plateNumber = new byte[32];
}

