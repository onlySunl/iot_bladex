package org.springblade.modules.iot.haikangisup.handler;


import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;
import org.springblade.modules.iot.common.domain.RtpServerParam;
import org.springblade.modules.iot.haikangisup.haikang.stream.HCISUPStream;
import org.springblade.modules.iot.haikangisup.manager.StreamManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 预览流处理器 - 每个预览会话独立的回调实例
 * 注意：不使用 @Component/@Service 注解，避免单例模式导致的状态混乱
 * 
 * 数据流程：海康SDK回调 -> PS解复用 -> H.264 NAL单元 -> RTP封装 -> UDP发送
 */
@Slf4j
public class PreviewStreamHandler implements HCISUPStream.PREVIEW_DATA_CB {
    // 状态变量
    private int seqNum = 0;
    private int currentTimestamp = 0;
    private final int CLOCK_RATE = 90000;
    private final int FPS = 25;
    private final int TIMESTAMP_INCREMENT = CLOCK_RATE / FPS; // 3600
    private long invokeCount = 0; // 回调调用计数
    private long videoDataCount = 0; // 视频数据回调计数
    private long totalBytesSent = 0; // 总发送字节数
    private long startTime = 0; // 开始时间
    private long lastStatsTime = 0; // 上次统计时间

    // 存储每个预览句柄对应的连接信息
    private class RtpConnection {
        int seqNum = 0;
        int timestamp = 0;
        int rtpPort = 0;
        String ssrc;
        DatagramSocket udpSocket;
        InetAddress targetAddress;
        PSStreamDemuxer psDemuxer = new PSStreamDemuxer(); // PS解复用器
    }

    // 使用线程安全的 Map 存储每个句柄对应的连接
    public final Map<Integer, RtpConnection> connectionMap = new ConcurrentHashMap<>();

    @Override
    public void invoke(int iPreviewHandle, HCISUPStream.NET_EHOME_PREVIEW_CB_MSG pPreviewCBMsg, Pointer pUserData) throws IOException {
        invokeCount++;
        if (invokeCount <= 3 || invokeCount % 100 == 0) {
            log.info("[RTP回调] 第{}次调用，句柄: {}, byDataType: {}, dwDataLen: {}", 
                    invokeCount, iPreviewHandle, pPreviewCBMsg.byDataType, pPreviewCBMsg.dwDataLen);
        }
        
        // 通过 iPreviewHandle 获取 sessionID
        Integer sessionID = StreamManager.previewHandSAndSessionIDandMap.get(iPreviewHandle);
        if (sessionID == null) {
            return;
        }

        // 通过 sessionID 获取对rtpServerParam
        RtpServerParam rtpServerParam = StreamManager.luserIdAndRtpServerParamMap.get(sessionID);

        if (rtpServerParam == null) {
            return;
        }

        // 获取或创建该句柄对应的连接
        RtpConnection connection = connectionMap.computeIfAbsent(iPreviewHandle, handle -> {
            RtpConnection conn = new RtpConnection();
            try {
                // 1. 解析目标地址
                conn.targetAddress = InetAddress.getByName(rtpServerParam.getIp());
                // 2. 创建本地 UDP Socket
                conn.udpSocket = new DatagramSocket();
                // 3. 设置 SSRC
                conn.ssrc = rtpServerParam.getSsrc();
                conn.rtpPort = rtpServerParam.getPort();
                log.info("预览句柄: {} ==== RTP Socket创建成功，ip: {}, 端口: {}, ssrc: {}, sessionID: {}", iPreviewHandle, rtpServerParam.getIp(), rtpServerParam.getPort(), rtpServerParam.getSsrc(), sessionID);
            } catch (Exception e) {
                log.error("创建RTP Socket失败，句柄: {}, ip: {}, 端口: {}, ssrc: {}, sessionID: {}", iPreviewHandle, rtpServerParam.getIp(), rtpServerParam.getPort(), rtpServerParam.getSsrc(), sessionID, e);
                return null;
            }
            return conn;
        });

        if (connection == null || connection.udpSocket == null || connection.targetAddress == null) {
            log.error("RTP连接不可用，句柄: {}", iPreviewHandle);
            return;
        }

        byte[] dataStream = pPreviewCBMsg.pRecvdata.getByteArray(0, pPreviewCBMsg.dwDataLen);
        if (dataStream != null && dataStream.length > 0) {
            if (pPreviewCBMsg.byDataType == 2) {
                videoDataCount++;
                if (seqNum == 0) {
                    log.info("[RTP发送] 首次收到视频数据，数据长度: {}, 目标: {}:{}", 
                            pPreviewCBMsg.dwDataLen, connection.targetAddress.getHostAddress(), connection.rtpPort);
                    // 打印前16字节的十六进制，用于分析数据格式
                    StringBuilder hex = new StringBuilder();
                    for (int i = 0; i < Math.min(16, dataStream.length); i++) {
                        hex.append(String.format("%02X ", dataStream[i] & 0xFF));
                    }
                    log.info("[PS数据格式] 前16字节: {}", hex.toString());
                }
                
                // 将PS数据送入解复用器提取NAL单元
                List<byte[]> nalUnits = connection.psDemuxer.processPSData(dataStream);
                if (!nalUnits.isEmpty()) {
                    if (videoDataCount <= 3) {
                        log.info("[PS解复用] 提取到{}个NAL单元", nalUnits.size());
                    }
                    for (byte[] nalUnit : nalUnits) {
                        sendNalUnitAsRtp(connection, nalUnit);
                    }
                }
                
                // 每5秒输出一次统计信息
                long now = System.currentTimeMillis();
                if (startTime == 0) {
                    startTime = now;
                    lastStatsTime = now;
                }
                if (now - lastStatsTime >= 5000) {
                    long elapsed = (now - startTime) / 1000;
                    long interval = (now - lastStatsTime) / 1000;
                    log.info("[RTP统计] 运行{}秒, 回调{}次, 视频数据{}次, 发送包{}个, 总字节{}, 速率: {}包/秒, {}字节/秒",
                            elapsed, invokeCount, videoDataCount, seqNum, totalBytesSent,
                            interval > 0 ? seqNum / interval : 0,
                            interval > 0 ? totalBytesSent / interval : 0);
                    lastStatsTime = now;
                }
            } else {
                log.info("[RTP发送] 收到非视频数据，byDataType: {}, 数据长度: {}", pPreviewCBMsg.byDataType, pPreviewCBMsg.dwDataLen);
            }
        } else {
            log.info("[RTP发送] 收到空数据，dataStream: {}, dwDataLen: {}", 
                    dataStream == null ? "null" : "empty", pPreviewCBMsg.dwDataLen);
        }
    }
    
    /**
     * 将NAL单元封装为RTP包并发送
     */
    private void sendNalUnitAsRtp(RtpConnection connection, byte[] nalUnit) throws IOException {
        if (nalUnit == null || nalUnit.length == 0) {
            return;
        }
        
        // 诊断：打印NAL单元类型
        int nalType = nalUnit[0] & 0x1F;
        String nalTypeName;
        switch (nalType) {
            case 1: nalTypeName = "Non-IDR Slice"; break;
            case 5: nalTypeName = "IDR Slice"; break;
            case 6: nalTypeName = "SEI"; break;
            case 7: nalTypeName = "SPS"; break;
            case 8: nalTypeName = "PPS"; break;
            default: nalTypeName = "Unknown"; break;
        }
        if (seqNum < 10) {
            log.info("[NAL诊断] NAL类型: {} ({}), 大小: {}, 首字节: 0x{}", 
                    nalType, nalTypeName, nalUnit.length, String.format("%02X", nalUnit[0]));
        }
        
        byte pt = 96; // H.264 Payload Type
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
        
        byte[] rtpPacket = new byte[1400];
        rtpPacket[0] = (byte) 0x80; // Version 2, no padding, no extension, no CSRC
        
        int offset = 0;
        int dataSize = nalUnit.length;
        
        while (dataSize > 0) {
            int chunkSize = Math.min(maxPayloadSize, dataSize);
            boolean isLastFragment = (dataSize <= maxPayloadSize);
            
            seqNum++;
            byte[] seqBytes = shortToBytes(seqNum);
            
            // RTP Header
            rtpPacket[0] = (byte) 0x80;
            rtpPacket[1] = (byte) (pt & 0x7F);
            rtpPacket[2] = seqBytes[0];
            rtpPacket[3] = seqBytes[1];
            System.arraycopy(tsBytes, 0, rtpPacket, 4, 4);
            System.arraycopy(ssrc, 0, rtpPacket, 8, 4);
            
            // Marker bit: 最后一个包设为1
            if (isLastFragment) {
                rtpPacket[1] = (byte) (rtpPacket[1] | 0x80);
            } else {
                rtpPacket[1] = (byte) (rtpPacket[1] & 0x7F);
            }
            
            // Payload
            System.arraycopy(nalUnit, offset, rtpPacket, 12, chunkSize);
            
            // 发送
            int packetLength = 12 + chunkSize;
            DatagramPacket packet = new DatagramPacket(rtpPacket, packetLength, connection.targetAddress, connection.rtpPort);
            connection.udpSocket.send(packet);
            totalBytesSent += packetLength;
            
            if (seqNum <= 5) {
                log.info("[RTP发送] 发送第{}个RTP包，NAL大小: {}, 分片大小: {}, 目标: {}:{}", 
                        seqNum, nalUnit.length, chunkSize, connection.targetAddress.getHostAddress(), connection.rtpPort);
            }
            
            offset += chunkSize;
            dataSize -= chunkSize;
        }
    }

    /**
     * 关闭指定句柄的RTP连接
     *
     * @param iPreviewHandle 预览句柄
     */
    public void close(int iPreviewHandle) {
        RtpConnection connection = connectionMap.remove(iPreviewHandle);
        if (connection != null && connection.udpSocket != null && !connection.udpSocket.isClosed()) {
            try {
                connection.udpSocket.close();
                log.info("关闭RTP连接成功，句柄: {}", iPreviewHandle);
            } catch (Exception e) {
                log.error("关闭RTP连接失败，句柄: {}", iPreviewHandle, e);
            }
        }
    }

    /**
     * 将int值转换为4字节的字节数组 大端序
     *
     * @param value 要转换的int值
     * @return 包含该int值的4字节表示形式的字节数组
     */
    private byte[] intToBytes(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }

    /**
     * 将short值转换为2字节的字节数组 大端序
     *
     * @param value 要转换的int值
     * @return 包含该int值的2字节表示形式的字节数组
     */
    private byte[] shortToBytes(int value) {
        return new byte[]{
                (byte) (value >>> 8),
                (byte) value
        };
    }
}
