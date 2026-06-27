package org.springblade.modules.iot.domain.zlm;

import lombok.Data;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class RTPServerParam extends CustomBaseEntity implements Serializable {

    private ZlmMediaServer mediaServer;
    private StreamInfo streamInfo;
    private String app;
    private String streamId;
    private String presetSsrc;
    private boolean ssrcCheck;
    private boolean playback;
    private Integer port;
    private boolean onlyAuto;
    private boolean disableAudio;
    private boolean reUsePort;
    private String ssrc;
    private String type;

    /**
     * tcp模式，0时为不启用tcp监听，1时为启用tcp监听，2时为tcp主动连接模式
     */
    private Integer tcpMode;

    /**
     * 通道号
     */
    private Integer channel;

    /**
     * 回放开始时间
     */
    private String startTime;

    /**
     * 回放结束时间
     */
    private String endTime;
    private static final long serialVersionUID = 1L;
}
