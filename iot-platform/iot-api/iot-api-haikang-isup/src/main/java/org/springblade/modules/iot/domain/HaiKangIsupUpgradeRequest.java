package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import lombok.Data;

/**
 * 海康ISUP设备升级请求参数
 */
@Data
@TableName("")
@Table(value = "", comment = "")
public class HaiKangIsupUpgradeRequest {

    /**
     * 设备ID
     */
    private Long deviceId;

    /**
     * FTP服务器IP地址
     */
    private String ftpServerIp;

    /**
     * FTP服务器端口号，默认21
     */
    private Integer ftpServerPort = 21;

    /**
     * FTP用户名
     */
    private String ftpAccount;

    /**
     * FTP密码
     */
    private String ftpPassword;

    /**
     * 升级文件名
     */
    private String fileName;

    /**
     * 待升级设备关联的通道号
     */
    private Integer channel = 1;
}
