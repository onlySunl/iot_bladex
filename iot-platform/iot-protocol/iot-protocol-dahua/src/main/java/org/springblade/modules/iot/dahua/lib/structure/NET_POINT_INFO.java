package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;


/**
 *
 * 
 * @author ： 260611
 * @since ： Created in 2021/10/19 19:35
 */
public class NET_POINT_INFO extends SdkStructure {
    /**
     *  主相机标定点
     */
    public DH_POINT stuMasterPoint = new DH_POINT();
    /**
     *  从相机(球机)标定点
     */
    public DH_POINT stuSlavePoint = new DH_POINT();
    /**
     *  保留字段
     */
    public byte             byReserved[] = new byte[256];
}

