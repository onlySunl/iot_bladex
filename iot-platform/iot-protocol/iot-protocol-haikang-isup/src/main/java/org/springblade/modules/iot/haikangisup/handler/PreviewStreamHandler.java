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
 * 数据流程：海康SDK回调 -> PS解复用 -> H.264/H.265 NAL单元 -> RTP封装 -> UDP发送
 * 
 * 支持H.264 (RFC 6184) 和 H.265/HEVC (RFC 7798) RTP格式
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
            log.debug("[RTP回调] 第{}次调用，句柄: {}, byDataType: {}, dwDataLen: {}",
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
                log.debug("预览句柄: {} ==== RTP Socket创建成功，ip: {}, 端口: {}, ssrc: {}, sessionID: {}", iPreviewHandle, rtpServerParam.getIp(), rtpServerParam.getPort(), rtpServerParam.getSsrc(), sessionID);
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
                if (connection.seqNum == 0) {
                    log.debug("[RTP发送] 首次收到视频数据，数据长度: {}, 目标: {}:{}",
                            pPreviewCBMsg.dwDataLen, connection.targetAddress.getHostAddress(), connection.rtpPort);
                    // 打印前32字节的十六进制，用于分析数据格式
                    StringBuilder hex = new StringBuilder();
                    for (int i = 0; i < Math.min(32, dataStream.length); i++) {
                        hex.append(String.format("%02X ", dataStream[i] & 0xFF));
                    }
                    log.debug("[PS数据格式] 前{}字节: {}", Math.min(32, dataStream.length), hex.toString().trim());
                }
                
                // 将PS数据送入解复用器提取NAL单元
                List<byte[]> nalUnits = connection.psDemuxer.processPSData(dataStream);
                if (!nalUnits.isEmpty()) {
                    if (connection.seqNum == 0) {
                        log.debug("[PS解复用] 首次提取到{}个NAL单元", nalUnits.size());
                        for (int i = 0; i < nalUnits.size(); i++) {
                            byte[] nal = nalUnits.get(i);
                            int nalType = nal.length > 0 ? (nal[0] & 0x1F) : -1;
                            log.debug("[PS解复用] NAL#{}: 长度={}, 类型={}, 前8字节={}",
                                    i, nal.length, nalType, 
                                    bytesToHex(nal, Math.min(8, nal.length)));
                        }
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
                    log.debug("[RTP统计] 运行{}秒, 回调{}次, 视频{}次, 发送RTP包{}个, 总字节{}, 速率: {}包/秒, {}字节/秒",
                            elapsed, invokeCount, videoDataCount, connection.seqNum, totalBytesSent,
                            interval > 0 ? connection.seqNum / interval : 0,
                            interval > 0 ? totalBytesSent / interval : 0);
                    lastStatsTime = now;
                }
            } else {
                if (invokeCount <= 3) {
                    log.debug("[RTP发送] 收到非视频数据，byDataType: {}, 数据长度: {}", pPreviewCBMsg.byDataType, pPreviewCBMsg.dwDataLen);
                }
            }
        } else {
            log.debug("[RTP发送] 收到空数据，dataStream: {}, dwDataLen: {}",
                    dataStream == null ? "null" : "empty", pPreviewCBMsg.dwDataLen);
        }
    }
    
    private String bytesToHex(byte[] bytes, int len) {
        if (bytes == null || len == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(String.format("%02X ", bytes[i] & 0xFF));
        }
        return sb.toString().trim();
    }
    
    /**
     * 检测是否为H.265/HEVC NAL单元
     * H.265 NAL头为2字节：forbidden_zero_bit(1) + nal_unit_type(6) + nuh_layer_id(6) + nuh_temporal_id_plus1(3)
     */
    private boolean isHevcNalUnit(byte[] nalUnit) {
        if (nalUnit == null || nalUnit.length < 2) return false;
        
        // H.265 NAL unit type在第一个字节的bit 1-6
        int hevcNalType = (nalUnit[0] >> 1) & 0x3F;
        
        // 检查是否为有效的H.265 NAL类型
        // VPS=32, SPS=33, PPS=34, IDR_W_RADL=19, IDR_N_LP=20, CRA_NUT=21
        // SEI: PREFIX_SEI_NUT=39, SUFFIX_SEI_NUT=40
        // 非VCL: RSV_NVCL41-47, UNSPEC48-63
        return hevcNalType == 32 || hevcNalType == 33 || hevcNalType == 34 ||  // VPS, SPS, PPS
               hevcNalType == 19 || hevcNalType == 20 || hevcNalType == 21 ||  // IDR, CRA
               hevcNalType == 39 || hevcNalType == 40 ||                        // SEI
               (hevcNalType >= 0 && hevcNalType <= 18) ||                       // Trail, TSA, STSA, etc.
               (hevcNalType >= 32 && hevcNalType <= 35);                        // VPS, SPS, PPS, AP, FP
    }
    
    /**
     * 将NAL单元封装为RTP包并发送
     * 支持H.264和H.265/HEVC
     */
    private void sendNalUnitAsRtp(RtpConnection connection, byte[] nalUnit) throws IOException {
        if (nalUnit == null || nalUnit.length == 0) {
            return;
        }
        
        // 检测是否为H.265
        boolean isHevc = isHevcNalUnit(nalUnit);
        
        if (isHevc) {
            sendHevcNalAsRtp(connection, nalUnit);
        } else {
            sendAvcNalAsRtp(connection, nalUnit);
        }
    }
    
    /**
     * 发送H.265/HEVC NAL单元为RTP包 (RFC 7798)
     */
    private void sendHevcNalAsRtp(RtpConnection connection, byte[] nalUnit) throws IOException {
        // H.265 Payload Type - 需要根据ZLM配置调整
        // 通常H.265使用PT=96或自定义值
        byte pt = 96; // HEVC Payload Type
        
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
        
        // H.265 NAL头为2字节
        int nalHeaderSize = 2;
        int nalType = (nalUnit[0] >> 1) & 0x3F;
        String nalTypeName;
        switch (nalType) {
            case 32: nalTypeName = "VPS"; break;
            case 33: nalTypeName = "SPS"; break;
            case 34: nalTypeName = "PPS"; break;
            case 19: nalTypeName = "IDR_W_RADL"; break;
            case 20: nalTypeName = "IDR_N_LP"; break;
            case 21: nalTypeName = "CRA"; break;
            case 39: nalTypeName = "PREFIX_SEI"; break;
            case 40: nalTypeName = "SUFFIX_SEI"; break;
            default: nalTypeName = "Type_" + nalType; break;
        }
        
        if (seqNum < 10) {
            log.debug("[HEVC诊断] NAL类型: {} ({}), 大小: {}, 首2字节: 0x{} 0x{}",
                    nalType, nalTypeName, nalUnit.length, 
                    String.format("%02X", nalUnit[0]), String.format("%02X", nalUnit[1]));
        }
        
        byte[] rtpPacket = new byte[1400];
        rtpPacket[0] = (byte) 0x80; // Version 2
        
        int offset = 0;
        int dataSize = nalUnit.length;
        
        if (dataSize <= maxPayloadSize) {
            // 单包模式：直接发送NAL单元
            rtpPacket[1] = (byte) (pt & 0x7F);
            rtpPacket[1] = (byte) (rtpPacket[1] | 0x80); // Marker bit = 1 (单包)
            
            byte[] seqBytes = shortToBytes(++seqNum);
            rtpPacket[2] = seqBytes[0];
            rtpPacket[3] = seqBytes[1];
            System.arraycopy(tsBytes, 0, rtpPacket, 4, 4);
            System.arraycopy(ssrc, 0, rtpPacket, 8, 4);
            
            // Payload: 完整的NAL单元（包含2字节NAL头）
            System.arraycopy(nalUnit, 0, rtpPacket, 12, dataSize);
            
            int packetLength = 12 + dataSize;
            DatagramPacket packet = new DatagramPacket(rtpPacket, packetLength, connection.targetAddress, connection.rtpPort);
            connection.udpSocket.send(packet);
            totalBytesSent += packetLength;
            
            if (seqNum <= 5) {
                log.debug("[HEVC RTP] 单包发送，NAL大小: {}, 目标: {}:{}",
                        dataSize, connection.targetAddress.getHostAddress(), connection.rtpPort);
            }
        } else {
            // 分片模式：使用FU (Fragmentation Unit)
            // H.265 FU格式：PayloadHdr(2字节) + FU type(1字节) + FU数据
            // PayloadHdr: 保持原始NAL头的forbidden_zero_bit和nal_unit_type，但nal_unit_type改为49(FU)
            // FU type字节: S(1bit) + E(1bit) + FuType(6bits)
            
            int fuHeaderSize = 3; // 2字节PayloadHdr + 1字节FU type
            int fuPayloadSize = maxPayloadSize - fuHeaderSize;
            
            // 保存原始NAL头
            byte nalHeader0 = nalUnit[0];
            byte nalHeader1 = nalUnit[1];
            
            // 构建FU PayloadHdr：nal_unit_type = 49 (FU)
            byte fuPayloadHdr0 = (byte) ((nalHeader0 & 0x81) | (49 << 1)); // 保持forbidden_zero_bit，设置nal_unit_type=49
            byte fuPayloadHdr1 = nalHeader1; // 保持nuh_layer_id和nuh_temporal_id_plus1
            
            int dataOffset = nalHeaderSize; // 跳过原始NAL头
            int remaining = dataSize - nalHeaderSize;
            boolean isFirst = true;
            
            while (remaining > 0) {
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
                
                // FU PayloadHdr
                rtpPacket[12] = fuPayloadHdr0;
                rtpPacket[13] = fuPayloadHdr1;
                
                // FU type字节：S(Start) + E(End) + FuType(6bits)
                byte fuType = (byte) (nalType & 0x3F);
                if (isFirst) {
                    fuType |= 0x80; // S bit
                    isFirst = false;
                }
                if (isLast) {
                    fuType |= 0x40; // E bit
                }
                rtpPacket[14] = fuType;
                
                // FU数据
                System.arraycopy(nalUnit, dataOffset, rtpPacket, 15, chunkSize);
                
                int packetLength = 15 + chunkSize;
                DatagramPacket packet = new DatagramPacket(rtpPacket, packetLength, connection.targetAddress, connection.rtpPort);
                connection.udpSocket.send(packet);
                totalBytesSent += packetLength;
                
                if (seqNum <= 5) {
                    log.debug("[HEVC RTP] 分片发送，NAL大小: {}, 分片大小: {}, 目标: {}:{}",
                            dataSize, chunkSize, connection.targetAddress.getHostAddress(), connection.rtpPort);
                }
                
                dataOffset += chunkSize;
                remaining -= chunkSize;
            }
        }
    }
    
    /**
     * 发送H.264/AVC NAL单元为RTP包 (RFC 6184)
     */
    private void sendAvcNalAsRtp(RtpConnection connection, byte[] nalUnit) throws IOException {
        byte pt = 98; // H.264 Payload Type (ZLM rtp_proxy配置: h264_pt=98)
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
        
        // 诊断：打印NAL单元类型
        int nalType = nalUnit[0] & 0x1F;
        int nalRefIdc = (nalUnit[0] >> 5) & 0x03;
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
            log.debug("[AVC诊断] NAL类型: {} ({}), 大小: {}, 首字节: 0x{}",
                    nalType, nalTypeName, nalUnit.length, String.format("%02X", nalUnit[0]));
        }
        
        byte[] rtpPacket = new byte[1400];
        rtpPacket[0] = (byte) 0x80; // Version 2
        
        int dataSize = nalUnit.length;
        
        if (dataSize <= maxPayloadSize) {
            // 单包模式：直接发送NAL单元（Single NAL Unit Packet）
            rtpPacket[1] = (byte) (pt & 0x7F);
            rtpPacket[1] = (byte) (rtpPacket[1] | 0x80); // Marker bit = 1
            
            byte[] seqBytes = shortToBytes(++seqNum);
            rtpPacket[2] = seqBytes[0];
            rtpPacket[3] = seqBytes[1];
            System.arraycopy(tsBytes, 0, rtpPacket, 4, 4);
            System.arraycopy(ssrc, 0, rtpPacket, 8, 4);
            
            // Payload: 完整的NAL单元
            System.arraycopy(nalUnit, 0, rtpPacket, 12, dataSize);
            
            int packetLength = 12 + dataSize;
            DatagramPacket packet = new DatagramPacket(rtpPacket, packetLength, connection.targetAddress, connection.rtpPort);
            connection.udpSocket.send(packet);
            totalBytesSent += packetLength;
            
            if (seqNum <= 5) {
                log.debug("[AVC RTP] 单包发送，NAL大小: {}, 目标: {}:{}",
                        dataSize, connection.targetAddress.getHostAddress(), connection.rtpPort);
            }
        } else {
            // 分片模式：使用FU-A (Fragmentation Unit A)
            // FU-A格式：FU indicator(1字节) + FU header(1字节) + FU payload
            // FU indicator: forbidden_zero_bit(1) + nal_ref_idc(2) + type(5)=28
            // FU header: S(1) + E(1) + R(1) + type(5)
            
            int fuHeaderSize = 2; // FU indicator + FU header
            int fuPayloadSize = maxPayloadSize - fuHeaderSize;
            
            // 构建FU indicator：保持forbidden_zero_bit和nal_ref_idc，type=28(FU-A)
            byte fuIndicator = (byte) ((nalUnit[0] & 0x80) | (nalRefIdc << 5) | 28);
            
            int dataOffset = 1; // 跳过原始NAL头
            int remaining = dataSize - 1;
            boolean isFirst = true;
            
            while (remaining > 0) {
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
                
                // FU header：S(Start) + E(End) + R(Reserved=0) + type(5bits)
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
                
                if (seqNum <= 5) {
                    log.debug("[AVC RTP] FU-A分片发送，NAL大小: {}, 分片大小: {}, S:{}, E:{}, 目标: {}:{}",
                            dataSize, chunkSize, (fuHeader & 0x80) != 0, (fuHeader & 0x40) != 0,
                            connection.targetAddress.getHostAddress(), connection.rtpPort);
                }
                
                dataOffset += chunkSize;
                remaining -= chunkSize;
            }
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
                log.debug("关闭RTP连接成功，句柄: {}", iPreviewHandle);
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
