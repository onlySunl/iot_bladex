package org.springblade.modules.iot.dahua.lib.structure;


/**
 * PTZ 绝对坐标
 *
 * @author ： 47040
 * @since ： Created in 2020/10/26 15:56
 */
public class POSTION extends SdkStructure {
    /**
     * 水平角度
     */
    public int              nHorizontalAngle;
    /**
     * 垂直角度
     */
    public int              nVerticalAngle;
    /**
     * 放大倍数
     */
    public int              nMagnification;
}

