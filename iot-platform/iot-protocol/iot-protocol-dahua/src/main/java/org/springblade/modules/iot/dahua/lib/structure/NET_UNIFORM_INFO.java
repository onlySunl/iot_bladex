package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/**
 * @author 260611
 * @description 制服相关属性状态信息
 * @date 2023/04/21 16:09:59
 */
public class NET_UNIFORM_INFO extends SdkStructure {
    /**
     * 是否有穿着制服, 0: 未知, 1: 没有, 2: 有, 3:不存在指定颜色制服
     */
    public int              nHasUniform;
    /**
     * 制服颜色 {@link org.springblade.modules.iot.dahua.lib.enumeration.EM_CLOTHES_COLOR}
     */
    public int              emUniformColor;
    /**
     * 预留字节
     */
    public byte[]           szReserved = new byte[24];

    public NET_UNIFORM_INFO() {
    }
}

