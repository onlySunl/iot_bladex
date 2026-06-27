package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import lombok.Data;

/**
 * @FileName DeviceInfo
 * @Description
 * @Author fengcheng
 * @date 2025-12-05
 **/
@Data
public class HaikangDeviceInfo {

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备序列号
     */
    private String deviceSerial;

    /**
     * 通道个数
     */
    private byte byChanNum;

    /**
     * 第一版本
     */
    private int firstVersion;

    /**
     * 第二版本
     */
    private int secondVersion;

    /**
     * 低版本
     */
    private int lowVersion;

    /**
     * 构建时间
     */
    private String buildTime;

    /**
     * DSP构建时间
     */
    private String DSPBuildDate;
}
