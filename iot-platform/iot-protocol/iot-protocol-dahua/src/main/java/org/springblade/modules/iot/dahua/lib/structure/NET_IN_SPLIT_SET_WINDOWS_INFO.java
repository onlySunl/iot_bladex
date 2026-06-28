package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CIENT_SetSplitWindowsInfo接口输入参数
*/
public class NET_IN_SPLIT_SET_WINDOWS_INFO extends SdkStructure
{
    public int              dwSize;
    /**
     * 画面分割所在的视频输出通道
    */
    public int              nChannel;
    /**
     * 拼接屏ID
    */
    public byte[]           szCompositeID = new byte[64];
    /**
     * 窗口信息,参见结构体定义 {@link NET_BLOCK_COLLECTION}
    */
    public NET_BLOCK_COLLECTION stuInfos = new NET_BLOCK_COLLECTION();

    public NET_IN_SPLIT_SET_WINDOWS_INFO() {
        this.dwSize = this.size();
    }
}

