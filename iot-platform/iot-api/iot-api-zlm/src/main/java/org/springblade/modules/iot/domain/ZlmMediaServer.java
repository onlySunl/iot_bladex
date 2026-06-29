package org.springblade.modules.iot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.mpe.autotable.annotation.Table;


import org.springblade.core.tool.utils.Func;
import org.springblade.modules.iot.common.entity.CustomBaseEntity;
import org.springblade.modules.iot.config.ZLMServerConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;

/**
 * 流媒体服务信息
 *
 * @author fengcheng
 */
@Data
@TableName("zlm_media_server")
@EqualsAndHashCode(callSuper = true)
@Table(value = "zlm_media_server", comment = "ZLM流媒体服务器表")
public class ZlmMediaServer extends CustomBaseEntity {

    private static final long serialVersionUID = 1L;

    /** IP */
    @TableField(value = "ip")
    @AutoColumn(comment = "IP", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String ip;

    /** hook使用的IP（zlm访问WVP使用的IP） */
    @TableField(value = "hook_ip")
    @AutoColumn(comment = "hook使用的IP", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String hookIp = "127.0.0.1";

    /** SDP IP */
    @TableField(value = "sdp_ip")
    @AutoColumn(comment = "SDP IP", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String sdpIp;

    /** 流IP */
    @TableField(value = "stream_ip")
    @AutoColumn(comment = "流IP", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String streamIp;

    /** HTTP端口 */
    @TableField(value = "http_port")
    @AutoColumn(comment = "HTTP端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer httpPort;

    /** HTTPS端口 */
    @TableField(value = "http_ssl_port")
    @AutoColumn(comment = "HTTPS端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer httpSslPort;

    /** RTMP端口 */
    @TableField(value = "rtmp_port")
    @AutoColumn(comment = "RTMP端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer rtmpPort;

    /** RTMPS端口 */
    @TableField(value = "rtmp_ssl_port")
    @AutoColumn(comment = "RTMPS端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer rtmpSslPort;

    /** RTP收流端口（单端口模式有用） */
    @TableField(value = "rtp_proxy_port")
    @AutoColumn(comment = "RTP收流端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer rtpProxyPort;

    /** RTSP端口 */
    @TableField(value = "rtsp_port")
    @AutoColumn(comment = "RTSP端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer rtspPort;

    /** RTSPS端口 */
    @TableField(value = "rtsp_ssl_port")
    @AutoColumn(comment = "RTSPS端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer rtspSslPort;

    /** flv端口 */
    @TableField(value = "flv_port")
    @AutoColumn(comment = "flv端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer flvPort;

    /** https-flv端口 */
    @TableField(value = "flv_ssl_port")
    @AutoColumn(comment = "https-flv端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer flvSslPort;

    /** mp4端口 */
    @TableField(value = "mp4_port")
    @AutoColumn(comment = "mp4端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer mp4Port;

    /** mp4 SSL端口 */
    @TableField(value = "mp4_ssl_port")
    @AutoColumn(comment = "mp4 SSL端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer mp4SslPort;

    /** ws-flv端口 */
    @TableField(value = "ws_flv_port")
    @AutoColumn(comment = "ws-flv端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer wsFlvPort;

    /** wss-flv端口 */
    @TableField(value = "ws_flv_ssl_port")
    @AutoColumn(comment = "wss-flv端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer wsFlvSslPort;

    /** 1078收流端口（单端口模式有用） */
    @TableField(value = "jtt_proxy_port")
    @AutoColumn(comment = "1078收流端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer jttProxyPort;

    /** 是否开启自动配置ZLM */
    @TableField(value = "auto_config")
    @AutoColumn(comment = "是否开启自动配置ZLM", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Boolean autoConfig;

    /** ZLM鉴权参数 */
    @TableField(value = "secret")
    @AutoColumn(comment = "ZLM鉴权参数", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String secret;

    /** 类型： zlm/abl */
    @TableField(value = "type")
    @AutoColumn(comment = "类型", length = 20, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String type;

    /** 是否使用多端口模式 */
    @TableField(value = "rtp_enable")
    @AutoColumn(comment = "是否使用多端口模式", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Boolean rtpEnable;

    /** 多端口RTP收流端口范围 */
    @TableField(value = "rtp_port_range")
    @AutoColumn(comment = "多端口RTP收流端口范围", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String rtpPortRange;

    /** RTP发流端口范围 */
    @TableField(value = "send_rtp_port_range")
    @AutoColumn(comment = "RTP发流端口范围", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String sendRtpPortRange;

    /** assist服务端口 */
    @TableField(value = "record_assist_port")
    @AutoColumn(comment = "assist服务端口", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer recordAssistPort;

    /** 是否是默认ZLM */
    @TableField(value = "default_server")
    @AutoColumn(comment = "是否是默认ZLM", length = 1, defaultValueType = DefaultValueEnum.NULL)
    private Boolean defaultServer;

    /** keepalive hook触发间隔,单位秒 */
    @TableField(value = "hook_alive_interval")
    @AutoColumn(comment = "keepalive hook触发间隔", length = 10, defaultValueType = DefaultValueEnum.NULL)
    private Float hookAliveInterval;

    /** 录像存储路径 */
    @TableField(value = "record_path")
    @AutoColumn(comment = "录像存储路径", length = 255, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String recordPath;

    /** 录像存储时长 */
    @TableField(value = "record_day")
    @AutoColumn(comment = "录像存储时长", length = 11, defaultValueType = DefaultValueEnum.NULL)
    private Integer recordDay;

    /** 转码的前缀 */
    @TableField(value = "transcode_suffix")
    @AutoColumn(comment = "转码的前缀", length = 50, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String transcodeSuffix;

    /** 服务Id */
    @AutoColumn(comment = "服务Id", length = 100, defaultValueType = DefaultValueEnum.EMPTY_STRING)
    private String serverId;


    public ZlmMediaServer() {
    }

    public ZlmMediaServer(ZLMServerConfig zlmServerConfig, String sipIp) {
        id = zlmServerConfig.getGeneralMediaServerId();
        ip = zlmServerConfig.getIp();
        hookIp = ObjectUtils.isEmpty(zlmServerConfig.getHookIp()) ? sipIp : zlmServerConfig.getHookIp();
        sdpIp = ObjectUtils.isEmpty(zlmServerConfig.getSdpIp()) ? zlmServerConfig.getIp() : zlmServerConfig.getSdpIp();
        streamIp = ObjectUtils.isEmpty(zlmServerConfig.getStreamIp()) ? zlmServerConfig.getIp() : zlmServerConfig.getStreamIp();
        httpPort = zlmServerConfig.getHttpPort();
        httpSslPort = zlmServerConfig.getHttpSSLport();
        rtmpPort = zlmServerConfig.getRtmpPort();
        rtmpSslPort = zlmServerConfig.getRtmpSslPort();
        rtpProxyPort = zlmServerConfig.getRtpProxyPort();
        rtspPort = zlmServerConfig.getRtspPort();
        rtspSslPort = zlmServerConfig.getRtspSSlport();
        autoConfig = true;
        secret = zlmServerConfig.getApiSecret();
        hookAliveInterval = zlmServerConfig.getHookAliveInterval();
        rtpEnable = false;
        rtpPortRange = zlmServerConfig.getPortRange().replace("_", ",");
        recordAssistPort = 0;
        transcodeSuffix = zlmServerConfig.getTranscodeSuffix();
    }
}
