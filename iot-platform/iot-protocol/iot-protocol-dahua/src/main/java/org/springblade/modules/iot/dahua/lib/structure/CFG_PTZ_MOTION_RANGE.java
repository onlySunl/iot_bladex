package org.springblade.modules.iot.dahua.lib.structure;/**

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
 * @author 47081
 * @descriptio
 * @date 2020/11/9
 * @version 1.0
 */


/**
 * @author 47081
 * @version 1.0
 * @description 云台转动角度范围，单位：度
 * @date 2020/11/9
 */
public class CFG_PTZ_MOTION_RANGE extends SdkStructure {
    /**
     * 水平角度范围最小值,单位:度
     */
    public int              nHorizontalAngleMin;
    /**
     * 水平角度范围最大值,单位:度
     */
    public int              nHorizontalAngleMax;
    /**
     * 垂直角度范围最小值,单位:度
     */
    public int              nVerticalAngleMin;
    /**
     * 垂直角度范围最大值,单位:度
     */
    public int              nVerticalAngleMax;
}

