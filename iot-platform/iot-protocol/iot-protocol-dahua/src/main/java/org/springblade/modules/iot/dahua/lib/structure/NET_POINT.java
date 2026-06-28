package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 点坐标结构
 */
public class NET_POINT extends SdkStructure {
    public float fX;                     // X坐标
    public float fY;                     // Y坐标
    
    public NET_POINT() {
    }
    
    public NET_POINT(float x, float y) {
        this.fX = x;
        this.fY = y;
    }
}
