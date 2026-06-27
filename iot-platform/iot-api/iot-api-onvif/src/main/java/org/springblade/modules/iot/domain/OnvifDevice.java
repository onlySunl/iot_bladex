package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import lombok.Data;

import java.util.List;


@Data
public class OnvifDevice {

    /**
     * ip
     */
    private String ip;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * host名称
     */
    private String hostName;

    /**
     * 设备厂商
     */
    private String firm;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 固件版本
     */
    private String firmwareVersion;

    /**
     * 球机多条播放
     */
    private List<String> streamUris;
}
