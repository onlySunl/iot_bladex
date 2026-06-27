package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * 海康Isup设备录像下载请求
 */
@Data
public class HaikangIsupRecordDownloadRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */

    /**
     * 通道ID
     */
    private int channelId;

    /**
     * 开始时间（格式：yyyy-MM-dd HH:mm:ss）
     */
    private String startTime;

    /**
     * 结束时间（格式：yyyy-MM-dd HH:mm:ss）
     */
    private String endTime;

    /**
     * 录像文件类型（0-主码流，1-子码流1，2-子码流2）
     */
    private Integer recordFileType;
}
