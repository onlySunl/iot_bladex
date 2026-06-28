package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;

import org.springblade.modules.iot.dahua.lib.NetSDKLib;

/**
 * @author 251823
 * @description 报警区域列表
 * @date 2022/01/07
 */
public class NET_GETSUMMARY_REGION_LIST_INFO extends SdkStructure {
    /**
     *  区域ID
     */
    public int              nRegionID;
    /**
     *  区域内人数统计值
     */
    public int              nPeopleNum;
    /**
     *  区域坐标个数
     */
    public int              nRegionPointNum;
    /**
     *  区域坐标
     */
    public NET_POINT[] stuRegionPoint = (NET_POINT[]) new NET_POINT().toArray(20);
    /**
     *  保留字节
     */
    public byte[]           szReserved = new byte[1024];
}

