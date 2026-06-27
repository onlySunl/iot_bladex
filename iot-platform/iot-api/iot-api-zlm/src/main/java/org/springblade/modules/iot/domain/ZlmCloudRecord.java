package org.springblade.modules.iot.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springblade.core.annotation.Excel;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 云端录像对象 zlm_cloud_record
 * 
 * @author fengcheng
 * @date 2026-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ZlmCloudRecord extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */

    /** 应用名 */
    private String app;

    /** 流id */
    private String stream;

    /** 健全ID */
    private String callId;

    /** 开始时间 */
    private Long startTime;

    /** 结束时间 */
    private Long endTime;

    /** ZLM Id */
    private String mediaServerId;

    /** 所属服务ID */
    private String serverId;

    /** 文件名称 */
    private String fileName;

    /** 文件夹 */
    private String folder;

    /** 文件路径 */
    private String filePath;

    /** 收藏，收藏的文件不移除 */
    private Boolean collect;

    /** 文件大小 */
    private Long fileSize;

    /** 文件时长 */
    private Double timeLen;

    /** 查询开始时间 */
    private String queryStartTime;

    /** 查询结束时间 */
    private String queryEndTime;
}
