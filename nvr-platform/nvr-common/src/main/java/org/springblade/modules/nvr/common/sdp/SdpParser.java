package org.springblade.modules.nvr.common.sdp;

import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SdpParser {

    /**
     * 入口方法，传入完整sdp字符串解析
     * @param sdpContent sdp原始文本
     * @return SdpInfo
     */
    public static SdpInfo parse(String sdpContent) {
        if (!StringUtils.hasText(sdpContent)) {
            throw new IllegalArgumentException("sdp内容不能为空");
        }
        SdpInfo sdpInfo = new SdpInfo();
        SdpInfo.MediaItem currentMedia = null;

        // 分行处理，兼容windows换行\r\n
        List<String> lines = Arrays.stream(sdpContent.split("\\r?\\n"))
                .map(String::trim)
                .filter(StringUtils::hasText)
                .collect(Collectors.toList());

        for (String line : lines) {
            if (line.length() < 2 || line.charAt(1) != '=') {
                continue;
            }
            char prefix = line.charAt(0);
            String value = line.substring(2);
            switch (prefix) {
                case 'v':
                    sdpInfo.setVersion(Integer.parseInt(value));
                    break;
                case 'o':
                    sdpInfo.setOrigin(value);
                    break;
                case 's':
                    sdpInfo.setSessionName(value);
                    break;
                case 'c':
                    // c=IN IP4 192.168.1.100，取出ip
                    String[] cParts = value.split("\\s+");
                    if (cParts.length >=3) {
                        sdpInfo.setConnectionAddress(cParts[2]);
                    }
                    break;
                case 't':
                    sdpInfo.setTiming(value);
                    break;
                case 'm':
                    // m=video 554 RTP/AVP 96 97
                    currentMedia = new SdpInfo.MediaItem();
                    String[] mParts = value.split("\\s+");
                    currentMedia.setMediaType(mParts[0]);
                    currentMedia.setPort(Integer.parseInt(mParts[1]));
                    currentMedia.setTransport(mParts[2]);
                    List<Integer> payloads = Arrays.stream(mParts).skip(3)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    currentMedia.setPayloadTypes(payloads);
                    sdpInfo.getMediaItemList().add(currentMedia);
                    break;
                case 'a':
                    if (currentMedia == null) {
                        break;
                    }
                    if (value.startsWith("rtpmap:")) {
                        // rtpmap:96 H264/90000
                        String rtpmapBody = value.substring("rtpmap:".length());
                        String[] rtpmapParts = rtpmapBody.split("\\s+");
                        String codecAndRate = rtpmapParts[1];
                        String[] cr = codecAndRate.split("/");
                        currentMedia.setCodecName(cr[0]);
                        currentMedia.setClockRate(Integer.parseInt(cr[1]));
                    } else if (value.startsWith("fmtp:")) {
                        currentMedia.setFmtp(value.substring("fmtp:".length()));
                    } else if ("sendonly".equals(value) || "recvonly".equals(value)
                            || "sendrecv".equals(value)) {
                        currentMedia.setDirection(value);
                    }
                    break;
                default:
                    break;
            }
        }
        return sdpInfo;
    }

    /**
     * 反向构造SDP字符串，用于构造GB28181应答SDP
     */
    public static String buildSdp(SdpInfo sdpInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("v=").append(sdpInfo.getVersion()).append("\r\n");
        sb.append("o=").append(sdpInfo.getOrigin()).append("\r\n");
        sb.append("s=").append(sdpInfo.getSessionName()).append("\r\n");
        sb.append("c=IN IP4 ").append(sdpInfo.getConnectionAddress()).append("\r\n");
        sb.append("t=").append(sdpInfo.getTiming()).append("\r\n");
        for (SdpInfo.MediaItem media : sdpInfo.getMediaItemList()) {
            sb.append("m=").append(media.getMediaType()).append(" ")
                    .append(media.getPort()).append(" ")
                    .append(media.getTransport()).append(" ")
                    .append(media.getPayloadTypes().stream().map(String::valueOf)
                            .collect(Collectors.joining(" "))).append("\r\n");
            if (StringUtils.hasText(media.getCodecName())) {
                sb.append("a=rtpmap:").append(media.getPayloadTypes().get(0))
                        .append(" ").append(media.getCodecName()).append("/")
                        .append(media.getClockRate()).append("\r\n");
            }
            if (StringUtils.hasText(media.getFmtp())) {
                sb.append("a=fmtp:").append(media.getPayloadTypes().get(0))
                        .append(" ").append(media.getFmtp()).append("\r\n");
            }
            if (StringUtils.hasText(media.getDirection())) {
                sb.append("a=").append(media.getDirection()).append("\r\n");
            }
        }
        return sb.toString();
    }
}