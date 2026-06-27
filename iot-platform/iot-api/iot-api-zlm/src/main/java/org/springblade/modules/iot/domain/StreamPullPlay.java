package org.springblade.modules.iot.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;

import lombok.Data;

import java.io.Serializable;

/**
 * 流拉流播放
 *
 * @FileName StreamPullPlay
 * @Description
 * @Author fengcheng
 * @date 2026-04-02
 **/
@Data
@TableName("")
@Table(value = "", comment = "")
public class StreamPullPlay implements Serializable{

    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 应用名称
     */
    private String app;

    /**
     * 流名称
     */
    private String stream;

    /**
     * 播放地址
     */
    private String url;

    /**
     * 转协议时是否开启音频
     */
    private boolean enable_audio;

    /**
     * 是否允许 mp4 录制
     */
    private boolean enable_mp4;

    /**
     * rtsp 拉流时，拉流方式，0：tcp，1：udp，2：组播
     */
    private String rtp_type;

    /**
     * 超时时间
     */
    private int timeOut;

    /** 当前拉流使用的流媒体服务ID */
    private String mediaServerId;

    /** 拉流代理时zlm返回的key，用于停止拉流代理 */
    private String streamKey;

    /** 是否是回放流 */
    private boolean isPlayback;
}
