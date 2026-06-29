package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;

/**
 * 云端录像对象 zlm_cloud_record
 * 
 * @author fengcheng
 * @date 2026-04-10
 */
@Data
@TableName("zlm_cloud_record")
@EqualsAndHashCode(callSuper = true)
@Table(value = "zlm_cloud_record", comment = "云端录像表")
public class ZlmCloudRecord extends CustomBaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableField(value = "id")
    @AutoColumn(comment = "主键ID", length = 20)
    private Long id;

    /** 应用名 */
    @TableField(value = "app")
    @AutoColumn(comment = "应用名", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String app;

    /** 流id */
    @TableField(value = "stream")
    @AutoColumn(comment = "流id", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String stream;

    /** 健全ID */
    @TableField(value = "call_id")
    @AutoColumn(comment = "健全ID", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String callId;

    /** 开始时间 */
    @TableField(value = "start_time")
    @AutoColumn(comment = "开始时间", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Long startTime;

    /** 结束时间 */
    @TableField(value = "end_time")
    @AutoColumn(comment = "结束时间", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Long endTime;

    /** ZLM Id */
    @TableField(value = "media_server_id")
    @AutoColumn(comment = "ZLM Id", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private Long mediaServerId;

    /** 所属服务ID */
    @TableField(value = "server_id")
    @AutoColumn(comment = "所属服务ID", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String serverId;

    /** 文件名称 */
    @TableField(value = "file_name")
    @AutoColumn(comment = "文件名称", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String fileName;

    /** 文件夹 */
    @TableField(value = "folder")
    @AutoColumn(comment = "文件夹", length = 500, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String folder;

    /** 文件路径 */
    @TableField(value = "file_path")
    @AutoColumn(comment = "文件路径", length = 500, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String filePath;

    /** 收藏，收藏的文件不移除 */
    @TableField(value = "collect")
    @AutoColumn(comment = "收藏，收藏的文件不移除", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Boolean collect;

    /** 文件大小 */
    @TableField(value = "file_size")
    @AutoColumn(comment = "文件大小", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Long fileSize;

    /** 文件时长 */
    @TableField(value = "time_len")
    @AutoColumn(comment = "文件时长", length = 20, defaultValueType = DefaultValueEnum.NULL)
    private Double timeLen;

    /** 查询开始时间（非数据库字段） */
    @TableField(exist = false)
    private String queryStartTime;

    /** 查询结束时间（非数据库字段） */
    @TableField(exist = false)
    private String queryEndTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("app", getApp())
            .append("stream", getStream())
            .toString();
    }
}
