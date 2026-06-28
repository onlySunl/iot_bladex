package org.springblade.modules.iot.dahua.lib.structure;

import org.springblade.modules.iot.dahua.lib.method.SdkStructure;

/**
 * 扩展时间结构
 */
public class NET_TIME_EX extends SdkStructure {
    public int dwYear;                   // 年
    public int dwMonth;                  // 月
    public int dwDay;                    // 日
    public int dwHour;                   // 时
    public int dwMinute;                // 分
    public int dwSecond;                 // 秒
    public int dwMillisecond;           // 毫秒
    public byte[] byReserved = new byte[2];  // 保留字段
    
    public NET_TIME_EX() {
    }
    
    /**
     * 将时间转换为字符串格式 "yyyy-MM-dd HH:mm:ss"
     * @return 时间字符串
     */
    public String toStringTime() {
        return String.format("%04d-%02d-%02d %02d:%02d:%02d", 
                dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
    }
}
