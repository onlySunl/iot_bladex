package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.callback.securityCheck.fNotifyPopulationStatisticsInfo;
import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
import com.sun.jna.Pointer;

/**
 * @author ： 291189
 * @since ： Created in 2021/7/5
 *接口 CLIENT_AttachPopulationStatistics 的输入参数
 */
public class NET_IN_ATTACH_GATE_POPULATION_STATISTICS_INFO extends SdkStructure {
    public int              dwSize;                               // 结构体大小
    public fNotifyPopulationStatisticsInfo cbNotifyPopulationStatisticsInfo; // 回调函数,有人数变化信息时,回调给上层
    public Pointer          dwUser;                               // 用户自定义参数

    public NET_IN_ATTACH_GATE_POPULATION_STATISTICS_INFO() {
        this.dwSize=this.size();
    }
}

