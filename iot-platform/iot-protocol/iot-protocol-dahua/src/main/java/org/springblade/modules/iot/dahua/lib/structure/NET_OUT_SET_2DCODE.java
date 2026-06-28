package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import com.sun.jna.Pointer;

/**
 * @author 47081
 * @version 1.0
 * @description {@link NetSDKLib#CLIENT_Set2DCode(LLong, Pointer, Pointer, int)}的出参
 * @date 2020/9/10
 */
public class NET_OUT_SET_2DCODE extends SdkStructure {
    /**
     * 结构体大小
     */
    public int              dwSize;

    public NET_OUT_SET_2DCODE() {
        this.dwSize = this.size();
    }
}

