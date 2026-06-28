package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 矩阵卡信息
 */
public class NET_MATRIX_CARD_INFO extends SdkStructure {
    public int dwSize;
    public byte[] szName = new byte[64];
    public int nInputChannel;
    public int nOutputChannel;
    
    public NET_MATRIX_CARD_INFO() {
        this.dwSize = this.size();
    }
}
