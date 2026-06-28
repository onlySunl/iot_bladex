package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;
/**
 * 日期
*/
public class CFG_DATA_TIME extends SdkStructure
{
    /**
     * 年
    */
    public int              dwYear;
    /**
     * 月
    */
    public int              dwMonth;
    /**
     * 日
    */
    public int              dwDay;
    /**
     * 时
    */
    public int              dwHour;
    /**
     * 分
    */
    public int              dwMinute;
    /**
     * 秒
    */
    public int              dwSecond;
    /**
     * 保留字段
    */
    public int[]            dwReserved = new int[2];

    public CFG_DATA_TIME() {
    }
}

