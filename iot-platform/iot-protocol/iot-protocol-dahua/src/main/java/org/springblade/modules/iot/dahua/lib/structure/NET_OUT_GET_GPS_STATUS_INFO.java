package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;



/**
 * @author 421657
 * @description CLIENT_GetGpsStatus 接口出参
 * @origin autoTool
 * @date 2023/09/27 10:21:43
 */
public class NET_OUT_GET_GPS_STATUS_INFO extends SdkStructure {
    /**
     * / 此结构体大小,必须赋值
     */
    public int              dwSize;
    /**
     * / GPS状态
     */
    public NET_GPS_STATUS_INFO stuGPSStatus = new NET_GPS_STATUS_INFO();

    public NET_OUT_GET_GPS_STATUS_INFO() {
        this.dwSize = this.size();
    }
}

