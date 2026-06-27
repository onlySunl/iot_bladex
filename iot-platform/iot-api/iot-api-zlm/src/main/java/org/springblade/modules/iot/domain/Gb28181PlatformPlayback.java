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
@TableName("")
@Table(value = "", comment = "")
public class Gb28181PlatformPlayback implements Serializable {
    private static final long serialVersionUID = 1L;

    private QsDevice qsDevice;
    private String platformDeviceGbId;
    private String dstUrl;
    private int dstPort;
    private String ssrc;
    private String streamId;
    private Integer isUdp;
    private Integer rtcp;
    private Long startTime;
    private Long endTime;
}
