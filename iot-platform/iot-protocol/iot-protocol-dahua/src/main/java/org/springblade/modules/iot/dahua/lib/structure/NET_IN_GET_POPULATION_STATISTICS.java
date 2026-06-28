package org.springblade.modules.iot.dahua.lib.structure;

/**
 * @author ： 291189
 * @since ： Created in 2021/7/5 10:06
 * CLIENT_GetPopulationStatistics 接口输入参数
 */
public class NET_IN_GET_POPULATION_STATISTICS extends SdkStructure {
    public  int             dwSize;                               // 结构体大小

    public NET_IN_GET_POPULATION_STATISTICS() {
        this.dwSize=this.size();
    }
}

