package org.springblade.modules.iot.dahua.lib.structure;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
import org.springblade.modules.iot.dahua.lib.NetSDKLib;
/**
 * CLIENT_RadiometryGetRandomRegionTemper 入参
*/
public class NET_IN_RADIOMETRY_RANDOM_REGION_TEMPER extends SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 通道号
    */
    public int              nChannel;
    /**
     * 有效坐标个数
    */
    public int              nPointNum;
    /**
     * 测温区域的坐标, 8192坐标系,参见结构体定义 {@link NET_POINT}
    */
    public NET_POINT[] stuPolygon = new NET_POINT[8];

    public NET_IN_RADIOMETRY_RANDOM_REGION_TEMPER() {
        this.dwSize = this.size();
        for(int i = 0; i < stuPolygon.length; i++){
            stuPolygon[i] = new NET_POINT();
        }
    }
}

