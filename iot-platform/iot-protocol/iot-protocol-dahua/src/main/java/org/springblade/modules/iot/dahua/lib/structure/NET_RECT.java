package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.SdkStructure;

/**
 * 矩形区域结构
 */
public class NET_RECT extends SdkStructure {
    public int nLeft;                   // 左上角X
    public int nTop;                    // 左上角Y
    public int nRight;                  // 右下角X
    public int nBottom;                 // 右下角Y
    
    public NET_RECT() {
    }
    
    public NET_RECT(int left, int top, int right, int bottom) {
        this.nLeft = left;
        this.nTop = top;
        this.nRight = right;
        this.nBottom = bottom;
    }
}
