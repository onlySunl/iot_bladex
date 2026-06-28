package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 291189
 * @version 1.0
 * @description 预置点的坐标和放大倍数
 * @date 2022/2/16 9:33
 */
public class NET_PRESET_POSITION extends SdkStructure {
    public    int           nHorizontal;                          // 水平坐标
    public  int             nVertical;                            // 垂直坐标
    public  int             nMagnification;                       // 放大倍数
}

