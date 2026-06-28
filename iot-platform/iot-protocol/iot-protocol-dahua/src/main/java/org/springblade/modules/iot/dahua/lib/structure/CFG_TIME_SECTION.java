package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 时间片段结构
 */
public class CFG_TIME_SECTION extends SdkStructure {
    /**
     * 开始时间 小时
     */
    public int nBeginHour;
    /**
     * 开始时间 分钟
     */
    public int nBeginMin;
    /**
     * 开始时间 秒
     */
    public int nBeginSec;
    /**
     * 结束时间 小时
     */
    public int nEndHour;
    /**
     * 结束时间 分钟
     */
    public int nEndMin;
    /**
     * 结束时间 秒
     */
    public int nEndSec;

    public CFG_TIME_SECTION() {
    }
}
