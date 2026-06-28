package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/**
 * @author 260611
 * @description 设置源边框高亮使能开关输出参数
 * @date 2022/06/22 09:56:20
 */
public class NET_OUT_SPLIT_SET_HIGHLIGHT extends SdkStructure {
    public int              dwSize;

    public NET_OUT_SPLIT_SET_HIGHLIGHT() {
        this.dwSize = this.size();
    }
}

