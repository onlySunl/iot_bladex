package org.springblade.modules.iot.dahua.lib.structure;
import com.sun.jna.Pointer;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_GetSnifferInfo 接口输入参数
*/
public class NET_IN_GET_SNIFFER_INFO extends SdkStructure
{
    public int              dwSize;
    public Pointer          pszNetInterface;

    public NET_IN_GET_SNIFFER_INFO() {
        this.dwSize = this.size();
    }
}

