package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import org.springblade.modules.iot.domain.QsDevice;
import lombok.Data;

import java.io.Serializable;

@Data
public class Gb28181PlatformPlay implements Serializable {
    private static final long serialVersionUID = 1L;

    private QsDevice qsDevice;
    private String platformDeviceGbId;
    private String dstUrl;
    private int dstPort;
    private String ssrc;
    private String streamId;
    /**
     * 是否使用UDP，1=UDP，0=TCP
     */
    private Integer isUdp;
    /**
     * 是否启用RTCP，1=启用，0=禁用
     */
    private Integer rtcp;
}
