package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 矩阵卡列表
 */
public class NET_MATRIX_CARD_LIST extends SdkStructure {
    public int dwSize;
    public int nChannelCount;
    public NET_MATRIX_CARD_INFO[] pstuCards;
    public int nMaxCardCount;
    public int nRetCardCount;
    
    public NET_MATRIX_CARD_LIST() {
        this.dwSize = this.size();
    }
}
