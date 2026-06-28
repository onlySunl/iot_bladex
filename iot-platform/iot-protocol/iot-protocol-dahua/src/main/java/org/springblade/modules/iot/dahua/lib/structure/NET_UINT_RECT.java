package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * 区域；各边距按整长8192的比例
*/
public class NET_UINT_RECT extends SdkStructure
{
    public int              nLeft;
    public int              nTop;
    public int              nRight;
    public int              nBottom;

    public NET_UINT_RECT() {
    }
}

