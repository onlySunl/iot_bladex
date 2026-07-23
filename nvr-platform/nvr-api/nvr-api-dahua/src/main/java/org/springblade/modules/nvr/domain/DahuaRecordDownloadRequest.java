package org.springblade.modules.nvr.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.entity.CustomBaseEntity;

import java.io.Serializable;

/**
 * 大华设备录像下载请求
 */
@Data
@TableName("dahua_record_download_request")
@EqualsAndHashCode(callSuper = true)
@AutoTable(value = "dahua_record_download_request", comment = "大华设备录像下载请求表")
public class DahuaRecordDownloadRequest extends CustomBaseEntity implements Serializable {

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
     * 录像文件类型（0-主码流，1-子码流1，2-子码流2，参考SDK文档）
     */
    private Integer recordFileType;
}
