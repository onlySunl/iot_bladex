package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 多边形顶点结构
 */
public class NET_POLY_POINTS extends SdkStructure {
    public int nPointNum;               // 顶点数量
    public NET_POINT[] stPoints = new NET_POINT[16];  // 顶点数组
    
    public NET_POLY_POINTS() {
        for (int i = 0; i < stPoints.length; i++) {
            stPoints[i] = new NET_POINT();
        }
    }
}
