package org.springblade.modules.nvr.common.sdp;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * SDP整体信息，适配GB28181
 */
@Data
public class SdpInfo {
    // v= 版本号，一般固定0
    private Integer version;
    // o= 会话所有者
    private String origin;
    // s= 会话名称
    private String sessionName;
    // c= 连接信息 IP地址
    private String connectionAddress;
    // t= 会话时间
    private String timing;
    // 媒体列表（视频、音频）
    private List<MediaItem> mediaItemList = new ArrayList<>();

    @Data
    public static class MediaItem {
        // m=video 554 RTP/AVP 96
        private String mediaType; // video audio
        private int port;
        private String transport; // RTP/AVP
        private List<Integer> payloadTypes;

        // a=rtpmap:96 H264/90000
        private String codecName;
        private int clockRate;
        // a=fmtp:96 profile-level-id
        private String fmtp;
        // a=sendonly / recvonly / sendrecv
        private String direction;
    }
}