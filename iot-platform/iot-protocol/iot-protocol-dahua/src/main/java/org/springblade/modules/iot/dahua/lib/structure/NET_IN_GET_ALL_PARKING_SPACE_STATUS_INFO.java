package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * CLIENT_QueryDevInfo NET_QUERY_GET_ALL_PARKING_SPACE_STATUS 类型接口输出参数
*/
public class NET_IN_GET_ALL_PARKING_SPACE_STATUS_INFO extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;

    public NET_IN_GET_ALL_PARKING_SPACE_STATUS_INFO() {
        this.dwSize = this.size();
    }
}

