package org.springblade.modules.iot.haikangisup.handler;

import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.common.domain.RtpServerParam;
import org.springblade.modules.iot.haikangisup.haikang.stream.HCISUPStream;
import org.springblade.modules.iot.haikangisup.manager.StreamManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 回放流处理器 - 每个回放会话独立的回调实例
 * 注意：不使用 @Component/@Service 注解，避免单例模式导致的状态混乱
 *
 * 数据流程：海康SDK回调 -> PS解复用 -> H.264 NAL单元 -> RTP封装(RFC 6184) -> UDP发送
 */
@Slf4j
public class PlaybackStreamHandler implements HCISUPStream.PLAYBACK_DATA_CB {
    // 常量
    private static final int CLOCK_RATE = 90000;
    private static final int FPS = 25;
    private static final int TIMESTAMP_INCREMENT = CLOCK_RATE / FPS; // 3600

    // 下载相关的latch map - 静态，用于跨实例访问
    public static final Map<String, CountDownLatch> downloadLatchMap = new ConcurrentHashMap<>();

    // 状态变量
    private int seqNum = 0;
    private int currentTimestamp = 0;
    private long totalBytesSent = 0;
    private long invokeCount = 0; // 回调调用计数
    private long videoDataCount = 0; // 视频数据回调计数

    // 存储每个回放句柄对应的连接信息
    private class RtpConnection {
        int rtpPort = 0;
        String ssrc;
        DatagramSocket udpSocket;
        InetAddress targetAddress;
        PSStreamDemuxer psDemuxer = new PSStreamDemuxer(); // PS解复用器
    }

    // 使用线程安全的 Map 存储每个句柄对应的连接
    public final Map<Integer, RtpConnection> connectionMap = new ConcurrentHashMap<>();

    @Override
    public boolean invoke(int iPlayBackLinkHandle, HCISUPStream.NET_EHOME_PLAYBACK_DATA_CB_INFO pDataCBInfo, Pointer pUserData) {
        invokeCount++;
        
        // 通过 iPlayBackLinkHandle 获取 sessionID
        Integer sessionID = StreamManager.playbackHandSAndSessionIDandMap.get(iPlayBackLinkHandle);
        if (sessionID == null) {
            if (invokeCount <= 3) {
                log.warn("[回放回调] 句柄: {} 未找到sessionID", iPlayBackLinkHandle);
            }
            return true;
        }

        // 获取回放类型
        String type = StreamManager.playbackUserIDandTypeMap.get(sessionID);

        if ("download".equals(type)) {
            // 下载模式：将数据写入文件
            String filePath = StreamManager.playbackUserIDandFilePathMap.get(sessionID);
            if (filePath == null) {
                log.error("下载文件路径未设置，sessionID: {}", sessionID);
                return true;
            }

            if (pDataCBInfo.dwType == 2 && pDataCBInfo.pData != null && pDataCBInfo.dwDataLen > 0) {
                try {
                    File file = new File(filePath);
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }

                    try (FileOutputStream fos = new FileOutputStream(file, true)) {
                        byte[] data = pDataCBInfo.pData.getByteArray(0, pDataCBInfo.dwDataLen);
                        fos.write(data);
                    }
                } catch (Exception e) {
                    log.error("写入录像文件失败，sessionID: {}, filePath: {}", sessionID, filePath, e);
                }
            } else if (pDataCBInfo.dwType == 3) {
                log.info("收到回放停止信令（下载模式），句柄: {}, sessionID: {}", iPlayBackLinkHandle, sessionID);
                String downloadKey = StreamManager.sessionIDandDownloadKeyMap.get(sessionID);
                if (downloadKey != null) {
                    CountDownLatch latch = downloadLatchMap.get(downloadKey);
                    if (latch != null) {
                        latch.countDown();
                        log.info("已唤醒下载等待线程，downloadKey: {}", downloadKey);
                    }
                }
            }
            return true;
        }

        // 回放模式：PS解复用 -> H.264 NAL提取 -> RTP发送
        RtpServerParam rtpServerParam = StreamManager.luserIdAndPlaybackRtpServerParamMap.get(sessionID);
        if (rtpServerParam == null) {
            if (invokeCount <= 3) {
                log.warn("[回放回调] sessionID: {} 未找到rtpServerParam", sessionID);
            }
            return true;
        }

        // 获取或创建该句柄对应的连接
        RtpConnection connection = connectionMap.computeIfAbsent(iPlayBackLinkHandle, handle -> {
            RtpConnection conn = new RtpConnection();
            try {
                conn.targetAddress = InetAddress.getByName(rtpServerParam.getIp());
                conn.udpSocket = new DatagramSocket();
                conn.ssrc = rtpServerParam.getSsrc();
                conn.rtpPort = rtpServerParam.getPort();
                log.info("回放句柄: {} ==== RTP Socket创建成功，ip: {}, 端口: {}, ssrc: {}, sessionID: {}",
                        iPlayBackLinkHandle, rtpServerParam.getIp(), rtpServerParam.getPort(),
                        rtpServerParam.getSsrc(), sessionID);
            } catch (Exception e) {
                log.error("创建RTP Socket失败，句柄: {}, ip: {}, 端口: {}, ssrc: {}, sessionID: {}",
                        iPlayBackLinkHandle, rtpServerParam.getIp(), rtpServerParam.getPort(),
                        rtpServerParam.getSsrc(), sessionID, e);
                return null;
            }
            return conn;
        });

        if (connection == null || connection.udpSocket == null || connection.targetAddress == null) {
            log.error("RTP连接不可用，句柄: {}", iPlayBackLinkHandle);
            return true;
        }

        // 诊断日志：前几次回调打印详细信息
        if (invokeCount <= 5) {
            log.info("[回放回调] 第{}次调用，句柄: {}, dwType: {}, dwDataLen: {}, pData: {}",
                    invokeCount, iPlayBackLinkHandle, pDataCBInfo.dwType, pDataCBInfo.dwDataLen,
                    pDataCBInfo.pData != null ? "非空" : "空");
        }

        // 检查数据类型
        if (pDataCBInfo.dwType == 2) { // 码流数据
            int dwBufSize = pDataCBInfo.dwDataLen;
            Pointer pBuffer = pDataCBInfo.pData;

            if (pBuffer != null && dwBufSize > 0) {
                videoDataCount++;
                try {
                    byte[] data = pBuffer.getByteArray(0, dwBufSize);

                    // 首次打印数据格式
                    if (videoDataCount <= 2) {
                        StringBuilder hex = new StringBuilder();
                        for (int i = 0; i < Math.min(32, data.length); i++) {
                            hex.append(String.format("%02X ", data[i] & 0xFF));
                        }
                        log.info("[回放PS数据] 第{}次视频数据，长度: {}, 前{}字节: {}",
                                videoDataCount, data.length, Math.min(32, data.length), hex.toString().trim());
                    }

                    // 将PS数据送入解复用器提取NAL单元
                    List<byte[]> nalUnits = connection.psDemuxer.processPSData(data);
                    if (!nalUnits.isEmpty()) {
                        if (videoDataCount <= 2) {
                            log.info("[回放PS解复用] 提取到{}个NAL单元", nalUnits.size());
                            for (int i = 0; i < nalUnits.size(); i++) {
                                byte[] nal = nalUnits.get(i);
                                int nalType = nal.length > 0 ? (nal[0] & 0x1F) : -1;
                                log.info("[回放PS解复用] NAL#{}: 长度={}, 类型={}", i, nal.length, nalType);
                            }
                        }
                        for (byte[] nalUnit : nalUnits) {
                            sendNalUnitAsRtp(connection, nalUnit);
                        }
                    }
                } catch (Exception e) {
                    log.error("[海康ISUP回放] 处理回调数据异常，句柄: {}", iPlayBackLinkHandle, e);
                }
            }
        } else if (pDataCBInfo.dwType == 3) { // 回放停止信令
            log.info("收到回放停止信令，句柄: {}", iPlayBackLinkHandle);
            close(iPlayBackLinkHandle);
        } else {
            if (invokeCount <= 5) {
                log.warn("[回放回调] 未知数据类型 dwType: {}", pDataCBInfo.dwType);
            }
        }

        return true;
    }

    /**
     * 将NAL单元封装为RTP包并发送 (RFC 6184 H.264)
     * 与PreviewStreamHandler使用相同的封装逻辑
     */
    private void sendNalUnitAsRtp(RtpConnection connection, byte[] nalUnit) throws IOException {
        if (nalUnit == null || nalUnit.length == 0) {
            return;
        }

        byte pt = 98; // H.264 Payload Type (ZLM配置: h264_pt=98)
        int maxPayloadSize = 1400 - 12; // MTU 1400 - RTP Header 12

        // 时间戳
        int frameTimestamp = currentTimestamp;
        currentTimestamp += TIMESTAMP_INCREMENT;

        // SSRC
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(Integer.parseInt(connection.ssrc));
        byte[] ssrc = buffer.array();
        byte[] tsBytes = intToBytes(frameTimestamp);

        int nalType = nalUnit[0] & 0x1F;
        int nalRefIdc = (nalUnit[0] >> 5) & 0x03;

        byte[] rtpPacket = new byte[1400];
        rtpPacket[0] = (byte) 0x80; // Version 2

        int dataSize = nalUnit.length;

        if (dataSize <= maxPayloadSize) {
            // 单包模式：Single NAL Unit Packet
            rtpPacket[1] = (byte) (pt & 0x7F);
            rtpPacket[1] = (byte) (rtpPacket[1] | 0x80); // Marker bit = 1

            byte[] seqBytes = shortToBytes(++seqNum);
            rtpPacket[2] = seqBytes[0];
            rtpPacket[3] = seqBytes[1];
            System.arraycopy(tsBytes, 0, rtpPacket, 4, 4);
            System.arraycopy(ssrc, 0, rtpPacket, 8, 4);

            System.arraycopy(nalUnit, 0, rtpPacket, 12, dataSize);

            int packetLength = 12 + dataSize;
            DatagramPacket packet = new DatagramPacket(rtpPacket, packetLength, connection.targetAddress, connection.rtpPort);
            connection.udpSocket.send(packet);
            totalBytesSent += packetLength;

            if (seqNum <= 3) {
                String nalTypeName;
                switch (nalType) {
                    case 5: nalTypeName = "IDR"; break;
                    case 7: nalTypeName = "SPS"; break;
                    case 8: nalTypeName = "PPS"; break;
                    case 6: nalTypeName = "SEI"; break;
                    case 1: nalTypeName = "Non-IDR"; break;
                    default: nalTypeName = "Type_" + nalType; break;
                }
                log.info("[回放RTP] 单包发送 NAL={} 大小={} 目标={}:{}", 
                        nalTypeName, dataSize, connection.targetAddress.getHostAddress(), connection.rtpPort);
            }
        } else {
            // 分片模式：FU-A (Fragmentation Unit A)
            // FU indicator: forbidden_zero_bit(1) + nal_ref_idc(2) + type(5)=28
            // FU header: S(1) + E(1) + R(1) + type(5)
            byte fuIndicator = (byte) ((nalUnit[0] & 0x80) | (nalRefIdc << 5) | 28);

            int dataOffset = 1; // 跳过原始NAL头
            int remaining = dataSize - 1;
            boolean isFirst = true;

            while (remaining > 0) {
                int fuPayloadSize = maxPayloadSize - 2; // FU indicator + FU header
                int chunkSize = Math.min(fuPayloadSize, remaining);
                boolean isLast = (remaining <= fuPayloadSize);

                seqNum++;
                byte[] seqBytes = shortToBytes(seqNum);

                // RTP Header
                rtpPacket[0] = (byte) 0x80;
                rtpPacket[1] = (byte) (pt & 0x7F);
                rtpPacket[2] = seqBytes[0];
                rtpPacket[3] = seqBytes[1];
                System.arraycopy(tsBytes, 0, rtpPacket, 4, 4);
                System.arraycopy(ssrc, 0, rtpPacket, 8, 4);

                // Marker bit: 最后一个分片设为1
                if (isLast) {
                    rtpPacket[1] = (byte) (rtpPacket[1] | 0x80);
                }

                // FU indicator
                rtpPacket[12] = fuIndicator;

                // FU header
                byte fuHeader = (byte) (nalType & 0x1F);
                if (isFirst) {
                    fuHeader |= 0x80; // S bit
                    isFirst = false;
                }
                if (isLast) {
                    fuHeader |= 0x40; // E bit
                }
                rtpPacket[13] = fuHeader;

                // FU payload
                System.arraycopy(nalUnit, dataOffset, rtpPacket, 14, chunkSize);

                int packetLength = 14 + chunkSize;
                DatagramPacket packet = new DatagramPacket(rtpPacket, packetLength, connection.targetAddress, connection.rtpPort);
                connection.udpSocket.send(packet);
                totalBytesSent += packetLength;

                dataOffset += chunkSize;
                remaining -= chunkSize;
            }

            if (seqNum <= 3) {
                log.info("[回放RTP] FU-A分片发送 NAL类型={} 原始大小={} 目标={}:{}", 
                        nalType, dataSize, connection.targetAddress.getHostAddress(), connection.rtpPort);
            }
        }
    }

    /**
     * 关闭指定句柄的RTP连接
     */
    public void close(int iPlayBackLinkHandle) {
        RtpConnection connection = connectionMap.remove(iPlayBackLinkHandle);
        if (connection != null && connection.udpSocket != null && !connection.udpSocket.isClosed()) {
            try {
                connection.udpSocket.close();
                log.info("关闭回放RTP连接成功，句柄: {}, 已发送{}字节", iPlayBackLinkHandle, totalBytesSent);
            } catch (Exception e) {
                log.error("关闭RTP连接失败，句柄: {}", iPlayBackLinkHandle, e);
            }
        }
    }

    /**
     * 关闭所有连接
     */
    public void closeAll() {
        for (Integer handle : connectionMap.keySet()) {
            close(handle);
        }
    }

    // 工具方法
    private byte[] intToBytes(int value) {
        return new byte[]{
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value
        };
    }

    private byte[] shortToBytes(int value) {
        return new byte[]{
                (byte) (value >> 8),
                (byte) value
        };
    }
}
