package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;


/**
 * @author 47081
 * @version 1.0
 * @description 用户的开门时间段
 * @date 2021/2/8
 */
public class USER_TIME_SECTION extends SdkStructure {
    public byte[]           userTimeSections = new byte[20];
}

